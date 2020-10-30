package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.twoactivities.constants.Constants;
import com.example.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(LOG_TAG, "onCreate MainActivity");

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String message = binding.etMessage.getText().toString().trim();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(intent, TEXT_REQUEST);
            }
        });

        if(savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean(Constants.REPLY_VISIBLE);
            if(isVisible) {
                binding.tvReply.setVisibility(View.VISIBLE);
                binding.tvReplyMessage.setText(savedInstanceState.getString(Constants.REPLY_TEXT));
                binding.tvReplyMessage.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                binding.tvReply.setVisibility(View.VISIBLE);
                binding.tvReplyMessage.setText(reply);
                binding.tvReplyMessage.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy MainActivity");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if(binding.tvReply.getVisibility() == View.VISIBLE) {
            outState.putBoolean(Constants.REPLY_VISIBLE, true);
            outState.putString(Constants.REPLY_TEXT, binding.tvReplyMessage.getText().toString());
        }
    }
}
