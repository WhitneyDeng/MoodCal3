package com.whitney.moodcal3;

//this section is preserved for future development
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This program provides the analysis for the pie chart in the Analysis tab
 *
 * @author  Whitney Deng
 * @version 1.0
 * @since   4-5-2019
 */

public class Analysis
{
    EntryStorage entryStorage;
    LinkedHashMap<String, Entry> entryLinkedHashMap;

    public Analysis(EntryStorage es)
    {
        entryStorage = es;
        entryLinkedHashMap = entryStorage.getEntryStorage();
    }

    /**
     * This method finds the number of entries with the inputted mood
     *
     * @param moodValue method counts the number of entries with this mood value
     *
     * @return int of the number of entries with the inputted moodValue (mood)
     */
    public int findNoOfSelectedMood (int moodValue)
    {
        int count = 0;

        //this section is preserved for future development (limiting the number of datapoints to the last 30 days)
//        List<String> alKeys = new ArrayList<String>(entryLinkedHashMap.keySet());
//
//        // reverse order of keys
//        Collections.reverse(alKeys);
//
//        for (int index = 0; index < 30; index++)
//        {
//            entryLinkedHashMap.get(alKeys.get(index));
//        }

        for (Map.Entry<String, Entry> entry : entryLinkedHashMap.entrySet())
        {
            if (entry.getValue().getMood().getMoodValue() == moodValue)
            {
                count++;
            }
        }
        return count;
    }
}
