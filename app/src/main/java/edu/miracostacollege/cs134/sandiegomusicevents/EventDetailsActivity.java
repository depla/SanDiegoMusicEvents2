package edu.miracostacollege.cs134.sandiegomusicevents;

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

/**
 * shows the user more information about the selected music event
 *
 * @author Dennis La
 * @version 2.0
 */

public class EventDetailsActivity extends AppCompatActivity {


    private TextView eventArtistTextView;
    private TextView eventDateDayTextView;
    private TextView eventTimeTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;
    private ImageView eventImageView;

    public static final String TAG = EventDetailsActivity.class.getName();

    /**
     * gets the intent from MainActivity and uses it to populate the views to show
     * the user information about the music event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        String artist = intent.getStringExtra("Artist");
        String date = intent.getStringExtra("Date");
        String day = intent.getStringExtra("Day");
        String time = intent.getStringExtra("Time");
        String venue = intent.getStringExtra("Venue");
        String city = intent.getStringExtra("City");
        String state = intent.getStringExtra("State");
        String fileName = intent.getStringExtra("ImageName");

        eventArtistTextView = findViewById(R.id.eventArtistTextView);
        eventDateDayTextView = findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventLocationTextView = findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = findViewById(R.id.eventAddress1TextView);
        eventAddress2TextView = findViewById(R.id.eventAddress2TextView);
        eventImageView = findViewById(R.id.eventImageView);

        eventArtistTextView.setText(artist);
        eventDateDayTextView.setText(date + " " + day);
        eventTimeTextView.setText(time);
        eventLocationTextView.setText(venue);
        eventAddress1TextView.setText(city);
        eventAddress2TextView.setText(state);

        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(fileName);
            Drawable eventImage = Drawable.createFromStream(stream, artist);
            eventImageView.setImageDrawable(eventImage);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }
}
