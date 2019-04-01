package com.whitney.moodcal3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CommonFactorsFragment extends Fragment
{
    private EntryStorage entryStorage;
    private CommonFactors commonFactors;

    private TextView happySleepDuration;
    private TextView happyMedication;
    private TextView happyMenstruation;
    private TextView sadSleepDuration;
    private TextView sadMedication;
    private TextView sadMenstruation;

    private static final String NOT_ENOUGH_DATA_MESSAGE = "Not enough data";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.commonfactors, container, false);

        commonFactors = new CommonFactors(entryStorage);

        happySleepDuration = v.findViewById(R.id.HappySleepDuration);
        happyMedication = v.findViewById(R.id.HappyMedication);
        happyMenstruation = v.findViewById(R.id.HappyMenstruation);
        sadSleepDuration = v.findViewById(R.id.SadSleepDuration);
        sadMedication = v.findViewById(R.id.SadMedication);
        sadMenstruation = v.findViewById(R.id.SadMenstruation);

        if (commonFactors.enoughData())
        {
            HashMap<String, Object> sadCommonFactors = commonFactors.getSadCommonFactors();
            HashMap<String, Object> happyCommonFactors = commonFactors.getHappyCommonFactors();

            happySleepDuration.setText(happyCommonFactors.get("Sleep Duration").toString());
            happyMedication.setText(medicationStatus((boolean) happyCommonFactors.get("Medication")));
            happyMenstruation.setText(happyCommonFactors.get("Menstrual Cycle").toString());

            sadSleepDuration.setText(sadCommonFactors.get("Sleep Duration").toString());
            sadMedication.setText(medicationStatus((boolean) sadCommonFactors.get("Medication")));
            sadMenstruation.setText(sadCommonFactors.get("Menstrual Cycle").toString());
        }
        else
        {
            happySleepDuration.setText(NOT_ENOUGH_DATA_MESSAGE);
            happyMedication.setText(NOT_ENOUGH_DATA_MESSAGE);
            happyMenstruation.setText(NOT_ENOUGH_DATA_MESSAGE);

            sadSleepDuration.setText(NOT_ENOUGH_DATA_MESSAGE);
            sadMedication.setText(NOT_ENOUGH_DATA_MESSAGE);
            sadMenstruation.setText(NOT_ENOUGH_DATA_MESSAGE);
        }
        return v;
    }

    //if true returns "taken" else returns "not taken
    private String medicationStatus(boolean b)
    {
        if (b)
        {
            return "taken";
        }
        return  "not taken";
    }

    public void setEntryStorage(EntryStorage es)
    {
        entryStorage = es;
    }
}
