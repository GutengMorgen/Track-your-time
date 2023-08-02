package src.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import src.DateHandler;

public class DataManager {
	public static String DynamicFile = "data.csv";
	public static String HistoryFile = "history.csv";
	private static String lastDate = "";
	
	public static String lineFormat(String tag, String description) {
		LocalDateTime now = LocalDateTime.now();
		String date = DateHandler.getDate(now);
		String time = DateHandler.getTime(now);
		String format = "DATE: %s;TIME: %s;TAG: %s;DESCRIPTION: %s";
		String line = String.format(format, date, time, tag, description.replace("\n", "\\n"));
		
		return line;
	}
	
	public static void appendToFile(String file, String line) {
		TaggedManager.appendToFile(file, "\n" + line, StandardOpenOption.APPEND);
	}
	
	public static void writeDynamic(String file, String line, int limit) {
		List<String> lines = TaggedManager.readLines(file);
		lines.add(line);
		
		if (lines.size() > limit) 
			lines = lines.subList(lines.size() - limit, lines.size());
		
		TaggedManager.writeToFile(file, lines);
	}
	
	/**
	 * put this method in TimerHandler
	 * i have to instance DataManager and get lastDate to do it
	 * @return
	 */
	public static String readLastDate() {
		String format = "Last Update - %s";
		String time = "";
		
		if(!lastDate.isEmpty()) {
			String splited = lastDate.split(" ")[1];
			time = String.format(format, splited);
		}
		else {
			time = String.format(format, "unknown");
		}
		
		return time;
	}

	
	/**
	 * get a line of a specific index of the file data.csv
	 * @param index if is null then will return the last line
	 * @param useFilter if is true will return a string with only tag and description
	 * @return 
	 */
	public static String readLineByIndex(Integer index, Boolean useFilter) {
		String line = "";
		
		try {
			List<String> lines = Files.readAllLines(getDataFile("data.csv"));
			
			if(index != null) 
				line = lines.get(index);
			else 
				line = lines.get(lines.size() - 1);
			
			if(useFilter) {
				String[] lineSplited = line.split(";");
				String tag = "";
				String description = "";

				for (String type : lineSplited) {
					if (type.contains("TAG:"))
						tag = type;
					else if (type.contains("DESCRIPTION:"))
						description = type;
					else if (type.contains("TIME:"))
						lastDate = type;
				}
				
				line = String.join(" - ", tag, description);
			}
		} catch (FileNotFoundException e) {
			line = e.getMessage();
		} catch (IOException e) {
			line = e.getMessage();
		}
		
		return line;
	}
	

	/**
	 * @deprecated don't get the date of the last line, witch is important
	 * @param index
	 * @param filters
	 * @return
	 */
	public static String readLineByIndex(Integer index, String... filters) {
		StringBuilder builder = new StringBuilder();
		String line = "";
		
		try {
			List<String> lines = Files.readAllLines(getDataFile("data.csv"));
			
			if(index != null) 
				line = lines.get(index);
			else 
				line = lines.get(lines.size() - 1);
			
			if(filters.length != 0) {
				String[] lineSplited = line.split(";");
				List<String> parts = new ArrayList<>();

				for (String part : lineSplited) {
					for (String filter : filters) {
						if(part.contains(filter)) {
							parts.add(part);
						}
					}
				}
				
				builder.append(String.join(" - ", parts));
			}
			
		} catch (FileNotFoundException e) {
			builder.append(e.getMessage());
		} catch (IOException e) {
			builder.append(e.getMessage());
		}
		
		return builder.toString();
	}
	
	public static Integer getSize() {
		int size = TaggedManager.readLines(DynamicFile).size();
		
		return size;
	}
	
	public static String splitFilter(String line, String filter) {
		String text = filter + " not found";
		int length = filter.length();
		
		String[] splitFilter = line.split(";");
		for (String part : splitFilter) {
			if(part.contains(filter))
				text = part.substring(length).trim().replace("\\n", "\n");
		}
		
		return text;
	}
	
	public static Path getDataFile(String file) throws FileNotFoundException {
		Path filePath = null;
		
		filePath = Paths.get("Data/" + file);
		if (!Files.exists(filePath))
			throw new FileNotFoundException("The file " + file + " does not exist in the Data/ directory.");
		
		return filePath;
	}
	
	/**
	 * i think is better to compare the txtHistory lines with the file lines and
	 * if their are different read the History file.
	 * @return a string with the lines of history.csv
	 */
	public static String readHistory() {
		StringBuilder txtBuilder = new StringBuilder();
		
		try {
			Files.lines(getDataFile(HistoryFile))
				 .forEach(e -> txtBuilder.append(e).append("\n"));
			
		} catch (FileNotFoundException e) {
			txtBuilder.append(e.getMessage());
		} catch (IOException e) {
			txtBuilder.append(e.getMessage());
		}
		
		return txtBuilder.toString().trim();
	}
}