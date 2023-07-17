package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import javax.swing.JTextField;

public class ReadWriteData {
	public void WriteData(String tag, String description){
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		
		StringBuilder builder = new StringBuilder();
			builder.append(dateTime.getDate(now));
			builder.append(";");
			builder.append(dateTime.getTime(now));
			builder.append(";Tag: ");
			builder.append(tag);
			builder.append(";Description: ");
			builder.append(description);
		String line = builder.toString();
		
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
	
	public void setTextField(JTextField out) {
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
