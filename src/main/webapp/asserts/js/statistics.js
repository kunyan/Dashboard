// function chart(id, data) {
//     var ctx = document.getElementById(id).getContext("2d");
//     barChart = new Chart(ctx).Bar(data, {
//         responsive: true,
//         barValueSpacing: 5
//     }).update();
// }
//
// function refreshCharData(unit, data){
//     var barChartData = {
//         labels: [],
//         datasets: [{
//             fillColor: "#F8C538",
//             strokeColor: "#F8C538",
//             highlightFill: "#F55854",
//             highlightStroke: "#F55854",
//             data: []
//         }]
//
//     }
//
//     for (var i = 0; i < data.length; i++) {
//        if (data[i][0] != 0) {
// 		   barChartData.labels.push(data[i][0] + unit);
// 		   barChartData.datasets[0].data.push(data[i][1]);
// 	   }
// 	}
//
//     return barChartData;
// }
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$(document).ready(function(){
    // chart('chartPower', refreshCharData('日', dataConfig.powerDay));
	// chart('chartPower', refreshCharData('月', dataConfig.powerMonth));
	
	// chart("chartTimeDay", refreshCharData("日", dataConfig.powerDay));
	// chart("chartCoDay", refreshCharData("日", dataConfig.powerDay));
	// chart("chartCo2Day", refreshCharData("日", dataConfig.powerDay));
	// $('.bs-docs-sidenav').affix({
	//     offset: {
	//       top: 100
	//     , bottom: function () {
	//         return (this.bottom = $('.footer').outerHeight(true))
	//       }
	//     }
	//   })
	
	$('#searchToday').click(function(){
		$("#startDate").val(new Date().Format("yyyy-MM-dd"));
		$("#endDate").val(new Date().Format("yyyy-MM-dd"));
		$("#form").submit();
	});
	
	$('#searchMonth').click(function(){
		$("#startDate").val(getCurrentMonthFirstDay().Format("yyyy-MM-dd"));
		$("#endDate").val(getCurrentMonthLastDay().Format("yyyy-MM-dd"));
		$("#form").submit();
	});
	
	$('#searchYear').click(function(){
		$("#startDate").val(getCurrentYearFirstDay().Format("yyyy-MM-dd"));
		console.log(getCurrentYearFirstDay().Format("yyyy-MM-dd"));
		$("#endDate").val(getCurrentYearLastDay().Format("yyyy-MM-dd"));
		$("#form").submit();
	});
	$('#search').click(function(){
		if ($('#startDate').val() != ""){
			if ($('#endDate').val() != "") {
				$("#form").submit();
//				createTable("月", dataConfig.yearData);
				
			} else {
				alert("截止日期不可为空");
			}
		} else {
			alert("起始日期不可为空")
		}
	});
});


function createTable(unit, data) {
	clean();
	var content = '';
	for (var i = 0; i < data.length; i ++) {
		content = content + '<tr><td>' +
			 Number(i + 1) + '</td><td>' + 
			 data[i][0] + unit + '</td><td class="js-col3">' +
			 data[i][1] + '</td><td class="js-col4">' +
			 data[i][2] + '</td><td class="js-col5">' +
			 data[i][3] + '</td><td class="js-col6">' +
			 data[i][4] + '</td><td class="js-col7">' +
			 data[i][5] + '</td><td class="js-col8">' +
			 data[i][6] + '</td><td class="js-col9">' +
			 data[i][7] + '</td><td class="js-col10">' +
			 data[i][8] + '</td><td class="js-col11">' +
			 data[i][9] + '</td><td class="js-col12">' +
			 Number (Number(data[i][7]) + Number(data[i][8]) + Number(data[i][9])) + '</td></tr>';
	}
	$('tbody').append(content);
	
	for (var i = 3 ; i <= 12; i ++) {
		var sum = 0;
		$('.js-col' + i ).each(function() {
		    sum += Number($(this).text());
			console.log($(this).text())
		});
		// console.log(sum)
		$('.js-col' + i ).last().text(sum);
	}

}

function clean() {
	$('tbody').empty();
	$('tfoot td').text(0);
	
}



function getCurrentMonthLastDay() {
	var now = new Date();	
	return new Date(now.getFullYear(), now.getMonth() + 1, 0);
}

function getCurrentMonthFirstDay() {
	var now = new Date();	
	return new Date(now.getFullYear(), now.getMonth(), 1);
}

function getCurrentYearLastDay() {
	var now = new Date();	
	return new Date(now.getFullYear(), 11, 31);
}

function getCurrentYearFirstDay() {
	var now = new Date();	
	return new Date(now.getFullYear(), 0, 1);
}

$(document).ready(function(){
	// $("table").children("thead").find("td,th").each(function(){
	// 	    var idx = $(this).index();
	// 		var td = $(this).closest("table").children("tbody")
	// 		                .children("tr:first").children("td,th").eq(idx);
	// 		$(this).width() > td.width() ? td.width($(this).width()) : $(this).width(td.width());
		// });
});
