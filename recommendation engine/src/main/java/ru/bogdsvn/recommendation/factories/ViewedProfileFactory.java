package ru.bogdsvn.recommendation.factories;

import org.springframework.stereotype.Component;
import ru.bogdsvn.recommendation.dtos.ResultDto;
import ru.bogdsvn.recommendation.store.ViewedProfileEmbeddedId;
import ru.bogdsvn.recommendation.store.entities.ViewedProfileEntity;

@Component
public class ViewedProfileFactory {
    public ViewedProfileEntity makeViewedProfileEntity(ResultDto resultDto, Long profileId) {
        return ViewedProfileEntity.builder()
                .id(ViewedProfileEmbeddedId.builder()
                        .profileId(profileId)
                        .viewedProfileId(resultDto.getId())
                        .build())
                .name(resultDto.getName())
                .description(resultDto.getDescription())
                .age(resultDto.getAge())
                .gender(resultDto.getGender())
                .dist(resultDto.getDist())
                .build();
    }
}
