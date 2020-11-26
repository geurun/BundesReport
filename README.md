![](/src/main/resources/static/img/bundesreport-logo-gray-layer.png)

1. **프로젝트 소개 / 팀 소개**
   - [Bundesreport](#bundesreport)
   - [Team Julnaf](#team-julnaf-율나프)
2. **프로젝트 진행**
   - [진행 기간](#진행-기간)
   - [개발환경 & 기술](#개발환경--기술)
3. **프로젝트 결과**
   - [구현 기능](#구현-기능)
   - [추후 계획](#추후-계획)
_____

## Bundesreport
독일 연방을 뜻하는 단어 'Bund-'와 기록을 뜻하는 단어 'Report'를 합성하여 만든 ``Bundesreport``는 **독일과 한국을 잇는 커뮤니티 사이트**입니다.
- 사용자의 편의에 따라 한국어와 독일어를 선택적으로 제공합니다.
- 미니게임을 통해 사용자의 독일어 학습을 돕고 높은 재방문율을 기대할 수 있습니다.

## Team Julnaf (율나프)
**이승재 (SeungJae Lee, Olaf)**
- 팀 리더, 백엔드 개발
- 개발환경 구축
- User 기능 구현
- 쪽지 기능 구현
- :link: [Github](https://github.com/veritas0806)

**이유진 (YouJin Lee, Juli)**
- 백엔드 개발
- 데이터 모델링
- 게시판/갤러리 기능 구현
- 댓글 기능 구현
- :link: [Github](https://github.com/dev-ujin)

**이지현 (JiHyun Lee, Hanna)**
- 프론트엔드 개발
- 레이아웃/뷰 디자인
- 미니게임 개발
- :link: [Github](https://github.com/leehuhlee)

## 진행 기간
2020 K-Move FKI 독일 IT 전문가 양성과정을 수료하며 1달(11월 4일 - 12월 4일)동안 진행하였습니다.

## 개발환경 & 기술
### 개발환경
- Java : 11.0.9(Adopt OpenJDK)
- MySQL : 8.0.22
- Apache Tomcat : 9.0.39
- Eclipse : 2020-09
- Maven

### 기술
- Spring Boot
- Thymeleaf
- Bootstrap
- Lombok
- JPA
- reCAPTCHA

### 기타
- 버전 관리 : Git
- 프로젝트 관리 : Github Project

## 구현 기능
- 회원가입, 로그인, 로그아웃, 회원 정보 수정
- 게시판 : 글 생성, 글 수정, 글 삭제, 조회수 확인, 좋아요 기능
- 댓글 : 댓글 작성, 댓글 수정, 댓글 삭제, 좋아요 기능
- 쪽지함 : 쪽지 전송, 쪽지 수신, 답장 보내기
- 독일어 미니 게임

## 추후 계획
1. 회원 관리
   - OAuth 지원
   - 비밀번호 찾기
   - 이메일 인증 혹은 이메일 발송 기능
2. 게시판 변경(Datatable Library -> Spring Data JPA의 Paging Web Support)
3. 관리자 페이지
4. 게시글 #해쉬태그
5. 미니 게임 랭킹 게시판




