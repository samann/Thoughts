package droidowl.thoughts;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by droidowl on 5/9/16.
 */
public class RecordRecyclerAdapter extends Adapter{

    List<RecordPieces> mPieces;

    Context context;

    int id;

    public RecordRecyclerAdapter(List<RecordPieces> pieces) {
        mPieces = pieces;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .record_cardview, parent, false);
        RecordViewHolder rvh = new RecordViewHolder(v);
        return rvh;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecordViewHolder recordViewHolder = (RecordViewHolder) holder;
        recordViewHolder.title.setText(mPieces.get(position).getTitle());
        recordViewHolder.body.setText(mPieces.get(position).getBody());
    }


    @Override
    public int getItemCount() {
        return mPieces.size();
    }

    public static class RecordViewHolder extends ViewHolder{
        CardView cv;
        TextView title;
        TextView body;

        public RecordViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            title = (TextView) itemView.findViewById(R.id.card_title);
            body = (TextView) itemView.findViewById(R.id.card_body);
         }
    }
}
