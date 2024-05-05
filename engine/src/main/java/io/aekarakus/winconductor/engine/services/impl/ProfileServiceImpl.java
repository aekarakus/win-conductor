package io.aekarakus.winconductor.engine.services.impl;

import io.aekarakus.winconductor.engine.domain.dtos.ProfileDto;
import io.aekarakus.winconductor.engine.domain.mappers.ProfileMapper;
import io.aekarakus.winconductor.engine.domain.models.Application;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import io.aekarakus.winconductor.engine.domain.repositories.ProfileRepository;
import io.aekarakus.winconductor.engine.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileDto createProfile(Profile profile) {
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.profileToProfileDto(savedProfile);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<ProfileDto> listProfiles() {
        return profileRepository.findAll().stream().map(profileMapper::profileToProfileDto).toList();
    }

    @Override
    public void updateProfile(List<Application> newApplicationList, Profile profile) {

    }
}
