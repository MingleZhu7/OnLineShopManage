<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/6/4
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>商铺列表</title>

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

                <li class="layui-nav-item ">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="goodslist">商品列表</a></dd>
                        <%--                        <dd><a href="pages/goods/goodslist.jsp">商品列表</a></dd>--%>
                        <dd><a href="goodsadd">添加商品</a></dd>
                        <dd><a href="hasloadgoods">上架列表</a></dd>
                        <dd><a href="typelist">商品类型</a></dd>

                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">商铺管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="shoplist">商铺列表</a></dd>
                        <dd><a href="shopadd">添加商铺</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item ">
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
                <a href="shoplist">商铺管理</a>
                <a>
                    <cite>商铺列表</cite></a>
            </span>

        </div>
        <!-- 内容主体区域 -->
        <form class="layui-form" action="shoplist" method="post">

            <label style="padding: 15px">店铺名称</label>
            <div class="layui-input-inline">
                <input type="text" name="shopname" placeholder="店铺名称" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="padding: 15px" >
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="formSearch">搜索</button>
            </div>
        </form>

        <!--每条数据的操作栏-->

        <div class="layui-btn-container" style="padding: 15px;">
            <button class="layui-btn layui-btn-sm"  onclick="location.href='shopadd'">添加</button>
            <%--            <button class="layui-btn layui-btn-sm" id="delete">删除</button>--%>
        </div>

        <div style="text-align:left">
            <table class="layui-table" lay-filter="demo" >
                <thead>
                <tr>
                    <th lay-data="{field:'status', align: 'center',width: 100}">序号</th>
                    <th lay-data="{field:'id', align: 'center',width: 100}">商铺编号</th>
                    <th lay-data="{field:'name', align: 'center'}">商铺名</th>
                    <th lay-data="{field:'describe',align: 'center'}">简介</th>
                    <th lay-data="{field:'score', align: 'center'}">评分</th>
                    <th lay-data="{fixed: 'right', toolbar: '#barDemo', align: 'center'}">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${shoplist}" var="shop" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${shop.id}</td>
                        <td>${shop.name}</td>
                        <td>${shop.describe}</td>
                        <td>${shop.score}</td>
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
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="loadup">管理货架</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
</script>
<script>
    var existShop = '<%=request.getAttribute("existShop")%>';

    if(existShop =='true' ){
        layer.msg("该商铺名已经存在，修改失败", {icon: 2});
    }

    layui.use(['table'], function(){
        var table = layui.table;
        table.init('demo', {
            height: 400, //设置高度
            limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            //支持所有基础参数,
            ,page: true
        });
        //监听行工具事件
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            console.log(data.id);
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除要删除吗', function(index){
                    //
                    console.log("yes");
                    layer.msg("删除成功",{icon: 0});
                    // 调用删除方法
                    window.location.href="shopdel?id="+data.id;
                });
            } else if(obj.event === 'edit'){
                // 编辑
                window.location.href="shopupdate?id="+data.id;
                // 跳转界面
            } else if(obj.event === 'loadup'){
                // 上架商品
                window.location.href="shopgoodslist?id="+data.id;
            }
        });
    });


</script>
</body>

</html>
