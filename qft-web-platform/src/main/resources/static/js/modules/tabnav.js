/** index.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */

var tab;

layui.config({
	base: '../static/js/modules/',
	version:new Date().getTime()
}).use(['layer', 'tab'], function() {
	var tab = layui.tab({
			elem: '.admin-nav-card', //设置选项卡容器
			maxSetting: {
				max: 6,
				tipMsg: '只能开5个哇，不能再开了。'
			},
			contextMenu:true
		});
    //iframe自适应
    $(window).on('resize', function() {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 80);
        $content.find('.layui-tab-item').each(function() {
            $(this).height($content.height());
        });
    }).resize();
	$('#accordion').find('dd').each(function() {
		var tabTit = $('.layui-tab-title');
		var tabCon = $('.layui-tab-content');
		$(this).on('click', function() {
			var $a = $(this).children('a');
			var href = $a.data('url');
			var title = $a.text();
			var data = {
				elem: $a,
				field: {
					href: href,
					title: title
				}
			};
            if(tabTit.find('li').length == 6 && tabCon.find('.layui-tab-item').length == 6){
                var liTxt = tabTit.find('li span').text();
                if (liTxt.indexOf(title) > 0){}else{
                    tabTit.find('li').eq(1).remove();
                    tabCon.find('.layui-tab-item').eq(1).remove();
                }
            }
            tab.tabAdd(data.field);
		});
	});
});
