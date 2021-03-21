package me.study.aop.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.study.AcceptanceTest;
import me.study.aop.dto.MenuRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @DisplayName("까페 메뉴를 관리한다.")
    @Test
    void manageMenu() {
        // given
        MenuRequest menuRequest = new MenuRequest("아이스 아메리카노", BigDecimal.valueOf(4000));

        // when
        ExtractableResponse<Response> createResponse = 메뉴_생성_요청(menuRequest);

        // then
        메뉴_생성됨(createResponse);

        // when
        ExtractableResponse<Response> findResponse = 상품_목록_조회_요청();

        // then
        상품_목록_응답됨(findResponse);
        상품_목록_포함됨(findResponse, Arrays.asList(createResponse));
    }

    private void 메뉴_생성됨(ExtractableResponse<Response> createResponse) {
        assertThat(createResponse.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> 메뉴_생성_요청(MenuRequest menuRequest) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(menuRequest)
                .when().post("/api/menus")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 상품_목록_조회_요청() {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/menus")
                .then().log().all()
                .extract();
    }

    private void 상품_목록_응답됨(ExtractableResponse<Response> findResponse) {
        assertThat(findResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private void 상품_목록_포함됨(ExtractableResponse<Response> findResponse, List<ExtractableResponse<Response>> createResponse) {
        List<Long> createMenuIds = createResponse.stream()
                .map(create -> Long.parseLong(create.header("Location").split("/")[3]))
                .collect(Collectors.toList());

        List<Long> findMenuIds = findResponse.jsonPath().getList("id", Long.class);
        assertThat(findMenuIds).containsAll(createMenuIds);
    }

}
