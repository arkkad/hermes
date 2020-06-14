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
public class User extends BaseEntity {


    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Column(name = "verification_code")
    private String verificationCode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private UserContact userContact;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(isEmailVerified, user.isEmailVerified) &&
                Objects.equals(verificationCode, user.verificationCode) &&
                Objects.equals(userContact, user.userContact) &&
                Objects.equals(cart, user.cart) &&
                Objects.equals(roles, user.roles);
    }


    @Override
    public int hashCode() {
        return Objects.hash(email, password, fullName, isEmailVerified, verificationCode, roles, username);
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public UserContact getUserContact() {
        return userContact;
    }

    public void setUserContact(UserContact userContact) {
        this.userContact = userContact;
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


        public Builder withisEmailVerified(boolean verified) {
            user.isEmailVerified = verified;
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

        public Builder withCart(Cart cart) {
            user.cart = cart;
            return this;
        }


        public Builder withRoles(Set<Role> roles) {
            user.roles = roles;
            return this;
        }


        public User build() {
            return user;
        }
    }
}
