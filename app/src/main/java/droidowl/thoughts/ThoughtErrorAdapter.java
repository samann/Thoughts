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
 * Created by droidowl on 3/1/16.
 */
public class ThoughtErrorAdapter extends ArrayAdapter<ThoughtError> {
    Context context;
    List<ThoughtError> errors;
    int id;

    public ThoughtErrorAdapter(Context context, int resource, List<ThoughtError>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.errors = objects;
        this.id = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ErrorHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(id, parent, false);
            holder = new ErrorHolder();
            holder.title = (TextView)row.findViewById(R.id
                    .error_title_text_view);
            holder.detail = (TextView)row.findViewById(R.id
                    .error_detail_text_view);
            row.setTag(holder);
        } else {
            holder = (ErrorHolder) row.getTag();
        }
        ThoughtError error = errors.get(position);
        holder.title.setText(error.getTitle());
        holder.detail.setText(error.getDetail());
        return row;
    }

    static class ErrorHolder {
        TextView title;
        TextView detail;
    }
}
