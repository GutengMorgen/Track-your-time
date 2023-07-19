package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReadWriteData {
	public void writeData(String tag, String description) {
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		String format = "Date: %s;Time: %s;Tag: %s;Description: %s";
		
		/*
		 * Description with \n replace to \\n
		*/
		String newDescription = description.replace("\n", "\\n");
		
		String line = String.format(format, dateTime.getDate(now), dateTime.getTime(now), tag, newDescription);
		
		try {
			Path filePath = Paths.get("./Data/data.csv");
			if(!Files.exists(filePath))
				throw new FileNotFoundException("The file data.csv doesnt exist in the directory Data");
			
//			Files.writeString(filePath, line, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			writer.newLine();
			writer.write(line);
			writer.close();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void WriteData(String tag, String description){
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		String format = "Date: %s;Time: %s;Tag: %s;Description: %s";
		
		/*
		 * Description with \n replace to \\n
		*/
		String newDescription = description.replace("\n", "\\n");
		
		String line = String.format(format, dateTime.getDate(now), dateTime.getTime(now), tag, newDescription);
		
		try {
			File file = new File("./Data/data.csv");
			if(file.exists()) {
				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true));
				bufferWriter.write(line);
				bufferWriter.newLine();
				bufferWriter.close();
			}
			else {
				throw new NullPointerException("The file data.csv doesnt exist in the directory Data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTextField(JTextArea out) {
		String info = ReadLastLine();
		out.setText(info);
	}
	
	private String ReadLastLine() {
		String line = "";
		String splitBy = ";";
		String lastLine = "";
		String info = "";
		
		try {
			File file = new File("./Data/data.csv");
			if(file.exists()) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				while ((line = bufferedReader.readLine()) != null)
					lastLine = line;
				
				if (!lastLine.isEmpty()) {
					String[] lineSplited = lastLine.split(splitBy);
					info = lineSplited[2] + " - " + lineSplited[3];
				} else
					info = "History clean";
				
				bufferedReader.close();
			}
			else {
				throw new NullPointerException("The file data.csv doesnt exist in the directory Data");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return info;
	}
	
	public void getFile() {
		
	}
}
