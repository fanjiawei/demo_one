<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>注册</title>
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
                    <span class="glyphicon glyphicon-lock"></span> 注册账号</div>
                <div class="panel-body">
                    <g:form class="form-horizontal" role="form" action="register" method="post">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-3 control-label">
                                <g:message code="user.username.label"/> </label>
                            <div class="col-sm-9">
                                <input type="email" name="username" class="form-control" id="inputEmail3" placeholder="you@example.com" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-3 control-label">
                                <g:message code="user.password.label"/> </label>
                            <div class="col-sm-9">
                                <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="大于6位,小于18位" required>
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-success btn-lg col-sm-12">
                                    <g:message code="user.create.label"/> </button>
                            </div>
                        </div>
                    </g:form>
                </div>
                <div class="panel-footer">
                    已有账号? <g:link controller="login" action="auth"><g:message code="login.label"/></g:link></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>