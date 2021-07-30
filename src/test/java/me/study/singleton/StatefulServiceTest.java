package me.study.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @DisplayName("Stateful 한 싱글톤 객체의 문제점 테스트")
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A: A 사용자 10000원 주문
        int priceA = statefulService1.order("A", 10000);
        // Thread B: B 사용자 50000원 주문
        int priceB = statefulService2.order("B", 50000);

        // Thread A: A 사용자 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("price: " + priceA);

        assertThat(priceA).isEqualTo(10000);
    }
}
