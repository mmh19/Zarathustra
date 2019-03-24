package com.example.domenico.Zarathustra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import com.example.domenico.Zarathustra.backend.api.API;
import com.example.domenico.Zarathustra.backend.server.tables.Post;


public class PostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private List<Post> posts;
    private LayoutInflater inflater;
    public PostsAdapter(Context c, List<Post> posts) {
        this.posts = posts;
        inflater = LayoutInflater.from(c);
    }
    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myView = inflater.inflate(R.layout.row_suggestions, viewGroup, false);
        return new PostsViewHolder(myView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder PostsViewHolder, int i) {
        // recupera i dati per quella posizione
        int imagepath = posts.get(i).getAuthor().getImageId();
        String fullname = posts.get(i).getAuthor().getName();
        String content = posts.get(i).getContent();
        String title = posts.get(i).getTitle();
        PostsViewHolder.profileImage.setImageResource(imagepath);
        PostsViewHolder.content.setText(content);
        PostsViewHolder.titolo.setText(title);
        PostsViewHolder.fullname.setText(fullname);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
