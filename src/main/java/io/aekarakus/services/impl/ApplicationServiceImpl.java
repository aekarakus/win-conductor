package io.aekarakus.services.impl;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.repositories.ApplicationRepository;
import io.aekarakus.services.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Transactional
    @Override
    public void deleteApplication(String chocolateyName) {
        System.out.println(chocolateyName);
        applicationRepository.deleteByChocolateyName(chocolateyName);
    }

    @Override
    public List<Application> listApplications() {
        return applicationRepository.findAll();
    }
}
