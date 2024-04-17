package org.example.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactDTO {

    private Long id;

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be a valid email format")
    private String email;

    @NotEmpty(message = "Subject is mandatory")
    private String subject;

    @NotEmpty(message = "Message is mandatory")
    private String message;
}
