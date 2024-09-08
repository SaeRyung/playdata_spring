package com.ohgiraffers.transactional.section01.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
/* DML(insert, update, delete) 작업 테스트 시 실제 DB 적용을 하지 않기 위해서
* 테스트 수행 후 rollback 요청하는 기능 */
@Transactional
class OrderServiceTest {

    // 테스트 하고 싶은 대상 의존성 주입으로 가져온다.
    @Autowired
    private OrderService orderService;

    private static Stream<Arguments> getOrderInfo()
            // orderDTO -> Argument로 담아서 출력
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(LocalDate.now());
        orderDTO.setOrderTime(LocalTime.now());
        orderDTO.setOrderMenus(
                List.of(
                        new OrderMenuDTO(1, 10),
                        new OrderMenuDTO(2, 5),
                        new OrderMenuDTO(3, 20)
                )
        );
        return Stream.of(Arguments.of(orderDTO));
    }


    @DisplayName("주문 등록 테스트")
    // 파라미터 여러개 넘겨서 테스트 진행
    @ParameterizedTest
    @MethodSource("getOrderInfo")
    // 메소드 통해서 파라미터 order 전달
    void testRegistNewOrder(OrderDTO orderDTO) {
        Assertions.assertDoesNotThrow(
                ()->orderService.registNewOrder(orderDTO)
        );
    }

}