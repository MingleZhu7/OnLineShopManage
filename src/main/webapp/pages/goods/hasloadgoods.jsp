<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>上架商品</title>


    <script src="./layui/layui/layui.js"></script>
    <script src="./js/jquery.js"></script>
    <link type="text/css" rel="stylesheet" href="./layui/layui/css/layui.css" />
    <link rel="stylesheet" href="./layui/layui/css/layui.css">
    <%--    <link rel="stylesheet" href="../../css/style.css">--%>

</head>

<body>

<div class="layui-layout layui-layout-admin">
    <%--    头部--%>
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">电子商城后台管理</div>



        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="login.jpg"
                         class="layui-nav-img">
                    ${username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="main">首页</a></dd>
                    <dd><a href="logout">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <%--侧边栏--%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="main">首页</a></li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="goodslist">商品列表</a></dd>
                        <%--                        <dd><a href="pages/goods/goodslist.jsp">商品列表</a></dd>--%>
                        <dd><a href="goodsadd">添加商品</a></dd>
                        <dd><a href="hasloadgoods">上架列表</a></dd>
                        <dd><a href="typelist">商品类型</a></dd>

                    </dl>
                </li>
                <li class="layui-nav-item ">
                    <a href="javascript:;">商铺管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="shoplist">商铺列表</a></dd>
                        <dd><a href="shopadd">添加商铺</a></dd>

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="orderlist">订单列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="padding: 15px;">
        <div class="weadmin-nav" style="padding-bottom: 15px">
            <span class="layui-breadcrumb">
                <a href="main">首页</a>
                <a href="goodslist">商品管理</a>
                <a>
                    <cite>上架商品</cite></a>
            </span>

        </div>
        <!-- 内容主体区域 -->
        <form class="layui-form" action="hasloadgoods" method="post">
            <label style="padding: 15px">商铺</label>
            <div class="layui-input-inline">

                <select name="shop">
                    <option value="">请选择商铺</option>
                    <c:forEach items="${shoplist}" var="shop" varStatus="status">
                        <option value=${shop.name}>${shop.name}</option>
                    </c:forEach>
                </select>
            </div>
            <label style="padding: 15px">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" name="goodsName" placeholder="商品名称" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="padding: 15px" >
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="formSearch">搜索</button>
            </div>
        </form>

        <!--每条数据的操作栏-->


        <div style="text-align:left">
            <table class="layui-table" lay-filter="demo" >
                <thead>
                <tr>
                    <th lay-data="{field:'status', align: 'center'}">序号</th>
                    <th lay-data="{field:'shop',align: 'center'}">商铺</th>

                    <th lay-data="{field:'goods', align: 'center'}">商品名</th>
                    <th lay-data="{field:'price', align: 'center'}">价格</th>
                    <th lay-data="{fixed: 'right', toolbar: '#barDemo', align: 'center'}">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${maplist}" var="map" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${map.shop}</td>
                        <td>${map.goods}</td>
                        <td>${map.price}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        电子商城
    </div>

</div>
<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">下架</a>
</script>
<script>

    layui.use(['table','form'], function() {
        var table = layui.table;
        table.init('demo', {
            height: 400, //设置高度
            limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            //支持所有基础参数,
            , page: true
        });
        //监听行工具事件
        table.on('tool(demo)', function (obj) {
            var data = obj.data;

            // console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的要下架吗', function (index) {
                    //
                    window.location.href="loaddel?Gname="+data.goods+"&&Sname="+data.shop;
                });
            }
        });
    });
</script>
</body>

</html>