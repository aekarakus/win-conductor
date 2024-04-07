package io.aekarakus.domain.mappers;

import io.aekarakus.domain.ProfileInfo;
import io.aekarakus.domain.dtos.ProfileDto;
import io.aekarakus.domain.models.Profile;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileMapper {

    ProfileDto profileToProfileDto(Profile profile);
    ProfileInfo profileToProfileInfo(Profile profile);
}
