package pe.upc.trackmyroute.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    public static UserDetailsImpl build(User user) {
        var authoritiesRoles =  user.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), authoritiesRoles);
    }
}
