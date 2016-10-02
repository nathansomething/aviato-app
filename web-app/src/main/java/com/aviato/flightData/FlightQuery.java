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
   * Main method for Flight Query. It takes input data from the Controller, initializes the URL
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
   *          into a JsonObject
   *
   */
  public static void main(String[] args) {
    URL url;
    HttpURLConnection con;
    DataOutputStream out;
    DataInputStream input;
    StringBuilder resultBuf;
    JsonParser parser;
    JsonObject queryResult;
    try {
      /**
       * Part 2.
       */
      String currentLocation = "BOS";
      String destinationLocation = "ATL";
      String date1 = "2016-10-01";
      String date2 = "2016-10-02";

      url = new URL("https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyCl9j-z6n8xFBxlZ9e-mcGwXcKoZLzc9qw");
      con = (HttpURLConnection) url.openConnection();

      /**
       * Part 3.
       */
      // curl: post, set the content type of your request to application.json
      con.setRequestMethod("POST");
      con.setRequestProperty("content-type", "application/json");
      con.setRequestProperty("accept", "application/json");

      // do output and do input
      con.setDoOutput(true);
      con.setDoInput(true);

      /**
       * Part 4a. Output.
       */
      out = new DataOutputStream(con.getOutputStream());
      out.writeBytes(createJSONRequest(currentLocation, destinationLocation, date1, date2));
      out.flush();
      out.close();

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

      ////  Supposedly reads the contents of the InputStream as String, doesn't work.
      // String response = input.readUTF();

      // Using StringBuilder should work
      int c;
      resultBuf = new StringBuilder();
      while ( (c = input.read()) != -1) {
        resultBuf.append((char) c);
      }
      input.close();
      String response = resultBuf.toString();
      System.out.println(response);

      /**
       * Part 4c. Create JsonObject.
       */
      // Json parser parses string response and creates a JsonObject
      parser = new JsonParser();
      queryResult = (JsonObject) parser.parse(response);
      // getAsJsonObject takes a String member and returns appropriate JSON
      System.out.println(queryResult);
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

  /**
   * Part 1. Creates a JSonObject given the parameters and returns the object as a String.
   * @param curr  current location
   * @param dest  destination
   * @param date1  date of departure to destination
   * @param date2 date of arrival from destination.
   * @return a String representation of the Json Object with the given parameters
   */
  public static String createJSONRequest(String curr, String dest, String date1,
                                         String date2) {
    JsonObject output = new JsonObject();

    JsonObject request = new JsonObject();

    JsonObject passengers = new JsonObject();
    JsonArray slice = new JsonArray();
    JsonObject solutions = new JsonObject();
    JsonObject refundable = new JsonObject();

    JsonObject departure = new JsonObject();
    JsonObject arrival = new JsonObject();
    JsonPrimitive current = new JsonPrimitive(curr);
    JsonPrimitive destination = new JsonPrimitive(dest);
    JsonPrimitive departDate = new JsonPrimitive(date1);
    JsonPrimitive arriveDate = new JsonPrimitive(date2);

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

    // request will look for trips that are both refundable and not
    refundable.add("refundable", new JsonPrimitive(false));

    request.add("passengers", passengers);
    request.add("slice", slice);

    output.add("request", request);

    return output.toString();
  }
}
