package maestro.dto;

import maestro.model.Role;
import maestro.model.User;

import java.util.Set;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userId;

    private String fullName;

    private String email;

    private Boolean isBanned;

    private Set<Role> roles;

    public UserResponseDTO() {
    }

    public UserResponseDTO(User user) {
        this.userId = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
