package com.maciek.streamDemo.persistence.repo;

import com.maciek.streamDemo.persistence.entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroups, Integer> {
}
