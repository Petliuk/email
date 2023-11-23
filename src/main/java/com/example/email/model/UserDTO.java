package com.example.email.model;

import lombok.*;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    Long  id;
    String name;
    String surname;
    String email;
}
