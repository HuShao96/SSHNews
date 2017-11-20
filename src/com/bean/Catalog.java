package com.bean;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository(value="catalog")
public class Catalog {
	private Integer caid;
	private String caname;
	private String canumber;
	private String castate;
	private Set<Article> articles=new HashSet<Article>();
	
	
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Integer getCaid() {
		return caid;
	}
	public void setCaid(Integer caid) {
		this.caid = caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}
	public String getCaname() {
		return caname;
	}
	public void setCaname(String caname) {
		this.caname = caname;
	}
	public String getCanumber() {
		return canumber;
	}
	public void setCanumber(String canumber) {
		this.canumber = canumber;
	}
	public String getCastate() {
		return castate;
	}
	public void setCastate(String castate) {
		this.castate = castate;
	}
	@Override
	public String toString() {
		return "Catalog [caid=" + caid + ", caname=" + caname + ", canumber=" + canumber + ", castate=" + castate + "]";
	}
	

}
