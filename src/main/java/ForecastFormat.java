//-----------------------------------weather.Clouds.java-----------------------------------


import com.google.gson.annotations.SerializedName;

import java.util.List;



     class Clouds {

        @SerializedName("all")
        private Integer all;

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }

    }
//-----------------------------------weather.Coord.java-----------------------------------


        class Coord {

        @SerializedName("lon")
        private Integer lon;
        @SerializedName("lat")

        private Integer lat;

        public Integer getLon() {
            return lon;
        }

        public void setLon(Integer lon) {
            this.lon = lon;
        }

        public Integer getLat() {
            return lat;
        }

        public void setLat(Integer lat) {
            this.lat = lat;
        }

    }
//-----------------------------------weather.ForecastFormat.java-----------------------------------



    public class ForecastFormat {

        @SerializedName("coord")
        private Coord coord;
        @SerializedName("weather")
        private List weather = null;
        @SerializedName("base")
        private String base;
        @SerializedName("main")
        private Main main;
        @SerializedName("wind")                               // <---------------------------------------
        private Wind wind;
        @SerializedName("clouds")
        private Clouds clouds;
        @SerializedName("dt")
        private Integer dt;
        @SerializedName("sys")
        private Sys sys;
        @SerializedName("timezone")
        private Integer timezone;
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("cod")
        private Integer cod;

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
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

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Integer getDt() {
            return dt;
        }

        public void setDt(Integer dt) {
            this.dt = dt;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
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

    }
//-----------------------------------weather.MAINCLASS.java-----------------------------------


     class Main {

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
        }                       // <---------------------------------------

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
        }              // <---------------------------------------

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
//-----------------------------------weather.Sys.java-----------------------------------



     class Sys {

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
//-----------------------------------weather.Weather.java-----------------------------------


     class Weather {

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
        }                      // <---------------------------------------

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
        }                      // <---------------------------------------

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
//-----------------------------------weather.Wind.java-----------------------------------



     class Wind {

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

