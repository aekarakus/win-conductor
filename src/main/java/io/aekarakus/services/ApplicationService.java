package io.aekarakus.services;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

    Application createApplication(Application application);
    void deleteApplication(String chocolateyName);
    List<Application> listApplications();
}
