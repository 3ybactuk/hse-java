package ru.hse.javaprogramming;

import ru.hse.javaprogramming.utils.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		if (args.length > 0) {
			String filename = args[0];
			censorFile(filename);
		} else {
			censorIO();
		}
	}

	public static void censorIO() {
		List<String> expletives = loadApplicationProperties().getExpletives();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			System.out.println(censorLine(scanner.nextLine(), expletives));
		}
	}

	public static void censorFile(String filename) {
		List<String> expletives = loadApplicationProperties().getExpletives();
		File cleanFile = new File("tmp/" + filename + ".clean");

		try (BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cleanFile)));
			 Stream<String> stream = Files.lines(Paths.get("tmp/" + filename)))
		{
			stream.forEach(ln -> writeCensoredLine(ln, expletives, bf));
		} catch (IOException e) {
			System.out.printf("File " + filename + " not found!");
		}
	}

	public static void writeCensoredLine(String string, List<String> expletives, BufferedWriter writer) {
		try {
			writer.write(censorLine(string, expletives));
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String censorLine(String string, List<String> expletives) {
		String censoredString = string;
		for (String expletive : expletives) {
			censoredString = censoredString.replace(expletive, "***");
		}

		return censoredString;
	}

	public static ApplicationProperties loadApplicationProperties() {
		Properties properties = ResourceUtils.getPropertiesFromClassPath("/application.properties");
		return new ApplicationProperties(
			// expletives=word1,word2, поэтому делаем split по запятой
			List.of(properties.getProperty("expletives").split(","))
		);
	}
}
