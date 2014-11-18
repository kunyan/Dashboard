var todayIncrease,
    todayTotal,
    thisYearTotal,
    careerTotal,
    yesterdayTotal,
    lastWeekTotal,
    lastMonthTotal,
    todaySaveCoal,
    careerSaveCoal,
    todaySaveCO2,
    barChart,
    careerSaveCO2,
    hour,
    dataConfig,
    chartData = [];

var data = {
	weater: null,
	careerTotalPower: 0,
    thisYearTotalPower: 0,
    lastMonthTotalPower: 0,
    lastWeekTotalPower: 0,
    yesterdayTotalPower: 0,
    todayTotalPower: 0,
    sunPowerData: null
}


function showRealTimeGauge() {
    var gauge;
    gauge = new Gauge({
        renderTo: "realTimeGauge",
        width: $('#power').width(),
        height: $('#power').height()-30,
        glow: true,
        units: 'Kw/h',
        title: false,
        minValue: sysConfig.powerGuageConfig.minValue,
        maxValue: sysConfig.powerGuageConfig.maxValue,
        majorTicks: false,
        minorTicks: false,
        strokeTicks: false,
        valueFormat: sysConfig.powerGuageConfig.valueFormat,
        highlights: sysConfig.powerGuageConfig.highlights,
        colors: {
            plate: '#fff',
            majorTicks: '#fff',
            minorTicks: '#ddd',
            title: '#fff',
            units: '#000',
            numbers: '#202020',
            needle: {
                start: 'rgba(32, 32, 32, 1)',
                end: 'rgba(32, 32, 32, .9)'
            }
        }
    });

    gauge.draw();
    return gauge;
}

function showConsumeGauge() {
    var gauge;
    gauge = new Gauge({
        renderTo: "consumeGauge",
        width: $('#waste').width(),
        height: $('#waste').height()-30,
        glow: true,
        units: 'Kw/h',
        title: false,
        minValue: 0,
        maxValue: data.sunPowerData.length * 3,
        majorTicks: ['0','5','10','15','20','25', '30'],
        minorTicks: false,
        strokeTicks: false,
        valueFormat: sysConfig.consumptionGuageConfig.valueFormat,
        highlights: [{ //区域及颜色设置
            from: 0,
            to: 10,
            color: '#7A9B00'
        }, {
            from: 10,
            to: 20,
            color: '#F8C538'
        }, {
            from: 20,
            to: data.sunPowerData.length * 3,
            color: '#F5584A'
        }],
        colors: {
            plate: '#fff',
            majorTicks: '#614d0e',
            minorTicks: '#ddd',
            title: '#fff',
            units: '#000',
            numbers: '#202020',
            needle: {
                start: 'rgba(32, 32, 32, 1)',
                end: 'rgba(32, 32, 32, .9)'
            }
        }
    });
    gauge.setValue(getUsedToday());
    gauge.draw();
    return gauge;
};

function getIncrease(min, max) {
    return Math.ceil(Math.random() * (max - min) + min)
}


function chart(data) {
    var ctx = document.getElementById("chart").getContext("2d");
    barChart = new Chart(ctx).Bar(data, {
        responsive: true,
        barValueSpacing: 10
    }).update();
}


var realTimeGauge,
    consumeGauge;

function getIncrease() {
    var now = new Date(),
        hour = now.getHours(),
        minutes = now.getMinutes(),
        seconds = now.getSeconds();

    var increase = 0;
    for (var i = 0; i < data.sunPowerData.length; i++) {
        if (hour == data.sunPowerData[i].hour) { //当前小时符合数据小时
            if ((i + 1) < data.sunPowerData.length) { //当前数据不是最后一个
                increase = increase + data.sunPowerData[i + 1].power / 3600 * (seconds + minutes * 60);
            }
            break;
        }
    }
    return increase;
}
//节约煤炭
function getSaveCoal(val) {
    return val * 0.4;
}
//节约二氧化碳
function getSaveCO2(val) {
    return val * 0.96;
}
//发电补贴收入
function getSubsidies(val){
    return val * 0.42;
}
//今日用电
function getUsedToday(){
	
	var now = new Date(),
		seconds = now.getMinutes() * 60 + now.getSeconds();
		msStartDiff = now.getTime() - new Date(data.weather.sunRiseTime).getTime(),
		msEndDiff = now.getTime() - new Date(data.weather.sunDownTime).getTime();
	if (msStartDiff >= 0) {
		if (msEndDiff <= 0) {
			return seconds * (3/60/60);
		}
		
	}
	return 0;
}
//用电节约收入
function getSaveIncome(val) {
    return val;
}
//余电上网收入
function getSurplusIncome(power, used){
    return (power - used) > 0 ? (power - used) * 0.4 : 0;
}
function render() {
    var increase = getIncrease();
    //统计部分
    $("#todayTotalPower").text(toDecimal2(data.todayTotal.power + increase));
    $("#thisYearTotalPower").text(toDecimal2(data.thisYearTotal.power  + increase));
    $("#careerTotalPower").text(toDecimal2(data.careerTotal.power + increase));

    //节约部分
    $("#todaySaveCoal").text(toDecimal2(getSaveCoal(data.todayTotal.power + increase)));
    $("#careerSaveCoal").text(toDecimal2(getSaveCoal(data.careerTotal.power + increase)));
    $("#todaySaveCO2").text(toDecimal2(getSaveCO2(data.todayTotal.power + increase)));
    $("#careerSaveCO2").text(toDecimal2(getSaveCO2(data.careerTotal.power + increase)));
    
    
    $(".todaySubsidies").text(toDecimal2(getSubsidies(data.todayTotal.power + increase)));
    $(".todaySubsidies").val(toDecimal2(getSubsidies(data.todayTotal.power + increase)));
    $(".todaySaveIncome").text(toDecimal2(getSaveIncome(data.todayTotal.used + getUsedToday())));
    $(".todaySaveIncome").val(toDecimal2(getSaveIncome(data.todayTotal.used + getUsedToday())));
    $(".todaySurplusIncome").text(toDecimal2(getSurplusIncome(data.todayTotal.power + increase, data.todayTotal.used + getUsedToday())));
    $(".todaySurplusIncome").val(toDecimal2(getSurplusIncome(data.todayTotal.power +increase, data.todayTotal.used + getUsedToday())));
    $(".todayIncomeTotal").text(toDecimal2(Number($(".todaySubsidies").val()) + Number($(".todaySaveIncome").val()) + Number($(".todaySurplusIncome").val())));
    
    
    
    
    $("#careerSubsidies").text(toDecimal2(getSubsidies(data.careerTotal.power + increase)));
    $("#careerSaveIncome").text(toDecimal2(getSaveIncome(getUsedToday()) + data.careerTotal.used));
    $("#careerSurplusIncome").text(toDecimal2(getSurplusIncome((data.careerTotal.power + increase) , (data.careerTotal.used + getUsedToday()))));
    $(".careerIncomeTotal").text(toDecimal2(Number($("#careerSubsidies").text()) + Number($("#careerSaveIncome").text()) + Number($("#careerSurplusIncome").text())));

    if (isSunExsits()){
    	$("img.pig").next("img").show().animate({
            top:"-5px"
        }, 1000, function(){
            $(this).hide();
            $(this).css({"top":"-45px"});
        });
    }
    
}


