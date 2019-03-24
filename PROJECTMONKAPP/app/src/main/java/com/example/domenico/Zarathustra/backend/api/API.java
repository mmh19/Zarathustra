package com.example.domenico.Zarathustra.backend.api;

import android.content.Context;

import com.example.domenico.Zarathustra.backend.server.Request;
import com.example.domenico.Zarathustra.backend.server.RequestType;
import com.example.domenico.Zarathustra.backend.server.Response;
import com.example.domenico.Zarathustra.backend.server.DB;
import com.example.domenico.Zarathustra.backend.server.tables.AlertPost;
import com.example.domenico.Zarathustra.backend.server.tables.EventPost;
import com.example.domenico.Zarathustra.backend.server.tables.Post;
import com.example.domenico.Zarathustra.backend.server.tables.SuggestionPost;
import com.example.domenico.Zarathustra.backend.server.tables.TextPost;
import com.example.domenico.Zarathustra.backend.server.User;
import com.example.domenico.Zarathustra.backend.server.Password;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class API {
    private final String serverIP = "192.168.3.161";
	private static API instance;
	static Context context;
	DB runningDB;
	Date lastUpdate;
	//private BackEndMap backEndMap;

	private API() {
	    getDB();
	}

	public static API getInstance() {
		if (instance == null) {
			if(context != null) {
				instance = new API();
			}else{
				throw new RuntimeException("The API has not been initialized yet, use init before getting an instance");
			}
		}
		return instance;
	}

	public static void init(Context c){
		context=c;
		SharedPreferencesManager.init(c);
	}

	public boolean loginValidation(String user, String pw) {
		Socket client;
        ObjectOutputStream out;
        ObjectInputStream in;

        try{
            client = new Socket(serverIP,1234);
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());

            Request request = new Request(-1, RequestType.LOGIN,new Serializable[]{new User(-1,user,(pw),null,null,0)});
            out.writeObject(request);
            out.flush();

            Response response = ((Response)in.readObject());
            if(response.isError())return false;
            long sessionId = ((Long)response.getResponse()[0]).longValue();
            SharedPreferencesManager.set("sessionId",""+sessionId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
	}

	public boolean isSessionAlreadyStarted() {
		return Long.parseLong(SharedPreferencesManager.get("sessionId","-1"))!=-1;
	}

	public void logout() {
		SharedPreferencesManager.set("sessionId", "-1");
	}

	private void getDB(){
	    if(runningDB==null||Calendar.getInstance().getTime().getTime()- lastUpdate.getTime()>60000){
            Socket client;
            ObjectOutputStream out;
            ObjectInputStream in;

            try {
                client = new Socket(serverIP, 1234);
                in = new ObjectInputStream(client.getInputStream());
                out = new ObjectOutputStream(client.getOutputStream());
                Request request = new Request(getSessionId(),RequestType.GET_DB,null);
                out.writeObject(request);
                Response response = (Response)in.readObject();
                runningDB = (DB)response.getResponse()[0];
                lastUpdate = Calendar.getInstance().getTime();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<Post> getAlertPosts(){
	    getDB();
	    return runningDB.tableAlertPost;
    }

    public List<Post> getTextPosts(){
        return runningDB.tableTextPost;
    }

    public List<Post> getSuggestionPosts(){
        return runningDB.tableSuggestionPost;
    }

    public List<Post> getEventPosts(){
        return runningDB.tableEventPost;
    }

    public List<Post> getAllPosts(){
	    List<Post> a = new ArrayList<>();
	    for(Post p:getAlertPosts()){
	        a.add(p);
        }
        for(Post p:getEventPosts()){
            a.add(p);
        }
        for(Post p:getSuggestionPosts()){
            a.add(p);
        }
        for(Post p:getTextPosts()){
            a.add(p);
        }
        return a;
    }

    private long getSessionId(){
	    return Long.parseLong(SharedPreferencesManager.get("sessionId","-1"));
    }

    public void addPost(AlertPost p){
        runningDB.add(p);
        updateDB();
    }
    public void addPost(EventPost p){
        runningDB.add(p);
        updateDB();
    }
    public void addPost(TextPost p){
        runningDB.add(p);updateDB();

    }
    public void addPost(SuggestionPost p){
        runningDB.add(p);
        updateDB();
    }

    private void updateDB(){
        Socket client;
        ObjectOutputStream out;
        ObjectInputStream in;
        try {
            client = new Socket(serverIP, 1234);
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
            Request request = new Request(getSessionId(),RequestType.UPDATE_DB,new Serializable[]{runningDB});
            out.writeObject(request);
            Response response = (Response)in.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}