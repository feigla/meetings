package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.errors.BadRequestException;
import ru.bogdsvn.profile.errors.NotFoundException;
import ru.bogdsvn.profile.factories.BioFactory;
import ru.bogdsvn.profile.store.entites.BioEntity;
import ru.bogdsvn.profile.store.entites.ProfileEntity;
import ru.bogdsvn.profile.store.repositories.BioRepository;
import ru.bogdsvn.profile.store.repositories.ProfileRepository;
import ru.bogdsvn.profile.utils.Gender;

@RequiredArgsConstructor
@Service
public class BioService {
    private final BioFactory bioFactory;
    private final ProfileRepository profileRepository;
    private final BioRepository bioRepository;

    @Transactional
    public BioDto fillBio(BioDto bioDto, Long id) {
        bioRepository
                .findById(id)
                .ifPresent((bioEntity) -> {
                    throw new BadRequestException("Профиль уже заполнен");
                });

        ProfileEntity profileEntity = profileRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Профиль не найден"));

        BioEntity bioEntity =  BioEntity
                .builder()
                .name(bioDto.getName())
                .age(bioDto.getAge())
                .description(bioDto.getDescription())
                .gender(Gender.valueOf(bioDto.getGender()))
                .profile(profileEntity)
                .build();

        bioRepository.save(bioEntity);

        return bioFactory.makeBioDto(bioEntity);
    }

    @Transactional
    public BioDto updateBio(BioDto bioDto, long id) {
        BioEntity bioEntity = bioRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Информация о профиле не заполнена"));

        bioEntity.setAge(bioDto.getAge());
        bioEntity.setDescription(bioDto.getDescription());
        bioEntity.setGender(Gender.valueOf(bioDto.getGender()));
        bioEntity.setName(bioDto.getName());

        bioRepository.save(bioEntity);

        return bioFactory.makeBioDto(bioEntity);
    }
}
