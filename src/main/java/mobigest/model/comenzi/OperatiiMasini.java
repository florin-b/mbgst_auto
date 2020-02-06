package mobigest.model.comenzi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import mobigest.beans.Info;
import mobigest.beans.MasinaNeincarcata;
import mobigest.database.connection.DBManager;
import mobigest.queries.articole.SqlQueries;
import mobigest.utils.DateUtils;
import mobigest.utils.Utils;

public class OperatiiMasini {

	private static final Logger logger = LogManager.getLogger(OperatiiMasini.class);

	public List<MasinaNeincarcata> getMasiniNeincarcate(String filiala) {

		List<MasinaNeincarcata> listMasini = new ArrayList<>();

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.getMasiniNeincarcate())) {

			stmt.clearParameters();
			stmt.setString(1, filiala);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				MasinaNeincarcata masina = new MasinaNeincarcata();

				masina.setNrBorderou(rs.getString("numarb"));
				masina.setNrMasina(rs.getString("masina").replace("-", "").replace(" ", "").trim().toUpperCase());
				masina.setStatusText("");

				if (rs.getString("numarb_ant") != null)
					masina.setStatusText(rs.getString("numarb_ant") + " nefinalizat");

				listMasini.add(masina);
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return listMasini;

	}

	public boolean setSfarsitIncarcare(String borderou, String codSofer) {

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.setSfarsitIncarcare())) {

			stmt.clearParameters();
			stmt.setString(1, borderou);
			stmt.setString(2, codSofer);

			stmt.executeQuery();

			return true;

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			return false;
		}
	}

	public Info saveSfarsitIncImg(String borderou, String img) {

		Info info = new Info();
		info.setInfoTime(DateUtils.getStrTimeStampRo());

		try (Connection conn = new DBManager().getProdDataSource().getConnection();
				PreparedStatement stmt = conn.prepareStatement(SqlQueries.setSfarsitIncImg())) {

			InputStream istream = new ByteArrayInputStream(img.getBytes(StandardCharsets.UTF_8));

			stmt.clearParameters();
			stmt.setString(1, borderou);
			stmt.setBinaryStream(2, istream);

			stmt.executeQuery();

			info.setSucces(true);

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			info.setSucces(false);
		}

		return info;
	}

	public String getPlateNumber_old() {

		String resp = "";
		String token = "ac5cb10a09abf35213fa2e3ba236542b29d53fc8";
		File file1 = new File(getClass().getClassLoader().getResource("car1.jpg").getFile());

		try {
			HttpResponse<String> response = Unirest.post("https://api.platerecognizer.com/v1/plate-reader/")
					.header("Authorization", "Token " + token).field("upload", file1).asString();
			resp = response.getBody();
		} catch (Exception e) {
			resp = e.toString();
			logger.error(Utils.getStackTrace(e));
		}

		return resp;

	}

	public String getNrAutoFromImg(String codUser, String filiala, String plateImg) {

		String plateNumber = "-1";

		try {

			String path = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path, "UTF-8");

			String[] pathArr = fullPath.split("/WEB-INF/classes/");
			fullPath = pathArr[0];

			String base64Image = plateImg.split(",")[1];

			byte[] bytes = Base64.getDecoder().decode(base64Image);

			String filePath = fullPath + File.separator + DateUtils.getStrTimeStamp() + codUser + ".jpg";

			File file = new File(filePath);

			OutputStream os = new FileOutputStream(file);
			os.write(bytes);

			os.close();

			String token = "ac5cb10a09abf35213fa2e3ba236542b29d53fc8";

			HttpResponse<String> response = Unirest.post("https://api.platerecognizer.com/v1/plate-reader/")
					.header("Authorization", "Token " + token).field("upload", file).asString();

			plateNumber = Utils.getPlateNrFromJson(response.getBody());

			file.delete();

		} catch (Exception e) {
			plateNumber = "-1";
			logger.error(Utils.getStackTrace(e));
		}

		return plateNumber.toUpperCase();

	}

	public MasinaNeincarcata valideazaMasina(String codUser, String filiala, String plateImg) {

		MasinaNeincarcata masina = new MasinaNeincarcata();
		String nrAuto = getNrAutoFromImg(codUser, filiala, plateImg);

		masina.setStatusText("");

		if (!("-1").equals(nrAuto)) {

			masina.setNrMasina(nrAuto);
			List<MasinaNeincarcata> listMasini = getMasiniNeincarcate(filiala);

			for (MasinaNeincarcata m : listMasini) {
				if (m.getNrMasina().equalsIgnoreCase(nrAuto)) {
					masina.setNrBorderou(m.getNrBorderou());
					masina.setStatusText(m.getStatusText());
					break;
				}
			}

		}

		masina.setDataOra(DateUtils.getStrTimeStampRo());

		return masina;

	}

	public Info trateazaSfarsitIncarcare(String document, String codSofer, String image) {
		Info info = new Info();

		boolean isIncarcare = new OperatiiMasini().setSfarsitIncarcare(document, codSofer);

		if (isIncarcare)
			info = new OperatiiMasini().saveSfarsitIncImg(document, image);
		else {
			info.setInfoTime(DateUtils.getStrTimeStampRo());
			info.setSucces(false);
		}

		return info;
	}

}
