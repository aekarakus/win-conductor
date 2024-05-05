package io.aekarakus.winconductor.engine.services.impl;

import io.aekarakus.winconductor.engine.domain.models.Application;
import io.aekarakus.winconductor.engine.domain.repositories.ApplicationRepository;
import io.aekarakus.winconductor.engine.services.ApplicationService;
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
