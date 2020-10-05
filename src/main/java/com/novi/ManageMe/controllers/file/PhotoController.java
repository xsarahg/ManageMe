package com.novi.ManageMe.controllers.file;

import com.novi.ManageMe.models.files.Photo;
import com.novi.ManageMe.payloads.file.UploadFileResponse;
import com.novi.ManageMe.services.files.PhotoStorageService;
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
@RequestMapping("/api/user/photo")
public class PhotoController {

    @Autowired
    private PhotoStorageService photoStorageService;

    // save photo
    @PostMapping(path = "/upload")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")  // method can be invoked by user with role USER or ADMIN
    public UploadFileResponse uploadFile(@Valid @RequestParam("file")MultipartFile file, @Valid @RequestParam("userId") Long userId, @Valid @RequestParam("description") String description, @Valid @RequestParam boolean profilePicture) {

        // save photo
        Photo photo = photoStorageService.storePhoto(file, userId, description, profilePicture);

        // create links
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/photo/download-photo/") // append given path to the existing path
                .path(photo.getId().toString()) // append given path to the existing path
                .toUriString(); // build, encode and return the String representation

        // return response
        return new UploadFileResponse(photo.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }

    // save photos
    @PostMapping("/upload-photos")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<UploadFileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files, Long userId) {
        return Arrays.asList(files)
                .stream() // stream array with files
                .map(file -> uploadFile(file, userId, "", false)) // invoke uploadFile method for every file
                .collect(Collectors.toList()); // return a Collector interface that gathers the input data into a new list
    }

    // get photo by photoId
    @GetMapping(path = "/get-photo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Photo getPhotoById(@Valid @RequestParam Long photoId) {
        return photoStorageService.getPhoto(photoId);
    }

    // get photos by userId
    @GetMapping(path = "/get-photos")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Photo> getPhotosByUserId(@Valid @RequestParam("userId") Long userId) {
        // get a user's photos
        return photoStorageService.getPhotosByUserId(userId);
    }

    // download photo
    @GetMapping(path = "/download-photo/{photoId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public ResponseEntity<Resource> downloadPhoto(@Valid @PathVariable Long photoId) {

        // get photo from the database by photoId
        Photo photo = photoStorageService.getPhoto(photoId);

        // return HttpResponse
        return ResponseEntity.ok() // status 200 (ok)
                .contentType(MediaType.parseMediaType(photo.getFileType())) // set file's content type (fileType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getFileName() + "\"") // set header (fileName)
                .body(new ByteArrayResource(photo.getData())); // set body (data)
    }

    // get a user's photoIds
    @GetMapping(path = "/personal/ids")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Long> getPhotoIdsByUserId(@Valid @RequestParam Long userId) {
        // get a user's photoIds
        return photoStorageService.getPhotoIdsByUserId(userId);
    }

    // delete photo
    @DeleteMapping(path = "/delete")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String deletePhotoById(@RequestParam("photoId") Long photoId) {
        return photoStorageService.deleteById(photoId);
    }

    // update description
    @PutMapping(path = "/update-description")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String updateDescriptionByPhotoId(@Valid @RequestParam Long photoId, @Valid @RequestParam String description) {
        return photoStorageService.updateDescriptionByPhotoId(photoId, description);
    }
}