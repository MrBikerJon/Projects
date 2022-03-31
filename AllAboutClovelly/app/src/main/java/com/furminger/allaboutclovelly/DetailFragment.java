package com.furminger.allaboutclovelly;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;
    private com.furminger.allaboutclovelly.databinding.FragmentDetailBinding binding;
    private String titleText;
    private String detailText;
    private Drawable drawable;


    private final String TAG = "mapDemo";

    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(String titleText, String detailText, Drawable drawable) {
        // Required empty public constructor
        this.titleText = titleText;
        this.detailText = detailText;
        this.drawable = drawable;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        binding = com.furminger.allaboutclovelly.databinding.FragmentDetailBinding.inflate(inflater, container, false);

        // hide the floating action button while in the detail view
        Context context = getActivity();
        MainActivity parentActivity = ((MainActivity) context);
        parentActivity.fabHide();

        return binding.getRoot();
//        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Set an onClick listener for the close button, to close the Fragment when clicked
        button = (Button) view.findViewById(R.id.closeButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // https://stackoverflow.com/questions/20812922/how-to-close-the-current-fragment-by-using-button-like-the-back-button
                getActivity().onBackPressed();

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();

        TextView titleText = (TextView) requireView().findViewById(R.id.textViewTitle);
        titleText.setText(this.titleText);
        TextView detailText = (TextView) requireView().findViewById(R.id.textViewDescription);
        detailText.setText(this.detailText);
        ImageView imageView = (ImageView) requireView().findViewById(R.id.imageView2);
        imageView.setImageDrawable(drawable);
    }

    /**
     * Method uses the parameters coming from MainActivity to modify the TextView objects
     * (MapsFragment passes a marker object to MainActivity)
     */
    public void addText(String str, int fontSize, boolean isBold) {
        TextView newTextView = new TextView(binding.frameLayout.getContext());
        newTextView.setText(str);
        newTextView.setTextSize(fontSize);
        if(isBold) newTextView.setTypeface(null, Typeface.BOLD);
        newTextView.setVisibility(View.VISIBLE);
        binding.frameLayout.addView(newTextView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // show the floating action button again when this fragment is gone
        Context context = getActivity();
        MainActivity parentActivity = ((MainActivity) context);
        parentActivity.fabShow();

        binding = null;
    }

    @Override
    public void onStop() {
        super.onStop();



    }

}