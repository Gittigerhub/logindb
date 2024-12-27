package com.muzik.logindb.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 값을 고정화 처리
@Getter
@AllArgsConstructor
public enum RoleType {

    USER, MANAGER, ADMIN

}
// RoleType(변수명)    :   USER, MANAGER, ADMIN(값)