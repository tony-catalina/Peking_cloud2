define("awd/zdy_echarts", [
    "../plugins/e-charts/echarts"
],
function(require) {
     //在押人员关押量分析
     var _zyrygylfxBar=function (id,url,title) {
        var _id = id;
        var _title =title || undefined ? title : "未定义";
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _legend = ['男', '女', '未说明','在押人员关押量人数'];
        var _man =[7,6,8,5,4,6,4,6,4,2,5,8,4,7,6,5,6];
        var _woman = [3,4,6,4,2,4,4,4,3,5,5,7,8,4,9,7,8];
        var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var result=[];
        for(var i=0;i<_man.length;i++)
        {
            result[i]=_man[i]+_woman[i]+_unknown[i];
        }
        var option = {
            backgroundColor: "#344b58",
            "title": {
                "text":_title,
                "subtext": '',
                x: "4%",

                textStyle: {
                    color: '#fff',
                    fontSize: '22'
                },
                subtextStyle: {
                    color: '#90979c',
                    fontSize: '20',

                },
            },
            "tooltip": {
                "trigger": "axis",
                "axisPointer": {
                    "type": "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            "grid": {
                "borderWidth": 0,
                "top": 110,
                "bottom": 95,
                textStyle: {
                    color: "#fff"
                }
            },
            "legend": {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                    fontSize:20
                },
                "data":_legend
            },


            "calculable": true,
            "xAxis": [{
                "type": "category",
                "axisLine": {
                    lineStyle: {
                        color: '#90979c',
                        fontSize:15
                    }
                },
                "splitLine": {
                    "show": false
                },
                "axisTick": {
                    "show": false
                },
                "splitArea": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,

                },
                "data": _dqm,
            }],
            "yAxis": [{
                "type": "value",
                
                "splitLine": {
                    "show": false
                },
                "axisLine": {
                    lineStyle: {
                        color: '#90979c',
                    }
                },
                "axisTick": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,

                },
                "splitArea": {
                    "show": false
                },
                axisLabel:{
                    fontSize:'20'
                },
            }],

            "series": [{
                "name": _legend[0],
                "type": "bar",
                "stack": "总量",
                "barMaxWidth": 35,
                "barGap": "10%",
                "itemStyle": {
                    "normal": {
                        "color": "rgba(255,185,15,1)",
                        "label": {
                            "show": true,
                            "textStyle": {
                                "color": "#fff"
                            },
                            "position": "insideTop",
                            formatter: function(p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data":_man,
            },
                {
                    "name": _legend[1],
                    "type": "bar",
                    "stack": "总量",
                    "barMaxWidth": 35,
                    "barGap": "10%",
                    "itemStyle": {
                        "normal": {
                            "color": "rgba(255,144,128,1)",
                            "label": {
                                "show": true,
                                "textStyle": {
                                    "color": "#fff"
                                },
                                "position": "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    "data": _woman
                },
                {
                    "name": _legend[2],
                    "type": "bar",
                    "stack": "总量",
                    "barMaxWidth": 35,
                    "barGap": "10%",
                    "itemStyle": {
                        "normal": {
                            "color": "rgba(0,191,183,1)",
                            "label": {
                                "show": true,
                                textStyle: {
                                    color: "#fff",
                                    fontSize:10
                                },
                                "position": "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    "data": _unknown
                }, {
                    "name": _legend[3],
                    "type": "line",
                    smooth:true,
                    // "stack": "总量",
                    symbolSize: 10,
                    symbol: 'circle',
                    "itemStyle": {
                        "normal": {
                            "color": "rgba(252,230,48,1)",
                            "barBorderRadius": 0,
                            "label": {
                                "show": true, 
                                textStyle:{
                                    fontSize:20
                                },
                                "position": "top",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    "data":result
                }
            ]
        }
        var myChar = echarts.init(document.getElementById(_id));
        myChar.setOption(option);
    }
    
    //户籍地分析
    var _hjdChar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['本省', '外省', '外国'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _bs=[10,0,0,50,0,0,0,0,70,0,0,10,0,0,0,50,0];
        var _ws=[60,0,0,80,0,0,0,0,80,0,0,70,0,50,0,0,90];
        var _wg=[50,0,0,90,0,0,0,50,0,0,0,60,0,0,30,0,0];
        var myChar = echarts.init(document.getElementById(_id));
        var option = {
            backgroundColor: '#0f375f',
            title: {
                text: '户籍地分析',
                textStyle: {
                    fontWeight: 'normal',
                    fontSize: 25,
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
                        color: '#d9dde3'
                    }
                },
                data:_dqm,
                axisLabel: {
                    fontSize:'22'
                }
            }],
            yAxis: [{
                type: 'value',
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#d9dde3'
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
            dataZoom: [{
                show: false,
                fillerColor:"white",
                height: 12,
                xAxisIndex: [0],
                bottom:'8%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"white",
                },
                textStyle:{
                    color:"white"
                },
                borderColor:"white"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [{
                name: '本省',
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
                data: _bs
            }, {
                name: '外省',
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
                data: _ws
            }, {
                name: '外国',
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
                data: _wg
            } ]
        };
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssqsfx/gjfxCount',
        //     type:"get",
        //     dataType:"json",
        //     success:function (data) {
        //         var option={
        //             xAxis: [{}],
        //             yAxis:[{}],
        //             series:[{
        //                 name:'中国',
        //                 data:data.result.zg
        //             },{
        //                 name: '外籍',
        //                 data:data.result.wj
        //             },{
        //                 name: '香港',
        //                 data:data.result.xg
        //             },{
        //                 name: '澳门',
        //                 data:data.result.am
        //             },{
        //                 name:'台湾',
        //                 data:data.result.tw
        //             }]
        //         }
        //         myChar.setOption(option);
        //     },error:function () {
        //     }
        // });
    }
    //年龄分析
    var _nlfxLine = function (id,url,title) {
        var _title = title || undefined ? title :"未定义";
        var myChar = echarts.init(document.getElementById(id));
        var  option = {
            title: {
                text: _title,
                textStyle:{
                    fontSize:22
                }
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
                    data : ['不满18岁','18-25岁','25-35岁','35岁以上'],
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
                    data:[0,10,0,0]
                },
                {
                    name:'东城区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  {
                    name:'西城区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'朝阳区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,10,0,0]
                },
                {
                    name:'丰台区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,40,0]
                },
                {
                    name:'石景山区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },
                {
                    name:'海淀区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data:[0,0,20,0]
                },
                {
                    name:'门头沟区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'房山区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  
                {
                    name:'通州区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'顺义区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },
                {
                    name:'昌平区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,15]
                },
                {
                    name:'大兴区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },                    
                {
                    name:'平谷区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'怀柔区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,30]
                },
                {
                    name:'密云区',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[0,0,60,0]
                },
                {
                    name:'延庆区',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
            ],
            textStyle:{
                fontSize:16
            }
        };
        myChar.setOption(option);
            //////////////////////////////
            // $.ajax({
            //     url: '/kssqsfx/gyqxCount',
            //     type: "get",
            //     dataType: "json",
            //     success: function (res) {
            //         console.log(res.result,111222)
            //         var gyqx = res.result;
            //         var option={
            //             xAxis: [{}],
            //             yAxis:[{}],
            //             series:[{
            //                 name:'北京市',
            //                 data:gyqx[0].data
            //             },
            //             {
            //                 name:'东城区',
            //                 data:gyqx[1].data
            //             },  {
            //                 name:'西城区',
            //                 data:gyqx[2].data
            //             },
            //             {
            //                 name:'朝阳区',
            //                 data:gyqx[3].data
            //             },
            //             {
            //                 name:'丰台区',
            //                 data:gyqx[4].data
            //             },
            //             {
            //                 name:'石景山区',
            //                 data:gyqx[5].data
            //             },
            //             {
            //                 name:'海淀区',
            //                 data:gyqx[6].data
            //             },
            //             {
            //                 name:'门头沟区',
            //                 data:gyqx[7].data
            //             },
            //             {
            //                 name:'房山区',
            //                 data:gyqx[8].data
            //             },  
            //             {
            //                 name:'通州区',
            //                 data:gyqx[9].data
            //             },
            //             {
            //                 name:'顺义区',
            //                 data:gyqx[10].data
            //             },
            //             {
            //                 name:'昌平区',
            //                 data:gyqx[11].data
            //             },
            //             {
            //                 name:'大兴区',
            //                 data:gyqx[12].data
            //             },                    
            //             {
            //                 name:'平谷区',
            //                 data:gyqx[13].data
            //             },
            //             {
            //                 name:'怀柔区',
            //                 data:gyqx[14].data
            //             },
            //             {
            //                 name:'密云区',
            //                 type:'line',
            //                 data:gyqx[15].data
            //             },
            //             {
            //                 name:'延庆区',
            //                 data:gyqx[16].data
            //             }]
            //         }
            //         myChar.setOption(option);
    
            //     },
            //     error:function(error){
            //         console.log(error,222222)
            //     }
            // })
        }
        //文化程度分析
        var _whcdChar=function(id,url,title){
            var _id = id;
            var _title=title || undefined ? title : '未定义';
            var _yl =  ['文盲', '小学', '中学','大专以上'];
            var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var myChar = echarts.init(document.getElementById(_id));
          /*   var _nan = [];
            var _nv = [];
            var _qt = []; */
            var option = {
                backgroundColor:'#fff',
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
                        color: "#2f2f2f",
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
                            color: '#2f2f2f'
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
                        show: true,
                        lineStyle: {
                            color: '#2f2f2f'
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
                dataZoom: [{
                    show: true,
                    fillerColor:"#f5844a",
                    height: 12,
                    xAxisIndex: [0],
                    bottom:'8%',
                    start: 0,
                    end: 60,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle:{
                        color:"white",
                    },
                    textStyle:{
                        color:"#2f2f2f"
                    },
                    borderColor:"white"
                }, {
                    type: "inside",
                    show: true,
                    height: 15,
                    start: 1,
                    end: 35
                }],
                series: [{
                    name: '文盲',
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
                    data: [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70],
                },
                    {
                        name: '小学',
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
                        data: [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100],
                    },
                    {
                        name: '中学',
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
                        data:[100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40],
                    },
                    {
                        name: '大专以上',
                        type: 'bar',
                        barWidth: '15%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#c53a24'
                                }, {
                                    offset: 1,
                                    color: '#de7225'
                                }]),
                                barBorderRadius: 11,
                            }
                        },
                        data:[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100],
                    }]
            };
            myChar.setOption(option);
            // $.ajax({
            //     url:'/kssqsfx/shseCount',
            //     type:"get",
            //     dataType:"json",
            //     success:function (data) {
            //         var option={
            //             xAxis:{},
            //             yAxis:{},
            //             series:[{
            //                 name: '男',
            //                 data:data.result.nan
            //             },{
            //                 name: '女',
            //                 data:data.result.nv
            //             },{
            //                 name: '其他',
            //                 data:data.result.qt
            //             }]
            //         }
            //         myChar.setOption(option);
            //     },error:function () {
            //     }
            // });
        }
        //健康情况分析
        var _jkzkfxChar= function (id,url,title) {
            var _id=id;
            var _title = title || undefined ?title:"未定义";
            var _jsmc = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var _sjlx=['健康或良好','一般或较弱','有病','有生理缺陷','残废'];
            var myChar = echarts.init(document.getElementById(_id));
            var  option = {
             title: {
                 text: _title,
                 textStyle:{
                    color: '#2f2f2f',
                    fontSize:'22'
                 }
             },
            //  backgroundColor: '#091034',
             legend: {
                 data: _sjlx,
                 textStyle: {
                     color: '#2f2f2f',
                     fontSize:'20'
                 },
             },
             grid: {
                 containLabel: true,
                 left: 0,
                 right: 15,
                 bottom: 30
             },
             tooltip: {
                 show: true,
                 backgroundColor: '#fff',
                 borderColor: '#ddd',
                 borderWidth: 1,
                 textStyle: {
                     color: '#3c3c3c',
                     fontSize: 16
                 },
                 formatter: function(p) {
                     console.log(p);
                     var _arr = p.seriesName.split('/'),
                         idx = p.seriesIndex;//1，2，3
                     return '名称：' + p.seriesName + '<br>' + '数量：' + p.value + '<br>' ;
                 },
                 extraCssText: 'box-shadow: 0 0 5px rgba(0, 0, 0, 0.1)'
             },
             xAxis: {
                 min:0,
                 max:500,
                 splitNumber:20,
                 axisLine: {
                     show: false
                 },
                 axisTick: {
                     show: false
                 },
                 splitLine: {
                     show: false
                 },
                 axisLabel:{
                     fontSize:22,
                     color:'#2f2f2f'
                 }
 
             },
             yAxis: [{
                 data: _jsmc,
                 axisLabel: {
                     fontSize: 17,
                     color: '#2f2f2f'
 
                 },
                 axisLine: {
                     show: false
                 },
                 axisTick: {
                     show: false
                 },
                 splitLine: {
                     show: false
                 }
             }],
             series: [{
                 type: 'bar',
                 name: _sjlx[0],
                 stack: '2',
                 legendHoverLink: false,
                 barWidth: 20,
                 itemStyle: {
                     normal: {
                         color: '#7E47FF'
                     },
                     emphasis: {
                         color: '#7E47FF'
                     }
                 },
                 data:[10,20,30,40,50,60,70,80,90,80,70,60,50,40,30,20,10],
             }, {
                 type: 'bar',
                 name:_sjlx[1],
                 stack: '2',
                 legendHoverLink: false,
                 barWidth: 20,
                 itemStyle: {
                     normal: {
                         color: '#FD5916'
                     },
                     emphasis: {
                         color: '#FD5916'
                     }
                 },
                 data: [10,20,30,40,50,60,70,80,90,80,70,60,50,40,30,20,10],
             }, {
                 type: 'bar',
                 stack: '2',
                 name: _sjlx[2],
                 legendHoverLink: false,
                 barWidth: 20,
                 itemStyle: {
                     normal: {
                         color: '#01A4F7'
                     },
                     emphasis: {
                         color: '#01A4F7'
                     }
                 },
                 data: [10,20,30,40,50,60,70,80,90,80,70,60,50,40,30,20,10],
             }, {
                 type: 'bar',
                 stack: '2',
                 name: _sjlx[3],
                 legendHoverLink: false,
                 barWidth: 20,
                 itemStyle: {
                     normal: {
                         color: '#2EDDCD'
                     },
                     emphasis: {
                         color: '#2EDDCD'
                     }
                 },
                 data: [10,20,30,40,50,60,70,80,90,80,70,60,50,40,30,20,10],
             }]
         };
         myChar.setOption(option);
            // $.ajax({
            //     url:'/kssqsfx/ryflfxCount',
            //     type:"get",
            //     dataTyle:"json",
            //     success:function (data) {
            //      var option={
            //          xAxis:{},
            //          yAxis:{},
            //          series:[{
            //              name: _sjlx[0],
            //              data:data.result.lh
            //          },{
            //              name:_sjlx[1],
            //              data:data.result.so
            //          },{
            //              name: _sjlx[2],
            //              data:data.result.slqx
            //          },{
            //              name: _sjlx[3],
            //              data:data.result.cz
            //          }]
            //      }
            //      myChar.setOption(option);
            //     },error:function () {
 
            //     }
            // });
 
        }
    //////////////
    return{
        zyrygylfxChar:_zyrygylfxBar,
        hjdChar:_hjdChar,
        nlfxChar:_nlfxLine,
        whcdfxChar:_whcdChar,
        jkzkfxChar:_jkzkfxChar,
    }
})