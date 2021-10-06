package com.rest.live.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipcode;
    private String city;
    private String dong;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "address")
    private User user_id;

    @Builder
    public Address(String zipcode, String city, String dong) {
        this.zipcode = zipcode;
        this.city = city;
        this.dong = dong;
    }
}
