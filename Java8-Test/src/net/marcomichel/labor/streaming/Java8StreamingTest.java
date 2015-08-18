package net.marcomichel.labor.streaming;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Java8StreamingTest {

	private static final String INPUT_FILE_NAME    = "D:/workspaces/test/netLog.1505111649.02.log";
	private static final String PATTERN_EXPRESSION = "\\{\\d+:\\d+:\\d+\\} System:\\d+\\(([^)]+)\\).*";
	private static final Pattern PATTERN           = Pattern.compile(PATTERN_EXPRESSION);
	
	private int startLine = 1;
	private String currentSystem = "";
	
	public void filterFile() {
		try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_NAME), Charset.forName("ISO-8859-1"))) {
			lines
			.skip(startLine)
			.filter(line -> {
				Matcher m = PATTERN.matcher(line); 
				return m.matches();
			})
			.map(line -> {
				Matcher m = PATTERN.matcher(line); 
				m.matches(); 
				return m.group(1);
			})
			.filter(system -> {
				if (system.equals(currentSystem)) {
					return false;
				} else {
					currentSystem = system;
					return true;
				}
			})
			.map(system -> "jumped to " + system)
			.forEach(System.out::println);
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		}
	}
	
	public void filterList() {
		final List<String> testData = Arrays.asList("Marco", "Tine", "Julia", "Daniel", "Manuel", "Rebekka");

		testData.stream()
			.filter(s -> s.length() > 5)
			.map(s -> "Hallo " + s)
			.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		Java8StreamingTest p = new Java8StreamingTest();
		System.out.println("##Test filter file");
		p.filterFile();
		System.out.println("##Test filter list");
		p.filterList();
	}

}
