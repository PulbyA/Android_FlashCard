package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ZoomImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        final ImageView zoomImageView = findViewById(R.id.zoomImageView);

        Intent srcIntent = getIntent();
        int imageId = srcIntent.getIntExtra("aZoomImageId", 0);

        zoomImageView.setImageResource(imageId);

        zoomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
