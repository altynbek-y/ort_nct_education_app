package neobis.project.iman_augustine.ort_nct.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.database_model.Question;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionAnswerChoice;
import neobis.project.iman_augustine.ort_nct.model.database_model.QuestionWithAnswers;
import neobis.project.iman_augustine.ort_nct.repository.Repository;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.SubjectViewHolder>
{
    // private List<Question> questionsList;
    private List<QuestionWithAnswers> questionsList;
    private OnItemListener onItemListener;
    private Context context;
    private Repository repository;

    public QuestionListAdapter(
            // List<Question> questionsList,
            List<QuestionWithAnswers> questionsList,
            OnItemListener onItemListener,
            Context context
    )
    {
        this.questionsList = questionsList;
        this.onItemListener = onItemListener;
        this.context = context;
    }

//    public void setValues(List<Question> newQuestionList)
//    {
//        this.questionsList.clear();
//        if(newQuestionList!=null) {
//            this.questionsList.addAll(newQuestionList);
//        }
//        this.notifyDataSetChanged();
//    }

    public void setValues(List<QuestionWithAnswers> newQuestionList)
    {
        this.questionsList.clear();
        if(newQuestionList!=null) {
            this.questionsList.addAll(newQuestionList);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int pos)
    {
        try {
            // String imageUrl;
            //TranslationsImageModel image = questions.get(pos).getTranslation();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String currentLocale = sharedPreferences.getString("locale", "ru");

            QuestionWithAnswers question = questionsList.get(pos);

            String summary = "<html><body>" + question.question +"</body></html>";
            holder.questionWebView.loadData(summary, "text/html; charset=utf-8", "utf-8");

            holder.answerWebViewA.loadData(question.answer_a, "text/html; charset=utf-8", "utf-8");
            holder.answerWebViewB.loadData(question.answer_b, "text/html; charset=utf-8", "utf-8");
            holder.answerWebViewC.loadData(question.answer_c, "text/html; charset=utf-8", "utf-8");
            holder.answerWebViewD.loadData(question.answer_d, "text/html; charset=utf-8", "utf-8");


            // int height, width;
            if(currentLocale.equals("ru")) {
               // imageUrl = image.getRussian().getImageUrl();
               // height = image.getRussian().getHeight();
               // width = image.getRussian().getWidth();
            } else {
               // imageUrl = image.getKyrgyz().getImageUrl();
               // height = image.getKyrgyz().getHeight();
               // width = image.getKyrgyz().getWidth();
            }
            //Picasso.get()
                 //   .load(imageUrl)
                  //  .into(holder.questionImage);

            //final List<Answer> answers = questions.get(pos).getAnswers(); // List of possible answers
            final int position = pos;

            holder.radioAnswerGroup.setOnCheckedChangeListener((radioGroup, i) ->
            {
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
            });
        } catch(IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
    }
    public int getItemCount() {
        return questionsList != null ? questionsList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnItemListener
    {
        void onItemClick(int i);
        void onAnswerClick(int position, int userAnswer, RadioGroup answerGroup);
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnItemListener onItemListener;
        private final RadioGroup radioAnswerGroup;
        private final WebView questionWebView;
        private final WebView answerWebViewA;
        private final WebView answerWebViewB;
        private final WebView answerWebViewC;
        private final WebView answerWebViewD;

        private SubjectViewHolder(View view, final OnItemListener onItemListener)
        {
            super(view);
            radioAnswerGroup = view.findViewById(R.id.answerRadioGroup);
            questionWebView = view.findViewById(R.id.questionWebView);
            answerWebViewA = view.findViewById(R.id.webView1);
            answerWebViewB = view.findViewById(R.id.webView2);
            answerWebViewC = view.findViewById(R.id.webView3);
            answerWebViewD = view.findViewById(R.id.webView4);


            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
