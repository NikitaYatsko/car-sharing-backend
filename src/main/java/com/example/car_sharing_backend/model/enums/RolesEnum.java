package com.example.car_sharing_backend.model.enums;

public enum RolesEnum {
    ADMIN,
    USER,
    ;

    public String asAuthority() {
        return "ROLE_" + name();
    }
}
