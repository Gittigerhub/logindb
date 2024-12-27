package com.muzik.logindb.Service;

import com.muzik.logindb.Entity.LoginEntity;
import com.muzik.logindb.Repository.LoginRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 회원 삽입, 수정, 삭제 다른 클래스에서 작업
// 로그인 클래스는 독립적으로 관리
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginRepository loginRepository;
    private final ModelMapper modelMapper;

    // UserDetailsService 추상 클래스로 구성되어 있다.
    // 오버드라이브로 특정 메소드를 사용자가 원하는 내용으로 변경해서 적용
    // throws UsernameNotFoundException 사용자 이름이 없으면 예외처리 발생

    @Override
    public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {

        // 입력받은 userid로 해당 사용자가 존재하는지를 확인
        Optional<LoginEntity> loginEntity = loginRepository.findByLoginid(loginid);

        if (loginEntity.isPresent()) {              // userid가 존재하면
            // 데이터베이스에 있는 회원정보를 가지고 로그인 처리
            return User.withUsername(loginEntity.get().getLoginid())
                    .password(loginEntity.get().getPassword())
                    .roles(loginEntity.get().getRoleType().name())
                    .build();

        } else {                                    // userid가 존재하지 않으면, 오류발생
            throw new UsernameNotFoundException(loginid+"가 존재하지 않습니다.");

        }

    }

}