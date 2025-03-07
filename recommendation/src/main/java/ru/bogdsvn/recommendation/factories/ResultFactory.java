package ru.bogdsvn.recommendation.factories;

import org.springframework.stereotype.Component;
import ru.bogdsvn.recommendation.dtos.BioDto;
import ru.bogdsvn.recommendation.dtos.ProfileDto;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.store.entities.ViewedProfileEntity;

@Component
public class ResultFactory {
    public ResultDto makeResultDto(ViewedProfileEntity profile) {
        return ResultDto.builder()
                .id(profile.getId().getViewedProfileId())
                .age(profile.getAge())
                .dist(profile.getDist())
                .name(profile.getName())
                .description(profile.getDescription())
                .gender(profile.getGender())
                .build();
    }
    public ResultDto makeResultDto(BioDto bio, ProfileDto p) {
        return ResultDto.builder()
                .id(p.getId())
                .age(bio.getAge())
                .name(bio.getName())
                .description(bio.getDescription())
                .gender(bio.getGender())
                .dist(p.getDist())
                .build();
    }
}
