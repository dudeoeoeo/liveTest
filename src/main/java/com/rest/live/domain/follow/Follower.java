package com.rest.live.domain.follow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.live.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_user_id", referencedColumnName = "id")
    @JsonIgnore
    private User follower_user_id;
}
