package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taskmaster.databinding.ActivityDataInsertBinding;
import com.example.taskmaster.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setHasFixedSize(true);
        RVAdapter adapter = new RVAdapter();
        binding.Rv.setAdapter(adapter);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
            Note note = new Note(title, disp);
            noteViewModel.insert(note);
        }
    }
}