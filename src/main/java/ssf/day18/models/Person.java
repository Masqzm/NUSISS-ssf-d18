package ssf.day18.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private static Integer idCounter = 0;
    private Integer id;

    // required - size btwn 5 char to 150 char
    @NotNull(message = "Name cannot be empty!")
    @NotEmpty(message = "Name cannot be empty!")
    @Size(min=5, max=150, message = "Name must be between 5 and 150 characters!")
    private String fullName;

    // required and must be email format
    @NotNull(message = "Email cannot be empty!")
    @NotEmpty(message = "Email cannot be empty!")
    @Email(message = "Must be a vaild email!")
    private String email;

    // between 111111 and 888888
    @Min(value=111111, message="Postal code must be within 111111 to 888888!") 
    @Max(value=888888, message="Postal code must be within 111111 to 888888!")
    private String postalCode;

    // 7 digits number only - use regex
    @Pattern(regexp="(8|9)[0-9]{7}", message="Phone number is in wrong format!")
    private String phoneNumber;

    public Person() {}

    public Person(int id, String fullName, String email, String postalCode, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public static Person csvToPerson(String csv) throws NumberFormatException {
        String[] values = csv.split(",");

        Person p = new Person(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);

        return p;
    }

    public String toCSV() {
        return id + "," + fullName + "," + email + "," + postalCode + "," + phoneNumber;
    }

    @Override
    public String toString() {
        JsonObject j = Json.createObjectBuilder()
                    .add("id", id)
                    .add("fullName", fullName)
                    .add("email", email)
                    .add("postalCode", postalCode)
                    .add("phoneNumber", phoneNumber)
                    .build();

        return j.toString();
    }

    public static Person jsonToPerson(String json) {

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject j = reader.readObject();

        Person p = new Person(j.getInt("id"),
                    j.getString("fullName"),
                    j.getString("email"),
                    j.getString("postalCode"),
                    j.getString("phoneNumber"));

        return p;
    }

    public static Integer getNewId() {
        return idCounter++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}