package com.example.taskmaster;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> noteList;
    private Executor executor = Executors.newSingleThreadExecutor();

    public NoteRepo(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList = noteDao.getAllData();
    }

    public void insertData(Note note) {
        executor.execute(() -> {
            System.out.println(note.getTitle());
            noteDao.insert(note);
        });
    }

    public void updateData(Note note) {
        executor.execute(() -> noteDao.update(note));
    }

    public void deleteData(Note note) {
        executor.execute(() -> noteDao.delete(note));
    }

    public LiveData<List<Note>> getAllData() {
        return noteList;
    }
}
