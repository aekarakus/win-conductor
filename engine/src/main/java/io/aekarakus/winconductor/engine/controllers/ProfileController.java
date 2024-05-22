package io.aekarakus.winconductor.engine.controllers;

import io.aekarakus.winconductor.engine.domain.dtos.ProfileDto;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import io.aekarakus.winconductor.engine.services.ProfileService;
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
    public ResponseEntity<ProfileDto> createProfile(@RequestBody Profile profile){

        ProfileDto createdProfile = profileService.createProfile(profile);
        return ResponseEntity.ok().body(createdProfile);

    }

    @DeleteMapping("/delete")
    public void deleteProfile(@RequestBody Long id){

        profileService.deleteProfile(id);
    }

    @GetMapping("/list")
    ResponseEntity<List<ProfileDto>> listProfiles(){

        List<ProfileDto> profiles = profileService.listProfiles();
        return ResponseEntity.ok().body(profiles);
    }
}
