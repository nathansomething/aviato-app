package com.aviato.pojodata;

public class BookingData {
    private String apId;
    private String dpId;
    private FlightData flightData;
    public String getApId() {
        return apId;
    }
    public void setApId(String apId) {
        this.apId = apId;
    }
    public String getDpId() {
        return dpId;
    }
    public void setDpId(String dpId) {
        this.dpId = dpId;
    }
    public FlightData getFlightData() {
        return flightData;
    }
    public void setFlightData(FlightData flightData) {
        this.flightData = flightData;
    }
    
}
