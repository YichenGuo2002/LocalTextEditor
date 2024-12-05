package com.editor;

public class File {
	private String name;
	private String content;
	private static int idCounter = 1;  // Start from 1 for the first file
    private int id;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 255;
    private static final int MIN_CONTENT_LENGTH = 1;
    private static final int MAX_CONTENT_LENGTH = 2147483647;
	
    public void setName(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters.");
        }
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setContent(String content) {
        if (content == null || content.length() < MIN_CONTENT_LENGTH || content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException("Content must be between " + MIN_CONTENT_LENGTH + " and " + MAX_CONTENT_LENGTH + " characters.");
        }
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public int getId(){
        return this.id;
    }
    
    public File(String name, String content) {
        this.id = idCounter++; 
        setName(name);  
        setContent(content); 
    }
}
