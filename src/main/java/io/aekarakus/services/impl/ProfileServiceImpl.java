package io.aekarakus.services.impl;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Profile;
import io.aekarakus.domain.repositories.ProfileRepository;
import io.aekarakus.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> listProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public void updateProfile(List<Application> newApplicationList, Profile profile) {

    }
}
