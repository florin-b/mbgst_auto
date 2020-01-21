package mobigest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {

	private static final Logger logger = LogManager.getLogger(Utils.class);

	public static String getStackTrace(Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

	public String getConnectionData() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mobigest/resource/db_connect.txt");

		StringBuilder result = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			while (reader.ready()) {
				String line = reader.readLine();
				result.append(line);
				result.append("#");
			}
		} catch (IOException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return result.toString();

	}

	public static String getPlateNrFromJson(String jsonString) {
		String plateNr = "-1";

		Object obj;
		try {
			obj = new JSONParser().parse(jsonString);
			org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
			JSONArray ja = (JSONArray) jo.get("results");

			if (ja.isEmpty()) {
				return "-1";

			}

			org.json.simple.JSONObject articolObject = (org.json.simple.JSONObject) ja.get(0);
			plateNr = (String) articolObject.get("plate");

		} catch (ParseException e) {
			logger.error(Utils.getStackTrace(e));
		}

		return plateNr;
	}

}
