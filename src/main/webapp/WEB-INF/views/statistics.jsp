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
			<form class="form-inline" role="form">
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
					<input type="date" class="form-control" id="startDate" placeholder="起始日期">
				</div>
				<div class="form-group">
					<label for="endDate">截止日期:</label>
					<input type="date" class="form-control" id="endDate" placeholder="截止日期">
				</div>
				<div class="form-group">
					<a href="javascript:void(0)" id="search" class="btn btn-primary">数据查询</a>
				</div>
			</form>
			<br>
			<div class="table-responsive">
			<table class="table table-bordered table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th colspan="2"></th>
						<th colspan="4">发电数据</th>
						<th colspan="2">节能减排数据</th>
						<th colspan="4">收入数据</th>
					</tr>
					<tr>
						<th>序号</th>
						<th>时间</th>
						<th>发电量(KW)</th>
						<th>发电时间(M)</th>
						<th>最高发电时点</th>
						<th>日均发电时点(H)</th>
						<th>减少C排放(KG)</th>
						<th>减少CO2排放(KG)</th>
						<th>发电补贴收入</th>
						<th>节约用电收入</th>
						<th>余电上网收入</th>
						<th>总收入</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				<tfoot>
					<tr>
						<th>总计</th>
						<th></th>
						<td class="js-col3">0</td>
						<td class="js-col4">0</td>
						<td class="js-col5">0</td>
						<td class="js-col6">0</td>
						<td class="js-col7">0</td>
						<td class="js-col8">0</td>
						<td class="js-col9">0</td>
						<td class="js-col10">0</td>
						<td class="js-col11">0</td>
						<td class="js-col12">0</td>
					</tr>
				</tfoot>
			</table>
		</div>
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