<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-6.14.15-blue">
  <img alt="node" src="https://img.shields.io/badge/node-14.18.2-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```

---

# Step 1. 노선 관리 기능 구현
인수 테스트 작성과 테스트를 충족하는 기능 구현

### 요구사항
- [X] 지하철 노선 생성 
  - [X] 인수 테스트 작성
    - Scenario: 지하철 노선 생성
    - When 지하철 노선 생성을 요청 하면
    - Then 지하철 노선 생성이 성공한다.
- [X] 지하철 노선 목록 조회
  - [X] 인수 테스트 작성
    - Given
      - 지하철 노선 생성을 요청 하고
      - 새로운 지하철 노선 생성을 요청 하고
    - When 지하철 노선 목록 조회를 요청 하면
    - Then 두 노선이 포함된 지하철 노선 목록을 응답받는다
  - [X] 기능 구현
- [X] 지하철 노선 조회
  - [X] 인수 테스트 작성
    - Given 지하철 노선 생성을 요청 하고
    - When 생성한 지하철 노선 조회를 요청 하면
    - Then 생성한 지하철 노선을 응답받는다
  - [X] 기능 구현
- [X] 지하철 노선 수정
  - [X] 인수 테스트 작성
    - case1: 존재하는 노선을 수정
      - Given 지하철 노선 생성을 요청 하고
      - When 지하철 노선의 정보 수정을 요청 하면
      - Then 지하철 노선의 정보 수정은 성공한다.
    - case2: 존재하지 않는 노선을 수정
      - When 없는 지하철 노선의 정보 수정을 요청하면
      - Then 노선 수정이 실패한다.
  - [X] 기능 구현
- [X] 지하철 노선 삭제
  - [X] 인수 테스트 작성
    - Scenario: 지하철 노선 삭제
    - Given 지하철 노선 생성을 요청 하고
    - When 생성한 지하철 노선 삭제를 요청 하면
    - Then 생성한 지하철 노선 삭제가 성공한다.
  - [X] 기능 구현

# Step 2. 인수 테스트 리팩터링

### Step1 피드백

- [X] LineNotFoundException에서 매직 리터럴 관리
- [X] HTTP PUT METHOD 사용시 없는 경우 에러를 반환
- [X] CONVERTER 대신 DTO가 변환 책임을 갖도록 변경
- [X] /lines 공통 @RequestMapping 사용
- 테스트코드 검증부 모듈화
- 테스트에서 메소드 직접 사용 대신 @MehtodSource, @ArgumentSource 사용 -> 테스트 스텝 안으로 이동

### Step2 요구사항

- [X] 지하철역과 지하철 노선 이름 중복 금지 기능 추가
  - [X] Feature: 지하철역 관리 기능 인수 테스트 작성
    - Scenario: 중복이름으로 지하철역 생성
    - Given 지하철역 생성을 요청 하고
    - When 같은 이름으로 지하철역 생성을 요청 하면
    - Then 지하철역 생성이 실패한다.
    - [X] 기능 구현
  - [X] Feature: 지하철 노선 관리 기능 인수 테스트 작성
    - Scenario: 중복이름으로 지하철 노선 생성
    - Given 지하철 노선 생성을 요청 하고
    - When 같은 이름으로 지하철 노선 생성을 요청 하면
    - Then 지하철 노선 생성이 실패한다.
    - [X] 기능 구현
- [X] 새로운 기능 추가하면서 인수 테스트 리팩터링

## Step3 요구사항

- 요구사항을 통해 인수조건을 직접 도출한다.
- 인수 조건 정의 -> 테스트 작성 -> 기능 구현 사이클을 지킨다.
- [X] 지하철 노선 생성 시 필요한 인자 추가하기
  - 노선 생성시 두 종점(상행, 하행)과 거리 추가
- [X] 지하철 노선에 구간을 등록하는 기능 구현
  - 새로운 구간의 상행역은 해당 노선에 등록되어있는 하행 종점역이어야 한다.
    - [X] 지하철 노선에 구간 등록 기능 인수 테스트 작성
      - Scenario: 지하철 노선에 구간 등록
      - Given 지하철 노선 생성을 요청 하고
      - When 생성 노선 하행역과 이어지는 구간 등록을 요청하면
      - Then 지하철 노선 구간 등록이 성공한다.
    - [X] 기능 구현
  - 새로운 구간의 하행역은 해당 노선에 등록되어있는 역일 수 없다.
    - [X] 지하철 노선에 구간 등록 기능 인수 테스트 작성
      - Scenario: 지하철 노선에 구간 등록
      - Given 지하철 노선 생성을 요청 하고
      - When 노선에 존재하는 역을 하행역으로 갖는 구간 등록을 요청하면
      - Then 지하철 노선 구간 등록이 실패한다.
    - [X] 기능 구현
  - 위 조건에 부합하지 않는 경우 에러가 발생한다.
    - [X] 지하철 노선에 구간 등록 기능 인수 테스트 작성
      - Scenario: 지하철 노선에 구간 등록
      - Given 지하철 노선 생성을 요청 하고
      - When 생성 노선 하행역과 이어지지 않는 구간 등록을 요청하면
      - Then 지하철 노선 구간 등록이 실패한다.
    - [X] 기능 구현
- [ ] 지하철 노선에 구간을 제거하는 기능 구현
  - 지하철 노선에 등록된 역(하행 종점역)만 제거할 수 있다. 즉, 마지막 구간만 제거할 수 있다.
  - 지하철 노선에 상행 종점역과 하행 종점역만 있는 경우(구간이 1개인 경우) 역을 삭제할 수 없다.
  - 새로운 구간 제거시 위 조건에 부합하지 않는 경우 에러 처리한다.
- [ ] 지하철 노선에 등록된 구간을 통해 역 목록을 조회하는 기능 구현
  - 지하철 노선 조회 시 등록된 역 목록을 함께 응답
  - 노선에 등록된 구간을 순서대로 정렬하여 상행 종점부터 하행 종점까지 목록을 응답하기
- [ ] 구간 등록 / 제거 시 예외 케이스에 대한 인수 테스트 작성
