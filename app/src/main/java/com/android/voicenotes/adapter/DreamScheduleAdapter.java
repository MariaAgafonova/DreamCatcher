package com.android.voicenotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.voicenotes.R;
import com.android.voicenotes.model.Note;
import com.android.voicenotes.utils.CalendarUtils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;


public class DreamScheduleAdapter extends AbstractRecyclerViewAdapter<Note> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    public DreamScheduleAdapter(Context context, OnViewHolderClick<Note> listener) {
        super(context, listener);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dream_item, viewGroup, false);

        return view;
    }

    @Override
    protected void bindView(final Note item, AbstractRecyclerViewAdapter.ViewHolder viewHolder) {
        if (item != null) {
            TextView status = (TextView) viewHolder.getView(R.id.noteText);
            ImageView imageStatus = (ImageView) viewHolder.getView(R.id.status);
            LinearLayout menu = (LinearLayout) viewHolder.getView(R.id.menuItem);


            status.setText(item.getText());
           if(item.isDone()){
               imageStatus.setImageResource(R.drawable.ic_check_circle);
           }else{
               imageStatus.setImageResource(R.drawable.ic_event_note);
           }
           menu.setVisibility(View.GONE);


        }
    }



    @Override
    public long getHeaderId(int position) {
        return CalendarUtils.getDay(getItem(position).getTime());
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_schedule_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.headerText);
        textView.setText(CalendarUtils.getWeekDateWithYear(getItem(position).getTime()));
    }


}
