package com.dhankher.matetracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dhankher.matetracker.R;
import com.dhankher.matetracker.workers.BackgroundWorker;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private Button signUpBtn;
    private TextView userIdTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userIdTV = (TextView) findViewById(R.id.ETusername);
        signUpBtn = (Button) findViewById(R.id.BTsignup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(SignUpActivity.this);
                String userId = userIdTV.getText().toString();
                if (userId.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter a valid UserId and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "userId: " + userId);
                String type = "signup";
                backgroundWorker.execute(type, userId);
                userIdTV.setText("");
                
            }
        });
    }
}
