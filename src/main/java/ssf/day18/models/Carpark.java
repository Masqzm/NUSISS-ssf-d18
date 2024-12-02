package ssf.day18.models;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Carpark {
    private int id;
    private String carpark;
    private String category;
    private String weekdayRate1;        // weekdays_rate_1
    private String weekdayRate2;        // weekdays_rate_2
    private String saturdayRate;        // saturday_rate
    private String sundayPHRate;        // sunday_publicholiday_rate
    
    public Carpark() {}
    
    public Carpark(int id, String carpark, String category, String weekdayRate1, String weekdayRate2,
            String saturdayRate, String sundayPHRate) {
        this.id = id;
        this.carpark = carpark;
        this.category = category;
        this.weekdayRate1 = weekdayRate1;
        this.weekdayRate2 = weekdayRate2;
        this.saturdayRate = saturdayRate;
        this.sundayPHRate = sundayPHRate;
    }

    public static List<Carpark> jsonToCarpark(String json) {
        if(json == null)
            return null;

        List<Carpark> cpList = new ArrayList<>();

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObj = reader.readObject();
        JsonArray recordsArr = jsonObj.getJsonObject("result").getJsonArray("records");

        for(int i = 0; i < recordsArr.size(); i++) {
            Carpark cp = new Carpark();
            JsonObject j = recordsArr.getJsonObject(i);

            cp.setId(j.getInt("_id"));
            cp.setCarpark(j.getString("carpark"));
            cp.setCategory(j.getString("category"));
            cp.setWeekdayRate1(j.getString("weekdays_rate_1"));
            cp.setWeekdayRate2(j.getString("weekdays_rate_2"));
            cp.setSaturdayRate(j.getString("saturday_rate"));
            cp.setSundayPHRate(j.getString("sunday_publicholiday_rate"));

            cpList.add(cp);
        }

        return cpList;
    }

    @Override
    public String toString() {
        return "Carpark [id=" + id + ", carpark=" + carpark + ", category=" + category + ", weekdayRate1="
                + weekdayRate1 + ", weekdayRate2=" + weekdayRate2 + ", saturdayRate=" + saturdayRate + ", sundayPHRate="
                + sundayPHRate + "]";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCarpark() {
        return carpark;
    }
    public void setCarpark(String carpark) {
        this.carpark = carpark;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getWeekdayRate1() {
        return weekdayRate1;
    }
    public void setWeekdayRate1(String weekdayRate1) {
        this.weekdayRate1 = weekdayRate1;
    }
    public String getWeekdayRate2() {
        return weekdayRate2;
    }
    public void setWeekdayRate2(String weekdayRate2) {
        this.weekdayRate2 = weekdayRate2;
    }
    public String getSaturdayRate() {
        return saturdayRate;
    }
    public void setSaturdayRate(String saturdayRate) {
        this.saturdayRate = saturdayRate;
    }
    public String getSundayPHRate() {
        return sundayPHRate;
    }
    public void setSundayPHRate(String sundayPHRate) {
        this.sundayPHRate = sundayPHRate;
    }
}
