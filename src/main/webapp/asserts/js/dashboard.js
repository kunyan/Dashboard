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
    chartData = [];


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
        minValue: sysConfig.consumptionGuageConfig.minValue,
        maxValue: sysConfig.consumptionGuageConfig.maxValue,
        majorTicks: ['0','5','10','15','20','25'],
        minorTicks: false,
        strokeTicks: false,
        valueFormat: sysConfig.consumptionGuageConfig.valueFormat,
        highlights: sysConfig.consumptionGuageConfig.highlights,
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
    for (var i = 0; i < chartData.length; i++) {
        increase = increase + chartData[i].power;
        if (hour == chartData[i].hour) { //当前小时符合数据小时
            if ((i + 1) < chartData.length) { //当前数据不是最后一个
                increase = increase + chartData[i + 1].power / 3600 * (seconds + minutes * 60);
            }
            break;
        }
    }
    return increase * sysConfig.weather;
}

function getSaveCoal(val) {
    return val * 0.4;
}

function getSaveCO2(val) {
    return val * 0.96;
}

function getTodaySubsidies(val){
    return val * 0.42;
}
function getUsedToday(){
    var now = new Date(),
        hour = now.getHours(),
        minutes = now.getMinutes(),
        seconds = now.getSeconds(),
        used = 0;
        
        if (hour >= (dataConfig.consumeStartTimeHour + 8)) {
            if (hour == (dataConfig.consumeStartTimeHour + 8) && minutes > dataConfig.consumeStartTimeMinutes) {
                return 3 * 8;
            } else if (hour > (dataConfig.consumeStartTimeHour + 8)){
                return 3 * 8;
            }
        } else if (hour >= dataConfig.consumeStartTimeHour && hour <= (dataConfig.consumeStartTimeHour + 8)) {
            var flag = true;
            if(hour == dataConfig.consumeStartTimeHour && minutes <= dataConfig.consumeStartTimeMinutes) {
                flag = false;
            } else if (hour == (dataConfig.consumeStartTimeHour + 8) && minutes >= dataConfig.consumeStartTimeMinutes) {
                flag = false;
            }
            
            
            if (flag) {
                used = (hour - dataConfig.consumeStartTimeHour) * 3 + seconds * (3/3600);
                if (minutes > dataConfig.consumeStartTimeMinutes) {
                    used = used + (minutes - dataConfig.consumeStartTimeMinutes) * (3/60)
                }
                
            }
            return used;
            
        }
        return used;
}
function getTodaySaveIncome(val) {
    return val;
}

function getTodaySurplusIncome(increase, consumption){
    return (increase - consumption) * 0.4;
}
function render() {
    var increase = getIncrease();
    //统计部分
    $("#todayTotal").text(toDecimal2(increase));
    $("#thisYearTotal").text(toDecimal2(dataConfig.thisYearTotal + increase));
    $("#careerTotal").text(toDecimal2(dataConfig.careerTotal + increase));

    //节约部分
    $("#todaySaveCoal").text(toDecimal2(getSaveCoal(increase)));
    $("#careerSaveCoal").text(toDecimal2(getSaveCoal(dataConfig.careerTotal + increase)));
    $("#todaySaveCO2").text(toDecimal2(getSaveCO2(increase)));
    $("#careerSaveCO2").text(toDecimal2(getSaveCO2(dataConfig.careerTotal + increase)));
    
    
    $(".todaySubsidies").text(toDecimal2(getTodaySubsidies(increase)));
    $(".todaySubsidies").val(toDecimal2(getTodaySubsidies(increase)));
    $(".todaySaveIncome").text(toDecimal2(getTodaySaveIncome(getUsedToday())));
    $(".todaySaveIncome").val(toDecimal2(getTodaySaveIncome(getUsedToday())));
    $(".todaySurplusIncome").text(toDecimal2(getTodaySurplusIncome(increase, getUsedToday())));
    $(".todaySurplusIncome").val(toDecimal2(getTodaySurplusIncome(increase, getUsedToday())));
    $(".todayIncomeTotal").text(toDecimal2(Number($(".todaySubsidies").val()) + Number($(".todaySaveIncome").val()) + Number($(".todaySurplusIncome").val())));
    
    
    
    
    $("#careerSubsidies").text(toDecimal2(getTodaySubsidies(dataConfig.careerTotal + increase)));
    $("#careerSaveIncome").text(toDecimal2(getTodaySaveIncome(getUsedToday()) + dataConfig.careerSaveIncome));
    $("#careerSurplusIncome").text(toDecimal2(((dataConfig.careerTotal + increase) - Number(dataConfig.careerSaveIncome))*0.4));
    $(".careerIncomeTotal").text(toDecimal2(Number($("#careerSubsidies").text()) + Number($("#careerSaveIncome").text()) + Number($("#careerSurplusIncome").text())));

    $("img.pig").next("img").show().animate({
        top:"-5px"
    }, 1000, function(){
        $(this).hide();
        $(this).css({"top":"-45px"});
    });
}


$(document).ready(function(){
	$.getJSON("/setting/power.json",function(data){
		chartData = eval(data);
		
		$("#systemTotal").text(dataConfig.systemTotal);
	    $("#yesterday").text(dataConfig.yesterday);
	    $("#lastWeek").text(dataConfig.lastWeek);
	    $("#lastMonth").text(dataConfig.lastMonth);
	    realTimeGauge = showRealTimeGauge();
	    consumeGauge = showConsumeGauge();
	    refresh();
	    render();
	    chart(refreshCharData());
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
    
    if (hour < chartData[0].hour) {
        for (var i = 0; i < chartData.length; i++) {
            if (chartData[i].power != 0) {
                barChartData.labels.push(chartData[i].hour + "时");
                barChartData.datasets[0].data.push(0);
            }
        }
    }
    
    if (hour >= chartData[0].hour) {
        for (var i = 0; i < chartData.length; i++) {
            if (chartData[i].power != 0) {
                if (chartData[i].hour <= hour) {
                    barChartData.labels.push(chartData[i].hour + "时");
                    barChartData.datasets[0].data.push(chartData[i].sunHeight);
                } else {
                    barChartData.labels.push(chartData[i].hour + "时");
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


function refresh() {
    setInterval(function() {
        render();
    }, sysConfig.textRefreshTime * 1000);
    
    //刷新仪表盘
    setInterval(function() {
        var now = new Date(),
            hour = now.getHours(),
            minutes = now.getMinutes(),
            seconds = now.getSeconds();
        
        var  current = 0; 
        for (var i = 0; i < chartData.length; i++) {
            
            if (chartData[i].power != 0) {
                if (hour == chartData[i].hour) { //当前小时符合数据小时
                    current = chartData[i].power;
                    if ((i + 1) <= chartData.length) { //当前数据不是最后一个
                        current = current + (chartData[i + 1].power - chartData[i].power) / 3600 * (seconds + minutes * 60);
                    
                    }
                    realTimeGauge.setValue(Math.random() * ((current - 0.02) - (current + 0.02)) + (current - 0.02));
                    break;
                } 
            }
        }

        consumeGauge.setValue(getUsedToday());

    }, sysConfig.guageRefreshTime * 1000);


}
