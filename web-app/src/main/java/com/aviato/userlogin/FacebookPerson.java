package com.aviato.userlogin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.restfb.json.JsonObject;

public class FacebookPerson {
    private JsonObject json;
    private String firstName;
    private String lastName;
    private String id;
    private String gender;
    private String birthday;
    private String picUrl;
    

    //creating from one json
    public FacebookPerson(String jsonInput) {
        JsonObject json = new JsonObject(jsonInput);
        this.firstName = json.getString("first_name");
        this.lastName = json.getString("last_name");
        this.id = json.getString("id");
        this.gender = json.getString("gender");
        this.birthday = json.getString("birthday");
        this.picUrl = json.getString("pic_url");
        
        this.json = json;
    }

    @Override
    public String toString() {
        return this.json.toString();
    }
    
    
    public String getPicUrl() {
        return picUrl;
    }
    

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    /***
     * Calculates age of this facebook person
     * @return
     */
    public int calculateAge() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        int diff = 18;
        try {
            Date birthdate = formatter.parse(this.birthday);
            Calendar birthCal = Calendar.getInstance();
            birthCal.setTime(birthdate);

            Calendar nowCal = Calendar.getInstance();
            
            diff = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
            if (birthCal.get(Calendar.DAY_OF_YEAR) > nowCal.get(Calendar.DAY_OF_YEAR)) {
                diff--;
            }
            
        } catch (ParseException e) {
            System.out.println("date didn't work");
        }
        
        return diff;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
}
