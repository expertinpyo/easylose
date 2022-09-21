package com.easylose.backend.api.v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easylose.backend.api.v1.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByRefreshJws(String refreshJws);

  List<User> findByProviderIdAndProvider(String providerId, String provider);
}
