package me.study.core.beanfind;

import me.study.core.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @DisplayName("스프링에 등록된 모든 빈 정보를 출력할 수 있다.")
    @Test
    void findAllBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("bean = " + beanDefinitionName + ", Object = " + bean);
        }
    }

    @DisplayName("빈을 ROLE에 따라 구분할 수 있다.")
    @Test
    void findApplicationBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("bean = " + beanDefinitionName + ", Object = " + bean);
            }
        }
    }
}
