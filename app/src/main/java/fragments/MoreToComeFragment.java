package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weathermon.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreToComeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreToComeFragment extends Fragment {

    public MoreToComeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoreToComeFragment.
     */
    public static MoreToComeFragment newInstance() {
        MoreToComeFragment fragment = new MoreToComeFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_to_come, container, false);
    }
}