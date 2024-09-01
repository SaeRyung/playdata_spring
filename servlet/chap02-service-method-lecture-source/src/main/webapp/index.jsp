<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Service Method</title>
</head>
<body>
<h1>Service Method의 역할</h1>

<h3>GET 방식의 요청</h3>
<h4>a 태그의 href 속성 값  요청</h4>
<a href="request-service">서비스 메소드 요청하기</a>

<h4>form 태그의 method 속성 값  요청</h4>
<!--form: 서버쪽의 어떤 요청을 전달할 때 사용. action: 서버에 요청할 주소-->
<form action="request-service" method="get">
    <input type="submit" value="get 방식 요청 전송">
</form>

<h4>form 태그의 method 속성 값  요청</h4>
<form action="request-service" method="post">
    <input type="submit" value="post 방식 요청 전송">
</form>

</body>
</html>