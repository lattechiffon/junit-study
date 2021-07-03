package com.lattechiffon.junit.mockito;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long idx;

    @Setter
    private String username;

    @Setter
    private String email;

    @Setter
    private List<String> roles;

}
