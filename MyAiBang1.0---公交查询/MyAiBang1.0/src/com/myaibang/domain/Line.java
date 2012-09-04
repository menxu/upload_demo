package com.myaibang.domain;

public class Line {
	private String name;
	private String info;
	private String stats;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	public Line(String name, String info, String stats) {
		this.name = name;
		this.info = info;
		this.stats = stats;
	}
	public Line() {
	}
	
}
