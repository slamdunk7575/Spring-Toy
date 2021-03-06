package me.study.di.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CafeController {

    // TODO 생성자 주입 정리하기
    private final Barista barista;

    public CafeController(Barista barista) {
        this.barista = barista;
    }

    @RequestMapping(value = "/coffee", method = RequestMethod.GET)
    public ResponseEntity<String> order() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/plain; charset=utf-8");

        String coffee = barista.makeCoffee();
        return new ResponseEntity<>(coffee, responseHeaders, HttpStatus.OK);
    }
}
