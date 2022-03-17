package com.ebookfrenzy.mapdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    final private String[] titles = {"Chapter One", "Chapter Two", "Chapter Three", "Chapter Four",
            "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight"
    };

    final private String[] details = {"Item one details", "Item two details", "Item three details",
    "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details"};

    final private int[] images = {R.drawable.android_image_1,
    R.drawable.android_image_2,
    R.drawable.android_image_3,
    R.drawable.android_image_4,
    R.drawable.android_image_5,
    R.drawable.android_image_6,
    R.drawable.android_image_7,
    R.drawable.android_image_8};

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

    /**
     * This method will be called by the RecyclerView to obtain a ViewHolder object.
     * It inflates the view hierarchy card_layout.xml file and creates and instance of
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
        Log.i("mapDemo", "RecyclerAdapter: View v....");
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
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

}
