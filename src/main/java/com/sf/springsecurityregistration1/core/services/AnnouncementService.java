/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.core.services;

import com.sf.springsecurityregistration1.core.entities.AnnouncementSearchCriteria;
import com.sf.springsecurityregistration1.core.entities.Announcements;
import com.sf.springsecurityregistration1.core.entities.Category;
import com.sf.springsecurityregistration1.core.repository.AnnouncementRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sf
 */
@Service
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    private AnnouncementRepository repository;
    
    @Override
    public Collection<Category> findAllCategories() {
        Collection<Category> categories = repository.findAllCategories();
        return categories;
    }

    @Override
    public Collection<Announcements> findAnnouncements(AnnouncementSearchCriteria criteria) {
        return repository.findAnnouncements(criteria);
    }

    @Override
    public Collection<String> findAllAuthors() {
        return repository.findAllAuthors();
    }

    @Override
    public Announcements findAnnouncement(long announcementId) {
        return repository.findAnnouncement(announcementId);
    }

    @Override
    public Announcements save(Announcements announcement) {
        return repository.save(announcement);
    }

    @Override
    public Announcements update(Announcements announcement) {
        return repository.update(announcement);
    }

    @Override
    public void delete(Announcements announcement) {
        repository.delete(announcement);
    }
    
}
