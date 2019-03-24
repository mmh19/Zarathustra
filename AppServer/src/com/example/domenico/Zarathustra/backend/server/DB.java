package com.example.domenico.Zarathustra.backend.server;

import com.example.domenico.Zarathustra.backend.server.table.AlertPost;
import com.example.domenico.Zarathustra.backend.server.table.EventPost;
import com.example.domenico.Zarathustra.backend.server.table.SuggestionPost;
import com.example.domenico.Zarathustra.backend.server.table.TextPost;
import com.quattrup1.usermanager.Password;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {

    private static final long serialVersionUID = 5185343382628581119L;

    public ArrayList<AlertPost> tableAlertPost = new ArrayList<>();
    public ArrayList<TextPost> tableTextPost = new ArrayList<>();
    public ArrayList<SuggestionPost> tableSuggestionPost = new ArrayList<>();
    public ArrayList<EventPost> tableEventPost = new ArrayList<>();
    public ArrayList<User> tableUser = new ArrayList<>();

    public DB() {
        for (int i = 0; i < 5; i++)
            tableUser.add(new User(-1, "utente" + i, "password" + i, "ciao" + i, null, i++));
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
