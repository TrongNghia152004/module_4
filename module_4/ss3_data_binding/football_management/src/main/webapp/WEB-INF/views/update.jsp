<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
    img {
        width: 69px;
        height: 69px;
    }
</style>
<body>
<form:form action="/soccer-player/update" method="post" modelAttribute="soccerPlayer">
    <label>Mã cầu thủ</label>
    <form:input path="code" type="text"/><br>
    <label>Tên cầu thủ</label>
    <form:input path="name" type="text"/><br>
    <label>Ngày sinh</label>
    <form:input path="dateOfBirth" type="text"/><br>
    <label>Kinh nghiệm</label>
    <form:input path="exp" type="text"/><br>
    <label>Vị trí</label>
    <form:input path="location" type="text"/><br>
    <label>Ảnh đại diện</label>
    <form:input path="image" type="text"/><br>
    <button type="submit">Sửa</button>
</form:form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
