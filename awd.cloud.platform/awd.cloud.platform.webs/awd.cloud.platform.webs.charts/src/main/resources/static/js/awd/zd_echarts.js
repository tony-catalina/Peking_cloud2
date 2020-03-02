define("awd/zd_echarts", [
    "../plugins/e-charts/echarts"
],
function(require) {
    //在押人员关押量分析
    var _zyrygylfxBar=function (id,url,title) {
        var _id = id;
        var _title =title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _legend = ['男', '女', '未知性别','关押总数'];
        var _man =[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _woman = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var result=[];
        for(var i=0;i<_man.length;i++) 
        {
            result[i]=_man[i]+_woman[i]+_unknown[i];
        }
        var option = {
            backgroundColor: "#344b58",
            title: {
                text:_title,
                subtext: '',
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
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            grid: {
                borderWidth: 0,
                top: 110,
                bottom: 95,
                textStyle: {
                    color: "#fff"
                }
            },
            legend: {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                    fontSize:20
                },
                data:_legend
            },
            calculable: true,
            xAxis: [{
                type: "category",
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                        fontSize:15
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    fontSize:20

                },
                data: _dqm,
            }],
            yAxis: [{
                type: "value", 
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,

                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:20
                },
            }],
            dataZoom: [{
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'2%',
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
                name: '男',
                type: "bar",
                stack: "总量",
                barMaxWidth: 35,
                barGap: "10%",
                itemStyle: {
                    normal: {
                        color: "rgba(255,185,15,1)",
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff"
                            },
                            position: "insideTop",
                            formatter: function(p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                data:_man,
            },
                {
                    name: '女',
                    type: "bar",
                    stack: "总量",
                    barMaxWidth: 35,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color: "rgba(255,144,128,1)",
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff"
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data: _woman
                },
                {
                    name: '未知性别',
                    type: "bar",
                    stack: "总量",
                    barMaxWidth: 35,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color: "rgba(0,191,183,1)",
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff",
                                    fontSize:10
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data: _unknown
                }, {
                    name: '关押总数',
                    type: "line",
                    smooth:true,
                    symbolSize: 10,
                    symbol: 'circle',
                    itemStyle: {
                        normal: {
                            color: "rgba(252,230,48,1)",
                            barBorderRadius: 0,
                            label: {
                                show: true, 
                                textStyle:{
                                    fontSize:20
                                },
                                position: "top",
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
        $.ajax({
            url:'/kssdp/zyrygyl',
            type:"get",
            dataType:"json",
            success:function (res) {
                // console.log(res,778899)
                if(res.status==200){
                    var _man = res.result.man;
                    var _woman = res.result.woman;
                    var _unknow = res.result.unknow;
                    var result = [];
                    for(var i=0;i<_man.length;i++)
                    {
                        result[i]=_man[i]+_woman[i]+_unknow[i];
                    }
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name:'男',
                            data:_man 
                        },{
                            name: '女',
                            data:_woman
                        },{
                            name: '未知性别',
                            data:_unknow
                        },{
                            name: '关押总数',
                            data:result
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //拘留所在押人员关押量分析
    var _jlsZyrygylfxBar=function (id,url,title) {
        var _id = id;
        var _title =title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _legend = ['拘留所男', '拘留所女', '未知性别','关押总数'];
        var _man =[10,0,0,0,0,30,0,0,0,0,0,0,0,0,0,0,0];
        var _woman = [10,0,0,0,30,30,0,0,0,0,0,0,0,0,0,0,0];
        var _unknown = [10,0,0,30,0,30,0,0,0,0,0,0,0,0,0,0,0];
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
                    interval: 0,
                    fontSize:20

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
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'2%',
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
            "series": [{
                "name": '拘留所男',
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
                    "name": '拘留所女',
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
                    "name": '未知性别',
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
                    "name": '关押总数',
                    "type": "line",
                    smooth:true,
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
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssdp/zyrygyl',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         // console.log(res,778899)
        //         if(res.status==200){
        //             var _man = res.result.man;
        //             var _woman = res.result.woman;
        //             var _unknow = res.result.unknow;
        //             var result = [];
        //             for(var i=0;i<_man.length;i++)
        //             {
        //                 result[i]=_man[i]+_woman[i]+_unknow[i];
        //             }
        //             var option={
        //                 xAxis: [{}],
        //                 yAxis:[{}],
        //                 series:[{
        //                     name:'男',
        //                     data:_man 
        //                 },{
        //                     name: '女',
        //                     data:_woman
        //                 },{
        //                     name: '未知性别',
        //                     data:_unknow
        //                 },{
        //                     name: '关押总数',
        //                     data:result
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         }
        //     },error:function (error) {
        //         console.log(error)
        //     }
        // });
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
                    fontSize:22,
                    interval: 0, 
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
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'0.5%',
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
        $.ajax({
            url:'/kssdp/hjdfx',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,778899)
                if(res.status==200){
                    var _bs = res.result.bs;
                    var _ws = res.result.ws;
                    var _wg = res.result.wg;
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name:'本省',
                            data:_bs 
                        },{
                            name: '外省',
                            data:_ws
                        },{
                            name: '外国',
                            data:_wg
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //拘留所户籍地分析
    var _jlsHjdChar=function(id,url,title){
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
                    fontSize:22,
                    interval: 0, 
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
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'0.5%',
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
        //     url:'/kssdp/hjdfx',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         console.log(res,778899)
        //         if(res.status==200){
        //             var _bs = res.result.bs;
        //             var _ws = res.result.ws;
        //             var _wg = res.result.wg;
        //             var option={
        //                 xAxis: [{}],
        //                 yAxis:[{}],
        //                 series:[{
        //                     name:'本省',
        //                     data:_bs 
        //                 },{
        //                     name: '外省',
        //                     data:_ws
        //                 },{
        //                     name: '外国',
        //                     data:_wg
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         }
        //     },error:function (error) {
        //         console.log(error)
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
                    // stack: '总量',
                    areaStyle: {},
                    data:[0,10,0,0]
                },
                {
                    name:'东城区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  {
                    name:'西城区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'朝阳区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,10,0,0]
                },
                {
                    name:'丰台区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,40,0]
                },
                {
                    name:'石景山区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },
                {
                    name:'海淀区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {},
                    data:[0,0,20,0]
                },
                {
                    name:'门头沟区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'房山区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  
                {
                    name:'通州区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'顺义区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },
                {
                    name:'昌平区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,15]
                },
                {
                    name:'大兴区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },                    
                {
                    name:'平谷区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'怀柔区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,30]
                },
                {
                    name:'密云区',
                    type:'line',
                    // stack: '总量',
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
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
            ],
            textStyle:{
                fontSize:16
            }
        };
        myChar.setOption(option);
            $.ajax({
                url: '/kssdp/nlfx',
                type: "get",
                dataType: "json",
                success: function (res) {
                    console.log(res.result,111222)
                    if(res.status==200){
                        var nlfx = res.result;
                        var option={
                            xAxis: [{}],
                            yAxis:[{}],
                            series:[{
                                name:'北京市',
                                data:nlfx[0].data
                            },
                            {
                                name:'东城区',
                                data:nlfx[1].data
                            },  {
                                name:'西城区',
                                data:nlfx[2].data
                            },
                            {
                                name:'朝阳区',
                                data:nlfx[3].data
                            },
                            {
                                name:'丰台区',
                                data:nlfx[4].data
                            },
                            {
                                name:'石景山区',
                                data:nlfx[5].data
                            },
                            {
                                name:'海淀区',
                                data:nlfx[6].data
                            },
                            {
                                name:'门头沟区',
                                data:nlfx[7].data
                            },
                            {
                                name:'房山区',
                                data:nlfx[8].data
                            },  
                            {
                                name:'通州区',
                                data:nlfx[9].data
                            },
                            {
                                name:'顺义区',
                                data:nlfx[10].data
                            },
                            {
                                name:'昌平区',
                                data:nlfx[11].data
                            },
                            {
                                name:'大兴区',
                                data:nlfx[12].data
                            },                    
                            {
                                name:'平谷区',
                                data:nlfx[13].data
                            },
                            {
                                name:'怀柔区',
                                data:nlfx[14].data
                            },
                            {
                                name:'密云区',
                                type:'line',
                                data:nlfx[15].data
                            },
                            {
                                name:'延庆区',
                                data:nlfx[16].data
                            }]
                        }
                        myChar.setOption(option);
                    }else{

                    }
                },
                error:function(error){
                    console.log(error)
                }
            })
        }
    //年龄分析
    var _jlsNlfxLine = function (id,url,title) {
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
                    // stack: '总量',
                    areaStyle: {},
                    data:[0,10,0,0]
                },
                {
                    name:'东城区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  {
                    name:'西城区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'朝阳区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,10,0,0]
                },
                {
                    name:'丰台区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,40,0]
                },
                {
                    name:'石景山区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },
                {
                    name:'海淀区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {},
                    data:[0,0,20,0]
                },
                {
                    name:'门头沟区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'房山区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },  
                {
                    name:'通州区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'顺义区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,20]
                },
                {
                    name:'昌平区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,15]
                },
                {
                    name:'大兴区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,20,0]
                },                    
                {
                    name:'平谷区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
                {
                    name:'怀柔区',
                    type:'line',
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,0,30]
                },
                {
                    name:'密云区',
                    type:'line',
                    // stack: '总量',
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
                    // stack: '总量',
                    areaStyle: {normal: {}},
                    data:[0,0,30,0]
                },
            ],
            textStyle:{
                fontSize:16
            }
        };
        myChar.setOption(option);
        // $.ajax({
        //     url: '/kssdp/nlfx',
        //     type: "get",
        //     dataType: "json",
        //     success: function (res) {
        //         console.log(res.result,111222)
        //         if(res.status==200){
        //             var nlfx = res.result;
        //             var option={
        //                 xAxis: [{}],
        //                 yAxis:[{}],
        //                 series:[{
        //                     name:'北京市',
        //                     data:nlfx[0].data
        //                 },
        //                 {
        //                     name:'东城区',
        //                     data:nlfx[1].data
        //                 },  {
        //                     name:'西城区',
        //                     data:nlfx[2].data
        //                 },
        //                 {
        //                     name:'朝阳区',
        //                     data:nlfx[3].data
        //                 },
        //                 {
        //                     name:'丰台区',
        //                     data:nlfx[4].data
        //                 },
        //                 {
        //                     name:'石景山区',
        //                     data:nlfx[5].data
        //                 },
        //                 {
        //                     name:'海淀区',
        //                     data:nlfx[6].data
        //                 },
        //                 {
        //                     name:'门头沟区',
        //                     data:nlfx[7].data
        //                 },
        //                 {
        //                     name:'房山区',
        //                     data:nlfx[8].data
        //                 },  
        //                 {
        //                     name:'通州区',
        //                     data:nlfx[9].data
        //                 },
        //                 {
        //                     name:'顺义区',
        //                     data:nlfx[10].data
        //                 },
        //                 {
        //                     name:'昌平区',
        //                     data:nlfx[11].data
        //                 },
        //                 {
        //                     name:'大兴区',
        //                     data:nlfx[12].data
        //                 },                    
        //                 {
        //                     name:'平谷区',
        //                     data:nlfx[13].data
        //                 },
        //                 {
        //                     name:'怀柔区',
        //                     data:nlfx[14].data
        //                 },
        //                 {
        //                     name:'密云区',
        //                     type:'line',
        //                     data:nlfx[15].data
        //                 },
        //                 {
        //                     name:'延庆区',
        //                     data:nlfx[16].data
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         }
        //     },
        //     error:function(error){
        //         console.log(error)
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
        var _wm = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _xx = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _zx = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40];
        var _dzys=[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
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
                    fontSize:22,
                    interval: 0, 
                }
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
                data: _wm,
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
                    data: _xx,
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
                    data: _zx
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
                    data: _dzys,
                }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/whcd',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    var _wm = res.result.wm;
                    var _xx = res.result.xx;
                    var _zx = res.result.zx;
                    var _dzys = res.result.dzys;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: '文盲',
                            data:_wm
                        },{
                            name: '小学',
                            data:_xx
                        },{
                            name: '中学',
                            data:_zx
                        },{
                            name: '大专以上',
                            data:_dzys
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function () {
            }
        });
    }
    //拘留所文化程度分析 
    var _jlsWhcdChar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['文盲', '小学', '中学','大专以上'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _wm = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _xx = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _zx = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40];
        var _dzys=[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
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
                    fontSize:22,
                    interval: 0, 
                }
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
                data: _wm,
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
                    data: _xx,
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
                    data: _zx
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
                    data: _dzys,
                }]
        };
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssdp/whcd',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         if(res.status==200){
        //             var _wm = res.result.wm;
        //             var _xx = res.result.xx;
        //             var _zx = res.result.zx;
        //             var _dzys = res.result.dzys;
        //             var option={
        //                 xAxis:{},
        //                 yAxis:{},
        //                 series:[{
        //                     name: '文盲',
        //                     data:_wm
        //                 },{
        //                     name: '小学',
        //                     data:_xx
        //                 },{
        //                     name: '中学',
        //                     data:_zx
        //                 },{
        //                     name: '大专以上',
        //                     data:_dzys
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         }
        //     },error:function () {
        //     }
        // });
    }
    //身份分析
    var _sffxChar = function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _sf =  ['国家公务员', '企业管理人员', '工人','农民','在校学生','个体工商人员','离退休人员','无业人员','军人','其他'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _gjgwy = [10,11,12,13,14,15,16,17,18,19,11,12,13,14,15,16,17];
        var _qyglry = [20,21,22,23,24,25,26,27,28,29,21,22,23,24,25,26,27];
        var _gr = [30,31,32,33,34,35,36,37,38,39,31,32,33,34,35,36,37];
        var _nm = [40,41,42,43,44,45,46,47,48,49,41,42,43,44,45,46,47];
        var _zxxs = [50,51,52,53,54,55,56,57,58,59,51,52,53,54,55,56,57];
        var _gtgsry = [60,61,62,63,64,65,66,67,68,69,61,62,63,64,65,66,67];
        var _ltxry = [70,71,72,73,74,75,76,77,78,79,71,72,73,74,75,76,77];
        var _wyry = [80,81,82,83,84,85,86,87,88,89,81,82,83,84,85,86,87];
        var _jr = [90,91,92,93,94,95,96,97,98,99,91,92,93,94,95,96,97];
        var _qt = [100,101,102,103,104,105,106,107,108,109,101,102,103,104,105,106,107];
        var option = {
            title: {
                text: '身份分析图'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: _sf
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    fontSize:20,
                    color:'#293c55',
                    interval:0,//代表显示所有x轴标签
                    // rotate:45, //代表逆时针旋转45度
                },
                data: _dqm
            },
            yAxis: {
                type: 'value'
            },
            dataZoom: [{
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'2%',
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
            series: [
                {
                    name: '国家公务员',
                    type: 'line',
                    data: _gjgwy
                },
                {
                    name: '企业管理人员',
                    type: 'line',
                    data: _qyglry
                },
                {
                    name: '工人',
                    type: 'line',
                    data: _gr
                },
                {
                    name: '农民',
                    type: 'line',
                    data: _nm
                },
                {
                    name: '在校学生',
                    type: 'line',
                    data: _zxxs
                },
                {
                    name: '个体工商人员',
                    type: 'line',
                    data: _gtgsry
                },
                {
                    name: '离退休人员',
                    type: 'line',
                    data: _ltxry
                },
                {
                    name: '无业人员',
                    type: 'line',
                    data: _wyry
                },
                {
                    name: '军人',
                    type: 'line',
                    data: _jr
                },
                {
                    name: '其他',
                    type: 'line',
                    data: _qt
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/sffx',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,662211)
                if(res.status==200){
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name: '国家公务员',
                            type: 'line',
                            data: res.result.gjgwy
                        },{
                            name: '企业管理人员',
                            data:res.result.qyglry
                        },{
                            name: '工人',
                            data:res.result.gr
                        },{
                            name: '农民',
                            data:res.result.nm
                        },{
                            name: '在校学生',
                            type: 'line',
                            data: res.result.zxxs
                        },{
                            name: '个体工商人员',
                            data:res.result.gtgsry
                        },{
                            name: '离退休人员',
                            data:res.result.ltxry
                        },{
                            name: '无业人员',
                            data:res.result.wyry
                        },{
                            name: '军人',
                            data:res.result.jr
                        },{
                            name: '其他',
                            data:res.result.qt
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //拘留所身份分析
    var _jlsSffxChar = function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _sf =  ['国家公务员', '企业管理人员', '工人','农民','在校学生','个体工商人员','离退休人员','无业人员','军人','其他'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _gjgwy = [10,11,12,13,14,15,16,17,18,19,11,12,13,14,15,16,17];
        var _qyglry = [20,21,22,23,24,25,26,27,28,29,21,22,23,24,25,26,27];
        var _gr = [30,31,32,33,34,35,36,37,38,39,31,32,33,34,35,36,37];
        var _nm = [40,41,42,43,44,45,46,47,48,49,41,42,43,44,45,46,47];
        var _zxxs = [50,51,52,53,54,55,56,57,58,59,51,52,53,54,55,56,57];
        var _gtgsry = [60,61,62,63,64,65,66,67,68,69,61,62,63,64,65,66,67];
        var _ltxry = [70,71,72,73,74,75,76,77,78,79,71,72,73,74,75,76,77];
        var _wyry = [80,81,82,83,84,85,86,87,88,89,81,82,83,84,85,86,87];
        var _jr = [90,91,92,93,94,95,96,97,98,99,91,92,93,94,95,96,97];
        var _qt = [100,101,102,103,104,105,106,107,108,109,101,102,103,104,105,106,107];
        var option = {
            title: {
                text: '身份分析图'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: _sf
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    fontSize:20,
                    color:'#293c55',
                    interval:0,//代表显示所有x轴标签
                    // rotate:45, //代表逆时针旋转45度
                },
                data: _dqm
            },
            yAxis: {
                type: 'value'
            },
            dataZoom: [{
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'2%',
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
            series: [
                {
                    name: '国家公务员',
                    type: 'line',
                    data: _gjgwy
                },
                {
                    name: '企业管理人员',
                    type: 'line',
                    data: _qyglry
                },
                {
                    name: '工人',
                    type: 'line',
                    data: _gr
                },
                {
                    name: '农民',
                    type: 'line',
                    data: _nm
                },
                {
                    name: '在校学生',
                    type: 'line',
                    data: _zxxs
                },
                {
                    name: '个体工商人员',
                    type: 'line',
                    data: _gtgsry
                },
                {
                    name: '离退休人员',
                    type: 'line',
                    data: _ltxry
                },
                {
                    name: '无业人员',
                    type: 'line',
                    data: _wyry
                },
                {
                    name: '军人',
                    type: 'line',
                    data: _jr
                },
                {
                    name: '其他',
                    type: 'line',
                    data: _qt
                }
            ]
        };
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssdp/sffx',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         console.log(res,662211)
        //         if(res.status==200){
        //             var option={
        //                 xAxis: [{}],
        //                 yAxis:[{}],
        //                 series:[{
        //                     name: '国家公务员',
        //                     type: 'line',
        //                     data: res.result.gjgwy
        //                 },{
        //                     name: '企业管理人员',
        //                     data:res.result.qyglry
        //                 },{
        //                     name: '工人',
        //                     data:res.result.gr
        //                 },{
        //                     name: '农民',
        //                     data:res.result.nm
        //                 },{
        //                     name: '在校学生',
        //                     type: 'line',
        //                     data: res.result.zxxs
        //                 },{
        //                     name: '个体工商人员',
        //                     data:res.result.gtgsry
        //                 },{
        //                     name: '离退休人员',
        //                     data:res.result.ltxry
        //                 },{
        //                     name: '无业人员',
        //                     data:res.result.wyry
        //                 },{
        //                     name: '军人',
        //                     data:res.result.jr
        //                 },{
        //                     name: '其他',
        //                     data:res.result.qt
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         }
        //     },error:function (error) {
        //         console.log(error)
        //     }
        // });
    }
    //健康状况分析
    var _jkzkChar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _jkzk =  ['健康或良好', '一般或较弱', '有病','有生理缺陷','残废','总数'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _jkhlh = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _ybhjr = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _yb = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40];
        var _slqx=[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _cf = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var result = [];
        for(var i=0;i<_jkhlh.length;i++){
            result[i]=_jkhlh[i]+_ybhjr[i]+_yb[i]+_slqx[i]+_cf[i];
        }
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            legend: {
                data:  _jkzk,
                top:30,
                textStyle:{
                    fontSize:16,
                    color:'#293c55'
                }
            },
            xAxis: [
                {
                    type: 'category',
                    name:"/监所",
                    nameTextStyle:{
                        color:"#293c55", 
                        fontSize:13,  
                        padding:[0,0,0,-10]
                    },
                    data: _dqm,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLabel: {
                        fontSize:20,
                        color:'#293c55',
                        interval:0,//代表显示所有x轴标签
                        // rotate:45, //代表逆时针旋转45度
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '/人数',
                    nameTextStyle:{
                        color:"#293c55", 
                        fontSize:13,  
                    },
                    axisLabel: {
                        formatter: '{value}',
                        fontSize:16,
                        color:'#293c55'
                    }
                }
            ],
            dataZoom: [{
                show: true,
                height: 8,
                fillerColor:"rgb(249, 207, 192)",
                xAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"rgb(247, 162, 132)",

                },
                textStyle:{
                    color:"#fff"
                },
                borderColor:"#fff"
            }, 
            {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '健康或良好',
                    type: 'bar',
                    data: _jkhlh,
                    itemStyle:{
                        normal:{
                            color:'#c23531'
                        }
                    },
                    
                },
                {
                    name: '一般或较弱',
                    type: 'bar',
                    data: _ybhjr,
                    itemStyle:{
                        normal:{
                            color:'#2f4554'
                        }
                    },
                },
                {
                    name: '有病',
                    type: 'bar',
                    data: _yb,
                    itemStyle:{
                        normal:{
                            color:'#61a0a8'
                        }
                    },
                },
                {
                    name: '有生理缺陷',
                    type: 'bar',
                    data: _slqx,
                    itemStyle:{
                        normal:{
                            color:'#d48265'
                        }
                    },
                },
                {
                    name: '残废',
                    type: 'bar',
                    data: _cf,
                    itemStyle:{
                        normal:{
                            color:'#91c7ae'
                        }
                    },
                },
                {
                    name: '总数',
                    type: 'line',
                    data: result
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/jkqkfx',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,225544)
                var _jkhlh = res.result.jkhlh;
                var _ybhjr = res.result.ybhjr;
                var _yb = res.result.yb;
                var _slqx = res.result.slqx;
                var _cf = res.result.cf;
                var result = [];
                for(var i=0;i<_jkhlh.length;i++){
                    result[i]=_jkhlh[i]+_ybhjr[i]+_yb[i]+_slqx[i]+_cf[i];
                }
                var option = {
                    xAxis: [{}],
                    yAxis:[{}],
                    series: [{
                        name: '健康或良好',
                        data: _jkhlh,
                    },
                    {
                        name: '一般或较弱',
                        data: _ybhjr,
                    },
                    {
                        name: '有病',
                        data: _yb,
                    },
                    {
                        name: '有生理缺陷',
                        data: _slqx,
                    },
                    {
                        name: '残废',
                        data: _cf,
                    },
                    {
                    name: '总数',
                        data: result
                    }]
                }
                myChar.setOption(option)
            },error:function(error){
                console.log(error)
            }
        })
    }
    //健康状况分析
    var _jlsJkzkChar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _jkzk =  ['健康或良好', '一般或较弱', '有病','有生理缺陷','残废','总数'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _jkhlh = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _ybhjr = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _yb = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40];
        var _slqx=[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _cf = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var result = [];
        for(var i=0;i<_jkhlh.length;i++){
            result[i]=_jkhlh[i]+_ybhjr[i]+_yb[i]+_slqx[i]+_cf[i];
        }
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            legend: {
                data:  _jkzk,
                top:30,
                textStyle:{
                    fontSize:16,
                    color:'#293c55'
                }
            },
            xAxis: [
                {
                    type: 'category',
                    name:"/监所",
                    nameTextStyle:{
                        color:"#293c55", 
                        fontSize:13,  
                        padding:[0,0,0,-10]
                    },
                    data: _dqm,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLabel: {
                        fontSize:20,
                        color:'#293c55',
                        interval:0,//代表显示所有x轴标签
                        // rotate:45, //代表逆时针旋转45度
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '/人数',
                    nameTextStyle:{
                        color:"#293c55", 
                        fontSize:13,  
                    },
                    axisLabel: {
                        formatter: '{value}',
                        fontSize:16,
                        color:'#293c55'
                    }
                }
            ],
            dataZoom: [{
                show: true,
                height: 8,
                fillerColor:"rgb(249, 207, 192)",
                xAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"rgb(247, 162, 132)",

                },
                textStyle:{
                    color:"#fff"
                },
                borderColor:"#fff"
            }, 
            {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '健康或良好',
                    type: 'bar',
                    data: _jkhlh,
                    itemStyle:{
                        normal:{
                            color:'#c23531'
                        }
                    },
                    
                },
                {
                    name: '一般或较弱',
                    type: 'bar',
                    data: _ybhjr,
                    itemStyle:{
                        normal:{
                            color:'#2f4554'
                        }
                    },
                },
                {
                    name: '有病',
                    type: 'bar',
                    data: _yb,
                    itemStyle:{
                        normal:{
                            color:'#61a0a8'
                        }
                    },
                },
                {
                    name: '有生理缺陷',
                    type: 'bar',
                    data: _slqx,
                    itemStyle:{
                        normal:{
                            color:'#d48265'
                        }
                    },
                },
                {
                    name: '残废',
                    type: 'bar',
                    data: _cf,
                    itemStyle:{
                        normal:{
                            color:'#91c7ae'
                        }
                    },
                },
                {
                    name: '总数',
                    type: 'line',
                    data: result
                }
            ]
        };
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssdp/jkqkfx',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         console.log(res,225544)
        //         var _jkhlh = res.result.jkhlh;
        //         var _ybhjr = res.result.ybhjr;
        //         var _yb = res.result.yb;
        //         var _slqx = res.result.slqx;
        //         var _cf = res.result.cf;
        //         var result = [];
        //         for(var i=0;i<_jkhlh.length;i++){
        //             result[i]=_jkhlh[i]+_ybhjr[i]+_yb[i]+_slqx[i]+_cf[i];
        //         }
        //         var option = {
        //             xAxis: [{}],
        //             yAxis:[{}],
        //             series: [{
        //                 name: '健康或良好',
        //                 data: _jkhlh,
        //             },
        //             {
        //                 name: '一般或较弱',
        //                 data: _ybhjr,
        //             },
        //             {
        //                 name: '有病',
        //                 data: _yb,
        //             },
        //             {
        //                 name: '有生理缺陷',
        //                 data: _slqx,
        //             },
        //             {
        //                 name: '残废',
        //                 data: _cf,
        //             },
        //             {
        //             name: '总数',
        //                 data: result
        //             }]
        //         }
        //         myChar.setOption(option)
        //     },error:function(error){
        //         console.log(error)
        //     }
        // })
    }
    //国籍分析
    var _gjfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _yl =  ['中国', '外籍', '香港','澳门','台湾'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _zg = [96,96,97,95,98,94,89,94,80,52,55,87,98,45,78,15,48];
        var _wj = [97,99,99,100,99,90,80,91,69,67,97,95,98,94,89,94,80];
        var _xg = [23,45,56,23,45,65,44,56,90,34,52,55,87,98,45,78,15];
        var _am = [88,45,67,78,98,12,34,56,87,23,65,44,56,90,34,52,55];
        var _tw = [98,12,34,54,77,88,99,34,65,78,65,44,56,90,34,52,55]; 
        var option = {
            backgroundColor: '#394056',
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
                    fontSize:22,
                    interval:0
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
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'1%',
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
                name: '中国',
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
                data: _zg
            }, {
                name: '外籍',
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
                data: _wj
            }, {
                name: '香港',
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
                data: _xg
            },{
                name: '澳门',
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
                            color: 'rgba(104, 200, 212, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(104, 200, 212, 0)'
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
                data: _am
            },{
                name: '台湾',
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
                                color: 'rgba(219, 90, 51, 0.3)'
                            }, {
                                offset: 0.8,
                                color: 'rgba(219, 90, 51, 0)'
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
                data: _tw
            } ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssqsfx/gjfxCount',
            type:"get",
            dataType:"json",
            success:function (res) {
                var _zg =res.result.zg;
                var _wj =res.result.wj;
                var _xg =res.result.xg;
                var _am =res.result.am;
                var _tw =res.result.tw;
                if(res.status==200){
                    var option={
                        xAxis:[{}],
                        yAxis:[{}],
                        series:[{
                            name: '中国',
                            data:_zg
                        },{
                            name: '外籍',
                            data:_wj
                        },{
                            name: '香港',
                            data:_xg
                        },{
                            name: '澳门',
                            data:_am
                        },{
                            name: '台湾',
                            data:_tw
                        }]
                    }
                    myChar.setOption(option);
                }else{
                    
                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //国籍分析
    var _jlsGjfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _yl =  ['中国', '外籍', '香港','澳门','台湾'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _zg = [96,96,97,95,98,94,89,94,80,52,55,87,98,45,78,15,48];
        var _wj = [97,99,99,100,99,90,80,91,69,67,97,95,98,94,89,94,80];
        var _xg = [23,45,56,23,45,65,44,56,90,34,52,55,87,98,45,78,15];
        var _am = [88,45,67,78,98,12,34,56,87,23,65,44,56,90,34,52,55];
        var _tw = [98,12,34,54,77,88,99,34,65,78,65,44,56,90,34,52,55]; 
        var option = {
            backgroundColor: '#394056',
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
                    fontSize:22,
                    interval:0
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
                show: true,
                fillerColor:"#f5844a",
                height: 12,
                xAxisIndex: [0],
                bottom:'1%',
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
                name: '中国',
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
                data: _zg
            }, {
                name: '外籍',
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
                data: _wj
            }, {
                name: '香港',
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
                data: _xg
            },{
                name: '澳门',
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
                            color: 'rgba(104, 200, 212, 0.3)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(104, 200, 212, 0)'
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
                data: _am
            },{
                name: '台湾',
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
                                color: 'rgba(219, 90, 51, 0.3)'
                            }, {
                                offset: 0.8,
                                color: 'rgba(219, 90, 51, 0)'
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
                data: _tw
            } ]
        };
        myChar.setOption(option);
        // $.ajax({
        //     url:'/kssqsfx/gjfxCount',
        //     type:"get",
        //     dataType:"json",
        //     success:function (res) {
        //         var _zg =res.result.zg;
        //         var _wj =res.result.wj;
        //         var _xg =res.result.xg;
        //         var _am =res.result.am;
        //         var _tw =res.result.tw;
        //         if(res.status==200){
        //             var option={
        //                 xAxis:[{}],
        //                 yAxis:[{}],
        //                 series:[{
        //                     name: '中国',
        //                     data:_zg
        //                 },{
        //                     name: '外籍',
        //                     data:_wj
        //                 },{
        //                     name: '香港',
        //                     data:_xg
        //                 },{
        //                     name: '澳门',
        //                     data:_am
        //                 },{
        //                     name: '台湾',
        //                     data:_tw
        //                 }]
        //             }
        //             myChar.setOption(option);
        //         }else{
                    
        //         }
        //     },error:function (error) {
        //         console.log(error)
        //     }
        // });
    }
    //严重疾病人员分析
    var _yzjbryfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['艾滋病人数', '重点病号人数','精神异常人数'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _azb = [10,0,30,0,0,50,0,0,70,0,0,90,0,0,0,0,0];
        var _zdbh = [20,0,40,0,0,60,0,0,80,0,0,100,0,0,0,0,0];
        var _jsyc=[30,0,0,60,0,0,90,0,0,120,0,0,150,0,0,0,0]
        var myChar = echarts.init(document.getElementById(_id));
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                color: ["#F58080", "#47D8BE"],
                data: _yl,
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
                
                axisLine: {
                    lineStyle: {
                        color: "#999",
                    },
                    
                },
                axisLabel:{
                    fontSize:22,
                    interval:0
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
                        color: "#333",
                    },
                },
                nameTextStyle: {
                    color: "#999",
                    fontSize:20
                },
                textStyle: {
                    fontSize:20
                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:'18'
                }
            },
            dataZoom: [{
                show: true,
                fillerColor:"#f9a589",
                height: 12,
                xAxisIndex: [0],
                bottom:'5%',
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
                name: '艾滋病人数',
                type: 'line',
                data: _azb,
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
                    name: '重点病号人数',
                    type: 'line',
                    data: _zdbh,
                    lineStyle: {
                        normal: {
                            width: 5,
                            fontSize:20,
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
                    name: '精神异常人数',
                    type: 'line',
                    data: _jsyc,
                    color: "#F6D06F",
                    lineStyle: {
                        normal: {
                            width: 5,
                            color: {
                                type: 'linear',

                                colorStops: [{
                                    offset: 0,
                                    color: '#F6D06F' // 0% 处的颜色
                                }, {
                                    offset: 0.4,
                                    color: '#F9A589' // 100% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#F9A589' // 100% 处的颜色
                                }],
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
                            /*shadowColor: 'rgba(72,216,191, 0.3)',
                                shadowBlur: 100,*/
                            borderColor: "#F6D06F"
                        }
                    },
                    smooth: true
                },
            ]
        };
        myChar.setOption(option);
        /////
        $.ajax({
            url:'/kssdp/yzjbfx',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    _azb = data.result.azb;
                    _zdbh = data.result.zdbh;
                    _jsyc = data.result.jsyc;
                    var option={
                        xAxis: [{}],
                            yAxis:[{}],
                            series:[{
                                name: '艾滋病人数',
                                data: _azb,
                            },{
                                name: '重点病号人数',
                                data: _zdbh,
                            },
                            ,{
                                name: '精神异常人数',
                                data: _jsyc,
                            }]
                    }
                    myChar.setOption(option);
                }else{

                } 
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //严重疾病人员分析
    var _jlsYzjbryfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['艾滋病人数', '重点病号人数','精神异常人数'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _azb = [10,0,30,0,0,50,0,0,70,0,0,90,0,0,0,0,0];
        var _zdbh = [20,0,40,0,0,60,0,0,80,0,0,100,0,0,0,0,0];
        var _jsyc=[30,0,0,60,0,0,90,0,0,120,0,0,150,0,0,0,0]
        var myChar = echarts.init(document.getElementById(_id));
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                color: ["#F58080", "#47D8BE"],
                data: _yl,
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
                axisLine: {
                    lineStyle: {
                        color: "#999",
                    },
                    
                },
                axisLabel:{
                    fontSize:22,
                    interval:0
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
                        color: "#333",
                    },
                },
                nameTextStyle: {
                    color: "#999",
                    fontSize:20
                },
                textStyle: {
                    fontSize:20
                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:'18'
                }
            },
            dataZoom: [{
                show: true,
                fillerColor:"#f9a589",
                height: 12,
                xAxisIndex: [0],
                bottom:'5%',
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
                name: '艾滋病人数',
                type: 'line',
                data: _azb,
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
                    name: '重点病号人数',
                    type: 'line',
                    data: _zdbh,
                    lineStyle: {
                        normal: {
                            width: 5,
                            fontSize:20,
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
                    name: '精神异常人数',
                    type: 'line',
                    data: _jsyc,
                    color: "#F6D06F",
                    lineStyle: {
                        normal: {
                            width: 5,
                            color: {
                                type: 'linear',

                                colorStops: [{
                                    offset: 0,
                                    color: '#F6D06F' // 0% 处的颜色
                                }, {
                                    offset: 0.4,
                                    color: '#F9A589' // 100% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#F9A589' // 100% 处的颜色
                                }],
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
                            /*shadowColor: 'rgba(72,216,191, 0.3)',
                                shadowBlur: 100,*/
                            borderColor: "#F6D06F"
                        }
                    },
                    smooth: true
                },
            ]
        };
        myChar.setOption(option);
        /////
        // $.ajax({
        //     url:'/kssdp/yzjbfx',
        //     type:"get",
        //     dataType:"json",
        //     success:function (data) {
        //         if(data.status==200){
        //             _azb = data.result.azb;
        //             _zdbh = data.result.zdbh;
        //             _jsyc = data.result.jsyc;
        //             var option={
        //                 xAxis: [{}],
        //                     yAxis:[{}],
        //                     series:[{
        //                         name: '艾滋病人数',
        //                         data: _azb,
        //                     },{
        //                         name: '重点病号人数',
        //                         data: _zdbh,
        //                     },
        //                     ,{
        //                         name: '精神异常人数',
        //                         data: _jsyc,
        //                     }]
        //             }
        //             myChar.setOption(option);
        //         }else{

        //         } 
        //     },error:function (error) {
        //         console.log(error)
        //     }
        // });
    }
    //拒收人员分析
    var _jsryfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _dpm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _bf = [100, 100, 100, 100, 100,100,100,100,100,100,100,100,100,100,100,100,100];
        var _js = [10,0,30,0,0,50,0,0,70,0,0,90,0,0,0,0,0]
        var _bfb = [10,0,30,0,0,50,0,0,70,0,0,90,0,0,0,0,0]
        var  option = {
            title: {
                text: _title,
                x:'center',
                textStyle:{
                    fontSize:33,
                    color:'#fff'
                }
            },
            backgroundColor: '#091034',
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: _dpm,
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
                data:_js,
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
                data: _bfb,
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
                data: _bf,
                barWidth: 25,
                itemStyle: {
                    normal: {
                        color: 'none',
                        borderColor: '#43baf3',
                        borderWidth: 3,
                        barBorderRadius: 15,
                    }
                }
            }]
        };
        myChar.setOption(option);
        ////////////
        $.ajax({
            url: '/kssdp/jsryfx',
            type: "get",
            dataType: "json",
            success: function (res) {
                console.log(res,778899)
                var option={
                    xAxis: {},
                    yAxis:[{
                            data:_dqm
                        },{
                            data:res.result.js
                        }
                    ],
                    series:[{
                            name: '条',
                            data:res.result.bfb.map((itm)=>itm*100)
                        },{
                            name: '框',
                            data: _bf,
                        }

                    ]
                }
                myChar.setOption(option);
            }, error: function (error) {
                console.log(error);
            }
        });
        }
    //入所性质分析
    var _rsxzfxCircle=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 2154,
            name: '刑事拘留'
        }, {
            value: 3854,
            name: '逮捕'
        }, {
            value: 3515,
            name: '临时寄押'
        }, {
            value: 3515,
            name: '外省市转入'
        }, {
            value: 3854,
            name: '本省市转入'
        }, {
            value: 2154,
            name: '异地羁押'
        }, {
            value: 3854,
            name: '取保候审转逮捕'
        }, {
            value: 3854,
            name: '监视居住转逮捕'
        }, {
            value: 3854,
            name: '取消监外执行'
        },{
            value: 3854,
            name: '撤销保外就医'
        }, {
            value: 3515,
            name: '取消假释'
        }, {
            value: 3515,
            name: '撤销缓刑'
        }, {
            value: 3854,
            name: '逃跑收回'
        }, {
            value: 2154,
            name: '其他收回'
        }, {
            value: 3854,
            name: '投送未收'
        }, {
            value: 3854,
            name: '其它'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            // title: {
            //     text:'北京市',
            //     left:'center',
            //     top:'45%',
            //     padding:[24,0],
            //     textStyle:{
            //         color:'#fff',
            //         fontSize:18*scale,
            //         align:'center'
            //     }
            // },
            legend: {
                selectedMode:false,
                // formatter: function(name) {
                //     var total = 0;
                //     var averagePercent;
                //     echartData.forEach(function(value, index, array) {
                //         total += value.value;
                //     });
                //     return '{total|' + total + '}';
                // },
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 22 ,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/rsxz?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    // console.log(params.value,8888888)
                                    res.forEach(function(value, index, array) {
                                        // console.log(value.value,555555)
                                        total += value.value*1;
                                        // console.log(total,666666)
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    // console.log(params.value,8888888)
                                    // percent = ((params.value / total) * 100).toFixed(1);
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //案件类别分析
    var _ajlbfxCircle=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 2154,
            name: '刑事拘留'
        }, {
            value: 3854,
            name: '逮捕'
        }, {
            value: 3515,
            name: '临时寄押'
        }, {
            value: 3515,
            name: '外省市转入'
        }, {
            value: 3854,
            name: '本省市转入'
        }, {
            value: 2154,
            name: '异地羁押'
        }, {
            value: 3854,
            name: '取保候审转逮捕'
        }, {
            value: 3854,
            name: '监视居住转逮捕'
        }, {
            value: 3854,
            name: '取消监外执行'
        },{
            value: 3854,
            name: '撤销保外就医'
        }, {
            value: 3515,
            name: '取消假释'
        }, {
            value: 3515,
            name: '撤销缓刑'
        }, {
            value: 3854,
            name: '逃跑收回'
        }, {
            value: 2154,
            name: '其他收回'
        }, {
            value: 3854,
            name: '投送未收（5）'
        }, {
            value: 3854,
            name: '其它（2）'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            // title: {
            //     text:'案件类别分析',
            //     left:'center',
            //     top:'53%',
            //     padding:[24,0],
            //     textStyle:{
            //         color:'#fff',
            //         fontSize:18*scale,
            //         align:'center'
            //     }
            // },
            legend: {
                selectedMode:false,
                // formatter: function(name) {
                //     var total = 0;
                //     var averagePercent;
                //     echartData.forEach(function(value, index, array) {
                //         total += value.value;
                //     });
                //     return '{total|' + total + '}';
                // },
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 22,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/ajlb?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    res.forEach(function(value, index, array) {
                                        total += value.value*1;
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res,
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //全省办案阶段分析
    var _qsbajdfxCircle=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 2154,
            name: '刑事拘留'
        }, {
            value: 3854,
            name: '逮捕'
        }, {
            value: 3515,
            name: '临时寄押'
        }, {
            value: 3515,
            name: '外省市转入'
        }, {
            value: 3854,
            name: '本省市转入'
        }, {
            value: 2154,
            name: '异地羁押'
        }, {
            value: 3854,
            name: '取保候审转逮捕'
        }, {
            value: 3854,
            name: '监视居住转逮捕'
        }, {
            value: 3854,
            name: '取消监外执行'
        },{
            value: 3854,
            name: '撤销保外就医'
        }, {
            value: 3515,
            name: '取消假释'
        }, {
            value: 3515,
            name: '撤销缓刑'
        }, {
            value: 3854,
            name: '逃跑收回'
        }, {
            value: 2154,
            name: '其他收回'
        }, {
            value: 3854,
            name: '投送未收（5）'
        }, {
            value: 3854,
            name: '其它（2）'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            // title: {
            //     text:'全省办案阶段分析',
            //     left:'center',
            //     top:'53%',
            //     padding:[24,0],
            //     textStyle:{
            //         color:'#fff',
            //         fontSize:18*scale,
            //         align:'center'
            //     }
            // },
            legend: {
                selectedMode:false,
                // formatter: function(name) {
                //     var total = 0;
                //     var averagePercent;
                //     echartData.forEach(function(value, index, array) {
                //         total += value.value;
                //     });
                //     return '{total|' + total + '}';
                // },
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 22,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_qsbajd?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    res.forEach(function(value, index, array) {
                                        total += (value.value)*1;
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //送监未收人员分析
    var _sjwsryfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _wsrs = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _tsjyws = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _tsljsws = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _tssgsws = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _tsakyyws = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _qttsws = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _yl = ['未收人数','投送监狱未收','投送劳教所未收','投送少管所未收','投送安康医院未收','其他投送未收'];
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:_yl,
                textStyle:{
                    fontSize:'20'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: _dqm,
                axisLabel:{
                    fontSize:22,
                    interval:0
                }
            },
            yAxis: {
                type: 'value',
                axisLabel:{
                    fontSize:'18'
                }
            },
            dataZoom: [{
                show: true,
                fillerColor:'#749f83',
                height: 12,
                xAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ffffff",

                },
                textStyle:{
                    color:"#ffffff"
                },
                borderColor:"#ffffff"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [
                {
                    name:'未收人数',
                    type:'line',
                    stack: '总量',
                    data:_wsrs
                },
                {
                    name:'投送监狱未收',
                    type:'line',
                    stack: '总量',
                    data:_tsjyws
                },
                {
                    name:'投送劳教所未收',
                    type:'line',
                    stack: '总量',
                    data:_tsljsws
                },
                {
                    name:'投送少管所未收',
                    type:'line',
                    stack: '总量',
                    data:_tssgsws
                },
                {
                    name:'投送安康医院未收',
                    type:'line',
                    stack: '总量',
                    data:_tsakyyws
                },
                {
                    name:'其他投送未收',
                    type:'line',
                    stack: '总量',
                    data:_qttsws
                }
            ]
        };
        myChar.setOption(option);
        
        $.ajax({
            url:'/kssqsfx/tswsfxCount',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    _wsrs = data.result.wsrs;
                    _tsjyws = data.result.tsjyws;
                    _tsljsws = data.result.tsljsws;
                    _tssgsws = data.result.tssgsws;
                    _tsakyyws = data.result.tsakyyws;
                    _qttsws = data.result.qttsws;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series: [
                            {
                                name:'未收人数',
                                data:_wsrs   
                            },
                            {
                                name:'投送监狱未收',
                                data:_tsjyws
                            },
                            {
                                name:'投送劳教所未收',
                                data:_tsljsws
                            },
                            {
                                name:'投送少管所未收',
                                data:_tssgsws  
                            },
                            {
                                name:'投送安康医院未收',
                                data:_tsakyyws
                            },
                            {
                                name:'其他投送未收',
                                data:_qttsws
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
    }
    //年度入所量
    var _ndrslfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _rs = ['今年入所量','去年入所量'];
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _jn = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _qn = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var option = {
            title: {
                text: '年度入所量',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: _rs,
                textStyle:{
                    fontSize:'20'
                }
            },
            xAxis: {
                type: 'value',
                axisLabel:{
                    fontSize:22,
                    interval:0
                }
            },
            yAxis: {
                type: 'category',
                axisLabel:{
                    fontSize:20,
                },
                data: _dqm
            },
            dataZoom: [{
                show: true,
                fillerColor:'#749f83',
                height: 12,
                yAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ffffff",

                },
                textStyle:{
                    color:"#ffffff"
                },
                borderColor:"#ffffff"
            }, {
                type: "inside",
                show: true,
                height: 15,
                yAxisIndex: [0],
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '今年入所量',
                    type: 'bar',
                    data: _jn
                },
                {
                    name: '去年入所量',
                    type: 'bar',
                    data: _qn
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url: '/kssdp/select_kss_ndrs',
            type: "get",
            dataType: "json",
            success: function (res) {
                console.log(res,111222)
                var option = {
                        xAxis:{},
                        yAxis:{},
                        series: [
                            {
                                name:'今年入所量',
                                data:res.result.jnArray   
                            },
                            {
                                name:'去年入所量',
                                data:res.result.qnArray   
                            },
                            
                        ]
                }
            
                myChar.setOption(option);

            },error:function(error){
                console.log(error)
            }
        })
    }
    //关押期限分析
	var _gyqxfxLine = function (id,url,title) {
        var _title = title || undefined ? title :"未定义";
        var myChar = echarts.init(document.getElementById(id));
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _yf = ['3月以下','3月至6月','6月至1年','1年至3年','3年以上'];
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
                data: _dqm
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
                    data : _yf,
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
                console.log(error)
            }
        })
    }
    //超期羁押分析
    var _cqjyqkfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _yb = [100, 100, 100, 100, 100,100,100,100,100,100,100,100,100,100,100,100,100];
        var _xz = [10,10,10,20,20,10,10,10,20,20,10,10,10,20,20,30,50];//百分比
        var _yz = [30,30,30,40,50,10,10,10,20,20,10,10,10,20,20,30,50];
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
                data: _dqm,
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
                data:_yz,
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
                data: _xz,
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
                data: _yb,
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
                        }
                    ]
                }
                myChar.setOption(option);
            }, error: function (error) {
                console.log(error);
            }
        });
	}
    //重点人员分析
    var _zdryfxBar=function (id,url,title) {
        var _id = id;
        var _title =title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _legend = ['男', '女', '未说明','重点人员人数'];
        var _man = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _woman = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var result=[];
        for(var i=0;i<_man.length;i++)
        {
            result[i]=_man[i]+_woman[i]+_unknown[i];
        }
        var option = {
            backgroundColor: "#344b58",
            title: {
                text:_title,
                subtext: '',
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
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            grid: {
                borderWidth: 0,
                top: 110,
                bottom: 95,
                textStyle: {
                    color: "#fff"
                }
            },
            legend: {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                    fontSize:20
                },
                data:_legend
            },


            calculable: true,
            xAxis: [{
                type: "category",
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                        fontSize:15
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    fontSize:22
                },
                data: _dqm,
            }],
            yAxis: [{
                type: "value",
                
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,

                },
                splitArea: {
                    show: false
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
                bottom:'4%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#344b58",

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
            series: [{
                name: '男',
                type: "bar",
                stack: "总量",
                barMaxWidth: 35,
                barGap: "10%",
                itemStyle: {
                    normal: {
                        color: "rgba(255,185,15,1)",
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff"
                            },
                            position: "insideTop",
                            formatter: function(p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                data:_man
            },
                {
                    name: '女',
                    type: "bar",
                    stack: "总量",
                    barMaxWidth: 35,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color: "rgba(255,144,128,1)",
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff"
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:_woman
                },
                {
                    name: '未说明',
                    type: "bar",
                    stack: "总量",
                    barMaxWidth: 35,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color: "rgba(0,191,183,1)",
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff"
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:_unknown
                }, {
                    name: '重点人员人数',
                    type: "line",
                    smooth:true,
                    symbolSize: 10,
                    symbol: 'circle',
                    itemStyle: {
                        normal: {
                            color: "rgba(252,230,48,1)",
                            barBorderRadius: 0,
                            label: {
                                show: true, 
                                position: "start",
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
    //人员就医情况分析
    var  _ryjyqkfxLine= function (id, url, title) {
        var _id = id ;
        var _title = title || undefined ? title :"未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _legend =['所内就医', '所外就医', '就医总数'];
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _sn = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _sw = [5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var result = []
        for(var i=0;i<_sn.length;i++)
        {
            result[i]=_sn[i]+_sw[i];
        }
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
            dataZoom: [{
                fillerColor:"#f9a689",
                show: true,
                height: 12,
                xAxisIndex: [0],
                bottom:'6%',
                start: 10,
                end: 90,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ebd373",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"#ebd373"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [{
                name: '所内就医',
                type: 'line',
                data: _sn,
                color: "#F58080",
                lineStyle: {
                    normal: {
                        width: 5,
                        shadowColor: 'rgba(245,128,128, 0.5)',
                        shadowBlur: 10,
                        shadowOffsetY: 7
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#F58080',
                        borderWidth: 10,
                        borderColor: "#F58080"
                    }
                },
                smooth: true
            },
                {
                    name: '所外就医',
                    type: 'line',
                    data: _sw,
                    lineStyle: {
                        normal: {
                            width: 5,
                            shadowColor: 'rgba(71,216,190, 0.5)',
                            shadowBlur: 10,
                            shadowOffsetY: 7
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#AAF487',
                            borderWidth: 10,
                            borderColor: "#AAF487"
                        }
                    },
                    smooth: true
                },
                {
                    name: '就医总数',
                    type: 'line',
                    data: result,
                    lineStyle: {
                        normal: {
                            width: 5,
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
                myChar.setOption(option);
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //涉黑涉恶人员统计分析
    var _shserytjBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _he =  ['男', '女', '未知性别','统计总数'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var result = [];
        var _man = [];
        var _woman = [];
        var _unknow = [];
        for(var i = 0;i<_man.length;i++){
            result[i] = _man[i] + _woman[i] + _unknow[i]
        }
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
                data: _he,
                right: 10,
                top:12,
                textStyle: {
                    color: "#fff",
                    fontSize:'25'
                },
                itemWidth: 12,
                itemHeight: 10,
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
                    interval: 0,
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
            dataZoom: [{
                show: true,
                height: 12,
                xAxisIndex: [0],
                bottom:'8%',
                start: 10,
                end: 90,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#d3dee5",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"#90979c"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
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
                data: _man,
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
                    data: _woman,
                },
                {
                    name: '未知性别',
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
                    data: _unknow,
                },
                {
                    name: '统计总数',
                    type: 'line',
                    data: result,
                    lineStyle: {
                        normal: {
                            width: 5,
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
                }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssqsfx/shseCount',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,778899)
                if(res.status==200){
                    var _nan = res.result.nan;
                    var _nv = res.result.nv;
                    var _qt = res.result.qt;
                    var result = [];
                    for(var i=0;i<_nan.length;i++)
                    {
                        result[i]=_nan[i]+_nv[i]+_qt[i];
                    }
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name:'男',
                            data:_nan 
                        },{
                            name: '女',
                            data:_nv
                        },{
                            name: '未知性别',
                            data:_qt
                        },{
                            name: '关押总数',
                            data:result
                        }]
                    }
                    myChar.setOption(option);
                }else{
 
                }
                myChar.setOption(option);
            },error:function () {
            }

        });
    }
    //留所服刑分析
    var  _lsfxfxBar=function (id,url,title) {
        var _id = id;
        var _tilte = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _lsfx=['男','女','未知性别','分析总数'];
        var _man = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _woman = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        var _unknown = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]; 
        var result=[];
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
                            "name": '男',
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
                            "name":'女',
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
                            "name":'未知性别',
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
                                                "color": "rgba(255,155,117,0.7)"
                                            },
                                            {
                                                "offset": 0.5,
                                                "color": "rgba(155,255,252,0.7)"
                                            },
                                            {
                                                "offset": 1,
                                                "color": "rgba(155,255,252,0.3)"
                                            }
                                        ],
                                        "globalCoord": false
                                    }
                                }
                            },
                            "barGap": "0"
                        },
                        {
                            "name":'分析总数',
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
                        var _man = data.result.man ;
                        var _woman = data.result.woman ;
                        var _unknown = data.result.unknown ;
                        var result=[]
                        for(var i=0;i<_man.length;i++)
                        {
                            result[i]=_man[i]+_woman[i]+_unknown[i];
                        }
                        var option={
                            xAxis:{},
                            yAxis:{},
                            series:[{
                                "name": '男',
                                data:_man
                            },{
                                "name":'女',
                                data:_woman
                            },{
                                "name":'未知性别',
                                data:_unknown
                            },{
                                "name": '分析总数',
                                data:result
                            }]
                        }
                    myChar.setOption(option);
                    },error:function () {

                    }
                });
            }
    //违规情况分析
    var _wgqkfxBar= function (id,url,title) {
            var _id = id;
            var _title = title || undefined ? title : "未定义";
            var myChar = echarts.init(document.getElementById(_id));
            var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var _wgfl=['监区违规', '监室违规','人员违规','违规总数']
            var _jqwg = [30,30,30,40,50,10,10,10,20,20,10,10,10,20,20,30,50];
            var _jswg = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10];
            var _rywg = [20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20];
            var _wgs = [];
            for(var i=0;i<_jqwg.length;i++)
            {
                _wgs[i]=_jqwg[i]+_jswg[i]+_rywg[i];
            }
        var option = {
            backgroundColor: "#191e40",
            title: {
                text:_title,
                subtext: '',
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
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            grid: {
                borderWidth: 0,
                top: 110,
                bottom: 95,
                textStyle: {
                    color: "#fff"
                }
            },
            legend: {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                    fontSize:20
                },
                data:_wgfl
            },


            calculable: true,
            xAxis: [{
                type: "category",
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                        fontSize:15
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    fontSize:22
                },
                data: _dqm,
            }],
            yAxis: [{
                type: "value",
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#90979c',
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,

                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:'20'
                },
            }],
            dataZoom: [{
                show: true,
                height: 12,
                fillerColor:'#ae9cef',
                xAxisIndex: [0],
                bottom:'4%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#c6bbf1",
                },
                textStyle:{
                    color:"#c6bbf1"
                },
                borderColor:"#c6bbf1"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [{
                name: '监区违规',
                type: "bar",
                stack: "总量",
                barWidth: 10,
                barGap: "10%",
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#3c2c63'},
                                // {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                                {offset: 1, color: 'rgba(156,107,211,0)'}
                            ]
                        ),
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff"
                            },
                            position: "insideTop",
                            formatter: function(p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                data:_jqwg
            },
                {
                    name: '监室违规',
                    type: "bar",
                    stack: "总量",
                    barWidth: 10,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color:  new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#8b7de4'},
                                    // {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                                    {offset: 1, color: '#4c36e6'}
                                ]
                            ),
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff"
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:_jswg
                },
                {
                    name: '人员违规',
                    type: "bar",
                    stack: "总量",
                    barWidth: 10,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#956FD4'},
                                    {offset: 1, color: '#3EACE5'}
                                ]
                            ),
                            label: {
                                show: true,
                                textStyle: {
                                    color: "#fff"
                                },
                                position: "insideTop",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:_rywg
                }, {
                    name: '违规总数',
                    type: "line",
                    smooth:true,
                    symbolSize: 10,
                    symbol: 'circle',
                    itemStyle: {
                        normal: {
                            color: "#b740f3",
                            barBorderRadius: 0,
                            label: {
                                show: true, 
                                position: "start",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:_wgs
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssqsfx/wgqkfxCount',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    _jqwg = res.result.jqwg;
                    _jswg = res.result.jswg;
                    _rywg = res.result.rywg;
                    var _wgs = [];
                    for(var i=0;i<_jqwg.length;i++)
                    {
                        _wgs[i]=_jqwg[i]+_jswg[i]+_rywg[i];
                    }
                    var option={
                        xAxis: [{}],
                        yAxis: [{}],
                        series:[
                            {
                                name: '监区违规',
                                data: _jqwg
                            },
                            {
                                name: '监室违规',
                                data: _jswg
                            },
                            {
                                name: '人员违规',
                                data: _rywg
                            },
                            {
                                name: '违规总数',
                                data: _wgs
                            },
                        ]
                        
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //人员管理情况分析
    var _ryglqkfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _rs = ['械具','禁闭','单独关押','严管人员','耳目'];
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _xj = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _jb = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _gy = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _yg = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _em = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var option = {
            title: {
                text: '人员管理情况分析',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: _rs,
                textStyle:{
                    fontSize:20,
                    color:'#7d838b'
                }
            },
            xAxis: {
                type: 'value',
                axisLabel: {
                    textStyle: {
                        color: "#7d838b",
                        fontSize:18
                    }
                }

            },
            yAxis: {
                type: 'category',
                axisLabel: {
                    textStyle: {
                        color: "#7d838b",
                        fontSize:18
                    }
                },
                data: _dqm
            },
            dataZoom: [{
                show: true,
                fillerColor:'#749f83',
                height: 12,
                yAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ffffff",

                },
                textStyle:{
                    color:"#ffffff"
                },
                borderColor:"#ffffff"
            }, {
                type: "inside",
                show: true,
                height: 15,
                yAxisIndex: [0],
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '械具',
                    type: 'bar',
                    textStyle:{
                        borderRadius: 18,
                    },
                    itemStyle: { 
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            //柱形图圆角，初始化效果
                            barBorderRadius:[10, 10, 10, 10],
                            label: {
                                show: true,//是否展示
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        },
                    },
                    data: _xj
                },
                {
                    name: '禁闭',
                    type: 'bar',
                    itemStyle: { 
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            //柱形图圆角，初始化效果
                            barBorderRadius:[10, 10, 10, 10],
                            label: {
                                show: true,//是否展示
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        },
                    },
                    data: _jb
                },
                {
                    name: '单独关押',
                    type: 'bar',
                    itemStyle: { 
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            //柱形图圆角，初始化效果
                            barBorderRadius:[10, 10, 10, 10],
                            label: {
                                show: true,//是否展示
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        },
                    },
                    data: _gy
                },
                {
                    name: '严管人员',
                    type: 'bar',
                    itemStyle: { 
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            //柱形图圆角，初始化效果
                            barBorderRadius:[10, 10, 10, 10],
                            label: {
                                show: true,//是否展示
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        },
                    },
                    data: _yg
                },
                {
                    name: '耳目',
                    type: 'bar',
                    itemStyle: { 
                        emphasis: {
                            barBorderRadius: 30
                        },
                        normal: {
                            //柱形图圆角，初始化效果
                            barBorderRadius:[10, 10, 10, 10],
                            label: {
                                show: true,//是否展示
                                textStyle: {
                                    fontWeight:'bolder',
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        },
                    },
                    data: _em
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_rygl',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    var option={
                        xAxis: [{}],
                            yAxis:[{}],
                            series:[{
                                name: '械具',
                                data: data.result.jj,
                            },{
                                name: '禁闭',
                                data: data.result.jb,
                            },{
                                name: '单独关押',
                                data: data.result.ddgy,
                            },{
                                name: '严管人员',
                                data: data.result.ygry,
                            },{
                                name: '耳目',
                                data: data.result.yzem,
                            }]
                    }
                    myChar.setOption(option);
                }else{

                } 
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //在所状态分析
    var _zsztfxLine=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _zs =  ['已决', '未决'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _yj = [10,0,30,0,0,50,0,0,70,0,0,90,0,0,0,0,0];
        var _wj = [20,0,40,0,0,60,0,0,80,0,0,100,0,0,0,0,0];
        var myChar = echarts.init(document.getElementById(_id));
        var option = {
            backgroundColor:'#394056',
            title:{},
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                color: ["#F58080", "#47D8BE"],
                data: _zs,
                left: 'center',
                top: 'top',
                textStyle:{
                    fontSize:20,
                    color:'#fff'
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
                
                axisLine: {
                    lineStyle: {
                        color: "#fff",
                    },
                    
                },
                axisLabel:{
                    fontSize:'22'
                }
                
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    lineStyle: {
                        type: 'dashed',
                        color: '#fff'
                    }
                },
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: "#fff",
                    },
                },
                nameTextStyle: {
                    color: "#fff",
                    fontSize:20
                },
                textStyle: {
                    fontSize:20
                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:'18'
                }
            },
            dataZoom: [{
                show: true,
                fillerColor:"#c17476",
                height: 12,
                xAxisIndex: [0],
                bottom:'5%',
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
                name: '已决',
                type: 'line',
                data: _yj,
                color: "#F58080",
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
                lineStyle: {
                    normal: {
                        width: 2,
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
                        shadowBlur: 5,
                        shadowOffsetY: 7
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#F58080',
                        borderWidth: 5,
                        borderColor: "#F58080"
                    }
                },
                smooth: true
            },
                {
                    name: '未决',
                    type: 'line',
                    data: _wj,
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
                    lineStyle: {
                        normal: {
                            width: 2,
                            fontSize:20,
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
                            borderWidth: 5,
                            borderColor: "#AAF487"
                        }
                    },
                    smooth: true
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_zszt',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    var option={
                        xAxis: [{}],
                            yAxis:[{}],
                            series:[{
                                name: '已决',
                                data: data.result.wj,
                            },{
                                name: '未决',
                                data: data.result.yj,
                            }]
                    }
                    myChar.setOption(option);
                }else{

                } 
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //会见人数分析
    var _hjrsfxLine = function(id,url,title){
        var _id = id;
        var _title = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _wgfl=['家属会见', '律师会见','提讯','总数']
        var _js = [30,30,30,40,50,10,10,10,20,20,10,10,10,20,20,30,50];
        var _ls = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10];
        var _tx = [20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20];
        var _zs = [60,60,60,70,80,40,40,40,50,50,40,40,40,50,50,60,80];
        for(var i=0;i<_js.length;i++)
        {
            _zs[i]=_js[i]+_ls[i]+_tx[i];
        }
        option = {
            title: {
                text: '会见人数分析'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: _wgfl,
                textStyle: {
                    fontSize:'20'
                },
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: _dqm,
                axisLabel:{
                    fontSize:'18'
                }
            },
            yAxis: {
                type: 'value',
                axisLabel:{
                    fontSize:'18'
                }
            },
            series: [
                {
                    name: '家属会见',
                    type: 'line',
                    stack: '总量',
                    data: _js
                },
                {
                    name: '律师会见',
                    type: 'line',
                    stack: '总量',
                    data: _ls
                },
                {
                    name: '提讯',
                    type: 'line',
                    stack: '总量',
                    data: _tx
                },
                {
                    name: '总数',
                    type: 'line',
                    stack: '总量',
                    data: _zs
                },
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_hjrs',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name: '家属会见',
                            data: data.result.jshj,
                        },{
                            name: '律师会见',
                            data: data.result.lshj,
                        },{
                            name: '提讯',
                            data: data.result.tx,
                        },{
                            name: '总数',
                            data: data.result.all,
                        }]
                    }
                    myChar.setOption(option);
                }else{

                } 
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //吸毒人员分析
    var _xdryfxCircle=function(id,url,title){
        var _id = id;
        var _title = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _wgfl=['吸毒人数', '百分比']
        var _rs = [30,30,30,40,50,10,10,10,20,20,10,10,10,20,20,30,50];
        var _bfb = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10];
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: _wgfl
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine:{
                    fontSize:'18'
                }
            },
            yAxis: {
                type: 'category',
                data: _dqm,
                axisLabel:{
                    fontSize:'18'
                }
            },
            series: [
                {
                    name: '直接访问',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: []
                },
                {
                    name: '邮件营销',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: []
                },
                {
                    name: '联盟广告',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data:[]
                },
                {
                    name: '吸毒人数',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _rs
                },
                {
                    name: '百分比',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _bfb
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_xdry',
            type:"get",
            dataType:"json",
            success:function (data) {
                if(data.status==200){
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name: '吸毒人数',
                            data: data.result.xdry,
                        },{
                            name: '百分比',
                            data: data.result.xdry_bfb,
                        }]
                    }
                    myChar.setOption(option);
                }else{

                } 
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //人员正常出所比对分析
    var _ryzccsbdChar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['今年出所', '去年出所'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _jncs = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _qncs = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var option = {
            backgroundColor:'#394056',
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
            },
            xAxis: {
                type: 'category',
                data: _dqm,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel: {
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
                        color: '#fff'
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
                name: '今年出所',
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
                data: _jncs,
            },
                {
                    name: '去年出所',
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
                    data: _qncs
                }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/zccs',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    var _jn = res.result.jn;
                    var _qn = res.result.qn;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: '今年出所',
                            data:_jn
                        },{
                            name: '去年出所',
                            data:_qn
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //临时出所分析
    var _lscsfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _lscs = ['开庭审理','公、检、法办案','探亲','看病','住院','劳动','其他','所外就医','请假出所'];
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _ktsl = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _gjfba = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _tq = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _kb = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _zy = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _ld = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _qt = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _swjy = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _qjcs = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: _lscs
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: _dqm
            },
            dataZoom: [{
                show: true,
                fillerColor:'#749f83',
                height: 12,
                yAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#ffffff",

                },
                textStyle:{
                    color:"#ffffff"
                },
                borderColor:"#ffffff"
            }, {
                type: "inside",
                show: true,
                height: 15,
                yAxisIndex: [0],
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '开庭审理',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _ktsl
                },
                {
                    name: '公、检、法办案',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _gjfba
                },
                {
                    name: '探亲',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _tq
                },
                {
                    name: '看病',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _kb
                },
                {
                    name: '住院',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _zy
                },
                {
                    name: '劳动',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _ld
                },
                {
                    name: '其他',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _qt
                },
                {
                    name: '所外就医',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _swjy
                },
                {
                    name: '请假出所',
                    type: 'bar',
                    stack: '总量',
                    label: {
                        show: true,
                        position: 'insideRight'
                    },
                    data: _qjcs
                }
            ]
        }
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/lscsfx',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    var _ktsl = res.result.ktsl ;
                    var _gjfba = res.result.gjfba;
                    var _tq = res.result.tq;
                    var _kb = res.result.kb;
                    var _zy = res.result.zy;
                    var _ld = res.result.ld;
                    var _qt = res.result.qt;
                    var _swjy = res.result.swjy;
                    var _qjcs = res.result.qjcs;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: '开庭审理',
                            data:_ktsl
                        },{
                            name: '公、检、法办案',
                            data:_gjfba
                        },{
                            name: '探亲',
                            data:_tq
                        },{
                            name: '看病',
                            data:_kb
                        },{
                            name: '住院',
                            data:_zy
                        },{
                            name: '劳动',
                            data:_ld
                        },{
                            name: '其他',
                            data:_qt
                        },{
                            name: '所外就医',
                            data:_swjy
                        },{
                            name: '请假出所',
                            data:_qjcs
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    } 
    //出所原因
    var _csyyfxCircle=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 2154,
            name: '投送监狱'
        }, {
            value: 3854,
            name: '释放'
        }, {
            value: 3515,
            name: '取保候审'
        }, {
            value: 3515,
            name: '监视居住'
        }, {
            value: 3854,
            name: '暂予监外执行'
        }, {
            value: 2154,
            name: '保外就医'
        }, {
            value: 3854,
            name: '假释'
        }, {
            value: 3854,
            name: '逃跑'
        }, {
            value: 3854,
            name: '死亡'
        },{
            value: 3854,
            name: '转其他所'
        }, {
            value: 3515,
            name: '执行死刑'
        }, {
            value: 3515,
            name: '其他'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            // title: {
            //     text:'有吸毒史总人数',
            //     left:'center',
            //     top:'53%',
            //     padding:[24,0],
            //     textStyle:{
            //         color:'#fff',
            //         fontSize:18*scale,
            //         align:'center'
            //     }
            // },
            legend: {
                selectedMode:false,
                // formatter: function(name) {
                //     var total = 0;
                //     var averagePercent;
                //     echartData.forEach(function(value, index, array) {
                //         total += value.value;
                //     });
                //     return '{total|' + total + '}';
                // },
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 16 * scale,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/csyyfx?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    res.forEach(function(value, index, array) {
                                        total += (value.value)*1;
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //深挖犯罪
    var _swfzfxBar = function(id,url,title){
        var _id = id;
        var _title = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _wgfl=['坦白交代', '检举揭发','总数']
        var _tbjd = [30,30,30,40,50,10,10,10,20,20,10,10,10,20,20,30,50];
        var _jj = [10,20,40,30,30,20,20,30,50,40,50,40,50,20,20,20,10];
        var _zs = [40,50,70,70,80,30,30,40,70,60,60,50,60,40,40,50,60];
        for(var i=0;i<_tbjd.length;i++){
            _zs[i]=_tbjd[i]+_jj[i];
        }
        option = {
            title: {
                text: '深挖犯罪'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: _wgfl,
                textStyle: {
                    fontSize:'18'
                },
            },
            xAxis: [
                {
                    type: 'category',
                    data: _dqm,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLabel:{
                        fontSize:'18'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} ',
                        fontSize:'18'
                    }
                },
                {
                    type: 'value',
                    name:"总数",
                    axisLabel: {
                        formatter: '{value} ',
                        fontSize:'18'
                    }
                },

            ],
            dataZoom: [{
                show: true,
                fillerColor:'#61a0a8',
                height: 12,
                xAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#61a0a8",

                },
                textStyle:{
                    color:"#61a0a8"
                },
                borderColor:"#61a0a8"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '坦白交代',
                    type: 'bar',
                    data: _tbjd
                },
                {
                    name: '检举揭发',
                    type: 'bar',
                    data: _jj
                },
                {
                    name: '总数',
                    type: 'line',
                    yAxisIndex: 1,
                    data: _zs
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/swfz',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    var _tbjd = res.result.tbjd ;
                    var _jj = res.result.jj;
                    var _zs = res.result.zs;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: '坦白交代',
                            data:_tbjd
                        },{
                            name: '检举揭发',
                            data:_jj
                        },{
                            name: '总数',
                            data:_zs
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //案件类别分析
    var _ajajlbfxCircle=function(id,url,title){
    var _id = id;
    var myChar = echarts.init(document.getElementById(_id));
    var _title=title || undefined ? title : '未定义';
    var scale = 1;
    var echartData = [{
        value: 2154,
        name: '危害国家安全案'
    }, {
        value: 3854,
        name: '军人违反职责案'
    }, {
        value: 3515,
        name: '危害公共安全案'
    }, {
        value: 3515,
        name: '危害国防利益案'
    }, {
        value: 3854,
        name: '贪污贿赂案'
    }, {
        value: 2154,
        name: '侵犯公民人身权利'
    }, {
        value: 3854,
        name: '民主权利案'
    }, {
        value: 3854,
        name: '侵犯财产案'
    }, {
        value: 3854,
        name: '妨碍社会管理秩序案'
    },{
        value: 3854,
        name: '破坏社会主义市场经济秩序案'
    }, {
        value: 3515,
        name: '渎职罪'
    }]
    var rich = {
        yellow: {
            color: "#ffc72b",
            fontSize: 25 * scale,
            // padding: [10, 8],
            align: 'center'
        },
        total: {
            color: "#ffc72b",
            fontSize: 40 * scale,
            align: 'center',
            padding: [10, 8],
        },
        white: {
            color: "#fff",
            align: 'center',
            fontSize: 20* scale,
            padding: [10, 0]
        },
        blue: {
            color: '#49dff0',
            fontSize: 20 * scale,
            align: 'center',
            padding: [10, 8],
        },
        hr: {
            borderColor: '#0b5263',
            width: '100%',
            borderWidth: 1,
            height: 0,
        }
    }
    var option = {
        backgroundColor: '#031f2d',
        legend: {
            selectedMode:false,
            data: ['北京市'],
            left: 'center',
            top: 'center',
            icon: 'none',
            align:'center',
            textStyle: {
                color: "#fff",
                fontSize: 22,
                rich: rich
            },
        },
        series: [{
            name: '北京市',
            type: 'pie',
            radius: ['42%', '50%'],
            hoverAnimation: false,
            color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
            label: {
                normal: {
                    formatter: function(params, ticket, callback) {
                        var total = 0;
                        var percent = 0;
                        echartData.forEach(function(value, index, array) {
                            total += value.value;
                        });
                        percent = ((params.value / total) * 100).toFixed(1);
                        return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                    },
                    rich: rich
                },
            },
            labelLine: {
                normal: {
                    length: 55 * scale,
                    length2: 0,
                    lineStyle: {
                        color: '#0b5263'
                    }
                }
            },
            data: echartData
        }]
    };
    myChar.setOption(option);
    $.ajax({
        url:'/kssdp/ajlb?jsbh=1100001',
        type:"get",
        dataType:"json",
        success:function (res) {
            console.log(res,654987)
            var option = {
                series:[{
                    label:{
                        normal: {
                            formatter: function(params, ticket, callback) {
                                var total = 0;
                                var percent = 0;
                                res.forEach(function(value, index, array) {
                                    total += value.value*1;
                                });
                                if(total!=0){
                                    percent = ((params.value / total) * 100).toFixed(1);
                                }else{
                                    percent = 0
                                }
                                return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                            },
                        },
                    },
                    data: res,
                }]
            }
            myChar.setOption(option)
        },error:function (error) {
            console.log(error)
        }
    });
    }
    //员涉嫌犯罪分析
    var _ysxfzfxBar=function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var myChar = echarts.init(document.getElementById(_id));
        var _wgfl = ['故意伤害致人重伤或死亡','贩卖毒品','强奸','故意杀人','抢劫','放火','爆炸','投毒'];
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _gysh = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _fmdp = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _qj = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _gysr = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _qiangjie = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _fh = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        var _bz = [120,200,300,400,410,500,600,321,500,400,300,451,200,100,367,700,600];
        var _td = [100,150,200,250,300,215,350,400,450,354,500,550,150,600,750,360,800];
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: _wgfl,
                textStyle:{
                    fontSize:'18'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: _dqm,
                    axisLabel:{
                        fontSize:'18'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel:{
                        fontSize:'18'
                    }
                }
            ],
            dataZoom: [{
                show: true,
                fillerColor:'#61a0a8',
                height: 12,
                xAxisIndex: [0],
                bottom:'1%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#61a0a8",

                },
                textStyle:{
                    color:"#61a0a8"
                },
                borderColor:"#61a0a8"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [
                {
                    name: '故意伤害致人重伤或死亡',
                    type: 'bar',
                    data: _gysh
                },
                {
                    name: '贩卖毒品',
                    type: 'bar',
                    stack: '广告',
                    data: _fmdp
                },
                {
                    name: '强奸',
                    type: 'bar',
                    stack: '广告',
                    data: _qj
                },
                {
                    name: '故意杀人',
                    type: 'bar',
                    stack: '广告',
                    data: _gysr
                },
                {
                    name: '抢劫',
                    type: 'bar',
                    data: _qiangjie,
                    markLine: {
                        lineStyle: {
                            type: 'dashed'
                        },
                        data: [
                            [{type: 'min'}, {type: 'max'}]
                        ]
                    }
                },
                {
                    name: '放火',
                    type: 'bar',
                    barWidth: 5,
                    stack: '搜索引擎',
                    data: _fh
                },
                {
                    name: '爆炸',
                    type: 'bar',
                    stack: '搜索引擎',
                    data: _bz
                },
                {
                    name: '投毒',
                    type: 'bar',
                    stack: '搜索引擎',
                    data: _td
                },
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/yzblfz',
            type:'get',
            dataType:'json',
            success:function(res){
                // var data = res.result
                var _gysh = res.result.gysh;
                var _fmdp = res.result.fmdp;
                var _qj = res.result.qj;
                var _gysr = res.result.gysr;
                var _qiangjie = res.result.qiangjie;
                var _fh = res.result.fh;
                var _bz = res.result.bz;
                var _td = res.result.td;
                var option = {
                    xAxis:[{}],
                    yAxis:[{}],
                    series:[
                        {
                            name: '故意伤害致人重伤或死亡',
                            data: _gysh
                        },
                        {
                            name: '贩卖毒品',
                            data: _fmdp
                        },
                        {
                            name: '强奸',
                            data: _qj
                        },
                        {
                            name: '故意杀人',
                            data: _gysr
                        },
                        {
                            name: '抢劫',
                            data: _qiangjie
                        },
                        {
                            name: '放火',
                            data: _fh
                        },
                        {
                            name: '爆炸',
                            data: _bz
                        },
                        {
                            name: '投毒',
                            data: _td
                        }, 
                    ]
                }   
                myChar.setOption(option)
            },error:function(error){
                console.log(error)
            }
            
        })
    }
    //涉黑涉恶案件情况分析
    var _shseajqkfxChar=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 3515,
            name: '组织、领导、参加黑社会性质组织'
        }, {
            value: 3515,
            name: '寻衅滋事'
        }, {
            value: 3854,
            name: '开设赌场'
        }, {
            value: 2154,
            name: '聚众斗殴'
        }, {
            value: 3854,
            name: '敲诈勒索'
        }, {
            value: 3854,
            name: '非法拘禁'
        }, {
            value: 3854,
            name: '组织卖淫'
        },{
            value: 3854,
            name: '强迫交易'
        }, {
            value: 3515,
            name: '强迫卖淫'
        }, {
            value: 3515,
            name: '故意损坏财物'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            legend: {
                selectedMode:false,
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 22,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/shsefx?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    res.forEach(function(value, index, array) {
                                        total += (value.value)*1;
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //民警数量分析
    var _mjslfxLine = function(id,url,title){
        var _id = id;
        var _title = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _mjsl=['民警数量']
        var _sl= [10,50,70,70,80,30,30,10,70,60,40,30,30,20,20,30,60];
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
                data: _mjsl,
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
                show: true,
                fillerColor:"#174d82",
                height: 12,
                xAxisIndex: [0],
                bottom:'0.5%',
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
                name: '民警数量',
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
                data: _sl
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/mjsl',
            type:'get',
            dataType:'json',
            success:function(res){
                if(res.status==200){
                    var sl = res.result.sl
                    var option = {
                        xAxis:[{}],
                        yAxis:[{}],
                        series:[{
                            name: '民警数量',
                            data:sl
                        }]
                    }
                    myChar.setOption(option)
                }
            },error:function(error){
                console.log(error)
            }
        })
    }
    //民警性别分析
    var _mjxbfxBar = function(id,url,title){
        var _id = id;
        var _title = title || undefined ? title : "未定义";
        var myChar = echarts.init(document.getElementById(_id));
        var _dqm=['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _mjxb=['男生','女生','总数']
        var _man= [10,50,70,70,80,30,30,10,70,60,40,30,30,20,20,30,60];
        var _woman = [10,50,70,70,80,30,30,10,70,60,40,30,30,20,20,30,60];
        var result = []
        for(i=0;i<_man.length;i++){
            result[i] = _man[i] + _woman[i]
        }
        var option = {
            backgroundColor: "#191e40",
            title: {
                text:_title,
                subtext: '',
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
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            grid: {
                borderWidth: 0,
                top: 110,
                bottom: 95,
                textStyle: {
                    color: "#fff"
                }
            },
            legend: {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                    fontSize:20
                },
                data:_mjxb
            },


            calculable: true,
            xAxis: [{
                type: "category",
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                        fontSize:15
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    fontSize:22
                },
                data: _dqm,
            }],
            yAxis: [{
                type: "value",
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,

                },
                splitArea: {
                    show: false
                },
                axisLabel:{
                    fontSize:'20'
                },
            }],
            dataZoom: [{
                show: true,
                height: 12,
                fillerColor:'#8779d7',
                xAxisIndex: [0],
                bottom:'4%',
                start: 0,
                end: 60,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#c6bbf1",
                },
                textStyle:{
                    color:"#c6bbf1"
                },
                borderColor:"#c6bbf1"
            }, {
                type: "inside",
                show: true,
                height: 15,
                start: 1,
                end: 35
            }],
            series: [{
                name: '男生',
                type: "bar",
                stack: "总量",
                barWidth: 10,
                barGap: "10%",
                itemStyle: {
                    normal: {
                        barBorderRadius: (5,0),
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: 'rgba(156,107,211,0.5)'},
                                {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                                {offset: 1, color: 'rgba(156,107,211,0)'}
                            ]
                        ),
                    }
                },
                data:_man
            },
                {
                    name: '女生',
                    type: "bar",
                    stack: "总量",
                    barWidth: 10,
                    barGap: "10%",
                    itemStyle: {
                        normal: {
                            barBorderRadius: (5,0),
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#956FD4'},
                                    {offset: 1, color: '#3EACE5'}
                                ]
                            )
                        }
                    },
                    data:_woman
                },{
                    name: '总数',
                    type: "line",
                    smooth:true,
                    symbolSize: 10,
                    symbol: 'circle',
                    itemStyle: {
                        normal: {
                            color: "#992ba2",
                            barBorderRadius: 0,
                            label: {
                                show: true, 
                                position: "top",
                                formatter: function(p) {
                                    return p.value > 0 ? (p.value) : '';
                                }
                            }
                        }
                    },
                    data:result
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/mjxbfx',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    _man = res.result.man;
                    _woman = res.result.woman;
                    var _zs = [];
                    for(var i=0;i<_woman.length;i++)
                    {
                        _zs[i]=_man[i]+_woman[i];
                    }
                    var option={
                        xAxis: [{}],
                        yAxis: [{}],
                        series:[
                            {
                                name: '男生',
                                data: _man
                            },
                            {
                                name: '女生',
                                data: _woman
                            },
                            {
                                name: '总数',
                                data: _zs
                            },
                        ]
                        
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }
    //民警警衔分析
    var _mjjxfxCircle=function(id,url,title){
        var _id = id;
        var myChar = echarts.init(document.getElementById(_id));
        var _title=title || undefined ? title : '未定义';
        var scale = 1;
        var echartData = [{
            value: 2154,
            name: '刑事拘留'
        }, {
            value: 3854,
            name: '逮捕'
        }, {
            value: 3515,
            name: '临时寄押'
        }, {
            value: 3515,
            name: '外省市转入'
        }, {
            value: 3854,
            name: '本省市转入'
        }, {
            value: 2154,
            name: '异地羁押'
        }, {
            value: 3854,
            name: '取保候审转逮捕'
        }, {
            value: 3854,
            name: '监视居住转逮捕'
        }, {
            value: 3854,
            name: '取消监外执行'
        },{
            value: 3854,
            name: '撤销保外就医'
        }, {
            value: 3515,
            name: '取消假释'
        }, {
            value: 3515,
            name: '撤销缓刑'
        }, {
            value: 3854,
            name: '逃跑收回'
        }, {
            value: 2154,
            name: '其他收回'
        }, {
            value: 3854,
            name: '投送未收（5）'
        }, {
            value: 3854,
            name: '其它（2）'
        }]
        var rich = {
            yellow: {
                color: "#ffc72b",
                fontSize: 25 * scale,
                // padding: [10, 8],
                align: 'center'
            },
            total: {
                color: "#ffc72b",
                fontSize: 40 * scale,
                align: 'center',
                padding: [10, 8],
            },
            white: {
                color: "#fff",
                align: 'center',
                fontSize: 20* scale,
                padding: [10, 0]
            },
            blue: {
                color: '#49dff0',
                fontSize: 20 * scale,
                align: 'center',
                padding: [10, 8],
            },
            hr: {
                borderColor: '#0b5263',
                width: '100%',
                borderWidth: 1,
                height: 0,
            }
        }
        var option = {
            backgroundColor: '#031f2d',
            legend: {
                selectedMode:false,
                data: ['北京市'],
                left: 'center',
                top: 'center',
                icon: 'none',
                align:'center',
                textStyle: {
                    color: "#fff",
                    fontSize: 22,
                    rich: rich
                },
            },
            series: [{
                name: '北京市',
                type: 'pie',
                radius: ['42%', '50%'],
                hoverAnimation: false,
                color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
                label: {
                    normal: {
                        formatter: function(params, ticket, callback) {
                            var total = 0;
                            var percent = 0;
                            echartData.forEach(function(value, index, array) {
                                total += value.value;
                            });
                            percent = ((params.value / total) * 100).toFixed(1);
                            return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                        },
                        rich: rich
                    },
                },
                labelLine: {
                    normal: {
                        length: 55 * scale,
                        length2: 0,
                        lineStyle: {
                            color: '#0b5263'
                        }
                    }
                },
                data: echartData
            }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/mjjxfx?jsbh=1100001',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,654987)
                var option = {
                    series:[{
                        label:{
                            normal: {
                                formatter: function(params, ticket, callback) {
                                    var total = 0;
                                    var percent = 0;
                                    res.forEach(function(value, index, array) {
                                        total += (value.value)*1;
                                    });
                                    if(total!=0){
                                        percent = ((params.value / total) * 100).toFixed(1);
                                    }else{
                                        percent = 0
                                    }
                                    return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
                                },
                            },
                        },
                        data: res
                    }]
                }
                myChar.setOption(option)
            },error:function (error) {
                console.log(error)
            }
        });
        
    }
    //民警学历分析
    var _mjxlfxChar = function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _yl =  ['文盲', '小学', '中学','大专以上'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _wm = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,70];
        var _xx = [10,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
        var _zx = [100,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,40];
        var _dzys=[50,30,20,40,50,60,70,50,70,30,40,70,10,30,20,40,100];
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
                data: _wm,
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
                    data: _xx,
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
                    data: _zx
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
                    data: _dzys,
                }]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/mjwhcd',
            type:"get",
            dataType:"json",
            success:function (res) {
                if(res.status==200){
                    var _wm = res.result.wm;
                    var _xx = res.result.xx;
                    var _zx = res.result.zx;
                    var _dzys = res.result.dzys;
                    var option={
                        xAxis:{},
                        yAxis:{},
                        series:[{
                            name: '文盲',
                            data:_wm
                        },{
                            name: '小学',
                            data:_xx
                        },{
                            name: '中学',
                            data:_zx
                        },{
                            name: '大专以上',
                            data:_dzys
                        }]
                    }
                    myChar.setOption(option);
                }else{

                }
            },error:function () {
            }
        });
    }
    //民警在职时长分析
    var _mjzzscfxLine = function (id,url,title) {
        var _title = title || undefined ? title :"未定义";
        var myChar = echarts.init(document.getElementById(id));
        var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var _yf = ['3月以下','3月至6月','6月至1年','1年至3年','3年以上'];
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
                data: _dqm
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
                    data : _yf,
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
        $.ajax({
            url: '/kssdp/zzscfx',
            type: "get",
            dataType: "json",
            success: function (res) {
                console.log(res.result,111222)
                var zzsc = res.result;
                var option={
                    xAxis: [{}],
                    yAxis:[{}],
                    series:[{
                        name:'北京市',
                        data:zzsc[0].data
                    },
                    {
                        name:'东城区',
                        data:zzsc[1].data
                    },  {
                        name:'西城区',
                        data:zzsc[2].data
                    },
                    {
                        name:'朝阳区',
                        data:zzsc[3].data
                    },
                    {
                        name:'丰台区',
                        data:zzsc[4].data
                    },
                    {
                        name:'石景山区',
                        data:zzsc[5].data
                    },
                    {
                        name:'海淀区',
                        data:zzsc[6].data
                    },
                    {
                        name:'门头沟区',
                        data:zzsc[7].data
                    },
                    {
                        name:'房山区',
                        data:zzsc[8].data
                    },  
                    {
                        name:'通州区',
                        data:zzsc[9].data
                    },
                    {
                        name:'顺义区',
                        data:zzsc[10].data
                    },
                    {
                        name:'昌平区',
                        data:zzsc[11].data
                    },
                    {
                        name:'大兴区',
                        data:zzsc[12].data
                    },                    
                    {
                        name:'平谷区',
                        data:zzsc[13].data
                    },
                    {
                        name:'怀柔区',
                        data:zzsc[14].data
                    },
                    {
                        name:'密云区',
                        type:'line',
                        data:zzsc[15].data
                    },
                    {
                        name:'延庆区',
                        data:zzsc[16].data
                    }]
                }
                myChar.setOption(option);

            },
            error:function(error){
                console.log(error)
            }
        })
    }
    //人员风险等级分析
    var _ryfxdjfxLine = function(id,url,title){
        var _id = id;
        var _title=title || undefined ? title : '未定义';
        var _ryfx =  ['一级', '二级', '三级'];
        var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
        var myChar = echarts.init(document.getElementById(_id));
        var _yj = [10,11,12,13,14,15,16,17,18,19,11,12,13,14,15,16,17];
        var _ej = [20,21,22,23,24,25,26,27,28,29,21,22,23,24,25,26,27];
        var _sj = [30,31,32,33,34,35,36,37,38,39,31,32,33,34,35,36,37];
        var option = {
            title: {
                text: '人员风险等级分析'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: _ryfx
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    fontSize:20,
                    color:'#293c55',
                    interval:0,//代表显示所有x轴标签
                    // rotate:45, //代表逆时针旋转45度
                },
                data: _dqm
            },
            yAxis: {
                type: 'value'
            },
            dataZoom: [{
                show: true,
                fillerColor:"#de908e",
                height: 12,
                xAxisIndex: [0],
                bottom:'2%',
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
            series: [
                {
                    name: '一级',
                    type: 'line',
                    data: _yj
                },
                {
                    name: '二级',
                    type: 'line',
                    data: _ej
                },
                {
                    name: '三级',
                    type: 'line',
                    data: _sj
                }
            ]
        };
        myChar.setOption(option);
        $.ajax({
            url:'/kssdp/select_kss_fxdj',
            type:"get",
            dataType:"json",
            success:function (res) {
                console.log(res,662211)
                if(res.status==200){
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name: '一级',
                            data: res.result.yjArray
                        },{
                            name: '二级',
                            data:res.result.ejArray
                        },{
                            name: '三级',
                            data:res.result.sjArray
                        }]}
                    myChar.setOption(option);
                }else{

                }
            },error:function (error) {
                console.log(error)
            }
        });
    }














    return{
        zyrygylfxChar:_zyrygylfxBar,
        jlsZyrygylfxChar:_jlsZyrygylfxBar,
        hjdChar:_hjdChar,
        jlsHjdChar:_jlsHjdChar,
        nlfxChar:_nlfxLine,
        jlsNlfxChar:_jlsNlfxLine,
        whcdfxChar:_whcdChar,
        jlsWhcdfxChar:_jlsWhcdChar,
        jkzkChar:_jkzkChar,
        jlsJkzkChar:_jlsJkzkChar,
        sffxChar:_sffxChar,
        jlsSffxChar:_jlsSffxChar,
        yzjbryfxChar:_yzjbryfxLine,
        jlsYzjbryfxChar:_jlsYzjbryfxLine,
        gjfxChar:_gjfxLine,
        jlsGjfxChar:_jlsGjfxLine,
        jsryfxChar:_jsryfxBar,
        sjwsryfxChar:_sjwsryfxLine,
        ndrslfxChar:_ndrslfxBar,
        gyqxfxChar:_gyqxfxLine,
        cqjyqkfxChar:_cqjyqkfxBar,
        zdryfxChar:_zdryfxBar,
        ryjyqkfxChar:_ryjyqkfxLine,
        shserytjChar:_shserytjBar,
        lsfxfxChar:_lsfxfxBar,
        wgqkfxChar:_wgqkfxBar,
        rsxzfxChar:_rsxzfxCircle,
        ajlbfxChar:_ajlbfxCircle,
        qsbajdfxChar:_qsbajdfxCircle,
        zsztfxChar:_zsztfxLine,
        hjrsfxChar:_hjrsfxLine,
        xdryfxChar:_xdryfxCircle,
        ryzccsbdfxChar:_ryzccsbdChar,
        lscsfxChar:_lscsfxBar,
        csyyfxChar:_csyyfxCircle,
        ryglqkfxChar:_ryglqkfxBar,
        swfzfxChar:_swfzfxBar,
        ajajlbfxChar:_ajajlbfxCircle,
        ysxfzfxChar:_ysxfzfxBar,
        shseajqkfxChar:_shseajqkfxChar,
        mjslfxChar:_mjslfxLine,
        mjxbfxChar:_mjxbfxBar,
        mjjxfxChar:_mjjxfxCircle,
        mjxlfxChar:_mjxlfxChar,
        mjzzscfxChar:_mjzzscfxLine,
        ryfxdjfxChar:_ryfxdjfxLine
    }
})