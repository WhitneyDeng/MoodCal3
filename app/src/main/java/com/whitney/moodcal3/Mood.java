package com.whitney.moodcal3;

/**
 * This program stores the data of a mood
 * @author  Whitney Deng
 * @version 1.0
 * @since   4-5-2019
 */

public class Mood
{
    private int moodValue;
    private String colour; //future development

    public Mood(int mv)
    {
        moodValue = mv;
        setColour(mv);
    }

    /**
     * This method resets the colour depending on the mood value (future development)
     *
     * @param mv the mood value
     */
    //resets the colour depending on the mood value (unfinished) (future development)
    private void setColour(int mv)
    {
        switch (mv)
        {
            case 1:
        }
    }

    public int getMoodValue()
    {
        return moodValue;
    }
}
