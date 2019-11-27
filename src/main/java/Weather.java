import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Weather {
    private String location;

    public Weather(Location location) {
        this.location = Bot.locationToString(location);
    }

    public Weather(String location) {
        this.location = location;
    }

    public synchronized String getTodayWeather() throws IOException {
        URL urlToday = new URL("http://api.openweathermap.org/data/2.5/weather?" + location + "&units=metric&appid=" + Main.API_KEY_PROPERTY_NAME);
        Gson gsonToday = new Gson();
        InputStream inputStreamToday = urlToday.openConnection().getInputStream();
        TodayFormat todayFormat = gsonToday.fromJson(new InputStreamReader(inputStreamToday), TodayFormat.class);
        return todayFormat.toString();
    }

    public String getTomorrowWeather() throws IOException {
        URL urlTomorrow = new URL("http://api.openweathermap.org/data/2.5/forecast?" + location + "&cnt=1&units=metric&appid=" + Main.API_KEY_PROPERTY_NAME);
        Gson gsonTomorrow = new Gson();
        InputStream inputStreamTomorrow = urlTomorrow.openConnection().getInputStream();
        TomorrowFormat tomorrowFormat = gsonTomorrow.fromJson(new InputStreamReader(inputStreamTomorrow), TomorrowFormat.class);
        return tomorrowFormat.toString();
    }
}