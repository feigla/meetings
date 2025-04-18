package ru.bogdsvn.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.auth.errors.BadRequestException;
import ru.bogdsvn.auth.store.entities.UserEntity;
import ru.bogdsvn.auth.store.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Пользователь с таким именем существует");
        }
        userRepository.save(user);
    }

    @Transactional
    public void reset(String username, String password) {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        user.setVersion(user.getVersion() + 1);
        user.setPassword(password);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
}
