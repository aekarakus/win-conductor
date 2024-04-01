package io.aekarakus.services;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import io.aekarakus.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getUsersOnDevice(Device device);
    void createKioskUser(User user);
    void deleteKioskUser(User user);
}
