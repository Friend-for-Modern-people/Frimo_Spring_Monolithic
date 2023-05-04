package com.gachon.frimo.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;
import com.gachon.frimo.domain.diary.Diary;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Component
public class User extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_pk")
    private Long userPk;

    @Column(length = 100, name = "user_id")
    private String userId;

    @Column(length =32, name="user_nn")
    private String userNN;
    
    // @OneToMany(fetch = FetchType.LAZY , mappedBy = "author", cascade = CascadeType.ALL)
    // private List<Diary> diaries;

    @Builder
    public User(String userId, String userNN) {
        this.userId = userId;
        this.userNN = userNN;
    }

}
