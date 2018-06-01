/*
* @module:  集中整租
* @time:  20180508
* @name:  liangbin
* */
//切换房态图
$('.subTopBox').on('click','.JS_switch',function(){
    var stateTxt = $(this).data('state');
    if(parseInt(stateTxt)  == 1){
        $(this).data('state','0');
        $(this).text('切换至列表');
        $('#roomList').addClass('hide');
        $('#roomState').removeClass('hide');
    }else{
        $(this).data('state','1');
        $(this).text('切换至房态图');
        $('#roomList').removeClass('hide');
        $('#roomState').addClass('hide');
    }
});
//登记房源-项目基本信息录入
$('.subTopBox').on('click','.masterInfo',function(){
    var index = layer.open({
        type: 2,
        title: '项目基本信息录入',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        maxmin: true,
        resize: false,
        area: ['740px','550px'],
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'qft_addroomInfo.html'
    });
    layer.iframeAuto(index)
});
//登记租客-项目基本信息录入
$('.subTopBox').on('click','.roomInfo',function(){
    layer.open({
        type: 2,
        title: '项目基本信息录入',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['740px','560px'],
        //btn:['关闭'],
        btnAlign: 'c',
        content: ''
    });
});
//查看房间信息
$('.wareList').on('dblclick','tbody tr',function(){
    layer.open({
        type: 2,
        title: '百康年世纪门A幢3单元4-12',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['900px','500px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'details/roomInfo.html'
    });
});
/*
* @module: 集中整租-房东操作
* @time:  20180531
* @name:  liangbin
*
*/
//修改房源-租赁他人物业
$('.wareList').on('click','.JS_modifyRoom', function(){
    layer.open({
        type: 2,
        title: '修改房源',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['900px','64%'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/modifyRoom.html'
    });
});
//修改房源-自持物业
$('.wareList').on('click','.JS_modifyRoom_self', function(){
    layer.open({
        type: 2,
        title: '修改房源',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['900px','560px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/modifyRoom_self.html'
    });
});
//添加欠费
$('.wareList').on('click', '.JS_addArrears',function(){
    layer.open({
        type: 2,
        title: '添加欠费',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['900px','364px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/qft_addArrears.html'
    });
});
//房源退房
$('.wareList').on('click', '.JS_CheckOut', function(){
    layer.open({
        type: 2,
        title: '房源退房',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['760px','510px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/qft_checkOut.html'
    });
});
//添加宽带
$('.wareList').on('click', '.JS_addNetwork', function(){
    layer.open({
        type: 2,
        title: '添加宽带',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['760px','490px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/qft_addNetwork.html'
    });
});
//添加备忘信息
$('.wareList').on('click', '.JS_addNote', function(){
    layer.open({
        type: 2,
        title: '添加备忘信息',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['760px','360px'],
        resize: false,
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'homeOperation/qft_addNote.html'
    });
});