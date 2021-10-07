package com.rest.live.service;

import com.rest.live.domain.follow.FollowDto;
import com.rest.live.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final FollowService followService;

    public User join(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        user.setRole(Role.USER);
        User user1 = userRepository.save(user);
        return user1;
    }

    public User join(Map<String, Object> map) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Map<String, String> userMap = (Map<String, String>) map.get("user");
        Map<String, String> addrMap = (Map<String, String>) map.get("address");
        Address address = new Address(addrMap.get("zipcode"), addrMap.get("city"), addrMap.get("dong"));
        User user = User.builder()
                .name(userMap.get("name"))
                .email(userMap.get("email"))
                .password(passwordEncoder.encode(userMap.get("password")))
                .phone(userMap.get("phone"))
                .address(address)
                .role(Role.ADMIN)
                .build();

        User user1 = userRepository.save(user);
        return user1;
    }

    public User LoginUser(User user) {
        User loginUser = userRepository.findByUserByEmail(user.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            return loginUser;
        }
        return null;
    }

    public MypageUserResponse findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        List<FollowDto> followings = followService.getFollowing(id);
        List<FollowDto> followers = followService.getFollower(id);

        MypageUserResponse mypage = new MypageUserResponse(user, followers, followings);
        return mypage;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User is not found => " + userEmail));
        return null;
    }
}
