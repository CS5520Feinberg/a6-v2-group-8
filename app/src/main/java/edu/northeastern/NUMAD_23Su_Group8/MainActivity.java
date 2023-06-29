package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagingActivity;
import edu.northeastern.NUMAD_23Su_Group8.Weather.WebServiceActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button webServiceBtn = findViewById(R.id.btn_web_service);
    Button aboutBtn = findViewById(R.id.about);
    Button messagingBtn = findViewById(R.id.btn_messaging);

    webServiceBtn.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, WebServiceActivity.class);
      MainActivity.this.startActivity(intent);
    });

    aboutBtn.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
      MainActivity.this.startActivity(intent);
    });

    messagingBtn.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, MessagingActivity.class);
      MainActivity.this.startActivity(intent);
    });
  }
}