package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.weathermon.R;
import com.example.weathermon.TrainWeathermonActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleTravelButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleTravelButtonFragment extends Fragment {

    public BattleTravelButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BattleTravelButtonFragment.
     */
    public static BattleTravelButtonFragment newInstance() {
        BattleTravelButtonFragment fragment = new BattleTravelButtonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
        ImageButton buttonTravel;

        View view = inflater.inflate(R.layout.fragment_battle_travel_button, container, false);

        buttonTravel=view.findViewById(R.id.buttonBattleTravelNewLocation);
        buttonTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainWeathermonActivity.buttonNewLocation();
            }
        });

        return view;
    }
}