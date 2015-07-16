define("echarts/util/mapData/params",["require"],function(require){function e(e){if(!e.UTF8Encoding)return e;for(var i=e.features,n=0;n<i.length;n++)for(var a=i[n],o=a.geometry.coordinates,s=a.geometry.encodeOffsets,l=0;l<o.length;l++){var r=o[l];if("Polygon"===a.geometry.type)o[l]=t(r,s[l]);else if("MultiPolygon"===a.geometry.type)for(var V=0;V<r.length;V++){var U=r[V];r[V]=t(U,s[l][V])}}return e.UTF8Encoding=!1,e}function t(e,t){for(var i=[],n=t[0],a=t[1],o=0;o<e.length;o+=2){var s=e.charCodeAt(o)-64,l=e.charCodeAt(o+1)-64;s=s>>1^-(1&s),l=l>>1^-(1&l),s+=n,l+=a,n=s,a=l,i.push([s/1024,l/1024])}return i}var i={none:{getGeoJson:function(e){e({type:"FeatureCollection",features:[{type:"Feature",geometry:{coordinates:[],encodeOffsets:[],type:"Polygon"},properties:{}}]})}},world:{getGeoJson:function(t){require(["./geoJson/world_geo"],function(i){t(e(i))})}},china:{getGeoJson:function(t){require(["./geoJson/china_geo"],function(i){t(e(i))})}},"南海诸岛":{textCoord:[126,25],getPath:function(e,t){for(var i=[[[0,3.5],[7,11.2],[15,11.9],[30,7],[42,.7],[52,.7],[56,7.7],[59,.7],[64,.7],[64,0],[5,0],[0,3.5]],[[13,16.1],[19,14.7],[16,21.7],[11,23.1],[13,16.1]],[[12,32.2],[14,38.5],[15,38.5],[13,32.2],[12,32.2]],[[16,47.6],[12,53.2],[13,53.2],[18,47.6],[16,47.6]],[[6,64.4],[8,70],[9,70],[8,64.4],[6,64.4]],[[23,82.6],[29,79.8],[30,79.8],[25,82.6],[23,82.6]],[[37,70.7],[43,62.3],[44,62.3],[39,70.7],[37,70.7]],[[48,51.1],[51,45.5],[53,45.5],[50,51.1],[48,51.1]],[[51,35],[51,28.7],[53,28.7],[53,35],[51,35]],[[52,22.4],[55,17.5],[56,17.5],[53,22.4],[52,22.4]],[[58,12.6],[62,7],[63,7],[60,12.6],[58,12.6]],[[0,3.5],[0,93.1],[64,93.1],[64,0],[63,0],[63,92.4],[1,92.4],[1,3.5],[0,3.5]]],n="",a=e[0],o=e[1],s=0,l=i.length;l>s;s++){n+="M "+((i[s][0][0]*t+a).toFixed(2)-0)+" "+((i[s][0][1]*t+o).toFixed(2)-0)+" ";for(var r=1,V=i[s].length;V>r;r++)n+="L "+((i[s][r][0]*t+a).toFixed(2)-0)+" "+((i[s][r][1]*t+o).toFixed(2)-0)+" "}return n+" Z"}},"新疆":{getGeoJson:function(t){require(["./geoJson/xin_jiang_geo"],function(i){t(e(i))})}},"西藏":{getGeoJson:function(t){require(["./geoJson/xi_zang_geo"],function(i){t(e(i))})}},"内蒙古":{getGeoJson:function(t){require(["./geoJson/nei_meng_gu_geo"],function(i){t(e(i))})}},"青海":{getGeoJson:function(t){require(["./geoJson/qing_hai_geo"],function(i){t(e(i))})}},"四川":{getGeoJson:function(t){require(["./geoJson/si_chuan_geo"],function(i){t(e(i))})}},"黑龙江":{getGeoJson:function(t){require(["./geoJson/hei_long_jiang_geo"],function(i){t(e(i))})}},"甘肃":{getGeoJson:function(t){require(["./geoJson/gan_su_geo"],function(i){t(e(i))})}},"云南":{getGeoJson:function(t){require(["./geoJson/yun_nan_geo"],function(i){t(e(i))})}},"广西":{getGeoJson:function(t){require(["./geoJson/guang_xi_geo"],function(i){t(e(i))})}},"湖南":{getGeoJson:function(t){require(["./geoJson/hu_nan_geo"],function(i){t(e(i))})}},"陕西":{getGeoJson:function(t){require(["./geoJson/shan_xi_1_geo"],function(i){t(e(i))})}},"广东":{getGeoJson:function(t){require(["./geoJson/guang_dong_geo"],function(i){t(e(i))})}},"吉林":{getGeoJson:function(t){require(["./geoJson/ji_lin_geo"],function(i){t(e(i))})}},"河北":{getGeoJson:function(t){require(["./geoJson/he_bei_geo"],function(i){t(e(i))})}},"湖北":{getGeoJson:function(t){require(["./geoJson/hu_bei_geo"],function(i){t(e(i))})}},"贵州":{getGeoJson:function(t){require(["./geoJson/gui_zhou_geo"],function(i){t(e(i))})}},"山东":{getGeoJson:function(t){require(["./geoJson/shan_dong_geo"],function(i){t(e(i))})}},"江西":{getGeoJson:function(t){require(["./geoJson/jiang_xi_geo"],function(i){t(e(i))})}},"河南":{getGeoJson:function(t){require(["./geoJson/he_nan_geo"],function(i){t(e(i))})}},"辽宁":{getGeoJson:function(t){require(["./geoJson/liao_ning_geo"],function(i){t(e(i))})}},"山西":{getGeoJson:function(t){require(["./geoJson/shan_xi_2_geo"],function(i){t(e(i))})}},"安徽":{getGeoJson:function(t){require(["./geoJson/an_hui_geo"],function(i){t(e(i))})}},"福建":{getGeoJson:function(t){require(["./geoJson/fu_jian_geo"],function(i){t(e(i))})}},"浙江":{getGeoJson:function(t){require(["./geoJson/zhe_jiang_geo"],function(i){t(e(i))})}},"江苏":{getGeoJson:function(t){require(["./geoJson/jiang_su_geo"],function(i){t(e(i))})}},"重庆":{getGeoJson:function(t){require(["./geoJson/chong_qing_geo"],function(i){t(e(i))})}},"宁夏":{getGeoJson:function(t){require(["./geoJson/ning_xia_geo"],function(i){t(e(i))})}},"海南":{getGeoJson:function(t){require(["./geoJson/hai_nan_geo"],function(i){t(e(i))})}},"台湾":{getGeoJson:function(t){require(["./geoJson/tai_wan_geo"],function(i){t(e(i))})}},"北京":{getGeoJson:function(t){require(["./geoJson/bei_jing_geo"],function(i){t(e(i))})}},"天津":{getGeoJson:function(t){require(["./geoJson/tian_jin_geo"],function(i){t(e(i))})}},"上海":{getGeoJson:function(t){require(["./geoJson/shang_hai_geo"],function(i){t(e(i))})}},"香港":{getGeoJson:function(t){require(["./geoJson/xiang_gang_geo"],function(i){t(e(i))})}},"澳门":{getGeoJson:function(t){require(["./geoJson/ao_men_geo"],function(i){t(e(i))})}}};return{decode:e,params:i}});