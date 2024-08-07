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
 * Use the {@link BattleNextButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleNextButtonFragment extends Fragment {

    public BattleNextButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BattleNextButtonFragment.
     */
    public static BattleNextButtonFragment newInstance() {
        BattleNextButtonFragment fragment = new BattleNextButtonFragment();
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

        ImageButton buttonNext;
        View view = inflater.inflate(R.layout.fragment_battle_next_button, container, false);

        buttonNext=view.findViewById(R.id.buttonBattleNextPage);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               trainWeathermonActivity.buttonNextPage();
            }
        });

    return view;
    }
}