package org.fossasia.openevent.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.fossasia.openevent.app.R;
import org.fossasia.openevent.app.model.Event;
import org.fossasia.openevent.app.views.EventDetailsActivity;

import java.util.List;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventRecyclerViewHolder>{

    private List<Event> events;
    private Context context;

    public EventListAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public EventRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);

        return new EventRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventRecyclerViewHolder holder, int position) {

        final Event thisEvent = events.get(position);
        holder.eventTitle.setText(thisEvent.getName());

        String thumbnail = thisEvent.getThumbnail();

        if(thumbnail != null && !TextUtils.isEmpty(thumbnail)) {
            Picasso.with(context)
                .load(thumbnail)
                .into(holder.eventImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventDetailsActivity.class);
                i.putExtra("position", holder.getAdapterPosition());
                i.putExtra("id", thisEvent.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    //view holder class
    class EventRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView eventTitle;
        ImageView eventImage;

        EventRecyclerViewHolder(View itemView) {
            super(itemView);
            eventTitle = (TextView) itemView.findViewById(R.id.tvEventTitle);
            eventImage = (ImageView) itemView.findViewById(R.id.ivEventProfile);
        }

    }

}