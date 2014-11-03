angular.module('dashBoardApp', []).controller('GaugeController', ['$scope', '$window', 'gauge', function($scope, $window, gauge) {
	"use strict";
    $scope.gauge = new Gauge({
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
	  
	$scope.init = function () {
		for(var i in SWther.w){
			$scope.weather.todayDay = $window.SWther.w[i][0].s1;
			$scope.weather.todayNight = $window.SWther.w[i][0].s2;
			$scope.weather.tempHigh = $window.SWther.w[i][0].t1;
			$scope.weather.tempLow = $window.SWther.w[i][0].t2;
			$scope.weather.windDirection = $window.SWther.w[i][0].p1;
			$scope.weather.windLevel = $window.SWther.w[i][0].d1;
			$scope.weather.update = $window.SWther.add.update;
		}
	}
  }]);