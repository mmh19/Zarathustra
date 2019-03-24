package com.example.domenico.Zarathustra.backend.server.tables;


import com.example.domenico.Zarathustra.backend.server.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuggestionPost extends Post{
    private static final long serialVersionUID = 7403128913764235852L;
	List<User> likes;

	public SuggestionPost(String title, User author, String content, Date submittingDate){
		super(title, author, content, submittingDate);
		likes = new ArrayList<>();
	}

	public void addLike(User liker){
		if(!contains(liker)){
			likes.add(liker);
		}
	}

	private boolean contains(User liker){
		for(User i: likes)
			if(i.equals(liker))
				return true;
		return false;
	}

	public int getLikesAmount(){ return likes.size();}

}