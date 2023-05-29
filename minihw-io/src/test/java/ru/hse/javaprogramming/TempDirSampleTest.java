package ru.hse.javaprogramming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static ru.hse.javaprogramming.Main.*;

public class TempDirSampleTest {
	@Test
	void fileWrittenAndRead(@TempDir Path path) throws IOException {
		// Строка для записи
		String expected = "myString";

		// Параметр path - временная директория, resolve - вернёт Path для файла в этой директории
		Path target = path.resolve("myfile.txt");
		// Пишем expected в value
		Files.writeString(target, expected);
		// А теперь обратно читаем
		String actual = Files.readString(target);

		assertEquals(expected, actual);
	}

	@Test
	public void testCensorLine() {
		List<String> expletives = Arrays.asList("МГУ", "шпиль");
		String line = "МГУ и его известный шпиль является одной из самых узнаваемых и красивых достопримечательностей Москвы.";
		String expected = "*** и его известный *** является одной из самых узнаваемых и красивых достопримечательностей Москвы.";
		String result = censorLine(line, expletives);
		assertEquals(expected, result);
	}

	@Test
	public void testCensorFile() throws IOException {
		List<String> expletives = Arrays.asList("C#", "Microsoft");

		String filename = "tmp/test.txt";
		File file = new File(filename);

		String expectedFilename = "tmp/test.txt.clean";

		Files.writeString(file.toPath(), "C# - это мощный и гибкий язык программирования, разработанный компанией Microsoft.\n");
		Files.writeString(file.toPath(), "C#.\n");

		censorFile(filename);

		File cleanFile = new File(expectedFilename);

		List<String> cleanLines = Files.readAllLines(Paths.get(cleanFile.toURI()));
		assertEquals("*** - это мощный и гибкий язык программирования, разработанный компанией ***.", cleanLines.get(0));
		assertEquals("***.", cleanLines.get(1));
	}

	@Test
	public void testCensorIO() {
		List<String> expletives = Arrays.asList("C#", "Microsoft");
		String input = "C# был явным результатом компании Microsoft, которая решила позаимствовать идеи Java.\n";
		String expectedOutput = "*** был явным результатом компании ***, которая решила позаимствовать идеи Java.\n";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		System.setIn(inputStream);
		System.setOut(printStream);

		censorIO();

		System.setIn(System.in);
		System.setOut(System.out);

		String result = outputStream.toString();
		assertEquals(expectedOutput, result);
	}
}
