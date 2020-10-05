package com.novi.ManageMe.services.roadmap;

import com.novi.ManageMe.models.page.ArtistPage;
import com.novi.ManageMe.models.roadmap.Fase;
import com.novi.ManageMe.models.roadmap.Roadmap;
import com.novi.ManageMe.models.roadmap.SubFase;
import com.novi.ManageMe.models.user.User;
import com.novi.ManageMe.services.page.ArtistPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoadmapServiceImpl {

    @Autowired
    RoadmapService roadmapService;

    @Autowired
    FaseService faseService;

    @Autowired
    SubFaseService subFaseService;

    @Autowired
    ArtistPageService artistPageService;

    public RoadmapServiceImpl(RoadmapService roadmapService, FaseService faseService, SubFaseService subFaseService) {
        this.roadmapService = roadmapService;
        this.faseService = faseService;
        this.subFaseService= subFaseService;
    }

    // in the future: create roadmaps based on template! Now not necessary
    public void createInitialRoadmap(User user) {

        // create roadmap for category artist
        if (user.getCategory().getName().name().equals("CATEGORY_ARTIST")) {

            // create lists for fases and subfaces
            Set<Fase> fases = new HashSet<>();
            Set<SubFase> subFases1 = new HashSet<>();
            Set<SubFase> subFases2 = new HashSet<>();
            Set<SubFase> subFases3 = new HashSet<>();

            // create roadmap
            Roadmap roadmap = new Roadmap("Artist Roadmap", user.getId(), user.getCategory().getName().name(), (long) 1);

            // save roadmap
            roadmapService.save(roadmap);

            // create fases
            Fase fase1 = new Fase(roadmap.getId());
            Fase fase2 = new Fase(roadmap.getId());
            Fase fase3 = new Fase(roadmap.getId());

            // save fases
            faseService.save(fase1);
            faseService.save(fase2);
            faseService.save(fase3);

            // place fases in list
            fases.add(fase1);
            fases.add(fase2);
            fases.add(fase3);

            // add list of fases to roadmap
            roadmap.setFases(fases);

            // create subfases
            SubFase subFase1 = new SubFase(fase1.getId());
            SubFase subFase2 = new SubFase(fase1.getId());
            SubFase subFase3 = new SubFase(fase1.getId());

            SubFase subFase4 = new SubFase(fase2.getId());
            SubFase subFase5 = new SubFase(fase2.getId());

            SubFase subFase6 = new SubFase(fase3.getId());
            SubFase subFase7 = new SubFase(fase3.getId());
            SubFase subFase8 = new SubFase(fase3.getId());

            // add subfases to list by fase
            subFases1.add(subFase1);
            subFases1.add(subFase2);
            subFases1.add(subFase3);

            subFases2.add(subFase4);
            subFases2.add(subFase5);

            subFases3.add(subFase6);
            subFases3.add(subFase7);
            subFases3.add(subFase8);


            for (SubFase subFase: subFases1) {
                subFaseService.save(subFase);
            }

            for (SubFase subFase: subFases2) {
                subFaseService.save(subFase);
            }

            for (SubFase subFase: subFases3) {
                subFaseService.save(subFase);
            }

            // set subfases in fases
            fase1.setSubFases(subFases1);
            fase2.setSubFases(subFases2);
            fase3.setSubFases(subFases3);

            // create ArtistPage
            ArtistPage artistPage = new ArtistPage(user.getId(), roadmap.getId());
            artistPageService.save(artistPage);

        }
    }
}
