<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" ng-app="dashBoardApp">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>高顺太阳能分布式发电监测系统</title>
<!-- Bootstrap -->
<link href="/asserts/css/bootstrap.min.css" rel="stylesheet" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media
        queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file://
        -->
<!--[if lt IE 9]>
            <script src="/asserts/html5shiv/dist/html5shiv.min.js">
            </script>
            <script src="js/lib/respond.min.js">
            </script>
        <![endif]-->
<body>
	<h2>设置页面</h2>

	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion" href="#saveCO2">
							发电量&太阳高度设定</a>
					</h4>
				</div>
				<div id="saveCO2" class="panel-collapse collapse in">
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>整点时间</th>
									<th>太阳高度</th>
									<th>发电量(KW)</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${powerList}" var="powerDefault">
								<tr>
									<td>${powerDefault.hour}时</td>
									<td><input type="text" value="${powerDefault.sunHeight}"/></td>
									<td><input type="text" value="${powerDefault.power}"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">.col-md-4</div>
	</div>
	<div class="row">
		<div class="col-md-4">.col-md-4</div>
		<div class="col-md-4">.col-md-4</div>
		<div class="col-md-4">.col-md-4</div>
	</div>
	<div class="row">
		<div class="col-md-6">.col-md-6</div>
		<div class="col-md-6">.col-md-6</div>
	</div>
</body>
</html>


