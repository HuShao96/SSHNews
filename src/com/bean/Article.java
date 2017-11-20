package com.bean;

import org.springframework.stereotype.Repository;

@Repository(value="article")
public class Article {
	private String arid;
	private Catalog catalog;
	private String arnumber;
	private String artitle;
	private String arcontent;
	private String aruser;
	private String artime;
	private String arstate;
	private int clicks;
	
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	public Article() {
		super();
	}
	public String getArid() {
		return arid;
	}
	public void setArid(String arid) {
		this.arid = arid;
	}
	
	public String getArnumber() {
		return arnumber;
	}
	public void setArnumber(String arnumber) {
		this.arnumber = arnumber;
	}
	public String getArtitle() {
		return artitle;
	}
	public void setArtitle(String artitle) {
		this.artitle = artitle;
	}
	public String getArcontent() {
		return arcontent;
	}
	public void setArcontent(String arcontent) {
		this.arcontent = arcontent;
	}
	public String getAruser() {
		return aruser;
	}
	public void setAruser(String aruser) {
		this.aruser = aruser;
	}
	public String getArtime() {
		return artime;
	}
	public void setArtime(String artime) {
		this.artime = artime;
	}
	public String getArstate() {
		return arstate;
	}
	public void setArstate(String arstate) {
		this.arstate = arstate;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	@Override
	public String toString() {
		return "Article [arid=" + arid + ", arnumber=" + arnumber + ", artitle=" + artitle
				+ ", arcontent=" + arcontent + ", aruser=" + aruser + ", artime=" + artime + ", arstate=" + arstate
				+ ", clicks=" + clicks + "]";
	}
	

}
