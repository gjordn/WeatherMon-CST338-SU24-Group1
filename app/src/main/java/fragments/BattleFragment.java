package fragments;

import static com.example.weathermon.viewholders.CardMaintenanceViewHolder.bonusBackgroundColor;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weathermon.R;
import com.example.weathermon.TrainWeathermonActivity;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.databinding.CardMaintenanceRecyclerItemBinding;
import com.example.weathermon.databinding.FragmentBattleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFragment extends Fragment {
    public FragmentBattleBinding fragmentBattleBinding;



    public BattleFragment() {
        // Required empty public constructor
    }

    public static BattleFragment newInstance(String param1, String param2) {
        BattleFragment fragment = new BattleFragment();
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
        fragmentBattleBinding = FragmentBattleBinding.inflate(inflater, container, false);

        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
        assert trainWeathermonActivity != null;
        Location trainingLocation = trainWeathermonActivity.getLocationInfo();
        CardWithMonster hero = trainWeathermonActivity.getHero();
        CardWithMonster villan = trainWeathermonActivity.getVillan();

        fragmentBattleBinding.arenaNameIDTextView.setText(trainingLocation.getArenaName());
        setCardInformation(hero, fragmentBattleBinding.ourHeroWeathermon);
        setCardInformation(villan, fragmentBattleBinding.ourVillanWeathermon);


        return fragmentBattleBinding.getRoot();
    }




    private void setCardInformation(CardWithMonster cardWithMonster, CardMaintenanceRecyclerItemBinding cardWithMonsterDisplayCard) {
        TextView cardName = cardWithMonsterDisplayCard.cardname;
        TextView cardStats = cardWithMonsterDisplayCard.cardstats;
        ImageView cardLogo = cardWithMonsterDisplayCard.cardImage;
        TextView cardLevel = cardWithMonsterDisplayCard.cardlevel;
        CardView cardView = cardWithMonsterDisplayCard.maincardcontainer;
        LinearLayout cardLayout =  cardWithMonsterDisplayCard.linlayoutcard;

        String line;
        if (cardWithMonster.getCardCustomName().isEmpty()){
            line = ("Monster: " + cardWithMonster.getMonster_name());
        }else {
            line = ("Name: " + cardWithMonster.getCardCustomName() +
                    "        Monster: " + cardWithMonster.getMonster_name());
        }
        cardName.setText(line);
        line=("XP: " + cardWithMonster.getMonsterXP() + "/" + cardWithMonster.getXPToNextLevel() +
                "       Level: " + cardWithMonster.getLevelFromXP() +
                "      Weather: " + Monster.convertWeatherTypeToString.get(cardWithMonster.getWeatherInnate())        );
        cardLevel.setText(line);
        int logo;
        if (Monster.convertWeatherTypeToDrawable.get(
                cardWithMonster.getWeatherInnate()) != null){
            logo=Monster.convertWeatherTypeToDrawable.get(
                    cardWithMonster.getWeatherInnate());
        } else {
            logo = R.drawable.noicon;
        }
        line = ("HP: " + cardWithMonster.getTotalHP() +
                "      ATK: " + cardWithMonster.getTotalAttack() +
                "      DEF: " + cardWithMonster.getTotalDefense());

        cardStats.setText(line);
        cardLogo.setImageResource(logo);

        if (CardWithMonster.getBattleLocation()!=null &&
                CardWithMonster.getBattleLocation().hasBonus(cardWithMonster.getWeatherInnate())){
            cardLayout.setBackgroundColor(bonusBackgroundColor);
        }

    }

}