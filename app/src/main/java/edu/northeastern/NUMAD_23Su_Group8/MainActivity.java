package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.northeastern.NUMAD_23Su_Group8.R;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button aboutMeButton = findViewById(R.id.btn_web_service);
    Button tempDetailsButton = findViewById(R.id.tempDetailsButton);
    Button webServiceBtn = findViewById(R.id.btn_web_service);

	aboutMeButton.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, WebServiceActivity.class);
      MainActivity.this.startActivity(intent);
    });

    tempDetailsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), WeatherForecastDetailsActivity.class);
        startActivity(intent);
      }
    });

    webServiceBtn.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, WebServiceActivity.class);
      MainActivity.this.startActivity(intent);
    });

    Log.println(Log.INFO, "Open Weather API Key: ", BuildConfig.OPEN_WEATHER_API_KEY);
  }
}