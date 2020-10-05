package com.novi.ManageMe.services.files;

import com.novi.ManageMe.exception.FileNotFoundException;
import com.novi.ManageMe.exception.FileStorageException;
import com.novi.ManageMe.models.page.ArtistPage;
import com.novi.ManageMe.models.files.Photo;
import com.novi.ManageMe.models.user.UserInformation;
import com.novi.ManageMe.repositories.files.PhotoRepository;
import com.novi.ManageMe.services.page.ArtistPageService;
import com.novi.ManageMe.services.user.UserInformationService;
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
public class PhotoStorageService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ArtistPageService artistPageService;

    @Autowired
    private UserInformationService userInformationService;

    // method for saving a photo to the database
    public Photo storePhoto(MultipartFile file, Long userId, String description, boolean profilePicture) {

        // normalizing file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // possible extensions
        String extensions = ".jpg,.jpeg,.png,";

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
                throw new FileStorageException("File must be .jpg, .jpeg or .png"); // throw exception when file does not have the right extension
            }

            // create instance of photo class
            Photo photo = new Photo(userId, description, fileName, file.getContentType(), file.getBytes(), profilePicture);

            // save photo
            Photo savedPhoto =  photoRepository.save(photo);

            // checks if user has an artistPage
            Optional<ArtistPage> artistPage = artistPageService.findByUser_id(userId);
            if (artistPage.isPresent()) {
                // if so, add photo id to ArtistPage
                artistPageService.addPhotoId(photo.getId(), userId);
            }

            // if profilePicture is true
            if (profilePicture) {
                // update user's profilePicture
                userInformationService.updateProfilePicture(userId, savedPhoto.getId());
            }

            // return photo
            return savedPhoto;

        // catch exception
        } catch (IOException e) {
            throw new FileStorageException("Could not store photo " + fileName + ". Please try again!"); // throw exception when photo could not be saved
        }
    }

    // get photo by photoId
    public Photo getPhoto(Long photoId) {
        return photoRepository.findById(photoId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + photoId.toString()));
    }

    // get photos by userId
    public List<Photo> getPhotosByUserId(Long userId) {

        // find all photos with this userId
        List<Photo> photos = photoRepository.findFileByUserId(userId);

        // if there are no photos found
        if (photos.isEmpty()) {
            throw new FileNotFoundException("No photos found for this user"); // throw exception when no photo was found
        } else {
            // return all found photos
            return photos;
        }
    }

    // delete photo by photoId
    public String deleteById(Long photoId) {
        try {
            Optional<Photo> toDelete = photoRepository.findById(photoId);
            if (toDelete.get().isProfilePicture()) {
                // delete and set null
                Optional<UserInformation> userInformation = userInformationService.findByProfilePictureId(photoId);
                userInformation.get().setProfilePictureId(null);
            }

            photoRepository.deleteById(photoId);
            return "Photo has been deleted!";
        } catch (Exception e) {
            return "Could not find photo to delete";
        }
    }

    // get photoIds by userId
    public List<Long> getPhotoIdsByUserId(Long userId) {
        // find user's photos
        List<Photo> photos = getPhotosByUserId(userId);

        // declare List where ids will be stored
        List<Long> ids = new ArrayList<>();

        // for user photo
        for (Photo photo: photos) {
            // add the id to the declared List
            ids.add(photo.getId());
        }
        // return the list with photoIds
        return ids;
    }

    // update description by photoId
    public String updateDescriptionByPhotoId(Long photoId, String description) {
        try {
            // get photo by photoId
            Photo photo = getPhoto(photoId);

            // set description
            photo.setDescription(description);

            // update description
            photoRepository.updateDescription(photoId, description);

            return "Description has been updated!";

        } catch (Exception e) {

            return "Could not find photo to update";
        }
    }

    public void updateProfilePictureById(Long photoId, boolean b) {
        photoRepository.updateProfilePictureById(photoId, b);
    }
}
