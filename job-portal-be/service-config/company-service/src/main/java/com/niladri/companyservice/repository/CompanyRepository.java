package com.niladri.companyservice.repository;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.niladri.companyservice.entity.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    boolean existsByOwnerEmail(@NotBlank(message = "Owner email is required") @Email(message = "Invalid owner email format") String ownerEmail);

    Optional<Company> findByOwnerEmail(String ownerEmail);

    Optional<Company> findCompanyByOwnerEmail(String ownerEmail);

    boolean existsByName(@NotBlank(message = "Company name is required") String name);

    boolean existsByRegistrationNumber(@NotBlank(message = "Registration number is required") String registrationNumber);
}
