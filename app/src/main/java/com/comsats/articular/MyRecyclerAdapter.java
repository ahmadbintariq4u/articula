package com.comsats.articular;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ArticleViewHolder> {
    Context context;
    ArrayList<String> data[];

    public MyRecyclerAdapter(Context context, ArrayList<String>[] data) {
        this.context = context;
        this.data = data;
    }

    public static int count=0;

    @NonNull
    @Override
    public MyRecyclerAdapter.ArticleViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_template,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ArticleViewHolder holder, int position) {
        holder.author.setText(data[position].get(0));
        holder.title.setText(data[position].get(1));
        if(data[position].get(5)!=null){

            try{
                byte [] encodeByte= Base64.decode(data[position].get(5),Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                holder.pic.setImageBitmap(bitmap);
                System.out.println("image successfluyy");
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Facing issue");
            }

        }
        else
        holder.pic.setImageResource(R.drawable.questionmark);

    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ImageView pic;
        public TextView title;
        public TextView author;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=(ImageView)itemView.findViewById(R.id.activity_recycler_template_pic);
            title=(TextView)itemView.findViewById(R.id.activity_recycler_template_title);
            author=(TextView)itemView.findViewById(R.id.activity_recycler_template_author);
        }
    }

}
