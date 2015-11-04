/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.web.controllers;

import com.sf.springsecurityregistration1.core.entities.AnnouncementSearchCriteria;
import com.sf.springsecurityregistration1.core.entities.Announcements;
import com.sf.springsecurityregistration1.core.entities.Category;
import com.sf.springsecurityregistration1.core.services.IAnnouncementService;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sf
 */
@Controller
@SessionAttributes(types = {Announcements.class })
public class AnnouncementDetailsController {
//    @Value("${pageEncoding}")
    private String pageEncoding = "ISO-8859-1";
//    @Value("${dbEncoding}")
    private String dbEncoding = "UTF8";    
    @Autowired
    private IAnnouncementService announcementService;
    
//    @ModelAttribute("headers")
//    public Collection<Category> getCategories() {
//        return this.announcementService.findAllCategories();
//    }

    /**
     * Method used to prepare our model and select the view to show / edit the details
     * of the selected announcement.
     *
     * @param announcementId the id of the announcement
     * @param model the implicit model
     * @return view name to render (customer/announcement/detail)
     */
    @RequestMapping(value = "/customer/announcement/detail/{announcementId}",  
            method=RequestMethod.GET)
    public String details(@PathVariable("announcementId") long announcementId, 
            Model model) {
        Announcements announcement = this.announcementService
                .findAnnouncement(announcementId);
        System.out.println("details " + announcement.getPublicationDate());
//        
//        ModelAndView modelAndView = new ModelAndView("customer/announcement/detail");
//        model.addAttribute(announcement);
//        modelAndView.addObject(announcement);
        Set<Category> headers = new LinkedHashSet<>();
        Category currentCategory = new Category(announcement.getHeader());
        headers.add(currentCategory);
        headers.addAll(this.announcementService.findAllCategories());
        model.addAttribute("announcements", announcement);
        model.addAttribute("headers", headers);
        return "customer/announcement/detail";
    }
    
    /**
     * Method used to prepare our model and select the view to show / edit the details
     * of the selected announcement.
     *
     * @param announcementId the id of the announcement
     * @param model the implicit model
     * @return view name to render (customer/announcement/detail)
     */
    @RequestMapping(value = "/customer/announcement/detail/*",  method=RequestMethod.POST)
//    @RequestMapping(value = "/customer/announcement/detail/edit",  method=RequestMethod.POST)
//    public ModelAndView editDetails(HttpServletRequest request, HttpSession session) {
//    @RequestMapping(value = "/customer/announcement/detail/**", method = RequestMethod.POST, params = "announcements")
    public String editDetails(SessionStatus status, @Validated 
    @ModelAttribute Announcements announcement, BindingResult errors) {
//    public ModelAndView editDetails(@ModelAttribute("announcement") //@Valid 
//            Announcements announcement, BindingResult result, WebRequest request,
//            Errors errors) {
        String newTitle = changeEncoding(announcement.getTitle(), 
                pageEncoding, dbEncoding);
        announcement.setTitle(newTitle);
        System.out.println("editDetails " + newTitle);  // ISO-8859-1
        String newHeader = changeEncoding(announcement.getHeader(), 
                pageEncoding, dbEncoding);
        announcement.setHeader(newHeader);
        String newContent = changeEncoding(announcement.getContent(), 
                pageEncoding, dbEncoding);
        announcement.setContent(newContent);
        this.announcementService.update(announcement);
        status.setComplete();
//        if (request == null) {
//            System.out.println("request == null");
//        }
//        System.out.println("editDetails " + Arrays.toString(request.getParameterValues("announcements")));
//        System.out.println("editDetails " + session.getAttribute("announcements"));
//        System.out.println("editDetails " + request.getParameter("content"));
//        for (Object object : request.getParameterMap().keySet()) {
//            System.out.println("object " + object);
//        }
//        
//        ModelAndView modelAndView = new ModelAndView("customer/announcement/search");
//        model.addAttribute(announcement);
//        modelAndView.addObject(new AnnouncementSearchCriteria());
//        Set<Category> headers = new LinkedHashSet<>();
//        Category currentCategory = new Category(announcement.getHeader());
//        headers.add(currentCategory);
//        headers.addAll(this.announcementService.findAllCategories());
//        model.addAttribute("announcement", announcement);
//        model.addAttribute("headers", headers);
//        return modelAndView;
        return "redirect:/customer/announcement/search";
    }
    
