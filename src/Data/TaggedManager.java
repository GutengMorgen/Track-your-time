package src.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaggedManager {
	public static String TEMPLATE = "Templates.csv";
	
	/**
	 * TODO: fix the empty line on Templates.csv
	 * crear un metodo para comprobar si la ultima/primera linea de los .csv estan en blanco
	 * para agregarlo si lo estan
	 * @param tag
	 * @param template
	 */
	public static void createLine(String tag, String template) {
		String newLine = String.join(";", tag, template);
		
		//maybe first read the file and then add the newLine into a new list and then write the list
//		writeIntoFile(TEMPLATE, "\n" + newLine, StandardOpenOption.APPEND);
		List<String> lines = readLines(TEMPLATE);
		lines.add(newLine);
		writeToFile(TEMPLATE, lines);
	}
	
	public static void updateLine(String tag, String template, int index) {
		String newLine = String.join(";", tag, template);
		
		List<String> lines = readLines(TEMPLATE);
		lines.set(index, newLine);
		writeToFile(TEMPLATE, lines);
	}
	
	public static void deleteLine(int index) {
		List<String> lines = readLines(TEMPLATE);
		lines.remove(index);
		writeToFile(TEMPLATE, lines);
	}
	
	/**
	 * to write a list of lines into a specific file
	 * @param file the file that will be write. the file have to be in Data directory
	 * @param lines a list of all line to write
	 */
	private static void writeToFile(String file, List<String> lines) {
		try {
			Files.write(DataManager.getDataPath(file), lines);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void writeIntoFile(String file, String line, StandardOpenOption Option) {
		try {
			Files.writeString(DataManager.getDataPath(file), line, Option);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static List<String> readLines(String file, String... filter){
		
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
