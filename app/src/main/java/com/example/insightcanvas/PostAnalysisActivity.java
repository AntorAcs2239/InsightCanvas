package com.example.insightcanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.insightcanvas.databinding.ActivityMainBinding;
import com.example.insightcanvas.databinding.ActivityPostAnalysisBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class PostAnalysisActivity extends AppCompatActivity {
    PieChart pieChart;
    ActivityPostAnalysisBinding binding;
    int positive,negative,neutral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_analysis);
        binding= ActivityPostAnalysisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        positive=getIntent().getIntExtra("positive",0);
        negative=getIntent().getIntExtra("negative",0);
        neutral=getIntent().getIntExtra("neutral",0);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(positive, "Positive"));
        entries.add(new PieEntry(negative, "Negative"));
        entries.add(new PieEntry(neutral, "Neutral"));

        int positiveColor = Color.parseColor("#27ae60");
        int negativeColor = Color.parseColor("#e74c3c");
        int neutralColor = Color.parseColor("#f39c12");

        PieDataSet dataSet = new PieDataSet(entries, " Opinions Distribution");
        dataSet.setColors(positiveColor, negativeColor, neutralColor);
        PieData data = new PieData(dataSet);

        binding.pieAnalysis.setData(data);
        binding.pieAnalysis.getDescription().setEnabled(false);
        binding.pieAnalysis.setHoleRadius(25f);
        binding.pieAnalysis.setTransparentCircleRadius(30f);
        binding.pieAnalysis.animateY(2000, Easing.EaseInOutCubic);
        binding.pieAnalysis.setVisibility(View.VISIBLE);

        Legend legend = binding.pieAnalysis.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setForm(Legend.LegendForm.CIRCLE);

        legend.getEntries()[0].label = "Positive";
        legend.getEntries()[1].label = "Negative";
        legend.getEntries()[2].label = "Neutral";

        legend.getEntries()[0].formColor = Color.GREEN;
        legend.getEntries()[1].formColor = Color.RED;
        legend.getEntries()[2].formColor = Color.YELLOW;
        binding.pieAnalysis.invalidate();

    }
}