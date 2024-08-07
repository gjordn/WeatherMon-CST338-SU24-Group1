package fragments;

import static com.example.weathermon.viewholders.CardMaintenanceViewHolder.bonusBackgroundColor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weathermon.R;
import com.example.weathermon.TrainWeathermonActivity;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.databinding.CardMaintenanceRecyclerItemBinding;
import com.example.weathermon.databinding.FragmentResultsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {
    private static final String WINNER_PARAMETER = "WinnerParameter";
    public static final String HERO_WON="hero";
    public static final String VILLAIN_WON="villain";
    private static final String WINNER_MESSAGE = "VICTORY!";
    private static final String LOSER_MESSAGE = "Defeat, better luck next time." ;

    public FragmentResultsBinding fragmentResultsBinding;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String battleWinner;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param winner Parameter 1.
     * @return A new instance of fragment ResultsFragment.
     */
    public static ResultsFragment newInstance(String winner) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(WINNER_PARAMETER, winner);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            battleWinner = getArguments().getString(WINNER_PARAMETER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentResultsBinding = FragmentResultsBinding.inflate(inflater, container, false);

        TrainWeathermonActivity trainWeathermonActivity = (TrainWeathermonActivity) getActivity();
        assert trainWeathermonActivity != null;
        CardWithMonster hero = trainWeathermonActivity.getHero();
        CardWithMonster villan = trainWeathermonActivity.getVillain();

        if (battleWinner.equals(HERO_WON)){
            setCardInformation(hero, fragmentResultsBinding.winnerWeathermon);
            setCardInformation(villan, fragmentResultsBinding.loserWeathermon);
            fragmentResultsBinding.resultTextView.setText(WINNER_MESSAGE);
        } else {
            setCardInformation(villan, fragmentResultsBinding.winnerWeathermon);
            setCardInformation(hero, fragmentResultsBinding.loserWeathermon);
            fragmentResultsBinding.resultTextView.setText(LOSER_MESSAGE);
        }


        return fragmentResultsBinding.getRoot();
    }

    private void setCardInformation(CardWithMonster cardWithMonster, CardMaintenanceRecyclerItemBinding cardWithMonsterDisplayCard) {
        TextView cardName = cardWithMonsterDisplayCard.cardname;
        TextView cardStats = cardWithMonsterDisplayCard.cardstats;
        ImageView cardLogo = cardWithMonsterDisplayCard.cardImage;
        TextView cardLevel = cardWithMonsterDisplayCard.cardlevel;
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