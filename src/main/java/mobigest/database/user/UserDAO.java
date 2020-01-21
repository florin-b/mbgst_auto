package mobigest.database.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobigest.beans.Login;
import mobigest.beans.User;
import mobigest.database.connection.DBManager;
import mobigest.helpers.HelperUser;
import mobigest.queries.articole.SqlQueries;
import mobigest.queries.articole.UserSqlQueries;
import mobigest.utils.MailOperations;
import mobigest.utils.Utils;

public class UserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	public User validateUser(Login login) {

		User user = new User();

		String storedProcedure = "{ call web_pkg.wlogin(?,?,?,?,?,?,?,?,?,?) }";
		int logonStatus = 0;

		try (Connection conn = new DBManager().getProdDataSource().getConnection(); CallableStatement callableStatement = conn.prepareCall(storedProcedure);) {

			callableStatement.setString(1, login.getUsername().trim());
			callableStatement.setString(2, login.getPassword().trim());

			callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(8, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(10, java.sql.Types.NUMERIC);

			callableStatement.execute();
			logonStatus = callableStatement.getInt(3);

			if (logonStatus == 3) {

				user.setFiliala(callableStatement.getString(5));

				String codAgent = callableStatement.getString(8);

				for (int i = 0; i < 8 - callableStatement.getString(8).length(); i++) {
					codAgent = "0" + codAgent;
				}

				user.setCodPers(codAgent);
				user.setNume(getNumeAngajat(conn, codAgent));
				user.setTipAcces(callableStatement.getString(6));
				user.setUnitLog(getUnitLogAngajat(conn, codAgent));
				user.setTipAngajat(getTipAngajat(conn, codAgent));

				if (!user.getTipAcces().equals("5") && !user.getTipAcces().equals("11") && !user.getTipAcces().equals("38") && !user.getTipAcces().equals("16")
						&& !user.getTipAcces().equals("4")) {
					user.setSuccessLogon(false);
					user.setLogonMessage("Acces interzis");
				} else
					user.setSuccessLogon(true);

			} else {
				user.setSuccessLogon(false);
				user.setLogonMessage(HelperUser.getLogonStatus(logonStatus));
			}
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));

			MailOperations.sendMail(Utils.getStackTrace(e));
			user.setSuccessLogon(false);
			user.setLogonMessage(HelperUser.getLogonStatus(logonStatus));

			return user;

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
			MailOperations.sendMail(Utils.getStackTrace(e));
		}

		return user;
	}

	private static String getNumeAngajat(Connection conn, String angajatId) {

		String fullName = null;

		try (PreparedStatement stmt = conn.prepareStatement(UserSqlQueries.getFullName())) {

			stmt.setString(1, angajatId);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				fullName = rs.getString("nume");
			}

		} catch (Exception ex) {
			logger.error(Utils.getStackTrace(ex));

		}

		return fullName;
	}

	private static String getTipAngajat(Connection conn, String angajatId) {

		String tipPers = null;

		try (PreparedStatement stmt = conn.prepareStatement(UserSqlQueries.getTipAngajat(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, angajatId);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				tipPers = rs.getString("functie");
			}

		} catch (Exception ex) {
			logger.error(Utils.getStackTrace(ex));
		}

		return tipPers;
	}

	private static String getUnitLogAngajat(Connection conn, String angajatId) {

		String unitLog = null;

		try (PreparedStatement stmt = conn.prepareStatement(SqlQueries.getUnitLogAngajat())) {

			stmt.setString(1, angajatId);

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				unitLog = rs.getString("filiala");
			}

		} catch (Exception ex) {
			logger.error(Utils.getStackTrace(ex));
		}

		return unitLog;
	}

}
