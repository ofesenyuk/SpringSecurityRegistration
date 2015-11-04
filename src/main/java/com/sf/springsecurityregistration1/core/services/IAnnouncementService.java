/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import com.sf.springsecurityregistration1.core.entities.AnnouncementSearchCriteria;
import com.sf.springsecurityregistration1.core.entities.Announcements;
import com.sf.springsecurityregistration1.core.entities.Category;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author sf
 */
public interface IAnnouncementService {

    public Collection<Category> findAllCategories();

    public Collection<Announcements> 
        findAnnouncements(AnnouncementSearchCriteria criteria);

    public Collection<String> findAllAuthors();

    public Announcements findAnnouncement(long announcementId);

    public Announcements save(Announcements announcement);

    public Announcements update(Announcements announcement);

    public void delete(Announcements announcement);
    
}
