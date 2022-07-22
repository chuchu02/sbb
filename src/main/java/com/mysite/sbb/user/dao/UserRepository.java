package com.mysite.sbb.user.dao;

import com.mysite.sbb.user.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
}