<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>订单列表</title>
    <script src="./layui/layui/layui.js"></script>
    <script src="./js/jquery.js"></script>
    <link type="text/css" rel="stylesheet" href="./layui/layui/css/layui.css" />
    <link rel="stylesheet" href="./layui/layui/css/layui.css">
</head>

<body>

<div class="layui-layout layui-layout-admin">
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

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="main">首页</a></li>

                <li class="layui-nav-item ">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="goodslist">商品列表</a></dd>
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
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="orderlist">订单列表</a></dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px">
            <div class="weadmin-nav" style="padding-bottom: 15px">
            <span class="layui-breadcrumb">
                <a href="main">首页</a>
                <a href="orderlist">订单管理</a>
                <a>
                    <cite>订单列表</cite></a>
            </span>

            </div>
            <div class="weadmin-body">
                <div class="layui-row">
                    <form class="layui-form layui-col-md12 we-search" action="orderlist" method="post">
                        <div class="layui-input-inline">
                            <select name="paystatus">
                                <option value="">支付状态</option>
                                <option value="已支付">已支付</option>
                                <option value="未支付">未支付</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <select name="payway">

                                <option value="">支付方式</option>
                                <option value="支付宝">支付宝</option>
                                <option value="微信">微信</option>
                                <option value="货到付款">货到付款</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <select name="orderstatus">
                                <option value="">订单状态</option>
                                <option value="待确认">待确认</option>
                                <option value="已确认">已确认</option>
                                <option value="已收货">已收货</option>
                                <option value="已取消">已取消</option>
                                <option value="已完成">已完成</option>
                                <option value="已作废">已作废</option>
                            </select>
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="id" placeholder="请输入订单号" autocomplete="off" class="layui-input">
                        </div>
                        <button class="layui-btn" lay-submit="" lay-filter="sreach"><i
                                class="layui-icon layui-icon-search"></i></button>
                    </form>
                </div>
                <table class="layui-table" lay-filter="demo" >
                    <thead>
                    <tr>
                        <th lay-data="{field:'status', align: 'center'}">序号</th>
                        <th lay-data="{field:'id', align: 'center'}">订单号</th>
                        <th lay-data="{field:'receive',align: 'center'}">收货人</th>
                        <th lay-data="{field:'money', align: 'center'}">总金额</th>
                        <th lay-data="{field:'orderstatus', align: 'center'}">订单状态</th>
                        <th lay-data="{field:'paystatus', align: 'center'}">支付状态</th>
                        <th lay-data="{field:'payway', align: 'center'}">支付方式</th>
                        <th lay-data="{field:'send', align: 'center'}">配送方式</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderlist}" var="order" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${order.id}</td>
                            <td>${order.receive}:${order.receivephone}</td>
                            <td>${order.money}</td>
                            <td>${order.orderstatus}</td>
                            <td>${order.paystatus}</td>
                            <td>${order.payway}</td>
                            <td>${order.send}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        电子商城
    </div>
</div>

<script>
    layui.use(['table'], function() {
        var table = layui.table;
        table.init('demo', {
            height: 400, //设置高度
            limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            //支持所有基础参数,
            , page: true
        });
    });
</script>
</body>


</html>
