package src.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaggedManager {
	public static String TEMPLATE = "Templates.csv";
	
	public static void createLine(String tag, String template) {
		String newLine = String.join(";", tag, template);
	}
	
	public static void updateLine(String tag, String template, int index) {
		String newLine = String.join(";", tag, template);
	}
	
	public static void deleteLine(int index) {
		List<String> lines = readLines(TEMPLATE);
//		String line = lines.get(index); 
		
		System.out.println("before to remove the index line. count: " + lines.size());
		for (String line : lines) {
			System.out.println(line);
		}
		
		lines.remove(index);

		System.out.println("after to remove the index line. count:" + lines.size());
		for (String line : lines) {
			System.out.println(line);
		}
//		Files.write(DataManager.getDataPath(TEMPLATE), lines);
	}
	
	
	
	/**
	 * read all lines from a file
	 * @param file the file to read all lines
	 * @return a list of all lines
	 */
	private static List<String> readLines(String file){
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(DataManager.getDataPath(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
	public static List<String> readLines(String file, Boolean useFilter, String... filter){
		
		//0 = tags; 1 = templates
		List<String> lineData = new ArrayList<String>();
		try {
			List<String> lines = Files.readAllLines(DataManager.getDataPath(file));
			
			for (String line : lines) {
				if (filter.equals("")) {
					lineData.add(line.split(";")[0]);
					
				} else if(filter.equals(TEMPLATE)) {
					lineData.add(line.split(";")[1]);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineData;
	}
}
