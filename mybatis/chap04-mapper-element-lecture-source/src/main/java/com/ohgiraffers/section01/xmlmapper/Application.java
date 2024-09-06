package com.ohgiraffers.section01.xmlmapper;

import java.util.Scanner;

// Mapper -> resultMap, association, collection 상황 테스트

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ElementTestService elementTestService = new ElementTestService();
        // ㄴ 세가지 테스트를 정의할 테스트 클래스 정의
        do {
            System.out.println("===== Mapper Element 테스트 (ResultMap 테스트) =====");
            System.out.println("1. <resultMap> 테스트 "); //
            System.out.println("2. <association> 테스트 ");
            System.out.println("3. <collection> 테스트 ");
            System.out.print("메뉴 번호 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : elementTestService.selectResultMapTest(); break;
                case 2 : elementTestService.selectResultMapAssociationTest(); break;
                case 3 : elementTestService.selectResultMapCollectionTest(); break;
                default:
                    System.out.println("잘못 된 번호를 입력하셨습니다.");
            }
        } while(true);
    }
}