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

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HomeFragment extends Fragment
{
    private TextView myDate;
    private CalendarView calender;
    private SeekBar moodSB;
    private SeekBar energyLevSB;
    private SeekBar sleepDurSB;
    private CheckBox medicationCB;
    private CheckBox mCycleCB;
    private TextInputLayout notesTI;

    private EntryStorage entryStorage;
    private String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.home, container, false);

        entryStorage = new EntryStorage();

        myDate = v.findViewById(R.id.Date);
        calender = v.findViewById(R.id.calendarView);
        moodSB = v.findViewById(R.id.MoodSeekBar);
        energyLevSB = v.findViewById(R.id.EnergyLevelSeekBar);
        sleepDurSB = v.findViewById(R.id.SleepDurationSeekBar);
        medicationCB = v.findViewById(R.id.MedicationCheckBox);
        mCycleCB = v.findViewById(R.id.MenstruationCheckBox);
        notesTI = v.findViewById(R.id.NotesTextInput);

        myDate.setText(currentDate()); // set the date to current date

        //when a date is clicked on the date indication changes
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2)
            {
                String newDate = i2 + "/" + (i1 + 1) + "/" + i; //string of the date
                date = newDate;
                myDate.setText(newDate); //set the date as the selected date

                displayEntryProfile();
            }
        });

        //set new mood when the seekbar changes
        moodSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                checkEntryExistence();
                Mood newMood = new Mood(i);
                entryStorage.getEntryStorage().get(date).setMood(newMood);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                System.out.print("k");
            }
        });

        //set new energy level when seekbar changes
        energyLevSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                checkEntryExistence();
//                FirebaseDatabase.getInstance().getReference().child("Calendar").updateChildren()child(date).setValue(i);
                entryStorage.getEntryStorage().get(date).setEnergyLevel(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        //set new sleepDuration
        sleepDurSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                checkEntryExistence();
                entryStorage.getEntryStorage().get(date).setSleepDuration(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        //set medication
        medicationCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                checkEntryExistence();
                entryStorage.getEntryStorage().get(date).setMedication(b);
            }
        });

        //set mCycle
        mCycleCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                checkEntryExistence();
                entryStorage.getEntryStorage().get(date).setmCycleBool(b);
                entryStorage.getEntryStorage().get(date).setmCycle(findMCycle());
            }
        });

        //set note
        notesTI.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                checkEntryExistence();
                entryStorage.getEntryStorage().get(date).setNote(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        return v;
    }

    //returns the string of the current date
    private String currentDate()
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    //returns day since last menstruation
    private int findMCycle()
    {
        if (mCycleCB.isChecked())
        {
            entryStorage.firstMenstruationDay();
        }

        return entryStorage.getDaySinceLastMenstruation();
    }

    //checks if an entry exists, if it doesn't a new entry is created
    private void checkEntryExistence()
    {
        if (!entryStorage.getEntryStorage().containsKey(date))
        {
            entryStorage.getEntryStorage().put(date, new Entry());
        }
    }

    //displays the entry on the selected day
    private void displayEntryProfile()
    {
        checkEntryExistence();

        Entry currentDateEntry = entryStorage.getEntryStorage().get(date);

        moodSB.setProgress(currentDateEntry.getMood().getMoodValue());
        energyLevSB.setProgress(currentDateEntry.getEnergyLevel());
        sleepDurSB.setProgress(currentDateEntry.getSleepDuration());
        medicationCB.setChecked(currentDateEntry.getMedication());
        mCycleCB.setChecked(currentDateEntry.getmCycleBoolean());
        notesTI.getEditText().setText(currentDateEntry.getNote());
    }

    public void setEntryStorage(EntryStorage es)
    {
        entryStorage = es;
    }
}
