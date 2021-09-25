package makarov.learning.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    user("user"),
    manager("manager"),
    admin("admin");
    private final String value;

    Authority(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
