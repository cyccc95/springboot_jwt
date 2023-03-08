package com.backend.web.member.service;

import com.backend.common.model.MemberType;
import com.backend.web.auth.dto.TokenDTO;
import com.backend.web.auth.entity.RefreshToken;
import com.backend.web.auth.repository.RefreshTokenRepository;
import com.backend.web.member.dto.MemberDTO;
import com.backend.web.member.entity.Member;
import com.backend.web.member.repository.MemberRepository;
import com.backend.common.model.CustomException;
import com.backend.common.model.StatusCode;
import com.backend.common.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberDTO.SignUp memberInfo) throws CustomException {
        if (CheckUtil.isEmptyString(memberInfo.getLoginId())) {
            throw new CustomException(StatusCode.CODE_601);
        } else if (CheckUtil.isEmptyString(memberInfo.getNickname())) {
            throw new CustomException(StatusCode.CODE_602);
        } else if (CheckUtil.isEmptyString(memberInfo.getPassword())) {
            throw new CustomException(StatusCode.CODE_604);
        }

        Member member = Member.builder()
                .loginId(memberInfo.getLoginId())
                .nickname(memberInfo.getNickname())
                .email(memberInfo.getEmail())
                .password(passwordEncoder.encode(memberInfo.getPassword()))
                .memberType(MemberType.ROLE_USER)
                .build();
        member.setCreateAt(LocalDateTime.now());
        member.setUpdateAt(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberDTO.Simple findByMemberIdx(Long idx) throws CustomException {
        if(CheckUtil.isNullObject(idx)) {
            throw new CustomException(StatusCode.CODE_701);
        } else {
            Member member = memberRepository.findByIdx(idx);
            return member.toMemberSimpleDTO();
        }
    }

    @Transactional
    public void updateByMemberIdx(Long idx, MemberDTO.Update updateInfo) throws CustomException {
        if (CheckUtil.isEmptyString(updateInfo.getNickname())) {
            throw new CustomException(StatusCode.CODE_602);
        } else if (CheckUtil.isEmptyString(updateInfo.getPassword())) {
            throw new CustomException(StatusCode.CODE_604);
        } else if (CheckUtil.isEmptyString(updateInfo.getEmail())) {
            throw new CustomException(StatusCode.CODE_603);
        }

        Member member = memberRepository.findByIdx(idx);
        member.setNickname(updateInfo.getNickname());
        member.setEmail(updateInfo.getEmail());
        member.setPassword(passwordEncoder.encode(updateInfo.getPassword()));
        member.setUpdateAt(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Transactional
    public void deleteByMemberIdx(Long idx, MemberDTO.Delete deleteInfo) throws CustomException {
        if (CheckUtil.isEmptyString(deleteInfo.getPassword())) {
            throw new CustomException(StatusCode.CODE_604);
        }

        Member member = memberRepository.findByIdx(idx);

        boolean isMatch = passwordEncoder.matches(deleteInfo.getPassword(), member.getPassword());
        if (!isMatch) {
            throw new CustomException(StatusCode.CODE_606);
        } else {
            memberRepository.delete(member);
            refreshTokenRepository.deleteByKey(Long.toString(idx));
        }
    }
}
