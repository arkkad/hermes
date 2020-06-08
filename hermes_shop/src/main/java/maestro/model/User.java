package maestro.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(min = 2, message = "More that 5 char.")
    private String password;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Column(name = "date_joined")
    private LocalDateTime dateJoined;

    @Column(name = "verification_code")
    private String verificationCode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Cart shoppingCart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private UserContact userContact;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive &&
                Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(isEmailVerified, user.isEmailVerified) &&
                Objects.equals(dateJoined, user.dateJoined) &&
                Objects.equals(verificationCode, user.verificationCode) &&
                Objects.equals(userContact, user.userContact) &&
                Objects.equals(shoppingCart, user.shoppingCart) &&
                Objects.equals(roles, user.roles);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, fullName, isEmailVerified, dateJoined, verificationCode, isActive, roles, username);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public UserContact getUserContact() {
        return userContact;
    }

    public void setUserContact(UserContact userContact) {
        this.userContact = userContact;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withName(String username) {
            user.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withFullName(String fullname) {
            user.fullName = fullname;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withActive(boolean active) {
            user.isActive = active;
            return this;
        }

        public Builder withisEmailVerified(boolean verified) {
            user.isEmailVerified = verified;
            return this;
        }

        public Builder withDateJoined(LocalDateTime date) {
            user.dateJoined = date;
            return this;
        }

        public Builder withVerificationCode(String vc) {
            user.verificationCode = vc;
            return this;
        }

        public Builder withContact(UserContact contact) {
            user.userContact = contact;
            return this;
        }

        public Builder withShoppingCart(Cart cart) {
            user.shoppingCart = cart;
            return this;
        }


        public Builder withRoles(Set<String> roles) {
            user.roles = roles;
            return this;
        }


        public User build() {
            return user;
        }
    }
}
