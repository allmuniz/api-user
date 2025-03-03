package com.allmuniz.api_user.domain.repository;

import com.allmuniz.api_user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
