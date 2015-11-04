/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.web.controllers;

/**
 *
 * @author sf
 */
import com.sf.springsecurityregistration1.core.entities.UserRoles;
import com.sf.springsecurityregistration1.core.entities.Users;
import com.sf.springsecurityregistration1.core.services.IUserService;
import com.sf.springsecurityregistration1.core.services.UserExistsException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@PropertySource("classpath:encoding.properties")
public class RegistrationController {    
//    @Value("${page.encoding}")
//    private String pageEncoding = "ISO-8859-1";
//    @Value("${db.encoding}")
//    private String dbEncoding = "UTF8";    
    @Autowired
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        System.out.println("setUserService");
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/welcome**", "/logout**", "/index**"}, 
            method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        System.out.println("welcomePage");
        ModelAndView model = new ModelAndView();
//        "Доска объявлений";
        String titleStr = "&#1044;&#1086;&#1089;&#1082;&#1072;&#32;&#1086;"
                + "&#1073;&#1098;&#1103;&#1074;&#1083;&#1077;&#1085;&#1080;"
                + "&#1081;";
//        "Для доступа необходимо пройти регистрацию. "
//                + "Перейдите по ссылке \"Регистрация\""
        String messageStr = "&#1044;&#1083;&#1103;&#32;&#1076;&#1086;&#1089;"
                + "&#1090;&#1091;&#1087;&#1072;&#32;&#1085;&#1077;&#1086;"
                + "&#1073;&#1093;&#1086;&#1076;&#1080;&#1084;&#1086;&#32;"
                + "&#1087;&#1088;&#1086;&#1081;&#1090;&#1080;&#32;&#1088;"
                + "&#1077;&#1075;&#1080;&#1089;&#1090;&#1088;&#1072;&#1094;"
                + "&#1080;&#1102;&#46;&#32;&#1055;&#1077;&#1088;&#1077;&#1081;"
                + "&#1076;&#1080;&#1090;&#1077;&#32;&#1087;&#1086;&#32;&#1089;"
                + "&#1089;&#1099;&#1083;&#1082;&#1077;&#32;";
//        "Регистрация"
        String registrationRefStr = "&#1056;&#1077;&#1075;&#1080;&#1089;&#1090;"
                + "&#1088;&#1072;&#1094;&#1080;&#1103;.";
        model.addObject("title", titleStr);
        model.addObject("message", messageStr);
        model.addObject("registrationRef", registrationRefStr);
        String logoutRef = "&#1042;&#1099;&#1093;&#1086;&#1076;";
        model.addObject("logoutRef", logoutRef);        
        String userTitle = "&#1055;&#1086;&#1083;&#1100;&#1079;&#1086;&#1074;"
                + "&#1072;&#1090;&#1077;&#1083;&#1100;";
        model.addObject("userTitle", userTitle);
//        Посмотреть объявления
        String announcementsRef = "&#1055;&#1086;&#1089;&#1084;&#1086;&#1090;"
                + "&#1088;&#1077;&#1090;&#1100;&#32;&#1086;&#1073;&#1098;"
                + "&#1103;&#1074;&#1083;&#1077;&#1085;&#1080;&#1103;";
        model.addObject("announcementsRef", announcementsRef);
        model.setViewName("hello");
        return model;

    }
    
    @RequestMapping(value = {"/registration"}, 
            method = RequestMethod.GET)
    public ModelAndView registrationPage() {
        System.out.println("registrationPage");
        Users user = new Users();
        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.setViewName("registration");
        return model;

    }
    @RequestMapping(value = "/user/**/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid 
            Users accountDto, BindingResult result, WebRequest request, 
            Errors errors) {
        System.out.println("/user/registration");
        Users registered = new Users();
//        accountDto.setUsername(changeEncoding(accountDto.getUsername(),
//                pageEncoding, dbEncoding));
        if (!result.hasErrors()) {
            System.out.println("!result.hasErrors()");
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            System.out.println("registered == null");
            result.rejectValue("username", "message.regError");
        }
        if (result.hasErrors()) {
            System.out.println("result.hasErrors()");
//            return new ModelAndView("registration", "user", accountDto);
            ModelAndView model = new ModelAndView("registration");
            model.addObject("user", accountDto);
            return model;
        } else {
//            return new ModelAndView("successRegister", "user", accountDto);
            ModelAndView model = new ModelAndView("successRegister");
            model.addObject("user", accountDto);
            return model;
        }
    }

    private Users createUserAccount(Users accountDto, BindingResult result) {
        Users registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
            System.out.println("createUserAccount: " 
                    + registered.getUsername());
        } catch (UserExistsException e) {
            System.out.println("createUserAccount: UserExistsException");
            return null;
        }
        return registered;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        System.out.println("adminPage");
        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Hello World");
//        model.addObject("message", "This is protected page - Admin Page!");
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/dba**", method = RequestMethod.GET)
    public ModelAndView dbaPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page - Database Page!");
        model.setViewName("admin");

        return model;

    }
    
    @RequestMapping(value = {"/login", "/user/**/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        System.out.println("login: logout = " + logout);
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

    private String changeEncoding(String text, String inEncoding,
            String outEncoding) {

        try {
            byte[] textBytes = text.getBytes(inEncoding);
            return new String(textBytes, outEncoding);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AnnouncementDetailsController.class.getName())
                    .log(Level.SEVERE, null, ex);
            return text;
        }
    }


}
