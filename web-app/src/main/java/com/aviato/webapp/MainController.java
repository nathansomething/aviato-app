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
import org.springframework.web.servlet.ModelAndView;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

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
	
	@RequestMapping(value = "/fbtest", method = RequestMethod.GET)
	public ModelAndView fbTest(Locale locale) {        
        ModelAndView mv = new ModelAndView();
	    
	    ScopeBuilder scopeBuilder = new ScopeBuilder();
	    
	    FacebookClient client = new DefaultFacebookClient(Version.LATEST);
	    
	    String loginUrl = client.getLoginDialogUrl("1305051219545441", "http://localhost:4997/fbloggedin", scopeBuilder,
	            Parameter.with("response_type", "code"));
	    
	    return new ModelAndView("redirect:" +loginUrl);
	    /*
	    System.out.println("Navigating to FBTest");
	    mv.setViewName("fbTest");
	    return mv;
	    */
	    
	}
	
	@RequestMapping(value = "/fbloggedin", method = RequestMethod.GET)
	public ModelAndView fbLoggedIn(
	        @RequestParam("code") String code) {
	    FacebookClient client = new DefaultFacebookClient(Version.LATEST);
	    
	    AccessToken token = client.obtainUserAccessToken("1305051219545441", "caecfb1894f0f6c80c5220cb86a55a7c", 
	            "http://localhost:4997/fbloggedin", code);
	    
	    System.out.println("Expires: " +token.getExpires().toString());
	    System.out.println("Actual Token: " +token.getAccessToken());
	    System.out.println("Token Type: " +token.getTokenType());
	    
	    return null;
	}
}