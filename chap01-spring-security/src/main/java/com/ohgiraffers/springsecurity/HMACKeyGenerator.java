package com.ohgiraffers.springsecurity;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

// JWT 비밀키 생성 > 서명 들어가야 한다 > 비밀키는 정해진 알고리즘에 키를 생성한다.
// 비밀키 생성 위한 클래스.
// 서명에 사용할 키값, 이 클래스만 따로 실행해서 생성함

public class HMACKeyGenerator {

    public static void main(String[] args) {
        try {
            /* HS512를 위한 KeyGenerator 생성 */
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
            keyGen.init(512); // 512비트 키 사이즈 설정

            /* 비밀 키 생성 */
            SecretKey secretKey = keyGen.generateKey();

            /* 키를 Base64로 인코딩하여 문자열로 변환 */
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            System.out.println("HS512 Key: " + encodedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}