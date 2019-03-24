package com.example.domenico.Zarathustra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.domenico.Zarathustra.backend.api.API;
import com.example.domenico.Zarathustra.backend.server.User;
import com.example.domenico.Zarathustra.backend.server.tables.AlertPost;
import com.example.domenico.Zarathustra.backend.server.tables.Post;

import java.util.ArrayList;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        /*RecyclerView recyclerView = findViewById(R.id.recyclerview);
        //Log.i("qty", API.getInstance().getAlertPosts().size()+"");
        ArrayList<Post> a = new ArrayList<>();
        a.add(new AlertPost("Avvio",new User(-1,"",null,"luigi",null,0),"Avviso importante di provaa", Calendar.getInstance().getTime()));
        a.add(new AlertPost("Avvio",new User(-1,"",null,"giuseppe",null,0),"prova", Calendar.getInstance().getTime()));
        a.add(new AlertPost("Avvio",new User(-1,"",null,"matteo aleo",null,0),"Miao", Calendar.getInstance().getTime()));
        PostsAdapter adapter = new PostsAdapter(this, a);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);*/
        getSupportActionBar().hide();
        Footer.bind(this);
    }

}
