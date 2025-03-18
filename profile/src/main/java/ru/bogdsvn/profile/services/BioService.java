package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.kafka_library.utils.Status;
import ru.bogdsvn.profile.dtos.BioDto;
import ru.bogdsvn.profile.dtos.ProfileStatusDto;
import ru.bogdsvn.profile.errors.BadRequestException;
import ru.bogdsvn.profile.errors.NotFoundException;
import ru.bogdsvn.profile.factories.BioFactory;
import ru.bogdsvn.profile.store.entites.BioEntity;
import ru.bogdsvn.profile.store.repositories.BioRepository;
import ru.bogdsvn.profile.utils.Gender;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BioService {
    private final BioFactory bioFactory;
    private final BioRepository bioRepository;

    @Transactional
    public BioDto fillBio(BioDto bioDto, Long id) {
        bioRepository
                .findById(id)
                .ifPresent((bioEntity) -> {
                    throw new BadRequestException("Профиль уже заполнен");
                });

        BioEntity bioEntity =  BioEntity
                .builder()
                .id(id)
                .name(bioDto.getName())
                .age(bioDto.getAge())
                .description(bioDto.getDescription())
                .gender(Gender.valueOf(bioDto.getGender()))
                .build();

        bioRepository.save(bioEntity);

        return bioFactory.makeBioDto(bioEntity);
    }

    @Transactional
    public void updateBio(BioDto bioDto, long id) {
        BioEntity bioEntity = bioRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Информация о пользователе не заполнена"));

        bioEntity.setAge(bioDto.getAge());
        bioEntity.setDescription(bioDto.getDescription());
        bioEntity.setGender(Gender.valueOf(bioDto.getGender()));
        bioEntity.setName(bioDto.getName());

        bioRepository.save(bioEntity);
    }

    public BioDto getBio(long id) {
        return bioFactory.makeBioDto(
                bioRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Информация о пользователе не заполнена"))
        );
    }

    public ProfileStatusDto getStatus(long id) {
        return ProfileStatusDto.builder()
                .status(
                        bioRepository
                                .getStatus(id)
                                .orElse(null)
                ).build();
    }

    @Transactional
    public void setStatus(long id, Status status) {
        bioRepository
                .findById(id)
                .ifPresent((p) -> {
                    if (p.getStatus().equals(Status.DEACTIVATED) && status.equals(Status.DEACTIVATE_PROCESSED)) {
                        throw new BadRequestException("Профиль выключен");
                    } else if (p.getStatus().equals(Status.ACTIVATED) && status.equals(Status.ACTIVATE_PROCESSED)) {
                        throw new BadRequestException("Профиль включен");
                    }
                    p.setStatus(status);
                });
    }
}
