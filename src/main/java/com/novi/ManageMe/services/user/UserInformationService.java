package com.novi.ManageMe.services.user;

import com.novi.ManageMe.models.user.EGender;
import com.novi.ManageMe.models.user.UserInformation;
import com.novi.ManageMe.repositories.user.UserInformationRepository;
import com.novi.ManageMe.services.files.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    public UserInformationService(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    public String save(UserInformation userInformation) {
        this.userInformationRepository.save(userInformation);
        return "saaaaaved";
    }

    public String updateUserInformation(String gender, String place, Long profilePictureId, Long userId) {
        try {
            EGender eGender;

            switch (gender) {
                case "male":
                    eGender = EGender.MALE;
                    break;
                case "female":
                    eGender = EGender.FEMALE;
                    break;
                default:
                    eGender = EGender.OTHER;
            }

            userInformationRepository.updateUserInformation(eGender, profilePictureId, place, userId);
            return "Updated user information!";
        } catch (Exception e) {
            return "No user information was found to update";
        }
    }

    public void updateProfilePicture(Long userId, Long profilePictureId) {
        // change profilePicture of previous profilePicture to false
        Optional<UserInformation> userInformation = userInformationRepository.findById(userId);
        userInformation.ifPresent(information -> photoStorageService.updateProfilePictureById(information.getProfilePictureId(), false));
        userInformationRepository.updateProfilePictureId(userId, profilePictureId);
    }

    public Optional<UserInformation> findByUserId(Long userId) {
        return userInformationRepository.findById(userId);
    }

    public Optional<UserInformation> findByProfilePictureId(Long photoId) {
        return userInformationRepository.findByProfilePictureId(photoId);
    }
}
