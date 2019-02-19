package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {


    private TextView eventTitleTextView;
    private TextView eventDetailsTextView;
    private ImageView eventImageView;

    public static final String TAG = EventDetailsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String details = intent.getStringExtra("Details");
        String fileName = title.replaceAll(" ", "") + ".png";

        eventTitleTextView = findViewById(R.id.eventTitleTextView);
        eventDetailsTextView = findViewById(R.id.eventDetailsTextView);
        eventImageView = findViewById(R.id.eventImageView);

        eventTitleTextView.setText(title);
        eventDetailsTextView.setText(details);

        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(fileName);
            Drawable eventImage = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(eventImage);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }
}
