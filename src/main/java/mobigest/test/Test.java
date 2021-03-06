package mobigest.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

import mobigest.utils.DateUtils;

public class Test {

	public static void main(String[] args) {

		System.out.println(DateUtils.getTimeStampUTC());

	}

	public static void write() {
		try (OutputStream output = new FileOutputStream("config.properties")) {

			Properties prop = new Properties();

			prop.setProperty("db_host", "10.1.3.76");
			prop.setProperty("db_port", "1521");
			prop.setProperty("db_sid", "PRD");
			prop.setProperty("db_user", "WEBSAP");
			prop.setProperty("db_password", "2INTER7");

			prop.store(output, null);

			System.out.println(prop);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void read() {
		try (InputStream input = new FileInputStream("config.properties")) {

			Properties prop = new Properties();

			prop.load(input);

			System.out.println(prop.getProperty("db_host"));
			System.out.println(prop.getProperty("db_port"));
			System.out.println(prop.getProperty("db_sid"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
