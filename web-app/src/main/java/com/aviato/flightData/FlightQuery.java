package com.aviato.flightData;

import java.io.DataOutputStream;
import java.io.DataInputStream;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


public class FlightQuery {
    public static void main(String[] args) {
        URL url;
        try {
            String currentLocation = "BOS";
            String destinationLocation = "ATL";
            String date1 = "2016-10-01";
            String date2 = "2016-10-02";
            // curl init and url
            url = new URL("https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyCl9j-z6n8xFBxlZ9e-mcGwXcKoZLzc9qw");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            // curl: post, set the content type of your request to application.json
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("accept", "application/json");
            
            // do output and do input
            con.setDoOutput(true);
            con.setDoInput(true);
            
            // send request
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(createJSONRequest(currentLocation, destinationLocation, date1, date2));
            out.flush();
            out.close();
            
            // waiting for reply from server
            int code = con.getResponseCode();
            System.out.println("Response (Code):" + code);
            System.out.println("Response (Message):" + con.getResponseMessage());
            
            // read response
            DataInputStream input = new DataInputStream(con.getInputStream());
            // Supposedly reads the contents of the InputStream as String
            String response = input.readUTF();
            
            JsonObject queryResult = new JsonObject();
            // getAsJsonObject takes a String member and returns appropriate JSON
            queryResult = queryResult.getAsJsonObject(response);  
        } 
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
        
    // Args -> JSON.String
    public static String createJSONRequest(String curr, String dest, String date,
            String date2) {
        JsonObject output = new JsonObject();

        JsonObject request = new JsonObject();

        JsonObject passengers = new JsonObject();
        JsonArray slice = new JsonArray();
        JsonObject solutions = new JsonObject();
        JsonObject refundable = new JsonObject();

        JsonObject departure = new JsonObject();
        JsonObject arrival = new JsonObject();
        JsonPrimitive current = new JsonPrimitive("curr");
        JsonPrimitive destination = new JsonPrimitive("dest");
        JsonPrimitive departDate = new JsonPrimitive("date1");
        JsonPrimitive arriveDate = new JsonPrimitive("date2");

        departure.add("origin", current);
        departure.add("destination", destination);
        departure.add("date", departDate);

        arrival.add("origin", destination);
        arrival.add("destination", current);
        arrival.add("date", arriveDate);

        passengers.add("adultCount", new JsonPrimitive(2));
        passengers.add("infantInLapCount", new JsonPrimitive(0));
        passengers.add("infantInSeatCount", new JsonPrimitive(0));
        passengers.add("childCount", new JsonPrimitive(0));
        passengers.add("seniorCount", new JsonPrimitive(0));

        slice.add(departure);
        slice.add(arrival);

        // give a total of the five cheapest options
        solutions.add("solutions", new JsonPrimitive(5));
        
        // request willlook for trips that are both refundable and not
        refundable.add("refundable", new JsonPrimitive(false));
        
        request.add("passengers", passengers);
        request.add("slice", slice);

        output.add("request", request);
        
        return output.toString();
    }
}
