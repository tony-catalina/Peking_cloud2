define("awd/e_charts", [
	"../plugins/e-charts/echarts"
	],
    function(require) {
        var _yzjbryfxLine=function(id,url,title){
            var _id = id;
            var _title=title || undefined ? title : '未定义';
            var _yl =  ['艾滋病人数', '重点病号人数','精神异常人数'];
            var _dqm =['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            var _az = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
            var _zd = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
            var _yc=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
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
                        fontSize:'22'
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
                    data: _az,
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
                        data: _zd,
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
                        data: _yc,
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
                url:'/kssqsfx/yzjbfxCount',
                type:"get",
                dataType:"json",
                success:function (data) {
                    if(data.status==200){
                        _az = data.result.az;
                        _zd = data.result.zd;
                        _jsyc = data.result.jsyc;
                        var option={
                            xAxis: [{}],
                                yAxis:[{}],
                                series:[{
                                    name: '艾滋病人数',
                                    data: _az,
                                },{
                                    name: '重点病号人数',
                                    data: _zd,
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


        var _xdryfxCircle=function(id,url,title){
            var _id = id;
            // console.log(_id,5879)
            var _title=title || undefined ? title : '未定义';
            var scale = 1;
            var echartData = [{
                value: 2154,
                name: '打架'
            }, {
                value: 3854,
                name: '斗殴'
            }, {
                value: 3515,
                name: '你好'
            }, {
                value: 3515,
                name: '你不好'
            }, {
                value: 3854,
                name: '星空'
            }, {
                value: 2154,
                name: '暖阳'
            }, {
                value: 3854,
                name: '大海'
            }, {
                value: 3854,
                name: '沙滩'
            }, {
                value: 3854,
                name: '阳光'
            },{
                value: 3854,
                name: '温暖'
            }, {
                value: 3515,
                name: 'happy'
            }, {
                value: 3515,
                name: '好烦'
            }, {
                value: 3854,
                name: '真好'
            }, {
                value: 2154,
                name: '巧克力'
            }, {
                value: 3854,
                name: '憨货'
            }, {
                value: 3854,
                name: '哈哈'
            }, {
                value: 3854,
                name: '呵呵'
            }
        ]
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

            $.ajax({
                url:url,
                type:"post", 
                dataType:"json",
                success:function (data) {

                },error:function () {
                }
            });

            var option = {
                backgroundColor: '#031f2d',
                title: {
                    text:'有吸毒史总人数',
                    left:'center',
                    top:'53%',
                    padding:[24,0],
                    textStyle:{
                        color:'#fff',
                        fontSize:18*scale,
                        align:'center'
                    }
                },
                legend: {
                    selectedMode:false,
                    formatter: function(name) {
                        var total = 0;
                        var averagePercent;
                        echartData.forEach(function(value, index, array) {
                            total += value.value;
                        });
                        return '{total|' + total + '}';
                    },
                    data: [echartData[0].name],
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
                    name: '有吸毒史总人数',
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

            var myChar = echarts.init(document.getElementById(_id));

            myChar.setOption(option);


        }

        var _sjwsryfxLine=function(id,url,title){
            var _id = id;
            var _title=title || undefined ? title : '未定义';
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
                        fontSize:22
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
            var myChar = echarts.init(document.getElementById(_id));
            myChar.setOption(option);
            //
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
                    }else{
                        
                    }
                },error:function (error) {
                    console.log(error)
                }
            });
           


        }

        var _lslsfxLine=function(id,url,title){
            var _id = id;
            var _title=title || undefined ? title : '未定义';

            var _yl = ['临时离所人数','开庭审理','公检法办案','探亲','看病','住院','劳动','其他'];
            var _dqm = ['北京市','东城区','西城区','朝阳区','丰台区','石景山区','海淀区','门头沟区','房山区','通州区','顺义区','昌平区','大兴区','平谷区','怀柔区','密云区','延庆区'];
            // var _lslsrs = [120, 132, 101, 134, 90, 230, 210, 182, 191, 234,115,124,157,125,147,135,164];
            // var _ktsl = [220, 182, 191, 234, 290, 330, 310,201, 154, 190,182, 191, 234, 290, 330, 310,201];
            // var _gjfba = [150, 232, 201, 154, 190, 330, 410,150, 232, 201,150, 232, 201, 154, 190, 330, 410];
            // var _tq = [234,566,987,456,687,234,890,123,145,500,987,456,687,234,890,879,478];
            // var _kb = [451,684,125,888,999,444,555,666,145,852,451,684,125,888,999,444,555];
            // var _zy = [458,985,102,100,300,200,654,254,210,540,458,985,102,100,300,200,654];
            // var _ld = [897,454,124,152,215,462,356,124,154,154,897,454,124,152,215,462,356];
            // var _qt = [120,356,987,541,364,154,254,564,245,154,120,356,987,541,364,154,254];
            // var _lslsrs = [];
            // var _ktsl = [];
            // var _gjfba = [];
            // var _tq = [];
            // var _kb = [];
            // var _zy = [];
            // var _ld = [];
            // var _qt = [];
            var fontColor = '#30eee9';

            var myChar = echarts.init(document.getElementById(_id));
            var option ={
                backgroundColor:'#11183c',
                grid: {
                    left: '5%',
                    right: '5%',
                    top:'15%',
                    bottom: '10%',
                    containLabel: true
                },
                tooltip : {
                    show: true,
                    trigger: 'axis'
                },
                legend: {
                    show:true,
                    x:'center',
                    y:'35',
                    icon: 'stack',
                    itemWidth:10,
                    itemHeight:10,
                    textStyle:{
                        color:'#1bb4f6',
                        fontSize:24
                    },
                    data:_yl
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        
                        axisLabel:{
                            color: fontColor,
                            textStyle:{
                                fontSize:22
                            },
                        },
                        axisLine:{
                            show:true,
                            lineStyle:{
                                color:'#397cbc'
                            }
                        },
                        axisTick:{
                            show:false,
                        },
                        splitLine:{
                            show:true,
                            lineStyle:{
                                color:'#195384'
                            }
                        },
                        data : _dqm
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        // min:0,
                        // max:20,
                        // scale:true,
                        axisLabel : {
                            formatter: '{value}',
                            textStyle:{
                                color:'#2ad1d2',
                                fontSize:22
                            }
                        },
                        axisLine:{
                            lineStyle:{
                                color:'#27b4c2',
                                fontSize:22
                            }
                        },
                        axisTick:{
                            show:false,
                        },
                        splitLine:{
                            show:true,
                            lineStyle:{
                                color:'#11366e',
                                fontSize:22
                            }
                        }
                    },
                    // {
                    //     type : 'value',
                    //     // min:0,
                    //     // max:20,
                    //     // scale:true,
                    //     axisLabel : {
                    //         formatter: '{value}',
                    //         textStyle:{
                    //             color:'#186afe',
                    //             fontSize:22
                    //         }
                    //     },
                    //     axisLine:{
                    //         lineStyle:{
                    //             color:'#186afe',
                    //             fontSize:22
                    //         }
                    //     },
                    //     axisTick:{
                    //         show:false,
                    //     },
                    //     splitLine:{
                    //         show:true,
                    //         lineStyle:{
                    //             color:'#11366e',
                    //             fontSize:22
                    //         }
                    //     }
                    // }
                ],
                dataZoom: [{
                    show: true,
                    height: 12,
                    fillerColor:'#2ac2c5',
                    xAxisIndex: [0],
                    bottom:'5%',
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
                series : [
                    {
                        name:'临时离所人数',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,
                        itemStyle: {
                            normal: {
                                color:'#0092f6',
                                lineStyle: {
                                    color: "#0092f6",
                                    width:1,
                                    fontSize:16
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,146,246,0.9)'
                                    }]),
                                }
                            }
                        },
                        markPoint:{
                            itemStyle:{
                                normal:{
                                    color:'red'
                                }
                            }
                        },
                        // data:_lslsrs
                        data:[]
                    },
                    {
                        name:'开庭审理',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#00d4c7',
                                lineStyle: {
                                    color: "#00d4c7",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_ktsl
                        data:[]
                    },
                    {
                        name:'公检法办案',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,
                        itemStyle: {
                            normal: {
                                color: '#aecb56',
                                lineStyle: {
                                    color: "#aecb56",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(114,144,89,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_gjfba
                        data:[]
                    },
                    {
                        name:'探亲',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#FFB6C1',
                                lineStyle: {
                                    color: "#FFB6C1",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_tq
                        data:[]
                    },
                    {
                        name:'看病',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#FF00FF',
                                lineStyle: {
                                    color: "#FF00FF",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_kb
                        data:[]
                    },
                    {
                        name:'住院',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#8B008B',
                                lineStyle: {
                                    color: "#8B008B",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_zy
                        data:[]
                    },
                    {
                        name:'劳动',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#00BFFF',
                                lineStyle: {
                                    color: "#00BFFF",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_ld
                        data:[]
                    },
                    {
                        name:'其他',
                        type:'line',
                        // stack: '总量',
                        symbol:'circle',
                        symbolSize: 8,

                        itemStyle: {
                            normal: {
                                color:'#00FFFF',
                                lineStyle: {
                                    color: "#00FFFF",
                                    width:1
                                },
                                areaStyle: {
                                    //color: '#94C9EC'
                                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                        offset: 0,
                                        color: 'rgba(7,44,90,0.3)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(0,212,199,0.9)'
                                    }]),
                                }
                            }
                        },
                        // data:_qt
                        data:[]
                    }
                ]
            };

            
            myChar.setOption(option);
            /////////////
            $.ajax({
                url:'/kssqsfx/lslsfxCount',
                type:"get",
                dataType:"json",
                success:function (res) {
                    console.log(res.result.kb,8888888)
                    var option={
                        xAxis: [{}],
                        yAxis:[{}],
                        series:[{
                            name:'临时离所人数',
                            data:res.result.lslsrs
                        },{
                            name: '开庭审理',
                            data:res.result.ktsl
                        },{
                            name: '公检法办案',
                            data:res.result.gjfba
                        },{
                            name: '探亲',
                            data:res.result.tq
                        },{
                            name:'看病',
                            data:res.result.kb
                        },{
                            name:'住院',
                            data:res.result.zy
                        },{
                            name:'劳动',
                            data:res.result.ld
                        },{
                            name:'其他',
                            data:res.result.qt
                        }]
                    }
                    myChar.setOption(option);
                },error:function (error) {
                    console.log(error,999999)
                }
            });

        }

        return{
            yzjbryfxChar:_yzjbryfxLine,
            xdryfxChar:_xdryfxCircle,
            sjwsryfxChar:_sjwsryfxLine,
            lslsfxChar:_lslsfxLine
        }
    }
    );