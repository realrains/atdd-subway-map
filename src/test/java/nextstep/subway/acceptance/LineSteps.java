package nextstep.subway.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.applicaion.dto.LineRequest;
import org.springframework.http.MediaType;

public class LineSteps {

    private static final String LINES_URI = "/lines";

    public static ExtractableResponse<Response> createLine(LineRequest lineRequest) {
        return RestAssured.given().log().all()
                .body(lineRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(LINES_URI)
                .then().log().all()
                .extract();
    }

    public static int 노선_생성_요청_응답_HttpStatusCode(LineRequest lineRequest) {
        ExtractableResponse<Response> response = createLine(lineRequest);
        return response.statusCode();
    }

    public static ExtractableResponse<Response> findLines() {
        return RestAssured.given().log().all()
                .when()
                .get(LINES_URI)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> findLines(String uri) {
        return RestAssured.given().log().all()
                .when()
                .get(uri)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> updateLine(String uri, LineRequest lineRequest) {
        return RestAssured.given().log().all()
                .body(lineRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(uri)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> deleteLine(String uri) {
        return RestAssured.given().log().all()
                .when()
                .delete(uri)
                .then().log().all()
                .extract();
    }

}