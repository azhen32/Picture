<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>图片信息</h4>
    <hr/>
    <table width="100%">
        <tr>
            <td width="50%" align="center">
                <img src="${ctx}${imageURL}" width="500">
            </td>
            <td width="50%" align="center">
                <img src="${ctx}${thumbImageURL}">
            </td>
        </tr>
    </table>
    <hr/>
    <a href="${ctx}">返回</a>
    <hr/>
</body>
</html>
