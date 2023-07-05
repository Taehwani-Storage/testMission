
# MiniProject_Basic_KimTaeHwan
=======

# MiniProject_Basic_KimTaeHwan

## ♻️멋사마켓 미니 프로젝트

      🥕당근마켓, 번개장터, 중고나라 등을 착안하여 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트!
      사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여
      수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드 실습

- - -

## 요구사항

### Day(1) 중고 물품 관리 요구사항

    1. 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.
      a. 이때 반드시 포함되어야 하는 내용은 제목, 설명, 최소가격, 작성자 이다.
      b. 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
      c. 최초로 물품이 등록될 때, 중고 물품의 상태는 판매중 상태가 된다.
    2. 등록된 물품 정보는 누구든지 열람할 수 있다.
      a. 페이지 단위 조회가 가능하다.
      b. 전체 조회, 단일 조회 모두 가능하다.
    3. 등록된 물품 정보는 수정이 가능하다.
      a. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    4. 등록된 물품 정보에 이미지를 첨부할 수 있다.
      a. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
      b. 이미지를 관리하는 방법은 자율이다.
    5. 등록된 물품 정보는 삭제가 가능하다.
      a. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

### Day(2) 중고 물품 댓글 요구사항

    1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.
      a. 이때 반드시 포함되어야 하는 내용은 대상 물품, 댓글 내용, 작성자 이다.
      b. 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    2. 등록된 댓글은 누구든지 열람할 수 있다.
      a. 페이지 단위 조회가 가능하다.
    3. 등록된 댓글은 수정이 가능하다.
      a. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    4. 등록된 댓글은 삭제가 가능하다.
      a. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    5. 댓글에는 초기에 비워져 있는 답글 항목이 존재한다.
      a. 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
      b. 답글은 댓글에 포함된 공개 정보이다.

### Day(3) 구매 제안 요구사항

    1. 등록된 물품에 대하여 구매 제안을 등록할 수 있다.
      a. 이때 반드시 포함되어야 하는 내용은 대상 물품, 제안 가격, 작성자 이다.
      b. 또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
      c. 구매 제안이 등록될 때, 제안의 상태는 제안 상태가 된다.
    2. 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
      a. 대상 물품의 주인은, 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다. 이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다.
      b. 등록한 사용자는, 조회를 위해서 자신이 사용한 작성자와 비밀번호를 첨부해야 한다. 이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다.
    3. 등록된 제안은 수정이 가능하다.
      a. 이때, 제안이 등록될때 추가한 작성자와 비밀번호 를 첨부해야 한다.
    4. 등록된 제안은 삭제가 가능하다.
      a. 이때, 제안이 등록될때 추가한 작성자와 비밀번호 를 첨부해야 한다.
    5. 대상 물품의 주인은 구매 제안을 수락할 수 있다.
      a. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호 를 첨부해야 한다.
      b. 이때 구매 제안의 상태는 수락이 된다.
    6. 대상 물품의 주인은 구매 제안을 거절할 수 있다.
      a. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호 를 첨부해야 한다.
      b. 이때 구매 제안의 상태는 거절이 된다.
    7. 구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다.
      a. 이를 위해서 제안을 등록할 때 사용한 작성자와 비밀번호 를 첨부해야 한다.
      b. 이때 구매 제안의 상태는 확정 상태가 된다.
      c. 구매 제안이 확정될 경우, 대상 물품의 상태는 판매 완료가 된다.
      d. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 거절이 된다.

- - -

# To Be Done (History)

## 2023.06.29(목)

- Project 생성 및 first commit
- 물품 등록 기능
- 물품 조회 기본 코드 구현

## 2023.07.01(토)

- 사용자 인증 추가
- update, delete 기능 구현
- Image 등록 및 삭제 기능 구현(Item)
- CommentDto와 CommentRepository 추가
- NegotiationDto와 NegotiationRepository 추가
- 사용자 인증 메소드 추가

## 2023.07.03(월)

- 물품 판매 댓글 기능 구현
  > CommentController, CommentService, CommentDto
- Negotiation 구매 제안 기능 구현
  > NegotiationController와 NegotiationService 구현
  > NegotiationRepository와 NegotiationDto 수정

## 2023.07.04(화)

