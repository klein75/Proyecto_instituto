package com.instituto.demoj.Recuperacion.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.Recuperacion.DTO.RProfileDto;
import com.instituto.demoj.Recuperacion.entity.RProfile;
import com.instituto.demoj.Recuperacion.entity.RUser;
import com.instituto.demoj.Recuperacion.repository.RProfileRepository;
import com.instituto.demoj.Recuperacion.repository.RUserRepositoy;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/profile")
public class RProfileController {

    @Autowired

    private RProfileRepository profileRepository;



    @Autowired

    private RUserRepositoy userRepository;



    @Autowired

    private ModelMapper modelMapper;



    @GetMapping("/all")
    public List<RProfileDto> getAllProfiles() {
        return profileRepository.findAll()
        .stream()
        .map(RProfile -> modelMapper.map(RProfile, RProfileDto.class))
        .collect(Collectors.toList());
    }



    @GetMapping("/{id}")
    public ResponseEntity<RProfileDto> getProfileById(@PathVariable Long id) {
        return profileRepository.findById(id)
                .map(profile -> ResponseEntity.ok(modelMapper.map(profile, RProfileDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    @PostMapping

    public ResponseEntity<RProfile> createProfile(@RequestBody RProfileDto profileDto) {

        RProfile profile = modelMapper.map(profileDto, RProfile.class);



        RUser user = userRepository.findById(profileDto.getUserId())

                .orElseThrow(() -> new RuntimeException("User not found with ID: " + profileDto.getUserId()));

        profile.setUser(user);

        profile.setId(profileDto.getUserId());



        RProfile createdProfile = profileRepository.save(profile);

        return ResponseEntity.status(201).body(createdProfile);

    }



    @PutMapping("/{userId}")

    public ResponseEntity<RProfile> updateProfile(@PathVariable Long userId, @RequestBody RProfileDto profileDto) {

        RProfile profile = profileRepository.findById(userId)

                .orElseThrow(() -> new RuntimeException("Profile not found for User ID: " + userId));



        modelMapper.map(profileDto, profile);



        RProfile updatedProfile = profileRepository.save(profile);

        return ResponseEntity.ok(updatedProfile);

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {

        if (profileRepository.existsById(id)) {

            profileRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }

}


