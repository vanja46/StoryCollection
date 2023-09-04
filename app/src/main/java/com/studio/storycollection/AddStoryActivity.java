package com.studio.storycollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStoryActivity extends AppCompatActivity {

    EditText titleEditText, storyEditText;
    Button saveButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        titleEditText = findViewById(R.id.titleEditText);
        storyEditText = findViewById(R.id.storyEditText);
        saveButton = findViewById(R.id.saveButton);

        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String story = storyEditText.getText().toString().trim();

                if (title.isEmpty() || story.isEmpty()) {
                    Toast.makeText(AddStoryActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                long id = databaseHelper.addStory(title, story);

                if (id > 0) {
                    Toast.makeText(AddStoryActivity.this, "Story added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddStoryActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddStoryActivity.this, "Failed to add story", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
