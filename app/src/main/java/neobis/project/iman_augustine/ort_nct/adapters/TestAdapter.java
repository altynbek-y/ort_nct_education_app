package neobis.project.iman_augustine.ort_nct.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import neobis.project.iman_augustine.ort_nct.R;
import neobis.project.iman_augustine.ort_nct.model.testmodel.Answer;
import neobis.project.iman_augustine.ort_nct.model.testmodel.Question;
import neobis.project.iman_augustine.ort_nct.model.testmodel.TranslationsImageModel;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.SubjectViewHolder> {

    private List<Question> questions;
    private OnItemListener onItemListener;
    private Context context;

    public TestAdapter(List<Question> questions, OnItemListener onItemListener, Context context) {
        this.questions = questions;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ort_test_item, parent, false);

        return new SubjectViewHolder(itemView, this.onItemListener);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int pos) {
        try {
            String imageUrl;
            TranslationsImageModel image = questions.get(pos).getTranslation();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String currentLocale = sharedPreferences.getString("locale", "ru");

            // int height, width;
            if(currentLocale.equals("ru")) {
                imageUrl = image.getRussian().getImageUrl();
               // height = image.getRussian().getHeight();
               // width = image.getRussian().getWidth();
            } else {
                imageUrl = image.getKyrgyz().getImageUrl();
               // height = image.getKyrgyz().getHeight();
               // width = image.getKyrgyz().getWidth();
            }
            Picasso.get()
                    .load(imageUrl)
                    .into(holder.questionImage);

            final List<Answer> answers = questions.get(pos).getAnswers(); // List of possible answers
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
        private RadioGroup radioAnswerGroup;
        private ImageView questionImage;
        private TextView explanation;
        private CardView explanationCard;

        private SubjectViewHolder(View view, final OnItemListener onItemListener) {
            super(view);
            radioAnswerGroup = view.findViewById(R.id.answerRadioGroup);
            questionImage = view.findViewById(R.id.questionImage);
            explanation = view.findViewById(R.id.explanation_textview);
            explanationCard = view.findViewById(R.id.explanation_cardview);

            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

}
