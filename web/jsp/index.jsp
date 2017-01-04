<%--
  Created by IntelliJ IDEA.
  User: 申尚宇
  Date: 2016/12/23
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ShsyChatServer</title>
</head>
<body>
只是一个App Server 别乱溜达
<br/>
不过想留言还是可以的 <a href="javascript:void(0);" onclick="checkLogin('${sessionScope.get("a")}')">就在这里</a>
<br/>
<a href="http://丁珂璠.我爱你">回首页</a>
</body>

<script type="text/javascript">
    /**
     * 如果没登录就去登录界面
     * 如果登录就到留言板界面
     * @param username
     */
    function checkLogin(username) {
        if (username == null || username.length == 0) {
            window.location.href = "login.jsp";
        } else {
            window.location.href = "/api/sunnywall/findall";
        }

    }
</script>
</html>
