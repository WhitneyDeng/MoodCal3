package com.whitney.moodcal3;

import android.graphics.drawable.Drawable;

public class Mood
{
    //    private String mood;
    private int moodValue;
    private String colour;

    public Mood(int mv)
    {
        moodValue = mv;
        setColour(mv);
    }

    //resets the colour depending on the mood value
    private void setColour(int mv)
    {
        switch (mv)
        {
            case 1:
        }
    }

    public void setMoodValue(int mv)
    {
        moodValue = mv;
        setColour(mv);
    }

    public int getMoodValue()
    {
        return moodValue;
    }
}
