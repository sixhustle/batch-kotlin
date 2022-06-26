# Spring Batch with Kotlin

> Spring Batch + Kotlin 연습 프로젝트입니다.
> 이상한 점 많을 수 있으니 참고하세요~!

---

# 메모
- validator는 대/소문자 구분을 한다.

---

# 겪었던 오류들
- 에러: Table 'batch.BATCH_JOB_INSTANCE' doesn't exist  
- 원인: Spring Boot 2.5 부터 yml 경로가 변경됨  
- 해결:   
  - spring.batch.initialize-schema -> spring.batch.jdbc.initialize-schema  
  - https://stackoverflow.com/questions/49443551/i-am-getting-error-table-test-batch-job-instance-doesnt-exist
