package com.muzik.logindb.DTO;

import com.muzik.logindb.Constant.RoleType;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LoginDTO {

    private Integer id;

    private String loginid;

    private String password;

    private String username;

    private RoleType roleType;

}