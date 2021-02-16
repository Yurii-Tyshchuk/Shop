package ru.skillsad.sad.domain.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skillsad.sad.domain.BaseEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "usrs")
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customers_role", joinColumns = @JoinColumn(name = "customers_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public User(String name, String password, Set<Roles> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getName();
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
