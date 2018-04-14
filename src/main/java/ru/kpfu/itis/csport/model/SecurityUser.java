package ru.kpfu.itis.csport.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/12/18 10:14 PM
 */
public class SecurityUser implements UserDetails {

    private User user;

    public SecurityUser(User user) {
        this.user = Objects.requireNonNull(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user instanceof Manager
                ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER"))
                : Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
