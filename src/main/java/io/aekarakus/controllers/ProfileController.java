package io.aekarakus.controllers;

import io.aekarakus.domain.models.Profile;
import io.aekarakus.services.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){

        Profile createdProfile = profileService.createProfile(profile);
        return ResponseEntity.ok().body(createdProfile);

    }

    @DeleteMapping("/delete")
    public void deleteProfile(@RequestBody Long id){

        profileService.deleteProfile(id);
    }

    @GetMapping("/list")
    ResponseEntity<List<Profile>> listProfiles(){

        List<Profile> profiles = profileService.listProfiles();
        return ResponseEntity.ok().body(profiles);
    }
}
