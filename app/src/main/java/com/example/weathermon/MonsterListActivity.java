package com.example.weathermon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.WeathermonDatabase;

import java.util.List;

public class MonsterListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MonsterAdapter monsterAdapter;
    private WeathermonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                WeathermonDatabase.class, "weathermon-database").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Monster> monsters = db.monsterDAO().getAllMonsters();
                runOnUiThread(() -> {
                    monsterAdapter = new MonsterAdapter(monsters);
                    recyclerView.setAdapter(monsterAdapter);
                });
            }
        }).start();

        Button addMonsterButton = findViewById(R.id.add_monster_button);
        addMonsterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MonsterListActivity.this, AddMonsterActivity.class);
            startActivity(intent);
        });
    }
}
