package com.gachon.frimo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserPk(Long userPk);

    public Optional<User> findByUserId(String userId);

    public User findByUserNN(String userNN);

    public boolean existsByUserNN(String userNN);

    public void deleteByUserPk(Long userPk);

}