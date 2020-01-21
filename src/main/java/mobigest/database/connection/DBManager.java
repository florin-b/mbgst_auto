package mobigest.database.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobigest.properties.ConfigProperties;
import mobigest.utils.Utils;
import oracle.jdbc.pool.OracleDataSource;

public class DBManager {

	private static final Logger logger = LogManager.getLogger(DBManager.class);

	public DataSource getProdDataSource() {
		OracleDataSource oracleDS = null;
		ConfigProperties configProperties = new ConfigProperties();
		try {
			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@" + configProperties.getParam("db_host") + ":"
					+ configProperties.getParam("db_port") + "/" + configProperties.getParam("db_sid"));
			oracleDS.setUser(configProperties.getParam("db_user"));
			oracleDS.setPassword(configProperties.getParam("db_password"));

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));

		}
		return oracleDS;
	}

	public DataSource getProdDataSourceOld() {

		String[] accesData = new Utils().getConnectionData().split("#");

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL(accesData[0]);
			oracleDS.setUser(accesData[1]);
			oracleDS.setPassword(accesData[2]);

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
		}
		return oracleDS;
	}

	public DataSource getTestDataSource() {

		OracleDataSource oracleDS = null;
		try {

			oracleDS = new OracleDataSource();
			oracleDS.setURL("jdbc:oracle:thin:@10.1.3.89:1527/TES");
			oracleDS.setUser("WEBSAP");
			oracleDS.setPassword("2INTER7");

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
		}
		return oracleDS;
	}

	public DataSource getProdDataSourceEnv() {

		InitialContext initContext;
		DataSource ds = null;
		try {

			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/prod_db");

		} catch (Exception e) {
			logger.error(Utils.getStackTrace(e));
		}
		return ds;
	}

}
