package com.aviato.webapp;
import com.aviato.flightData.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.JsonObject;


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
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup(Locale locale) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Navigating to Signup");
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