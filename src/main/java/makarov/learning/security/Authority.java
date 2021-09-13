package makarov.learning.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    AUTH1("auth1"),
    AUTH2("auth2"),
    AUTH3("auth3");
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
