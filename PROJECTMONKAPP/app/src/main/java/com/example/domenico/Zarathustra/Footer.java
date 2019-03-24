package com.example.domenico.Zarathustra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.domenico.Zarathustra.backend.api.API;

public class Footer {

    public static void bind(AppCompatActivity activity) {
        ImageButton home, mappa, forum;
        home = activity.findViewById(R.id.homeButton);
        mappa= activity.findViewById(R.id.mappaButton);
        forum= activity.findViewById(R.id.forumButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (activity, HomePage.class );
                activity.startActivity(intent);
                activity.finish();
            }
        });
        mappa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (activity, MappaActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (activity, Main2Activity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }
}