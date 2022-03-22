package com.furminger.allaboutclovelly.ui.main;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.furminger.allaboutclovelly.databinding.FragmentTextBinding;

/**
 *
 */
public class TextFragment extends Fragment {

    private FragmentTextBinding binding;

    public TextFragment() {
        // Required empty public constructor
    }

    /**
     * This factory method creates a new instance of
     * this fragment.
     *
     * @return A new instance of fragment TextFragment.
     */
    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTextBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void clearTextFragment() {
        LinearLayout linearLayout = binding.linearLayout;
        linearLayout.removeAllViews();
        binding.scrollView.scrollTo(0, 0);
    }

    /**
     * Method uses the parameters coming from MainActivity to modify the TextView objects
     * (MapsFragment passes a marker object to MainActivity)
     */
    public void addText(String str, int fontSize, boolean isBold) {
        TextView newTextView = new TextView(binding.linearLayout.getContext());
        newTextView.setText(str);
        newTextView.setTextSize(fontSize);
        if(isBold) newTextView.setTypeface(null, Typeface.BOLD);
        newTextView.setVisibility(View.VISIBLE);
        binding.linearLayout.addView(newTextView);
    }

    public void addPhoto(Drawable drawable) {
        ImageView newImageView = new ImageView(binding.linearLayout.getContext());
        newImageView.setImageDrawable(drawable);
        newImageView.setVisibility(View.VISIBLE);
        binding.linearLayout.addView(newImageView);
    }

}