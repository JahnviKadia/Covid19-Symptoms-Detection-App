package com.mc2022.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CovidDetectionSecond extends Activity {

    private Button checkButton;

    private TextView detailText,
                     nameText,
                     detectedText,
                     notdetectedText,
                     resultText;

    private static final String SHARED_PREF_NAME = "MyUserPrefs",
            KEY_NAME = "userName",
            KEY_DETECT = "detected",
            KEY_NDETECT = "notDetected",
            KEY_DISPRES = "dispResult",
            KEY_CURNO = "current_number",
            KEY = "CovidDetectionSecond";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_detection);
        Log.i(KEY, "CovidDetectionSecond onCreate() is called");
        Toast.makeText(this, "\n State of CovidDetectionSecond onCreate called \n ", Toast.LENGTH_SHORT).show();

        checkButton = findViewById(R.id.checkbutton);
        detailText = findViewById(R.id.detailText);
        nameText = findViewById(R.id.nameText);
        detectedText = findViewById(R.id.detectedText);
        notdetectedText = findViewById(R.id.notdetectedText);
        resultText = findViewById(R.id.resultText);


        SharedPreferences sp = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, "");
        int Dscore = sp.getInt(KEY_DETECT, 0);
        int NDscore = sp.getInt(KEY_NDETECT, 0);
        String Dispres = sp.getString(KEY_DISPRES, "");

        Intent i = getIntent();

        detailText.setText("USER DETAILS ");
        detailText.setTextSize(20);
        nameText.setText("Name: " + name);
        nameText.setTextSize(18);
        detectedText.setText("Number of symptoms detected: " + Dscore);
        detectedText.setTextSize(18);
        notdetectedText.setText("Number of symptoms not detected: " + NDscore);
        notdetectedText.setTextSize(18);
        resultText.setText("Form responses of user: " + Dispres);
        resultText.setTextSize(18);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //displayTextView.setVisibility(View.INVISIBLE);
                nameText.setVisibility(View.INVISIBLE);
                notdetectedText.setVisibility(View.INVISIBLE);
                detailText.setVisibility(View.INVISIBLE);
                resultText.setVisibility(View.INVISIBLE);

                if(Dscore >= 3){
                    detectedText.setText("YOU NEED TO GET TESTED!!");
                    detectedText.setTextSize(20);
                }

                else{
                    detectedText.setText("YOU ARE SAFE!!");
                    detectedText.setTextSize(20);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(KEY, "State of CovidDetectionSecond activity onStart() changed from Create to Start or Restart to Start");
        Toast.makeText(this, "\n State of CovidDetectionSecond onStart() \t changed from \t Create to Start \n or Restart to Start \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(KEY, "State of CovidDetectionSecond activity onResume() changed from Start to Resume");
        Toast.makeText(this, "\n State of CovidDetectionSecond onResume() \t changed from \t Start to Resume \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(KEY, "State of CovidDetectionSecond activity onPause() changed from Resume to Pause ");
        Toast.makeText(this, "\n State of CovidDetectionSecond onPause() \t changed from \t Resume to Pause \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(KEY, "State of CovidDetectionSecond activity onStop() changed from Pause to Stop ");
        Toast.makeText(this, "\n State of CovidDetectionSecond onStop() \t changed from \t Pause to Stop \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(KEY, "State of CovidDetectionSecond activity onRestart() changed from Stop to Start ");
        Toast.makeText(this, "\n State of CovidDetectionSecond onRestart() \t changed from \t Stop to Start \n ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(KEY, "State of CovidDetectionSecond activity onDestroy() changed from Stop to Destroy");
        Toast.makeText(this, "\n State of CovidDetectionSecond onDestroy() \t changed from \t Stop to Destroy \n ", Toast.LENGTH_SHORT).show();
    }

}
