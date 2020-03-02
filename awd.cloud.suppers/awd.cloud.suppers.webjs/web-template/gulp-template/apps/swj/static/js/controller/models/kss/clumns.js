define(function (require) {
    var tools = require('awd/jquery/tools');
    return {
        jbxx: function (option) {

            var clumns = [
                {
                    field: 'ooid',
                    title: '',
                    width: '',
                    checkbox: option.multi == true ? false : true,
                    formatter: function (value, rowData, rowIndex) {
                        if (!option.multi) {
                            return '<input type="radio" name="ryRadio" class="ryRadio" id="ryRadio"' + rowIndex + 'value="' + rowData.ooid + '" />';
                        } else {
                            return '<input type="checkbox" name="ryRadio" />';
                        }

                    }
                },
                {
                    field: 'fxpgLastfxpgfz',
                    title: '风险趋势图',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '>' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '</span>';
                    }
                },
                {
                    field: 'snbh',
                    title: '人员编号',
                    width: 100,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.snbh == null ? '' : row.snbh) + '>' + (row.snbh == null ? '' : row.snbh) + '</span>';
                    }
                },
                {
                    field: 'xm',
                    title: '姓名',
                    width: 100,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xm == null ? '' : row.xm) + '>' + (row.xm == null ? '' : row.xm) + '</span>';
                    }
                },
                {
                    field: 'sykzrq',
                    title: '收押开证日期',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sykzrq == null ? '' : row.sykzrq) + '>' + (row.sykzrq == null ? '' : row.sykzrq) + '</span>';
                    }
                },
                {
                    field: 'jsh',
                    title: '监室号',
                    width: 100,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jsh == null ? '' : row.jsh) + '>' + (row.jsh == null ? '' : row.jsh) + '</span>';
                    }
                },
                {
                    field: 'bahjString',
                    title: '办案环节',
                    width: 100,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.bahjString == null ? '' : row.bahjString) + '>' + (row.bahjString == null ? '' : row.bahjString) + '</span>';
                    }
                },
                {
                    field: 'rsrq',
                    title: '入所时间',
                    width: 200,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.rsrq == null ? '' : row.rsrq) + '>' + (row.rsrq == null ? '' : row.rsrq) + '</span>';
                    }
                },
                {
                    field: 'gyqx',
                    title: '关押期限',
                    width: 200,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.gyqx == null ? '' : row.gyqx) + '>' + (row.gyqx == null ? '' : row.gyqx) + '</span>';
                    }
                },
                {
                    field: 'badw',
                    title: '办案单位',
                    width: 200,
                    hidden: false,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.badw == null ? '' : row.badw) + '>' + (row.badw == null ? '' : row.badw) + '</span>';
                    }
                },
                {
                    field: 'xbString',
                    title: '性别',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xbString == null ? '' : row.xbString) + '>' + (row.xbString == null ? '' : row.xbString) + '</span>';
                    }
                },
                {
                    field: 'dwlxString',
                    title: '单位类型',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.dwlxString == null ? '' : row.dwlxString) + '>' + (row.dwlxString == null ? '' : row.dwlxString) + '</span>';
                    }
                },
                {
                    field: 'rsxzString',
                    title: '入所性质',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.rsxzString == null ? '' : row.rsxzString) + '>' + (row.rsxzString == null ? '' : row.rsxzString) + '</span>';
                    }
                },
                {
                    field: 'csrq',
                    title: '出生日期',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.csrq == null ? '' : row.csrq) + '>' + (row.csrq == null ? '' : row.csrq) + '</span>';
                    }
                },
                {
                    field: 'zjlxString',
                    title: '证件类型',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title='
                            + (row.zjlxString == null ? '' : row.zjlxString) + '>' + (row.zjlxString == null ? '' : row.zjlxString) + '</span>';
                    }
                },
                {
                    field: 'gcbh',
                    title: '案件编号',
                    width: 200, hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.gcbh == null ? '' : row.gcbh) + '>' + (row.gcbh == null ? '' : row.gcbh) + '</span>';
                    }
                },
                {
                    field: 'zjh',
                    title: '证件号',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.zjh == null ? '' : row.zjh) + '>' + (row.zjh == null ? '' : row.zjh) + '</span>';
                    }
                },
                {
                    field: 'hjdString',
                    title: '户籍地',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.hjdString == null ? '' : row.hjdString) + '>' + (row.hjdString == null ? '' : row.hjdString) + '</span>';
                    }
                },
                {
                    field: 'xzdString',
                    title: '现住地',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xzdString == null ? '' : row.xzdString) + '>' + (row.xzdString == null ? '' : row.xzdString) + '</span>';
                    }
                },
                {
                    field: 'mzString',
                    title: '民族',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.mzString == null ? '' : row.mzString) + '>' + (row.mzString == null ? '' : row.mzString) + '</span>';
                    }
                },
                {
                    field: 'gjString',
                    title: '国籍',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.gjString == null ? '' : row.gjString) + '>' + (row.gjString == null ? '' : row.gjString) + '</span>';
                    }
                },
                {
                    field: 'whcdString',
                    title: '文化程度',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.whcdString == null ? '' : row.whcdString) + '>' + (row.whcdString == null ? '' : row.whcdString) + '</span>';
                    }
                },
                {
                    field: 'zyString',
                    title: '职业',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.zyString == null ? '' : row.zyString) + '>' + (row.zyString == null ? '' : row.zyString) + '</span>';
                    }
                },
                {
                    field: 'sfString',
                    title: '身份',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.sfString == null ? '' : row.sfString) + '>' + (row.sfString == null ? '' : row.sfString) + '</span>';
                    }
                },
                {
                    field: 'tssfString',
                    title: '特殊身份',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.tssfString == null ? '' : row.tssfString) + '>' + (row.tssfString == null ? '' : row.tssfString) + '</span>';
                    }
                },
                {
                    field: 'cypjfz',
                    title: '处遇评鉴分值',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.cypjfz == null ? '' : row.cypjfz) + '>' + (row.cypjfz == null ? '' : row.cypjfz) + '</span>';
                    }
                },
                {
                    field: 'jyrq',
                    title: '羁押日期',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jyrq == null ? '' : row.jyrq) + '>' + (row.jyrq == null ? '' : row.jyrq) + '</span>';
                    }
                },
                {
                    field: 'ayString',
                    title: '主要案由',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.ayString == null ? '' : row.ayString) + '>' + (row.ayString == null ? '' : row.ayString) + '</span>';
                    }
                }
            ];

            return clumns;
        },
        clcs: function (multi) {
            var clumns = [{
                field: 'ooid',
                title: '',
                width: '',
                checkbox: option.multi == true ? false : true,
                formatter: function (value, rowData, rowIndex) {
                    if (!option.multi) {
                        return '<input type="radio" name="ryRadio" class="ryRadio" id="ryRadio"' + rowIndex + 'value="' + rowData.ooid + '" />';
                    } else {
                        return '<input type="checkbox" name="ryRadio" />';
                    }

                }
            },
                {
                    field: 'jsbhString',
                    title: '监所名称',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.jsbhString == null ? '' : row.jsbhString) + '>' + (row.jsbhString == null ? '' : row.jsbhString) + '</span>';
                    }
                },
                {
                    field: 'xm',
                    title: '人员姓名',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.xm == null ? '' : row.xm) + '>' + (row.xm == null ? '' : row.xm) + '</span>';
                    }
                },
                {
                    field: 'csyyString',
                    title: '出所原因',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.csyyString == null ? '' : row.csyyString) + '>' + (row.csyyString == null ? '' : row.csyyString) + '</span>';
                    }
                },
                {
                    field: 'cssj',
                    title: '出所时间',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.cssj == null ? '' : row.cssj) + '>' + (row.cssj == null ? '' : row.cssj) + '</span>';
                    }
                },
                {
                    field: 'csqx',
                    title: '出所去向',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.csqx == null ? '' : row.csqx) + '>' + (row.csqx == null ? '' : row.csqx) + '</span>';
                    }
                },
                {
                    field: 'pzdw',
                    title: '批准单位',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.pzdw == null ? '' : row.pzdw) + '>' + (row.pzdw == null ? '' : row.pzdw) + '</span>';
                    }
                },
                {
                    field: 'pzr',
                    title: '批准人',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.pzr == null ? '' : row.pzr) + '>' + (row.pzr == null ? '' : row.pzr) + '</span>';
                    }
                },
                {
                    field: 'dbdw',
                    title: '担保单位',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.dbdw == null ? '' : row.dbdw) + '>' + (row.dbdw == null ? '' : row.dbdw) + '</span>';
                    }
                },
                {
                    field: 'dbr',
                    title: '担保人',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '>' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '</span>';
                    }
                },
                {
                    field: 'jddw',
                    title: '监督单位',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '>' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '</span>';
                    }
                },
                {
                    field: 'zcdwszd',
                    title: '转出单位所在地',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '>' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '</span>';
                    }
                },
                {
                    field: 'jsbhString',
                    title: '备注',
                    width: 200,
                    hidden: true,
                    sortable: 'true',
                    formatter: function (value, row) {
                        return '<span title=' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '>' + (row.fxpgLastfxpgfz == null ? '' : row.fxpgLastfxpgfz) + '</span>';
                    }
                }
            ];
            return clumns;
        },
        tsdj: function (multi) {
            return [];
        },
        tjdj: function (multi) {
            return [];
        },
        lshj: function (multi) {
            return [];
        },
        jshj: function (multi) {
            return [];
        },
        lscs: function (multi) {
            return [];
        },
        swjy: function (multi) {
            return [];
        },
        zyjwzx: function (multi) {
            return [];
        },
        sjzl: function (multi) {
            return [];
        },
        sjjsx: function (multi) {
            return [];
        },
    };
});