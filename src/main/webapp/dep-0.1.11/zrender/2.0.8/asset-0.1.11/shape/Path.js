define("zrender/shape/Path",["require","./Base","./util/PathProxy","../tool/util"],function(require){var e=require("./Base"),t=require("./util/PathProxy"),i=t.PathSegment,n=function(e){return Math.sqrt(e[0]*e[0]+e[1]*e[1])},a=function(e,t){return(e[0]*t[0]+e[1]*t[1])/(n(e)*n(t))},r=function(e,t){return(e[0]*t[1]<e[1]*t[0]?-1:1)*Math.acos(a(e,t))},o=function(t){e.call(this,t)};return o.prototype={type:"path",buildPathArray:function(e,t,n){if(!e)return[];t=t||0,n=n||0;var a=e,r=["m","M","l","L","v","V","h","H","z","Z","c","C","q","Q","t","T","s","S","a","A"];a=a.replace(/-/g," -"),a=a.replace(/  /g," "),a=a.replace(/ /g,","),a=a.replace(/,,/g,",");var o;for(o=0;o<r.length;o++)a=a.replace(new RegExp(r[o],"g"),"|"+r[o]);var s=a.split("|"),l=[],h=0,d=0;for(o=1;o<s.length;o++){var u=s[o],c=u.charAt(0);u=u.slice(1),u=u.replace(new RegExp("e,-","g"),"e-");var p=u.split(",");if(p.length>0&&""===p[0])p.shift();for(var m=0;m<p.length;m++)p[m]=parseFloat(p[m]);for(;p.length>0&&!isNaN(p[0]);){var f,g,y,V,U,b,_,v,x=null,k=[],L=h,w=d;switch(c){case"l":h+=p.shift(),d+=p.shift(),x="L",k.push(h,d);break;case"L":h=p.shift(),d=p.shift(),k.push(h,d);break;case"m":h+=p.shift(),d+=p.shift(),x="M",k.push(h,d),c="l";break;case"M":h=p.shift(),d=p.shift(),x="M",k.push(h,d),c="L";break;case"h":h+=p.shift(),x="L",k.push(h,d);break;case"H":h=p.shift(),x="L",k.push(h,d);break;case"v":d+=p.shift(),x="L",k.push(h,d);break;case"V":d=p.shift(),x="L",k.push(h,d);break;case"C":k.push(p.shift(),p.shift(),p.shift(),p.shift()),h=p.shift(),d=p.shift(),k.push(h,d);break;case"c":k.push(h+p.shift(),d+p.shift(),h+p.shift(),d+p.shift()),h+=p.shift(),d+=p.shift(),x="C",k.push(h,d);break;case"S":if(f=h,g=d,y=l[l.length-1],"C"===y.command)f=h+(h-y.points[2]),g=d+(d-y.points[3]);k.push(f,g,p.shift(),p.shift()),h=p.shift(),d=p.shift(),x="C",k.push(h,d);break;case"s":if(f=h,g=d,y=l[l.length-1],"C"===y.command)f=h+(h-y.points[2]),g=d+(d-y.points[3]);k.push(f,g,h+p.shift(),d+p.shift()),h+=p.shift(),d+=p.shift(),x="C",k.push(h,d);break;case"Q":k.push(p.shift(),p.shift()),h=p.shift(),d=p.shift(),k.push(h,d);break;case"q":k.push(h+p.shift(),d+p.shift()),h+=p.shift(),d+=p.shift(),x="Q",k.push(h,d);break;case"T":if(f=h,g=d,y=l[l.length-1],"Q"===y.command)f=h+(h-y.points[0]),g=d+(d-y.points[1]);h=p.shift(),d=p.shift(),x="Q",k.push(f,g,h,d);break;case"t":if(f=h,g=d,y=l[l.length-1],"Q"===y.command)f=h+(h-y.points[0]),g=d+(d-y.points[1]);h+=p.shift(),d+=p.shift(),x="Q",k.push(f,g,h,d);break;case"A":V=p.shift(),U=p.shift(),b=p.shift(),_=p.shift(),v=p.shift(),L=h,w=d,h=p.shift(),d=p.shift(),x="A",k=this._convertPoint(L,w,h,d,_,v,V,U,b);break;case"a":V=p.shift(),U=p.shift(),b=p.shift(),_=p.shift(),v=p.shift(),L=h,w=d,h+=p.shift(),d+=p.shift(),x="A",k=this._convertPoint(L,w,h,d,_,v,V,U,b)}for(var W=0,I=k.length;I>W;W+=2)k[W]+=t,k[W+1]+=n;l.push(new i(x||c,k))}if("z"===c||"Z"===c)l.push(new i("z",[]))}return l},_convertPoint:function(e,t,i,n,o,s,l,h,d){var u=d*(Math.PI/180),c=Math.cos(u)*(e-i)/2+Math.sin(u)*(t-n)/2,p=-1*Math.sin(u)*(e-i)/2+Math.cos(u)*(t-n)/2,m=c*c/(l*l)+p*p/(h*h);if(m>1)l*=Math.sqrt(m),h*=Math.sqrt(m);var f=Math.sqrt((l*l*h*h-l*l*p*p-h*h*c*c)/(l*l*p*p+h*h*c*c));if(o===s)f*=-1;if(isNaN(f))f=0;var g=f*l*p/h,y=f*-h*c/l,V=(e+i)/2+Math.cos(u)*g-Math.sin(u)*y,U=(t+n)/2+Math.sin(u)*g+Math.cos(u)*y,b=r([1,0],[(c-g)/l,(p-y)/h]),_=[(c-g)/l,(p-y)/h],v=[(-1*c-g)/l,(-1*p-y)/h],x=r(_,v);if(a(_,v)<=-1)x=Math.PI;if(a(_,v)>=1)x=0;if(0===s&&x>0)x-=2*Math.PI;if(1===s&&0>x)x+=2*Math.PI;return[V,U,l,h,b,x,u,s]},buildPath:function(e,t){var i=t.path,n=t.x||0,a=t.y||0;t.pathArray=t.pathArray||this.buildPathArray(i,n,a);for(var r=t.pathArray,o=t.pointList=[],s=[],l=0,h=r.length;h>l;l++){if("M"==r[l].command.toUpperCase())s.length>0&&o.push(s),s=[];for(var d=r[l].points,u=0,c=d.length;c>u;u+=2)s.push([d[u],d[u+1]])}s.length>0&&o.push(s);for(var l=0,h=r.length;h>l;l++){var p=r[l].command,d=r[l].points;switch(p){case"L":e.lineTo(d[0],d[1]);break;case"M":e.moveTo(d[0],d[1]);break;case"C":e.bezierCurveTo(d[0],d[1],d[2],d[3],d[4],d[5]);break;case"Q":e.quadraticCurveTo(d[0],d[1],d[2],d[3]);break;case"A":var m=d[0],f=d[1],g=d[2],y=d[3],V=d[4],U=d[5],b=d[6],_=d[7],v=g>y?g:y,x=g>y?1:g/y,k=g>y?y/g:1;e.translate(m,f),e.rotate(b),e.scale(x,k),e.arc(0,0,v,V,V+U,1-_),e.scale(1/x,1/k),e.rotate(-b),e.translate(-m,-f);break;case"z":e.closePath()}}},getRect:function(e){if(e.__rect)return e.__rect;var t;if("stroke"==e.brushType||"fill"==e.brushType)t=e.lineWidth||1;else t=0;for(var i=Number.MAX_VALUE,n=Number.MIN_VALUE,a=Number.MAX_VALUE,r=Number.MIN_VALUE,o=e.x||0,s=e.y||0,l=e.pathArray||this.buildPathArray(e.path),h=0;h<l.length;h++)for(var d=l[h].points,u=0;u<d.length;u++)if(u%2===0){if(d[u]+o<i)i=d[u];if(d[u]+o>n)n=d[u]}else{if(d[u]+s<a)a=d[u];if(d[u]+s>r)r=d[u]}var c;if(i===Number.MAX_VALUE||n===Number.MIN_VALUE||a===Number.MAX_VALUE||r===Number.MIN_VALUE)c={x:0,y:0,width:0,height:0};else c={x:Math.round(i-t/2),y:Math.round(a-t/2),width:n-i+t,height:r-a+t};return e.__rect=c,c}},require("../tool/util").inherits(o,e),o});