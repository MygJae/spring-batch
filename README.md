# [스프링 배치] 예제로 배우는 핵심 Spring Batch

### 환경설정
- 스프링 5.3.23 </br>
- 스프링 부트 2.7.4 </br>
- 자바 17 </br>
- 스프링 배치 4.3.7 </br>
- MySql 8.0.36 </br>
- JPA 2.7.3 </br>

### 준비 과정
- 스프링 부트3 버전에서 2로 다운그레이
  - 3이상 부터 스프링 배치 5 사용됨
- Mysql 설치
   - 기존에 설치 되어있었고, root pw 잊어버린 경우여서 재설치 할때
   - -> C:\ProgramData\MySQL 이 경로에 있는 폴더까지 삭제 필요
- 디비버 MySql 접속
  - Test connection 할 때 Public key retrieval is not allowed 에러 발생
  - -> MySql8.0부터 보안 이슈로 useSSL 옵션에 대한 추가적인 설정이 필요
  - -> Driver properties탭 -> allowPublicKeyRetrieval를 false에서 true
- MySql 의존성 주입 실패
  - runtimeOnly 'com.mysql:mysql-connector-j' -> implementation 'mysql:mysql-connector-java:8.0.29'

### 스프링 배치 사용 이유
- 사용 이유
  - 대용량 데이터를 처리
  - 예외 처리, 재시도 메커니즘, 트랜잭션 관리
  - 모니터링과 관리
  - 소스와의 통합을 지원
  - 장애 복구 메커니즘을 제공
- 사용 예시
  - 대규모 데이터 병렬 처리: 이메일 일괄 전송, 정산
  - ETL: Extract 추출, Transform 가공, Load 로드
  - 데이터 마이그레이션: 시스템 간 데이터 통합 자동화
  - 백업 및 복구 관리

### 스프링 배치 구성
- JobLauncher -> Job ->(1:N) Step -> Tasklet ->(1:N) ItemReader,ItemProcessor, ItemWriter
- Job이 실행 -> Job에 정의된 각각의 Step을 실행 -> Tasklet을 실행(비즈니스 로직)
### 역할
- Step
  - 배치작업을 어떻게 구성하고 실행할 것인지에 대한 Job의 세부 작업을 Task 기반으로 설정하고 명세해 놓은 객체
- Step 구현체
  - TaskletStep: Step의 가장 기본이 되는 클래스로 Tasklet 타입의 구현체들을 제어한다. 
  - PartitionStep: 멀티 스레드 방식으로 Step을 여러 개로 분리해서 실행한다. 
  - JobStep: Step 내에서 Job을 실행한다.
  - FlowStep: Step 내에서 Flow를 실행한다.
- Job
  - 독립적으로 실행할 수 있는 고유하며 순서가 지정된 Step의 list
- Tasklet
  - Chunk 기반 클래스: ItemReader, ItemProcessor, ItemWriter
  - execute 단일로 구성 가능
