package mobigest.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mobigest.utils.Utils;

public class ConfigProperties {

	private static final Logger logger = LogManager.getLogger(ConfigProperties.class);
	private String cfgPropertiesFileName = "mobigest/properties/config.properties";
	private Properties cfgProperties = null;

	public ConfigProperties() {

		cfgProperties = new Properties();

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream cfgPropertiesStream = classLoader.getResourceAsStream(cfgPropertiesFileName);

		try {
			cfgProperties.load(cfgPropertiesStream);
			cfgPropertiesStream.close();
		} catch (IOException io) {
			logger.error(Utils.getStackTrace(io));
		}

	}

	public String getParam(String propertyName) {
		if (null == propertyName) {
			return null;
		}
		if (null == this.cfgProperties) {
			return null;
		} else {
			return cfgProperties.getProperty(propertyName);
		}
	}
}
