package com.editor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class File {
	private String name;
	private String content;
	private static int idCounter = 1;  // Start from 1 for the first file
    private int id;
    private LocalDateTime modifyTime;
    private String creator;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 255;
    private static final int MIN_CONTENT_LENGTH = 1;
    private static final int MAX_CONTENT_LENGTH = 2147483647;
	
    public void setName(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters.");
        }
        this.name = name;
        setModifyTime();
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setContent(String content) {
        if (content == null || content.length() < MIN_CONTENT_LENGTH || content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("Content must be between " + MIN_CONTENT_LENGTH + " and " + MAX_CONTENT_LENGTH + " characters.");
        }
        this.content = content;
        setModifyTime();
    }
    
    public String getContent() {
        return this.content;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setCreator(String creator) {
    	this.creator = creator;
    }
    
    public String getCreator() {
    	return this.creator;
    }
    
    public LocalDateTime getModifyTime(){
        return this.modifyTime;
    }
    
    private void setModifyTime(){
        this.modifyTime = LocalDateTime.now();
    }
    
    public void setModifyTime(LocalDateTime time){
        this.modifyTime = time;
    }
    
    public String printModifyTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
        return this.modifyTime.format(formatter); 
    }
    
    public String printFile() {
    	return "." + getName() + ", by " + getCreator() + ", last modified at " + printModifyTime() + ".";
	}
    
    public File(String name, String content, String creator) {
        this.id = idCounter++; 
        setName(name);  
        setContent(content); 
        setCreator(creator);
    }
    
    public File(int id, String name, String content, String creator) {
        this.id = id; 
        if(idCounter <= id) idCounter = id + 1;
        setName(name);  
        setContent(content); 
        setCreator(creator);
    }
}
