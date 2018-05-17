var map = new BMap.Map("village");
var lngMap = $('#lng').val();
var latMap = $('#lat').val();
var point = new BMap.Point(lngMap,latMap);
map.centerAndZoom(point, 14);   // 初始化地图,设置城市和地图级别。
map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放

var geoc = new BMap.Geocoder();
var marker = new BMap.Marker(point);  // 创建标注
map.addOverlay(marker);
marker.enableDragging();             //是否可拖拽
marker.addEventListener("dragend", function(e){
    var pt = e.point;
    var dizhi;
    console.log(e);
    geoc.getLocation(pt, function(rs){
        var addComp = rs.addressComponents;
        console.log(rs);
        dizhi = addComp.city + addComp.district + addComp.street + addComp.streetNumber;
        document.getElementById('suggestId').value = rs.address;//更新地址数据
        document.getElementById('lng').value = rs.point.lng;//更新地理经度
        document.getElementById('lat').value = rs.point.lat;//更新地理纬度
        //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    });
});