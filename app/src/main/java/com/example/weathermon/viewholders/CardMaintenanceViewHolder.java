package com.example.weathermon.viewholders;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.R;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.Monster;

public class CardMaintenanceViewHolder extends RecyclerView.ViewHolder {

    private final TextView cardName;
    private final TextView cardStats;
    private final TextView cardLevel;
    private final ImageView cardLogo;
    public final CardView cardView;
    private final LinearLayout cardLayout;
    public static final int bonusBackgroundColor = Color.parseColor("#9edba0");

    private CardMaintenanceViewHolder(View cardMaintenenceView){
        super(cardMaintenenceView);
        cardName = cardMaintenenceView.findViewById(R.id.cardname);
        cardStats = cardMaintenenceView.findViewById(R.id.cardstats);
        cardLogo = cardMaintenenceView.findViewById(R.id.card_image);
        cardLevel = cardMaintenenceView.findViewById(R.id.cardlevel);
        cardView = cardMaintenenceView.findViewById(R.id.maincardcontainer);
        cardLayout =  cardMaintenenceView.findViewById(R.id.linlayoutcard);
    }

    public void bind (CardWithMonster cardWithMonster){
        String line;
        if (cardWithMonster.getCardCustomName().isEmpty()){
            line = ("Monster: " + cardWithMonster.getMonster_name());
        }else {
            line = ("Name: " + cardWithMonster.getCardCustomName() +
                    "        Monster: " + cardWithMonster.getMonster_name());
        }
        cardName.setText(line);
        line=("XP: " + cardWithMonster.getMonsterXP() + "/" + cardWithMonster.getXPToNextLevel() +
                            "       Level: " + cardWithMonster.getLevelFromXP() +
                "      Weather: " + Monster.convertWeatherTypeToString.get(cardWithMonster.getWeatherInnate())        );
        cardLevel.setText(line);
        int logo;
        if (Monster.convertWeatherTypeToDrawable.get(
                cardWithMonster.getWeatherInnate()) != null){
            logo=Monster.convertWeatherTypeToDrawable.get(
                    cardWithMonster.getWeatherInnate());
        } else {
            logo = R.drawable.noicon;
        }
        line = ("HP: " + cardWithMonster.getTotalHP() +
                "      ATK: " + cardWithMonster.getTotalAttack() +
                "      DEF: " + cardWithMonster.getTotalDefense());

        cardStats.setText(line);
        cardLogo.setImageResource(logo);

        if (CardWithMonster.getBattleLocation()!=null &&
                CardWithMonster.getBattleLocation().hasBonus(cardWithMonster.getWeatherInnate())){
            cardLayout.setBackgroundColor(bonusBackgroundColor);
        }
    }

    static CardMaintenanceViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_maintenance_recycler_item, parent, false);
        return new CardMaintenanceViewHolder(view);
    }
}