- Negotiation 구매 제안 기능 구현
  > NegotiationController, NegotiationService 구현
  > NegotiationRepository, NegotiationDto 수정
- Feedback
  > 중복 코드 모듈화
  > 사용하지 않는 코드 삭제

## 2023.07.05(수)

- 조직 GitHub 연동 문제 발생
- 개인 GitHub과 조직 GitHub 연동 차이 식별 및 해결

=======

### `POST /items/{itemId}/comments`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417",
    "content": "할인 가능하신가요?"
}
```

Response Status: 200

Response Body:

```json
{
    "message": "댓글이 등록되었습니다."
}
```

### `GET /items/{itemId}/comments`

Request Body: 없음

Response Status: 200

Response Body:
```json
{
    "content": [
        {
            "id": 1,
            "content": "할인 가능하신가요?",
            "reply": "아니요"
        },
    ],
    "totalPages": 4,
    "totalElements": 100,
    "last": false,
    "size": 25,
    "number": 1,
    "numberOfElements": 25,
    "first": false,
    "empty": false
}
```
### `PUT /items/{itemId}/comments/{commentId}`

Request Body:

  ```json
  {
      "writer": "taehwan.mutsa",
      "password": "likelion0417",
      "content": "할인 가능하신가요? 1000000 정도면 고려 가능합니다"
  }
  ```

Response Body:

  ```json
  {
      "message": "댓글이 수정되었습니다."
  }
  ```

### `PUT /items/{itemId}/comments/{commentId}/reply`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417",
    "reply": "안됩니다"
}
```

Response Body:

```json
{
    "message": "댓글에 답변이 추가되었습니다."
}
```
### `DELETE /items/{itemId}/comments/{commentId}`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417"
}
```

Response Body:
```json
{
    "message": "댓글을 삭제했습니다."
}
```

### `POST /items/{itemId}/proposal`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417",
    "suggestedPrice": 1000000
}
```

Response Status: 200

Response Body:

```json
{
    "message": "구매 제안이 등록되었습니다."
}
```

### `GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1`

Request Body: 없음

Response Status: 200

Response Body:

```json
{
    "content": [
          {
            "id": 1,
                    "suggestedPrice": 1000000,
            "status": "거절"
        },
          {
            "id": 2,
                    "suggestedPrice": 1200000,
            "status": "제안"
        },
    ],
    "totalPages": 4,
    "totalElements": 100,
    "last": false,
    "size": 25,
    "number": 1,
    "numberOfElements": 25,
    "first": false,
    "empty": false
}
```

### `PUT /items/{itemId}/proposals/{proposalId}`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417",
    "suggestedPrice": 1100000
}
```

Response Body:

```json
{
    "message": "제안이 수정되었습니다."
}
```

`writer` 와 `password` 가 물품 등록할 때의 값과 일치하지 않을 경우 실패

### `DELETE /items/{itemId}/proposals/{proposalId}`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417"
}
```

Response Body:

```json
{
    "message": "제안을 삭제했습니다."
}
```

`writer` 와 `password` 가 제안 등록할 때의 값과 일치하지 않을 경우 실패

### `PUT /items/{itemId}/proposals/{proposalId}/progress`

Request Body:

```json
{
    "writer": "yoonha.dev",
    "password": "loveth0214",
    "status": "수락" || "거절"
}
```

Response Body:

```json
{
    "message": "제안의 상태가 변경되었습니다."
}
```

`writer` 와 `password` 가 물품 등록할 때의 값과 일치하지 않을 경우 실패

### `PUT /items/{itemId}/proposals/{proposalId}/progress`

Request Body:

```json
{
    "writer": "taehwan.mutsa",
    "password": "likelion0417",
    "status": "확정"
}
```

Response Body:

```json
{
    "message": "구매가 확정되었습니다."
}
```

## 요구사항을 구현하기 위해 집중한 부분
- JPA 동작 및 사용법을 숙지하기
- 예외상황 고려하여 확인하기
- 서버와 사용자 간에 관계 파악하기

## 코드를 작성 시 더 나은 방향으로 생각해보기
  1. 다른 사람이 봐도 이해할 수 있도록 코드 짜기
  2. 객체를 사용해서 기능별로 나누어 코드 작성하기
  3. 현재 짠 코드보다 더 나은 코드가 있는지 찾아보기