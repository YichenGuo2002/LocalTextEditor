package com.editor;

public class File {
	private String name;
	private String content;
	private static int idCounter = 1;  // Start from 1 for the first file
    private int id;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getId(){
		return this.id;
	}
	
	public File(String name, String content) {
        this.id = idCounter++;  // Assign the current id and then increment it
        this.name = name;
        this.content = content;
    }
}
