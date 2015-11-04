/*
 * To change this license headerError, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.springsecurityregistration1.web.controllers;

/**
 *
 * @author sf
 */
public class AnnouncementErrorMessages {
    private final String titleError;
    private final String headerError;
    private final String contentError;
    
    public AnnouncementErrorMessages(String titleError, String headerError,
            String contentError) {
        this.contentError = contentError;
        this.headerError = headerError;
        this.titleError = titleError;
    }

    public String getTitle() {
        return titleError;
    }

    public String getHeader() {
        return headerError;
    }

    public String getContent() {
        return contentError;
    }
}
