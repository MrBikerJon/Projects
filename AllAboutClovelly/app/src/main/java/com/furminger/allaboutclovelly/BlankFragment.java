package com.furminger.allaboutclovelly;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.furminger.allaboutclovelly.databinding.FragmentBlankBinding;
import com.furminger.allaboutclovelly.databinding.FragmentTextBinding;
import com.google.android.gms.maps.SupportMapFragment;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button button;
    private FragmentBlankBinding binding;
    public String titleText;
    public String detailText;

    private final String TAG = "mapDemo";

    public BlankFragment() {
        // Required empty public constructor
    }

    public BlankFragment(String titleText, String detailText) {
        // Required empty public constructor
        this.titleText = titleText;
        this.detailText = detailText;
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
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        binding = FragmentBlankBinding.inflate(inflater, container, false);

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

        TextView titleText = (TextView) getView().findViewById(R.id.textViewTitle);
        titleText.setText(this.titleText);
        TextView detailText = (TextView) getView().findViewById(R.id.textViewDescription);
        detailText.setText(this.detailText);
    }


    public void clearTextFragment() {
        ConstraintLayout constraintLayout = binding.frameLayout;
        constraintLayout.removeAllViews();
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
        binding = null;
    }

}