package com.rental.controller;

import com.rental.domain.user.User;
import com.rental.domain.user.dto.userDto;
import com.rental.jwt.JwtFIlter;
import com.rental.jwt.TokenProvider;
import com.rental.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 회원가입
     */
    @PostMapping("/api/user/create")
    public ResponseEntity<userDto.Info> signup(@Valid @RequestBody userDto.create userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    /**
     * 내 정보 조회
     */
    @GetMapping("/api/user/myInfo")
    public userDto.Info getMyUserInfo(@AuthenticationPrincipal UserDetails currentUser) {
        return userService.getMyInfo(currentUser);
    }

    /**
     * 유저 정보 조회
     */
    @GetMapping("/api/user/{userId}")
    public userDto.Info getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    /**
     * 로그인
     */
    @PostMapping("/api/login")
    public ResponseEntity<userDto.token> authorize(@Valid @RequestBody userDto.login loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFIlter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new userDto.token(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * 내 정보 수정
     */
    @PostMapping("/api/user/update")
    public userDto.Info update(@RequestBody userDto.update updateDto, @AuthenticationPrincipal UserDetails currentUser) {
        return userService.updateMyInfo(updateDto, currentUser);
    }

    /**
     * 권한 변경 (판매자 <-> 구매자)
     */
    @PostMapping("/api/user/changeRole")
    public userDto.Info changeMyRole(@AuthenticationPrincipal UserDetails currentUser) {
        return userService.changeRole(currentUser);
    }
}
