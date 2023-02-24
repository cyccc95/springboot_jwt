package com.backend.web.member.controller;

import com.backend.web.member.dto.MemberDTO;
import com.backend.web.member.service.MemberService;
import com.backend.common.model.ApiResponse;
import com.backend.common.model.CustomException;
import com.backend.common.util.ResponseMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/member/signUp")
    public ResponseEntity<ApiResponse> signUp(@RequestBody MemberDTO.SignUp memberInfo) {
        try {
            memberService.createMember(memberInfo);
            return ResponseMessageUtil.successMessage();
        } catch (CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

    @GetMapping("/api/member/{idx}")
    public ResponseEntity<ApiResponse> findByMemberIdx(@PathVariable Long idx) {
        try {
            return ResponseMessageUtil.successMessage(memberService.findByMemberIdx(idx));
        } catch (CustomException ce) {
            return ResponseMessageUtil.errorMessage(ce.getCode());
        } catch (Exception e) {
            return ResponseMessageUtil.errorMessage(e);
        }
    }

}
