package com.furminger.allaboutclovelly;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final String TAG = "mapDemo";

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDetail = itemView.findViewById(R.id.itemDetail);
        }
    }

    private Map<String, PointOfInterest> pointsOfInterest;

    public RecyclerAdapter(Map<String, PointOfInterest> pointsOfInterest) {
        super();
        this.pointsOfInterest = pointsOfInterest;

    }

    /**
     * This method will be called by the RecyclerView to obtain a ViewHolder object.
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

        viewHolder.itemTitle.setText(pointsOfInterest.get(key).getPlaceTitle());
        viewHolder.itemDetail.setText(pointsOfInterest.get(key).getPlaceDescription());
        viewHolder.itemImage.setImageDrawable(pointsOfInterest.get(key).getPlacePhotos().get(0));
    }

    @Override
    public int getItemCount() {
        return pointsOfInterest.size();
    }

}
