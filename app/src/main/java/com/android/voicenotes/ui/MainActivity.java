package com.android.voicenotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.voicenotes.R;
import com.android.voicenotes.adapter.AbstractRecyclerViewAdapter;
import com.android.voicenotes.adapter.DreamScheduleAdapter;
import com.android.voicenotes.db.Preferences;
import com.android.voicenotes.model.Note;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listNotes;
    private DreamScheduleAdapter dreamScheduleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final View mainView = inflater.inflate(R.layout.activity_main, null);
        setContentView(mainView);
        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbarMain);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView addNote = (ImageView) mainView.findViewById(R.id.addNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ActivityAddDream.class);
            }
        });

        listNotes = (RecyclerView) mainView.findViewById(R.id.list);


        dreamScheduleAdapter = new DreamScheduleAdapter(this, new AbstractRecyclerViewAdapter.OnViewHolderClick<Note>() {
            @Override
            public void onClick(View view, int position, final Note item) {
                LinearLayout menu = (LinearLayout) view.findViewById(R.id.menuItem);
                if(menu.isShown()){
                    menu.setVisibility(View.GONE);
                }else{
                    menu.setVisibility(View.VISIBLE);
                }
                ImageView delete = (ImageView) view.findViewById(R.id.deleteNote);
                ImageView done = (ImageView) view.findViewById(R.id.done);
                if(item.isDone()){
                    done.setVisibility(View.GONE);
                }else {
                    done.setVisibility(View.VISIBLE);
                }
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dreamScheduleAdapter.setList( Preferences.getInstance().deleteNote(item));
                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       dreamScheduleAdapter.setList( Preferences.getInstance().doneNote(item));
                    }
                });

            }
        });
        listNotes.setAdapter(dreamScheduleAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listNotes.setLayoutManager(layoutManager);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(dreamScheduleAdapter);
        listNotes.addItemDecoration(headersDecor);
        dreamScheduleAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

    }


    public synchronized void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dreamScheduleAdapter.setList(Preferences.getInstance().getNotes());
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
