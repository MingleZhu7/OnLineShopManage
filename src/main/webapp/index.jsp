<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商城管理</title>
    <script src="./layui/layui/layui.js"></script>
    <script src="./js/jquery.js"></script>
    <link type="text/css" rel="stylesheet" href="./layui/layui/css/layui.css" />
    <link rel="stylesheet" href="./layui/layui/css/layui.css">
    <style>
        .layui-top-box {padding:40px 20px 20px 20px;color:#fff}
        .panel {margin-bottom:17px;background-color:#fff;border:1px solid transparent;border-radius:3px;-webkit-box-shadow:0 1px 1px rgba(0,0,0,.05);box-shadow:0 1px 1px rgba(0,0,0,.05)}
        .panel-body {padding:15px}
        .panel-title {margin-top:0;margin-bottom:0;font-size:14px;color:inherit}
        .label {display:inline;padding:.2em .6em .3em;font-size:75%;font-weight:700;line-height:1;color:#fff;text-align:center;white-space:nowrap;vertical-align:baseline;border-radius:.25em;margin-top: .3em;}
        .layui-red {color:red}
        .main_btn > p {height:40px;}
    </style>
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

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="goodslist">商品列表</a></dd>
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
        <div class="layuimini-main layui-top-box" style="padding-left: 100px">
            <div class="layui-row layui-col-space30">

                <div class="layui-col-md3">
                    <div class="col-xs-6 col-md-3">
                        <div class="panel layui-bg-cyan">
                            <div class="panel-body">
                                <div class="panel-title">
                                    <span class="label pull-right layui-bg-blue">实时</span>
                                    <h5>商铺统计</h5>
                                </div>
                                <div class="panel-content">
                                    <h1 class="no-margins">${map.shopcount}</h1>
                                    <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> ${map.shopcount}</div>
                                    <small>当前商铺总记录数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md3">
                    <div class="col-xs-6 col-md-3">
                        <div class="panel layui-bg-blue">
                            <div class="panel-body">
                                <div class="panel-title">
                                    <span class="label pull-right layui-bg-cyan">实时</span>
                                    <h5>商品统计</h5>
                                </div>
                                <div class="panel-content">
                                    <h1 class="no-margins">${map.goodscount}</h1>
                                    <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> ${map.goodscount}</div>
                                    <small>当前商品总记录数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md3">
                    <div class="col-xs-6 col-md-3">
                        <div class="panel layui-bg-green">
                            <div class="panel-body">
                                <div class="panel-title">
                                    <span class="label pull-right layui-bg-orange">实时</span>
                                    <h5>商品类型</h5>
                                </div>
                                <div class="panel-content">
                                    <h1 class="no-margins">${map.typecount}</h1>
                                    <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> ${map.typecount}</div>
                                    <small>当前分类总记录数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md9">
                    <div class="col-xs-6 col-md-3">
                        <div class="panel layui-bg-orange">
                            <div class="panel-body">
                                <div class="panel-title">
                                    <span class="label pull-right layui-bg-green">实时</span>
                                    <h5>订单统计</h5>
                                </div>
                                <div class="panel-content">
                                    <h1 class="no-margins">${map.ordercount}</h1>
                                    <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> ${map.ordercount}</div>
                                    <small>当前订单总记录数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="col-xs-6 col-md-3">
                        <div class="panel layui-bg-cyan">
                            <div class="panel-body">
                                <div class="panel-title">
                                    <span class="label pull-right layui-bg-blue">实时</span>
                                    <h5>上架统计</h5>
                                </div>
                                <div class="panel-content">
                                    <h1 class="no-margins">${map.shopgoodscount}</h1>
                                    <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> ${map.shopgoodscount}</div>
                                    <small>当前上架商品总记录数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            电子商城
        </div>
    </div>
</div>
</body>

</html>
