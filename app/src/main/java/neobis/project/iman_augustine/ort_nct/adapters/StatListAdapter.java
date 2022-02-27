package  neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.statistics_model.TestStat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class StatListAdapter extends RecyclerView.Adapter<StatListAdapter.StatisticsViewHolder> {

    private List<TestStat> statisticsList;
    private OnItemListener onItemListener;
    private Context context;

    public StatListAdapter(List<TestStat> statisticsList, OnItemListener onItemListener, Context context) {
        this.statisticsList = statisticsList;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    public void setValues(List<TestStat> statList) {
        this.statisticsList.clear();
        if(statList!=null) {
            this.statisticsList.addAll(statList);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stat_item, parent, false);

        return new StatisticsViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(StatisticsViewHolder holder, int i) {
        String title = statisticsList.get(i).getSubjectName() ;
        String variant = statisticsList.get(i).getVariant()+" вариант\n"
                +  statisticsList.get(i).getGrade() + " класс";

        holder.subject_title.setText(title); // Setting title
        holder.pieChart.setVisibility(View.VISIBLE);
        holder.pieChart.setEnabled(false);
        holder.pieChart.setSaveEnabled(false);
        holder.pieChart.setRotationEnabled(false);
        holder.pieChart.setTouchEnabled(false);
        holder.pieChart.setDrawSlicesUnderHole(false);

        holder.pieEntries = new ArrayList<>();
        holder.pieEntries.add(new PieEntry(statisticsList.get(i).getIncorrect(), ""));
        holder.pieEntries.add(new PieEntry(statisticsList.get(i).getCorrect(), ""));

        PieDataSet pieDataSet = new PieDataSet(holder.pieEntries, variant );
        pieDataSet.setFormSize(0);
        pieDataSet.setValueTextSize(15);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        holder.pieChart.setData(pieData);

        Description description = new Description();
        description.setText("");

        holder.pieChart.setDescription(description);
        holder.pieChart.invalidate();
    }

    @Override
    public int getItemCount() {
        return statisticsList != null ? statisticsList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnItemListener {
        void onRemoveClick(int i);
        void onItemClick(int i);
    }

    public static class StatisticsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnItemListener onItemListener;
        private TextView subject_title;
        private List<PieEntry> pieEntries;
        private PieChart pieChart;

        private StatisticsViewHolder(View view, final OnItemListener onItemListener) {
            super(view);
            subject_title = view.findViewById(R.id.subject_title);
            pieChart = view.findViewById(R.id.pie_chart_nct);

            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
