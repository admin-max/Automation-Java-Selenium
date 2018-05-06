package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	public static Properties loadfile(String fileName) {
		InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);
		if (stream == null) {
			throw new RuntimeException("Could not load the file: " + fileName);
		}

		Properties props = new Properties();

		try {
			props.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("error in loading files: " + fileName);
		}
		return props;
	}
}
