package com.muzik.logindb;

import com.muzik.logindb.Constant.RoleType;
import com.muzik.logindb.Entity.LoginEntity;
import com.muzik.logindb.Repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

// Repository, Service를 주로 확인할 때 사용한다.
// 스프링부트 버전과 상관없이 이 SpringBootTest는 옛날거고 아직까지 갱신이 안되었기 때문에, 옛날 방식으로 사용해야한다.
// 클래스 주입은 옛방식인 Autowired로 적용
// 각 메소드에 Test를 선언해서 동작
@SpringBootTest
public class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;        // 데이터베이스 처리(회원등록)
    @Autowired
    private PasswordEncoder passwordEncoder;        // 암호를 암호화 처리

    @Test
    public void loginInsert() {         // 데이터베이스에 회원가입이 정상적으로 구동되는지 테스트하는 메소드

        // sample1~4번까지 4명을 저장, 비밀번호 1234, 1, 2, 3=USER, 4=ADMIN
        for (int i=1; i<5; i++) {
            // builder는 각 변수에 값을 넣어서 Entity값으로 만들어 준다.
            LoginEntity loginEntity = LoginEntity.builder()
                    .loginid("sample"+i)                                    // sample1, sample2, ..
                    .password(passwordEncoder.encode("1234"))    // 비밀번호 "1234" 암호화
                    .username("홍길동"+i)                                    // 홍길동1, 홍길동2, ..
                    .build();

            // 권한 부여
            if (i==2 || i==4) {         // i가 2또는 4이면, ADMIN을 추가
                loginEntity.setRoleType(RoleType.ADMIN);
            } else {                    // 그렇지 않으면(1, 3일 때) USER를 추가
                loginEntity.setRoleType(RoleType.USER);
            }

            loginRepository.save(loginEntity);          // 데이터베이스 저장

        }

    }
    // 실행은 메소드별로 실행한다.
    // H2 서버는 두개가 구동이 안되기 때문에 다른 H2서버가 틀어져 있다면, 종료 후 테스트를 진행한다.
}