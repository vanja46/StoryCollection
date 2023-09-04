package com.studio.storycollection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStoryActivity extends AppCompatActivity {

    TextView storyTextView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);

        storyTextView = findViewById(R.id.storyTextView);

        databaseHelper = new DatabaseHelper(this);

        // Get the passed title from MainActivity
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        if (title != null && !title.isEmpty()) {
            String story = databaseHelper.getStory(title);
            if (story != null) {
                storyTextView.setText(story);
            } else {
                storyTextView.setText("Sorry, this story could not be found.");
            }
        } else {
            storyTextView.setText("Sorry, this story could not be found.");
        }
    }
}
