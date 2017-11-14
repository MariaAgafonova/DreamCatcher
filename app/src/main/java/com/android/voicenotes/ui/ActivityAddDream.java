package com.android.voicenotes.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.voicenotes.R;
import com.android.voicenotes.db.Preferences;
import com.android.voicenotes.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 17.10.17.
 */

public class ActivityAddDream extends AppCompatActivity {

    private ImageButton deleteNote, saveNote, startVoice;
    private TextView textNote;
    private static final int REQUEST_CODE = 1234;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final View mainView = inflater.inflate(R.layout.activity_add_dream, null);
        setContentView(mainView);

        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbarAddDream);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textNote = (TextView) mainView.findViewById(R.id.textNote);

        deleteNote = (ImageButton) mainView.findViewById(R.id.deleteNote);
        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNote.setText("");
            }
        });

        saveNote = (ImageButton) mainView.findViewById(R.id.saveNote);
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textNote.getText().toString();
                if(!text.isEmpty()){
                    Note note = new Note();
                    note.setText(textNote.getText().toString());
                    note.setTime(System.currentTimeMillis());
                    note.setDone(false);
                    Preferences.getInstance().saveNote(note);
                    onBackPressed();
                }
            }
        });

        startVoice = (ImageButton) mainView.findViewById(R.id.startVoice);
        startVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVoiceRecognizer();
                startVoiceRecognitionActivity();
            }
        });

    }
    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getResources().getString(R.string.speak));
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            if(!matches.isEmpty()){
                textNote.setText(matches.get(0));
            }
        }

//            wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                    matches));

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void checkVoiceRecognizer(){
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0)
        {
            startVoice.setEnabled(false);
            Toast.makeText(this,getResources().getString(R.string.recognized_not_found), Toast.LENGTH_SHORT).show();
        }
    }

}
