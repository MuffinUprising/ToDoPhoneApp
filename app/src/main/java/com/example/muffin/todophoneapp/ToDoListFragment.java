package com.example.muffin.todophoneapp;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;

//should be done..

public class ToDoListFragment extends Fragment {

    //list view and array adapter variables
    private ListView toDoListView;
    private ArrayAdapter<String> toDoArray;

    //button variable
    private Button addNewItem;

    //selected listener and new item listener variables
    private OnListItemSelectedListener selectedListener;
    private OnNewItemRequestListener newItemListener;


    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_to_do_list, group, false);
        toDoListView = (ListView) v.findViewById(R.id.to_do_list_view);

        toDoListView.setAdapter(toDoArray);
        toDoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedListener.onListItemSelected(toDoArray.getItem(position));
            }
        });

        addNewItem = (Button) v.findViewById(R.id.add_new_to_do_item);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemListener.onNewItemRequest();
            }
        });
        return v;
    }

    //onAttach
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        toDoArray = new ArrayAdapter<String>(this.getActivity(), R.layout.list_view_to_do_item, R.id.list_item);
        addTestData();

        try {
            selectedListener = (OnListItemSelectedListener) activity;
            newItemListener = (OnNewItemRequestListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement all required listeners");
        }
    }

    //add some test data
    private void addTestData() {
        toDoArray.add("Clean up garden");
        toDoArray.add("Buy warmer socks");
        toDoArray.add("Clean dishes");
    }

    //add new item
    void addNewItem(String newItem) {
        toDoArray.add(newItem);
        toDoArray.notifyDataSetChanged();
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected(String toDoItem);
    }

    public interface OnNewItemRequestListener {
        void onNewItemRequest();
    }

}
