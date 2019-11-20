import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {


    public static String getWeather(String message, WeatherFormat weatherFormat) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "------------------");

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
                "Main: "+weatherFormat.getMain()+"\n"+
                "http://openweathermap.org/img/wn/" + weatherFormat.getIcon() + ".png";


    }
}
