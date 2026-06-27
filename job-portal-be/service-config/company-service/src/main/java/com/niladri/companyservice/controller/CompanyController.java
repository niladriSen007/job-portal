package com.niladri.companyservice.controller;

import com.niladri.companyservice.dto.request.CompanyFilterRequest;
import com.niladri.companyservice.dto.request.CompanyPatchRequest;
import com.niladri.companyservice.dto.request.CompanyRequest;
import com.niladri.companyservice.dto.response.CompanyResponse;
import com.niladri.companyservice.service.ICompanyService;
import com.niladri.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final ICompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest companyRequest) {
        CompanyResponse response = companyService.createCompany(companyRequest,ownerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, HttpStatus.CREATED.value()));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyById(
            @PathVariable Long companyId) {
        return ResponseEntity.ok(ApiResponse.success(companyService.getCompanyById(companyId)));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyByOwnerEmail(
            @RequestHeader("X-User-Email") String ownerEmail) {
        return ResponseEntity.ok(ApiResponse.success(companyService.getCompanyByOwnerEmail(ownerEmail)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CompanyResponse>>> getAllCompanies(
            @ModelAttribute CompanyFilterRequest companyFilterRequest,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(companyService.getAllCompanies(companyFilterRequest, pageable)));
    }

    @PatchMapping("/{companyId}")
    public ResponseEntity<ApiResponse<CompanyResponse>> patchCompany(
            @PathVariable Long companyId,
            @RequestBody @Valid CompanyPatchRequest patchRequest,
            @RequestHeader("X-User-Email") String ownerEmail) {
        return ResponseEntity.ok(ApiResponse.success(companyService.patchCompany(companyId, patchRequest, ownerEmail)));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(
            @PathVariable Long companyId,
            @RequestHeader("X-User-Email") String ownerEmail) {
        companyService.deleteCompany(companyId, ownerEmail);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{companyId}/suspend")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CompanyResponse>> suspendCompany(
            @PathVariable Long companyId) {
        return ResponseEntity.ok(ApiResponse.success(companyService.suspendCompany(companyId)));
    }

    @PatchMapping("/{companyId}/verify")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CompanyResponse>> verifyCompany(
            @PathVariable Long companyId) {
        return ResponseEntity.ok(ApiResponse.success(companyService.verifyCompany(companyId)));
    }
}

