define(function () {
    'use strict';
    return {
        sjzl: function () {
            return [
                {
                    field: '',
                    title: '序号',
                    width: 50,
                    hidden: false,
                    checkbox:true,
                    sortable: 'true',
                },
                {
                    field: 'jsbhString',
                    title: '监所名称',
                    width: 100,
                    align:'center',
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                    }
                },
                {
                    field: 'dddx',
                    title: '数据量',
                    width: 200,
                    align:'center',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sjl == null ? '' : row.sjl) + '>' + (row.sjl == null ? '' : row.sjl) + '</span>';
                    }
                },
                {
                    field: 'ddnr',
                    title: '不合格数',
                    width: 200,
                    align:'center',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.bhg == null ? '' : row.bhg) + '>' + (row.bhg == null ? '' : row.bhg) + '</span>';
                    }
                },
                {
                    field: 'ddsj',
                    title: '合格率',
                    width: 200,
                    align:'center',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.hgl == null ? '' : row.hgl) + '>' + (row.hgl == null ? '' : row.hgl) + '</span>';
                    }
                }
            ];
        },
        sjjsx: function () {
            return [
                {
                    field: '',
                    title: '序号',
                    width: 300,
                    hidden: false,
                    checkbox:true,
                    sortable: 'true',
                },
                {
                    field: 'jsbhString',
                    title: '监所名称',
                    width: '20%',
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                    }
                },
                {
                    field: 'sjl',
                    title: '表名称',
                    width: '20%',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sjl == null ? '' : row.sjl) + '>' + (row.sjl == null ? '' : row.sjl) + '</span>';
                    }
                },
                {
                    field: 'sjl',
                    title: '数据量',
                    width: '20%',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sjl == null ? '' : row.sjl) + '>' + (row.sjl == null ? '' : row.sjl) + '</span>';
                    }
                },
                {
                    field: 'bhg',
                    title: '不及时量',
                    width: '20%',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.bhg == null ? '' : row.bhg) + '>' + (row.bhg == null ? '' : row.bhg) + '</span>';
                    }
                },
                {
                    field: 'hgl',
                    title: '及时率',
                    width: '20%',
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.hgl == null ? '' : row.hgl) + '>' + (row.hgl == null ? '' : row.hgl) + '</span>';
                    }
                }
            ];
        },
        mjzf: function () {
            return [  {
                field: '',
                title: '序号',
                width: 300,
                hidden: false,
                checkbox:true,
                sortable: 'true',
            },{
                    field: 'jsbhString',
                    title: '监所名称',
                    width: 200,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                    }
                },
                {
                    field: 'sjl',
                    title: '',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sjl == null ? '' : row.sjl) + '>' + (row.sjl == null ? '' : row.sjl) + '</span>';
                    }
                },
                {
                    field: 'wgcs',
                    title: '违规次数',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.wgcs == null ? '' : row.wgcs) + '>' + (row.v == null ? '' : row.wgcs) + '</span>';
                    }
                }
            ];
        },
        jcdd:function(multil){
            return [  {
                field: '',
                title: '序号',
                width: 50,
                hidden: false,
                checkbox:true,
                sortable: 'true',
            },{
                field: 'jsbhString',
                title: '监所名称',
                width: 100,
                hidden: false,
                align:'center',
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                }
            },
                {
                    field: 'dddx',
                    title: '督导对象',
                    width: 100,
                    hidden: false,
                    align:'center',
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.dddx == null ? '' : row.dddx) + '>' + (row.dddx == null ? '' : row.dddx) + '</span>';
                    }
                },
                {
                    field: 'ddnr',
                    title: '督导内容',
                    width: 100,
                    hidden: false,
                    align:'center',
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.ddnr == null ? '' : row.ddnr) + '>' + (row.ddnr == null ? '' : row.ddnr) + '</span>';
                    }
                },
                {
                    field: 'ddsj',
                    title: '督导时间',
                    width: 100,
                    hidden: false,
                    align:'center',
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.ddsj == null ? '' : row.ddsj) + '>' + (row.ddsj == null ? '' : row.ddsj) + '</span>';
                    }
                },
                {
                    field: 'hfsj',
                    title: '回复时间',
                    width: 100,
                    hidden: false,
                    align:'center',
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.hfsj == null ? '' : row.hfsj) + '>' + (row.hfsj == null ? '' : row.hfsj) + '</span>';
                    }
                },
                {
                    field: 'zgfk',
                    title: '整改反馈',
                    width: 100,
                    hidden: false,
                    align:'center',
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.zgfk == null ? '' : row.zgfk) + '>' + (row.zgfk == null ? '' : row.zgfk) + '</span>';
                    }
                }
            ];
        },
        gwgl:function(multil){
            return [  {
                field: '',
                title: '序号',
                width: 300,
                hidden: false,
                checkbox:true,
                sortable: 'true',
            },{
                field: 'jsbhString',
                title: '监所名称',
                width: 200,
                hidden: false,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                }
            },
                {
                    field: 'gwmc',
                    title: '公文名称',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.gwmc == null ? '' : row.gwmc) + '>' + (row.gwmc == null ? '' : row.gwmc) + '</span>';
                    }
                },
                {
                    field: 'gwnr',
                    title: '公文内容',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.gwnr == null ? '' : row.gwnr) + '>' + (row.gwnr == null ? '' : row.gwnr) + '</span>';
                    }
                },
                {
                    field: 'fssj',
                    title: '发送时间',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fssj == null ? '' : row.fssj) + '>' + (row.fssj == null ? '' : row.fssj) + '</span>';
                    }
                },
                {
                    field: 'fsr',
                    title: '发送人',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fsr == null ? '' : row.fsr) + '>' + (row.fsr == null ? '' : row.fsr) + '</span>';
                    }
                }
            ];
        },
        xxgl:function(multil){
            return [  {
                field: '',
                title: '序号',
                width: 300,
                hidden: false,
                checkbox:true,
                sortable: 'true',
            },{
                field: 'jsbhString',
                title: '监所名称',
                width: 200,
                hidden: false,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                }
            },
                {
                    field: 'xxmc',
                    title: '消息名称',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xxmc == null ? '' : row.xxmc) + '>' + (row.xxmc == null ? '' : row.xxmc) + '</span>';
                    }
                },
                {
                    field: 'xxnr',
                    title: '消息内容',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xxnr == null ? '' : row.xxnr) + '>' + (row.xxnr == null ? '' : row.xxnr) + '</span>';
                    }
                },
                {
                    field: 'fssj',
                    title: '发送时间',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fssj == null ? '' : row.fssj) + '>' + (row.fssj == null ? '' : row.ddsj) + '</span>';
                    }
                },
                {
                    field: 'fsr',
                    title: '发送人',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fsr == null ? '' : row.fsr) + '>' + (row.fsr == null ? '' : row.fsr) + '</span>';
                    }
                },
                {
                    field: 'fsbm',
                    title: '发送部门',
                    width: 100,
                    hidden: false,
                    sortable: 'false',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fsbm == null ? '' : row.fsbm) + '>' + (row.fsbm == null ? '' : row.fsbm) + '</span>';
                    }
                }
            ];
        }
    };
});