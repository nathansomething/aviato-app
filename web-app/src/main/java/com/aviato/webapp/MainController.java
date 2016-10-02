package com.aviato.webapp;
import com.aviato.flightData.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;


import com.aviato.pojos.FlightData;


import com.aviato.userlogin.FacebookPerson;
import com.aviato.userlogin.FacebookUtils;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

@Controller
public class MainController {
    
    private String verifiedLoginUrl = "http://localhost:4997/fbloggedin";
    

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
	public void home(@ModelAttribute FlightData flightData) {
		System.out.println("IN POST REQUEST");
		// SAVE THIS IN THE DATABASE
		JsonObject flightDataJson = new FlightQuery(
				flightData.getStartingLocation(),
				flightData.getDestination(),
				flightData.getTravelDate().toString()).getJson();
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
	
	@RequestMapping(value = "/fblogin", method = RequestMethod.GET)
	public ModelAndView fbTest(Locale locale) {        
        ModelAndView mv = new ModelAndView();
        
	    System.out.println("Verifying User. Sending To Log in");
	    
	    return new ModelAndView("redirect:" +FacebookUtils.getScopedLoginUrl(verifiedLoginUrl));
	    
	}
	
	@RequestMapping(value = "/fbloggedin", method = RequestMethod.GET)
	public ModelAndView fbLoggedIn(
	        @RequestParam("code") String code) {
	    FacebookClient client =  FacebookUtils.getClient(code, verifiedLoginUrl);
	    
	    FacebookPerson fp = FacebookUtils.getRelevantDetails(client);
	    System.out.println(fp);
	    
	    return new ModelAndView("redirect:home"); // new FacebookJson(picUrl);
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
    public ModelAndView book() {
        return null;
	    
	}

	
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

