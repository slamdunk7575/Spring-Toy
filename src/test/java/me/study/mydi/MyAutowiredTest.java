package me.study.mydi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MyAutowiredTest {

    @Test
    @DisplayName("MyAutowired 테스트")
    public void my_di_test() {
        CafeService cafeService = MyIocContainer.getObject(CafeService.class);
        assertThat(cafeService).isNotNull();
        assertThat(cafeService.cafeRepository).isNotNull();
    }

    @Test
    @DisplayName("인스턴스 생성 테스트")
    public void create_instance() {
        CafeRepository cafeRepository = MyIocContainer.createInstance(CafeRepository.class);
        assertThat(cafeRepository).isNotNull();
    }

}