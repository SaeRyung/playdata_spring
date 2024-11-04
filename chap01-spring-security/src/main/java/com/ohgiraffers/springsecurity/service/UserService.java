package com.ohgiraffers.springsecurity.service;

import com.ohgiraffers.springsecurity.aggregate.UserEntity;
import com.ohgiraffers.springsecurity.dto.CreateUserRequest;
import com.ohgiraffers.springsecurity.dto.UserInfoResponse;
import com.ohgiraffers.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    // UserDetailsService 타입 변경해서 사용 -> loadUserByUsername 호출된다.

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserRequest newUser) {
        UserEntity user = modelMapper.map(newUser, UserEntity.class);
        // modelMapper 이용해서 DTO > Entity 변환
        user.encryptPassword(bCryptPasswordEncoder.encode(newUser.getPwd()));
        // bCryptPasswordEncoder: 비밀번호 문자열 인코딩, 본인만의 규칙을 가지고 인코딩한다. 랜덤이므로 그때마다 다른 문자열 들어간다.
        userRepository.save(user);
    }

    /* 로그인 요청 시 AuthenticationManager를 통해서 호출 될 메소드*/
    // 로그인 성공했을때
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        /* 인증 토큰에 담긴 userId가 메소드로 넘어오므로 해당 값을 기준으로 DB에서 조회 한다. */
        // userId 기준으로 DB에서 조회한다.
        // 3. UserDetailService
        UserEntity loginUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(loginUser.getUserRole().name()));
        // 아래 코드 User 권한 맞춰주기 위해, collection

        return new User(loginUser.getUserId(), loginUser.getPwd(), grantedAuthorities);
        // passEncoder 체킹, DB에서 조회된 결과를 담아서 보내준다.
        // id. pwd, 권한(권한 여러개 가질 수 있으므로 타입은 보통 collection)
    }

    public UserInfoResponse getUserInfoById(Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow();
        return modelMapper.map(user, UserInfoResponse.class);
    }
}
