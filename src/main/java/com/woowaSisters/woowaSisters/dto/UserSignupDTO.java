package com.woowaSisters.woowaSisters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignupDTO {
    private String email; // 구글에서 가져온 사용자의 이메일
    private String nickname; // 클라이언트에서 제공하는 닉네임
    private List<GenreSelection> genre; // 클라이언트에서 제공하는 장르 선택

}


