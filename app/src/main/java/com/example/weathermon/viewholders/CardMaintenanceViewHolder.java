package com.example.weathermon.viewholders;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.R;
import com.example.weathermon.database.entities.Monster;

public class CardMaintenanceViewHolder extends RecyclerView.ViewHolder {

    private final TextView cardName;
    private final TextView cardStats;
    private final ImageView cardLogo;

    private CardMaintenanceViewHolder(View cardMaintenenceView){
        super(cardMaintenenceView);
        cardName = cardMaintenenceView.findViewById(R.id.cardname);
        cardStats = cardMaintenenceView.findViewById(R.id.cardstats);
        cardLogo = cardMaintenenceView.findViewById(R.id.card_image);
    }

    public void bind (String name, String stats, @DrawableRes int logo){
        cardName.setText(name);
        cardStats.setText(stats);
        cardLogo.setImageResource(R.drawable.darklogo);
    }

    static CardMaintenanceViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_maintenance_recycler_item, parent, false);
        return new CardMaintenanceViewHolder(view);
    }
}
