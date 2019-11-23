import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class WeatherParse {

    public static String getWeather(String message, WeatherFormat weatherFormat) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?" + message + "&appid=                   ");
        Gson gson = new Gson();


        ForecastFormat forecastFormat = gson.fromJson(url.getContent().toString(), ForecastFormat.class);
        return  forecastFormat.getName();

/*
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {

            result += in.nextLine();

            JSONObject jsonObject = new JSONObject(result);

            weatherFormat.setName(jsonObject.getString("name"));

            JSONObject main = jsonObject.getJSONObject("main");
            weatherFormat.setTemp(main.getDouble("temp"));
            weatherFormat.setHumidity(main.getDouble("humidity"));

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                weatherFormat.setIcon((String) jsonObject1.get("icon"));
                weatherFormat.setMain((String) jsonObject1.get("main"));
            }
        }
        return "City: " + weatherFormat.getName() + "\n" +
                "Temperature: " + weatherFormat.getTemp() + " C " + "\n" +
                "Humidity: " + weatherFormat.getHumidity() + " % " + "\n" +
                "Main: " + weatherFormat.getMain() + "\n" +
                "http://openweathermap.org/img/wn/" + weatherFormat.getIcon() + ".png";

 */
    }


    public static String getTomorrowWeather(String message, WeatherFormat weatherFormat) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?" + message + "&cnt=10&appid=            ");
        Scanner in = new Scanner((InputStream) url.getContent());


        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject0 = jsonObject.getJSONObject("city");
            weatherFormat.setName(jsonObject0.getString("name"));

            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("temp");
                weatherFormat.setTemp(jsonObject2.getDouble("day"));
                weatherFormat.setHumidity(jsonObject1.getDouble("humidity"));
                JSONArray jsonArray1 = jsonObject1.getJSONArray("weather");
                for (int j = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObject3 = jsonArray1.getJSONObject(i);
                    weatherFormat.setIcon((String) jsonObject3.get("icon"));
                    weatherFormat.setMain((String) jsonObject3.get("main"));

                }

            }

        }
        return "City: " + weatherFormat.getName() + "\n" +
                "Temperature: " + weatherFormat.getTemp() + " C " + "\n" +
                "Humidity: " + weatherFormat.getHumidity() + " % " + "\n" +
                "Main: " + weatherFormat.getMain() + "\n" +
                "http://openweathermap.org/img/wn/" + weatherFormat.getIcon() + ".png";


    }


}








