package com.example.activity_lifecycle;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_request = 1;
    public static final String EXTRA_MESSAGE
            = "com.example.android.Activity_Lifecycle.extra.MESSAGE";

    private EditText send_message;
    private TextView replyHeadText;
    private TextView replyTextView;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(replyHeadText.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("reply_text",replyTextView.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        send_message = findViewById(R.id.editText_main);
        replyHeadText = findViewById(R.id.text_header_reply);
        replyTextView = findViewById(R.id.text_message_reply);

        if(savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");

            if(isVisible){
                replyHeadText.setVisibility(View.VISIBLE);
                replyTextView.setText(savedInstanceState.getString("reply_text"));
                replyTextView.setVisibility(View.VISIBLE);
        }

        }
    }

    public  void  launchSecondActivity()
    {
       Log.d(LOG_TAG,"Button_clicked");
        Intent intent =new Intent(this,SecondActivity.class);
        String message = send_message.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent,TEXT_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_request){

            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                replyHeadText.setVisibility(View.VISIBLE);
                replyTextView.setText(reply);
                replyTextView.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
