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







}
