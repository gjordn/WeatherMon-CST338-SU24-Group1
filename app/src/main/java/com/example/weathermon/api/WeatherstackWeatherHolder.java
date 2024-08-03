package com.example.weathermon.api;

import com.example.weathermon.database.entities.Location;

import java.time.LocalDateTime;

public class WeatherstackWeatherHolder {
    public Request request;
    public Location location;
    public current current;

    public com.example.weathermon.database.entities.Location WeatherstackToLocationDatabaseEntitiy(){
        com.example.weathermon.database.entities.Location dbLocation;

        int windspeed;
        int humidity;
        int temperature;
        boolean realLocation;
        LocalDateTime localTime;
        boolean dayOrNight;


        dbLocation = new com.example.weathermon.database.entities.Location("tenp", "temp",false,false);
        return dbLocation;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public current getCurrentWeather() {
        return current;
    }

    public void setCurrentWeather(current current) {
        this.current = current;
    }

    public class Request {
        public String type;
        public String query;
        public String language;
        public String unit;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public class Location{
        public String name;
        public String country;
        public String region;
        public String lat;
        public String lon;
        public String timezone_id;
        public String localtime;
        public String localtime_epoch;
        public String utc_offset;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTimezone_id() {
            return timezone_id;
        }

        public void setTimezone_id(String timezone_id) {
            this.timezone_id = timezone_id;
        }

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }

        public String getLocaltime_epoch() {
            return localtime_epoch;
        }

        public void setLocaltime_epoch(String localtime_epoch) {
            this.localtime_epoch = localtime_epoch;
        }

        public String getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(String utc_offset) {
            this.utc_offset = utc_offset;
        }
    }

    public class current {
        public String observation_time;
        public String temperature;
        public String weather_code;
        public String[] weather_icons;
        public String[] weather_descriptions;
        public String wind_speed;
        public String wind_degree;
        public String wind_dir;
        public String pressure;
        public String precip;
        public String humidity;
        public String cloudcover;
        public String feelslike;
        public String uv_index;
        public String visibility;
        public String is_day;

        public String getObservation_time() {
            return observation_time;
        }

        public void setObservation_time(String observation_time) {
            this.observation_time = observation_time;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String[] getWeather_icons() {
            return weather_icons;
        }

        public void setWeather_icons(String[] weather_icons) {
            this.weather_icons = weather_icons;
        }

        public String[] getWeather_descriptions() {
            return weather_descriptions;
        }

        public void setWeather_descriptions(String[] weather_descriptions) {
            this.weather_descriptions = weather_descriptions;
        }

        public String getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(String wind_speed) {
            this.wind_speed = wind_speed;
        }

        public String getWind_degree() {
            return wind_degree;
        }

        public void setWind_degree(String wind_degree) {
            this.wind_degree = wind_degree;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getCloudcover() {
            return cloudcover;
        }

        public void setCloudcover(String cloudcover) {
            this.cloudcover = cloudcover;
        }

        public String getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(String feelslike) {
            this.feelslike = feelslike;
        }

        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }
    }

}
