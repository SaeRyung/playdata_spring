package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


// 서비스계층 - 비즈니스로직수행 단위, 하나의 트랜잭션 안에서 실행합니다 정의
// 내가 필요한 기능을 메소드
// 엔티티 내용은 보여주면 안된다. 엔티티 > DTO로 변환 , DTO > 엔티티 변환이 서비스계층에서 일어난다.
@Service
public class MenuService {

    //db 연동 계층 레퍼지토리 사용 위해 생성자 주입
    private final MenuRepository menuRepository;
    // 모델 매퍼 추가
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    /* Entity to DTO (DTO to Entity) 작업이 필요하다.
    * 1. 수동 매핑 메소드 작성(toEntity, fromEntity, toDTO, fromDTO...)
    * 2. 자동 매핑 라이브러리 사용 */
    // 수동, 자동은 선택의 문제이다.

    /* 1. findById */
    public MenuDTO findMenuByMenuCode(int menuCode){
        // 넘어온 menuCode 엔티티로 변경 > 변경 후 MenuDTO로 변환하기

        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        // null일 경우 Exceptiion > 핸들링 방식.. / 찾아지면 menu 엔티티 변환

        return modelMapper.map(menu, MenuDTO.class);
        //modelMapper : 자동 매핑 기능
    }


    /* 2. findAll(Sort) */
    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());
        // List자체로 옮겨올 수 없다
        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }


    /* 3. findAll(Pageable) */
    public Page<MenuDTO> findMenuList(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending()
        );

        Page<Menu> menuList = menuRepository.findAll(pageable);
        // Page type : map 메소드를 가지고 있다.

        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }


    /* Query Method */
    public List<MenuDTO> findByMenuPrice(Integer menuPrice) {

//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThenOrderByMenuPrice(menuPrice);
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuPrice").descending());
        return menuList.stream()
                // 반환값 DTO 로 변경
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }



    /* 5. JPQL or Native Query */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }


    /* 6. save */
    @Transactional //시작끝점에 커밋될 수 있도록 처리
    public void registMenu(MenuDTO menuDTO) {
        menuRepository.save(modelMapper.map(menuDTO, Menu.class));
    }


    /* 7. 수정 */
    @Transactional
    public void modifyMenu(MenuDTO menuDTO) {
        Menu foundMenu = menuRepository.findById(menuDTO.getMenuCode()).orElseThrow(IllegalArgumentException::new);
        // setter 사용 지양, 기능에 맞는 메소드를 별도로 정의해서 사용하기
        foundMenu.modifyMenuName(menuDTO.getMenuName());
    }


    /* 8. 삭제*/
    public void deleteMenu(Integer menuCode) {

        menuRepository.deleteById(menuCode);
    }


}
