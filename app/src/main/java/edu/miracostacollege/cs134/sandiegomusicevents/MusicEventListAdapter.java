package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracostacollege.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter<MusicEvent>
{

    //declare member variables to store the parameters (context, resource id, list of <MusicEvent>
    private Context mContext;
    private int mResourceId;
    private List<MusicEvent> mAllEvents;



    //this constructor is being called by MainActivity
    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List<MusicEvent> objects) {
        super(context, resource, objects);

        mContext = context;
        mResourceId = resource;
        mAllEvents = objects;

    }


    //in order to bridge the view (music_event_list_item) with the model (MusicEvent) we override:
    //ctrl + o => override


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //inflate our custom layout with data from the List<MusicEvent>
        MusicEvent focusedEvent = mAllEvents.get(position);

        //manually inflate the custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //tell the inflator to inflate music_event_list_item
        View customView = inflater.inflate(mResourceId, null);
        //null because theres no parent to the custom view


        //fill the parts of the custom view
        ImageView listItemImageView = customView.findViewById(R.id.listItemImageView);
        TextView listItemArtistTextView = customView.findViewById(R.id.listItemArtistTextView);
        TextView listItemDateTextView = customView.findViewById(R.id.listItemDateTextView);

        //put information into text view and image view
        listItemArtistTextView.setText(focusedEvent.getArtist());
        listItemDateTextView.setText(focusedEvent.getDate());

        //load the picture dynamically
        AssetManager am = mContext.getAssets();
        //b/c main activity is holdin all the assets

        try {
            InputStream stream = am.open(focusedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, focusedEvent.getArtist());

            //put the image into the ImageView
            listItemImageView.setImageDrawable(image);


        } catch (IOException e) {
            Log.e("SD Music Events", e.getMessage());
        }

        return customView;
    }
}
