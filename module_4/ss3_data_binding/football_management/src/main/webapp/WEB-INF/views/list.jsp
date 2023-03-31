<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1 class="text-center" style="color: red">Danh sách cầu thủ bóng đá</h1>
<a class="btn btn-add btn-sm" href="/soccer-player/create">
    Thêm mới cầu thủ</a>
<table class="table table-striped able-bordered table-hover">
    <tr>
        <th>Mã cầu thủ</th>
        <th>Họ và tên</th>
        <th>Ngày sinh</th>
        <th>Kinh nghiệm</th>
        <th>Vị trí</th>
        <th>Ảnh đại diện</th>
        <th>Thông tin chi tiết</th>
        <th>Sửa</th>
        <th>Xoá</th>
    </tr>
    <c:forEach var="soccerPlayer" items="${soccerPlayerList}">
        <tr>
            <td>${soccerPlayer.code}</td>
            <td>${soccerPlayer.name}</td>
            <td>${soccerPlayer.dateOfBirth}</td>
            <td>${soccerPlayer.exp}</td>
            <td>${soccerPlayer.location}</td>
            <td><img src="${soccerPlayer.image}" alt=""></td>
            <td><a class="btn btn-primary btn-sm" href="/soccer-player/detail/${soccerPlayer.id}">Xem chi tiết</a></td>
            <td><a href="/soccer-player/update/${soccerPlayer.id}" class="btn btn-primary btn-sm">Sửa</a></td>
            <td>
                <button type="button" onclick="infoDelete(${soccerPlayer.id},'${soccerPlayer.name}')"
                        class="btn btn-danger btn-sm"
                        data-toggle="modal" data-target="#exampleModal">
                    Xoá
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<%--Modal--%>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Box</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/soccer-player/delete">
                <div class="modal-body">
                    <input hidden type="text" id="idDelete" name="idDelete">
                    <span>You may want to delete </span><span style="color: red" class="fw-bolder"
                                                              id="nameDelete"></span> ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <button type="submit" class="btn btn-primary">Yes</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function infoDelete(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = name;
    }
</script>
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
