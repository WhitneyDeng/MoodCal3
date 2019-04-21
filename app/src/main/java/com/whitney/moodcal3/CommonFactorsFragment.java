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
import java.util.Map;

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
        HashMap<String, TextView> happyFactorsHashMap = new HashMap<>();
        HashMap<String, TextView> sadFactorsHashMap = new HashMap<>();

        commonFactors = new CommonFactors(entryStorage);

        happySleepDuration = v.findViewById(R.id.HappySleepDuration);
        happyMedication = v.findViewById(R.id.HappyMedication);
        happyMenstruation = v.findViewById(R.id.HappyMenstruation);
        sadSleepDuration = v.findViewById(R.id.SadSleepDuration);
        sadMedication = v.findViewById(R.id.SadMedication);
        sadMenstruation = v.findViewById(R.id.SadMenstruation);

        happyFactorsHashMap.put("Sleep Duration", happySleepDuration);
        happyFactorsHashMap.put("Medication", happyMedication);
        happyFactorsHashMap.put("Menstrual Cycle", happyMenstruation);
        sadFactorsHashMap.put("Sleep Duration", sadSleepDuration);
        sadFactorsHashMap.put("Medication", sadMedication);
        sadFactorsHashMap.put("Menstrual Cycle", sadMenstruation);

        HashMap<String, TextView> hashMapInUse = new HashMap<>();
        HashMap<String, Object> commonFactorSourceInUse = new HashMap<>();

        for (int countMap = 1; countMap <= 2; countMap++) //1st time happy, 2nd time sad
        {
            switch (countMap)
            {
                case 1: // set textview of happy factors
                    hashMapInUse = happyFactorsHashMap;
                    commonFactorSourceInUse = commonFactors.getHappyCommonFactors();
                    break;
                case 2: // set textview of sad factors
                    hashMapInUse = sadFactorsHashMap;
                    commonFactorSourceInUse = commonFactors.getSadCommonFactors();
            }

            for (Map.Entry<String, TextView> entry : hashMapInUse.entrySet())
            {
                String key = entry.getKey();
                TextView value = entry.getValue();

                if (commonFactors.enoughData())//if there are enough data to do analysis
                {
                    if (key.equals("Medication")) //future dev: this can check for bool object type in commonFactorSourceInUse
                    {
                        value.setText(medicationStatus((boolean) commonFactorSourceInUse.get(key)));
                    }
                    else
                    {
                        value.setText(commonFactorSourceInUse.get(key).toString());
                    }
                }
                else //if not enough data
                {
                    value.setText(NOT_ENOUGH_DATA_MESSAGE);
                }
            }
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
