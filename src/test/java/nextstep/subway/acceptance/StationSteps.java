package nextstep.subway.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.applicaion.dto.StationRequest;
import nextstep.subway.applicaion.dto.StationResponse;
import org.springframework.http.MediaType;

public class StationSteps {

    public static final String STATION_URI = "/stations";

    public static ExtractableResponse<Response> 지하철역_생성_요청(StationRequest stationRequest) {
        return RestAssured.given().log().all()
                .body(stationRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(STATION_URI)
                .then().log().all()
                .extract();
    }

    public static StationResponse 지하철_역_생성_요청_응답(StationRequest stationRequest) {
        return 지하철역_생성_요청(stationRequest).as(StationResponse.class);
    }

    public static ExtractableResponse<Response> 지하철역_목록_조회() {
        return RestAssured.given().log().all()
                .when()
                .get(STATION_URI)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 지하철역_삭제(String uri) {
        return RestAssured.given().log().all()
                .when()
                .delete(uri)
                .then().log().all()
                .extract();
    }

}