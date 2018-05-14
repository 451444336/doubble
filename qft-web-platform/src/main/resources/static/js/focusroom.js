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
//房间、房东信息切换
$('.subTopBox').on('click','.subTopBtn',function(){
    var infoTxt = $(this).data('info');
    $(this).addClass('subHover').siblings().removeClass('subHover');
    if(parseInt(infoTxt) == 0){
        $('.roomState').fadeIn();
        $('.register').addClass('roomInfo').text('登记房源').removeClass('masterInfo');
    }else{
        $('.roomState').fadeOut();
        $('.register').addClass('masterInfo').text('登记房东').removeClass('roomInfo');
    }
});
//登记房源-项目基本信息录入
$('.subTopBox').on('click','.roomInfo',function(){
    layer.open({
        type: 2,
        title: '项目基本信息录入',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['740px','550px'],
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'qft_addroomInfo.html'
    });
});
//登记房东-项目基本信息录入
$('.subTopBox').on('click','.masterInfo',function(){
    layer.open({
        type: 2,
        title: '项目基本信息录入',
        skin: 'popup-title-bg', //样式类名
        closeBtn : 1,
        area: ['740px','560px'],
        //btn:['关闭'],
        btnAlign: 'c',
        content: 'qft_recordList.html'
    });
});