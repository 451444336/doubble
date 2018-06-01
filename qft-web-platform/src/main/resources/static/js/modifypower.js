$(function(){

    //初始化
    $('.powerSubMeun').first().show().siblings().hide();
    showSubMeun($(".mainMeun li:eq(0)")[0].id);
    //$('#powerBtn').html("<input class=\"powerBtn\" type=\"button\" value=\"全选查看权限\" onclick=\"allSelectByTpye(this,'search')\"><input class=\"powerBtn\" type=\"button\" value=\"全选操作权限\" onclick=\"allSelectByTpye(this,'hand')\"><input class=\"powerBtn\" type=\"button\" value=\"全选删除权限\" onclick=\"allSelectByTpye(this,'delete')\">");
    //一级菜单点击事件
    $('.mainMeun').on('click','li',function(){
        var id = this.id;
        $(this).addClass('meunHover').siblings('li').removeClass('meunHover');
        $('.powerSubMeun li').removeClass('subMeunHover');
        showSubMeun(id);
        //$('#powerBtn').html("<input class=\"powerBtn\" type=\"button\" value=\"全选查看权限\" onclick=\"allSelectByTpye(this,'search')\"><input class=\"powerBtn\" type=\"button\" value=\"全选操作权限\" onclick=\"allSelectByTpye(this,'hand')\"><input class=\"powerBtn\" type=\"button\" value=\"全选删除权限\" onclick=\"allSelectByTpye(this,'delete')\">")
    });
    //二级菜单点击事件
    $('.powerSubMeun').on('click','li',function(){
        $(this).addClass('subMeunHover').siblings('li').removeClass('subMeunHover');
        var subId = this.id;
        showSubMeunTwo(subId);
        //$('#powerBtn').html("<input class=\"powerBtn\" type=\"button\" value=\"全选查看权限\" onclick=\"allSelectByTpye(this,'searchTwo')\"><input class=\"powerBtn\" type=\"button\" value=\"全选操作权限\" onclick=\"allSelectByTpye(this,'handTwo')\"><input class=\"powerBtn\" type=\"button\" value=\"全选删除权限\" onclick=\"allSelectByTpye(this,'deleteTwo')\">")
    });
});
//查询二级菜单与权限
function showSubMeun(id){
    var subMeun = $('.powerSubMeun');
    var powerList = $('.powerOption');
    //二级菜单展示
    for (var i = 0; i < subMeun.length; i++){
        if(subMeun[i].className.indexOf(id)>0){
            subMeun[i].style.display = "block";
        }else{
            subMeun[i].style.display = "none";
        }
    }
    //一级菜单下的所有权限展示
    for (var i = 0; i < powerList.length; i++){
        if(powerList[i].className.indexOf(id)>0){
            powerList[i].style.display = "block";
        }else{
            powerList[i].style.display = "none";
        }
    }
}
//二级菜单对应的权限
function showSubMeunTwo(id){
    var subMeunTwo = $('.powerOption');
    //二级菜单展示
    for (var i = 0; i < subMeunTwo.length; i++){
        if(subMeunTwo[i].className.indexOf(id)>0){
            console.log(subMeunTwo[i]);
            subMeunTwo[i].style.display = "block";
        }else{
            subMeunTwo[i].style.display = "none";
        }
    }
}

//全选操作
function allSelectByTpye(obj,type){
    $("#box_"+type).click();
    var isCheck = $("#box_"+type).is(':checked');
    if('checked' == isCheck || isCheck){
        isCheck = true;
    } else {
        isCheck = false;
    }
    //console.log(isCheck);
    var dds = $(".powerOption:visible");
    //var ids = "";
    for(var i = 0; i < dds.length; i++){
        var id = $(dds)[i].id;
        console.log(id);
        id = id.substring(id.indexOf("_")+1,id.length);
        console.log(id);
        //ids += id+",";
        //console.log(ids);
        if(type == "search"){
            $(dds[i]).find(".commonly_checkbox").prop("checked",isCheck);
            $(".commonly_"+id).prop("checked",isCheck);
            /*if('admin' != "system"){
                if(id == "201502050005" || id == "201502050003"){
                    var commonly = $(".commonly_"+id);
                    if(isCheck){
                        var len = $(".checkHouse").length;
                        if(len > 1){
                            for(var i=0;i<commonly.length;i++){
                                if(commonly[i].id != "Menu_201507160003" && commonly[i].id != "Menu_20150907000001"
                                    && commonly[i].id != "Menu_201507170009" && commonly[i].id != "Menu_20170930145532JMbeCT"){
                                    commonly[i].checked = isCheck;
                                }
                            }
                            Dialog.alert("储备房源查看自己，查看所有2个权限只能勾选一个，用户设置查看全部店面员工详情，查看本店面员工详情2个权限只能勾选一个，请手动勾选。");
                        } else {
                            for(var i=0;i<commonly.length;i++){
                                commonly[i].checked = isCheck;
                            }
                        }
                    } else {
                        for(var j=0;j<commonly.length;j++){
                            commonly[j].checked = isCheck;
                        }
                    }
                } else {
                    $(".commonly_"+id).attr("checked",isCheck);
                }
            } else {
                $(".commonly_"+id).attr("checked",isCheck);
            }*/
        } else if(type == "hand"){
            $(dds[i]).find(".hand_checkbox").attr("checked",isCheck);
            $(".hand_"+id).attr("checked",isCheck);
            /*if('admin' != "system"){
                if(id == "Menu_201503070001"){
                    var hand = $(".hand_"+id);
                    if(isCheck){
                        var len = $(".checkFinance").length;
                        if(len > 1){
                            for(var j=0;j<hand.length;j++){
                                if(hand[j].id != "Menu_20151029000001" && hand[j].id != "Menu_20170724111420J0w8kH" && hand[j].id != "Menu_20170724111457JyDhTZ"){
                                    hand[j].checked = isCheck;
                                }
                            }
                            Dialog.alert("复核所有金额，复核除房租外1000以下，复核除房租外3000以下此3个权限只能勾选一个，请手动勾选。");
                        } else {
                            for(var j=0;j<hand.length;j++){
                                hand[j].checked = isCheck;
                            }
                        }
                    } else {
                        for(var j=0;j<hand.length;j++){
                            hand[j].checked = isCheck;
                        }
                    }
                } else {
                    $(".hand_"+id).attr("checked",isCheck);
                }
            } else {
                $(".hand_"+id).attr("checked",isCheck);
            }*/
        } else {
            $(dds[i]).find(".important_checkbox").attr("checked",isCheck);
            $(".important_"+id).attr("checked",isCheck);
        }
    }
}