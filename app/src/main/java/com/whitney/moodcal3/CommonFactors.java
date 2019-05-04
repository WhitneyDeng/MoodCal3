package com.whitney.moodcal3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This program analyses and provides the common factors that triggers a positive or negative emotion
 * for the table in the Common Factor tab
 *
 * @author  Whitney Deng
 * @version 1.0
 * @since   4-5-2019
 */

public class CommonFactors
{
    private EntryStorage entryStorage;
    private HashMap<String, Object> sadCommonFactors;
    private HashMap<String, Object> happyCommonFactors;
    private ArrayList<Entry> happyEntries;
    private ArrayList<Entry> sadEntries;

    public static final int SAD_MOOD_VALUE_CUTOFF = 3;
    public static final int ZERO = 0;
    public static final int NOT_ENOUGH_DATA_CUTOFF = 10;

    public CommonFactors(EntryStorage es)
    {
        entryStorage = es;
        sadCommonFactors = new HashMap<>();
        happyCommonFactors = new HashMap<>();
        happyEntries = new ArrayList<>();
        sadEntries = new ArrayList<>();

        createEntryLists();
    }

    /**
     * This method sorts through the entries and sort them into sad and happy list
     */
    private void createEntryLists()
    {
        Collection<Entry> entries = entryStorage.getEntryStorage().values();

        for (Entry currentEntry : entries)
        {
            if (currentEntry.getMood().getMoodValue() < SAD_MOOD_VALUE_CUTOFF) //moodvalue < 3 is sad
            {
                sadEntries.add(currentEntry);
            }
            else
            {
                happyEntries.add(currentEntry);
            }
        }
    }

    /**
     * This method is reusable. It extracts the data for each factor from the entryArrayList and
     * inputs them into the respective ArrayLists. Finally, it puts the categorised ArrayLists into
     * their analysis methods and puts the analysed ArrayLists into a HashMap that stores the factor
     * in correspondence to it's analysed data (e.g. key: sleep duration; value: 5 (hours)).
     *
     * @param entryArrayList is an ArrayList of entries
     *
     * @return HashMap of the factors and their corresponding data
     */
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

    /**
     * This method is reusable. It finds the average int
     *
     * @param intList method returns the average of the values in this list
     *
     * @return int of the average value
     */
    private int findAverageInt(ArrayList<Integer> intList)
    {
        int count = ZERO;
        int sum = ZERO;

        for (Integer integer: intList)
        {
            sum += integer;
            count++;
        }

        if (count == ZERO) //to prevent mathematical error of dividing by 0 when the list is empty
        {
            count = 1;
        }
        return sum/count;
    }

    /**
     * This method is reusable. It finds most common boolean
     *
     * @param boolList method returns the most common boolean in this List
     *
     * @return boolean of the most common boolean
     */
    private boolean findAverageBoolean(ArrayList<Boolean> boolList)
    {
        int trueCount = ZERO;
        int falseCount = ZERO;

        for (Boolean bool: boolList)
        {
            if (bool)
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

    /**
     * This method returns Hashmap of factors causing sadness
     *
     * @return HashMap of factors causing sadness
     */
    public HashMap<String, Object> getSadCommonFactors()
    {
        sadCommonFactors = findCommonFactors(sadEntries);
        return sadCommonFactors;
    }

    /**
     * This method returns Hashmap of factors causing happiness
     *
     * @return HashMap of factors causing happiness
     */
    public HashMap<String, Object> getHappyCommonFactors()
    {
        happyCommonFactors = findCommonFactors(happyEntries);
        return happyCommonFactors;
    }

    /**
     * This method assesses whether there is enough data (more than 10 entries)
     *
     * @return boolean of whether there is enough data
     */
    public boolean enoughData()
    {
        if (entryStorage.getEntryStorage().size() < NOT_ENOUGH_DATA_CUTOFF)
        {
            return false;
        }
        return true;
    }
}
