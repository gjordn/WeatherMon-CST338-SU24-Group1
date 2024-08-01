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
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;
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

    public void bind (CardWithMonster cardWithMonster){
        if (cardWithMonster.getCardCustomName().isEmpty()){
            cardName.setText("Monster: " + cardWithMonster.getMonster_name());
        }else {
            cardName.setText("Name: " + cardWithMonster.getCardCustomName() +
                    "        Monster: " + cardWithMonster.getMonster_name());
        }
        cardStats.setText("XP: " + cardWithMonster.getMonsterXP() +
                            "       Level: " + cardWithMonster.getMonsterLevel() +
                "      Weather: " + Monster.convertWeatherTypeToString.get(cardWithMonster.getWeatherInnate())
        );
        cardLogo.setImageResource(Monster.convertWeatherTypeToDrawable.get(
                cardWithMonster.getWeatherInnate()));
    }

    static CardMaintenanceViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_maintenance_recycler_item, parent, false);
        return new CardMaintenanceViewHolder(view);
    }
}
