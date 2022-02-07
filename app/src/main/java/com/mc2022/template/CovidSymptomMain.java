package com.mc2022.template;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CovidSymptomMain extends AppCompatActivity {

    private Button trueButton,
                   falseButton,
                   startButton,
                   nextButton,
                   submitButton,
                   clearButton;

    private TextView txtName,
                     txtQuestion,
                     textView,
                     textView2,
                     textView3,
                     displayQuestions;

    private EditText editName;

    private int Dscore = 0,
                NDscore = 0,
                currNumber = 0,
                storeQue,
                i = 0;

    private String save = " ",
                   quesave = " ";

    private ArrayList<String> obj = new ArrayList<String>();
    private ArrayList<String> obj2 = new ArrayList<String>();

    private long lastClikedTime = 0;

    private SharedPreferences sp;

    private static final String SHARED_PREF_NAME = "MyUserPrefs",
                                KEY_NAME = "userName",
                                KEY_DETECT = "detected",
                                KEY_NDETECT = "notDetected",
                                KEY_DISPRES = "dispResult",
                                KEY_CURNO = "current_number",
                                KEY = "CovidSymptomMain";

    private SymptomData[] showQuestion = new SymptomData[]{
            new SymptomData(R.string.question_1),
            new SymptomData(R.string.question_2),
            new SymptomData(R.string.question_3),
            new SymptomData(R.string.question_4),
            new SymptomData(R.string.question_5)
    };

    private void questionChange(){
        storeQue = showQuestion[currNumber].getQue();
        displayQuestions.setText(storeQue);
        nextButton.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_symptom);
        Log.i(KEY, "For CovidSymptomMain activity onCreate is called");
        Toast.makeText(this, "\n State of CovidSymptomMain onCreate is called \n ", Toast.LENGTH_SHORT).show();

        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        clearButton = findViewById(R.id.clearButton);
        startButton = findViewById(R.id.startButton);
        submitButton = findViewById(R.id.submitButton);

        txtName = findViewById(R.id.txtName);
        displayQuestions = findViewById(R.id.txtQuestion);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        editName = findViewById(R.id.editName);

        sp = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        trueButton.setBackgroundColor(Color.GREEN);
        falseButton.setBackgroundColor(Color.RED);

        //start button

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editName.getText().toString().isEmpty()) {
                    Toast.makeText(CovidSymptomMain.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    questionChange();
                    trueButton.setVisibility(View.VISIBLE);
                    falseButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);
                    txtName.setVisibility(View.INVISIBLE);
                    startButton.setVisibility(View.GONE);
                    editName.setVisibility(View.INVISIBLE);
                }
            }
        });

        // true button

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextButton.setEnabled(true);
                trueButton.setEnabled(false);
                falseButton.setEnabled(false);
                Toast.makeText(CovidSymptomMain.this, "Reply is frozen", Toast.LENGTH_SHORT).show();
                Dscore = Dscore + 1;
                textView.setText("" + Dscore);

                String a = " ";
                a = (String) displayQuestions.getText();
                textView2.setText(a + "  ");
                quesave = textView2.getText().toString();

                obj.add(" \t true ");
                textView2.append(obj.get(i).toString() + " \n ");
                textView2.append(save);
                save = textView2.getText().toString();

                SharedPreferences.Editor edit = sp.edit();
                edit.putString(KEY_DISPRES, " \n " + save + " \n ");
                edit.apply();
            }
        });

        // false button

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextButton.setEnabled(true);
                falseButton.setEnabled(false);
                trueButton.setEnabled(false);
                Toast.makeText(CovidSymptomMain.this, "Reply is frozen", Toast.LENGTH_SHORT).show();
                NDscore = NDscore + 1;
                textView3.setText("" + NDscore);

                String a = " ";
                a = (String) displayQuestions.getText();
                textView2.setText(a + "   ");
                quesave = textView2.getText().toString();

                obj2.add(" \t false ");
                textView2.append(obj2.get(i).toString() + " \n ");
                textView2.append(save);
                save = textView2.getText().toString();

                SharedPreferences.Editor edit = sp.edit();
                edit.putString(KEY_DISPRES, " \n " + save + " \n ");
                edit.apply();
            }
        });

        // next button

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trueButton.setEnabled(true);
                falseButton.setEnabled(true);
                if (currNumber < showQuestion.length + 1) {
                    currNumber = (currNumber + 1);
                    if (currNumber == showQuestion.length) {
                        submitButton.setVisibility(View.VISIBLE);
                        clearButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                        trueButton.setVisibility(View.INVISIBLE);
                        falseButton.setVisibility(View.INVISIBLE);
                        displayQuestions.setText("YOU ARE DONE WITH YOUR TESTING");
                    } else {
                        questionChange();
                    }
                }
            }
        });

        // submit button

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString();
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(KEY_NAME, name);
                edit.putInt(KEY_DETECT, Dscore);
                edit.putInt(KEY_NDETECT, NDscore);
                edit.apply();

                Intent i = new Intent(CovidSymptomMain.this, CovidDetectionSecond.class);
                startActivity(i);
            }
        });

        // clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currNumber == showQuestion.length) {
                    Toast.makeText(CovidSymptomMain.this, "your data has been cleared", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences.Editor edit = sp.edit();
                edit.putInt(KEY_DETECT, 0);
                edit.putInt(KEY_NDETECT, 0);
                edit.putString(KEY_NAME, "");
                edit.clear();
                edit.apply();
                finish();

                Intent i = new Intent(getBaseContext(), CovidSymptomMain.class);
                startActivity(i);
            }
        });

        if (savedInstanceState != null) {
            currNumber = savedInstanceState.getInt(KEY_CURNO, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(KEY, "State of CovidSymptomMain activity onStart() changed from Create to Start or Restart to Start");
        Toast.makeText(this, "\n State of CovidSymptomMain onStart() \t changed from \t Create to Start \n or Restart to Start \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(KEY, "State of CovidSymptomMain activity onResume() changed from Start to Resume");
        Toast.makeText(this, "\n State of CovidSymptomMain onResume() \t changed from \t Start to Resume \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(KEY, "State of CovidSymptomMain activity onPause() changed from Resume to Pause ");
        Toast.makeText(this, "\n State of CovidSymptomMain onPause() \t changed from \t Resume to Pause \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(KEY, "State of CovidSymptomMain activity onStop() changed from Pause to Stop ");
        Toast.makeText(this, "\n State of CovidSymptomMain onStop() \t changed from \t Pause to Stop \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(KEY, "State of CovidSymptomMain activity onRestart() changed from Stop to Start ");
        Toast.makeText(this, "\n State of of CovidSymptomMain onRestart() \t changed from \t Stop to Start \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(KEY, "State of CovidSymptomMain activity onDestroy() changed from Stop to Destroy");
        Toast.makeText(this, "\n State of of CovidSymptomMain onDestroy() \t changed from \t Stop to Destroy \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(KEY, "onSaveInstanceSate");
        Toast.makeText(this, "SavedInstanceState", Toast.LENGTH_SHORT).show();
        savedInstanceState.putInt(KEY_CURNO, currNumber);
    }
}