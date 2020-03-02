// 当前区
var name = getQueryString('name');

$(document).ready(function(){
    initBox2();
})

function initBox2() {
    //参数二和参数三需要动态传入
    //参数index为渲染图标索引，currentCount为当前使用，totalCount为总共使用，color需要画的颜色
    showPercentPie(1, 8, 10, '#64c100');
    showPercentPie(2, 9, 10, '#be111d');
    showPercentPie(3, 7, 10, '#cdbc00');
    
    showPage2Bar();
    showPage2Bar2();
}

//图二的饼图
//参数index为渲染图标索引，currentCount为当前使用，totalCount为总共使用，color需要画的颜色
function showPercentPie(index,currentCount, totalCount, color) {
    var myChart = echarts.init(document.getElementById('pieChart' + index));
    var option = {
        series : [
            {
                type: 'pie',
                radius : ['40%','80%'],
                center: ['50%', '50%'],
                label: { normal: {show: false}},
                labelLine: {normal: {show: false}},
                data:[
                    {value:currentCount},
                    {value:totalCount - currentCount}
                ]
            }
        ],
        color: [color, '#cecece']
    };
    myChart.setOption(option);
}

//图二左边的柱状图
function showPage2Bar() {
    var myChart = echarts.init(document.getElementById('barChart2'));
    var option = {
        title: {
            text: '其他临时出所',
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
            left: '2%',
            right: '8%',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisTick: {
                show: false,
            },
            axisLine: {
                lineStyle: {show: false}
            },
        },
        yAxis: {
            type: 'category',
            data: ['其他', '公检法办案', '出所开庭'],
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 23}
            }
        },
        series : [
            {
                barCategoryGap: '50%',
                type:'bar',
                data:[6, 5, 4],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'inside',
                            color: '#fff',
                            fontSize: 38
                        },
                        
                        color: '#ff7200'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}
//图二右边的柱状图
function showPage2Bar2() {
    var myChart = echarts.init(document.getElementById('barChart3'));
    var option = {
        tooltip : {
            trigger: 'axis',
        },
        grid: {
            top: '10%',
            left: '0',
            right: '0',
            bottom: '2%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['逮捕', '补充侦查', '审查起诉', '一审', '二审', '重申', '已决'],
            axisLine: {
                lineStyle: {color: '#fff'}
            },
            axisLabel: {
                textStyle: {fontSize: 16}
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                textStyle: {fontSize: 28, color: '#fff'}
            },
            axisLine: {
                show: false,
            },
            splitNumber: 10,
            max: 10
        },
        series : [
            {
                name: '今日诉讼',
                barCategoryGap: '70%',
                type:'bar',
                data:[6, 5, 4, 7, 8, 9, 6],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'top',
                            color: '#fff',
                            fontSize: 25
                        },
                        
                        color: '#ff7200'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}