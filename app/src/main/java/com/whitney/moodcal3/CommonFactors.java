package com.whitney.moodcal3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CommonFactors
{
    private EntryStorage entryStorage;
    private HashMap<String, Object> sadCommonFactors;
    private HashMap<String, Object> happyCommonFactors;
    private ArrayList<Entry> happyEntries;
    private ArrayList<Entry> sadEntries;

    public CommonFactors(EntryStorage es)
    {
        entryStorage = es;
        sadCommonFactors = new HashMap<String, Object>();
        happyCommonFactors = new HashMap<String, Object>();
        happyEntries = new ArrayList<Entry>();
        sadEntries = new ArrayList<Entry>();

        createEntryLists();
    }

    //sorts through the entries and sort them into sad and happy list
    private void createEntryLists()
    {
        Collection<Entry> entries = entryStorage.getEntryStorage().values();

        for (Entry currentEntry : entries)
        {
            if (currentEntry.getMood().getMoodValue() < 3) //moodvalue < 3 is sad
            {
                sadEntries.add(currentEntry);
            }
            else
            {
                happyEntries.add(currentEntry);
            }
        }
    }

    //reusable code, finds the common factors that leads to a mood
    private HashMap<String, Object> findCommonFactors(ArrayList<Entry> entryArrayList)
    {
        ArrayList<Integer> sleepDuration = new ArrayList<>();
        ArrayList<Boolean> medication = new ArrayList<>();
        ArrayList<Integer> mCycle = new ArrayList<>();

        for (Entry entry: entryArrayList)
        {
            sleepDuration.add(entry.getSleepDuration());
            medication.add(entry.getMedication());
            mCycle.add(entry.getmCycle());
        }

        HashMap<String, Object> commonFactors = new HashMap<>();

        commonFactors.put("Sleep Duration", findAverageInt(sleepDuration));
        commonFactors.put("Medication", findAverageBoolean(medication));
        commonFactors.put("Menstrual Cycle", findAverageInt(mCycle));

        return commonFactors;
    }

    //reusable code; finds the average int
    private int findAverageInt(ArrayList<Integer> intList)
    {
        int count = 0;
        int sum = 0;

        for (Integer integer: intList)
        {
            sum += integer;
            count++;
        }

        if (count == 0) //to prevent mathematical error of dividing by 0
        {
            count = 1;
        }
        return sum/count;
    }

    //reusable code; finds most common boolean
    private boolean findAverageBoolean(ArrayList<Boolean> boolList)
    {
        int trueCount = 0;
        int falseCount = 0;

        for (Boolean bool: boolList)
        {
            if (bool == true)
            {
                trueCount++;
            }
            else
            {
                falseCount++;
            }
        }

        if (trueCount > falseCount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //returns Hashmap of factors causing sadness
    public HashMap<String, Object> getSadCommonFactors()
    {
        sadCommonFactors = findCommonFactors(sadEntries);
        return sadCommonFactors;
    }

    //returns Hashmap of factors causing happiness
    public HashMap<String, Object> getHappyCommonFactors()
    {
        happyCommonFactors = findCommonFactors(happyEntries);
        return happyCommonFactors;
    }

    //returns false if insufficient data
    public boolean enoughData()
    {
        if (entryStorage.getEntryStorage().size() < 10) //todo need static final for int 10
        {
//            return false; //todo change it back to false
        }
        return true;
    }
}
