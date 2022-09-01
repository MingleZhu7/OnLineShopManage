<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/6/4
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>上架商品</title>

    <script src="./layui/layui/layui.js"></script>
    <script src="./js/jquery.js"></script>
    <link type="text/css" rel="stylesheet" href="./layui/layui/css/layui.css" />
    <link rel="stylesheet" href="./layui/layui/css/layui.css">
</head>
<body>
<div style="width: 400px; position: relative; left:15%; padding-top: 20px">
    <form class="layui-form" action="shoploadup" method="post" >
        <div class="layui-form-item" style="padding-bottom: 10px">
            <input type="hidden" name="Sid" value="${Sid}"/>
            <label class="layui-form-label">
                <span style="color: red">*</span>商品</label>
            <div class="layui-input-inline">
                <select name="goodsName" lay-verify="required">
                    <option value="">请选择要上架的商品</option>
                    <c:forEach items="${goodslist}" var="goods" varStatus="status">
                        <option value=${goods.name}>${goods.name}</option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 10px">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
<script>
    var hasLoadUp = '<%=request.getAttribute("hasLoadUp")%>';
    if(hasLoadUp == "true"){
        layer.msg("该商品已在本店铺上架，上架失败", {icon: 2});
        setTimeout("window.close()",2000);
    }else if (hasLoadUp == "false"){
        layer.msg("上架成功", {icon: 1});
        setTimeout("window.close()",2000);
    }
</script>

</body>
</html>
