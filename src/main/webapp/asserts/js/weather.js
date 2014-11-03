angular.module('dashBoardApp', []).controller('WeatherController', ['$scope', '$window', function($scope, $window) {
	"use strict";
    $scope.weather =
      {
		  todayDay: '',
		  todayNight: '',
		  tempHigh : '',
		  tempLow: '',
		  windDirection: '',
		  windLevel: '',
		  update: ''
	  };
	  
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