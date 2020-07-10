package com.example.co.squeeg;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hari on 11-12-2018.
 */

public class MyDashboard extends BaseActivity implements OnChartValueSelectedListener {


    @BindView(R.id.chart1)
    PieChart chart;
    ArrayList<PieEntry> entries = new ArrayList<>();
    PieDataSet pieDataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dashboard);
        ButterKnife.bind(this);


        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterText("Total Points");
        chart.setCenterTextColor(getResources().getColor(R.color.colorPrimary));

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(40f);
        chart.setTransparentCircleRadius(42f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        chart.setOnChartValueSelectedListener(this);

        chart.animateX(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(12f);


        entries.add(new PieEntry(5f, "Completed", 0));
        entries.add(new PieEntry(3f, "New", 1));
        entries.add(new PieEntry(16f, "Refunded", 2));
        entries.add(new PieEntry(4f, "Declined", 3));

        pieDataSet = new PieDataSet(entries, "GIGS Results");
        pieDataSet.setDrawIcons(false);

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(pieDataSet);
//        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("GIGS");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 4, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 4, s.length() - 4, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 4, s.length() - 4, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 4, s.length() - 4, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 4, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 4, s.length(), 0);
        return s;
    }

}
