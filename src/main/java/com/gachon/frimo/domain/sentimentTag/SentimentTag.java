package com.gachon.frimo.domain.sentimentTag;

import java.io.Serializable;

import com.gachon.frimo.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.sentimentTag.SentimentTag;

import javax.persistence.*;


@Getter
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sentiment_tag")
public class SentimentTag extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "sent_pk")
    private Long sentPk;

    @Column(name="sent_large_id")
    private int sentLargeId;

    @Column(name ="sent_small_id", length=10)
    private String sentSmallId;

    @Builder
    public SentimentTag(int sentLargeId, String sentSmallId){
        this.sentLargeId = sentLargeId;
        this.sentSmallId = sentSmallId;
    }
}
