package com.rest.live.domain.follow;

import com.rest.live.domain.BaseTimeEntity;
import com.rest.live.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser;

    @Builder
    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
