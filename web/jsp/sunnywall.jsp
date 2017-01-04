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

<jsp:useBean id="sunnyWalls" scope="request" type="java.util.ArrayList<com.shsy.shsychatserver.bean.SunnyWallBean>"/>
<c:forEach items="${sunnyWalls}" var="sunnyWall">

    <div>${sunnyWall.id}${sunnyWall.username}${sunnyWall.date}${sunnyWall.msg}</div>

</c:forEach>

</body>
</html>
