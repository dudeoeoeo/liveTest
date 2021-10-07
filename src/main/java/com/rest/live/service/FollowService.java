package com.rest.live.service;

import com.rest.live.domain.follow.Follow;
import com.rest.live.domain.follow.FollowDto;
import com.rest.live.domain.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void follow(long fromUserId, long toUserId) {
        if(followRepository.findFollowByFromUserIdAndToUserId(fromUserId, toUserId) != null) throw new RuntimeException("이미 팔로우 중입니다.");
        followRepository.follow(fromUserId, toUserId);
    }

    @Transactional
    public void unFollow(long fromUserId, long toUserId) {
        followRepository.unFollow(fromUserId, toUserId);
    }

    @Transactional
    public List<FollowDto> getFollowing(long user_id) {
        List<FollowDto> followings = followRepository.getFollowing(user_id);
        return followings;
    }

    @Transactional
    public List<FollowDto> getFollower(long user_id) {
        List<FollowDto> followers = followRepository.getFollower(user_id);
        return followers;
    }
}
