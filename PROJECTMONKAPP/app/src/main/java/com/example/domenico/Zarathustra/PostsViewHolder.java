package com.example.domenico.Zarathustra;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

public class PostsViewHolder extends RecyclerView.ViewHolder {
    public ImageView profileImage;
    public TextView fullname, titolo, content;
    public PostsAdapter adapter;

    public PostsViewHolder(@NonNull View itemView, PostsAdapter adapter){
        super(itemView);
        this.adapter = adapter;
        profileImage = profileImage.findViewById(R.id.profileImage);
        fullname = fullname.findViewById(R.id.name);
        titolo = titolo.findViewById(R.id.title);
        content = content.findViewById(R.id.content);
    }
    //eventuali onClick
}
