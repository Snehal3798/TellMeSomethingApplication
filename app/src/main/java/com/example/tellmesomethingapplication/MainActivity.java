package com.example.tellmesomethingapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView voiceInput,speakBtn;
     final int REQ_SPEECH_CODE=100;
     ToggleButton toggleButton;
     ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voiceInput=findViewById(R.id.voiceInput);
        speakBtn=findViewById(R.id.btnSpeak);
        toggleButton=findViewById(R.id.toggleButton);
        constraintLayout=findViewById(R.id.constrain);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    speakBtn.setBackground(getResources().getDrawable(android.R.drawable.alert_dark_frame));
                    Toast.makeText(MainActivity.this, "ONN", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bolhalkehalke();
            }
        });
    }

    private void bolhalkehalke() {
        Intent intent_mic = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent_mic.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent_mic.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent_mic.putExtra(RecognizerIntent.EXTRA_PROMPT, "Kuch tum kahoo..");
        try {
            startActivityForResult(intent_mic, REQ_SPEECH_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Mic Issue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_SPEECH_CODE:{
                if(resultCode == RESULT_OK && data!=null){
                    ArrayList<String> stringArrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(stringArrayList.get(0));

                }
            }
            break;
        }
    }
}
