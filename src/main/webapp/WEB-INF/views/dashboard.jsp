<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <link href="asserts/css/dashboard.css" rel="stylesheet" />
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
                        <li class="active">
                            <a href="dashboard">
                                监控看板
                            </a>
                        </li>
                        <li>
                            <a href="statistics">
                                数据统计
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
        <div class="container-fluid">
            <div class="row">
				<!-- left warp -->
                <div class="col-xs-12 col-md-6">
                    <div class="row">
						<!-- weather warp -->
                        <div class="col-xs-12 col-md-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#weather">
                                            天气
                                        </a>
                                    </h4>
                                </div>
                                <div id="weather" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <table class="table table-hover table-condensed">
                                            <tr>
                                                <th>
                                                    今天白天：
                                                </th>
                                                <td id="dayWeather" colspan="2">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    今天夜间：
                                                </th>
                                                <td id="nightWeather" colspan="2">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    温度：
                                                </th>
                                                <td>
                                                    高:<span id="t1"></span>°C
                                                </td>
                                                <td>
                                                    低:<span id="t2"></span>°C
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    风：
                                                </th>
                                                <td>
                                                    风向:<span id="d1"></span>
                                                </td>
                                                <td>
                                                    风力:<span id="p1"></span>
                                                </td>
                                            </tr>
                                            <tr>
												<td id="weatherUpdate"colspan="3"></td>
											</tr>
                                            
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#stats">
                                            统计
                                        </a>
                                    </h4>
                                </div>
                                <div id="stats" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <table class="table table-hover table-condensed">
                                            <tr>
                                                <th>
                                                    系统装机容量：
                                                </th>
                                                <td id="systemTotal" class="">
                                                    0
                                                </td>
                                                <td>
                                                    Kw
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    今日总发电量：
                                                </th>
                                                <td id="todayTotalPower" class="">
                                                    0
                                                </td>
                                                <td>
                                                    度
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    今年总发电量：
                                                </th>
                                                <td id="thisYearTotalPower" class="">
                                                    0
                                                </td>
                                                <td>
                                                    度
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    生涯总发电量：
                                                </th>
                                                <td id="careerTotalPower" class="">
                                                    0
                                                </td>
                                                <td>
                                                    度
                                                </td>
                                            </tr>
                                        </table>
										<section id="remark">
											<small>备注: 本示范屋顶面积100m<sup>2</sup>, 以每平米100W安装容量计算,每天按8小时计算,每小时100m<sup>2</sup>用电3度</small>
										</section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="row">
                        <div class="col-xs-6 col-md-3">
                            <div class="panel panel-default gauge">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#power">
                                            发电即时功率
                                        </a>
                                    </h4>
                                </div>
                                <div id="power" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <canvas id="realTimeGauge">
                                        </canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-md-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#waste">
                                            耗用电量电表
                                        </a>
                                    </h4>
                                </div>
                                <div id="waste" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <canvas id="consumeGauge">
                                        </canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-md-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#saveCO">
                                            节约煤炭
                                        </a>
                                    </h4>
                                </div>
                                <div id="saveCO" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <p><h5>今日 Today</h5></p>
                                        <p class="tc">
                                            <span id="todaySaveCoal" class="number">
                                                0
                                            </span>
                                            kg
                                        </p>
                                        <p>
                                            <h5>
                                                生涯 Career
                                            </h5>
                                        </p>
                                        <p class="tc">
                                            <span id="careerSaveCoal" class="number">
                                                123123
                                            </span>
                                            kg
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-md-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#saveCO2">
                                            减少CO2排放
                                        </a>
                                    </h4>
                                </div>
                                <div id="saveCO2" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <p>
                                            <h5>
                                                今日 Today
                                            </h5>
                                        </p>
                                        <p class="tc">
                                            <span id="todaySaveCO2" class="number">
                                                0
                                            </span>
                                            kg
                                        </p>
                                        <p>
                                            <h5>
                                                生涯 Career
                                            </h5>
                                        </p>
                                        <p class="tc">
                                            <span id="careerSaveCO2" class="number">
                                                0
                                            </span>
                                            kg
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-md-6">
					<div class="row">
						<div class="col-xs-12 col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#saveCO2">
                                    收入数据
                                </a>
                            </h4>
                        </div>
                        <div id="saveCO2" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4 col-md-3">
                                        <div class="solar-panel">
                                            <a href="javascript:void(0)">
                                                <img src="${pageContext.request.contextPath}/asserts/img/solar-panel.jpg" />
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-md-3">
                                        <div class="solar-panel">
                                            <a href="javascript:void(0)">
                                                <img src="${pageContext.request.contextPath}/asserts/img/solar-panel.jpg" />
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-md-3">
                                        <div class="solar-panel">
                                            <a href="javascript:void(0)">
                                                <img src="${pageContext.request.contextPath}/asserts/img/solar-panel.jpg" />
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-3 tc">
                                        <h4 style="font-size:20px">
                                            生涯总收入
                                        </h4>
                                        <p>
                                            <span class="number careerIncomeTotal red">
                                                0
                                            </span>
                                            元
                                        </p>
                                    </div>
                                </div>
                                <table id="moneyTable" class="table">
                                    <thead>
                                        <tr>
                                            <th>
                                            </th>
                                            <th>
                                                <img class="pig" src="${pageContext.request.contextPath}/asserts/img/pig.png" />
                                                <img class="coin" src="${pageContext.request.contextPath}/asserts/img/coin.gif" />
                                            </th>
                                            <th>
                                                <img class="pig" src="${pageContext.request.contextPath}/asserts/img/pig.png" />
                                                <img class="coin" src="${pageContext.request.contextPath}/asserts/img/coin.gif" />
                                            </th>
                                            <th>
                                                <img class="pig" src="${pageContext.request.contextPath}/asserts/img/pig.png" />
                                                <img class="coin" src="${pageContext.request.contextPath}/asserts/img/coin.gif" />
                                            </th>
                                            <th>
                                                <img class="pig" src="${pageContext.request.contextPath}/asserts/img/Coins.png" />
                                                <img class="coin" src="${pageContext.request.contextPath}/asserts/img/coin.gif" />
                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td>
                                                当日发电补贴收入
                                            </td>
                                            <td style="color:red">
                                                用电节约收入
                                            </td>
                                            <td>
                                                余电上网收入
                                            </td>
                                            <td>
                                                总收入
                                            </td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td style="font-size:14px;padding-left:4px" class="tl">
                                                当日发电收入
                                            </td>
                                            <td>
                                                <span class="todaySubsidies number">
                                                    0
                                                </span>
                                                元
                                                <input class="todaySubsidies" style="display:none" value="" />
                                            </td>
                                            <td>
                                                <span class="todaySaveIncome number">
                                                    0
                                                </span>
                                                元
                                                <input class="todaySubsidies" style="display:none" value="" />
                                            </td>
                                            <td>
                                                <span class="todaySurplusIncome number">
                                                    0
                                                </span>
                                                元
                                                <input class="todaySubsidies" style="display:none" value="" />
                                            </td>
                                            <td>
                                                <span class="todayIncomeTotal number">
                                                    0
                                                </span>
                                                元
                                                <input class="todaySubsidies" style="display:none" value="" />
                                            </td>
                                        </tr>
                                        <tr id="moneyRemark">
                                            <td style="font-size:14px;padding-left:4px" class="tl">
                                                当日发电收入算法
                                            </td>
                                            <td>
                                                发电度数 x 0.42
                                                <br>
                                                【发改委补贴标准】
                                            </td>
                                            <td>
                                                用电度数 x 1 元
                                            </td>
                                            <td>
                                                余电上网度数 x 0.4元
                                                <br>
                                                【供电脱硫电价】
                                            </td>
                                            <td>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size:14px;padding-left:4px" class="tl">
                                                生涯总收入
                                            </td>
                                            <td>
                                                <span id="careerSubsidies" class="number">
                                                    0
                                                </span>
                                                元
                                            </td>
                                            <td>
                                                <span id="careerSaveIncome" class="number">
                                                    0
                                                </span>
                                                元
                                            </td>
                                            <td>
                                                <span id="careerSurplusIncome" class="number">
                                                    0
                                                </span>
                                                元
                                            </td>
                                            <td>
                                                <span class="number careerIncomeTotal">
                                                    0
                                                </span>
                                                元
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
			</div>
		</div>
                <div class="col-xs-12 col-md-6">
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#yesterDayPower">
                                            昨日累计发电量(度)
                                        </a>
                                    </h4>
                                </div>
                                <div id="yesterDayPower" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <p id="yesterdayTotalPower" class="number">
                                            0
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#lastWeekPower">
                                            上周累计发电量(度)
                                        </a>
                                    </h4>
                                </div>
                                <div id="lastWeekPower" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <p id="lastWeekTotalPower" class="number">
                                            0
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#lastMonthPower">
                                            上月累计发电量(度)
                                        </a>
                                    </h4>
                                </div>
                                <div id="lastMonthPower" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <p id="lastMonthTotalPower" class="number">
                                            0
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#sunChart">
                                    日照强度变化
                                </a>
                            </h4>
                        </div>
                        <div id="sunChart" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <canvas id="chart" height="100" width="300">
                                </canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="http://php.weather.sina.com.cn/iframe/index/w_cl.php?day=0&charset=utf-8&city=%E9%A1%BA%E4%B9%89">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/lib/jquery-1.11.1.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/lib/bootstrap.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/lib/gauge.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/lib/Chart.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/sys_config.js">
        </script>
        <script src="${pageContext.request.contextPath}/asserts/js/dashboard.js">
        </script>
    </body>

</html>