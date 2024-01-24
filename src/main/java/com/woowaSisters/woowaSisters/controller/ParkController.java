package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.service.ParkService;
import com.woowaSisters.woowaSisters.vo.ParkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("/api/parkInfo")
@RequiredArgsConstructor
public class ParkController {

    @Autowired
    private final ParkService parkService;

    @Autowired
    private final RestTemplate restTemplate;
    @GetMapping("/parkList")
    public List<ParkVO> listPark() {
        List<Parks> parkList = parkService.viewParkList();
        List<ParkVO> parkListVO = parkList.stream().map(ParkVO::new).collect(Collectors.toList());

        return parkListVO;
    }

    @GetMapping("")
    public ResponseEntity<List<ParkVO>> searchPark(@RequestParam List<String> keyword) {
        String flaskApiUrl = "http://localhost:8000/data?keyword=" + String.join(",", keyword);

        try {
            ResponseEntity<Map<String, List<String>>> flaskResponse = restTemplate.exchange(
                    flaskApiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<String>>>() {}
            );

            List<String> uuidList = flaskResponse.getBody().get("park_uuid");

            List<UUID> parkUuidList = uuidList.stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());

            List<Parks> parkList = parkService.searchParkList(parkUuidList); // 수정: 공원 정보 가져오기

            List<ParkVO> parkVOList = parkList.stream().map(ParkVO::new).collect(Collectors.toList());

            return ResponseEntity.ok(parkVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}