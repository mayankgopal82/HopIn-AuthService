package AuthService.AuthService.DTO;

import AuthService.AuthService.Enum.Role;
import AuthService.AuthService.Enum.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupPassengerRequestDto {

    @NotNull
    @NotEmpty
    private String email;
    private String mobile;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String firstName;
    private String lastName;

    private Role role;

    public @NotNull @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @NotEmpty String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public @NotNull @NotEmpty String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @NotEmpty String firstName) {
        this.firstName = firstName;
    }

    public @NotNull @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotEmpty String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

}
