package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.weathermon.MainActivity;
import com.example.weathermon.R;
import com.example.weathermon.database.Util;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPageAdminButton#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageAdminButton extends Fragment {
    ImageButton buttonAdministrator;

    public MainPageAdminButton() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainPageAdminButton.
     */
    public static MainPageAdminButton newInstance() {
        MainPageAdminButton fragment = new MainPageAdminButton();
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
        View view = inflater.inflate(R.layout.fragment_main_page_admin_button, container, false);
        container.findViewById(R.id.buttonAdministrator).setOnClickListener();
        buttonAdministrator=(ImageButton) view.findViewById(R.id.buttonAdministrator);
        buttonAdministrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "I work", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}