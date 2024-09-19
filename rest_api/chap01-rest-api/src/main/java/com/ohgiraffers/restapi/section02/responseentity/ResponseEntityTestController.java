package com.ohgiraffers.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);
        // 성공 시 200 응답

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).findFirst().get();
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(responseMessage);

    }

    // 특정 유저 등록하기
    @PostMapping("/users")
    public ResponseEntity<Void> registUser(@RequestBody UserDTO userDTO) {
        // body에 들어갈 내용 없는 경우 <Void>
        // 이 기반으로 데이터 등록 => client에서 요청 넘어온다: @RequestBody 통해서 넘어오는 데이터 전달받기
        // @RequestBody : client에서 넘어오는 데이터 JSON인 경우 해석 처리.. req, resp 둘다 JSON 형식으로 하려고 한다.

        // DB 활용하지 않으려고 작성하는 코드이다.
        int lastUserNo = users.get(users.size() - 1).getNo();
        userDTO.setNo(lastUserNo + 1);
        userDTO.setEnrollDate(new Date());
        users.add(userDTO);

        return ResponseEntity
                .created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))
                // created : 생성 시 성공 코드 201..."/entity/users/4" 라고 응답에 넣어주었다.
                .build();
    }

    // 특정 유저 수정하기
    @PutMapping("/users/{userNo}")
    //@PathVariable: userNo 받아오기, userDTO: 어떤 내용으로 수정할지, JSON 형태이므로 @RequestBody
    public ResponseEntity<Void> modifyUser(@PathVariable int userNo, @RequestBody UserDTO userDTO) {

        // 메모리상에서 수정
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).findFirst().get();
        foundUser.setPwd(userDTO.getPwd());
        foundUser.setName(userDTO.getName());

        return ResponseEntity.created(URI.create("/entity/users/" + userNo)).build();
        // "created" :201번 코드로 , "URI.create("/entity/users/" + userNo": 접근할 수 있는 주소와 함게 성공을 보낸다.
    }

    // 특정 유저 제거하기
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<Void> removeUser(@PathVariable int userNo) {
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).findFirst().get();
        users.remove(foundUser);

        return ResponseEntity.noContent().build();
        // noContent: 204 상태코드, 리소스 제거 시 제거 확인 응답코드
    }
}