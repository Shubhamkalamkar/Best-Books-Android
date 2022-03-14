package com.shubham.BestBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.shubham.BestBooks.databinding.ActivityPdfreaderBinding;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class PdfReader extends AppCompatActivity implements DownloadFile.Listener{




    ActivityPdfreaderBinding binding;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfreaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait While Loading....");

        progress.show();

        String url = getIntent().getStringExtra("url");
        RemotePDFViewPager remotePDFViewPager =
                new RemotePDFViewPager(PdfReader.this, url, this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public void onSuccess(String url, String destinationPath) {
        progress.dismiss();
        PDFPagerAdapter adapter = new PDFPagerAdapter(this, extractFileNameFromURL(url));
        binding.pdfViewPager.setAdapter(adapter);

    }
    //identify images
    public static String extractFileNameFromURL(String url){
        return url.substring(url.lastIndexOf('/')+1);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}