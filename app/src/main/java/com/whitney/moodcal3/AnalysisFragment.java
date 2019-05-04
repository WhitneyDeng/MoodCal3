package com.whitney.moodcal3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

/**
 * This program generates the Analysis fragment interface.
 *
 * @author  Whitney Deng
 * @version 1.0
 * @since   4-5-2019
 */

public class AnalysisFragment extends Fragment
{
    EntryStorage entryStorage;
    Analysis analysis;

    public static final int NUMBER_OF_MOOD_TYPES = 5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.analysis, container, false);

        //Line Graph (for future development)

        //Pie Chart
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        for (int count = 1; count <= NUMBER_OF_MOOD_TYPES; count++) //loops through the mood types
        {
            String key = Integer.toString(count); //the key is the moodValue
            data.add(new ValueDataEntry(key, analysis.findNoOfSelectedMood(count))); //the value is the number of entries with the mood value
        }

        pie.data(data); //enters the data into the pie chart

        AnyChartView anyChartView = v.findViewById(R.id.any_chart_view_pie);
        anyChartView.setChart(pie);

        return v;
    }

    /**
     * This method sets the entry storage (this was for the firebase integration)
     *
     * @param es the entry storage to be assigned
     */
    public void setEntryStorage(EntryStorage es)
    {
        entryStorage = es;
        analysis = new Analysis(es);
    }
}
