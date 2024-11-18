package com.instituto.demoj.Profile.business.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import java.util.List; 
import java.util.Optional;

import com.instituto.demoj.Profile.business.ProfileService;
import com.instituto.demoj.Profile.domain.entity.ProfileEntity;
import com.instituto.demoj.Profile.persistence.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    
    
    @Autowired 
    public ProfileServiceImpl(ProfileRepository profileRepository) 
    { this.profileRepository = profileRepository; } 
    
    @Override 
    public ProfileEntity createProfile(ProfileEntity profileEntity) 
    { return profileRepository.save(profileEntity); } 
    
    @Override 
    public ProfileEntity updateProfile(Long id, ProfileEntity profileEntity)  { 
        if (profileRepository.existsById(id)) 
           { profileEntity.setId(id); 
            return profileRepository.save(profileEntity); } 
            return null; } 
            
    @Override 
    public void deleteProfile(Long id) 
    { profileRepository.deleteById(id); } 
    
    @Override public Optional<ProfileEntity> getProfileById(Long id) { 
        return profileRepository.findById(id); } 


    @Override 
    public List<ProfileEntity> getAllProfiles() { 
        return profileRepository.findAll(); }

}
