package com.example.domenico.Zarathustra.backend.server.tables;

import com.example.domenico.Zarathustra.backend.server.User;

import java.util.Date;

public class EventPost extends Post{
    private static final long serialVersionUID = 4183541439146537795L;
	private Date date;


    public EventPost(String title, User authorId, String content, Date submittingTime, Date date){
		super(title, authorId, content, submittingTime);
		this.date = date;
	}

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate(){
		return this.date;
	}
}