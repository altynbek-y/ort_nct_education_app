package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;

import neobis.project.iman_augustine.ort_nct.model.education_model.EducationModel;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.SubjectViewHolder> {

    private List<EducationModel> educationList;
    private OnItemListener onItemListener;
    private Context context;

    public EducationAdapter(List<EducationModel> learningMaterials, OnItemListener onItemListener, Context context) {
        this.educationList = learningMaterials;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    public void setValues(List<EducationModel> educationList) {
         this.educationList.clear();
         if(educationList!=null) {
             this.educationList.addAll(educationList);
         }
         this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int i) {
        holder.subject_title.setText(""+educationList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return educationList != null ? educationList.size() : 0;
    }

    public interface OnItemListener {
        void onItemClick(int i);
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        private TextView subject_title;
        OnItemListener onItemListener;

        private SubjectViewHolder(View view, OnItemListener onItemListener) {
            super(view);
           /* subject_title = view.findViewById(R.id.subject_item_title);
            final OnItemListener onItemListenerFinal = onItemListener;
            this.onItemListener = onItemListener;
            view.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    view.setEnabled(false);
                    onItemListenerFinal.onItemClick(getAdapterPosition());
                }
            });*/
        }
    }
}
