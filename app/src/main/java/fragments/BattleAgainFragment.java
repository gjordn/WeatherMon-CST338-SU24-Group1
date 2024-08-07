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
 * Use the {@link BattleAgainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleAgainFragment extends Fragment {

    public BattleAgainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BattleAgainFragment.
     */
    public static BattleAgainFragment newInstance() {
        BattleAgainFragment fragment = new BattleAgainFragment();
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
        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
        ImageButton buttonBattleAgain;
        View view = inflater.inflate(R.layout.fragment_battle_again, container, false);

        buttonBattleAgain=view.findViewById(R.id.buttonBattleAgain);
        buttonBattleAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainWeathermonActivity.setUpBattleLocation();
            }
        });

        return view;
    }
}