package com.furminger.allaboutclovelly;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final String TAG = "mapDemo";
    private static Context context;
    private static DetailFragment detailFragment;
    private Map<String, PointOfInterest> pointsOfInterest;

    /**
     * static ViewHolder class for the RecyclerView
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDetail = itemView.findViewById(R.id.itemDetail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    int position = getAbsoluteAdapterPosition();

                    // call a method in the MainActivity. Pass in the int position.
                    // that method will
                    // 1. open a new window
                    // 2. display the correct point of interest details related to that position

                    // Open a new BlankFragment to display the details. This should be done by the Activity in the Callback
                    // https://stackoverflow.com/questions/34310592/how-open-fragment-from-recyclerview-adaptercardadapter-viewholder
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    DetailFragment detailFragment = new DetailFragment(itemTitle.getText().toString(), itemDetail.getText().toString(),
                            itemImage.getDrawable());

                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.activity_main, detailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }


    /**
     * constructor for the RecyclerAdapter
     * @param pointsOfInterest
     */
    public RecyclerAdapter(Map<String, PointOfInterest> pointsOfInterest) {
        super();
        this.pointsOfInterest = pointsOfInterest;

    }

    /**
     * Called by the RecyclerView to obtain a ViewHolder object.
     * It inflates the view hierarchy card_layout.xml file and creates an instance of
     * the ViewHolder class initialized with the view hierarchy, before returning it to
     * the RecyclerView.
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * This method populates the view hierarchy within the ViewHolder object with the
     * data to be displayed.
     * It is passed the ViewHolder object and an integer indicating the list item that
     * is to be displayed.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        String key = "";
        // loop through the pointsOfInterest Map, looking for a match between i and
        // the id - then extract the key
        for(Map.Entry<String, PointOfInterest> pair : pointsOfInterest.entrySet()) {
            if(pair.getValue().getId() == i) {
                key = pair.getKey();
            }
        }

        // https://stackoverflow.com/questions/42468113/how-can-i-use-getresources-inside-of-onbindviewholder
        Resources res = viewHolder.itemView.getContext().getResources();

        viewHolder.itemTitle.setText(pointsOfInterest.get(key).getPlaceTitle());
        viewHolder.itemDetail.setText(pointsOfInterest.get(key).getPlaceDescription());

        String photoName = pointsOfInterest.get(key).getPlacePhotos().get(0);

        context = viewHolder.itemView.getContext();

        Drawable newImage = res.getDrawable(getDrawableIdentifier(context, photoName), null);

//        Drawable newImage = res.getDrawable(getDrawableIdentifier(context, photoName), null);
                //MainActivity.getContext().getDrawable(getDrawableIdentifier(context, photoName));
        viewHolder.itemImage.setImageDrawable(newImage);
    }

    @Override
    public int getItemCount() {
        return pointsOfInterest.size();
    }

    public int getDrawableIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }




}



