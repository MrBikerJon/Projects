package com.ebookfrenzy.storagedemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;

import com.ebookfrenzy.storagedemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    // register an activity launcher to handle the opening of file content
    ActivityResultLauncher<Intent> startOpenForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    Uri currentUri = data.getData();
                                    try {
                                        String content =
                                                readFileContent(currentUri);
                                        binding.fileText.setText(content);
                                    } catch (IOException e) {
                                        // Handle error here
                                    }
                                }
                            }
                        }
                    });

    // register an activity launcher to handle the saving of content
    ActivityResultLauncher<Intent> startSaveForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    Uri currentUri = data.getData();
                                    try {
                                        writeFileContent(currentUri);
                                    } catch (IOException e) {
                                        // Handle error here
                                    }
                                }
                            }
                        }
                    });

    ActivityResultLauncher<Intent> startCreateForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data != null) {
                            binding.fileText.setText("");
                        }
                    }
                }
            });

    // method to create a file
    // When the New button is selected, the application needs to trigger an ACTION_CREATE_DOCUMENT
    // intent, configured to create a file with a plain-text MIME type
    public void newFile(View view) {
        // create a new ACTION_CREATE_INTENT Intent object
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);


        // configure the intent so that only files that can be opened with a file descriptor are
        // returned
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // specify that the file to be opened is to have a plain text MIME type, with a placeholder
        // filename (which can be changed by the user in the picker interface
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "newfile.txt");

        // make a csll to the startCreateForResult() lambda, passed with the Uri of the newly
        // created document
        startCreateForResult.launch(intent);
    }

    public void saveFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");

        startSaveForResult.launch(intent);
    }

    // method to save a file.
    // The user interface is configured to call the saveFile() method when the Save button is
    // selected by the user. This method will be responsible for starting a new intent of type
    // ACTION_OPEN_DOCUMENT which will result in the picker user interface appearing
    private void writeFileContent(Uri uri) throws IOException
    {

        try{
            // begin by obtaining and opening the file descriptor from the Uri of the file selected
            // by the user
            ParcelFileDescriptor pfd =
                    this.getContentResolver().
                            openFileDescriptor(uri, "w");

            // the file desriptor is used as the basis for creating an output stream that will
            // enable the application to write to the file
            FileOutputStream fileOutputStream =
                    new FileOutputStream(
                            pfd.getFileDescriptor());

            // The text entered by the user is extracted from the edit text object
            String textContent =
                    binding.fileText.getText().toString();

            // the text is written to the output stream
            fileOutputStream.write(textContent.getBytes());

            fileOutputStream.close();
            pfd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to open and read a file from the storage

    public void openFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startOpenForResult.launch(intent);
    }

    // readFileContent() method that is called by the startOpenForResult() lambda
    private String readFileContent(Uri uri) throws IOException {

        // extract the file descriptor for the selected text file
        InputStream inputStream =
                getContentResolver().openInputStream(uri);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(
                        inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String currentline;
        while ((currentline = reader.readLine()) != null) {
            stringBuilder.append(currentline).append("\n");
        }
        inputStream.close();
        return stringBuilder.toString();
    }

}