package com.google.developer.bugmaster.data;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.developer.bugmaster.features.details.InsectDetailsActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.views.DangerLevelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public List<Insect> getInsects() {
        return insects;
    }

    public class InsectHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtCommonName) TextView txtCommonName;
        @BindView(R.id.txtScientificName) TextView txtScientificName;
        @BindView(R.id.danger_level) DangerLevelView dangerLevelView;

        public InsectHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

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
        holder.dangerLevelView.setDangerLevel(insect.getDangerLevel());
        holder.itemView.setOnClickListener(v -> {
                Insect itemInsect = getItem(holder.getAdapterPosition());
                Intent intent = new Intent(v.getContext(), InsectDetailsActivity.class);
                intent.putExtra("insect", itemInsect);
                v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return insects.size();
    }

    public Insect getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            throw new IllegalArgumentException("Item position is out of adapter's range");
        } else if (insects.get(position) != null) {
            return insects.get(position);
        }
        return null;
    }
}
