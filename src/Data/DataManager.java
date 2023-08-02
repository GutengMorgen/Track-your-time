package src.Data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import src.DateHandler;

public class DataManager {
	public static String DataFile = "data.csv";
	public static String HistoryFile = "history.csv";
	private static String lastDate = "";

	/**
	 * 
	 * @param tag
	 * @param description
	 */
	public static void writeData(String tag, String description) {
		int limit = 5;
		LocalDateTime now = LocalDateTime.now();
		String format = "Date: %s;Time: %s;Tag: %s;Description: %s";

		String newDescription = description.replace("\n", "\\n");
		String line = String.format(format, DateHandler.getDate(now), DateHandler.getTime(now), tag, newDescription);

		try {
			Path[] paths = { Paths.get("./Data/data.csv"), Paths.get("./Data/history.csv") };

			if (!Files.exists(paths[0]))
				throw new FileNotFoundException("The file data.csv doesnt exist in the directory Data");
			else if (!Files.exists(paths[1]))
				throw new FileNotFoundException("The file history.csv doesnt exist in the directory Data");

			for (Path path : paths) {
				BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
						StandardOpenOption.APPEND);
				writer.newLine();
				writer.write(line);
				writer.close();
			}

			//borra el contenido de data.csv si la cantidad de lineas supera al limite y escribe nuevas lineas sin la ultima
			Path dataCsvPath = Paths.get("./Data/data.csv");
			List<String> lines = Files.readAllLines(dataCsvPath);
			lines.removeIf(String::isEmpty);

			if (lines.size() > limit) {
				List<String> updatedLines = lines.subList(lines.size() - limit, lines.size());
				Files.write(dataCsvPath, updatedLines, StandardCharsets.UTF_8);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public static String lineFormat(String tag, String description) {
		LocalDateTime now = LocalDateTime.now();
		String date = DateHandler.getDate(now);
		String time = DateHandler.getTime(now);
		String format = "DATE: %s;TIME: %s;TAG: %s;DESCRIPTION: %s";
		String line = String.format(format, date, time, tag, description.replace("\n", "\\n"));
		
		return line;
	}
	
	public static void appendToFile(String file, String line) {
		TaggedManager.writeToFile(file, "\n" + line, StandardOpenOption.APPEND);
	}
	
	public static void writeDynamic(String file, String line, int limit) {
		List<String> lines = TaggedManager.readLines(file);
		lines.add(line);
		
		if (lines.size() > limit) 
			lines = lines.subList(lines.size() - limit, lines.size());
		
		TaggedManager.writeToFile(file, lines);
	}
	
	public static void createLine(String file, String line) {
		List<String> lines = TaggedManager.readLines(file);
		lines.add(line);
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
			List<String> lines = Files.readAllLines(getDataPath("data.csv"));
			
			if(index != null) 
				line = lines.get(index);
			else 
				line = lines.get(lines.size() - 1);
			
			if(useFilter) {
				String[] lineSplited = line.split(";");
				String tag = "";
				String description = "";

				for (String type : lineSplited) {
					if (type.contains("Tag:"))
						tag = type;
					else if (type.contains("Description:"))
						description = type;
					else if (type.contains("Time:"))
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
			List<String> lines = Files.readAllLines(getDataPath("data.csv"));
			
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
		int size = 0;
		
		try {
			List<String> lines = Files.readAllLines(getDataPath("data.csv"));
			size = lines.size();
		} catch (Exception e) {
			size = 0;
		}
		
		return size;
	}
	
	public static String filterLine(String line, String filter) {
		String result = "no se encontro " + filter;
		int length = filter.length();
		
		String[] lineSplited = line.split(";");
		
		for (String part : lineSplited) {
			if(part.contains(filter)) {
				result = part.substring(length)
						.trim()
						.replace("\\n", "\n");
			}
		}
		
		return result;
	}
	
	public static Path getDataPath(String file) throws FileNotFoundException {
		Path filePath = null;
		
		filePath = Paths.get("Data/" + file);
		if (!Files.exists(filePath))
			throw new FileNotFoundException("The file " + file + " does not exist in the Data directory.");
		
		return filePath;
	}
	
	/**
	 * creo que seria mejor comparar el texto del txtHistorial con las lineas y falta alguna recien modificarlo
	 */
	public static String readHistory() {
		StringBuilder txtBuilder = new StringBuilder();
		
		try {
			Files.lines(getDataPath("history.csv"))
				 .forEach(e -> txtBuilder.append(e).append("\n"));
			
		} catch (FileNotFoundException e) {
			txtBuilder.append(e.getMessage());
		} catch (IOException e) {
			txtBuilder.append(e.getMessage());
		}
		
		return txtBuilder.toString().trim();
	}
}