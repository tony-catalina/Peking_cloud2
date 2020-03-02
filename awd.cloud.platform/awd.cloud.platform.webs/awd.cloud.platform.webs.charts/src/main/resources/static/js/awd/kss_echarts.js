define("awd/kss_echarts", [
    "../plugins/e-charts/echarts"
],
    function(require) {
         //重点人员分析
         var _zdryfxBar=function (id,url,title) {
            var _id = id;
            var _title =title || undefined ? title : "未定义";
                /////////////////////
                var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
                var _legend = ['男', '女', '未说明','重点人员人数'];
                var result=[];
                var _man = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
                var _woman = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
                var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
                var myChar = echarts.init(document.getElementById(_id));
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
                            fontSize: '16',

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
                            fontSize:22
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
                    dataZoom: [{
                        show: true,
                        height: 12,
                        fillerColor:'#748087',
                        xAxisIndex: [0],
                        bottom:'8%',
                        start: 0,
                        end: 60,
                        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                        handleSize: '110%',
                        handleStyle:{
                            color:"##344b58",
        
                        },
                        textStyle:{
                            color:"#344b58"
                        },
                        borderColor:"#344b58"
                    }, {
                        type: "inside",
                        show: true,
                        height: 15,
                        start: 1,
                        end: 35
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
                        data:_man
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
                            data:_woman
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
                            data:_unknown
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
                                        "position": "start",
                                        formatter: function(p) {
                                            return p.value > 0 ? (p.value) : '';
                                        }
                                    }
                                }
                            },
                            data:result
                        }
                    ]
                }
                
                myChar.setOption(option);
                    // ///////////////////////
                    $.ajax({
                        url:'/kssqsfx/zdryCount',
                        type:"get",
                        dataType:"json", 
                        success:function (res) {
                            console.log(res,9999);
                            var _man=res.result.man
                            var _woman=res.result.woman
                            var _unknown=res.result.unknow
                            var result=[]
                            for(var i=0;i<_man.length;i++)
                            {
                                result[i]=_man[i]+_woman[i]+_unknown[i];
                            }
                            var option={
                                xAxis: [{}],
                                yAxis:[{}],
                                series:[{
                                        "name": _legend[0],
                                        data:_man
                                    },{
                                        "name": _legend[1],
                                        data:_woman
                                    },{
                                        "name": _legend[2],
                                        data:_unknown
                                    },{
                                        "name": _legend[3],    
                                        data:result
                                }]
                            }
                            myChar.setOption(option);
                        },
                        error:function (error) {
                            console.log(error,11111)
                        }
                    });         
        }



        //医疗情况分析
        var  _ylqkfxLine= function (id, url, title) {
            var _id = id ;
            var _title = title || undefined ? title :"未定义";
            /* var _jys =[];
            var _swjy=[];
            var _snjy=[]; */
            var _legend =['就医数', '所外就医', '所内就医'];
            var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
             
            var myChar = echarts.init(document.getElementById(_id));
            var option = {  
                title: {
                    text: _title
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    color: ["#F58080", "#47D8BE", "#F9A589"],
                    data: _legend,
                    left: 'center',
                    top: 'top',
                    textStyle:{
                        fontSize:20
                    }
                },
                grid: {
                    top: 'middle',
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    height: '80%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data: _dqm,
                    axisLabel: {
                        fontSize:'16'
                    },
                    axisLine: {
                        lineStyle: {
                            color: "#999"
                        }
                    }
                },
                yAxis: {
                    type: 'value',

                    splitLine: {
                        lineStyle: {
                            type: 'dashed',
                            color: '#DDD'
                        }
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: "#333"
                        },
                    },
                    nameTextStyle: {
                        color: "#999"
                    },
                    splitArea: {
                        show: false
                    },
                    axisLabel:{
                        fontSize:'25'
                    }
                },
                "dataZoom": [{
                    fillerColor:"#f9a689",
                    "show": true,
                    "height": 12,
                    "xAxisIndex": [
                        0
                    ],
                    bottom:'6%',
                    "start": 10,
                    "end": 90,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle:{
                        color:"#ebd373",
    
                    },
                    textStyle:{
                        color:"#fff"},
                    borderColor:"#ebd373"
                }, {
                    "type": "inside",
                    "show": true,
                    "height": 15,
                    "start": 1,
                    "end": 35
                }],
                series: [{
                    name: _legend[0],
                    type: 'line',
                    data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    color: "#F58080",
                    lineStyle: {
                        normal: {
                            width: 5,
                            color: {
                                type: 'linear',

                                colorStops: [{
                                    offset: 0,
                                    color: '#FFCAD4' // 0% 处的颜色
                                }, {
                                    offset: 0.4,
                                    color: '#F58080' // 100% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#F58080' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            },
                            shadowColor: 'rgba(245,128,128, 0.5)',
                            shadowBlur: 10,
                            shadowOffsetY: 7
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#F58080',
                            borderWidth: 10,
                            /*shadowColor: 'rgba(72,216,191, 0.3)',
                             shadowBlur: 100,*/
                            borderColor: "#F58080"
                        }
                    },
                    smooth: true
                },
                    {
                        name: _legend[1],
                        type: 'line',
                        data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        lineStyle: {
                            normal: {
                                width: 5,
                                color: {
                                    type: 'linear',

                                    colorStops: [{
                                        offset: 0,
                                        color: '#AAF487' // 0% 处的颜色
                                    },
                                        {
                                            offset: 0.4,
                                            color: '#47D8BE' // 100% 处的颜色
                                        }, {
                                            offset: 1,
                                            color: '#47D8BE' // 100% 处的颜色
                                        }
                                    ],
                                    globalCoord: false // 缺省为 false
                                },
                                shadowColor: 'rgba(71,216,190, 0.5)',
                                shadowBlur: 10,
                                shadowOffsetY: 7
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#AAF487',
                                borderWidth: 10,
                                /*shadowColor: 'rgba(72,216,191, 0.3)',
                                 shadowBlur: 100,*/
                                borderColor: "#AAF487"
                            }
                        },
                        smooth: true
                    },
                    {
                        name: _legend[2],
                        type: 'line',
                        data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                        lineStyle: {
                            normal: {
                                width: 5,
                                color: {
                                    type: 'linear',

                                    colorStops: [{
                                        offset: 0,
                                        color: '#F6D06F' // 0% 处的颜色
                                    },
                                        {
                                            offset: 0.4,
                                            color: '#F9A589' // 100% 处的颜色
                                        }, {
                                            offset: 1,
                                            color: '#F9A589' // 100% 处的颜色
                                        }
                                    ],
                                    globalCoord: false // 缺省为 false
                                },
                                shadowColor: 'rgba(249,165,137, 0.5)',
                                shadowBlur: 10,
                                shadowOffsetY: 7
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#F6D06F',
                                borderWidth: 10,

                                borderColor: "#F6D06F"
                            }
                        },
                        smooth: true
                    }
                ]
            };
            myChar.setOption(option);
            $.ajax({
                url:'/kssqsfx/ylwsCount',
                type:"get",
                dataType:"json",
                success:function (data) {
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: _legend[0],
                            data:data.result.jys
                        },{
                            name: _legend[1],
                            data:data.result.swjy
                        },{
                            name: _legend[2],
                            data:data.result.snjy
                        }]
                    }
                   /*  if(data.status==200){
                        _jys = data.result.jys;
                    console.log(_jys)
                    _swjy = data.result.swjy;
                    _snjy = data.result.snjy
                    
                    }else{
                    } */
                    
                    myChar.setOption(option);
                },error:function () {

                }
            });
            
        }



        //人员分类分析
       var _ryflfxLine= function (id,url,title) {
           var _id=id;
           var _title = title || undefined ?title:"未定义";

           var _jsmc = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
           var _sjlx=['离婚','丧偶','有生理缺陷','残废'];
           var myChar = echarts.init(document.getElementById(_id));
          /*  var _lh = [];
           var _so = [];
           var _yslqx = [];
           var _cf = []; */
           var  option = {
            title: {
                text: _title
            },
            backgroundColor: '#091034',
            legend: {
                data: _sjlx,
                textStyle: {
                    color: '#ccc',
                    fontSize:'25'
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
                    fontSize:'25'
                }

            },
            yAxis: [{
                data: _jsmc,
                axisLabel: {
                    fontSize: 17,
                    color: '#fff'

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
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: '#7E47FF'
                    },
                    emphasis: {
                        color: '#7E47FF'
                    }
                },
                data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, {
                type: 'bar',
                name:_sjlx[1],
                stack: '2',
                legendHoverLink: false,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: '#FD5916'
                    },
                    emphasis: {
                        color: '#FD5916'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, {
                type: 'bar',
                stack: '2',
                name: _sjlx[2],
                legendHoverLink: false,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: '#01A4F7'
                    },
                    emphasis: {
                        color: '#01A4F7'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }, {
                type: 'bar',
                stack: '2',
                name: _sjlx[3],
                legendHoverLink: false,
                barWidth: 40,
                itemStyle: {
                    normal: {
                        color: '#2EDDCD'
                    },
                    emphasis: {
                        color: '#2EDDCD'
                    }
                },
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            }]
        };
        myChar.setOption(option);
           $.ajax({
               url:'/kssqsfx/ryflfxCount',
               type:"get",
               dataTyle:"json",
               success:function (data) {
                var option={
                    xAxis:{},
                    yAxis:{},
                    series:[{
                        name: _sjlx[0],
                        data:data.result.lh
                    },{
                        name:_sjlx[1],
                        data:data.result.so
                    },{
                        name: _sjlx[2],
                        data:data.result.slqx
                    },{
                        name: _sjlx[3],
                        data:data.result.cz
                    }]
                }
                myChar.setOption(option);
               },error:function () {

               }
           });

       }

    //违规情况分析
       var _wgqkfxLineAndBar= function (id,url,title) {
            var _id = id;
            var _title = title || undefined ? title : "未定义";
            var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var _wgfl=['监区违规', '监室违规','人员违规','违规数']
            var _jqwg = [];
            var _jswg = [];
            var _rywg = [];
            var _wgs = [];
            for(var i=0;i<_jqwg.length;i++)
            {
                _wgs[i]=_jqwg[i]+_jswg[i]+_rywg[i];
            }
            var myChar = echarts.init(document.getElementById(_id));
            var  option = {
                title: {
                    text:_title,
                    x: 'center',
                    y: 0,
                    textStyle:{
                        color:'#B4B4B4',
                        fontSize:20,
                        fontWeight:'normal',
                    },
                },
                backgroundColor: '#191E40',
                tooltip: {
                    trigger: 'axis',
                    backgroundColor:'rgba(255,255,255,0.1)',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            backgroundColor: '#7B7DDC'
                        }
                    }
                },
                legend: {
                    data:_wgfl ,
                    textStyle: {
                        color: '#B4B4B4',
                        fontSize:'20'
                    },
                    top:'7%',
                },
                grid:{
                    x:'12%',
                    width:'82%',
                    y:'12%',
                },
                xAxis: {
                    data: _dqm,
                    axisLine: {
                        lineStyle: {
                            color: '#B4B4B4'
                        }
                    },
                    axisTick:{
                        show:false,
                    },
                    axisLabel:{
                        fontSize:'22'
                    }
                },
                yAxis: [
                    {
                    type: 'value',
                    min: 0,
                    max: 60,
                    interval: 10,
                    splitLine: {show: false},
                    axisLine: {
                        lineStyle: {
                            color: '#B4B4B4',
                        }
                    },
                    axisLabel:{
                        formatter:'{value} ',
                        fontSize:'15'
                    }
                },
                    {
                        type: 'value',
                        min: 0,
                        max: 60,
                        interval: 10,
                        splitLine: {show: false},
                        axisLine: {
                            lineStyle: {
                                color: '#B4B4B4',
                            }
                        },
                        axisLabel:{
                            formatter:'{value} ',
                            fontSize:'15'
                        }
                    }
                ],
                dataZoom: [{
                    show: true,
                    height: 12,
                    fillerColor:"#7287db",
                    xAxisIndex: [0],
                    bottom:'2%',
                    start: 0,
                    end: 60,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle:{
                        color:"#191e40",
    
                    },
                    textStyle:{
                        color:"#191e40"
                    },
                    borderColor:"#191e40"
                }, {
                    type: "inside",
                    show: true,
                    height: 15,
                    start: 1,
                    end: 35
                }],
                series: [{
                    name: _wgfl[3],
                    type: 'line',
                    smooth: true,
                    showAllSymbol: true,
                    symbol: 'emptyCircle',
                    symbolSize: 8,
                    yAxisIndex: 1,
                    itemStyle: {
                        normal: {
                        color:'#F02FC2'},
                    },
                    data: _wgs
                    },
                    {
                        name: _wgfl[0],
                        type: 'bar',
                        barWidth: 10,
                        itemStyle: {
                            normal: {
                                barBorderRadius: 5,
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#956FD4'},
                                        {offset: 1, color: '#3EACE5'}
                                    ]
                                )
                            }
                        },
                        data: _jqwg
                    },

                    {
                        name: _wgfl[1],
                        type: 'bar',
                        barGap: '-100%',
                        barWidth: 10,
                        itemStyle: {
                            normal: {
                                barBorderRadius: 5,
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: 'rgba(156,107,211,0.5)'},
                                        {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                                        {offset: 1, color: 'rgba(156,107,211,0)'}
                                    ]
                                )
                            }
                        },
                        z: -12,
                        data: _jswg
                    },
                    {
                        name: _wgfl[2],
                        type: 'bar',
                        barGap: '-100%',
                        barWidth: 10,
                        itemStyle: {
                            normal: {
                                barBorderRadius: 5,
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: 'rgba(156,107,211,0.5)'},
                                        {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                                        {offset: 1, color: 'rgba(156,107,211,0)'}
                                    ]
                                )
                            }
                        },
                        z: -12,
                        data: _rywg
                    }
            ]};
            
            myChar.setOption(option);
            $.ajax({
                url:'/kssqsfx/wgqkfxCount',
                type:"get",
                dataType:"json",
                success:function (data) {
                    if(data.status==200){
                        _jqwg = data.result.jqwg;
                        _jswg = data.result.jswg;
                        _rywg = data.result.rywg;
                        var _wgs = [];
                        for(var i=0;i<_jqwg.length;i++)
                        {
                            _wgs[i]=_jqwg[i]+_jswg[i]+_rywg[i];
                        }
                        var option={
                            xAxis: {},
                            yAxis: [{}],
                            series:[
                                {
                                    name: _wgfl[3],
                                    data: _wgs
                                },
                                {
                                    name: _wgfl[0],
                                    data: _jqwg
                                },
                                {
                                    name: _wgfl[1],
                                    data: _jswg
                                },
                                {
                                    name: _wgfl[2],
                                    data: _rywg
                                }
                            ]
                            
                        }
                        myChar.setOption(option);
                    }else{

                    }
                },error:function (error) {
                    console.log(error)
                }
            });

           
          /*  var _jqwg=[12,4,23,11,2,9,8,7,9,12];
           var _jswg=[11,10,9,6,4,16,4,8,12,8];
           var _rywg=[0,1,0,5,6,2,3,0,4,6];
          var _wgs =[]; */
           
