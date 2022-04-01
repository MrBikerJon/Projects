package com.furminger.allaboutclovelly.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.furminger.allaboutclovelly.MainActivity;
import com.furminger.allaboutclovelly.RecyclerAdapter;
import com.furminger.allaboutclovelly.databinding.FragmentListBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);

        Context context = requireActivity().getApplicationContext();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerView.setLayoutManager(layoutManager);

        // set up Recycler Adapter to show the list of items
        RecyclerAdapter adapter = new RecyclerAdapter(((MainActivity) getActivity()).getPointsOfInterest());
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}