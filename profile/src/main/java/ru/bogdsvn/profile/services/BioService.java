package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.profile.dtos.BioDto;
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
    public BioDto fillBio(BioDto bioDto, String id) {
        ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        BioEntity bioEntity =  BioEntity
                .builder()
                .name(bioDto.getName())
                .age(bioDto.getAge())
                .description(bioDto.getDescription())
                .gender(Gender.valueOf(bioDto.getGender()))
                .build();
        bioEntity.setProfile(profileEntity);
        bioRepository.save(bioEntity);

        return bioFactory.makeBioDto(bioEntity);
    }
}
