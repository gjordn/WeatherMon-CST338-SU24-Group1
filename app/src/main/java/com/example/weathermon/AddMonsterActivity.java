package com.example.weathermon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Monster;

public class AddMonsterActivity extends AppCompatActivity {
    private EditText nameEditText, hpEditText, attackEditText, defenseEditText, ability1EditText, ability2EditText, ability3EditText, weatherInnateEditText;
    private Button saveButton;
    private WeathermonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monster);

        nameEditText = findViewById(R.id.monster_name);
        hpEditText = findViewById(R.id.base_hp);
        attackEditText = findViewById(R.id.base_attack);
        defenseEditText = findViewById(R.id.base_defense);
        ability1EditText = findViewById(R.id.ability1);
        ability2EditText = findViewById(R.id.ability2);
        ability3EditText = findViewById(R.id.ability3);
        weatherInnateEditText = findViewById(R.id.weather_innate);
        saveButton = findViewById(R.id.save_button);

        db = Room.databaseBuilder(getApplicationContext(),
                WeathermonDatabase.class, "weathermon-database").build();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Monster monster = new Monster();
                monster.setMonster_name(nameEditText.getText().toString());
                monster.setBaseHP(Integer.parseInt(hpEditText.getText().toString()));
                monster.setBaseAttack(Integer.parseInt(attackEditText.getText().toString()));
                monster.setBaseDefense(Integer.parseInt(defenseEditText.getText().toString()));
                monster.setAbility1(Integer.parseInt(ability1EditText.getText().toString()));
                monster.setAbility2(Integer.parseInt(ability2EditText.getText().toString()));
                monster.setAbility3(Integer.parseInt(ability3EditText.getText().toString()));
                monster.setWeatherInnate(Integer.parseInt(weatherInnateEditText.getText().toString()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.monsterDAO().insert(monster);
                        runOnUiThread(() -> finish());
                    }
                }).start();
            }
        });
    }
}
