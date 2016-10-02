package com.aviato.webapp;
import com.aviato.flightData.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;

<<<<<<< HEAD

import com.aviato.pojos.FlightData;
=======
>>>>>>> e1a7402ff2f798c87471d9ee7064747135e3d127

@Controller
public class MainController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Navigating to root");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		mv.addObject("serverTime", formattedDate.toString());
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping(value = "/book_session", method = RequestMethod.GET)
	public ModelAndView book_session(Locale locale) {
		ModelAndView mv = new ModelAndView();
	    System.out.println("Navigating to Book Session");
	    mv.addObject("flightData", new FlightData());
	    return mv;
	}

	@RequestMapping(value = "/get_flight_info", method = RequestMethod.POST)
	public ModelAndView home(@ModelAttribute FlightData flightData) {
		System.out.println("IN POST REQUEST");
		System.out.println(flightData.getStartingLocation());
		System.out.println(flightData.getDestination());
		System.out.println(flightData.getTravelDate());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView signup(Locale locale) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Navigating to Register");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		mv.addObject("serverTime", formattedDate.toString());
		mv.setViewName("signup");
		return mv;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(Locale locale) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Navigating to About");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		mv.addObject("serverTime", formattedDate.toString());
		mv.setViewName("about");
		return mv;
	}
<<<<<<< HEAD
}

=======
	
	@RequestMapping(value = "/flightdata", method = RequestMethod.GET)
    public ModelAndView flightdata(Locale locale) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Navigating to Flight Data");
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate);
        mv.addObject("serverTime", formattedDate.toString());
        
        mv.setViewName("flightdata");
        JsonObject myFlight = new FlightQuery("BOS", "ATL", "2016-10-2").getJson();
        mv.addObject("Flight Data", myFlight);
        
        return mv;
    }
}
>>>>>>> e1a7402ff2f798c87471d9ee7064747135e3d127
