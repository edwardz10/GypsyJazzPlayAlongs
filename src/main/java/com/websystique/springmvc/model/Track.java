package com.websystique.springmvc.model;

public class Track {
	private String name;
	private String id;
	
	public Track() {
	}

	public Track(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Track [name=" + name + ", id=" + id + "]";
    }
}
