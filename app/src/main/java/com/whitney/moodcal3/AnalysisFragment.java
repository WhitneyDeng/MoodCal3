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
        for (int count = 1; count <= NUMBER_OF_MOOD_TYPES; count++)
        {
            String key = Integer.toString(count);
            data.add(new ValueDataEntry(key, analysis.findNoOfSelectedMood(count)));
        }

        pie.data(data);

        AnyChartView anyChartView = v.findViewById(R.id.any_chart_view_pie);
        anyChartView.setChart(pie);

        return v;
    }

    public void setEntryStorage(EntryStorage es)
    {
        entryStorage = es;
        analysis = new Analysis(es);
    }
}
