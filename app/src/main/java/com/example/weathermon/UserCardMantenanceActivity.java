package com.example.weathermon;

import static com.example.weathermon.database.Util.WEATHERMON_LOGGED_IN_USER_ID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityUserCardMantenanceBinding;
import com.example.weathermon.viewholders.CardMaintenanceAdapter;
import com.example.weathermon.viewholders.CardMaintenanceViewModel;

public class UserCardMantenanceActivity extends AppCompatActivity {
    ActivityUserCardMantenanceBinding binding;
    private WeathermonRepository repository;
    private CardMaintenanceViewModel cardMaintenanceViewModel;

    private int loggedInUserID = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCardMantenanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = WeathermonRepository.getRepository(getApplication());

        cardMaintenanceViewModel = new ViewModelProvider(this).get(CardMaintenanceViewModel.class);

        RecyclerView recyclerView = binding.cardMaintenanceDisplayRecyclerView;

        final CardMaintenanceAdapter adapter = new CardMaintenanceAdapter(new CardMaintenanceAdapter.CardMaintenanceDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cardMaintenanceViewModel.getAllCardsByID(loggedInUserID).observe(this, cardList -> {
            adapter.submitList(cardList);
        });


    }

    static Intent userCardMaintenanceIntentFactory(Context context, int userID){
        Intent intent = new Intent(context, UserCardMantenanceActivity.class);
        intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, userID);
        return intent;
    }

}