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
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AnalysisFragment extends Fragment
{
    EntryStorage entryStorage;
    Analysis analysis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.analysis, container, false);

        //Line Graph

        //Pie Chart
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("1", analysis.findNoOfSelectedMood(1)));
        data.add(new ValueDataEntry("2", analysis.findNoOfSelectedMood(2)));
        data.add(new ValueDataEntry("3", analysis.findNoOfSelectedMood(3)));
        data.add(new ValueDataEntry("4", analysis.findNoOfSelectedMood(4)));
        data.add(new ValueDataEntry("5", analysis.findNoOfSelectedMood(5)));

        pie.data(data);

        AnyChartView anyChartView = v.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

        return v;
    }

    public void setEntryStorage(EntryStorage es)
    {
        entryStorage = es;
    }
}
