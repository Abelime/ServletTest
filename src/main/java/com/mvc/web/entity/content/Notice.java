package com.mvc.web.entity.content;

import java.util.Date;

public class Notice {
	int id;
	String title;
	String writeid;
	String content;
	Date regdate;
	int hit;
	String board;
	
	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writeid=" + writeid + ", content=" + content + ", regdate="
				+ regdate + ", hit=" + hit + "]";
	}
	
	public Notice(int id, String title, String writeid, String content, Date regdate, int hit) {
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}

	public Notice(String board, int id, String title, String writeid, String content, Date regdate, int hit) {
		this.board=board;
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
	}
	
	public Notice(String userID, String title2, String content2) {
		this.writeid=userID;
		this.title=title2;
		this.content=content2;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriteid() {
		return writeid;
	}
	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
