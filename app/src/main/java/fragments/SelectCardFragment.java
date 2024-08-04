package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weathermon.R;
import com.example.weathermon.viewholders.CardMaintenanceViewModel;

public class SelectCardFragment extends Fragment {

    private CardMaintenanceViewModel mViewModel;

    public static SelectCardFragment newInstance() {
        return new SelectCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_card, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CardMaintenanceViewModel.class);
        // TODO: Use the ViewModel
    }

}