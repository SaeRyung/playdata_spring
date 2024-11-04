package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* @RestController : @ResponseBody + @Controller => http 응답데이터(body)에 자바 객체 매핑되어 전달 */
/* @RestController : 모든 핸들러 메소드에 @ResponseBody가 적용된 것과 같다. */
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    /* 1. 문자열 응답 */
    // 문자열 타입은 text
    @GetMapping("/hello")
    public String helloWorld() { return "Hello World!"; }

    // 문자열 외 java object 는 json 타입으로 반환된다.
    /* 2. Object 응답 */
    @GetMapping("/message")
    public Message getMessage() { return new Message(200, "메세지를 응답합니다."); }

    /* 3. List 응답 */
    @GetMapping("/list")
    public List<String> getList() { return List.of(new String[]{"사과", "바나나", "복숭아"}); }

    /* 4. Map 응답 */
    @GetMapping("/map")
    public Map<Integer, String> getMap() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상 응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다."));
        messageList.add(new Message(500, "개발자의 잘못입니다."));
        return messageList.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
    }

    /* 5. file 응답 */
    // produces = MediaType.IMAGE_PNG_VALUE : 응답데이터 타입 설정
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/images/wafflegom.png").readAllBytes();
    }

    /* 6. ResponseEntity 응답 */
    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {
        return ResponseEntity.ok(new Message(200, "hello world!"));
    }

}