package com.dealboard.org.repository;

import com.dealboard.org.entity.OrganizationMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationMemberRepository
        extends JpaRepository<OrganizationMember, Long> {

    List<OrganizationMember> findByUserId(Long userId);
}
