package com.bean;

import org.springframework.stereotype.Repository;

@Repository(value="images")
public class Images {
	private Integer imid;
	private String imtitle;
	private String imname;
	private String imdate;
	private String imstate;
	
	public Images() {
		super();
	}
	
	public Integer getImid() {
		return imid;
	}

	public void setImid(Integer imid) {
		this.imid = imid;
	}

	public String getImtitle() {
		return imtitle;
	}
	public void setImtitle(String imtitle) {
		this.imtitle = imtitle;
	}
	public String getImname() {
		return imname;
	}
	public void setImname(String imname) {
		this.imname = imname;
	}
	public String getImdate() {
		return imdate;
	}
	public void setImdate(String imdate) {
		this.imdate = imdate;
	}
	public String getImstate() {
		return imstate;
	}
	public void setImstate(String imstate) {
		this.imstate = imstate;
	}
	@Override
	public String toString() {
		return "Images [imid=" + imid + ", imtitle=" + imtitle + ", imname=" + imname + ", imdate=" + imdate
				+ ", imstate=" + imstate + "]";
	}
	
}
