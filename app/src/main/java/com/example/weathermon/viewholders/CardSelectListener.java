package com.example.weathermon.viewholders;

import com.example.weathermon.database.entities.CardWithMonster;

public interface CardSelectListener {
    void onItemClicked(CardWithMonster cardWithMonster);

    void onItemLongClicked(CardWithMonster current);
}
