<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表</title>


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
                    <a class="" href="javascript:;">商品</a>
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
                <a href="goodslist">商铺管理</a>
                <a>
                    <cite>货架列表</cite></a>
            </span>

        </div>
        <hr>
        <div >
            <label style="padding: 15px">${shop.name}的货架</label>
        </div>

        <!-- 内容主体区域 -->
        <form class="layui-form" action="shopgoodslist" method="post">
            <input type="hidden" id="Sid"  name="Sid" value="${shop.id}"/>
            <input  type="hidden" id="Sname"  name="Sname" value="${shop.name}"/>
            <label style="padding: 15px">类型</label>
            <div class="layui-input-inline">

                <select name="type">


                    <option value="">请选择商品类型</option>
                    <c:forEach items="${typelist}" var="type" varStatus="status">
                        <option value=${type.typename}>${type.typename}</option>
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

        <div class="layui-btn-container" style="padding: 15px;">
            <button class="layui-btn layui-btn-sm"  onclick="loadup()">上架商品</button>
            <button class="layui-btn layui-btn-sm"  onclick="updatetable()">更新货架</button>
        </div>

        <div style="text-align:left">
            <table class="layui-table" lay-filter="demo" >
                <thead>
                <tr>
                    <th lay-data="{field:'status', align: 'center'}">序号</th>
                    <th lay-data="{field:'id', align: 'center'}">商品编号</th>
                    <th lay-data="{field:'name', align: 'center'}">商品名</th>
                    <th lay-data="{field:'type',align: 'center'}">类型</th>
                    <th lay-data="{field:'price', align: 'center'}">价格</th>
                    <th lay-data="{fixed: 'right', toolbar: '#barDemo', align: 'center'}">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goodslist}" var="goods" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${goods.id}</td>
                        <td>${goods.name}</td>
                        <td>${goods.type}</td>
                        <td>${goods.price}</td>
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
    <a class="layui-btn layui-btn-sm" lay-event="del" >下架</a>
</script>
<script>
    var existGoods = '<%=request.getAttribute("existGoods")%>';

    if(existGoods == "true"){
        layer.msg("该商品名已经存在，修改失败", {icon: 2});
    }
    layui.use(['table','form'], function(){
        var table = layui.table;
        table.init('demo', {
            height: 380, //设置高度
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
                layer.confirm('真的要下架该商品吗', function(index){
                    //

                    console.log("yes");
                    layer.msg("下架成功",{icon: 0});
                    var Sid = document.getElementById('Sid').value
                    console.log(Sid);
                    // 调用删除方法
                    window.location.href="shopgoodsdel?Gid="+data.id+"&&Sid="+Sid;
                });
            } else if(obj.event === 'edit'){
                // 编辑
                window.location.href="goodsupdate?id="+data.id;
                // 跳转界面
            } else if(obj.event === 'loadup'){
                // 上架商品
                WeAdminShow("商品"+data.name+"上架","goodsloadup?Gid="+data.id,600,300);
                // window.open("pages/goods/goodsloadup.jsp");
            }
        });
        window.WeAdminShow = function (title, url, w, h) {
            if (title == null || title == '') {
                title = false;
            };
            if (url == null || url == '') {
                url = "404.html";
            };
            if (w == null || w == '') {
                w = ($(window).width() * 0.9);
            };
            if (h == null || h == '') {
                h = ($(window).height() - 50);
            };
            layer.open({
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: title,
                content: url,
                // btn: ['完成'], //按钮组
                // yes: function(index) {//layer.msg('yes');    //点击确定回调
                //     var Sid = document.getElementById('Sid').value
                //     layer.close(index);
                //
                // }
            });
        }
    });
    loadup= function(){
        var Sid = document.getElementById('Sid').value;
        var Sname = document.getElementById('Sname').value;
        WeAdminShow(Sname+"的货架","shoploadup?Sid="+Sid,600,300);
    };
    updatetable = function (){
        var Sid = document.getElementById('Sid').value;
        window.location.href ="shopgoodslist?id="+Sid;
    }


</script>
</body>

</html>