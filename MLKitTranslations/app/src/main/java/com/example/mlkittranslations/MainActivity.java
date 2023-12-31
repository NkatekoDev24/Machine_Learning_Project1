package com.example.mlkittranslations;
/*  Nkateko Nkuna
    2016134773
    Assignment 6
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTranslate;
    private Button btnTranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTranslate = findViewById(R.id.editTextTranslate);
        btnTranslate = findViewById(R.id.btnTranslate);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editTextTranslate.getText().toString())){
                    Toast.makeText(MainActivity.this, "No text allowed", Toast.LENGTH_SHORT).show();
                }else {
                    TranslatorOptions options = new TranslatorOptions.Builder()
                            .setTargetLanguage("es")
                            .setSourceLanguage("en")
                            .build();
                    Translator translator = Translation.getClient(options);

                    String sourceText = editTextTranslate.getText().toString();
                    ProgressBar progressBar = new ProgressBar(MainActivity.this, null, android.R.attr.progressBarStyleLarge);
                    progressBar.setVisibility(view.VISIBLE);

                    translator.downloadModelIfNeeded();
                    Task<String> result = translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}