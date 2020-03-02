// 当前区
var name = getQueryString('name');

$(document).ready(function(){
    initBox3();
})

function initBox3() {
    showPage3Line();
    
    //图表三饼图对应的数据
    var pieData1 = {
        index: 1,
        name: '一监区',
        data: [
            {value:335, name:'打架斗殴'},
            {value:310, name:'擅自娱乐'},
            {value:234, name:'值班不规范'},
            {value:135, name:'私藏物品'}
        ]
    }
    var pieData2 = {
        index: 2,
        name: '二监区',
        data: [
            {value:335, name:'打架斗殴'},
            {value:310, name:'擅自娱乐'},
            {value:234, name:'值班不规范'},
            {value:135, name:'私藏物品'}
        ]
    }
    var pieData3 = {
        index: 3,
        name: '三监区',
        data: [
            {value:335, name:'打架斗殴'},
            {value:310, name:'擅自娱乐'},
            {value:234, name:'值班不规范'},
            {value:135, name:'私藏物品'}
        ]
    }
    var pieData4 = {
        index: 4,
        name: '四监区',
        data: [
            {value:335, name:'打架斗殴'},
            {value:310, name:'擅自娱乐'},
            {value:234, name:'值班不规范'},
            {value:135, name:'私藏物品'}
        ]
    }
    //显示四个饼状图
    showPage3Pie(pieData1);
    showPage3Pie(pieData2);
    showPage3Pie(pieData3);
    showPage3Pie(pieData4);
    
    //显示柱形图
    showPage3Bar();
}

//图三折线图
function showPage3Line() {
    var myChart = echarts.init(document.getElementById('lineChart'));
    var option = {
        tooltip : {
            trigger: 'axis',
        },
        grid: {
            top: '6%',
            left: '0',
            right: '0',
            bottom: '2%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['七月', '八月', '九月', '十月', '本月'],
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 22}
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                textStyle: {fontSize: 21, color: '#fff'}
            },
            axisLine: {
                show: false,
            },
            splitNumber: 6,
            splitLine: {
                lineStyle: {type: 'dashed'}
            }
        },
        series : [
            {
                name: '违规记录',
                type:'line',
                data:[58, 35, 46, 47, 38],
                lineStyle: {
                    normal: {
                        color: '#ffff00',
                        width: 4
                    }
                },
                symbol: 'rect',
                symbolSize: 10,
            }
        ],
        color: ['#ffff00']
    };
    myChart.setOption(option);
}

function showPage3Pie(params) {
    var myChart = echarts.init(document.getElementById('page3PieChart' + params.index));
    var option = {
        title: {
            left: 'center',
            bottom: 20,
            text: params.name,
            textStyle: {
                color: '#fff',
                fontSize: '42'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
            {
                name: params.name,
                type:'pie',
                center: ['50%', '40%'],
                radius: ['40%', '80%'],
                label: {
                    normal: {
                        show: true,
                        formatter: '{b}',
                        position: 'inside',
                        textStyle: {
                            fontSize: '22'
                        }
                    }
                },
                data:params.data
            }
        ],
        color: ['#0000ff','#ff6800','#bdbdbd','#fdbf00']
    };
    myChart.setOption(option);
}

function showPage3Bar() {
    var myChart = echarts.init(document.getElementById('page3BarChart'));
    var option = {
        title: {
            text: '重点违规监室',
            textStyle: {
                color: '#fff',
                fontSize: 30
            },
            left: 'center'
        },
        tooltip : {
            trigger: 'axis',
        },
        grid: {
            top: 100,
            left: '4%',
            right: '6%',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                fontSize: 33,
                color: '#fff'
            },
            axisTick: {
                show: true,
            },
        },
        yAxis: {
            type: 'category',
            data: ['流水牌','流水牌','流水牌','流水牌','流水牌','xxx监室','201监室'
                ,'201监室','201监室'],
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 21}
            }
        },
        series : [
            {
                barCategoryGap: '50%',
                type:'bar',
                data:[2, 2, 2, 3, 4, 5, 6, 7, 10],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'right',
                            color: '#fff',
                            fontSize: 25
                        },
                        
                        color: '#ffc60f'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}