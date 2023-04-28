package com.example.test_security.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    GUEST(Set.of(Permission.PERSON_READ)),
    USER(Set.of(Permission.PERSON_CREATE, Permission.PERSON_READ)),
    MODERATOR(Set.of(Permission.PERSON_CREATE,
                                Permission.PERSON_READ,
                                Permission.PERSON_UPDATE)),
    ADMIN(Set.of(Permission.PERSON_CREATE,
                            Permission.PERSON_READ,
                            Permission.PERSON_UPDATE,
                            Permission.PERSON_DELETE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
