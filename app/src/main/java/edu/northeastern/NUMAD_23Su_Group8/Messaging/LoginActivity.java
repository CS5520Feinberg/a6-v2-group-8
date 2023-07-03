package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import edu.northeastern.NUMAD_23Su_Group8.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "_LoginActivity";
    private final Handler handler = new Handler();
    private MessagingRepository messagingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername = findViewById(R.id.unameInput);
        Button buttonLogin = findViewById(R.id.loginButton);
        Button buttonRegister = findViewById(R.id.registerButton);

        this.messagingRepository = MessagingRepository.getInstance();

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            if (username.isEmpty()) {
                Log.i(TAG, "Username not entered");
                Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "Login User thread started");
                Thread loginUserThread = new Thread(
                    () -> LoginActivity.this.messagingRepository.loginUser(this.handler, this, username));
                loginUserThread.start();

            }
        });

        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            if (username.isEmpty()) {
                Log.i(TAG, "Username not entered");
                Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "Register User thread started");
                Thread registerUserThread = new Thread(
                    () -> LoginActivity.this.messagingRepository.registerUser(this.handler, this, username));
                registerUserThread.start();
            }
        });
    }
}
