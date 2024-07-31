package com.example.weathermon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.nameTextView.setText(monster.getMonster_name());
        holder.statsTextView.setText("HP: " + monster.getBaseHP() + ", ATK: " + monster.getBaseAttack() + ", DEF: " + monster.getBaseDefense());
        // Set the monster image if available
        // holder.monsterImageView.setImageResource(R.drawable.monster_image); // example
    }

    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    static class MonsterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, statsTextView;
        ImageView monsterImageView;

        public MonsterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.monster_name);
            statsTextView = itemView.findViewById(R.id.monster_stats);
            monsterImageView = itemView.findViewById(R.id.monster_image);
        }
    }
}

