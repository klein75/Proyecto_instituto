package com.instituto.demoj.Profile.business;

import com.instituto.demoj.Profile.domain.entity.ProfileEntity;
import java.util.List; import java.util.Optional;

public interface ProfileService {

ProfileEntity createProfile(ProfileEntity profileEntity); 
ProfileEntity updateProfile(Long id, ProfileEntity profileEntity); 
void deleteProfile(Long id); 
Optional<ProfileEntity> getProfileById(Long id); 
List<ProfileEntity> getAllProfiles();

}
