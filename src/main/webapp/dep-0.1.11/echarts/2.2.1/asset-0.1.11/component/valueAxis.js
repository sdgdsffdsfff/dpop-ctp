define("echarts/component/valueAxis",["require","./base","zrender/shape/Text","zrender/shape/Line","zrender/shape/Rectangle","../config","../util/date","zrender/tool/util","../util/smartSteps","../util/accMath","../component"],function(require){function t(t,i,s,a,o,r,h){if(!h||0===h.length)return void console.err("option.series.length == 0.");e.call(this,t,i,s,a,o),this.series=h,this.grid=this.component.grid;for(var n in r)this[n]=r[n];this.refresh(a,h)}var e=require("./base"),i=require("zrender/shape/Text"),s=require("zrender/shape/Line"),a=require("zrender/shape/Rectangle"),o=require("../config");o.valueAxis={zlevel:0,z:0,show:!0,position:"left",name:"",nameLocation:"end",nameTextStyle:{},boundaryGap:[0,0],axisLine:{show:!0,onZero:!0,lineStyle:{color:"#48b",width:2,type:"solid"}},axisTick:{show:!1,inside:!1,length:5,lineStyle:{color:"#333",width:1}},axisLabel:{show:!0,rotate:0,margin:8,textStyle:{color:"#333"}},splitLine:{show:!0,lineStyle:{color:["#ccc"],width:1,type:"solid"}},splitArea:{show:!1,areaStyle:{color:["rgba(250,250,250,0.3)","rgba(200,200,200,0.3)"]}}};var r=require("../util/date"),h=require("zrender/tool/util");return t.prototype={type:o.COMPONENT_TYPE_AXIS_VALUE,_buildShape:function(){if(this._hasData=!1,this._calculateValue(),this._hasData&&this.option.show){this.option.splitArea.show&&this._buildSplitArea(),this.option.splitLine.show&&this._buildSplitLine(),this.option.axisLine.show&&this._buildAxisLine(),this.option.axisTick.show&&this._buildAxisTick(),this.option.axisLabel.show&&this._buildAxisLabel();for(var t=0,e=this.shapeList.length;e>t;t++)this.zr.addShape(this.shapeList[t])}},_buildAxisTick:function(){var t,e=this._valueList,i=this._valueList.length,a=this.option.axisTick,o=a.length,r=a.lineStyle.color,h=a.lineStyle.width;if(this.isHorizontal())for(var n,l="bottom"===this.option.position?a.inside?this.grid.getYend()-o-1:this.grid.getYend()+1:a.inside?this.grid.getY()+1:this.grid.getY()-o-1,d=0;i>d;d++)n=this.subPixelOptimize(this.getCoord(e[d]),h),t={_axisShape:"axisTick",zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{xStart:n,yStart:l,xEnd:n,yEnd:l+o,strokeColor:r,lineWidth:h}},this.shapeList.push(new s(t));else for(var p,c="left"===this.option.position?a.inside?this.grid.getX()+1:this.grid.getX()-o-1:a.inside?this.grid.getXend()-o-1:this.grid.getXend()+1,d=0;i>d;d++)p=this.subPixelOptimize(this.getCoord(e[d]),h),t={_axisShape:"axisTick",zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{xStart:c,yStart:p,xEnd:c+o,yEnd:p,strokeColor:r,lineWidth:h}},this.shapeList.push(new s(t))},_buildAxisLabel:function(){var t,e=this._valueList,s=this._valueList.length,a=this.option.axisLabel.rotate,o=this.option.axisLabel.margin,r=this.option.axisLabel.clickable,h=this.option.axisLabel.textStyle;if(this.isHorizontal()){var n,l;if("bottom"===this.option.position)n=this.grid.getYend()+o,l="top";else n=this.grid.getY()-o,l="bottom";for(var d=0;s>d;d++){if(t={zlevel:this.getZlevelBase(),z:this.getZBase()+3,hoverable:!1,style:{x:this.getCoord(e[d]),y:n,color:"function"==typeof h.color?h.color(e[d]):h.color,text:this._valueLabel[d],textFont:this.getFont(h),textAlign:h.align||"center",textBaseline:h.baseline||l}},a)t.style.textAlign=a>0?"bottom"===this.option.position?"right":"left":"bottom"===this.option.position?"left":"right",t.rotation=[a*Math.PI/180,t.style.x,t.style.y];this.shapeList.push(new i(this._axisLabelClickable(r,t)))}}else{var p,c;if("left"===this.option.position)p=this.grid.getX()-o,c="right";else p=this.grid.getXend()+o,c="left";for(var d=0;s>d;d++){if(t={zlevel:this.getZlevelBase(),z:this.getZBase()+3,hoverable:!1,style:{x:p,y:this.getCoord(e[d]),color:"function"==typeof h.color?h.color(e[d]):h.color,text:this._valueLabel[d],textFont:this.getFont(h),textAlign:h.align||c,textBaseline:h.baseline||(0===d&&""!==this.option.name?"bottom":d===s-1&&""!==this.option.name?"top":"middle")}},a)t.rotation=[a*Math.PI/180,t.style.x,t.style.y];this.shapeList.push(new i(this._axisLabelClickable(r,t)))}}},_buildSplitLine:function(){var t,e=this._valueList,i=this._valueList.length,a=this.option.splitLine,o=a.lineStyle.type,r=a.lineStyle.width,h=a.lineStyle.color;h=h instanceof Array?h:[h];var n=h.length;if(this.isHorizontal())for(var l,d=this.grid.getY(),p=this.grid.getYend(),c=0;i>c;c++)l=this.subPixelOptimize(this.getCoord(e[c]),r),t={zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{xStart:l,yStart:d,xEnd:l,yEnd:p,strokeColor:h[c%n],lineType:o,lineWidth:r}},this.shapeList.push(new s(t));else for(var g,u=this.grid.getX(),f=this.grid.getXend(),c=0;i>c;c++)g=this.subPixelOptimize(this.getCoord(e[c]),r),t={zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{xStart:u,yStart:g,xEnd:f,yEnd:g,strokeColor:h[c%n],lineType:o,lineWidth:r}},this.shapeList.push(new s(t))},_buildSplitArea:function(){var t,e=this.option.splitArea.areaStyle.color;if(!(e instanceof Array))t={zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{x:this.grid.getX(),y:this.grid.getY(),width:this.grid.getWidth(),height:this.grid.getHeight(),color:e}},this.shapeList.push(new a(t));else{var i=e.length,s=this._valueList,o=this._valueList.length;if(this.isHorizontal())for(var r,h=this.grid.getY(),n=this.grid.getHeight(),l=this.grid.getX(),d=0;o>=d;d++)r=o>d?this.getCoord(s[d]):this.grid.getXend(),t={zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{x:l,y:h,width:r-l,height:n,color:e[d%i]}},this.shapeList.push(new a(t)),l=r;else for(var p,c=this.grid.getX(),g=this.grid.getWidth(),u=this.grid.getYend(),d=0;o>=d;d++)p=o>d?this.getCoord(s[d]):this.grid.getY(),t={zlevel:this.getZlevelBase(),z:this.getZBase(),hoverable:!1,style:{x:c,y:p,width:g,height:u-p,color:e[d%i]}},this.shapeList.push(new a(t)),u=p}},_calculateValue:function(){if(isNaN(this.option.min-0)||isNaN(this.option.max-0)){for(var t,e,i={},s=this.component.legend,a=0,r=this.series.length;r>a;a++)if(this.series[a].type==o.CHART_TYPE_LINE||this.series[a].type==o.CHART_TYPE_BAR||this.series[a].type==o.CHART_TYPE_SCATTER||this.series[a].type==o.CHART_TYPE_K||this.series[a].type==o.CHART_TYPE_EVENTRIVER)if(!s||s.isSelected(this.series[a].name))if(t=this.series[a].xAxisIndex||0,e=this.series[a].yAxisIndex||0,this.option.xAxisIndex==t||this.option.yAxisIndex==e)this._calculSum(i,a);else;else;else;var h;for(var a in i){h=i[a];for(var n=0,l=h.length;l>n;n++)if(!isNaN(h[n])){this._hasData=!0,this._min=h[n],this._max=h[n];break}if(this._hasData)break}for(var a in i){h=i[a];for(var n=0,l=h.length;l>n;n++)if(!isNaN(h[n]))this._min=Math.min(this._min,h[n]),this._max=Math.max(this._max,h[n])}var d=Math.abs(this._max-this._min);if(this._min=isNaN(this.option.min-0)?this._min-Math.abs(d*this.option.boundaryGap[0]):this.option.min-0,this._max=isNaN(this.option.max-0)?this._max+Math.abs(d*this.option.boundaryGap[1]):this.option.max-0,this._min===this._max)if(0===this._max)this._max=1;else if(this._max>0)this._min=this._max/this.option.splitNumber!=null?this.option.splitNumber:5;else this._max=this._max/this.option.splitNumber!=null?this.option.splitNumber:5;"time"!=this.option.type?this._reformValue(this.option.scale):this._reformTimeValue()}else this._hasData=!0,this._min=this.option.min-0,this._max=this.option.max-0,"time"!=this.option.type?this._customerValue():this._reformTimeValue()},_calculSum:function(t,e){var i,s,a=this.series[e].name||"kener";if(!this.series[e].stack)if(t[a]=t[a]||[],this.series[e].type!=o.CHART_TYPE_EVENTRIVER){s=this.series[e].data;for(var h=0,n=s.length;n>h;h++)if(i=this.getDataFromOption(s[h]),this.series[e].type===o.CHART_TYPE_K)t[a].push(i[0]),t[a].push(i[1]),t[a].push(i[2]),t[a].push(i[3]);else if(i instanceof Array){if(-1!=this.option.xAxisIndex)t[a].push("time"!=this.option.type?i[0]:r.getNewDate(i[0]));if(-1!=this.option.yAxisIndex)t[a].push("time"!=this.option.type?i[1]:r.getNewDate(i[1]))}else t[a].push(i)}else{s=this.series[e].data;for(var h=0,n=s.length;n>h;h++)for(var l=s[h].evolution,d=0,p=l.length;p>d;d++)t[a].push(r.getNewDate(l[d].time))}else{var c="__Magic_Key_Positive__"+this.series[e].stack,g="__Magic_Key_Negative__"+this.series[e].stack;t[c]=t[c]||[],t[g]=t[g]||[],t[a]=t[a]||[],s=this.series[e].data;for(var h=0,n=s.length;n>h;h++)if(i=this.getDataFromOption(s[h]),"-"!==i){if(i-=0,i>=0)if(null!=t[c][h])t[c][h]+=i;else t[c][h]=i;else if(null!=t[g][h])t[g][h]+=i;else t[g][h]=i;if(this.option.scale)t[a].push(i)}else;}},_reformValue:function(t){var e=require("../util/smartSteps"),i=this.option.splitNumber;if(!t&&this._min>=0&&this._max>=0)this._min=0;if(!t&&this._min<=0&&this._max<=0)this._max=0;var s=e(this._min,this._max,i);i=null!=i?i:s.secs,this._min=s.min,this._max=s.max,this._valueList=s.pnts,this._reformLabelData()},_reformTimeValue:function(){var t=null!=this.option.splitNumber?this.option.splitNumber:5,e=r.getAutoFormatter(this._min,this._max,t),i=e.formatter,s=e.gapValue;this._valueList=[r.getNewDate(this._min)];var a;switch(i){case"week":a=r.nextMonday(this._min);break;case"month":a=r.nextNthOnMonth(this._min,1);break;case"quarter":a=r.nextNthOnQuarterYear(this._min,1);break;case"half-year":a=r.nextNthOnHalfYear(this._min,1);break;case"year":a=r.nextNthOnYear(this._min,1);break;default:if(72e5>=s)a=(Math.floor(this._min/s)+1)*s;else a=r.getNewDate(this._min- -s),a.setHours(6*Math.round(a.getHours()/6)),a.setMinutes(0),a.setSeconds(0)}if(a-this._min<s/2)a-=-s;for(e=r.getNewDate(a),t*=1.5;t-->=0;){if("month"==i||"quarter"==i||"half-year"==i||"year"==i)e.setDate(1);if(this._max-e<s/2)break;this._valueList.push(e),e=r.getNewDate(e- -s)}this._valueList.push(r.getNewDate(this._max)),this._reformLabelData(i)},_customerValue:function(){var t=require("../util/accMath"),e=null!=this.option.splitNumber?this.option.splitNumber:5,i=(this._max-this._min)/e;this._valueList=[];for(var s=0;e>=s;s++)this._valueList.push(t.accAdd(this._min,t.accMul(i,s)));this._reformLabelData()},_reformLabelData:function(t){this._valueLabel=[];var e=this.option.axisLabel.formatter;if(e){for(var i=0,s=this._valueList.length;s>i;i++)if("function"==typeof e)this._valueLabel.push(t?e.call(this.myChart,this._valueList[i],t):e.call(this.myChart,this._valueList[i]));else if("string"==typeof e)this._valueLabel.push(t?r.format(e,this._valueList[i]):e.replace("{value}",this._valueList[i]))}else if(t)for(var i=0,s=this._valueList.length;s>i;i++)this._valueLabel.push(r.format(t,this._valueList[i]));else for(var i=0,s=this._valueList.length;s>i;i++)this._valueLabel.push(this.numAddCommas(this._valueList[i]))},getExtremum:function(){return this._calculateValue(),{min:this._min,max:this._max}},refresh:function(t,e){if(t)this.option=this.reformOption(t),this.option.axisLabel.textStyle=h.merge(this.option.axisLabel.textStyle||{},this.ecTheme.textStyle),this.series=e;if(this.zr)this.clear(),this._buildShape()},getCoord:function(t){t=t<this._min?this._min:t,t=t>this._max?this._max:t;var e;if(!this.isHorizontal())e=this.grid.getYend()-(t-this._min)/(this._max-this._min)*this.grid.getHeight();else e=this.grid.getX()+(t-this._min)/(this._max-this._min)*this.grid.getWidth();return e},getCoordSize:function(t){if(!this.isHorizontal())return Math.abs(t/(this._max-this._min)*this.grid.getHeight());else return Math.abs(t/(this._max-this._min)*this.grid.getWidth())},getValueFromCoord:function(t){var e;if(!this.isHorizontal())t=t<this.grid.getY()?this.grid.getY():t,t=t>this.grid.getYend()?this.grid.getYend():t,e=this._max-(t-this.grid.getY())/this.grid.getHeight()*(this._max-this._min);else t=t<this.grid.getX()?this.grid.getX():t,t=t>this.grid.getXend()?this.grid.getXend():t,e=this._min+(t-this.grid.getX())/this.grid.getWidth()*(this._max-this._min);return e.toFixed(2)-0},isMaindAxis:function(t){for(var e=0,i=this._valueList.length;i>e;e++)if(this._valueList[e]===t)return!0;return!1}},h.inherits(t,e),require("../component").define("valueAxis",t),t});