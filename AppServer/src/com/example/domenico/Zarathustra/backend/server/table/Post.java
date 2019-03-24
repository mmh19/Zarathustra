package com.example.domenico.Zarathustra.backend.server.table;
import com.example.domenico.Zarathustra.backend.server.User;

import java.io.Serializable;
import java.util.Date;


public abstract class Post implements Serializable {
    private static final long serialVersionUID = -1651968622414322439L;
    private String title;
    private User author;
    private String content;
    private Date submittingTime;

	public Post(String title, User author, String content, Date submittingTime){
		this.content=content;
		this.title = title;
		this.author = author;
		this.submittingTime = submittingTime;
	}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubmittingTime(Date submittingTime) {
        this.submittingTime = submittingTime;
    }

	public String getTitle(){ return this.title;}
    public User getAuthor(){ return this.author;}
    public String getContent(){ return this.content;}
    public Date getSubmittingTime(){ return this.submittingTime;}
    public Date getSubmittingTimeAsDate(){ return this.submittingTime;}
}