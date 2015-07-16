define("echarts/chart/scatter",["require","./base","../util/shape/Symbol","../component/axis","../component/grid","../component/dataZoom","../component/dataRange","../config","zrender/tool/util","zrender/tool/color","../chart"],function(require){function e(e,i,a,s,r){t.call(this,e,i,a,s,r),this.refresh(s)}var t=require("./base"),i=require("../util/shape/Symbol");require("../component/axis"),require("../component/grid"),require("../component/dataZoom"),require("../component/dataRange");var a=require("../config");a.scatter={zlevel:0,z:2,clickable:!0,legendHoverLink:!0,xAxisIndex:0,yAxisIndex:0,symbolSize:4,large:!1,largeThreshold:2e3,itemStyle:{normal:{label:{show:!1}},emphasis:{label:{show:!1}}}};var s=require("zrender/tool/util"),r=require("zrender/tool/color");return e.prototype={type:a.CHART_TYPE_SCATTER,_buildShape:function(){var e=this.series;this._sIndex2ColorMap={},this._symbol=this.option.symbolList,this._sIndex2ShapeMap={},this.selectedMap={},this.xMarkMap={};for(var t,i,s,o,l=this.component.legend,n=[],h=0,d=e.length;d>h;h++)if(t=e[h],i=t.name,t.type===a.CHART_TYPE_SCATTER){if(e[h]=this.reformOption(e[h]),this.legendHoverLink=e[h].legendHoverLink||this.legendHoverLink,this._sIndex2ShapeMap[h]=this.query(t,"symbol")||this._symbol[h%this._symbol.length],l){if(this.selectedMap[i]=l.isSelected(i),this._sIndex2ColorMap[h]=r.alpha(l.getColor(i),.5),s=l.getItemShape(i)){var o=this._sIndex2ShapeMap[h];if(s.style.brushType=o.match("empty")?"stroke":"both",o=o.replace("empty","").toLowerCase(),o.match("rectangle"))s.style.x+=Math.round((s.style.width-s.style.height)/2),s.style.width=s.style.height;if(o.match("star"))s.style.n=o.replace("star","")-0||5,o="star";if(o.match("image"))s.style.image=o.replace(new RegExp("^image:\\/\\/"),""),s.style.x+=Math.round((s.style.width-s.style.height)/2),s.style.width=s.style.height,o="image";s.style.iconType=o,l.setItemShape(i,s)}}else this.selectedMap[i]=!0,this._sIndex2ColorMap[h]=r.alpha(this.zr.getColor(h),.5);if(this.selectedMap[i])n.push(h)}this._buildSeries(n),this.addShapeList()},_buildSeries:function(e){if(0!==e.length){for(var t,i,a,s,r,o,l,n,h=this.series,d={},p=0,c=e.length;c>p;p++)if(t=e[p],i=h[t],0!==i.data.length){r=this.component.xAxis.getAxis(i.xAxisIndex||0),o=this.component.yAxis.getAxis(i.yAxisIndex||0),d[t]=[];for(var u=0,g=i.data.length;g>u;u++)if(a=i.data[u],s=this.getDataFromOption(a,"-"),!("-"===s||s.length<2))l=r.getCoord(s[0]),n=o.getCoord(s[1]),d[t].push([l,n,u,a.name||""]);else;this.xMarkMap[t]=this._markMap(r,o,i.data,d[t]),this.buildMark(t)}else;this._buildPointList(d)}},_markMap:function(e,t,i,a){for(var s,r={min0:Number.POSITIVE_INFINITY,max0:Number.NEGATIVE_INFINITY,sum0:0,counter0:0,average0:0,min1:Number.POSITIVE_INFINITY,max1:Number.NEGATIVE_INFINITY,sum1:0,counter1:0,average1:0},o=0,l=a.length;l>o;o++){if(s=i[a[o][2]].value||i[a[o][2]],r.min0>s[0])r.min0=s[0],r.minY0=a[o][1],r.minX0=a[o][0];if(r.max0<s[0])r.max0=s[0],r.maxY0=a[o][1],r.maxX0=a[o][0];if(r.sum0+=s[0],r.counter0++,r.min1>s[1])r.min1=s[1],r.minY1=a[o][1],r.minX1=a[o][0];if(r.max1<s[1])r.max1=s[1],r.maxY1=a[o][1],r.maxX1=a[o][0];r.sum1+=s[1],r.counter1++}var n=this.component.grid.getX(),h=this.component.grid.getXend(),d=this.component.grid.getY(),p=this.component.grid.getYend();r.average0=r.sum0/r.counter0;var c=e.getCoord(r.average0);r.averageLine0=[[c,p],[c,d]],r.minLine0=[[r.minX0,p],[r.minX0,d]],r.maxLine0=[[r.maxX0,p],[r.maxX0,d]],r.average1=r.sum1/r.counter1;var u=t.getCoord(r.average1);return r.averageLine1=[[n,u],[h,u]],r.minLine1=[[n,r.minY1],[h,r.minY1]],r.maxLine1=[[n,r.maxY1],[h,r.maxY1]],r},_buildPointList:function(e){var t,i,a,s,r=this.series;for(var o in e)if(t=r[o],i=e[o],!(t.large&&t.data.length>t.largeThreshold))for(var l=0,n=i.length;n>l;l++)a=i[l],s=this._getSymbol(o,a[2],a[3],a[0],a[1]),s&&this.shapeList.push(s);else this.shapeList.push(this._getLargeSymbol(i,this.getItemStyleColor(this.query(t,"itemStyle.normal.color"),o,-1)||this._sIndex2ColorMap[o]))},_getSymbol:function(e,t,i,a,s){var r,o=this.series,l=o[e],n=l.data[t],h=this.component.dataRange;if(h){if(r=isNaN(n[2])?this._sIndex2ColorMap[e]:h.getColor(n[2]),!r)return null}else r=this._sIndex2ColorMap[e];var d=this.getSymbolShape(l,e,n,t,i,a,s,this._sIndex2ShapeMap[e],r,"rgba(0,0,0,0)","vertical");return d.zlevel=this.getZlevelBase(),d.z=this.getZBase(),d._main=!0,d},_getLargeSymbol:function(e,t){return new i({zlevel:this.getZlevelBase(),z:this.getZBase(),_main:!0,hoverable:!1,style:{pointList:e,color:t,strokeColor:t},highlightStyle:{pointList:[]}})},getMarkCoord:function(e,t){var i,a=this.series[e],s=this.xMarkMap[e],r=this.component.xAxis.getAxis(a.xAxisIndex),o=this.component.yAxis.getAxis(a.yAxisIndex);if(t.type&&("max"===t.type||"min"===t.type||"average"===t.type)){var l=null!=t.valueIndex?t.valueIndex:1;i=[s[t.type+"X"+l],s[t.type+"Y"+l],s[t.type+"Line"+l],s[t.type+l]]}else i=["string"!=typeof t.xAxis&&r.getCoordByIndex?r.getCoordByIndex(t.xAxis||0):r.getCoord(t.xAxis||0),"string"!=typeof t.yAxis&&o.getCoordByIndex?o.getCoordByIndex(t.yAxis||0):o.getCoord(t.yAxis||0)];return i},refresh:function(e){if(e)this.option=e,this.series=e.series;this.backupShapeList(),this._buildShape()},ondataRange:function(e,t){if(this.component.dataRange)this.refresh(),t.needRefresh=!0}},s.inherits(e,t),require("../chart").define("scatter",e),e});