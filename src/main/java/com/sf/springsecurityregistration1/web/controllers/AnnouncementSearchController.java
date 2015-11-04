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
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    
    @ModelAttribute
    public AnnouncementSearchCriteria criteria() {
        return new AnnouncementSearchCriteria();
    }

    @ModelAttribute("categories")
    public Collection<Category> getCategories() {
        return this.announcementService.findAllCategories();
    }
    
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
     * @return the found announcements
     *
     * @see
     * com.apress.prospringmvc.bookstore.repository.BookRepository#findBooks(BookSearchCriteria)
     */
    @RequestMapping(value = "/customer/announcement/search", 
            method = {RequestMethod.GET})
    public ModelAndView list(@ModelAttribute("announcementSearchCriteria") 
            AnnouncementSearchCriteria criteria) {
        System.out.println("criteria " + criteria.getAuthor() + " " 
                + criteria.getCategory());
        
//        if Enum.valueOf(criteria.getAuthor().equals(SELECTION_TYPES.my.)) {
        if ("my".equals(criteria.getAuthor())) {
            Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();
            String name = auth.getName(); //get logged in username
            System.out.println("logged " + name);
            System.out.println("my is selected ");
            criteria.setAuthor(name);
        }
        Collection<Announcements> announcementsList =  this
                .announcementService.findAnnouncements(criteria);
        ModelAndView model = new ModelAndView("/customer/announcement/search");
        model.addObject("announcementsList", announcementsList);
        return model;
    }
    
}
