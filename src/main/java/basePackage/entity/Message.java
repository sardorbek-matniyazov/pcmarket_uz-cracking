package basePackage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Message extends ParentEntity{
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Message is required")
    @Column(nullable = false, length = 1000)
    private String message;

    public Message(String name, String email, String message) {
        super(name);
        this.email = email;
        this.message = message;
    }

    public Message(Long id, String name, String email, String message) {
        super(id, name);
        this.email = email;
        this.message = message;
    }
}
