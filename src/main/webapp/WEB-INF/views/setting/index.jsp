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
<link href="${pageContext.request.contextPath}/asserts/css/bootstrap.min.css" rel="stylesheet" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media
        queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file://
        -->
<!--[if lt IE 9]>
            <script src="${pageContext.request.contextPath}/asserts/html5shiv/dist/html5shiv.min.js">
            </script>
            <script src="${pageContext.request.contextPath}/js/lib/respond.min.js">
            </script>
        <![endif]-->
<body>
	<h2>设置页面</h2>

	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion" href="#sunHeightConfig">
							发电量&太阳高度设定</a>
					</h4>
				</div>
				<div id="sunHeightConfig" class="panel-collapse collapse in">
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>整点时间</th>
									<th>太阳高度</th>
									<th>发电量(KW)</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${powerList}" var="powerDefault">
								<tr>
									<td>${powerDefault.hour}时<input class="hour" type="hidden" value="${powerDefault.hour}"></td>
									<td><input class="sunHeight" type="text" value="${powerDefault.sunHeight}"/></td>
									<td><input class="power" type="text" value="${powerDefault.power}"/></td>
									<td><input class="js-update-power" type="button" value="更新"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion" href="#weatherConfig">
							天气情况设定</a>
					</h4>
				</div>
				<div id="Config" class="panel-collapse collapse in">
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>天气类型</th>
									<th>发电百分比</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${weatherList}" var="weatherType">
								<tr>
									<td>${weatherType.chinese}<input class="id" type="hidden" value="${weatherType.id}"></td>
									<td><input class="rate" type="text" value="${weatherType.rate}"/>%</td>
									<td><input class="js-update-weather" type="button" value="更新"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/asserts/js/lib/jquery-1.11.1.min.js">
        </script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".js-update-power").on("click", function(){
			var hour = $(this).parents("tr").find(".hour").val();
			var sunHeight = $(this).parents("tr").find(".sunHeight").val();
			var power = $(this).parents("tr").find(".power").val();
			
			$.ajax({
				  url: "${pageContext.request.contextPath}/setting/power/" + hour + "/" + sunHeight + "/" + power,
				  type: "POST"
				}).done(function() {
					alert("更新成功");
				});
			
		});
		
		$(".js-update-weather").on("click", function(){
			var id = $(this).parents("tr").find(".id").val();
			var rate = $(this).parents("tr").find(".rate").val();
			
			$.ajax({
				  url: "${pageContext.request.contextPath}/setting/weather/" + id + "/" + rate,
				  type: "POST"
				}).done(function() {
					alert("更新成功");
				});
			
		});
	});
	</script>
</body>
</html>


