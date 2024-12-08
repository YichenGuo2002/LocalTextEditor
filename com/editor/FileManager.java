package com.editor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	private String filePath = "files.txt";
	private List<File> files;
	
	public FileManager() {
	    files = new ArrayList<>();
	    load();
	}
	
	public void load() {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	// Assuming each file's information is separated by newlines and in format:
                // ID
                // Name
                // Content (with /n replaced by actual newlines)
                // Modify Time
                String idStr = line.trim();
                String name = reader.readLine().trim();
                String content = reader.readLine().trim();
                String modifyTimeStr = reader.readLine().trim();

                content = content.replace("//n", "\n");

                File file = new File(Integer.valueOf(idStr), name, content);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
                file.setModifyTime(LocalDateTime.parse(modifyTimeStr, formatter));
                files.add(file);
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading the file: " + e.getMessage());
	    }
	}
	
    private void clean() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(""); 
            System.out.println("File contents have been cleared.");
        } catch (IOException e) {
            System.out.println("Error clearing the file: " + e.getMessage());
        }
    }
    
	public void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (File file : files) {
                writer.write(file.getId() + "\n");
                writer.write(file.getName() + "\n");
                String content = file.getContent().replace("\n", "//n");
                writer.write(content + "\n");
                writer.write(file.printModifyTime() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
	}
	
	public void add(File file) {
	    files.add(file);
	}
	
	public File getFileById(int id) {
	    for (File file : files) {
	        if (file.getId() == id) {
	            return file;
	        }
	    }
	    return null;
	}
	
	public boolean deleteFileById(int id) {
	    return files.removeIf(file -> file.getId() == id);
	}
	
	public boolean updateFile(int id, String newName, String newContent) {
	    File file = getFileById(id);
	    if (file != null) {
	        file.setName(newName);
	        file.setContent(newContent);
	        return true;
	    }
	    return false;
	}
	
	public List<String> printFiles() {
		List<String> fileInfoList = new ArrayList<>();
	    for (File file : files) {
	    	fileInfoList.add("ID: " + file.getId() + ", Name: " + file.getName() + ", Modify Time: " + file.printModifyTime());
	    }
	    return fileInfoList;
	}
	
	public List<File> getFiles() {
	    return files;
	}
	
	/*testing code
	public static void main(String[] args){
		FileManager a = new FileManager();
		a.add(new File("b", "cc"));
		a.add(new File("c", "dd"));
		a.save();
		System.out.println(a.printFiles().toString());
	}*/
}