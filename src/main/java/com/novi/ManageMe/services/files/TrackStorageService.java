package com.novi.ManageMe.services.files;

import com.novi.ManageMe.exception.FileNotFoundException;
import com.novi.ManageMe.exception.FileStorageException;
import com.novi.ManageMe.models.page.ArtistPage;
import com.novi.ManageMe.models.files.Track;
import com.novi.ManageMe.repositories.files.TrackRepository;
import com.novi.ManageMe.services.page.ArtistPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TrackStorageService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistPageService artistPageService;

    // method for saving a track to the database
    public Track storeTrack(MultipartFile file, Long userId, String description, String name) {

        // normalizing file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // possible extensions
        String extensions = ".mp3";

        // get extension first index number
        int lastIndex = fileName.lastIndexOf('.');

        // get file extension
        String substring = fileName.substring(lastIndex, fileName.length());

        try {

            // check for invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename is invalid: " + fileName); // throw exception when file contains invalid characters
            }

            // check if file has the correct extension
            if (!extensions.contains(substring)) {
                throw new FileStorageException("File must be .mp3"); // throw exception when file does not have the right extension
            }

            // create instance of track class
            Track track = new Track(userId, name, description, fileName, file.getContentType(), file.getBytes());

            // save track
            Track savedTrack =  trackRepository.save(track);

            // checks if user has an artistPage
            Optional<ArtistPage> artistPage = artistPageService.findByUser_id(userId);
            if (artistPage.isPresent()) {
                // if so, add track id to ArtistPage
                artistPageService.addTrackId(track.getId(), userId);
            }

            // return photo
            return savedTrack;

            // catch exception
        } catch (IOException e) {
            throw new FileStorageException("Could not store track " + fileName + ". Please try again!"); // throw exception when track could not be saved
        }
    }

    // get track by trackId
    public Track getTrack(Long trackId) {
        return trackRepository.findById(trackId)
                .orElseThrow(() -> new FileNotFoundException("Track with id: " + trackId + " not found")); // throw exception when track could not be found
    }


    // get tracks by userId
    public List<Track> getTracksByUserId(Long userId) {

        // find all tracks with this userId
        List<Track> tracks = trackRepository.findFileByUserId(userId);

        // if there are no tracks found
        if (tracks.isEmpty()) {

            throw new FileNotFoundException("No tracks found for this user"); // throw exception when no photo was found
        } else {

            // return all found tracks
            return tracks;
        }
    }

    public String deleteById(Long trackId) {
        try {
            trackRepository.deleteById(trackId);
            return "track had been deleted!";
        } catch (Exception e) {
            return "could not find track to delete";
        }
    }

    // get ptrackIds by userId
    public List<Long> getTrackIdsByUserId(Long userId) {
        // find user's tracks
        List<Track> tracks = getTracksByUserId(userId);

            // declare List where ids will be stored
            List<Long> ids = new ArrayList<>();

            // for user photo
            for (Track track: tracks) {
                // add the id to the declared List
                ids.add(track.getId());
            }
            // return the list with trackIds
            return ids;
        }

    public String updateDescriptionByTrackId(Long trackId, String description) {
        try {
            // get track by trackId
            Track track = getTrack(trackId);

            // set description
            track.setDescription(description);

            // update description
            trackRepository.updateDescription(trackId, description);

            return "Description has been updated!";

        } catch (Exception e) {

            return "Could not find photo to update";
        }
    }

    public String updateNameByTrackId(Long trackId, String name) {
        try {
            // get track by trackId
            Track track = getTrack(trackId);

            // set name
            track.setName(name);

            // update name
            trackRepository.updateName(trackId, name);

            return "Name has been updated!";

        } catch (Exception e) {

            return "Could not find name to update";
        }
    }
}
