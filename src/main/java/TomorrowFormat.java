import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
//-----------------------------------CityTomorrow.java-----------------------------------

class City {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private CoordTomorrow coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

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

    public CoordTomorrow getCoord() {
        return coord;
    }

    public void setCoord(CoordTomorrow coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
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
//-----------------------------------CloudsTomorrow.java-----------------------------------


class CloudsTomorrow {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
//-----------------------------------CoordTomorrow.java-----------------------------------

class CoordTomorrow {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
//-----------------------------------TomorrowFormat.java-----------------------------------


public class TomorrowFormat {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private List<ListTomorrow> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<ListTomorrow> getList() {
        return list;
    }

    public void setList(List<ListTomorrow> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public String toString() {
        return "Tomorrow: " + "\n" +
                list.get(0).getWeather().get(0).getMain() + "\n" +
                "temp= " + list.get(0).getMain().getTemp() + " °C\n" +
                "tempMin= " + list.get(0).getMain().getTempMin() + " °C\n" +
                "tempMax= " + list.get(0).getMain().getTempMax() + " °C\n" +
                "pressure= " + list.get(0).getMain().getPressure() + "  mm\n" +
                "humidity= " + list.get(0).getMain().getHumidity() + " %\n" +
                "wind= " + list.get(0).getWind().getSpeed() + " m/s\n";
    }


}
//-----------------------------------ListTomorrow.java-----------------------------------


class ListTomorrow {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private MainTomorrow main;
    @SerializedName("weather")
    @Expose
    private List<WeatherTomorrow> weather;
    @SerializedName("clouds")
    @Expose
    private CloudsTomorrow clouds;
    @SerializedName("wind")
    @Expose
    private WindTomorrow wind;
    @SerializedName("sys")
    @Expose
    private SysTomorrow sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public MainTomorrow getMain() {
        return main;
    }

    public void setMain(MainTomorrow main) {
        this.main = main;
    }

    public List<WeatherTomorrow> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherTomorrow> weather) {
        this.weather = weather;
    }

    public CloudsTomorrow getClouds() {
        return clouds;
    }

    public void setClouds(CloudsTomorrow clouds) {
        this.clouds = clouds;
    }

    public WindTomorrow getWind() {
        return wind;
    }

    public void setWind(WindTomorrow wind) {
        this.wind = wind;
    }

    public SysTomorrow getSys() {
        return sys;
    }

    public void setSys(SysTomorrow sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}
//-----------------------------------MainTomorrow.java-----------------------------------


class MainTomorrow {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("sea_level")
    @Expose
    private Integer seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Integer grndLevel;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_kf")
    @Expose
    private Double tempKf;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Integer seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Integer getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Integer grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempKf() {
        return tempKf;
    }

    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

}
//-----------------------------------SysTomorrow.java-----------------------------------


class SysTomorrow {

    @SerializedName("pod")
    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
//-----------------------------------WeatherTomorrow.java-----------------------------------


class WeatherTomorrow {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
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
//-----------------------------------WindTomorrow.java-----------------------------------


class WindTomorrow {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

}