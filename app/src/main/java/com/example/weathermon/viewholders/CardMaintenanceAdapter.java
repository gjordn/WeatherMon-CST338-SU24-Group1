package com.example.weathermon.viewholders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weathermon.R;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;

public class CardMaintenanceAdapter extends ListAdapter<CardWithMonster, CardMaintenanceViewHolder> {
    public CardMaintenanceAdapter(@NonNull DiffUtil.ItemCallback<CardWithMonster> diffCallBack){
        super(diffCallBack);
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
