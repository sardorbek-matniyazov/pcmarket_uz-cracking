package basePackage.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MessageDto {
    @NotBlank(message = "Message cannot be empty")
    private String message;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
}
