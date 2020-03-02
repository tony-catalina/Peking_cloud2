// 当前区
var name = getQueryString('name');

$(document).ready(function(){
    initBox4();
})

function initBox4() {
    showPage4Bar();
    showPage4Line1();
    showPage4Line2();
}

//图四柱状图
function showPage4Bar() {
    var myChart = echarts.init(document.getElementById('barChart4'));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            orient: 'vertical',
            left: 'right',
            top:'middle',
            align: 'left',
            itemGap: 20,
            textStyle: {
                color: '#fff',
                fontSize: 21
            },
            data:['高血压','糖尿病','心脏病','皮肤病', '高龄', '体内异物', '脑梗']
        },
        grid: {
            left: '3%',
            right: '180',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['一监区','二监区','三监区','四监区'],
                axisLabel: {
                    fontSize: 20,
                    color: '#fff'
                },
                axisTick: {
                    show: true,
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitNumber: 10,
                axisLabel: {
                    fontSize: 20,
                    color: '#fff'
                },
                axisTick: {
                    show: true,
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        series : [
            {
                name:'高血压',
                type:'bar',
                barGap: 0,
                data:[5, 6, 7, 4]
            },
            {
                name:'糖尿病',
                type:'bar',
                data:[5, 6, 7, 4]
            },
            {
                name:'心脏病',
                type:'bar',
                data:[3, 7, 7, 1]
            },
            {
                name:'皮肤病',
                type:'bar',
                data:[8, 7, 3, 2]
            },
            {
                name:'高龄',
                type:'bar',
                data:[4, 2, 3, 2]
            },
            {
                name:'体内异物',
                type:'bar',
                data:[3, 7, 7, 1]
            },
            {
                name:'脑梗',
                type:'bar',
                data:[4, 2, 3, 2]
            }
        ],
        color: ['#1616e5', '#ffae00', '#979797', '#e9d928', '#28b511', '#16ede1', '#bf31be']
    };
    myChart.setOption(option);
}

//图四上方折线图
function showPage4Line1() {
    var myChart = echarts.init(document.getElementById('page4LineChart1'));
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
                name: '月统计全所报病人数',
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

//图四下方折线图
function showPage4Line2() {
    var myChart = echarts.init(document.getElementById('page4LineChart2'));
    var option = {
        title: {
            text: '各监区报病情况变化图',
            textStyle: {
                color: '#fff',
                fontSize: 30
            },
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['一监区','二监区','三监区','四监区'],
            right: 10,
            top: 10,
            textStyle: {
                fontSize: 20,
                color: '#fff'
            }
        },
        grid: {
            top: 84,
            left: '3%',
            right: '2%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['四月','五月','六月','七月','八月','九月','十月','本月'],
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 21}
            },
            axisTick: {
                show: true,
                lineStyle: {color: '#465570'}
            },
            splitLine: {
                show: true,
                lineStyle: {color: '#465570'}
            }
        },
        yAxis: {
            type: 'value',
            splitNumber: 11,
            name: '(人数)',
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 21}
            },
            axisTick: {
                show: true,
                lineStyle: {color: '#465570'}
            },
            splitLine: {
                lineStyle: {
                    color: '#465570'
                }
            }
        },
        series: [
            {
                name:'一监区',
                type:'line',
                data:[89, 67, 98, 76, 87, 98, 84, 90],
                symbol: 'rect',
                symbolSize: 10,
            },
            {
                name:'二监区',
                type:'line',
                data:[76, 87, 65, 85, 65, 76, 65, 87],
                symbol: 'triangle',
                symbolSize: 10,
            },
            {
                name:'三监区',
                type:'line',
                data:[98, 76, 87, 76, 87, 56, 54, 98],
                symbol: 'circle',
                symbolSize: 10,
            },
            {
                name:'四监区',
                type:'line',
                data:[65, 76, 65, 54, 65, 87, 65, 90],
                symbol: 'diamond',
                symbolSize: 10,
            }
        ],
        color: ['#cb0505', '#3aa710', '#dd8d10', '#e3e5e7']
    };
    myChart.setOption(option);
}