// option
           
       }
 

       var _ajqkfxBar =function (id,url,title) {
           var _id=id;
           var _title = title || undefined ? title :"未定义";
           var myChar = echarts.init(document.getElementById(_id));
          
           // 指定图表的配置项和数据
           var _jsmc = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var _sjlx=  ['军人违反职责案', '开设赌场', '贪污贿赂案', '盗窃案'];
         
           /* var _jrwfzza = [];
           var _ksdc = [];
           var _twhla = [];
           var _dqa = []; */
           var option = {
            color: ['#388BFF', '#05C3FA', '#F6931C', '#FFD52E'],
            tooltip: {
                trigger: 'axis',
            },
            legend: {

                top: '8%',
                data:_sjlx ,
                textStyle:{
                    fontSize:'25'
                }
            },
            grid: { //图表的位置
                top: '20%',
                left: '3%',
                right: '4%',
                bottom: '5%',
                containLabel: true
            },
            yAxis: [{
                type: 'value',
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value} ',
                    fontSize:'25'
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        type: 'dashed'
                    }
                },
                show: true

            }],
            xAxis: [{
                type: 'category',
                axisLabel: {
                    interval: 0,
                    show: true,
                    splitNumber: 15,
                    textStyle: {
                        fontSize: 25,
                        color: '#000'
                    },

                },
                data: _jsmc,
            }],
            "dataZoom": [{
                fillerColor:"#d1bc94",
                "show": true,
                "height": 12,
                "xAxisIndex": [
                    0
                ],
                bottom:'2%',
                "start": 10,
                "end": 90,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ebd373",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"#ebd373"
            }, {
                "type": "inside",
                "show": true,
                "height": 15,
                "start": 1,
                "end": 35
            }],
            series: [{
                name: _sjlx[0],
                type: 'bar',
                stack: 'sum',
                barWidth: '20px',
                data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],

            },
                {
                    name: _sjlx[1],
                    type: 'bar',
                    barWidth: '20px',
                    stack: 'sum',
                    data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],

                },
                {
                    name:_sjlx[2],
                    type: 'bar',
                    color: '#F6931C',
                    stack: 'sum1',
                    barWidth: '20px',
                    data: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],

                },
                {
                    name: _sjlx[3],
                    type: 'bar',
                    color: '#FFD52E',
                    stack: 'sum1',
                    barWidth: '20px',
                    data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],

                },
            ]
        };
        myChar.setOption(option);
           $.ajax({
               url :'/kssqsfx/ajqkfxCount',
               type:"get",
               dataType:"json",
               success:function (data) {
                var option={
                    xAxis:{},
                    yAxis:{},
                    series:[{
                        name: _sjlx[0],
                        data:data.result.jrwfzza
                    },{
                        name:_sjlx[1],
                        data:data.result.ksdc
                    },{
                        name: _sjlx[2],
                        data:data.result.twhla
                    },{
                        name: _sjlx[3],
                        data:data.result.dqa
                    }]
                }
                   myChar.setOption(option);
               },error:function () {

               }
           });
           
       }

  
       var  _lsfxfxBarAndLine=function (id,url,title) {
        var _id = id;
        var _tilte = title || undefined ? title : "未定义";
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _lsfx=['留所服刑分析','男','女','未说明的性别'];
        var _man = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _woman = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]; 
        var result=[];
        var myChar = echarts.init(document.getElementById(_id));
         for(var i=0;i<_man.length;i++)
         {
             result[i]=_man[i]+_woman[i]+_unknown[i];
         }
        
        var option = {
                     "title": {
                        "text": _tilte,
                        "left": "center",
                        "y": "10",
                        "textStyle": {
                            "color": "#fff"
                        }
                    },
                    "backgroundColor": "#1c2e40",
                    "color": "#384757",
                    "tooltip": {
                        "trigger": "axis",
                        "axisPointer": {
                            "type": "cross",
                            "crossStyle": {
                                "color": "#384757"
                            }
                        }
                    },
                    "legend": {
                        "data": _lsfx,
                        "top": "10%",
                        "textStyle": {
                            "color": "#fff",
                            fontSize:'25'
                        }
                    },
                    "xAxis": [
                        {
                            "type": "category",
                            "data":_dqm,
                            "axisPointer": {
                                "type": "shadow"
                            },
                            "axisLabel": {
                                "show": true,
                                "textStyle": {
                                    "color": "#7d838b",
                                    fontSize:'15'
                                }
                            }
                        }
                    ],
                    "yAxis": [
                        {
                            "type": "value",
                            "name": "留所服刑分析",
                            "nameTextStyle": {
                                "color": "#7d838b",
                                fontSize:'20'
                            },
                            "axisLabel": {
                                "show": true,
                                "textStyle": {
                                    "color": "#7d838b",
                                    fontSize:'25'
                                }
                            },
                            "axisLine": {
                                "show": true
                            },
                            "splitLine": {
                                "lineStyle": {
                                    "color": "#7d838b"
                                }
                            }
                        }
                    ],
                    dataZoom: [{
                        show: true,
                        height: 12,
                        xAxisIndex: [0],
                        bottom:'2%',
                        start: 0,
                        end: 60,
                        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                        handleSize: '110%',
                        handleStyle:{
                            color:"#d3dee5",
        
                        },
                        textStyle:{
                            color:"#fff"
                        },
                        borderColor:"#90979c"
                    }, {
                        type: "inside",
                        show: true,
                        height: 15,
                        start: 1,
                        end: 35
                    }],
                    "grid": {
                        "top": "20%"
                    },
                    "series": [
                        {
                            "name": _lsfx[1],
                            "type": "bar",
                            "data":_man,
                            "barWidth": "auto",
                            "itemStyle": {
                                "normal": {
                                    "color": {
                                        "type": "linear",
                                        "x": 0,
                                        "y": 0,
                                        "x2": 0,
                                        "y2": 1,
                                        "colorStops": [
                                            {
                                                "offset": 0,
                                                "color": "rgba(255,37,117,0.7)"
                                            },
                                            {
                                                "offset": 0.5,
                                                "color": "rgba(0,133,245,0.7)"
                                            },
                                            {
                                                "offset": 1,
                                                "color": "rgba(0,133,245,0.3)"
                                            }
                                        ],
                                        "globalCoord": false
                                    }
                                }
                            }
                        },
                        {
                            "name":_lsfx[2],
                            "type": "bar",
                            "data": _woman,
                            "barWidth": "auto",
                            "itemStyle": {
                                "normal": {
                                    "color": {
                                        "type": "linear",
                                        "x": 0,
                                        "y": 0,
                                        "x2": 0,
                                        "y2": 1,
                                        "colorStops": [
                                            {
                                                "offset": 0,
                                                "color": "rgba(255,37,117,0.7)"
                                            },
                                            {
                                                "offset": 0.5,
                                                "color": "rgba(0,255,252,0.7)"
                                            },
                                            {
                                                "offset": 1,
                                                "color": "rgba(0,255,252,0.3)"
                                            }
                                        ],
                                        "globalCoord": false
                                    }
                                }
                            },
                            "barGap": "0"
                        },{
                            "name":_lsfx[3],
                            "type": "bar",
                            "data": _unknown,
                            "barWidth": "auto",
                            "itemStyle": {
                                "normal": {
                                    "color": {
                                        "type": "linear",
                                        "x": 0,
                                        "y": 0,
                                        "x2": 0,
                                        "y2": 1,
                                        "colorStops": [
                                            {
                                                "offset": 0,
                                                "color": "rgba(255,37,117,0.7)"
                                            },
                                            {
                                                "offset": 0.5,
                                                "color": "rgba(0,255,252,0.7)"
                                            },
                                            {
                                                "offset": 1,
                                                "color": "rgba(0,255,252,0.3)"
                                            }
                                        ],
                                        "globalCoord": false
                                    }
                                }
                            },
                            "barGap": "0"
                        },
                        {
                            "name": _lsfx[0],
                            "type": "line",
                            "data":result,
                            "itemStyle": {
                                "normal": {
                                    "color": "#ffaa00"
                                }
                            },
                            "smooth": true
                        }
                    ]
                };
                myChar.setOption(option);
           $.ajax({
               url:'/kssqsfx/lsfxfxCount',
               type:"get",
               dataType:"json",
               success:function (data) {
                var result=[]
                for(var i=0;i<_man.length;i++)
                {
                    result[i]=_man[i]+_woman[i]+_unknown[i];
                }
                var option={
                    xAxis:{},
                    yAxis:{},
                    series:[{
                        "name": _lsfx[1],
                        data:data.result.man
                    },{
                        "name":_lsfx[2],
                        data:data.result.woman
                    },{
                        "name":_lsfx[3],
                        data:data.result.unknown
                    },{
                        "name": _lsfx[0],
                        data:result
                    }]
                }
             myChar.setOption(option);
            },error:function () {
 
            }
        });
        
      
 
    }

   var _xskhSankey=function (id,url,title) {
       var _id=id;
       var _title = title || undefined ?title:"未定义";
       $.ajax({
           url:url,
           type:"post",
           dataType:"json",
           success:function (data) {

           },error:function () {

           }
       });


       var city={
           '监护医疗所':'#e9bae8',
           '北京市第一看守所':'#3dc5e7',
           '北京市第二看守所':'#2aa12d',
           '北京市第三看守所':'#e88886',
           '北京市第六看守所':'#feda61',
           '东城区看守所':'#d52315',
           '西城区看守所':'#8bc77b',
           '朝阳区看守所':'#990099',
           '丰台区看守所':'#33FFFF',
           '石景山区看守所':'#E38EFF',
           '海淀区看守所':'#66FF33',
           '门头沟区看守所':'#CC3366',
           '房山区看守所':'#FF3333',
           '房山区第二看守所':'#FF0066',
           '通州区看守所':'#CC99FF',
           '顺义区看守所':'#CCFF00',
           '昌平区看守所':'#FFCC00',
           '大兴区看守所':'#FF9966',
           '平谷区看守所':'#FFCCFF',
           '怀柔区看守所':'#CC0000',
           '密云区看守所':'#993366',
           '延庆区看守所':'#3300CC',


           '考核类别':'#fb7f10',
           '考核性质':'#e492d0',
           '违规/故障类型':'#2aa12d',
           '违规项':'#fb7f10',
           '考核时间':'#98c0fd',
           '处理措施':'#4393c3',
           '记录人':'#2166ac',
           '处理结果':'#f97494',
           '单位名称':'#220088',
           '反馈民警':'#C63300',
           '反馈时间':'#55AA00',


       }
       var population= [
           {"source": "监护医疗所", "target": "考核类别",  "value":1},
           {"source": "监护医疗所", "target": "考核性质",  "value": 1},
           {"source": "监护医疗所", "target": "违规/故障类型",  "value": 1},
           {"source": "监护医疗所", "target": "违规项",  "value": 1},
           {"source": "监护医疗所", "target": "考核时间",  "value": 1},
           {"source": "监护医疗所", "target": "处理措施",  "value":1},
           {"source": "监护医疗所", "target": "记录人",  "value": 1},
           {"source": "监护医疗所", "target": "处理结果",  "value": 1},
           {"source": "监护医疗所", "target": "单位名称",  "value": 1},
           {"source": "监护医疗所", "target": "反馈民警",  "value": 1},
           {"source": "监护医疗所", "target": "反馈时间",  "value": 1},


           {"source": "北京市第一看守所", "target": "考核类别",  "value": 2},
           {"source": "北京市第一看守所", "target": "考核性质",  "value": 2},
           {"source": "北京市第一看守所", "target": "违规/故障类型",  "value": 2},
           {"source": "北京市第一看守所", "target": "违规项",  "value": 2},
           {"source": "北京市第一看守所", "target": "考核时间",  "value": 2},
           {"source": "北京市第一看守所", "target": "处理措施",  "value": 2},
           {"source": "北京市第一看守所", "target": "记录人",  "value": 2},
           {"source": "北京市第一看守所", "target": "处理结果",  "value": 2},
           {"source": "北京市第一看守所", "target": "单位名称",  "value":2},
           {"source": "北京市第一看守所", "target": "反馈民警",  "value": 2},
           {"source": "北京市第一看守所", "target": "反馈时间",  "value": 2},


           {"source": "北京市第二看守所", "target": "考核类别",  "value": 3},
           {"source": "北京市第二看守所", "target": "考核性质",  "value": 3},
           {"source": "北京市第二看守所", "target": "违规/故障类型",  "value": 3},
           {"source": "北京市第二看守所", "target": "违规项",  "value": 3},
           {"source": "北京市第二看守所", "target": "考核时间",  "value": 3},
           {"source": "北京市第二看守所", "target": "处理措施",  "value": 3},
           {"source": "北京市第二看守所", "target": "记录人",  "value": 3},
           {"source": "北京市第二看守所", "target": "处理结果",  "value":3},
           {"source": "北京市第二看守所", "target": "单位名称",  "value": 3},
           {"source": "北京市第二看守所", "target": "反馈民警",  "value": 3},
           {"source": "北京市第二看守所", "target": "反馈时间",  "value": 3},

           {"source": "北京市第三看守所", "target": "考核类别",  "value": 4},
           {"source": "北京市第三看守所", "target": "考核性质",  "value": 4},
           {"source": "北京市第三看守所", "target": "违规/故障类型",  "value": 4},
           {"source": "北京市第三看守所", "target": "违规项",  "value": 4},
           {"source": "北京市第三看守所", "target": "考核时间",  "value":4},
           {"source": "北京市第三看守所", "target": "处理措施",  "value": 4},
           {"source": "北京市第三看守所", "target": "记录人",  "value": 4},
           {"source": "北京市第三看守所", "target": "处理结果",  "value": 4},
           {"source": "北京市第三看守所", "target": "单位名称",  "value": 4},
           {"source": "北京市第三看守所", "target": "反馈民警",  "value": 4},
           {"source": "北京市第三看守所", "target": "反馈时间",  "value": 4},


           {"source": "北京市第六看守所", "target": "考核类别",  "value": 5},
           {"source": "北京市第六看守所", "target": "考核性质",  "value": 5},
           {"source": "北京市第六看守所", "target": "违规/故障类型",  "value": 5},
           {"source": "北京市第六看守所", "target": "违规项",  "value": 5},
           {"source": "北京市第六看守所", "target": "考核时间",  "value": 5},
           {"source": "北京市第六看守所", "target": "处理措施",  "value": 5},
           {"source": "北京市第六看守所", "target": "记录人",  "value": 5},
           {"source": "北京市第六看守所", "target": "处理结果",  "value": 5},
           {"source": "北京市第六看守所", "target": "单位名称",  "value": 5},
           {"source": "北京市第六看守所", "target": "反馈民警",  "value": 5},
           {"source": "北京市第六看守所", "target": "反馈时间",  "value": 5},


           {"source": "东城区看守所", "target": "考核类别",  "value": 6},
           {"source": "东城区看守所", "target": "考核性质",  "value": 6},
           {"source": "东城区看守所", "target": "违规/故障类型",  "value": 6},
           {"source": "东城区看守所", "target": "违规项",  "value": 6},
           {"source": "东城区看守所", "target": "考核时间",  "value": 6},
           {"source": "东城区看守所", "target": "处理措施",  "value": 6},
           {"source": "东城区看守所", "target": "记录人",  "value": 6},
           {"source": "东城区看守所", "target": "处理结果",  "value": 6},
           {"source": "东城区看守所", "target": "单位名称",  "value": 6},
           {"source": "东城区看守所", "target": "反馈民警",  "value": 6},
           {"source": "东城区看守所", "target": "反馈时间",  "value": 6},



           {"source": "西城区看守所", "target": "考核类别",  "value": 7},
           {"source": "西城区看守所", "target": "考核性质",  "value":7},
           {"source": "西城区看守所", "target": "违规/故障类型",  "value":7},
           {"source": "西城区看守所", "target": "违规项",  "value": 7},
           {"source": "西城区看守所", "target": "考核时间",  "value": 7},
           {"source": "西城区看守所", "target": "处理措施",  "value": 7},
           {"source": "西城区看守所", "target": "记录人",  "value": 7},
           {"source": "西城区看守所", "target": "处理结果",  "value":7},
           {"source": "西城区看守所", "target": "单位名称",  "value": 7},
           {"source": "西城区看守所", "target": "反馈民警",  "value": 7},
           {"source": "西城区看守所", "target": "反馈时间",  "value": 7},

           {"source": "朝阳区看守所", "target": "考核类别",  "value": 8},
           {"source": "朝阳区看守所", "target": "考核性质",  "value": 8},
           {"source": "朝阳区看守所", "target": "违规/故障类型",  "value":8},
           {"source": "朝阳区看守所", "target": "违规项",  "value": 8},
           {"source": "朝阳区看守所", "target": "考核时间",  "value": 8},
           {"source": "朝阳区看守所", "target": "处理措施",  "value": 8},
           {"source": "朝阳区看守所", "target": "记录人",  "value":8},
           {"source": "朝阳区看守所", "target": "处理结果",  "value": 8},
           {"source": "朝阳区看守所", "target": "单位名称",  "value": 8},
           {"source": "朝阳区看守所", "target": "反馈民警",  "value": 8},
           {"source": "朝阳区看守所", "target": "反馈时间",  "value": 8},


           {"source": "丰台区看守所", "target": "考核类别",  "value": 9},
           {"source": "丰台区看守所", "target": "考核性质",  "value": 9},
           {"source": "丰台区看守所", "target": "违规/故障类型",  "value":9},
           {"source": "丰台区看守所", "target": "违规项",  "value":9},
           {"source": "丰台区看守所", "target": "考核时间",  "value":9},
           {"source": "丰台区看守所", "target": "处理措施",  "value": 9},
           {"source": "丰台区看守所", "target": "记录人",  "value": 9},
           {"source": "丰台区看守所", "target": "处理结果",  "value": 9},
           {"source": "丰台区看守所", "target": "单位名称",  "value": 9},
           {"source": "丰台区看守所", "target": "反馈民警",  "value": 9},
           {"source": "丰台区看守所", "target": "反馈时间",  "value": 9},

           {"source": "石景山区看守所", "target": "考核类别",  "value": 10},
           {"source": "石景山区看守所", "target": "考核性质",  "value": 10},
           {"source": "石景山区看守所", "target": "违规/故障类型",  "value": 10},
           {"source": "石景山区看守所", "target": "违规项",  "value": 10},
           {"source": "石景山区看守所", "target": "考核时间",  "value":10},
           {"source": "石景山区看守所", "target": "处理措施",  "value":10},
           {"source": "石景山区看守所", "target": "记录人",  "value":10},
           {"source": "石景山区看守所", "target": "处理结果",  "value": 10},
           {"source": "石景山区看守所", "target": "单位名称",  "value": 10},
           {"source": "石景山区看守所", "target": "反馈民警",  "value": 10},
           {"source": "石景山区看守所", "target": "反馈时间",  "value":10},

           {"source": "海淀区看守所", "target": "考核类别",  "value":11},
           {"source": "海淀区看守所", "target": "考核性质",  "value": 111},
           {"source": "海淀区看守所", "target": "违规/故障类型",  "value": 11},
           {"source": "海淀区看守所", "target": "违规项",  "value": 11},
           {"source": "海淀区看守所", "target": "考核时间",  "value": 11},
           {"source": "海淀区看守所", "target": "处理措施",  "value":11},
           {"source": "海淀区看守所", "target": "记录人",  "value": 11},
           {"source": "海淀区看守所", "target": "处理结果",  "value": 11},
           {"source": "海淀区看守所", "target": "单位名称",  "value": 11},
           {"source": "海淀区看守所", "target": "反馈民警",  "value": 11},
           {"source": "海淀区看守所", "target": "反馈时间",  "value": 11},


           {"source": "门头沟区看守所", "target": "考核类别",  "value": 12},
           {"source": "门头沟区看守所", "target": "考核性质",  "value": 12},
           {"source": "门头沟区看守所", "target": "违规/故障类型",  "value": 12},
           {"source": "门头沟区看守所", "target": "违规项",  "value": 12},
           {"source": "门头沟区看守所", "target": "考核时间",  "value": 12},
           {"source": "门头沟区看守所", "target": "处理措施",  "value": 12},
           {"source": "门头沟区看守所", "target": "记录人",  "value": 12},
           {"source": "门头沟区看守所", "target": "处理结果",  "value": 12},
           {"source": "门头沟区看守所", "target": "单位名称",  "value":12},
           {"source": "门头沟区看守所", "target": "反馈民警",  "value": 12},
           {"source": "门头沟区看守所", "target": "反馈时间",  "value": 12},


           {"source": "房山区看守所", "target": "考核类别",  "value": 13},
           {"source": "房山区看守所", "target": "考核性质",  "value": 13},
           {"source": "房山区看守所", "target": "违规/故障类型",  "value": 13},
           {"source": "房山区看守所", "target": "违规项",  "value": 13},
           {"source": "房山区看守所", "target": "考核时间",  "value": 13},
           {"source": "房山区看守所", "target": "处理措施",  "value": 13},
           {"source": "房山区看守所", "target": "记录人",  "value": 13},
           {"source": "房山区看守所", "target": "处理结果",  "value":13},
           {"source": "房山区看守所", "target": "单位名称",  "value": 13},
           {"source": "房山区看守所", "target": "反馈民警",  "value": 13},
           {"source": "房山区看守所", "target": "反馈时间",  "value": 13},

           {"source": "房山区第二看守所", "target": "考核类别",  "value": 14},
           {"source": "房山区第二看守所", "target": "考核性质",  "value": 14},
           {"source": "房山区第二看守所", "target": "违规/故障类型",  "value": 14},
           {"source": "房山区第二看守所", "target": "违规项",  "value": 14},
           {"source": "房山区第二看守所", "target": "考核时间",  "value":14},
           {"source": "房山区第二看守所", "target": "处理措施",  "value": 14},
           {"source": "房山区第二看守所", "target": "记录人",  "value": 14},
           {"source": "房山区第二看守所", "target": "处理结果",  "value": 14},
           {"source": "房山区第二看守所", "target": "单位名称",  "value": 14},
           {"source": "房山区第二看守所", "target": "反馈民警",  "value": 14},
           {"source": "房山区第二看守所", "target": "反馈时间",  "value": 14},


           {"source": "通州区看守所", "target": "考核类别",  "value": 15},
           {"source": "通州区看守所", "target": "考核性质",  "value": 15},
           {"source": "通州区看守所", "target": "违规/故障类型",  "value": 15},
           {"source": "通州区看守所", "target": "违规项",  "value": 15},
           {"source": "通州区看守所", "target": "考核时间",  "value": 15},
           {"source": "通州区看守所", "target": "处理措施",  "value": 15},
           {"source": "通州区看守所", "target": "记录人",  "value": 15},
           {"source": "通州区看守所", "target": "处理结果",  "value": 15},
           {"source": "通州区看守所", "target": "单位名称",  "value": 15},
           {"source": "通州区看守所", "target": "反馈民警",  "value": 15},
           {"source": "通州区看守所", "target": "反馈时间",  "value": 15},


           {"source": "顺义区看守所", "target": "考核类别",  "value": 16},
           {"source": "顺义区看守所", "target": "考核性质",  "value": 16},
           {"source": "顺义区看守所", "target": "违规/故障类型",  "value": 16},
           {"source": "顺义区看守所", "target": "违规项",  "value": 16},
           {"source": "顺义区看守所", "target": "考核时间",  "value": 16},
           {"source": "顺义区看守所", "target": "处理措施",  "value": 16},
           {"source": "顺义区看守所", "target": "记录人",  "value": 16},
           {"source": "顺义区看守所", "target": "处理结果",  "value": 16},
           {"source": "顺义区看守所", "target": "单位名称",  "value": 16},
           {"source": "顺义区看守所", "target": "反馈民警",  "value": 16},
           {"source": "顺义区看守所", "target": "反馈时间",  "value": 16},



           {"source": "昌平区看守所", "target": "考核类别",  "value": 17},
           {"source": "昌平区看守所", "target": "考核性质",  "value":17},
           {"source": "昌平区看守所", "target": "违规/故障类型",  "value":17},
           {"source": "昌平区看守所", "target": "违规项",  "value": 17},
           {"source": "昌平区看守所", "target": "考核时间",  "value": 17},
           {"source": "昌平区看守所", "target": "处理措施",  "value": 17},
           {"source": "昌平区看守所", "target": "记录人",  "value": 17},
           {"source": "昌平区看守所", "target": "处理结果",  "value":17},
           {"source": "昌平区看守所", "target": "单位名称",  "value": 17},
           {"source": "昌平区看守所", "target": "反馈民警",  "value": 17},
           {"source": "昌平区看守所", "target": "反馈时间",  "value": 17},

           {"source": "大兴区看守所", "target": "考核类别",  "value": 18},
           {"source": "大兴区看守所", "target": "考核性质",  "value": 18},
           {"source": "大兴区看守所", "target": "违规/故障类型",  "value":18},
           {"source": "大兴区看守所", "target": "违规项",  "value": 18},
           {"source": "大兴区看守所", "target": "考核时间",  "value": 18},
           {"source": "大兴区看守所", "target": "处理措施",  "value": 18},
           {"source": "大兴区看守所", "target": "记录人",  "value":18},
           {"source": "大兴区看守所", "target": "处理结果",  "value": 18},
           {"source": "大兴区看守所", "target": "单位名称",  "value": 18},
           {"source": "大兴区看守所", "target": "反馈民警",  "value": 18},
           {"source": "大兴区看守所", "target": "反馈时间",  "value": 18},


           {"source": "平谷区看守所", "target": "考核类别",  "value": 19},
           {"source": "平谷区看守所", "target": "考核性质",  "value": 19},
           {"source": "平谷区看守所", "target": "违规/故障类型",  "value":19},
           {"source": "平谷区看守所", "target": "违规项",  "value":19},
           {"source": "平谷区看守所", "target": "考核时间",  "value":19},
           {"source": "平谷区看守所", "target": "处理措施",  "value": 19},
           {"source": "平谷区看守所", "target": "记录人",  "value": 19},
           {"source": "平谷区看守所", "target": "处理结果",  "value": 19},
           {"source": "平谷区看守所", "target": "单位名称",  "value": 19},
           {"source": "平谷区看守所", "target": "反馈民警",  "value": 19},
           {"source": "平谷区看守所", "target": "反馈时间",  "value": 19},

           {"source": "怀柔区看守所", "target": "考核类别",  "value": 20},
           {"source": "怀柔区看守所", "target": "考核性质",  "value": 20},
           {"source": "怀柔区看守所", "target": "违规/故障类型",  "value": 20},
           {"source": "怀柔区看守所", "target": "违规项",  "value": 20},
           {"source": "怀柔区看守所", "target": "考核时间",  "value":20},
           {"source": "怀柔区看守所", "target": "处理措施",  "value":20},
           {"source": "怀柔区看守所", "target": "记录人",  "value":20},
           {"source": "怀柔区看守所", "target": "处理结果",  "value": 20},
           {"source": "怀柔区看守所", "target": "单位名称",  "value": 20},
           {"source": "怀柔区看守所", "target": "反馈民警",  "value": 20},
           {"source": "怀柔区看守所", "target": "反馈时间",  "value":20},

           {"source": "密云区看守所", "target": "考核类别",  "value": 21},
           {"source": "密云区看守所", "target": "考核性质",  "value": 21},
           {"source": "密云区看守所", "target": "违规/故障类型",  "value": 21},
           {"source": "密云区看守所", "target": "违规项",  "value": 21},
           {"source": "密云区看守所", "target": "考核时间",  "value":21},
           {"source": "密云区看守所", "target": "处理措施",  "value":21},
           {"source": "密云区看守所", "target": "记录人",  "value":21},
           {"source": "密云区看守所", "target": "处理结果",  "value": 21},
           {"source": "密云区看守所", "target": "单位名称",  "value": 21},
           {"source": "密云区看守所", "target": "反馈民警",  "value": 21},
           {"source": "密云区看守所", "target": "反馈时间",  "value":21},

           {"source": "延庆区看守所", "target": "考核类别",  "value": 22},
           {"source": "延庆区看守所", "target": "考核性质",  "value": 22},
           {"source": "延庆区看守所", "target": "违规/故障类型",  "value": 22},
           {"source": "延庆区看守所", "target": "违规项",  "value": 22},
           {"source": "延庆区看守所", "target": "考核时间",  "value":22},
           {"source": "延庆区看守所", "target": "处理措施",  "value":22},
           {"source": "延庆区看守所", "target": "记录人",  "value":22},
           {"source": "延庆区看守所", "target": "处理结果",  "value": 22},
           {"source": "延庆区看守所", "target": "单位名称",  "value": 22},
           {"source": "延庆区看守所", "target": "反馈民警",  "value": 22},
           {"source": "延庆区看守所", "target": "反馈时间",  "value":22},

       ]


       var citylist=[];
//遍历city
       for(var key in city){
           citylist.push(
               {
                   name: key,
                   itemStyle: {
                       normal:  {
                           color:city[key]
                       }
                   }
               }   //构造节点对象，包括name和itemStyle
           )
       }
       console.log(citylist)
       var data=[];
       for(var i=0;i<population.length;i++){
           var color = new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                   offset: 0,
                   color: city[population[i].source]  //获取起始节点的颜色属性
               },{
                   offset: 1,
                   color: city[population[i].target]  //获取结尾节点的颜色属性
               }]
           )
           data.push({
                   source: population[i].source,
                   target: population[i].target,
                   value: population[i].value,
                   lineStyle: {   //添加样式配置
                       normal: {
                           color:color
                       }

                   }
               }
           )
       }

       var option = {
           backgroundColor:'#000',
           title: {
               text: _title,
               textStyle: {
                   fontWeight: 'normal',
                   fontSize: 20,
                   color: '#F1F1F3'
               },
               top: "3%",
               x: 'center'
           },
           tooltip: {
               trigger: 'item',
               triggerOn: 'mousemove'
           },
           series: [
               {
                   type: 'sankey',
                   data: citylist,
                   links: data,
                   top:'10%',
                   right:'10%',
                   bottom:'5%',
                   focusNodeAdjacency: 'allEdges',
                   itemStyle: {
                       normal: {
                           borderWidth: 0,
                           borderColor: '#fff',
                           opacity:1
                       }
                   },
                   label:{
                       normal: {
                           fontSize:'14',
                           color: '#fff'
                       }
                   },
                   lineStyle: {
                       normal: {
                           curveness: 0.5,
                           opacity:0.5
                       }
                   }
               }
           ]
       };
       var myChar = echarts.init(document.getElementById(_id));
       myChar.setOption(option);
   }



       return{
           zdryfxChar:_zdryfxBar,
           ylqkfxChar:_ylqkfxLine,
           reflfxChar:_ryflfxLine,
           wgqkfxChar:_wgqkfxLineAndBar,
           ajqkfxChar:_ajqkfxBar,
           lsfxfxChar:_lsfxfxBarAndLine,
           xskhChar:_xskhSankey
       }
    });

