package droidowl.thoughts;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by droidowl on 5/10/16.
 */
public class ValueRecyclerAdapter extends RecyclerView.Adapter {

    List<ThoughtValue> mValues;

    Context mContext;

    ThoughtsApplication mApplication;

    public ValueRecyclerAdapter(List<ThoughtValue> values, Context context,
                                ThoughtsApplication application) {
        mApplication = application;
        mValues = values;
        mContext = context;
    }

    @Override
    public ValueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .value_card_view, parent, false);
        ValueViewHolder rvh = new ValueViewHolder(v, parent.getContext(),
                this);
        return rvh;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ValueViewHolder valueViewHolder = (ValueViewHolder) holder;
        valueViewHolder.setPosition(position);
        valueViewHolder.setValue(mValues.get(position));
        if (!mValues.isEmpty()) {
            if (valueViewHolder.title != null || valueViewHolder.rank != null) {
                valueViewHolder.rank.setText(String.valueOf(mValues.get(position)
                        .getRank()));
                valueViewHolder.title.setText(String.valueOf(mValues.get
                        (position).getTitle()));
            }
        }
    }

    public ThoughtValue delete(int position) {
        ThoughtValue old = mValues.get(position);
        mValues.remove(old);
        mApplication.mFirebase.child("value").child(old.getKey()).removeValue();
        notifyDataSetChanged();
        return old;
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ValueViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{
        ThoughtValue mValue;
        Context mContext;
        CardView cv;
        TextView title;
        TextView rank;
        ValueRecyclerAdapter mAdapter;
        private int position;

        public ValueViewHolder(View itemView, Context context,
                               ValueRecyclerAdapter adapter) {
            super(itemView);
            mContext = context;
            mAdapter = adapter;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            cv = (CardView) itemView.findViewById(R.id.value_cardview);
            title = (TextView) itemView.findViewById(R.id.value_title);
            rank = (TextView) itemView.findViewById(R.id.value_rank_textview);
        }

        public void setValue(ThoughtValue value) {
            mValue = value;
        }

        @Override
        public void onClick(View v) {
            Log.d("Value", mValue.getTitle());
        }

        @Override
        public boolean onLongClick(View v) {
            mAdapter.delete(position);
            return true;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
