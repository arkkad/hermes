package maestro.config.sec.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {
    private final Long id;
    private final String username;
    private final String fullName;
    private final String email;
    private final String password;
    private final boolean isEmailVerified;
    private final String verificationCode;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id,
                   String username,
                   String fullName,
                   String email,
                   String password,
                   boolean isEmailVerified,
                   String verificationCode,
                   boolean enabled,
                   Date lastPasswordResetDate,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
