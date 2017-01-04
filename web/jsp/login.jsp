<%--
  Created by IntelliJ IDEA.
  User: Shsy
  Date: 2017/1/4
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/api/user/login" method="get" onsubmit="return checkForm()">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" id="username"/></td>
        </tr>
        <tr>
            <td>密&nbsp码</td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
    </table>

    <input type="submit" value="登录"/>
</form>

</body>

<script type="text/javascript">

    function checkForm() {
        var username = findViewById("username").value;
        var password = findViewById("password").value;

        if (isEmpty(username) || isEmpty(password)) {
            alert("用户名密码不能为空啊...");
            return false;
        } else {
            findViewById("password").value = hex_md5(password).toUpperCase();
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/md5.js"></script>
</html>
