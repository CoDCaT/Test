package com.google.developer.bugmaster.data;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.developer.bugmaster.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.InsectHolder> {

    private List<Insect> insects = new ArrayList<>();

    public MyAdapter() {
    }

    public MyAdapter(List<Insect> insects) {
        this.insects = insects;
    }

    public void setInsects(List<Insect> insects) {
        this.insects = insects;
    }

    public class InsectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtCommonName, txtScientificName;

        public InsectHolder(View itemView) {
            super(itemView);

            txtCommonName = (TextView) itemView.findViewById(R.id.txtCommonName);
            txtScientificName = (TextView) itemView.findViewById(R.id.txtScientificName);

        }

        @Override
        public void onClick(View v) {

        }
    }

    private Cursor mCursor;

    @Override
    public InsectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bug_item, parent, false);
        return new InsectHolder(v);
    }

    @Override
    public void onBindViewHolder(InsectHolder holder, int position) {
        Insect insect = insects.get(position);

        holder.txtCommonName.setText(insect.getName());
        holder.txtScientificName.setText(insect.getScientificName());

    }

    @Override
    public int getItemCount() {
        return insects.size();
    }

    public Insect getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            throw new IllegalArgumentException("Item position is out of adapter's range");
        } else if (mCursor.moveToPosition(position)) {
            return new Insect(mCursor);
        }
        return null;
    }
}
