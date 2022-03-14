package com.shubham.BestBooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.StaggeredGridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubham.BestBooks.Adapters.BookAdapter;
//import com.shubham.sppuguru.databinding.ActivityMainBinding;
import com.shubham.BestBooks.databinding.ActivityMainBinding;
import com.shubham.BestBooks.models.BookModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    RecyclerView recyclerView;
    ActivityMainBinding binding;
    private Object GridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        recyclerView = findViewById(R.id.recyclerView);
        binding = ActivityMainBinding.inflate(
                getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<BookModel> list = new ArrayList<>();
//        list.add(new BookModel(R.drawable.img1, "engineering"));
//        list.add(new BookModel(R.drawable.img2, "engineering"));
//        list.add(new BookModel(R.drawable.img3, "engineering"));
//        list.add(new BookModel(R.drawable.img4, "engineering"));
//        list.add(new BookModel(R.drawable.img5, "engineering"));
//        list.add(new BookModel(R.drawable.img6, "engineering"));
//        list.add(new BookModel(R.drawable.img7, "engineering"));
//        list.add(new BookModel(R.drawable.img8, "engineering"));
//        list.add(new BookModel(R.drawable.img9, "engineering"));


        BookAdapter adapter = new BookAdapter(list, MainActivity.this);
        binding.recyclerView.setAdapter(adapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        binding.recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(gridLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
//                (3 , StaggeredGridLayoutManager.VERTICAL);
//        binding.recyclerView.setLayoutManager(staggeredGridLayoutManager);

        FirebaseDatabase.getInstance().getReference().child("books")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            BookModel model = snapshot1.getValue(BookModel.class);
                            list.add(model);

                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}