package com.rest.live.domain.user;

import com.rest.live.domain.BaseTimeEntity;
import com.rest.live.domain.follow.Follower;
import com.rest.live.domain.follow.Following;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String phone;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "following_user_id", fetch = FetchType.EAGER)
    private List<Following> following;

//    @OneToMany(mappedBy = "follower_user_id", fetch = FetchType.EAGER)
//    private List<Follower> follower;

    @Builder
    public User(String name, String email, String password, String phone, Address address, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", email: " +
                this.email + ", phone: " + this.phone + ", address: " +
                this.address.toString();
    }
}
