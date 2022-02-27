package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.test_model.SubjectTest;

import java.util.List;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.SubjectViewHolder> {
    private List<SubjectTest> subjectTests;
    private OnItemListener onItemListener;
    private Context context;

    public TestListAdapter(List<SubjectTest> subjectTests, TestListAdapter.OnItemListener onItemListener, Context context) {
        this.subjectTests = subjectTests;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    public void setValues(List<SubjectTest> educationList) {
        this.subjectTests.clear();
        if(educationList!=null) {
            this.subjectTests.addAll(educationList);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int i) {
        holder.subject_name.setText(subjectTests.get(i).getSubjectName());
    }

    @Override
    public int getItemCount() {
        return subjectTests != null ? subjectTests.size() : 0;
    }

    public interface OnItemListener {
        void onItemClick(int i);
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        public TextView subject_name;

        OnItemListener onItemListener;

        public SubjectViewHolder(View view, OnItemListener onItemListener) {
            super(view);
           /* subject_name = view.findViewById(R.id.subject_item_title);
            final OnItemListener onItemListenerFinal = onItemListener;
            this.onItemListener = onItemListener;
            view.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    onItemListenerFinal.onItemClick(getAdapterPosition());
                }
            });*/
        }
    }
}
