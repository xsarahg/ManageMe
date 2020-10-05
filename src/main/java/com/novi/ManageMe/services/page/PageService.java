package com.novi.ManageMe.services.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    ArtistPageService artistPageService;

    public PageService(ArtistPageService artistPageService) {
        this.artistPageService = artistPageService;
    }

}
