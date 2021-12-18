package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.NctTestSubjectInfo;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;

import java.util.List;

public class NctTestListAdapter extends RecyclerView.Adapter<NctTestListAdapter.SubjectViewHolder> {

    private List<NctTestSubjectInfo> nctTestInfoList;
    private OnItemListener onItemListener;
    private Context context;

    public NctTestListAdapter(List<NctTestSubjectInfo> nctTestInfoList, OnItemListener onItemListener, Context context) {
        this.nctTestInfoList = nctTestInfoList;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    public void setValues(List<NctTestSubjectInfo> nctTestInfoList) {
        this.nctTestInfoList.clear();
        if(nctTestInfoList!=null) {
            this.nctTestInfoList.addAll(nctTestInfoList);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_practice_ort_test_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int i) {
        NctTestSubjectInfo testInfo = nctTestInfoList.get(i);
        holder.subject_title.setText(testInfo.getSubject().getName()+" ("+testInfo.getGrade()+" класс, "+testInfo.getVariant()+" вариант)");
    }

    @Override
    public int getItemCount() {
        return nctTestInfoList != null ? nctTestInfoList.size() : 0;
    }

    public interface OnItemListener {
        void onItemClick(int i);
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        public TextView subject_title;

        OnItemListener onItemListener;

        public SubjectViewHolder(View view, OnItemListener onItemListener) {
            super(view);
            subject_title = view.findViewById(R.id.subject_item_title);
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
