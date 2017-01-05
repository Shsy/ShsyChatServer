<%--
  Created by IntelliJ IDEA.
  User: Shsy
  Date: 2017/1/3
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>心晴墙</title>
</head>
<body>

<div class="div_input">

    <form action="${pageContext.request.contextPath}/api/sunnywall/addSunnyWall" method="get" onsubmit="checkForm()">

        <input type="text" id="msg" name="msg" class="input_msg">

        <input type="submit" value="提交"/>
    </form>


</div>

<div class="div_list">
    <table border="1" style="border-color: #b2bbba" cellpadding="0" cellspacing="0">
        <tr>
            <th>用户名</th>
            <th>时间</th>
            <th>留言内容</th>
        </tr>
        <jsp:useBean id="sunnyWalls" scope="request"
                     type="java.util.ArrayList<com.shsy.shsychatserver.bean.SunnyWallBean>"/>
        <c:forEach items="${sunnyWalls}" var="sunnyWall">
            <tr>
                <td>${sunnyWall.username}</td>
                <td>${sunnyWall.date}</td>
                <td>${sunnyWall.msg}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>


<script type="text/javascript">
    function checkForm() {
        var msg = findViewById("msg").value;

        if (isEmpty(msg)) {
            alert("留言内容不能为空吧..")
            return false;
        }
        else {
            return true;
        }
    }

    function isEmpty(str) {
        return str == null || str.length == 0;
    }

    function findViewById(id) {
        return document.getElementById(id);
    }

</script>

<style type="text/css">
    .div_input {

    }

    .input_msg {

    }

    td{
       text-align: center;
    }

    .div_list {

    }
</style>


</html>
