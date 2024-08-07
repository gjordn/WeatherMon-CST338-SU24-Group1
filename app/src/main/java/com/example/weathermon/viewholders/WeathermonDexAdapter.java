package com.example.weathermon.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.R;
import com.example.weathermon.Weathermon;

import java.util.List;

public class WeathermonDexAdapter extends RecyclerView.Adapter<WeathermonDexAdapter.WeathermonViewHolder> {

    private List<Weathermon> weathermonList;

    public static class WeathermonViewHolder extends RecyclerView.ViewHolder {
        public ImageView monsterImage;
        public TextView monsterName;
        public TextView monsterStats;

        public WeathermonViewHolder(View itemView) {
            super(itemView);
            monsterImage = itemView.findViewById(R.id.monster_image);
            monsterName = itemView.findViewById(R.id.monster_name);
            monsterStats = itemView.findViewById(R.id.monster_stats);
        }
    }

    public WeathermonDexAdapter(List<Weathermon> weathermonList) {
        this.weathermonList = weathermonList;
    }

    @NonNull
    @Override
    public WeathermonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weathermon_item, parent, false);
        return new WeathermonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeathermonViewHolder holder, int position) {
        Weathermon weathermon = weathermonList.get(position);
        holder.monsterImage.setImageResource(weathermon.getImageResource());
        holder.monsterName.setText(weathermon.getName());
        holder.monsterStats.setText(weathermon.getWeatherType());
    }

    @Override
    public int getItemCount() {
        return weathermonList.size();
    }
}




