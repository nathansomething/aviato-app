package com.aviato.webapp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aviato.userlogin.FacebookJson;
import com.aviato.userlogin.FacebookUtils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.scope.ScopeBuilder;

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
	
	@RequestMapping(value = "/fblogin", method = RequestMethod.GET)
	public ModelAndView fbTest(Locale locale) {        
        ModelAndView mv = new ModelAndView();
        
	    System.out.println("Verifying User. Sending To Log in");
	    
	    return new ModelAndView("redirect:" +FacebookUtils.getScopedLoginUrl(verifiedLoginUrl));
	    
	}
	
	@RequestMapping(value = "/fbloggedin", method = RequestMethod.GET)
	public @ResponseBody FacebookJson fbLoggedIn(
	        @RequestParam("code") String code) {
	    FacebookClient client =  FacebookUtils.getClient(code, verifiedLoginUrl);
	    
	    JsonObject picture = 
                client.fetchObject("me/picture", 
                    JsonObject.class, Parameter.with("redirect","false"));
	    
        System.out.println(picture);
        
        picture.getString("url");
	    
	    return picture;
	}
}