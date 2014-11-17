<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
        />
        <title>
            高顺太阳能分布式发电监测系统
        </title>
        <!-- Bootstrap -->
        <link href="asserts/css/bootstrap.min.css" rel="stylesheet" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media
        queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file://
        -->
        <!--[if lt IE 9]>
            <script src="asserts/js/lib/html5shiv.min.js">
            </script>
            <script src="asserts/js/lib/respond.min.js">
            </script>
        <![endif]-->
        <link href="asserts/css/statistics.css" rel="stylesheet" />
    </head>
    <body>
        <header class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button class="navbar-toggle collapsed" type="button" data-toggle="collapse"
                    data-target=".bs-navbar-collapse">
                        <span class="sr-only">
                            Toggle navigation
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                    </button>
                    <a href="/" class="navbar-brand">
                        高顺太阳能分布式发电监测系统
                    </a>
                </div>
                <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/">
                                项目介绍
                            </a>
                        </li>
                        <li>
                            <a href="dashboard">
                                监控看板
                            </a>
                        </li>
                        <li class="active">
                            <a href="statistics">
                                数据统计
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
        <div class="container-fluid">
			<form id="form" class="form-inline" role="form" method="post">
				<div class="form-group">
					<a href="javascript:void(0)" id="searchToday" class="btn btn-primary">当日数据查询</a>
				</div>
				<div class="form-group">
					<a href="javascript:void(0)" id="searchMonth" class="btn btn-primary">当月数据查询</a>
				</div>
				<div class="form-group">
					<a href="javascript:void(0)" id="searchYear" class="btn btn-primary">当年数据查询</a>
				</div>
				<div class="form-group">
					<label for="startDate">起始日期:</label>
					<input type="date" class="form-control" id="startDate" name="startDate" value="${startDate }" placeholder="起始日期">
				</div>
				<div class="form-group">
					<label for="endDate">截止日期:</label>
					<input type="date" class="form-control" id="endDate" name="endDate" value="${endDate }" placeholder="截止日期">
				</div>
				<div class="form-group">
					<a href="javascript:void(0)" id="search" class="btn btn-primary">数据查询</a>
				</div>
			</form>
			<br>
			<section class="">
			<div class="table-container">
			<table class="table">
				<thead>
					<tr class="header">
						<th>序号<div>序号</div></th>
						<th>时间<div>时间</div></th>
						<th>发电量(KW)<div>发电量(KW)</div></th>
						<th>发电时间(小时)<div>发电时间(小时)</div></th>
						<th>最高发电时点<div>最高发电时点</div></th>
						<th>日均发电(KW)<div>日均发电(KW)</div></th>
						<th>减少C排放(KG)<div>减少C排放(KG)</div></th>
						<th>减少CO2排放(KG)<div>减少CO2排放(KG)</div></th>
						<th>发电补贴收入<div>发电补贴收入</div></th>
						<th>节约用电收入<div>节约用电收入</div></th>
						<th>余电上网收入<div>余电上网收入</div></th>
						<th>总收入<div>总收入</div></th>
					</tr>
				</thead>
				<tbody>
					<c:set var="powerSum" value="0"/>
					<c:set var="powerHourSum" value="0"/>
					<c:set var="saveCoalSum" value="0"/>
					<c:set var="saveCO2Sum" value="0"/>
					<c:set var="subsidiesSum" value="0"/>
					<c:set var="saveIncomeSum" value="0"/>
					<c:set var="surplusIncomeSum" value="0"/>
					<c:set var="totalIncomeSum" value="0"/>
					<c:forEach var="data" items="${dataList}" varStatus="dataStatus">
						<c:set var="powerSum" value="${powerSum + data.power}"/>
						<c:set var="powerHourSum" value="${powerHourSum + data.powerHour}"/>
						<c:set var="saveCoalSum" value="${saveCoalSum + data.saveCoal}"/>
						<c:set var="saveCO2Sum" value="${saveCO2Sum + data.saveCO2}"/>
						<c:set var="subsidiesSum" value="${subsidiesSum + data.subsidies}"/>
						<c:set var="saveIncomeSum" value="${saveIncomeSum + data.saveIncome}"/>
						<c:set var="surplusIncomeSum" value="${surplusIncomeSum + data.surplusIncome}"/>
						<c:set var="totalIncomeSum" value="${totalIncomeSum + data.subsidies + data.saveIncome + data.surplusIncome}"/>
						<tr>
							<td>${dataStatus.index + 1}</td>
							<td><fmt:formatDate value="${data.createDateTime}" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatNumber  value="${data.power}"  pattern="#0.00"/></td>
							<td>${data.powerHour}</td>
							<td><fmt:formatDate value="${data.powerMaxDateTime}" pattern="HH:mm:ss"/></td>
							<td><fmt:formatNumber  value="${data.avgPower}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.saveCoal}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.saveCO2}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.subsidies}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.saveIncome}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.surplusIncome}"  pattern="#0.00"/></td>
							<td><fmt:formatNumber  value="${data.subsidies + data.saveIncome + data.surplusIncome}"  pattern="#0.00"/></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>总计</th>
						<th></th>
						<td class="js-col3"><fmt:formatNumber  value="${powerSum}"  pattern="#0.00"/></td>
						<td class="js-col4"></td>
						<td class="js-col5"><fmt:formatNumber  value="${powerHourSum}"  pattern="#0.00"/></td>
						<td class="js-col6"></td>
						<td class="js-col7"><fmt:formatNumber  value="${saveCoalSum}"  pattern="#0.00"/></td>
						<td class="js-col8"><fmt:formatNumber  value="${saveCO2Sum}"  pattern="#0.00"/></td>
						<td class="js-col9"><fmt:formatNumber  value="${subsidiesSum}"  pattern="#0.00"/></td>
						<td class="js-col10"><fmt:formatNumber  value="${saveIncomeSum}"  pattern="#0.00"/></td>
						<td class="js-col11"><fmt:formatNumber  value="${surplusIncomeSum}"  pattern="#0.00"/></td>
						<td class="js-col12"><fmt:formatNumber  value="${totalIncomeSum}"  pattern="#0.00"/></td>
					</tr>
				</tfoot>
			</table>
		</div>
		</section>
		</div>
        <script src="http://php.weather.sina.com.cn/iframe/index/w_cl.php?day=0&charset=utf-8&city=%E9%A1%BA%E4%B9%89">
        </script>
        <script src="asserts/js/lib/jquery-1.11.1.min.js">
        </script>
        <script src="asserts/js/lib/bootstrap.min.js">
        </script>
        <script src="asserts/js/lib/gauge.min.js">
        </script>
        <script src="asserts/js/lib/Chart.min.js">
        </script>
        <script src="asserts/js/sys_config.js">
        </script>
        <script src="asserts/js/data_config.js">
        </script>
        <script src="asserts/js/statistics.js">
        </script>
    </body>

</html>