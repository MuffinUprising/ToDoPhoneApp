package com.example.muffin.todophoneapp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

//should be done..
public class ToDoItemFragment extends Fragment {

    TextView toDoDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_to_do_item, group, false);

        toDoDetail = (TextView) v.findViewById(R.id.to_do_item_text);
        String itemText = getArguments().getString(ToDoManagerActivity.TODO_DETAIL);
        toDoDetail.setText(itemText);

        return v;
    }



}
