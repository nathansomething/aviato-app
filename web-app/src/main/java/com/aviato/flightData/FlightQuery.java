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
import com.google.gson.JsonParser;

/**
 * This class is a container for a program that sends a request to Google QPX Express API, which is
 * an API for airline pricing options. (Find out more at https://developers.google.com/qpx-express/)
 */
public class FlightQuery {
  /**
   * Flight Query. It takes input data from the Controller, initializes the URL
   * (which has the API key), type of request, pushes a JSON request as string via DataOutputStream
   * to the API, gets a JSON (String) back as response via DataInputStream, and converts the string
   * to a JsonObject.
   * @param args
   *
   * Reasoning for Program Structure
   * Like sending a curl request from Git Bash to API
   * This request in Git would simply be:
   * curl -d @request.json --header "Content-Type: application/json" https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyCl9j-z6n8xFBxlZ9e-mcGwXcKoZLzc9qw
   * Part 1.  request.json = file with proper inputs to be sent to API
   *          Similarly we create a JsonObject with the given inputs to be sent (createJSONRequest())
   * Part 2. "https:/....."
   *          Similarly we create URL url with the given URL and API key
   * Part 3. "Content-type: application/json"
   *          We also specify that we "POST" a request of "content type" "application/json"
   * Part 4.  curl -d (transforming to or from server)
   *          We send the request via formatting our json as string and then via DataOutputStream,
   *          and then receive a JSON via DataInputStream, and we then convert that json string
   *          into a JsonObject.
   */
    private JsonObject finalJson;    
    public FlightQuery(String currentLocation, String destinationLocation, String date) {
        finalJson = getJsonFromApi(currentLocation, destinationLocation, date);
    }
    
    /**
     * This method returns the JsonObject created in the constructor.
     * @return cleanJson;
     */
    public JsonObject getJson() {
        return finalJson;
    }
    

  /**
   * This method takes care of submitting a JSON (String) to the QPX API and retrieving a JSON,
   * returning a JsonObject.
   * @param currentLocation   A String, the current location.
   * @param destinationLocation   The destination location.
   * @param date The date to depart.
   * @return  JsonObject of the response from API
   */
  public JsonObject getJsonFromApi(String currentLocation, String destinationLocation, String date) {
    URL url;
    HttpURLConnection con;
    DataOutputStream out;
    DataInputStream input;
    StringBuilder resultBuf;
    JsonParser parser;
    JsonObject queryResult = new JsonObject();
    try {
      //method to take string location and find nearest airport city
        
      /**
       * Part 2. URL setup.
       */
      url = new URL("https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyCl9j-z6n8xFBxlZ9e-mcGwXcKoZLzc9qw");
      con = (HttpURLConnection) url.openConnection();

      /**
       * Part 3. More HTTP URL Connection setup.
       */
      con.setRequestMethod("POST");
      con.setRequestProperty("content-type", "application/json");
      con.setRequestProperty("accept", "application/json");

      con.setDoOutput(true);
      con.setDoInput(true);

      /**
       * Part 4a. Output.
       */
      out = new DataOutputStream(con.getOutputStream());
      out.writeBytes(createJSONRequest(currentLocation, destinationLocation, date));
      out.flush();
      out.close();

      System.out.println(createJSONRequest(currentLocation, destinationLocation, date));

      /**
       * Part 4b. Server Response.
       */
      // waiting for reply from server
      int code = con.getResponseCode(); // between 200 to 401 is okay for HTTP
      System.out.println("Response (Code):" + code);
      System.out.println("Response (Message):" + con.getResponseMessage());

      /**
       * Part 4b. Read Response. Input.
       */
      input = new DataInputStream(con.getInputStream());

      // Using StringBuilder should work
      int c;
      resultBuf = new StringBuilder();
      while ( (c = input.read()) != -1) {
        resultBuf.append((char) c);
      }
      input.close();
      String response = resultBuf.toString();

      /**
       * Part 4c. Create JsonObject.
       */
      parser = new JsonParser();
      queryResult = parser.parse(response).getAsJsonObject();

      System.out.println(queryResult);
      return queryResult;
    }
    catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return queryResult;
  }

  /**
   * Part 1. Creates a JSonObject given the parameters and returns the object as a String.
   * @param curr  current location
   * @param dest  destination
   * @param date  date of departure to destination
   * @return a String representation of the Json Object with the given parameters
   */
  public String createJSONRequest(String curr, String dest, String date) {
    JsonObject output = new JsonObject();

    JsonObject request = new JsonObject();

    JsonObject passengers = new JsonObject();
    JsonArray slice = new JsonArray();

    JsonObject departure = new JsonObject();
    
    JsonPrimitive current = new JsonPrimitive(curr);
    JsonPrimitive destination = new JsonPrimitive(dest);
    JsonPrimitive departDate = new JsonPrimitive(date);

    departure.add("origin", current);
    departure.add("destination", destination);
    departure.add("date", departDate);

    passengers.add("adultCount", new JsonPrimitive(2));
    passengers.add("infantInLapCount", new JsonPrimitive(0));
    passengers.add("infantInSeatCount", new JsonPrimitive(0));
    passengers.add("childCount", new JsonPrimitive(0));
    passengers.add("seniorCount", new JsonPrimitive(0));

    slice.add(departure); 

    request.add("slice", slice);
    request.add("passengers", passengers);
    // give a total of the five cheapest options
    request.add("solutions", new JsonPrimitive(5));
    // request will look for trips that are both refundable and not
    request.add("refundable", new JsonPrimitive(false));

    output.add("request", request);

    return output.toString();
  }  
}
