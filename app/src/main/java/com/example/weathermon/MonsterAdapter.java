package com.example.weathermon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.entities.Monster;

import java.util.List;

public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder> {
    private List<Monster> monsterList;

    public MonsterAdapter(List<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    @NonNull
    @Override
    public MonsterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monster_item, parent, false);
        return new MonsterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonsterViewHolder holder, int position) {
        Monster monster = monsterList.get(position);
        holder.nameTextView.setText(monster.monster_name);
        holder.hpTextView.setText("HP: " + monster.baseHP);
        holder.attackTextView.setText("Attack: " + monster.baseAttack);
        holder.defenseTextView.setText("Defense: " + monster.baseDefense);
    }

    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    static class MonsterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, hpTextView, attackTextView, defenseTextView;

        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.monster_name);
            hpTextView = itemView.findViewById(R.id.monster_hp);
            attackTextView = itemView.findViewById(R.id.monster_attack);
            defenseTextView = itemView.findViewById(R.id.monster_defense);
        }
    }
}
