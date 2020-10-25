package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        binding.textReceived.setText(message);

        binding.replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                String replyMessage = binding.etReply.getText().toString().trim();
                replyIntent.putExtra(EXTRA_REPLY, replyMessage);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

    }
}
