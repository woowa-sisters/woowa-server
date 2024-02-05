package com.woowaSisters.woowaSisters.domain.userInfo;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_info_uuid", columnDefinition = "BINARY(16)")
    private UUID userInfoUuid;

    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "소설")
    private Boolean novel;


    @Column(name = "시_에세이")
    private Boolean essay;

    @Column(name = "경제_경영")
    private Boolean economics_management;


    @Column(name = "자기계발")
    private Boolean self_development;


    @Column(name = "인문")
    private Boolean humanities;


    @Column(name = "역사_문화")
    private Boolean history_culture;


    @Column(name = "사회")
    private Boolean society;


    @Column(name = "과학")
    private Boolean science;


    @Column(name = "예술_대중문화")
    private Boolean art_popular_culture;

    @Column(name = "종교")
    private Boolean religion;


    @Column(name = "외국어")
    private Boolean foreign_language;


    @Column(name = "기술_공학")
    private Boolean technology_engineering;


    @Column(name = "컴퓨터_IT")
    private Boolean computer_IT;


    @Column(name = "취미_스포츠")
    private Boolean hobby_sports;


    @Column(name = "건강_다이어트")
    private Boolean health_diet;


    @Column(name = "가정_육아")
    private Boolean family_parenting;


    @Column(name = "요리")
    private Boolean cooking;


    @Column(name = "여행")
    private Boolean travel;


    @Column(name = "교재_수험서")
    private Boolean textbooks_exam_prep;


    @Column(name = "커리어_수험서")
    private Boolean career_exam_prep;


    @Column(name = "청소년")
    private Boolean youth;


    @Column(name = "어린이")
    private Boolean children;


    @Column(name = "만화")
    private Boolean comics;


    @Column(name = "잡지")
    private Boolean magazine;


    @Column(name = "해외도서")
    private Boolean foreign_books;

    
    @Column(name = "오디오북")
    private Boolean audiobook;

}
