package src;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ReadWriteData {
	private String lastDataTime = "";
	
	public static void main(String[] args) {
		new ReadWriteData().writeData("taewtew", "testing again removing the empty lines is working");
	}
	
	public void writeData(String tag, String description) {
		int limit = 5;
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		String format = "Date: %s;Time: %s;Tag: %s;Description: %s";
		
		String newDescription = description.replace("\n", "\\n");
		String line = String.format(format, dateTime.getDate(now), dateTime.getTime(now), tag, newDescription);
		
		try {
			Path[] paths = {Paths.get("./Data/data.csv"), Paths.get("./Data/history.csv")};
			
			if(!Files.exists(paths[0]))
				throw new FileNotFoundException("The file data.csv doesnt exist in the directory Data");
			else if (!Files.exists(paths[1]))
				throw new FileNotFoundException("The file history.csv doesnt exist in the directory Data");
			
			for (Path path : paths) {
				BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
				writer.newLine();
				writer.write(line);
				writer.close();
			}

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
	
	public void setLastData(JTextArea out) {
		String info = readLastLine();
		out.setText(info);
	}
	
	public void setLblDescription(JLabel out) {
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		String format = "Description(%s - %s):";
		String splited = lastDataTime.split(" ")[1];
		
		String info = String.format(format, splited, dateTime.getTime(now));
		out.setText(info);
	}
	
	private String readLastLine() {
		String info = "";
		
		try {
			Path filePath = Paths.get("./Data/data.csv");
			if(!Files.exists(filePath))
				throw new FileNotFoundException("The file data.csv doesnt exist in the directory Data");

			List<String> lines = Files.readAllLines(filePath);

			if (!lines.isEmpty()) {
				//to get information of the last line in data.csv
				String lastLine = lines.get(lines.size() - 1);
				String[] lineSplited = lastLine.split(";");
				String tag = "";
				String description = "";
				
				for (String part : lineSplited) {
					if(part.contains("Tag:"))
						tag = part;
					else if(part.contains("Description:"))
						description = part;
					else if(part.contains("Time:"))
						lastDataTime = part;
				}
				
				info = tag + " - " + description;
			} else
				info = "History clean";
			
		} catch (Exception e) {
			System.out.println("An error occurred while reading the file: " + e.getMessage());
		}
		
		return info;
	}
}
