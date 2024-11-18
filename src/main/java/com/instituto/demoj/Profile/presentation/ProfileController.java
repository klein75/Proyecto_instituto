package com.instituto.demoj.Profile.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instituto.demoj.Profile.business.Facade.ProfileFacade;
import com.instituto.demoj.Profile.domain.entity.ProfileEntity;



@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileFacade profileFacade;

    @Autowired
    public ProfileController(ProfileFacade profileFacade) {
        this.profileFacade = profileFacade;
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileEntity> createProfile(@RequestBody ProfileEntity profileEntity) {
        return profileFacade.createProfile(profileEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileEntity> updateProfile(@PathVariable Long id, @RequestBody ProfileEntity profileEntity) {
        return profileFacade.updateProfile(id, profileEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        return profileFacade.deleteProfile(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileEntity> getProfileById(@PathVariable Long id) {
        return profileFacade.getProfileById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileEntity>> getAllProfiles() {
        return profileFacade.getAllProfiles();
    }
}
