package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText cityNameEditText;
    Button checkTheWeatherButton;

    public void search(View view){

        // bind the elements from layout
        cityNameEditText = findViewById(R.id.cityNameEditText);
        checkTheWeatherButton = findViewById(R.id.checkTheWeatherButton);

        //get the city name from the EditText
        String cityNameString = cityNameEditText.getText().toString();

        //create the container for the weather data
        String content;

        //create new API Utility class
        APIUtils objAPIUtils = new APIUtils();

        try {

            //fetch the weather data as a JSONObject
            content = objAPIUtils.execute("https://api.openweathermap.org/data/2.5/weather?q=" + cityNameString + "&units=metric&appid=5fcc3af20758ebbcda5faac1881f4b7f").get();
            JSONObject jsonObject = new JSONObject(content);

            //get the data from the JSONObject and insert them to the variables
            String weatherData = jsonObject.getString("weather");
            String mainData    = jsonObject.getString("main");
            String windData    = jsonObject.getString("wind");
            String countryData = jsonObject.getString("sys");

            //create JSON type objects from the variables
            JSONArray weatherArray   = new JSONArray(weatherData);
            JSONObject mainObject    = new JSONObject(mainData);
            JSONObject windObject    = new JSONObject(windData);
            JSONObject countryObject = new JSONObject(countryData);

            //create the data containers for the needed informations
            String main        = "";
            String description = "";
            String temperature = "";
            String pressure    = "";
            String humidity    = "";
            String windspeed   = "";
            String country     = "";
            String id          = "";

            //loop through the array and store the main weather description into variables
            for(int i=0;i<weatherArray.length();i++){
                JSONObject weatherPart = weatherArray.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");
                id = weatherPart.getString("icon");
            }

            //get the rest of the information
            temperature = mainObject.getString("temp");
            pressure = mainObject.getString("pressure");
            humidity = mainObject.getString("humidity");
            windspeed = windObject.getString("speed");
            country = countryObject.getString("country");

            //get the city name
            String city = cityNameString.substring(0, 1).toUpperCase() + cityNameString.substring(1).toLowerCase();

            //create a new Intent, put the variables in it and start the WeatherView activity
            Intent openWeatherView = new Intent(getApplicationContext(),WeatherView.class);
            openWeatherView.putExtra("main",main);
            openWeatherView.putExtra("description",description);
            openWeatherView.putExtra("temperature",temperature);
            openWeatherView.putExtra("pressure",pressure);
            openWeatherView.putExtra("humidity",humidity);
            openWeatherView.putExtra("windspeed",windspeed);
            openWeatherView.putExtra("country",country);
            openWeatherView.putExtra("city",city);
            openWeatherView.putExtra("id",id);
            startActivity(openWeatherView);

            //catch the Exception, if API couldn't get the city
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"City not found!",Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
