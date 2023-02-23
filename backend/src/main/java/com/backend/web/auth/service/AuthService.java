package com.backend.web.auth.service;

import com.backend.web.auth.dto.AuthDTO;
import com.backend.web.user.entity.User;
import com.backend.web.user.repository.UserRepository;
import com.backend.common.model.CustomException;
import com.backend.common.model.StatusCode;
import com.backend.common.util.CheckUtil;
import com.backend.common.util.JwtUtil;
import com.backend.config.security.JwtAuthenticationResponse;
import com.backend.config.security.JwtProvider;
import com.backend.config.security.JwtValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public JwtAuthenticationResponse signIn(AuthDTO.Create signInInfo) throws CustomException {
        if (CheckUtil.isEmptyString(signInInfo.getLoginId())) {
            throw new CustomException(StatusCode.CODE_601);
        } else if (CheckUtil.isEmptyString(signInInfo.getPassword())) {
            throw new CustomException(StatusCode.CODE_603);
        }
        User user = userRepository.findByLoginId(signInInfo.getLoginId());
        if (user == null) {
            throw new CustomException(StatusCode.CODE_605);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean isMatch = passwordEncoder.matches(signInInfo.getPassword(), user.getPassword());
            if (isMatch) {
                return getJwtAuthenticationResponse(signInInfo, user);
            } else {
                throw new CustomException(StatusCode.CODE_605);
            }
        }

    }

    private JwtAuthenticationResponse getJwtAuthenticationResponse(AuthDTO.Create signInInfo, User user) {
        Long userIdx = user.getIdx();
        String accessToken = jwtProvider.createAccessToken(String.valueOf(userIdx));
        String refreshToken = jwtProvider.createRefreshToken(String.valueOf(userIdx));
        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }

    @Transactional(readOnly = true)
    public JwtAuthenticationResponse getNewAccessToken(HttpServletRequest request) throws CustomException {
        String jwt = JwtUtil.getJwtFromRequest(request);
        JwtValidation jwtValidation = jwtProvider.validateToken(jwt);
        if (jwt == null) {
            throw new CustomException(StatusCode.CODE_652);
        } else if (jwtValidation.isSuccess()) {
            if (jwtValidation.isRefreshToken()) {
                Long userIdx = jwtProvider.getUserIdxFromJwt(jwt);
                String newAccessToken = jwtProvider.createAccessToken(String.valueOf(userIdx));
                return new JwtAuthenticationResponse(newAccessToken);
            } else {
                throw new CustomException(StatusCode.CODE_654);
            }
        } else {
            throw new CustomException(StatusCode.CODE_652);
        }
    }

    @Transactional
    public JwtAuthenticationResponse refreshToken(HttpServletRequest request) throws CustomException {
        String jwt = JwtUtil.getJwtFromRequest(request);
        JwtValidation jwtValidation = jwtProvider.validateToken(jwt);
        if (jwt == null) {
            throw new CustomException(StatusCode.CODE_652);
        } else if (jwtValidation.isSuccess()) {
            Long userIdx = jwtProvider.getUserIdxFromJwt(jwt);
            String newAccessToken = jwtProvider.createAccessToken(String.valueOf(userIdx));
            String newRefreshToken = jwtProvider.createRefreshToken(String.valueOf(userIdx));
            return new JwtAuthenticationResponse(newAccessToken, newRefreshToken);
        } else {
            throw new CustomException(StatusCode.CODE_652);
        }
    }
}
