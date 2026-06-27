package com.niladri.userservice.utils;

import java.util.Map;
import java.util.Set;

import com.niladri.domain.UserPermission;
import com.niladri.domain.UserRole;

public class RolePermissionMapping {
    private static final Map<UserRole, Set<UserPermission>> roleBasedPermission = Map.of(UserRole.ROLE_JOB_SEEKER,
            Set.of(UserPermission.JOB_SEARCH),
            UserRole.ROLE_EMPLOYER,
            Set.of(UserPermission.JOB_CREATE, UserPermission.JOB_UPDATE, UserPermission.JOB_DELETE),
            UserRole.ROLE_ADMIN, Set.of(UserPermission.SUSPEND_USER, UserPermission.DELETE_USER,
                    UserPermission.ACTIVATE_USER, UserPermission.VIEW_ALL_USERS));

    public static Set<UserPermission> getPermissionsByRole(UserRole role) {
        return roleBasedPermission.get(role);
    }
}
