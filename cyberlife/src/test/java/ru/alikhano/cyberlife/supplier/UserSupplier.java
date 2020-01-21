package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.model.User;

import java.util.Collections;

public class UserSupplier {

    private static final Integer TEST_USER_ID = 1;
    private static final String TEST_USERNAME = "user";
    private static final String TEST_PASSWORD = "1234";
    private static final String TEST_PASSWORD_ENCODED = "$2a$11$CGbN7TL4PbIg3nnRzRCPjOJPGl2e2enng/xSbf1SQ.WuKWCfTLtF6";

    public static User getAdminUser() {
        User adminUser = new User();
        adminUser.setUserId(TEST_USER_ID);
        adminUser.setUsername(TEST_USERNAME);
        adminUser.setPassword(TEST_PASSWORD);
        adminUser.setEnabled(true);
        adminUser.setRoles(Collections.singleton(RoleSupplier.getAdminRole()));

        return adminUser;
    }

    public static UserDTO getAdminUserDTO() {
        UserDTO adminUser = new UserDTO();
        adminUser.setUserId(TEST_USER_ID);
        adminUser.setUsername(TEST_USERNAME);
        adminUser.setPassword(TEST_PASSWORD_ENCODED);
        adminUser.setEnabled(true);
        adminUser.setRoles(Collections.singleton(RoleSupplier.getAdminRoleDTO()));

        return adminUser;
    }
}
