package ru.bogdsvn.profile.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bogdsvn.profile.saga.ProfileSaga;
import ru.bogdsvn.profile.store.repositories.ProfileRepository;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileSaga profileSaga;

    public void deactivateProfile(long id) {
        profileSaga.startSaga(id);
    }
}
