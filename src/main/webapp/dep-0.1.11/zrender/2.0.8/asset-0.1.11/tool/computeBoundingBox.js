define("zrender/tool/computeBoundingBox",["require","./vector","./curve"],function(require){function e(e,t,i){if(0!==e.length){for(var n=e[0][0],r=e[0][0],a=e[0][1],o=e[0][1],s=1;s<e.length;s++){var l=e[s];if(l[0]<n)n=l[0];if(l[0]>r)r=l[0];if(l[1]<a)a=l[1];if(l[1]>o)o=l[1]}t[0]=n,t[1]=a,i[0]=r,i[1]=o}}function t(e,t,i,n,a,o){var s=[];r.cubicExtrema(e[0],t[0],i[0],n[0],s);for(var l=0;l<s.length;l++)s[l]=r.cubicAt(e[0],t[0],i[0],n[0],s[l]);var h=[];r.cubicExtrema(e[1],t[1],i[1],n[1],h);for(var l=0;l<h.length;l++)h[l]=r.cubicAt(e[1],t[1],i[1],n[1],h[l]);s.push(e[0],n[0]),h.push(e[1],n[1]);var d=Math.min.apply(null,s),u=Math.max.apply(null,s),c=Math.min.apply(null,h),p=Math.max.apply(null,h);a[0]=d,a[1]=c,o[0]=u,o[1]=p}function i(e,t,i,n,a){var o=r.quadraticExtremum(e[0],t[0],i[0]),s=r.quadraticExtremum(e[1],t[1],i[1]);o=Math.max(Math.min(o,1),0),s=Math.max(Math.min(s,1),0);var l=1-o,h=1-s,d=l*l*e[0]+2*l*o*t[0]+o*o*i[0],u=l*l*e[1]+2*l*o*t[1]+o*o*i[1],c=h*h*e[0]+2*h*s*t[0]+s*s*i[0],p=h*h*e[1]+2*h*s*t[1]+s*s*i[1];n[0]=Math.min(e[0],i[0],d,c),n[1]=Math.min(e[1],i[1],u,p),a[0]=Math.max(e[0],i[0],d,c),a[1]=Math.max(e[1],i[1],u,p)}var n=require("./vector"),r=require("./curve"),a=n.create(),o=n.create(),s=n.create(),l=function(e,t,i,r,l,h,d,u){if(Math.abs(r-l)>=2*Math.PI)return d[0]=e-i,d[1]=t-i,u[0]=e+i,void(u[1]=t+i);if(a[0]=Math.cos(r)*i+e,a[1]=Math.sin(r)*i+t,o[0]=Math.cos(l)*i+e,o[1]=Math.sin(l)*i+t,n.min(d,a,o),n.max(u,a,o),r%=2*Math.PI,0>r)r+=2*Math.PI;if(l%=2*Math.PI,0>l)l+=2*Math.PI;if(r>l&&!h)l+=2*Math.PI;else if(l>r&&h)r+=2*Math.PI;if(h){var c=l;l=r,r=c}for(var p=0;l>p;p+=Math.PI/2)if(p>r)s[0]=Math.cos(p)*i+e,s[1]=Math.sin(p)*i+t,n.min(d,s,d),n.max(u,s,u)};return e.cubeBezier=t,e.quadraticBezier=i,e.arc=l,e});