package com.example.hayawanpedia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Quiz extends AppCompatActivity {
    private TextView countLabel;
    private ImageView questionImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    private ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    private String[][] quizData = {
            // Sample questions with images, options, and explanations
            {"https://asset.kompas.com/crops/cWj4CZkd1_NP8SuWWRhMxUnK4_U=/0x0:3072x2048/1200x800/data/photo/2022/05/09/62789dfb5bdc7.png", "أخطبوط", "غَنَمٌ", "حِصان", "إرْبِيَان"},//gurita
            {"https://asset.kompas.com/crops/cTyTCDdA52laPhP-lLB1772bDbM=/0x0:780x520/1200x800/data/photo/2019/05/23/3115135182.jpg", "قنديل البحر", "دُلْفِين", "أخطبوط", "حِصان"},//ubur
            {"https://asset.kompas.com/crops/Qdx3HYTtl-PnMfCfA6CSc9qRZfU=/0x0:1000x667/375x240/data/photo/2023/03/01/63ff1d44a0201.jpg", "قِطَّةٌ", "أخطبوط", "قناديل البحر", "حِصان"},//kucing
            {"https://imgs.mongabay.com/wp-content/uploads/sites/20/2020/04/28104649/Flamingo-friends-credit-Paul-Rose-WWT-Slimbridge-1-e1588085935989-1832x1374.jpg", "فلامنغو", "قِطَّةٌ", "إرْبِيَان", "دُلْفِين"},//flaminggo
            {"https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/12/20062711/Saat-Kelinci-Peliharaan-Terlalu-Gemuk-Lakukan-X-Hal-Ini.jpg.webp", "أرنب", "غَنَمٌ", "إرْبِيَان", "بَطَّةٌ"}//kelinci
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        for (int i = 0; i < quizData.length; i++) {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // Image Name
            tmpArray.add(quizData[i][1]); // Right Answer
            tmpArray.add(quizData[i][2]); // Choice1
            tmpArray.add(quizData[i][3]); // Choice2
            tmpArray.add(quizData[i][4]); // Choice3
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
        if (quizArray.size() > 0) {
            countLabel.setText("Soal " + quizCount);
            Random random = new Random();
            int randomNum = random.nextInt(quizArray.size());
            ArrayList<String> quiz = quizArray.get(randomNum);
            String imageURL = quiz.get(0);

            // Load image from URL using Picasso library
            Picasso.get().load(imageURL).into(questionImage);

            rightAnswer = quiz.get(1);
            quiz.remove(0);
            Collections.shuffle(quiz);
            answerBtn1.setText(quiz.get(0));
            answerBtn2.setText(quiz.get(1));
            answerBtn3.setText(quiz.get(2));
            answerBtn4.setText(quiz.get(3));

            quizArray.remove(randomNum);
        } else {
            showResult();
        }
    }
    public void checkAnswer(View view) {
        Button answerBtn = (Button) view;
        String btnText = answerBtn.getText().toString();

        if (btnText.equals(rightAnswer)) {
            rightAnswerCount += 10;
            jawabanBenar();
        } else {
            jawabanSalah();
        }
    }

    private void jawabanBenar() {
        Toast.makeText(this, "Jawaban Anda benar!", Toast.LENGTH_SHORT).show();
        quizCount++;
        showNextQuiz();
    }

    private void jawabanSalah() {
        Toast.makeText(this, "Jawaban Anda salah!", Toast.LENGTH_SHORT).show();
        quizCount++;
        showNextQuiz();
    }

    private void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Skor");
        builder.setMessage(rightAnswerCount + " Poin");
        builder.setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
}