package src.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaggedManager {
	public static String TemplateFile = "Templates.csv";
	
	/**
	 * TODO: fix the empty line on Templates.csv
	 * crear un metodo para comprobar si la ultima/primera linea de los .csv estan en blanco
	 * para agregarlo si lo estan
	 * @param tag
	 * @param template
	 */
	public static void createLine(String line) {
		//maybe first read the file and then add the newLine into a new list and then write the list
//		writeIntoFile(TEMPLATE, "\n" + newLine, StandardOpenOption.APPEND);
		List<String> lines = readLines(TemplateFile);
		lines.add(line);
		writeToFile(TemplateFile, lines);
	}
	
	public static void updateLine(int index, String line) {
		List<String> lines = readLines(TemplateFile);
		lines.set(index, line);
		writeToFile(TemplateFile, lines);
	}
	
	public static void deleteLine(int index) {
		List<String> lines = readLines(TemplateFile);
		lines.remove(index);
		writeToFile(TemplateFile, lines);
	}
	
	/**
	 * to write a list of lines into a specific file
	 * @param file the file that will be write. the file have to be in Data directory
	 * @param lines a list of all line to write
	 */
	public static void writeToFile(String file, List<String> lines) {
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
	
	/**
	 * 
	 * @param file
	 * @param line
	 * @param Option
	 */
	public static void writeToFile(String file, String line, StandardOpenOption Option) {
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
	 * return a list of lines without filters from a specific file in Data directory
	 * @param file the file to read all lines
	 * @return a list of all lines
	 */
	public static List<String> readLines(String file){
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
	
	public static List<String> readLines(String file, Filters filter){
		
		//0 = tags; 1 = templates
		List<String> lineData = new ArrayList<String>();
		try {
			List<String> lines = Files.readAllLines(DataManager.getDataPath(file));
			
			for (String line : lines) {
				if (filter == Filters.TAG) {
					lineData.add(line.split(";")[0]);
					
				} else if(filter == Filters.TEMPLATE) {
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