$(document).ready(function(){
	$.getJSON("/api/data.json",function(response){
		data = eval(response);
		$("#systemTotal").text(10);
	    $("#yesterdayTotalPower").text(data.yesterdayTotal.power);
	    $("#lastWeekTotalPower").text(data.lastWeekTotal.power);
	    $("#lastMonthTotalPower").text(data.lastMonthTotal.power);
	    chart(refreshCharData());
	    realTimeGauge = showRealTimeGauge();
	    consumeGauge = showConsumeGauge();
	    refresh();
	    render();
	    
	    setWeather();
	});
	
    
});

function setWeather(){
	 for(var i in SWther.w){
	     $("#dayWeather").text(SWther.w[i][0].s1);
	     $("#nightWeather").text(SWther.w[i][0].s2);
	
	     $("#t1").text(SWther.w[i][0].t1);
	     $("#t2").text(SWther.w[i][0].t2);
	     $("#p1").text(SWther.w[i][0].p1);
	     $("#d1").text(SWther.w[i][0].d1);
	
	 	$("#weatherUpdate").text(SWther.add.update);
	  }
}
function refreshCharData(){
    var now = new Date(),
        hour = now.getHours(),
        minutes = now.getMinutes(),
        seconds = now.getSeconds();
        
    var barChartData = {
        labels: [],
        datasets: [{
            fillColor: "#F8C538",
            strokeColor: "#F8C538",
            highlightFill: "#F55854",
            highlightStroke: "#F55854",
            data: []
        }]

    }
    
    if (hour < data.sunPowerData[0].hour) {
        for (var i = 0; i < data.sunPowerData.length; i++) {
            if (data.sunPowerData[i].power != 0) {
                barChartData.labels.push(data.sunPowerData[i].hour + "时");
                barChartData.datasets[0].data.push(0);
            }
        }
    }
    
    if (hour >= data.sunPowerData[0].hour) {
        for (var i = 0; i < data.sunPowerData.length; i++) {
            if (data.sunPowerData[i].power != 0) {
                if (data.sunPowerData[i].hour <= hour) {
                    barChartData.labels.push(data.sunPowerData[i].hour + "时");
                    barChartData.datasets[0].data.push(data.sunPowerData[i].sunHeight);
                } else {
                    barChartData.labels.push(data.sunPowerData[i].hour + "时");
                    barChartData.datasets[0].data.push(0);
                }
            }
        }
    } 
    return barChartData;
}
function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(x * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}

function isSunExsits() {
	var now = new Date();
	return now >= new Date(data.weather.sunRiseTime) && now <= new Date(data.weather.sunDownTime)
}

function refresh() {
    setInterval(function() {
    	isSunExsits() ? render(): 0;
    }, sysConfig.textRefreshTime * 1000);
    
    //刷新仪表盘
    setInterval(function() {
        var now = new Date(),
            hour = now.getHours(),
            minutes = now.getMinutes(),
            seconds = now.getSeconds();
        
        var  current = 0; 
        for (var i = 0; i < data.sunPowerData.length; i++) {
            
            if (data.sunPowerData[i].power != 0) {
                if (hour == data.sunPowerData[i].hour) { //当前小时符合数据小时
                    current = data.sunPowerData[i].power;
                    if ((i + 1) <= data.sunPowerData.length) { //当前数据不是最后一个
                        current = current + (data.sunPowerData[i + 1].power - data.sunPowerData[i].power) / 3600 * (seconds + minutes * 60);
                    
                    }
                    realTimeGauge.setValue(Math.random() * ((current - 0.02) - (current + 0.02)) + (current - 0.02));
                    break;
                } 
            }
        }

        consumeGauge.setValue(data.todayTotal.used + getUsedToday());

    }, sysConfig.guageRefreshTime * 1000);


}
