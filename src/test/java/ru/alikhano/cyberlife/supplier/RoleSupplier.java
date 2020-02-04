package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.RoleDTO;
import ru.alikhano.cyberlife.model.Role;

public class RoleSupplier {

    private static final String  ADMIN_ROLE    = "ADMIN";
    private static final String  USER_ROLE     = "USER";
    private static final Integer ADMIN_ROLE_ID = 1;
    private static final Integer USER_ROLE_ID  = 2;

    public static Role getAdminRole() {
        return new Role(ADMIN_ROLE_ID, ADMIN_ROLE);
    }

    public static RoleDTO getAdminRoleDTO() {
        return new RoleDTO(ADMIN_ROLE_ID, ADMIN_ROLE);
    }

    public static Role getUserRole() { return new Role(USER_ROLE_ID, USER_ROLE); }

    public static RoleDTO getUserRoleDTO() { return new RoleDTO(USER_ROLE_ID, USER_ROLE); }
}
