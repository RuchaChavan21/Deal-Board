package com.dealboard.org.service;

import com.dealboard.org.entity.Organization;
import com.dealboard.org.entity.OrganizationMember;
import com.dealboard.org.repository.OrganizationMemberRepository;
import com.dealboard.org.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository orgRepo;
    private final OrganizationMemberRepository memberRepo;

    public OrganizationService(
            OrganizationRepository orgRepo,
            OrganizationMemberRepository memberRepo) {
        this.orgRepo = orgRepo;
        this.memberRepo = memberRepo;
    }

    public Organization createOrganization(
            Long userId,
            String name,
            String description) {

        Organization org = new Organization(name, description);
        Organization savedOrg = orgRepo.save(org);

        // creator becomes ADMIN
        OrganizationMember member =
                new OrganizationMember(userId, savedOrg.getId(), "ADMIN");

        memberRepo.save(member);

        return savedOrg;
    }

    public List<OrganizationMember> getUserOrganizations(Long userId) {
        return memberRepo.findByUserId(userId);
    }

    public void addMember(
            Long orgId,
            Long userId,
            String role) {

        OrganizationMember member =
                new OrganizationMember(userId, orgId, role);

        memberRepo.save(member);
    }

    // inside OrganizationService.java

    public Organization getOrganizationById(Long id) {
        // Assuming you have a repository.
        // If you use JpaRepository, it's findById(id).orElse(null);
        return orgRepo.findById(id).orElse(null);
    }

    public List<Organization> getAllOrganizations() {
        return orgRepo.findAll();
    }

}
