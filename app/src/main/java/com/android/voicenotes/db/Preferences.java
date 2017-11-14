package com.android.voicenotes.db;

import android.app.Activity;
import android.content.SharedPreferences;

import com.android.voicenotes.ClientApplication;
import com.android.voicenotes.model.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Preferences {
    private static final Preferences instance = new Preferences();
    private static final String APP_SHARED_PREFS = "com.android.voice.notes";
    private static final String NOTES = "notes";
    private final SharedPreferences appSharedPrefs;
    private final SharedPreferences.Editor prefsEditor;


    private Preferences() {
        appSharedPrefs = ClientApplication.getContext().getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        prefsEditor = appSharedPrefs.edit();
    }

    public static Preferences getInstance() {
        return instance;
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        if (appSharedPrefs.contains(NOTES)) {
            final Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Note>>(){}.getType();
            notes = gson.fromJson(appSharedPrefs.getString(NOTES, ""), listType);
        }
        return notes;
    }

    public void saveNote(Note value) {
        ArrayList<Note> notes = getNotes();
        notes.add(value);
        setNotes(notes);
    }

    public ArrayList<Note> deleteNote(Note value) {
        ArrayList<Note> notes = getNotes();
        notes.remove(value);
        setNotes(notes);
        return notes;
    }


    public ArrayList<Note> doneNote(Note note){
        ArrayList<Note> list = getNotes();
       int index = list.indexOf(note);
        Note cur = list.get(index);
        cur.setDone(true);
        list.set(index, cur);
        setNotes(list);
        return list;
    }

    public void setNotes(ArrayList<Note> notes){
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(notes);
        prefsEditor.putString(NOTES, serializedObject);
        prefsEditor.apply();
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "appSharedPrefs=" + appSharedPrefs +
                ", prefsEditor=" + prefsEditor +
                '}';
    }
}
