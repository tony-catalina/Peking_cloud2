// 记录模式索引
var modeIndex = 0;

//记录左侧选中类型以及索引
var type = 'average';
var index = 0;
$(document).ready(function(){
    
    $('.menu-box li').click(function() {
        var name = $(this).attr("id");
        var tabIndex = $(this).index();
        
        if(name == 'ImportantTips') {
            //重点因素 不做任何
            return;
        }
        else if(name == 'RankingList') {
            //排行榜，更新页面
            alert('排行榜页面')
        }
        else {
            window.location.href = 'index.html?tabIndex=' + tabIndex;
        }
    });
    
    // 模式切换
    $(".filter-box li").click(function () {
        if ($(this).hasClass('selected')) {
            return;
        }
        $(this).siblings().removeClass('selected');
        $(this).addClass('selected');
        
        var name = $(this).attr("id");
        if (name == 'checkMode') {
            //切换到考核模式，todo 将左侧数据更新，模拟选中平均框中的第一条数据
            $('#average').find('li')[0].click();
        }
        else if(name == 'warningMode') {
            //切换到预警模式，todo 将左侧数据更新，模拟选中平均框中的第一条数据
            $('#average').find('li')[0].click();
        }
    });
    
    $('.item-box li').click(function() {
        $('.list-item').removeClass('selected');
        $(this).addClass('selected');
        type = $(this).parents('ul').attr('id');
        index = $(this).index();
        
        if(type == 'average') {
            updateAverageChart();
        }
        else if(type == 'important') {
            updataImportantChart();
        }
    });
    
    $('#' + type).find('li')[index].click();
});

//展示平均指数对应的表
function updateAverageChart() {
    $('.average-box').fadeIn();
    $('.important-box').fadeOut();
    showAverageLine();
    showAverageBar()
}

//展示重点工作对应的表
function updataImportantChart() {
    $('.average-box').fadeOut();
    $('.important-box').fadeIn();
    showImportantBar();
}

//全市指数变化折线图
function showAverageLine() {
    var myChart = echarts.init(document.getElementById('lineChart'));
    var option = {
        title: {
            text: '全市指数变化',
            left: 'center',
            textStyle: {fontSize: 44}
        },
        tooltip : {
            trigger: 'axis',
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            top: 100,
            containLabel: true,
            backgroundColor: '#d9d9d9'
        },
        xAxis : [
            {
                type : 'category',
                splitLine: {show: true, lineStyle: {color: '#a8a8a8'}},
                boundaryGap : false,
                axisLabel: {
                    textStyle: {
                        fontSize: 18
                    }
                },
                data : ['0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17',
                    '18','19','20','21','22','23','24','25','26','27','28','29','30']
            }
        ],
        yAxis : [
            {
                type : 'value',
                min: 40,
                max: 100,
                splitLine: {show: true, lineStyle: {color: '#a8a8a8'}},
                splitArea: {
                    show: true,
                    areaStyle: {color: ['#d9d9d9']}
                },
                splitNumber: 6,
                axisLabel: {
                    textStyle: {
                        fontSize: 18
                    }
                }
            }
        ],
        series : [
            {
                type:'line',
                lineStyle: {normal: {width: 4}},
                data:[90,91,92,93,94,95,96,97,98,99,90,91,89,87,67,87,84,92,
                    93,90,91,92,93,94,95,96,97,98,96,85,87]
            },
        ],
        color: ['#0303fe']
    }
    myChart.setOption(option);
    
}
//各所指数变化柱状图
function showAverageBar() {
    var myChart = echarts.init(document.getElementById('barChart'));
    var option = {
        title: {
            text: '各所指数情况',
            left: 'center',
            textStyle: {fontSize: 44}
        },
        tooltip : {
            trigger: 'axis',
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: 50,
            top: 100,
            containLabel: true,
        },
        xAxis : [
            {
                type : 'category',
                data : ['上海市一看','上海市二看','上海市三看','上海市四看', '静安区','黄浦区','徐汇区','长宁区','普陀区','虹口区',
                '杨浦区','浦东新区','闵行区','奉贤区','松江区','金山区','青浦区','嘉定区','宝山区','崇明区'],
                axisLabel: {
                    rotate: 45,
                    interval: 0,
                    textStyle: {
                        fontSize: 18,
                        fontWeight: 'bold'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                min: 0,
                max: 120,
                splitLine: {show: true, lineStyle: {color: '#a8a8a8'}},
                axisLabel: {
                    textStyle: {
                        fontSize: 18,
                        fontWeight: 'bold',
                        color: function (value, index) {
                            return value == 80 ? '#ff6700' : '#000';
                        }
                    }
                },
                splitNumber: 6,
            }
        ],
        series : [
            {
                type:'bar',
                barWidth: 20,
                barGap: 37,
                data:[90,91,92,93,94,95,97,98,99,90,91,89,87,67,87,84,92,
                    93,90,91],
                markLine: {
                    lineStyle: {
                        normal: {
                            type: 'solid',
                            color: '#ff6700',
                            width: 4
                        }
                    },
                    symbolSize: 0,
                    label: {
                        normal: {show: false}
                    },
                    data : [
                        {yAxis: 80 }
                    ]
                }
            },
        ],
        color: ['#0303fe']
    }
    myChart.setOption(option);
}

//重要工作柱状图
function showImportantBar() {
    var myChart = echarts.init(document.getElementById('importantBarChart'));
    var option = {
        title: {
            text: '看守所数量',
            left: 'center',
            textStyle: {fontSize: 42}
        },
        tooltip: {
            trigger: 'axis',
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: 50,
            top: 70,
            containLabel: true,
        },
        xAxis: [
            {
                type: 'category',
                data: ['未开展','招投标','试运行','已完成'],
                axisTick: {show: false},
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    textStyle: {
                        fontSize: 23,
                    }
                },
                axisLine: {show: false},
                axisTick: {show: false},
            }
        ],
        series: [
            {
                type: 'bar',
                barWidth: 75,
                barGap: 150,
                data: [5, 6, 1, 12]
            },
        ],
        color: ['#0303fe']
    }
    myChart.setOption(option);
    
}