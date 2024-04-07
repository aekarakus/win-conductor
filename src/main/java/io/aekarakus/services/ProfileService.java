package io.aekarakus.services;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    Profile createProfile(Profile profile);

    void deleteProfile(Long id);

    List<Profile> listProfiles();

    void updateProfile(List<Application> newApplicationList, Profile profile);

}