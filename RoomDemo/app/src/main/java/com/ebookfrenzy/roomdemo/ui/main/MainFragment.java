package com.ebookfrenzy.roomdemo.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebookfrenzy.roomdemo.Product;
import com.ebookfrenzy.roomdemo.R;
import com.ebookfrenzy.roomdemo.databinding.MainFragmentBinding;

import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private ProductListAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void clearFields() {
        binding.productID.setText("");
        binding.productName.setText("");
        binding.productQuantity.setText("");
    }

    private void listenerSetup() {

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.productName.getText().toString();
                String quantity = binding.productQuantity.getText().toString();

                if(!name.equals("") && !quantity.equals("")) {
                    Product product = new Product(name,
                            Integer.parseInt(quantity));
                    mViewModel.insertProduct(product);
                    clearFields();
                } else {
                    binding.productID.setText("Incomplete information");
                }
            }
        });

        binding.findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.findProduct(binding.productName.getText().toString());
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteProduct(binding.productName.getText().toString());
                clearFields();
            }
        });
    }

    // observers to remain synchronized with the searchResults and allProducts live data objects
    // within the ViewModel
    private void observerSetup() {

        // this observer passes the current list of products to the setProductList() method
        // of the RecyclerAdapter where the displayed list will be updated
        mViewModel.getAllProducts().observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> products) {
                        adapter.setProductList(products);
                    }
                });

        // this adapter checks that at least one matching result has been located in the database,
        // extracts the first matching Product entity object from the list, gets the data from the
        // object, converts it where necessary and assigns it to the TextView and EditText views in
        // the layout. If the product search failed, the user is notified view a message displayed
        // on the product ID TextView
        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> products) {

                        if(products.size() > 0) {
                            binding.productID.setText(String.format(Locale.US, "%d",
                                    products.get(0).getId()));
                            binding.productName.setText(String.format(Locale.US, "%d",
                                    products.get(0).getQuantity()));
                        } else {
                            binding.productID.setText("No match");
                        }
                    }
                });
    }

    // this method initializes and configures the RecyclerView and adapter
    private void recyclerSetup() {
        adapter = new ProductListAdapter(R.layout.product_list_item);
        binding.productRecycler.setLayoutManager(
                new LinearLayoutManager(getContext()));
        binding.productRecycler.setAdapter(adapter);
    }

}