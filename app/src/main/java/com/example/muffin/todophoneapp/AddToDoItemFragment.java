package com.example.muffin.todophoneapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddToDoItemFragment extends Fragment {

    //button and details variables
    Button saveButton;
    EditText details;

    OnNewToDoItemCreatedListener newItemListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_to_do_item, group, false);
        details = (EditText) v.findViewById(R.id.new_todo_text);

        saveButton = (Button) v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get text from EditText and send to host Activity
                String newText = details.getText().toString();
                newItemListener.newItemCreated(newText);
            }
        });

        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            newItemListener = (OnNewToDoItemCreatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNewToDoItemCreatedListener");
        }
    }

    interface OnNewToDoItemCreatedListener {
        void newItemCreated(String newItem);
    }

}
