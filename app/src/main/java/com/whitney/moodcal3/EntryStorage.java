package com.whitney.moodcal3;

import java.util.Date;
import java.util.HashMap;

public class EntryStorage
{
    private HashMap<String, Entry> entryStorage;
    private int daySinceLastMenstruation;

    public EntryStorage()
    {
        entryStorage = new HashMap<>();
        daySinceLastMenstruation = 0;
    }

    public HashMap<String, Entry> getEntryStorage()
    {
        return entryStorage;
    }

    public int getDaySinceLastMenstruation()
    {
        return daySinceLastMenstruation;
    }

    //sets daySinceLastMenstruation to 0
    public void firstMenstruationDay()
    {
        daySinceLastMenstruation = 0;
    }
}
