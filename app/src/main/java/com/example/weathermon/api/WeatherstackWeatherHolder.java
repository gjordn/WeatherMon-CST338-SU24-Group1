package com.example.weathermon.api;

public class WeatherstackWeatherHolder {
    public Request request;
    public Location location;
    public CurrentWeather currentWeather;

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

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public class Request {
        public String ip;
        public String query;
        public String language;
        public String unit;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
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
        public String localTime;
        public String LocalTime_Epoch;
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

        public String getLocalTime() {
            return localTime;
        }

        public void setLocalTime(String localTime) {
            this.localTime = localTime;
        }

        public String getLocalTime_Epoch() {
            return LocalTime_Epoch;
        }

        public void setLocalTime_Epoch(String localTime_Epoch) {
            LocalTime_Epoch = localTime_Epoch;
        }

        public String getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(String utc_offset) {
            this.utc_offset = utc_offset;
        }
    }

    public class CurrentWeather {
        public String ovservation_time;
        public String temperature;
        public String weather_cone;
        public String[] weather_icons;
        public String[] weather_descriptions;
        public String wind_speed;
        public String  wind_degree;
        public String wind_dir;
        public String pressure;
        public String precipitation;
        public String humidity;
        public String cloud_cover;
        public String feels_like;
        public String uv_index;
        public String visibility;
        public String is_day;

        public String getOvservation_time() {
            return ovservation_time;
        }

        public void setOvservation_time(String ovservation_time) {
            this.ovservation_time = ovservation_time;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather_cone() {
            return weather_cone;
        }

        public void setWeather_cone(String weather_cone) {
            this.weather_cone = weather_cone;
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

        public String getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getCloud_cover() {
            return cloud_cover;
        }

        public void setCloud_cover(String cloud_cover) {
            this.cloud_cover = cloud_cover;
        }

        public String getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(String feels_like) {
            this.feels_like = feels_like;
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
