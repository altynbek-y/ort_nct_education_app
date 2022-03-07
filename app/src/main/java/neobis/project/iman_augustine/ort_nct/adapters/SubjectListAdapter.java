package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.database_model.Subject;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;

import java.util.List;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder> {
    private List<Subject> subjectList;
    private OnItemListener onItemListener;
    private Context context;

    public SubjectListAdapter(List<Subject> subjectList, SubjectListAdapter.OnItemListener onItemListener, Context context) {
        this.subjectList = subjectList;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    public void setValues(List<Subject> newSubjectList) {
        this.subjectList.clear();
        if(newSubjectList!=null) {
            this.subjectList.addAll(newSubjectList);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_list_test_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int i) {
        holder.subject_name.setText(subjectList.get(i).getSubjectName());
    }

    @Override
    public int getItemCount() {
        return subjectList != null ? subjectList.size() : 0;
    }

    public interface OnItemListener {
        void onItemClick(int i);
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {

        public TextView subject_name;

        OnItemListener onItemListener;

        public SubjectViewHolder(View view, OnItemListener onItemListener) {
            super(view);

            subject_name = view.findViewById(R.id.subject_item_title);
            final OnItemListener onItemListenerFinal = onItemListener;
            this.onItemListener = onItemListener;

            view.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    onItemListenerFinal.onItemClick(getAdapterPosition());

                }
            });
        }
    }
}
