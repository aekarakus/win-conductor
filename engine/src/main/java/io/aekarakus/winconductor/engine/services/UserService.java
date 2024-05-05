package io.aekarakus.winconductor.engine.services;

import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getUsersOnDevice(Device device);
    void createKioskUser(User user);
    void deleteKioskUser(User user);
}
