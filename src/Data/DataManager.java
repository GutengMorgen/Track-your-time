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

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import src.DateHandler;
import src.MyItems;

public class DataManager {
	public static String TAG = "tag",TEMPLATE = "template";
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

	/**
	 * put this method in TimerHandler
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
	 * on Testing, i think is better to make a method to read lines by index
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
	 * read all lines with a filter in Templates.csv
	 * @param dataType can be DataManager.TAG or DataManager.TEMPLATE
	 * @return a String with all lines read from Templates.csv
	 */
	public static String readTemplates(String dataType) {
		StringBuilder tagBuilder = new StringBuilder();

		//0 = tags; 1 = templates
		String lineData = "";
		try {
			List<String> lines = Files.readAllLines(getDataPath("Templates.csv"));
			
			for (String line : lines) {
				if (dataType.equals(TAG)) {
					lineData = line.split(";")[0];
					
				} else if(dataType.equals(TEMPLATE)) {
					lineData = line.split(";")[1];
				}
				
				//join all individual line in a string builder
				tagBuilder.append(lineData).append("\n");
			}
			
		} catch (FileNotFoundException e) {
			tagBuilder.append(e.getMessage());
		} catch (IOException e) {
			tagBuilder.append(e.getMessage());
		}
		
		//trim() to delete the last "\n" of the tagBuilder
		return tagBuilder.toString().trim();
	}
	
	/**
	 * return a list of lines with a filter or data type from Templates.csv
	 * @param filter can be DataManager.TAG or DataManager.TEMPLATE
	 * @deprecated maybe this method will be delete
	 */
	public static List<String> linesTemplate(String filter){
		
		//0 = tags; 1 = templates
		List<String> lineData = new ArrayList<String>();
		try {
			List<String> lines = Files.readAllLines(getDataPath("Templates.csv"));
			
			for (String line : lines) {
				if (filter.equals(TAG)) {
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
	
	/**
	 * return a list of lines without filters from Templates.csv
	 */
	public static List<String> linesTemplate(){
		List<String> lineData = new ArrayList<String>();
		try {
			List<String> lines = Files.readAllLines(getDataPath("Templates.csv"));
			
			for (String line : lines) {
				lineData.add(line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lineData;
	}
	
	public static void writeTemplate(List<String> newTemplates) {
		try {
			Files.write(getDataPath("Templates.csv"), newTemplates, StandardCharsets.UTF_8);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void writeTags(List<String> newTags) {
		try {
			Files.write(getDataPath("Templates.csv"), newTags, StandardCharsets.UTF_8);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	
	/**
	 * save tags in Template.csv
	 * @param tagArray
	 */
	public static void saveTags(String[] tagArray) {
	    List<String> lines = DataManager.linesTemplate();
	    String[] tags = tagArray;

	    for (String tag : tags) {
	        boolean tagExists = false;

	        for (String line : lines) {
	            String[] split = line.split(";");
	            if (split[0].equals(tag)) {
	                tagExists = true;
	                break;
	            }
	        }

	        if (!tagExists)
	            lines.add(String.join(";", tag, "default template"));
	    }

	    DataManager.writeTags(lines);
	}
	
	/**
	 * save template of the current tag in Template.csv
	 * @param comboTags
	 * @param txtTemplate
	 */
	public static void saveTemplate(JComboBox<MyItems> comboTags, JTextArea txtTemplate) {
		MyItems currentTag = (MyItems) comboTags.getSelectedItem();
		String template = txtTemplate.getText().replace("\n", "\\n");

		//TODO: to not create the reset button, although i think i have to
		currentTag.setTemplate(template);
		
		List<String> lines = DataManager.linesTemplate();
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] split = line.split(";");
			
			if(split[0].equals(currentTag.toString())) {
				lines.remove(i);
				lines.add(i, String.join(";", split[0], template));
				DataManager.writeTemplate(lines);
			}
		}
	}
}
