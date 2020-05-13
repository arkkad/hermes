package maestro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID id;

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
                Objects.equals(isEmailVerified, user.isEmailVerified) &&
                Objects.equals(dateJoined, user.dateJoined) &&
                Objects.equals(verificationCode, user.verificationCode) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(username, user.username);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, fullName, isEmailVerified, dateJoined, verificationCode, isActive, roles, username);
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

        public Builder withRoles(Set<String> roles) {
            user.roles = roles;
            return this;
        }


        public User build() {
            return user;
        }
    }
}
//@Entity
//@Table(name = "users")
//public class User implements Serializable, UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Type(type = "uuid-char")
//    @Column(name = "id", length = 36, nullable = false, updatable = false)
//    private UUID id;
//
//    @Column(name = "full_name", nullable = false)
//    private String fullName;
//
//    @Column(name = "username", nullable = false)
//    private String username;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Size(min = 2, message = "More that 5 char.")
//    private String password;
//
//    @Column(name = "isActive", nullable = false)
//    private boolean isActive;
//
//    @Column(name = "is_email_verified")
//    private boolean isEmailVerified;
//
//    @Column(name = "date_joined")
//    private LocalDateTime dateJoined;
//
//    @Column(name = "verification_code")
//    private String verificationCode;
//
//    @Fetch(FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false))
//    private Set<Role> roles = new HashSet<>();
//
//    public User() {
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public boolean isEmailVerified() {
//        return isEmailVerified;
//    }
//
//    public void setEmailVerified(boolean emailVerified) {
//        isEmailVerified = emailVerified;
//    }
//
//    public LocalDateTime getDateJoined() {
//        return dateJoined;
//    }
//
//    public void setDateJoined(LocalDateTime dateJoined) {
//        this.dateJoined = dateJoined;
//    }
//
//    public String getVerificationCode() {
//        return verificationCode;
//    }
//
//    public void setVerificationCode(String verificationCode) {
//        this.verificationCode = verificationCode;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return isActive == user.isActive &&
//                Objects.equals(id, user.id) &&
//                Objects.equals(email, user.email) &&
//                Objects.equals(password, user.password) &&
//                Objects.equals(fullName, user.fullName) &&
//                Objects.equals(isEmailVerified, user.isEmailVerified) &&
//                Objects.equals(dateJoined, user.dateJoined) &&
//                Objects.equals(verificationCode, user.verificationCode) &&
//                Objects.equals(roles, user.roles);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, password, fullName, isEmailVerified, dateJoined, verificationCode, isActive, roles);
//    }
//
//    public static class Builder {
//        private UUID id;
//        private String email;
//        private String password;
//        private String fullName;
//        private boolean isEmailVerified;
//        private LocalDateTime dateJoined;
//        private String verificationCode;
//        private boolean isActive;
//        private Set<Role> roles = new HashSet<>();
//
//        public Builder() {
//        }
//
//        public Builder(User user) {
//            this.id = user.id;
//            this.email = user.email;
//            this.password = user.password;
//            this.fullName = user.fullName;
//            this.isEmailVerified = user.isEmailVerified;
//            this.dateJoined = user.dateJoined;
//            this.verificationCode = user.verificationCode;
//            this.isActive = user.isActive;
//            this.roles = user.roles;
//        }
//
//        public User build() {
//            User user = new User();
//            user.id = id;
//            user.email = email;
//            user.password = password;
//            user.fullName = fullName;
//            user.isEmailVerified = isEmailVerified;
//            user.dateJoined = dateJoined;
//            user.verificationCode = verificationCode;
//            user.isActive = isActive;
//            user.roles = roles;
//            return user;
//        }
//
//        public UUID getId() {
//            return id;
//        }
//
//        public void setId(UUID id) {
//            this.id = id;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public String getFullName() {
//            return fullName;
//        }
//
//        public void setFullName(String fullName) {
//            this.fullName = fullName;
//        }
//
//        public boolean isEmailVerified() {
//            return isEmailVerified;
//        }
//
//        public void setEmailVerified(boolean emailVerified) {
//            isEmailVerified = emailVerified;
//        }
//
//        public LocalDateTime getDateJoined() {
//            return dateJoined;
//        }
//
//        public void setDateJoined(LocalDateTime dateJoined) {
//            this.dateJoined = dateJoined;
//        }
//
//        public String getVerificationCode() {
//            return verificationCode;
//        }
//
//        public void setVerificationCode(String verificationCode) {
//            this.verificationCode = verificationCode;
//        }
//
//        public boolean isActive() {
//            return isActive;
//        }
//
//        public void setActive(boolean active) {
//            isActive = active;
//        }
//
//        public Set<Role> getRoles() {
//            return roles;
//        }
//
//        public void setRoles(Set<Role> roles) {
//            this.roles = roles;
//        }
//    }
//}