        /**
     * This method creates a new announcement.
     *
     * @param criteria the criteria used for searching
     * @return the found announcements
     *
     * @see
     * com.apress.prospringmvc.bookstore.repository.BookRepository#findBooks(BookSearchCriteria)
     */
    @RequestMapping(value = "/customer/announcement/create",
            method = {RequestMethod.GET})
    public ModelAndView create() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName(); //get logged in username
        Announcements announcement = new Announcements(null, name,
                new Timestamp((new Date()).getTime()), "", "", "");
        ModelAndView model = new ModelAndView("/customer/announcement/create");
        model.addObject("announcements", announcement);
        Set<Category> headers = new HashSet<>();
        headers.addAll(this.announcementService.findAllCategories());
        model.addObject("categories", headers);
        System.out.println("/customer/announcement/create "
                + announcement.getContent());
        return model;
    }
    
    /**
     * Method used to prepare our model and select the view to show / edit the
     * details of the selected announcement.
     *
     * @param announcementId the id of the announcement
     * @param model the implicit model
     * @return view name to render (customer/announcement/detail)
     */
    @RequestMapping(value = "/customer/announcement/create",
            method = {RequestMethod.POST})
    public ModelAndView create(SessionStatus status, @Valid
            @ModelAttribute Announcements announcement, BindingResult result, 
            WebRequest request, Errors errors) {
//    public ModelAndView editDetails(@ModelAttribute("announcement") //@Valid 
//            Announcements announcement, BindingResult result, WebRequest request,
//            Errors errors) {
        String title = changeEncoding(announcement.getTitle(),
                pageEncoding, dbEncoding);
        announcement.setTitle(title);
        System.out.println("create " + title);  // ISO-8859-1
        String header = changeEncoding(announcement.getHeader(),
                pageEncoding, dbEncoding);
        announcement.setHeader(header);
        String content = changeEncoding(announcement.getContent(),
                pageEncoding, dbEncoding);
        announcement.setContent(content);
        Announcements registered = null;
        if (!result.hasErrors() && !announcement.getHeader().equals("new")) {
            System.out.println("!result.hasErrors() ");
            registered = createAnnouncement(announcement, result);
        }
        if (registered == null) {
            System.out.println("registered == null");
//            result.rejectValue("title", "message.title.error");
            
        }
        if (result.hasErrors()) {
            String titleErrorMessage = result.hasFieldErrors("title") 
                    ? result.getFieldError("title").getDefaultMessage()
                    : "";
            System.out.println("result.hasErrors() " + titleErrorMessage);
            if (!titleErrorMessage.isEmpty()) {
                result.rejectValue("title", "message.title.error", result
                        .getFieldError("title").getDefaultMessage());
            }
            String contentErrorMessage = result.hasFieldErrors("content")
                    ? result.getFieldError("content").getDefaultMessage()
                    : "";
            if (!contentErrorMessage.isEmpty()) {
                result.rejectValue("content", "message.content.error", result
                        .getFieldError("content").getDefaultMessage());
            }
            ModelAndView model 
                    = new ModelAndView("/customer/announcement/create");
            model.addObject("announcements", announcement);
//            AnnouncementErrorMessages announcementErrorMessages 
//                    = new AnnouncementErrorMessages(result
//                            .getFieldError("title").toString(), 
//                            "", 
//                            result.getFieldError("content").toString());
//            model.addObject("announcementErrorMessages", 
//                    announcementErrorMessages);
            return model;
//            return "redirect:/customer/announcement/create";
        } else {
//            return new ModelAndView("successRegister", "user", accountDto);
            if (announcement.getHeader().equals("new")) {
                int size = announcement.getNewHeader().length();
                if (size > 25 || 0 == size) {
                    result.rejectValue("newHeader", "message.header.error");
                    ModelAndView model 
                            = new ModelAndView("/customer/announcement/create");
                    model.addObject("announcements", announcement);
                    return model;
                }
                announcement.setHeader(changeEncoding(announcement
                            .getNewHeader(), pageEncoding, dbEncoding));
            }
            ModelAndView model 
                    = new ModelAndView("/customer/announcement/search");
//            model.addObject("announcements", announcement);
            model.addObject("announcementSearchCriteria",
                    new AnnouncementSearchCriteria());
            model.addObject("categories", this.announcementService
                    .findAllCategories());            
            model.addObject("authors", this.announcementService
                    .findAllAuthors());
            model.addObject("announcementsList", this.announcementService
                    .findAnnouncements(new AnnouncementSearchCriteria()));
            status.setComplete();
            return model;
//            return "redirect:/customer/announcement/search";
        }
    }
    
    @RequestMapping(value = "/customer/announcement/search/delete")
    public String delete(SessionStatus status, @Validated
            @ModelAttribute Announcements announcement, BindingResult errors) {
        this.announcementService.delete(announcement);
        status.setComplete();
//        if (request == null) {
//            System.out.println("request == null");
//        }
//        System.out.println("editDetails " + Arrays.toString(request.getParameterValues("announcements")));
//        System.out.println("editDetails " + session.getAttribute("announcements"));
//        System.out.println("editDetails " + request.getParameter("content"));
//        for (Object object : request.getParameterMap().keySet()) {
//            System.out.println("object " + object);
//        }
//        
//        ModelAndView modelAndView = new ModelAndView("customer/announcement/search");
//        model.addAttribute(announcement);
//        modelAndView.addObject(new AnnouncementSearchCriteria());
//        Set<Category> headers = new LinkedHashSet<>();
//        Category currentCategory = new Category(announcement.getHeader());
//        headers.add(currentCategory);
//        headers.addAll(this.announcementService.findAllCategories());
//        model.addAttribute("announcement", announcement);
//        model.addAttribute("headers", headers);
//        return modelAndView;
        return "redirect:/customer/announcement/search";
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

    private Announcements createAnnouncement(Announcements announcement, BindingResult result) {
        return this.announcementService.save(announcement);
    }
}
