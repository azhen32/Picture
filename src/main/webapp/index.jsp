<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <link href="${ctx}/main.css" rel="stylesheet" type="text/css"/>
    <title>上传文件</title>
</head>
<body>
    <div class="demo">
        <div class="bheader">
            <h2>--图片上传--</h2>
        </div>
        <div class="bbody">
            <form id="upload_form" enctype="multipart/form-data" method="post" action="${ctx}/thumbnail">
                <h2>请选择上传文件</h2>
                <div>
                    <input type="file" name="image" id="image"/>
                    <input type="submit" value="上传"/>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
