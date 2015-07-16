define("zrender/shape/Sector",["require","../tool/math","../tool/computeBoundingBox","../tool/vector","./Base","../tool/util"],function(require){var e=require("../tool/math"),t=require("../tool/computeBoundingBox"),i=require("../tool/vector"),n=require("./Base"),a=i.create(),r=i.create(),o=i.create(),s=i.create(),l=function(e){n.call(this,e)};return l.prototype={type:"sector",buildPath:function(t,i){var n=i.x,a=i.y,r=i.r0||0,o=i.r,s=i.startAngle,l=i.endAngle,h=i.clockWise||!1;if(s=e.degreeToRadian(s),l=e.degreeToRadian(l),!h)s=-s,l=-l;var d=e.cos(s),u=e.sin(s);if(t.moveTo(d*r+n,u*r+a),t.lineTo(d*o+n,u*o+a),t.arc(n,a,o,s,l,!h),t.lineTo(e.cos(l)*r+n,e.sin(l)*r+a),0!==r)t.arc(n,a,r,l,s,h);t.closePath()},getRect:function(n){if(n.__rect)return n.__rect;var l=n.x,h=n.y,d=n.r0||0,u=n.r,c=e.degreeToRadian(n.startAngle),p=e.degreeToRadian(n.endAngle),m=n.clockWise;if(!m)c=-c,p=-p;if(d>1)t.arc(l,h,d,c,p,!m,a,o);else a[0]=o[0]=l,a[1]=o[1]=h;return t.arc(l,h,u,c,p,!m,r,s),i.min(a,a,r),i.max(o,o,s),n.__rect={x:a[0],y:a[1],width:o[0]-a[0],height:o[1]-a[1]},n.__rect}},require("../tool/util").inherits(l,n),l});