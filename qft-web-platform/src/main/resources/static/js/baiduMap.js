var map = new BMap.Map("village");
var point = new BMap.Point(116.400244,39.92556);
map.centerAndZoom(point, 12);

function myFun(result){
    var cityName = result.name;
    map.setCenter(cityName);
    alert("当前定位城市:"+cityName);
}
var myCity = new BMap.LocalCity();
myCity.get(myFun);

var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r){
    if(this.getStatus() == BMAP_STATUS_SUCCESS){
        var mk = new BMap.Marker(r.point);
        map.addOverlay(mk);
        map.panTo(r.point);
        map.enableScrollWheelZoom();
        mk.enableDragging();             //是否可拖拽
        alert('您的位置：'+r.point.lng+','+r.point.lat);
    }
    else {
        alert('failed'+this.getStatus());
    }
},{enableHighAccuracy: true})