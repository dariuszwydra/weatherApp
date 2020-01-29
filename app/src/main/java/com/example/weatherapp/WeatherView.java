package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherView extends AppCompatActivity {

    //create the elements from layout file
    TextView cityView,
             countryView,
             mainView,
             descriptionView,
             temperatureView,
             pressureView,
             humidityView,
             windspeedView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_view);

        //bind the elements from the layout file
        cityView = findViewById(R.id.cityView);
        countryView = findViewById(R.id.countryView);
        mainView = findViewById(R.id.mainView);
        descriptionView = findViewById(R.id.descriptionView);
        temperatureView = findViewById(R.id.temperatureView);
        pressureView = findViewById(R.id.pressureView);
        humidityView = findViewById(R.id.humidityView);
        windspeedView = findViewById(R.id.windspeedView);
        imageView = findViewById(R.id.imageView);

        //get the weather info from the Intent and store it into variables
        String main = getIntent().getStringExtra("main");
        String descriptionToFormat = getIntent().getStringExtra("description");
        String description = descriptionToFormat.substring(0, 1).toUpperCase() + descriptionToFormat.substring(1).toLowerCase();
        String temperature = getIntent().getStringExtra("temperature");
        String pressure = getIntent().getStringExtra("pressure");
        String humidity = getIntent().getStringExtra("humidity");
        String windspeed = getIntent().getStringExtra("windspeed");
        String country = getIntent().getStringExtra("country");
        String city = getIntent().getStringExtra("city");
        String id = getIntent().getStringExtra("id");

        //get the proper image (despite the image ID from API) from resources and set the ImageView
        switch (id){
            case "01d" :
            case "01n" : imageView.setImageResource(R.drawable.w01); break;
            case "02d" :
            case "02n" : imageView.setImageResource(R.drawable.w02); break;
            case "03d" :
            case "03n" : imageView.setImageResource(R.drawable.w03); break;
            case "04d" :
            case "04n" : imageView.setImageResource(R.drawable.w04); break;
            case "09d" :
            case "09n" : imageView.setImageResource(R.drawable.w09); break;
            case "10d" :
            case "10n" : imageView.setImageResource(R.drawable.w10); break;
            case "11d" :
            case "11n" : imageView.setImageResource(R.drawable.w11); break;
            case "13d" :
            case "13n" : imageView.setImageResource(R.drawable.w13); break;
            case "50d" :
            case "50n" : imageView.setImageResource(R.drawable.w50); break;
            default : break;
        }

        //insert the weather info into layout elements
        mainView.setText(main);
        descriptionView.setText(description);
        temperatureView.setText(temperature + " ℃");
        pressureView.setText(pressure + " hPa");
        humidityView.setText(humidity + " %");
        windspeedView.setText(windspeed + "m/s");
        countryView.setText(country);
        cityView.setText(city);

    }
}
