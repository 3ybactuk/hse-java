package ru.hse.javaprogramming.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public abstract class ResourceUtils {
	private ResourceUtils() {
	}

	public static Properties getPropertiesFromClassPath(String path) {
		Objects.requireNonNull(path);

		try (InputStream inputStream = ResourceUtils.class.getResourceAsStream(path)) {
			if (inputStream == null) {
				throw new IllegalStateException("Could not find properties file '" + path + "'");
			}
			Properties properties = new Properties();
			try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
				properties.load(reader);
			}
			return properties;
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't load properties, path = '" + path + "'", e);
		}
	}
}
