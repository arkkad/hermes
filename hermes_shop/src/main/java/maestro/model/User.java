package maestro.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

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

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
                Objects.equals(roles, user.roles);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, fullName, isEmailVerified, dateJoined, verificationCode, isActive, roles);
    }

    public static class Builder {
        private UUID id;
        private String email;
        private String password;
        private String fullName;
        private boolean isEmailVerified;
        private LocalDateTime dateJoined;
        private String verificationCode;
        private boolean isActive;
        private Set<Role> roles = new HashSet<>();

        public Builder() {
        }

        public Builder(User user) {
            this.id = user.id;
            this.email = user.email;
            this.password = user.password;
            this.fullName = user.fullName;
            this.isEmailVerified = user.isEmailVerified;
            this.dateJoined = user.dateJoined;
            this.verificationCode = user.verificationCode;
            this.isActive = user.isActive;
            this.roles = user.roles;
        }

        public User build(){
            User user = new User();
            user.id = id;
            user.email = email;
            user.password = password;
            user.fullName = fullName;
            user.isEmailVerified = isEmailVerified;
            user.dateJoined = dateJoined;
            user.verificationCode = verificationCode;
            user.isActive = isActive;
            user.roles = roles;
            return user;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public boolean isEmailVerified() {
            return isEmailVerified;
        }

        public void setEmailVerified(boolean emailVerified) {
            isEmailVerified = emailVerified;
        }

        public LocalDateTime getDateJoined() {
            return dateJoined;
        }

        public void setDateJoined(LocalDateTime dateJoined) {
            this.dateJoined = dateJoined;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }
    }
}
