<%--
  Created by IntelliJ IDEA.
  User: 家玮
  Date: 13-12-22
  Time: 下午4:17
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
<head>
    <title>登录</title>
    <r:require module="bootstrap"/>
    <r:layoutResources/>
    <style type="text/css">
    body {
        background: url(${resource(dir:'images',file: 'login_background.jpg')}) no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }

    .panel-default {
        opacity: 0.9;
        margin-top:30px;
    }
    .form-group.last { margin-bottom:0px; }
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> 登录</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${postUrl}" method="post">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-3 control-label">
                                邮箱</label>
                            <div class="col-sm-9">
                                <input type="email" name="username" class="form-control" id="inputEmail3" placeholder="您的邮箱" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-3 control-label">
                                密码</label>
                            <div class="col-sm-9">
                                <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="您的密码" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <div class="checkbox">
                                    <label>
                                        <input type='checkbox' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                        Remember me
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm">
                                    登 录</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    没有账号? <g:link controller="user" action="create">注册</g:link></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>