package com.example.chatapp.service;

import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final String[] colors = {"#000000", "#0000FF", "#008000", "#008080", "#00FF00", "#00FFFF", "#FF0000", "#FF00FF", "#FFFF00"};

    //creation et recuperation d'un user
    public User createOrGetUser(String username) {
        var existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            var user = existingUser.get();
            user.setOnline(true);
            user.setLastSeen(LocalDateTime.now());

            return userRepository.save(user);
        }
        var randomColor = colors[new Random().nextInt(colors.length)];
        var newUser = new User(username, randomColor);
        newUser.setOnline(true);

        return userRepository.save(newUser);
    }

    //chercher un user dans le repositorty
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    //recuperer les user online
    public List<User> getOnlineTrue() {
        return userRepository.findByIsOnlineTrue();
    }

    //pour indiquer que le user est ofline
    public void setUserOffline(User user) {
        user.setOnline(false);
        userRepository.save(user);
    }

    //voir le user existe
    public boolean existingUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
