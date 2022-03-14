package com.shubham.BestBooks.Adapters;

//import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubham.BestBooks.PdfReader;
import com.shubham.BestBooks.R;
import com.shubham.BestBooks.models.BookModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.viewHolder>{

    ArrayList <BookModel> list;
//    private final MainActivity mainActivity;
    Context context;

    public BookAdapter(ArrayList<BookModel> list,  Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_books ,parent ,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BookModel model = list.get(position);
//        holder.bookImage.setImageResource(model.getImage());
//        online image lana with picaso
        Picasso.get()
                .load(model.getImage())
                .into(holder.bookImage);


        holder.textView.setText(model.getBookName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , PdfReader.class);
                intent.putExtra("url" , model.getUrl());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView bookImage;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);


        }
    }
}
