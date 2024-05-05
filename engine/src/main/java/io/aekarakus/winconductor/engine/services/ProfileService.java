package io.aekarakus.winconductor.engine.services;

import io.aekarakus.winconductor.engine.domain.dtos.ProfileDto;
import io.aekarakus.winconductor.engine.domain.models.Application;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    ProfileDto createProfile(Profile profile);

    void deleteProfile(Long id);

    List<ProfileDto> listProfiles();

    void updateProfile(List<Application> newApplicationList, Profile profile);

}