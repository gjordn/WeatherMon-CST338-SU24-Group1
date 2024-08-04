package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weathermon.TrainWeathermon;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.databinding.FragmentLocationSelectionFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationSelectionFragment extends Fragment {
    public FragmentLocationSelectionFragmentBinding fragmentLocationSelectionFragmentBinding;
    public LocationSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LocationSelectionFragment.
     */
    public static LocationSelectionFragment newInstance() {
        LocationSelectionFragment fragment = new LocationSelectionFragment();
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
        fragmentLocationSelectionFragmentBinding = FragmentLocationSelectionFragmentBinding.inflate(inflater, container, false);
        TrainWeathermon trainWeathermon = (TrainWeathermon) getActivity();
        Location trainingLocation = trainWeathermon.getLocationInfo();

        fragmentLocationSelectionFragmentBinding.arenaNameIDTextView.setText(trainingLocation.getArenaNameToString());
        fragmentLocationSelectionFragmentBinding.tempBoostTextView.setText(trainingLocation.getTemperatureToString());
        fragmentLocationSelectionFragmentBinding.dayOrNightText.setText(trainingLocation.getDayOrNightToString());
        fragmentLocationSelectionFragmentBinding.windBonusTextView.setText(trainingLocation.getWindspeedToString());
        fragmentLocationSelectionFragmentBinding.humidityBonusText.setText(trainingLocation.getHumidityToString());

        fragmentLocationSelectionFragmentBinding.tempBoostImage.setImageResource(trainingLocation.getTempBoostImage());
        fragmentLocationSelectionFragmentBinding.lightDarkBoostImage.setImageResource(trainingLocation.getIsDayBoostImage());
        fragmentLocationSelectionFragmentBinding.windBonus.setImageResource(trainingLocation.getWindBoostImage());
        fragmentLocationSelectionFragmentBinding.humidityBonusImage.setImageResource(trainingLocation.getHumidityBoostImage());


        return fragmentLocationSelectionFragmentBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentLocationSelectionFragmentBinding = null;
    }
}