require.config({
    paths: {
        echarts: '../../static/js/echarts'
    }
});
require(
    [
        'echarts',
        'echarts/theme/blue',   //主题颜色
        'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        'echarts/chart/bar'
    ],
    function (ec,theme) {
        var myChart = ec.init(document.getElementById('dataAnalysis'),theme);
        var option = {
            grid: {  //图表填充与容器的边距
                x : '0', y : '30px', width : '236px', height : '100px'
            },
            title : {
                text: '',
                subtext: ''
            },
            tooltip : {
                show: false,  //提示框，鼠标悬浮交互时的信息提示
                trigger: 'axis'
            },
            legend: {
                data:['空置间数']
            },
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : false,
            xAxis : [
                {
                    splitLine:{
                        show: false
                    }, //去除网格线
                    splitArea : {
                        show : false
                    },  //保留网格区域
                    type : 'category',
                    data : ['0-7','8-15','16-20','21-30','31-50','50以上'],
                    axisLabel : {  //x轴轴文本标签旋转
                        interval : 0,
                        rotate : -30
                    }
                }
            ],
            yAxis : [
                {
                    show : false,
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'空置间数',
                    type:'bar',
                    data:[68, 1, 1, 1, 4, 2],
                    itemStyle: {   //图表内图形的默认样式和强调样式（悬浮时样式）
                        normal: {
                            label: {
                                show: true,//是否展示
                                formatter: '{c}间',
                                textStyle: {
                                    fontSize : '12',
                                    fontFamily : '微软雅黑',
                                }
                            }
                        }
                    },
                }
            ]
        };
        myChart.setOption(option);
    }
);