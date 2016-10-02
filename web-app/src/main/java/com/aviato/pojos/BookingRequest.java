package com.aviato.pojos;
import lombok.Data;

@Data
public class BookingRequest {

	private DesperatePerson desperatePerson;
	private FlightData flightData;
	private AttractivePerson attractivePerson;
}
