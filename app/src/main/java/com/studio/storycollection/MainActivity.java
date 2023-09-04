package com.studio.storycollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView storyListView;
    ArrayList<String> storyList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storyListView = findViewById(R.id.storyListView);
        FloatingActionButton addButton = findViewById(R.id.addButton);

        databaseHelper = new DatabaseHelper(this);

        // Populate ListView
        updateListView();

        //Search Story
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


        // Add a story
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStoryActivity.class);
                startActivity(intent);
            }
        });

        // Delete a story on long click
        storyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String title = storyList.get(position);
                databaseHelper.deleteStory(title);
                updateListView();
                Toast.makeText(MainActivity.this, "Story deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // View a story on click
        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = storyList.get(position);
                String story = databaseHelper.getStory(title);
                Intent intent = new Intent(MainActivity.this, ViewStoryActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("story", story);
                startActivity(intent);
            }
        });
    }
    private void filter(String text){
        ArrayList<String> filteredList = new ArrayList<>();
        for (String item : storyList) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        storyListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    // Method to update the ListView
    private void updateListView() {
        storyList = databaseHelper.getAllStories();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storyList);
        storyListView.setAdapter(arrayAdapter);
    }
}
