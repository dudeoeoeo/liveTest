package com.rest.live.web;

import com.rest.live.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;
    private final HttpSession httpSession;

    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<?> followUser(@PathVariable long toUserId) {
        if(httpSession.getAttribute("id") != null) {
            long fromUserId = (long) httpSession.getAttribute("id");
            followService.follow(fromUserId, toUserId);
            return new ResponseEntity<>("팔로우 되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<>("로그인 하지 않았거나 유효하지 않은 팔로우 아이디 입니다.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/follow/{toUserId}")
    public ResponseEntity<?> unFollowUser(@PathVariable long toUserId) {
        if(httpSession.getAttribute("id") != null) {
            long fromUserId = (long) httpSession.getAttribute("id");
            followService.unFollow(fromUserId, toUserId);
            return new ResponseEntity<>("팔로우가 취소 되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<>("로그인 하지 않았거나 유효하지 않은 팔로우 아이디 입니다.", HttpStatus.BAD_REQUEST);
    }
}
