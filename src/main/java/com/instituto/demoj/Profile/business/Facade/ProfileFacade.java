package com.instituto.demoj.Profile.business.Facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.instituto.demoj.Profile.business.ProfileService;
import com.instituto.demoj.Profile.domain.entity.ProfileEntity;

@Component
public class ProfileFacade {

    private final ProfileService profileService;
    
    @Autowired
    public ProfileFacade(ProfileService profileService) {
        this.profileService = profileService;
    }

     public ResponseEntity<ProfileEntity> createProfile(ProfileEntity profileEntity) {
        ProfileEntity createdProfile = profileService.createProfile(profileEntity);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    public ResponseEntity<ProfileEntity> updateProfile(Long id, ProfileEntity profileEntity) {
        ProfileEntity updatedProfile = profileService.updateProfile(id, profileEntity);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteProfile(Long id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<ProfileEntity> getProfileById(Long id) {
        Optional<ProfileEntity> profileEntity = profileService.getProfileById(id);
        return profileEntity.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<ProfileEntity>> getAllProfiles() {
        List<ProfileEntity> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}


