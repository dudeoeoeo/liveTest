package com.rest.live.domain.user;

import com.rest.live.domain.follow.FollowDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MypageUserResponse {

    private String name;
    private String email;
    private String phone;
    private Address address;
    private String role;

    private List<FollowDto> follower;
    private List<FollowDto> following;

    public MypageUserResponse(User entity, List<FollowDto> follower, List<FollowDto> following) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
        this.role = entity.getRole().getTitle();

        this.follower = follower;
        this.following = following;
    }
}
