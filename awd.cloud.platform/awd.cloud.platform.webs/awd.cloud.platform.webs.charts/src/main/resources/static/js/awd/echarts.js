define("awd/echarts", [
	"../plugins/e-charts/echarts"
	],
function(require) {
     //超期羁押情况分析
    // var url='/kssqsfx/cqjyCount'
	var _cqjyqkfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
       
            var myChar = echarts.init(document.getElementById(_id));
            var  option = {
                title: {
                    text: _title,
                    x:'center',
                    textStyle:{
                        fontSize:33,
                        color:'#fff'
                    }
                },
                backgroundColor: '#0e2147',
                xAxis: {
                    show: false
                },
                yAxis: [{
                    show: true,
                    data: ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'],
                    inverse: true,

                    axisLine: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        color: '#fff',
                        fontSize: 18,
                        formatter: function(value, index) {
                            return [
                                '{lg|' + (index + 1) + '}' + '{title|' + value + '} '
                            ].join('\n')
                        },
                        rich: {
                            lg: {
                                backgroundColor: 'rgb(97, 192, 222)',
                                color: '#fff',
                                borderRadius: 18,
                                align: 'center',
                                width: 15,
                                height: 15,
                                lineHeight:15,
                            },
                        }
                    },
                }, {
                    show: true,
                    inverse: true,
                    data:[],
                    axisLabel: {
                        textStyle: {
                            fontSize: 18,
                            color: '#fff',
                        },
                    },
                    axisLine: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },

                }],
                series: [{
                    name: '条',
                    type: 'bar',
                    yAxisIndex: 0,
                    data: [],
                    barWidth: 25,
                    areaStyle: {normal: {}},
                    itemStyle: {
                        normal: {
                            barBorderRadius: 30,
                            //随机颜色
                            color:function(d){
                                return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);
                                }
                        }
                    },
                    label: {
                        normal: {
                            show: true,
                            position: 'inside',
                            formatter: '{c}%'
                        }
                    },
                }, {
                    name: '框',
                    type: 'bar',
                    yAxisIndex: 1,
                    barGap: '-100%',
                    data: [100, 100, 100, 100, 100,100,100,100,100,100,100,100,100,100,100,100,100],
                    barWidth: 25,
                    itemStyle: {
                        normal: {
                            color: 'none',
                            borderColor: 'rgb(0, 136, 201)',
                            borderWidth: 3,
                            barBorderRadius: 15,
                        }
                    }
                }]
            };
            myChar.setOption(option);
            ////////////
            $.ajax({
                url: '/kssqsfx/cqjyCount',
                type: "get",
                dataType: "json",
                success: function (res) {
                    console.log(res.result.value+'11111111111111'); 
                    console.log(typeof res.result.value);
                    var option={
                        xAxis: {
                            show: false
                        },
                        yAxis:[{
                                // data:['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'],
                            },{
                                data:res.result.cqjy
                            }
                        ],
                        series:[{
                                name: '条',
                                data:res.result.value.map((itm)=>itm*100)
                            },{
                                name: '框',
                                // data: [100, 100, 100, 100, 100,100,100,100,100,100,100,100,100,100,100,100,100],
                            }

                        ]
                    }
                    myChar.setOption(option);
                }, error: function (error) {
                    console.log(error);
                }
            });
            ////////////
	}
    //关押期限分析
	var _gyqxfxLine = function (id,url,title) {
        var _title = title || undefined ? title :"未定义";
        var myChar = echarts.init(document.getElementById(id));
        var  option = {
            title: {
                text: _title
            },
            tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                top:20,
                data:['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'],
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['3月以下','3月至6月','6月至1年','1年至3年','3年以上'],
                    axisLabel: {
                        textStyle:{
                            fontSize:20
                        }
                    }
                    
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel: {
                        textStyle:{
                            fontSize:20
                        }
                    }
                    
                }
            ],
            series : [
                {
                    name:'北京市',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data:[0,0,0,0,0]
                },
                {
                    name:'东城区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },  {
                    name:'西城区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'朝阳区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'丰台区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'石景山区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {
                        // show: true,
                    }},
                    data:[0,0,0,0,0]
                },
                {
                    name:'海淀区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data:[0,0,0,0,0]
                },
                {
                    name:'门头沟区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'房山区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },  
                {
                    name:'通州区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'顺义区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'昌平区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'大兴区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },                    
                {
                    name:'平谷区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'怀柔区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'密云区',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            // show: true,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
                {
                    name:'延庆区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,0,0]
                },
            ],
            textStyle:{
                fontSize:16
            }
        };
        myChar.setOption(option);
                    //////////////////////////////
            $.ajax({
                url: '/kssqsfx/gyqxCount',
                type: "get",
                dataType: "json",
                success: function (res) {
                    console.log(res.result,111222)
                    var gyqx = res.result;
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name:'北京市',
                            data:gyqx[0].data
                        },
                        {
                            name:'东城区',
                            data:gyqx[1].data
                        },  {
                            name:'西城区',
                            data:gyqx[2].data
                        },
                        {
                            name:'朝阳区',
                            data:gyqx[3].data
                        },
                        {
                            name:'丰台区',
                            data:gyqx[4].data
                        },
                        {
                            name:'石景山区',
                            data:gyqx[5].data
                        },
                        {
                            name:'海淀区',
                            data:gyqx[6].data
                        },
                        {
                            name:'门头沟区',
                            data:gyqx[7].data
                        },
                        {
                            name:'房山区',
                            data:gyqx[8].data
                        },  
                        {
                            name:'通州区',
                            data:gyqx[9].data
                        },
                        {
                            name:'顺义区',
                            data:gyqx[10].data
                        },
                        {
                            name:'昌平区',
                            data:gyqx[11].data
                        },
                        {
                            name:'大兴区',
                            data:gyqx[12].data
                        },                    
                        {
                            name:'平谷区',
                            data:gyqx[13].data
                        },
                        {
                            name:'怀柔区',
                            data:gyqx[14].data
                        },
                        {
                            name:'密云区',
                            type:'line',
                            data:gyqx[15].data
                        },
                        {
                            name:'延庆区',
                            data:gyqx[16].data
                        }]
                    }
                    myChar.setOption(option);
    
                },
                error:function(error){
                    console.log(error,222222)
                }
            })
    
            
        }
    
   //超量关押分析
     var _clgyfxLineAndBar=function (id,url,title) {
           var _id=id;
           var _title = title || undefined ? title :"未定义";
           var _yl =  ["设计押量", "实际押量"];
           var _dqm =['北京市','海淀区','石景山区','朝阳区','东城区','西城区','丰台区','顺义区','延庆区','密云区'];
           var _sejyl = [67, 78, 65, 89, 69,54,42,43,88,54];
           var _sijyl = [55,63,42,67,45,34,23,33,71,43];

           $.ajax({
               url:url,
               type:"post",
               dataType:"json",
               success:function (data) {
                console.log(data,010110101)
               },error:function () {
               }
           });
         var option = {
                   title: {
                       text: _title,
                       textStyle: {
                           align: 'center',
                           color: '#fff',
                           fontSize: 20,
                       },
                       top: '3%',
                       left: '10%',
                   },
                   backgroundColor: '#0f375f',
                   grid: {
                       top: "25%",
                       bottom: "10%"
                   },
                   tooltip: {
                       trigger: "axis",
                       axisPointer: {
                           type: "shadow",
                           label: {
                               show: true
                           }
                       }
                   },
                   legend: {
                       data:_yl,
                       top: "15%",
                       textStyle: {
                           color: "#ffffff"
                       }
                   },
                   xAxis: {
                       data: _dqm,
                       axisLine: {
                           show: true //隐藏X轴轴线
                       },
                       axisTick: {
                           show: true //隐藏X轴刻度
                       },
                       axisLabel: {
                           show: true,
                           textStyle: {
                               color: "#ebf8ac" //X轴文字颜色
                           }
                       },
                       axisLine: {
                           lineStyle: {
                               color: '#01FCE3'
                           }
                       },
                   },
                   yAxis: [{
                       type: "value",
                       name: "个",
                       nameTextStyle: {
                           color: "#ebf8ac"
                       },
                       splitLine: {
                           show: false
                       },
                       splitLine: {
                           show: false
                       },
                       axisTick: {
                           show: true
                       },
                       axisLine: {
                           show: true
                       },
                       axisLabel: {
                           show: true,
                           textStyle: {
                               color: "#ebf8ac"
                           }
                       },
                       axisLine: {
                           lineStyle: {
                               color: '#FFFFFF'
                           }
                       },
                   },
                       {
                           type: "value",
                           name: "占比",
                           nameTextStyle: {
                               color: "#ebf8ac"
                           },
                           position: "right",
                           splitLine: {
                               show: false
                           },
                           splitLine: {
                               show: false
                           },
                           axisTick: {
                               show: false
                           },
                           axisLine: {
                               show: false
                           },
                           axisLabel: {
                               show: true,
                               formatter: "{value} %", //右侧Y轴文字显示
                               textStyle: {
                                   color: "#ebf8ac"
                               }
                           }
                       },
                       {
                           type: "value",
                           gridIndex: 0,
                           min: 50,
                           max: 100,
                           splitNumber: 8,
                           splitLine: {
                               show: false
                           },
                           axisLine: {
                               show: false
                           },
                           axisTick: {
                               show: false
                           },
                           axisLabel: {
                               show: false
                           },
                           splitArea: {
                               show: true,
                               areaStyle: {
                                   color: ["rgba(250,250,250,0.0)", "rgba(250,250,250,0.05)"]
                               }
                           }
                       }
                   ],
                   series: [{
                       name: "设计押量",
                       type: "line",
                       yAxisIndex: 1, //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用
                       smooth: true, //平滑曲线显示
                       showAllSymbol: true, //显示所有图形。
                       symbol: "circle", //标记的图形为实心圆
                       symbolSize: 10, //标记的大小
                       itemStyle: {
                           //折线拐点标志的样式
                           color: "#058cff"
                       },
                       lineStyle: {
                           color: "#058cff"
                       },
                       areaStyle:{
                           color: "rgba(5,140,255, 0.2)"
                       },
                       data:_sejyl
                   },
                       {
                           name: "实际押量",
                           type: "bar",
                           barWidth: 15,
                           itemStyle: {
                               normal: {
                                   color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                       offset: 0,
                                       color: "#00FFE3"
                                   },
                                       {
                                           offset: 1,
                                           color: "#4693EC"
                                       }
                                   ])
                               }
                           },
                           data:_sijyl
                       }
                   ]
               };
               var myChar = echarts.init(document.getElementById(_id));
               myChar.setOption(option);
           }




    var _shserytjBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['男', '女', '其他'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
      /*   var _nan = [];
        var _nv = [];
        var _qt = []; */
        var option = {
            backgroundColor:'#323a5e',
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '2%',
                right: '4%',
                bottom: '14%',
                top:'16%',
                containLabel: true
            },
            legend: {
                data: _yl,
                right: 10,
                top:12,
                textStyle: {
                    color: "#fff",
                    fontSize:'25'
                },
                itemWidth: 12,
                itemHeight: 10,
                // itemGap: 35
            },
            xAxis: {
                type: 'category',
                data: _dqm,
                axisLine: {
                    lineStyle: {
                        color: 'white'
                    }
                },
                axisLabel: {
                    // interval: 0,
                    // rotate: 40,
                    textStyle: {
                        fontFamily: 'Microsoft YaHei',
                        fontSize:'20'
                    }
                },
            },

            yAxis: {
                type: 'value',
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: 'white'
                    }
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: 'rgba(255,255,255,0.3)'
                    }
                },
                axisLabel: {
                    fontSize:'25'
                }
            },
            "dataZoom": [{
                "show": true,
                "height": 12,
                "xAxisIndex": [
                    0
                ],
                bottom:'8%',
                "start": 10,
                "end": 90,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#d3dee5",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"#90979c"
            }, {
                "type": "inside",
                "show": true,
                "height": 15,
                "start": 1,
                "end": 35
            }],
            series: [{
                name: '男',
                type: 'bar',
                barWidth: '15%',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#fccb05'
                        }, {
                            offset: 1,
                            color: '#f5804d'
                        }]),
                        barBorderRadius: 12,
                    },
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            },
                {
                    name: '女',
                    type: 'bar',
                    barWidth: '15%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#8bd46e'
                            }, {
                                offset: 1,
                                color: '#09bcb7'
                            }]),
                            barBorderRadius: 11,
                        }

                    },
                    data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                },
                {
                    name: '其他',
                    type: 'bar',
                    barWidth: '15%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#248ff7'
                            }, {
                                offset: 1,
                                color: '#6851f1'
                            }]),
                            barBorderRadius: 11,
                        }
                    },
                    data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssqsfx/shseCount',
            type:"get",
            dataType:"json",
            success:function (data) {
                var option={
                    xAxis:{},
                    yAxis:{},
                    series:[{
                        name: '男',
                        data:data.result.nan
                    },{
                        name: '女',
                        data:data.result.nv
                    },{
                        name: '其他',
                        data:data.result.qt
                    }]
                }
                myChar.setOption(option);
            },error:function () {
            }
    
        });
        // var app = {
        //     currentIndex: -1,
        // };
        // setInterval(function () {
        //     var dataLen = option.series[0].data.length;
        //
        //     // 取消之前高亮的图形
        //     myChart.dispatchAction({
        //         type: 'downplay',
        //         seriesIndex: 0,
        //         dataIndex: app.currentIndex
        //     });
        //     app.currentIndex = (app.currentIndex + 1) % dataLen;
        //     //console.log(app.currentIndex);
        //     // 高亮当前图形
        //     myChart.dispatchAction({
        //         type: 'highlight',
        //         seriesIndex: 0,
        //         dataIndex: app.currentIndex,
        //     });
        //     // 显示 tooltip
        //     myChart.dispatchAction({
        //         type: 'showTip',
        //         seriesIndex: 0,
        //         dataIndex: app.currentIndex
        //     });
        //
        //
        // }, 1000);


    }

    var _gjfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        
        var _yl =  ['中国', '外籍', '香港','澳门','台湾'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];

        var myChar = echarts.init(document.getElementById(_id));
        /* var _zg = [];
        var _wj = [];
        var _xg = [];
        var _am = [];
        var _tw = []; */

       /*  var _zg = [96,96,97,95,98,94,89,94,80,52];
        var _wj = [97,99,99,100,99,90,80,91,69,67];
        var _xg = [23,45,56,23,45,65,44,56,90,34];
        var _am = [88,45,67,78,98,12,34,56,87,23];
        var _tw = [98,12,34,54,77,88,99,34,65,78]; */
        var option = {
            backgroundColor: '#394056',
            title: {
                text: _title,
                textStyle: {
                    fontWeight: 'normal',
                    fontSize: 16,
                    color: '#F1F1F3'
                },
                left: '6%'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    lineStyle: {
                        color: '#57617B'
                    }
                }
            },
            legend: {
                icon: 'rect',
                itemWidth: 14,
                itemHeight: 5,
                itemGap: 13,
                data: _yl,
                right: '4%',
                textStyle: {
                    fontSize: 25,
                    color: '#F1F1F3'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#57617B'
                    }
                },
                data:_dqm,
                axisLabel: {
                    fontSize:'17'
                }
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#57617B'
                    }
                },
                axisLabel: {
                    margin: 10,
                    textStyle: {
                        fontSize: 25
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#57617B'
                    }
                }
            }],
            /* "dataZoom": [{
                fillerColor:"#3d7899",
                "show": true,
                "height": 12,
                "xAxisIndex": [
                    0
                ],
                bottom:'0%',
                "start": 10,
                "end": 90,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"##3d7899",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"##3d7899"
            }, {
                "type": "inside",
                "show": true,
                "height": 15,
                "start": 1,
                "end": 35
            }], */
            series: [{
                name: _yl[0],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(137, 189, 27, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(137, 189, 27, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(137,189,27)'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, {
                name:  _yl[1],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0, 136, 212, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(0, 136, 212, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(0,136,212)'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, {
                name:  _yl[2],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(219, 50, 51, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(219, 50, 51, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(219,50,51)'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, , {
                name:  _yl[3],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0, 136, 212, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(0, 136, 212, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(0,136,212)'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, , {
                name:  _yl[4],
                type: 'line',
                smooth: true,
                lineStyle: {
                    normal: {
                        width: 1
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0, 136, 212, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(0, 136, 212, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(0,136,212)'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssqsfx/gjfxCount',
            type:"get",
            dataType:"json",
            success:function (data) {
                var option={
                    xAxis:{},
                    yAxis:{},
                    series:[{
                        name: _yl[0],
                        data:data.result.zg
                    },{
                        name: _yl[1],
                        data:data.result.wj
                    },{
                        name: _yl[2],
                        data:data.result.xg
                    },{
                        name: _yl[3],
                        data:data.result.am
                    },{
                        name: _yl[4],
                        data:data.result.tw
                    }]
                }
                myChar.setOption(option);
            },error:function () {
            }
        });


    }


    return{
		cqjyqkfxChar:_cqjyqkfxBar,
        gyqxfxChar:_gyqxfxLine,
        clgyfxChar:_clgyfxLineAndBar,
        shserytjChar:_shserytjBar,
        gjfxChar:_gjfxLine
	}
});