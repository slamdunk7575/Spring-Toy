package me.study.scope.web;

import lombok.RequiredArgsConstructor;
import me.study.scope.application.LogService;
import me.study.scope.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogController {

    /**
     * Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton;
     * nested exception is java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread?
     * If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
     *
     * 그냥 돌리면 스프링 컨테이너 한테 MyLogger(Request Scope) 빈을 달라고 할때 위와 같은 에러가 발생한다.
     *
     * 이유: Request Scope 빈의 생명주기는 Client 요청과 함께 생성되고 응답과 함께 소멸되기 때문이다.
     *
     * 해결 1: ObjectProvider 덕분에 ObjectProvider.getObject()를 호출하는 시점까지 Request Scope '빈의 생성을(스프링 컨테이너한테 요청하는 시점을) 지연' 할 수 있다.
     * ObjectProvider.getObject()를 LogController, LogService 에서 각각 한번씩 따로 호출해도 같은 HTTP 요청이면 같은 스프링 빈이 반환된다.
     * 이걸 직접 구현하려면 얼마나 힘들까..갓스프링!
     *
     * 해결 2: @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
     * 가짜 Proxy 객체를 만들어서 주입해놨다가 실제 MyLogger 를 사용(호출)하는 시점에 가짜 Proxy 객체가 진짜 MyLogger 객체를 호출한다.
     * (가짜 Proxy 객체는 내부적으로 진짜 MyLogger 를 찾는 방법을 알고있다.)
     *
     * 핵심: Provider 를 사용하든 Proxy 를 사용하든 진짜 객체 조회를 꼭 필요한 시점까지 지연한다는 점이다.
     * 단지 애노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다. Client 는 프록시를 사용하는지 진짜 객체를 사용하는지 모름.
     * -> 이것이 다형성과 DI 컨테이너가 가진 큰 장점
     */

    // private ObjectProvider<MyLogger> myLoggerProvider;
    private MyLogger myLogger;
    private LogService logService;

    public LogController(MyLogger myLogger, LogService logService) {
        this.myLogger = myLogger;
        this.logService = logService;
    }

    @RequestMapping("log-demo")
    public ResponseEntity<String> logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("Log Controller test");

        logService.logic("testId");
        return ResponseEntity.ok().build();
    }

}
