# Reservation
project - reservation


## 개요
- 매장 테이블 예약 서비스
- 목표 : 매장을 방문할 때 미리 방문 예약을 진행하느 기능을 구현
- 기술 : SpringBoot, Java, MySQL, Docker, Jpa


## 회원 서버
### 공통
- [x] 로그인 토큰 발행
- [x] 로그인 토큰을 통한 제어 확인
- [x] 로그인
### 사용자
- [x] 회원가입
### 점장
- [x] 회원가입


## 매장 서버
### 공통
- [x] 매장 검색
- [x] 매장 상세 정보 조회(가나다순, 별점순, 거리 가까운순)
### 점장
- [x] 매장 등록(로그인된 상태에서, 매장명, 상점위치, 상점 설명)
- [x] 매장 정보 수정 


## 예약 서버
### 사용자
- [x] 예약 등록(로그인된 상태에서, 전화번호로)
- [ ] 도착 확인
### 점장
- [x] 예약 정보 확인

## 리뷰 서버
### 사용자
- [ ] 리뷰 작성
