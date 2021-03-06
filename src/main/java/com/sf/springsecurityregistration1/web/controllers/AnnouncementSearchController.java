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
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sf
 */
@Controller
//@SessionAttributes(types = {Announcements.class })
public class AnnouncementSearchController {
    private static enum SELECTION_TYPES {
        all, my
    };
    @Autowired
    private IAnnouncementService announcementService;
    
    /**
     * This method creates a new AnnouncementSearchCriteria for search form
     *
     * @return criteria
     *
     */
    @ModelAttribute
    public AnnouncementSearchCriteria criteria() {
        return new AnnouncementSearchCriteria();
    }

    /**
     * This method creates a new Category list for search form
     *
     * @return Category list
     *
     */
    @ModelAttribute("categories")
    public Collection<Category> getCategories() {
        return this.announcementService.findAllCategories();
    }
    
    /**
     * This method prepares authors list for search form
     *
     * @return authors list
     *
     */
    @ModelAttribute("authors")
    public Collection<String> getAuthors() {
        return this.announcementService.findAllAuthors();
    }

    /**
     * This method searches our database for announcements based on the given
     * {@link AnnouncementSearchCriteria}. Only announcements matching the 
     * criteria are returned.
     *
     * @param criteria the criteria used for searching
     * @return model of next view
     */
    @RequestMapping(value = "/customer/announcement/search", 
            method = {RequestMethod.GET})
    public ModelAndView list(@ModelAttribute("announcementSearchCriteria") 
            AnnouncementSearchCriteria criteria) {
//        System.out.println("criteria " + criteria.getAuthor() + " " 
//                + criteria.getCategory());
        
//        if Enum.valueOf(criteria.getAuthor().equals(SELECTION_TYPES.my.)) {
        if ("my".equals(criteria.getAuthor())) {
            Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();
            String name = auth.getName(); //get logged in username
//            System.out.println("logged " + name);
//            System.out.println("my is selected ");
            criteria.setAuthor(name);
        }
        Collection<Announcements> announcementsList =  this
                .announcementService.findAnnouncements(criteria);
        ModelAndView model = new ModelAndView("/customer/announcement/search");
        model.addObject("announcementsList", announcementsList);
        return model;
    }
    
}
