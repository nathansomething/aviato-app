package com.aviato.webapp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
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
	    
	    
	    
        mv.setViewName("fbTest");
	    System.out.println("Navigating to FBTest");
	    mv.setViewName("fbTest");
	    return mv;
	}
}