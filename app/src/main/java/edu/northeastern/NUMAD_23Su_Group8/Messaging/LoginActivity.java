package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import edu.northeastern.NUMAD_23Su_Group8.Messaging.model.User;
import edu.northeastern.NUMAD_23Su_Group8.R;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        EditText editTextUsername = findViewById(R.id.unameInput);
        Button buttonLogin = findViewById(R.id.loginButton);
        Button buttonRegister = findViewById(R.id.registerButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").addChildEventListener(
                new ChildEventListener() {

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "onCancelled:" + error);
                        Toast.makeText(getApplicationContext()
                                , "DBError: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            if (username.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                verifyUname(username, "login");
            }
        });

        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            if (username.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                verifyUname(username, "register");
            }
        });
    }

    private void verifyUname(String uname, String taskName) {
        mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    HashMap value = (HashMap) task.getResult().getValue();
                    boolean flag = true;
                    for(Object key : value.keySet()) {
                        if(key.toString().equals(uname)) {
                            flag = false;
                        }
                    }
                    if(!flag && taskName.equals("login")) {
                        Intent intent = new Intent(LoginActivity.this, MessagingActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else if(flag && taskName.equals("login")) {
                        Toast.makeText(getApplicationContext()
                                , "User does no exist, please register. " , Toast.LENGTH_SHORT).show();
                    } else if(flag && taskName.equals("register")) {
                        addUserToDb(uname);
                    } else {
                        Toast.makeText(getApplicationContext()
                                , "User already exists! " , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void addUserToDb(String uname) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");
        User user = new User(uname);
        Task t1 = mDatabase.child("users").child(user.uname).setValue(user);

        // Read from the database by listening for a change to that item.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Toast.makeText(getApplicationContext()
                        , "Welcome to stickers. " , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MessagingActivity.class);
                LoginActivity.this.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getApplicationContext()
                        , "Failed to write value into firebase. " , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
