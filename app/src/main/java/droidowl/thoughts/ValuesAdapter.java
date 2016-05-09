package droidowl.thoughts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by droidowl on 4/11/16.
 */
public class ValuesAdapter extends ArrayAdapter<ThoughtValue> {

    Context context;

    int id;

    List<ThoughtValue> mValues;

    public ValuesAdapter(Context c, int i, List<ThoughtValue> list) {
        super(c, i, list);
        this.context = c;
        this.id = i;
        this.mValues = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ValueHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(id, parent, false);
            holder = new ValueHolder();
            holder.title = (TextView) row.findViewById(R.id
                    .value_title_textview);
            holder.rank = (TextView) row.findViewById(R.id
                    .value_rank_textview);
            setupValue(position, holder);
            row.setTag(holder);
            return row;
        } else {
            holder = (ValueHolder) row.getTag();
            setupValue(position, holder);
            return convertView;
        }
    }

    private void setupValue(int position, ValueHolder holder) {
        ThoughtValue value = mValues.get(position);
        holder.rank.setText(String.valueOf(value.getRank()));
        holder.title.setText(value.getTitle());
    }

    static class ValueHolder {
        TextView title;
        TextView rank;
    }
}
