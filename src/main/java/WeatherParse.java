import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class WeatherParse {

    public static String getWeather(String message, WeatherFormat weatherFormat) throws IOException {
        // units=metric!
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?" + message + "&appid=TOKEN&units=metric"); // todo token from properties
        Gson gson = new Gson();

        /*
            url.getContent().toString() it is "sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@305fd85d",
            NOT JSON!
         */
        InputStream inputStream = url.openConnection().getInputStream();
        ForecastFormat forecastFormat = gson.fromJson(new InputStreamReader(inputStream), ForecastFormat.class);
        return forecastFormat.getMain().toString();
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








