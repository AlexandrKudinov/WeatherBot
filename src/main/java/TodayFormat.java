import com.google.gson.annotations.SerializedName;

import java.util.List;

//-----------------------------------CloudsToday.java-----------------------------------


class CloudsToday {

    @SerializedName("all")
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}

//-----------------------------------CoordToday.java-----------------------------------


class CoordToday {

    @SerializedName("lon")
    private Float lon;
    @SerializedName("lat")

    private Float lat;

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

}
//-----------------------------------TodayFormat.java-----------------------------------

public class TodayFormat {

    @SerializedName("coord")
    private CoordToday coord;
    @SerializedName("weather")
    private List<WeatherToday> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private MainToday main;
    @SerializedName("wind")
    private WindToday wind;
    @SerializedName("clouds")
    private CloudsToday clouds;
    @SerializedName("dt")
    private Integer dt;
    @SerializedName("sys")
    private SysToday sys;
    @SerializedName("timezone")
    private Integer timezone;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private Integer cod;

    public CoordToday getCoord() {
        return coord;
    }

    public void setCoord(CoordToday coord) {
        this.coord = coord;
    }

    public List getWeather() {
        return weather;
    }


    public void setWeather(List weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainToday getMain() {
        return main;
    }

    public void setMain(MainToday main) {
        this.main = main;
    }

    public WindToday getWind() {
        return wind;
    }

    public void setWind(WindToday wind) {
        this.wind = wind;
    }

    public CloudsToday getClouds() {
        return clouds;
    }

    public void setClouds(CloudsToday clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public SysToday getSys() {
        return sys;
    }

    public void setSys(SysToday sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }


    public String toString() {
        return name +"\n"+
                "Today: " + "\n" +
                weather.get(0).getMain() + "\n" +
                "temp= " + main.getTemp() + " °C\n" +
                "tempMin= " + main.getTempMin() + " °C\n" +
                "tempMax= " + main.getTempMax() + " °C\n" +
                "pressure= " + main.getPressure() + "  mm\n" +
                "humidity= " + main.getHumidity() + " %\n" +
                "wind= " + wind.getSpeed() + " m/s\n";
    }


}

//-----------------------------------MainToday.java-----------------------------------

class MainToday {

    @SerializedName("temp")
    private Double temp;
    @SerializedName("pressure")
    private Integer pressure;
    @SerializedName("humidity")
    private Integer humidity;
    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("temp_max")
    private Double tempMax;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }


}

//-----------------------------------SysToday.java-----------------------------------

class SysToday {

    @SerializedName("type")

    private Integer type;
    @SerializedName("id")

    private Integer id;
    @SerializedName("message")

    private Double message;
    @SerializedName("country")

    private String country;
    @SerializedName("sunrise")

    private Integer sunrise;
    @SerializedName("sunset")

    private Integer sunset;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}

//-----------------------------------WeatherToday.java-----------------------------------


class WeatherToday {

    @SerializedName("id")

    private Integer id;
    @SerializedName("main")

    private String main;
    @SerializedName("description")

    private String description;
    @SerializedName("icon")

    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

//-----------------------------------WindToday.java-----------------------------------


class WindToday {

    @SerializedName("speed")

    private Double speed;
    @SerializedName("deg")

    private Double deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

}