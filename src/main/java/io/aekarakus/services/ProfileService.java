package io.aekarakus.services;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {

    void createProfile(Profile profile);
    void deleteProfile(Profile profile);

    void updateProfile(List<Application> newApplicationList, Profile profile);

    void getProfileOnDevice();

    //void addApplicationToProfile(Application application, Profile profile);
    //void addApplicationsToProfie(List<Application> applications, Profile profile);
}
