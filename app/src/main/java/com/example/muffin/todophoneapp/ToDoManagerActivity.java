package com.example.muffin.todophoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

public class ToDoManagerActivity extends AppCompatActivity implements ToDoListFragment.OnListItemSelectedListener, ToDoListFragment.OnNewItemRequestListener, AddToDoItemFragment.OnNewToDoItemCreatedListener {

    //to do detail and list fragment
    public static final String TODO_DETAIL = "to do item details";

    private ToDoListFragment listFragment;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add list fragment
        listFragment = new ToDoListFragment();

        //new fragment manager and transaction
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();

        trans.add(android.R.id.content, listFragment);

        trans.commit();
    }
    //When item is selected
    public void onListItemSelected(String toDoItemDetail) {
        //item selected from list
        //display to do item fragment

        //new fragment manager and transaction
        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();

        //new item fragment
        ToDoItemFragment itemFragment = new ToDoItemFragment();

        //new bundle o' arguments
        Bundle arguments = new Bundle();
        arguments.putString(TODO_DETAIL, toDoItemDetail);
        itemFragment.setArguments(arguments);

        //transaction tag = fragment class name
        trans.addToBackStack(itemFragment.getClass().getName());
        //replace current fragment with item selected
        trans.replace(android.R.id.content, itemFragment);
        trans.commit();
    }

    //When new item requested
    public void onNewItemRequest() {
        //new fragment to enter new item data
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        AddToDoItemFragment addFragment = new AddToDoItemFragment();
        //add to back stack
        //must remove later or it will cycle through all previous additions before exiting
        trans.addToBackStack(addFragment.getClass().getName());
        //replace current fragment with new item fragment
        trans.replace(android.R.id.content, addFragment);
        trans.commit();

    }

    //When a new item is created
    public void newItemCreated(String newItem) {
        if (newItem == null || newItem.equals("")) {
            //show error toast if nothing is entered
            Toast.makeText(this, "No text has been entered, so no item has bee created", Toast.LENGTH_LONG).show();
        } else {
            //new fragment manager and transaction
            FragmentManager mgr = getFragmentManager();
            //pop last backstack from onNewItemRequest
            mgr.popBackStack();
            FragmentTransaction trans = mgr.beginTransaction();
            //replace current fragment with listFragment
            trans.replace(android.R.id.content, listFragment);
            trans.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
