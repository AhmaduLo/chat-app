package com.example.chatapp.repository;

import com.example.chatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

//    Récupère tous les utilisateurs dont l’attribut isonline vaut true.
//    Nécessite que dans mon entité User, tu aies : private boolean isOnline;
    List<User> findByIsOnlineTrue();

    boolean existsByUsername(String username);
}
