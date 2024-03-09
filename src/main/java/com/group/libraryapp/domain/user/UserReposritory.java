package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposritory extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

}
