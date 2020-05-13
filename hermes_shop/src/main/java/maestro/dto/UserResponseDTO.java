package maestro.dto;

import maestro.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userId;

    private String username;


    private Boolean isBanned;

    private Set<String> roles;

    public UserResponseDTO() {
    }

    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
