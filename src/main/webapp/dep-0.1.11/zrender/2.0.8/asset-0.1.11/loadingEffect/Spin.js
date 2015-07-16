define("zrender/loadingEffect/Spin",["require","./Base","../tool/util","../tool/color","../tool/area","../shape/Sector"],function(require){function e(e){t.call(this,e)}var t=require("./Base"),i=require("../tool/util"),n=require("../tool/color"),a=require("../tool/area"),r=require("../shape/Sector");return i.inherits(e,t),e.prototype._start=function(e,t){var o=i.merge(this.options,{textStyle:{color:"#fff",textAlign:"start"},backgroundColor:"rgba(0, 0, 0, 0.8)"}),s=this.createTextShape(o.textStyle),l=10,h=a.getTextWidth(s.highlightStyle.text,s.highlightStyle.textFont),d=a.getTextHeight(s.highlightStyle.text,s.highlightStyle.textFont),u=i.merge(this.options.effect||{},{r0:9,r:15,n:18,color:"#fff",timeInterval:100}),c=this.getLocation(this.options.textStyle,h+l+2*u.r,Math.max(2*u.r,d));u.x=c.x+u.r,u.y=s.highlightStyle.y=c.y+c.height/2,s.highlightStyle.x=u.x+u.r+l;for(var p=this.createBackgroundShape(o.backgroundColor),m=u.n,f=u.x,g=u.y,y=u.r0,V=u.r,U=u.color,b=[],_=Math.round(180/m),v=0;m>v;v++)b[v]=new r({highlightStyle:{x:f,y:g,r0:y,r:V,startAngle:_*v*2,endAngle:_*v*2+_,color:n.alpha(U,(v+1)/m),brushType:"fill"}});var k=[0,f,g];return setInterval(function(){e(p),k[0]-=.3;for(var i=0;m>i;i++)b[i].rotation=k,e(b[i]);e(s),t()},u.timeInterval)},e});