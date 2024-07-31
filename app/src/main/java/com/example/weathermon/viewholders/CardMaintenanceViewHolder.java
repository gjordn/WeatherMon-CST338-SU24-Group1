package com.example.weathermon.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.R;

public class CardMaintenanceViewHolder extends RecyclerView.ViewHolder {

    private final TextView cardMaintenanceViewItem;

    private CardMaintenanceViewHolder(View cardMaintenenceView){
        super(cardMaintenenceView);
        cardMaintenanceViewItem = cardMaintenenceView.findViewById(R.id.recyclerCardItemTextView);
    }

    public void bind (String text){
        cardMaintenanceViewItem.setText(text);
    }

    static CardMaintenanceViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_maintenance_recycler_item, parent, false);
        return new CardMaintenanceViewHolder(view);
    }
}
