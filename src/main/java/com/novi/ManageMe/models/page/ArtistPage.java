package com.novi.ManageMe.models.page;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "artist_pages") // specifies the details of the table that will be used to create the table in the database
public class ArtistPage extends Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 1000)
    @Column(name = "biography")
    private String biography;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_page_genres",
            joinColumns = @JoinColumn(name = "artist_page_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_page_experiences",
            joinColumns = @JoinColumn(name = "artist_page_id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id"))
    private Set<Experience> experiences;

    @ElementCollection
    @CollectionTable(name = "artist_page_photos", joinColumns = @JoinColumn(name = "artist_page_id"))
    @Column(name = "photo_id")
    private Set<Long> photo_ids = new HashSet<>();

    @ElementCollection //handles non-standard relationship mappings
    @CollectionTable(name = "artist_page_tracks", joinColumns = @JoinColumn(name = "artist_page_id"))
    @Column(name = "track_id")
    private Set<Long> track_ids;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_page_news",
            joinColumns = @JoinColumn(name = "artist_page_id"),
            inverseJoinColumns = @JoinColumn(name = "news_item_id"))
    private Set<NewsItem> news;

    public ArtistPage(Long user_id, Long roadmapId) {
        super();
        this.user_id = user_id;
        this.roadmapId = roadmapId;
    }
}
