package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.common.CommonMethod;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.TestAnswerNct;
import neobis.project.iman_augustine.ort_nct.model.ncttestmodel.TestQuestionNct;
import neobis.project.iman_augustine.ort_nct.model.testmodel.Answer;
import neobis.project.iman_augustine.ort_nct.model.testmodel.Question;
import neobis.project.iman_augustine.ort_nct.singleclicklistener.OnSingleClickListener;

public class NctTestAdapter extends RecyclerView.Adapter<NctTestAdapter.SubjectViewHolder> {

    private List<TestQuestionNct> questions;
    private OnItemListener onItemListener;
    private Context context;

    public NctTestAdapter(List<TestQuestionNct> questions, OnItemListener onItemListener, Context context) {
        this.questions = questions;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nct_test_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int pos) {
        try {
            holder.questionWebView.loadData(questions.get(pos).getPayload(), "text/html", "UTF-8");
            List<TestAnswerNct> options = questions.get(pos).getOptions(); // Answer options
           // holder.explanation.setText(questions.get(pos).getExplanation());
            holder.optionWebView1.loadData(options.get(0).getPayload(), "text/html", "UTF-8");
            holder.optionWebView2.loadData(options.get(1).getPayload(), "text/html", "UTF-8");
            holder.optionWebView3.loadData(options.get(2).getPayload(), "text/html", "UTF-8");
            holder.optionWebView4.loadData(options.get(3).getPayload(), "text/html", "UTF-8");
            final int position = pos;
            holder.radioAnswerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int userAnswer = 0;
                    switch(i) {
                        case R.id.radioAnswerOne:
                            userAnswer = 0;
                            break;
                        case R.id.radioAnswerTwo:
                            userAnswer = 1;
                            break;
                        case R.id.radioAnswerThree:
                            userAnswer = 2;
                            break;
                        case R.id.radioAnswerFour:
                            userAnswer = 3;
                    }
                    onItemListener.onAnswerClick(position, userAnswer, holder.radioAnswerGroup);
                }
            });
        } catch(IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
    }
    public int getItemCount() {
        return questions != null ? questions.size() : 0;
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
        void onItemClick(int i);
        void onAnswerClick(int position, int userAnswer, RadioGroup answerGroup);
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemListener onItemListener;
        private WebView questionWebView;
        private CardView explanationCard;
        private WebView optionWebView1,optionWebView2,optionWebView3,optionWebView4;
        private RadioGroup radioAnswerGroup;

        private SubjectViewHolder(View view, final OnItemListener onItemListener) {
            super(view);
            radioAnswerGroup = view.findViewById(R.id.answerRadioGroup);
            questionWebView = view.findViewById(R.id.questionWebView);
            explanationCard = view.findViewById(R.id.explanation_cardview);
            optionWebView1 = view.findViewById(R.id.webView1);
            optionWebView2 = view.findViewById(R.id.webView2);
            optionWebView3 = view.findViewById(R.id.webView3);
            optionWebView4 = view.findViewById(R.id.webView4);

            // explanation = view.findViewById(R.id.explanation_textview);
            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
