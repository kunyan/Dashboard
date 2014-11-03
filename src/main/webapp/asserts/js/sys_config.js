var sysConfig = {
    powerGuageConfig : { //发电即时功率表配置
        minValue: 0,    //最小值
        maxValue: 9,    //最大值
        valueFormat:{ int : 1, dec : 2 }, //显示格式 int:整数x位, dec:小数保留x位
        highlights: [{ //区域及颜色设置
            from: 0,
            to: 5,
            color: '#7A9B00'
        }, {
            from: 5,
            to: 7.6,
            color: '#F8C538'
        }, {
            from: 7.6,
            to: 9,
            color: '#F5584A'
        }]
    },
    
    consumptionGuageConfig : { //发电即时功率表配置
        minValue: 0,    //最小值
        maxValue: 25,    //最大值
        valueFormat:{ int : 1, dec : 2 }, //显示格式 int:整数x位, dec:小数保留x位
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
            to: 25,
            color: '#F5584A'
        }]
    },
    
    textRefreshTime : 10,    //文字部分刷新时间 单位 秒
    guageRefreshTime : 0.3, //仪表盘刷新时间 单位 秒
    weather : 1 // 1 晴天， 0.8 阴天 , 0.4 雨天
}