package com.example.domenico.Zarathustra.backend.server;

import com.example.domenico.Zarathustra.backend.server.tables.AlertPost;
import com.example.domenico.Zarathustra.backend.server.tables.EventPost;
import com.example.domenico.Zarathustra.backend.server.tables.Post;
import com.example.domenico.Zarathustra.backend.server.tables.SuggestionPost;
import com.example.domenico.Zarathustra.backend.server.tables.TextPost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DB implements Serializable {

	private static final long serialVersionUID = 5185343382628581119L;

    public List<Post> tableAlertPost = new ArrayList<>();
    public List<Post> tableTextPost = new ArrayList<>();
    public List<Post> tableSuggestionPost = new ArrayList<>();
    public List<Post> tableEventPost = new ArrayList<>();
    public List<User> tableUser = new ArrayList<>();

    public DB() {
        for (int i = 0; i < 5; i++)
            tableUser.add(new User(-1, "utente" + i, ("password" + i), "ciao" + i, null, i++));
    }

    public void add(AlertPost alertPost) {
        tableAlertPost.add(alertPost);
    }

    public void add(TextPost TextPost) {
        tableTextPost.add(TextPost);
    }

    public void add(SuggestionPost suggestionPost) {
        tableSuggestionPost.add(suggestionPost);
    }

    public void add(EventPost eventPost) {
        tableEventPost.add(eventPost);
    }

    public void add(User user) {
        tableUser.add(user);
    }
	
}
