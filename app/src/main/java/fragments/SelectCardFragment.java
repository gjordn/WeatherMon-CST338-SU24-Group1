package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weathermon.R;
import com.example.weathermon.TrainWeathermonActivity;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.viewholders.CardMaintenanceAdapter;
import com.example.weathermon.viewholders.CardMaintenanceViewModel;
import com.example.weathermon.viewholders.CardSelectListener;

public class SelectCardFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_select_card, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardMaintenanceViewModel cardMaintenanceViewModel = new ViewModelProvider(this).get(CardMaintenanceViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.cardSelectCardRecyclerView);

        final CardMaintenanceAdapter adapter = new CardMaintenanceAdapter(new CardMaintenanceAdapter.CardMaintenanceDiff(), new CardSelectListener() {
            @Override
            public void onItemClicked(CardWithMonster cardWithMonster) {
                TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
                trainWeathermonActivity.setCardToTrain(cardWithMonster);

            }

            @Override
            public void onItemLongClicked(CardWithMonster cardWithMonster) {

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
        int loggedInUserID = trainWeathermonActivity.getUserID();


        cardMaintenanceViewModel.getAllCardsByID(loggedInUserID).observe(getViewLifecycleOwner(), cardList -> {
            adapter.submitList(cardList);
        });


    }


}