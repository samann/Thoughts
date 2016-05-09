package droidowl.thoughts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by droidowl on 2/29/16.
 */
public class ThoughtRecordAdapter extends ArrayAdapter<ThoughtRecord> {
    Context context;
    int id;
    List<ThoughtRecord> mRecords;

    public ThoughtRecordAdapter(Context context, int resource, List<ThoughtRecord>
     records) {
        super(context, resource, records);
        this.context = context;
        this.id = resource;
        this.mRecords = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ThoughtHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(id, parent, false);
            holder = new ThoughtHolder();
            holder.triggerTextView = (TextView)row.findViewById(R.id
                    .list_item_text_view);
            holder.dateTextView = (TextView)row.findViewById(R.id
                    .date_text_view) ;
            holder.timeTextView = (TextView)row.findViewById(R.id
                    .time_text_view);
            row.setTag(holder);
            setupHolder(position, holder);
            return row;
        } else {
            holder = (ThoughtHolder) convertView.getTag();
            setupHolder(position, holder);
            return convertView;
        }

    }

    private void setupHolder(int position, ThoughtHolder holder) {
        ThoughtRecord record = mRecords.get(position);
        holder.triggerTextView.setText(record.getTrigger());
        holder.dateTextView.setText(record.getDate());
        holder.timeTextView.setText(record.getTime());
    }

    @Override
    public void add(ThoughtRecord record) {
        if (mRecords != null) {
            mRecords.add(record);
        } else {
            mRecords = new ArrayList<>();
            mRecords.add(record);
        }
    }

    static class ThoughtHolder {
        TextView triggerTextView;
        TextView dateTextView;
        TextView timeTextView;
    }
}
