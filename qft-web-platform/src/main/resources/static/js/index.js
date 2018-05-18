$(function(){
    //禁止鼠标右键
    $(document).bind("contextmenu",function(e){
        return false;
    });
    //左侧导航
    var Accordion = function(el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;
        // Variables privadas
        var links = this.el.find('.link_conTxt');
        var submenu = this.el.find('.subMenu').find('a');
        //默认第一个二级菜单显示
        var openMenu = this.el.find('li').hasClass('openMenu');
        if(openMenu){
            $('.openMenu .subMenu').slideToggle();
        }
        // Evento
        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        submenu.on('click',function(){
            $(this).addClass('action').parent('dd').siblings().find('a').removeClass('action');
            $(this).addClass('action').parents('.navMenu li').siblings().find('a').removeClass('action');
        });
    };
    Accordion.prototype.dropdown = function(e) {
        var $el = e.data.el;
        $this = $(this);
        $next = $this.parents('.link_tit').next('.subMenu');
        $next.slideToggle();
        if($next.length != 0){
            $this.parents('li').toggleClass('openMenu');
        }
        if (!e.data.multiple) {
            $el.find('.subMenu').not($next).slideUp().parent().removeClass('openMenu');
        }
    };
   new Accordion($('#accordion'), false);
   //监听浏览器分辨率改变菜单显示
   $(window).on('resize',function(){
       var width = $(this).width();
       if(width < 1920){
           $('.mainBox').addClass('main-shrink');
           $('.navMenu').addClass('mouseMenu');
           $('.navMenu').find('li.openMenu .subMenu').hide();
           $('.mainContent').animate({
               left: '70px'
           },100);
           //$('.mouseMenu li').eq(-1).find('.subMenu').addClass('subBottom');
       }else{
           $('.mainBox').removeClass('main-shrink');
           $('.navMenu').removeClass('mouseMenu');
           $('.navMenu').find('li.openMenu .subMenu').show();
           $('.mainContent').animate({
               left: '128px'
           },100);
           //$('.navMenu li').eq(-1).find('.subMenu').removeClass('subBottom');
       }
   }).resize();
   //左侧收缩展开
    $('.leftNav').on('click','.openBtn',function(){
        if($('.mainBox').hasClass('main-shrink')){
            $('.mainBox').removeClass('main-shrink');
            $('.mainContent').animate({
                left: '128px'
            },100);
            $('.navMenu').find('li.openMenu .subMenu').show();
            $('.navMenu').removeClass('mouseMenu');
            //$('.navMenu li').eq(-1).find('.subMenu').removeClass('subBottom');
        }else{
            $('.mainBox').addClass('main-shrink');
            $('.mainContent').animate({
                left: '70px'
            },100);
            $('.navMenu').find('li.openMenu .subMenu').hide();
            $('.navMenu').addClass('mouseMenu');
            //$('.mouseMenu li').eq(-1).find('.subMenu').addClass('subBottom');
        }
    });
    //收缩展开按钮
    //收缩展开按钮
    $('.leftNav').hover(function(){
        $('.openBtn').show();
    },function(){
        $('.openBtn').hide();
    });
    //窄菜单时，鼠标悬停显示
    $('.leftNav').on('mouseover mouseout', '.mouseMenu li', function(event){
        if(event.type == "mouseover"){
            $(this).find('.subMenu').show();
        }else if(event.type == "mouseout"){
            $(this).find('.subMenu').hide();
        }
    });
    //右侧收缩展开
    $('.rightContent').on('click', '.shrinkBtn', function(){
        if($('.rightContent').hasClass('spreadBg')){
            $('.rightContent').removeClass('spreadBg');
            $('.rightContent').animate({
                right: '0'
            },100);
            $('.focusLeftCon').animate({
                marginRight: '274px'
            },100);
        }else{
            $('.rightContent').addClass('spreadBg');
            $('.rightContent').animate({
                right: '-260px'
            },100);
            $('.focusLeftCon').animate({
                marginRight: '15px'
            },100);
        }

    });
    //监听浏览分辨率改变布局
    $(window).on('resize',function(){
        var width = $(this).width();
        if(width < 1400){
            $('.rightContent').addClass('spreadBg');
            $('.rightContent').animate({
                right: '-260px'
            },100);
            $('.focusLeftCon').animate({
                marginRight: '15px'
            },100);
        }else{
            $('.rightContent').removeClass('spreadBg');
            $('.rightContent').animate({
                right: '0'
            },100);
            $('.focusLeftCon').animate({
                marginRight: '274px'
            },100);
        }
    }).resize();
    //项目搜索、经营数据、推广数据展开收缩
    /*$('.rightContent').on('click','.titOpenBtn',function(){
        $('.openPublicBox').hide();
        if($('.titSearchIco').hasClass('icoHide')){
            $('.titSearchIco').removeClass('icoHide');
        }
        if($('.openRicon').hasClass('upIcon')){
            $('.openRicon').removeClass('upIcon');
            $('.openRicon').text('展开');
        }
        $(this).parents('.rightPublic').find('.titSearchIco').addClass('icoHide');
        $(this).parents('.rightPublic').find('.openPublicBox').show();
        $(this).parents('.rightPublic').find('.openRicon').addClass('upIcon').text('折叠');
    });*/
    //搜索图标点击事件
    $('.rightPublic').on('click', '.titSearchIco', function(){
        $(this).parents('.rightPublic').find('.searchInput').slideToggle();
    });
    //集中整租折叠按钮
    $('.rightContent').on('click','.openRicon',function(){
        var foldtxt = $(this).data('fold');
        if(foldtxt == '0'){
            $(this).data('fold','1');
            $(this).removeClass('upIcon').text('展开');
            $(this).parents('.rightPublic').find('.openPublicBox').hide();
        }else{
            $(this).data('fold','0');
            $(this).addClass('upIcon').text('折叠');
            $(this).parents('.rightPublic').find('.openPublicBox').show();
        }
    });
    //管理员弹出列表
    $('.subHeadMenu').hover(function(){
        $('.subHeadMenuList').show();
    },function(){
        $('.subHeadMenuList').hide();
    });
    //table列表操作按钮
    $('.wareList').on('click', '.operationBtn', function(event){
        //取消事件冒泡
        event.stopPropagation();
        if($(this).siblings('.operationList').hasClass('hide')){
            $(this).siblings('.operationList').removeClass('hide');
        }else{
            $(this).siblings('.operationList').addClass('hide');
        }
        $(this).parents('tr').siblings('tr').find('.operationList').addClass('hide');
        return false;
    });
    //table列表点击空白处隐藏弹出层，下面为滑动消失效果和淡出消失效果。
    $(document).click(function(event){
        var _con = $('.operationList');   // 设置目标区域
        if(!_con.is(event.target) && _con.has(event.target).length === 0){ // Mark 1
            //$('#divTop').slideUp('slow');   //滑动消失
            $('.operationList').addClass('hide');          //淡出消失
        }
    });
    //table列表操作二级菜单
    $('.operationList li:first-child').find('.subOperMenu').show();
    $('.wareList').on('mouseenter',' li',function(){
        $(this).parents('.operationList').find('.js_operMenu').removeClass('operHover');
        $(this).parents('.operationList').find('.subOperMenu').hide();
        $(this).find('.subOperMenu').show();
        $(this).find('.js_operMenu').addClass('operHover');
    });
    //table 隔行变色
    $(".wareList tr:nth-child(even)").addClass("trBack");
    //列表全选
    $('.wareList').on('click', '.check-all', function () {
        $('.ids').prop('checked', this.checked);
        if ($('.ids:disabled').prop('disabled')) {
            $('.ids:disabled').prop('checked', false);
        }
    });
    //全选/全不选
    $('.wareList').on('click', '.mitAll', function () {
        $('.ids').prop('checked', true);
        $('.check-all').prop('checked', true);
        if ($('.ids:disabled').prop('disabled')) {
            $('.ids:disabled').prop('checked', false);
        }
    });
    $('.wareList').on('click', '.ids', function () {
        var option = $('.ids');
        var Cnum = option.not(":disabled").size();
        var count = 0;
        option.each(function (i) {
            if ($(this).is(':checked')) {
                count++;
            }
        });
        if (Cnum == count) {
            $('.check-all').prop('checked', true);
        } else {
            $('.check-all').prop('checked', false);
        }
    });
    //更多搜索条件
    $('.searchTerm').on('click', '.JS_moreBtn', function(){
        $('.searchMore').fadeToggle();
    });
});