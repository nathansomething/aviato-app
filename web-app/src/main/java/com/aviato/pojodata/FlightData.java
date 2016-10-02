package com.aviato.pojodata;

public class FlightData {
    private String source;
    public String getSource() {
        return source;
    }
    
    //setters and getters
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    private String destination;
    private String price;    
}
