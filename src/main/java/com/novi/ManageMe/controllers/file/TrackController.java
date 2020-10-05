package com.novi.ManageMe.controllers.file;

import com.novi.ManageMe.models.files.Track;
import com.novi.ManageMe.payloads.file.UploadFileResponse;
import com.novi.ManageMe.services.files.TrackStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/user/track")
public class TrackController {

    @Autowired
    private TrackStorageService trackStorageService;

    // save track
    @PostMapping(path = "/upload", headers = ("content-type=multipart/form-data;boundary=032a1ab685934650abbe059cb45d6ff3"))
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("description") String description, @RequestParam("name") String name) {

        // save track
        Track track = trackStorageService.storeTrack(file, userId, description, name);

        // create links
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/track/download-track/") // append given path to the existing path
                .path(track.getId().toString()) // append given path to the existing path
                .toUriString(); // build, endode and return the String representation

        // return response
        return new UploadFileResponse(track.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }

    // save tracks
    @PostMapping("/upload-tracks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<UploadFileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files, Long userId) {
        return Arrays.asList(files)
                .stream() // stream array with files
                .map(file -> uploadFile(file, userId, "", "")) // invoke uploadFile method for every file
                .collect(Collectors.toList()); // return a Collector interface that gathers the input data into a new list
    }

    // get tracks by userId
    @GetMapping(path = "/get-tracks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Track> getTracksByUserId(@Valid @RequestParam("userId") Long userId) {
        return trackStorageService.getTracksByUserId(userId);
    }


    // download track
    @GetMapping(path = "/download-track")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public ResponseEntity<Resource> downloadTrack(@Valid @RequestParam Long trackId) {

        // get track from the database by trackId
        Track track = trackStorageService.getTrack(trackId);

        // return HttpResponse
        return ResponseEntity.ok() // status 200 (ok)
                .contentType(MediaType.parseMediaType(track.getFileType())) // set file's content type (fileType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + track.getFileName() + "\"") // set header (fileName)
                .body(new ByteArrayResource(track.getData())); // set body (data)
    }

    // get a user's trackIds
    @GetMapping(path = "/personal/ids")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Long> getTrackIdsByUserId(@Valid @RequestParam Long userId) {
        // get a user's trackIds
        return trackStorageService.getTrackIdsByUserId(userId);
    }

    // delete track
    @DeleteMapping(path = "/delete")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String deleteTrackById(@RequestParam("trackId") Long trackId) {
        return trackStorageService.deleteById(trackId);
    }

    // update description
    @PutMapping(path = "/update-description")
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER or ADMIN
    public String updateDescriptionByPhotoId(@Valid @RequestParam Long trackId, @Valid @RequestParam String description) {
        return trackStorageService.updateDescriptionByTrackId(trackId, description);
    }

    // update name
    @PutMapping(path = "/update-name")
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER or ADMIN
    public String updateNameByTrackId(@Valid @RequestParam Long trackId, @Valid @RequestParam String name) {
        return trackStorageService.updateNameByTrackId(trackId, name);
    }
}