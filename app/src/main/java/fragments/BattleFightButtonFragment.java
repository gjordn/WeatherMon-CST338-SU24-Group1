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
 * Use the {@link BattleFightButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFightButtonFragment extends Fragment {


    public BattleFightButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattleFightButtonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BattleFightButtonFragment newInstance(String param1, String param2) {
        BattleFightButtonFragment fragment = new BattleFightButtonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
                  ImageButton buttonBattleStart;
                  View view = inflater.inflate(R.layout.fragment_battle_fight_button, container, false);

                  buttonBattleStart=view.findViewById(R.id.buttonBattleStart);
                  buttonBattleStart.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                         trainWeathermonActivity.runResultsFragment();
                      }
                  });

              return view;
              }

}