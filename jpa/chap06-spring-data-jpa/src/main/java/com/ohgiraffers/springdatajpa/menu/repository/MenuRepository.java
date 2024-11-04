package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// JpaRepository 를 상속받아 Menu 엔티티 기본 crud를 가진 인터페이스 MenuRepository 를 구현
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 */
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 + 가격순 조회 */
    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(Integer menuPrice);

    /* 파라미터로 전달 받은 가격을 초과하는 메뉴 목록 조회 + 전달 받은 정렬 기준 */
    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort sort);
}