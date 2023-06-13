package edu.northeastern.a6_group8;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button aboutMeButton = findViewById(R.id.btn_web_service);

    aboutMeButton.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, WebServiceActivity.class);
      MainActivity.this.startActivity(intent);
    });
  }
}