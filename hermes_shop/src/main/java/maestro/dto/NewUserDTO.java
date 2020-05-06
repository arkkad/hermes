package maestro.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
