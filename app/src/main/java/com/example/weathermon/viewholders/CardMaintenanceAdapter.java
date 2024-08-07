package com.example.weathermon.viewholders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weathermon.database.entities.CardWithMonster;

public class CardMaintenanceAdapter extends ListAdapter<CardWithMonster, CardMaintenanceViewHolder> {
    private final CardSelectListener cardSelectListener;

    public CardMaintenanceAdapter(@NonNull DiffUtil.ItemCallback<CardWithMonster> diffCallBack, CardSelectListener listener){
        super(diffCallBack);
        this.cardSelectListener = listener;
    }

    @NonNull
    @Override
    public CardMaintenanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return CardMaintenanceViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CardMaintenanceViewHolder holder, int position) {
        CardWithMonster current = getItem(position);
        holder.bind(current);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSelectListener.onItemClicked(current);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cardSelectListener.onItemLongClicked(current);
                return false;
            }
        });
    }


    public static class CardMaintenanceDiff extends DiffUtil.ItemCallback<CardWithMonster>{
        @Override
        public boolean areItemsTheSame(@NonNull CardWithMonster oldItem, @NonNull CardWithMonster newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CardWithMonster oldItem, @NonNull CardWithMonster newItem) {
            return oldItem.equals(newItem);
        }
    }

}
