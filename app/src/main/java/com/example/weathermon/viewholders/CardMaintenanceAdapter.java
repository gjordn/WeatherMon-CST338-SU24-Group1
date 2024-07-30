package com.example.weathermon.viewholders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weathermon.database.entities.Card;

public class CardMaintenanceAdapter extends ListAdapter<Card, CardMaintenanceViewHolder> {
    public CardMaintenanceAdapter(@NonNull DiffUtil.ItemCallback<Card> diffCallBack){
        super(diffCallBack);
    }

    @NonNull
    @Override
    public CardMaintenanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return CardMaintenanceViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CardMaintenanceViewHolder holder, int position) {
        Card current = getItem(position);
        holder.bind(current.toString());
    }


    public static class GymLogDiff extends DiffUtil.ItemCallback<Card>{
        @Override
        public boolean areItemsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem.equals(newItem);
        }
    }

}
