package com.rest.live.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFromUserIdAndToUserId(long from_user_id, long to_user_id);

    @Modifying
    @Query(value = "select u.* from user u where u.id in (select to_user_id FROM follow where from_user_id = ?1)", nativeQuery = true)
    List<FollowDto> getFollowing(long from_user_id);

    @Modifying
    @Query(value = "select u.* from user u where u.id in (select from_user_id FROM follow where to_user_id = ?1)", nativeQuery = true)
    List<FollowDto> getFollower(long to_user_id);

    @Modifying
    @Query(value = "insert into follow(from_user_id, to_user_id) values (?1, ?2)", nativeQuery = true)
    void follow(long fromId, long toId);

    @Modifying
    @Query(value = "delete from follow where from_user_id = ?1 and to_user_id = ?2", nativeQuery = true)
    void unFollow(long fromId, long toId);

}
