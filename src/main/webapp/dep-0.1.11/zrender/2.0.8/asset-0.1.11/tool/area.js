define("zrender/tool/area",["require","./util","./curve"],function(require){"use strict";function e(e){if(e%=X,0>e)e+=X;return e}function t(e,t,r,a){if(!t||!e)return!1;var o=e.type;x=x||k.getContext();var s=i(e,t,r,a);if("undefined"!=typeof s)return s;if(e.buildPath&&x.isPointInPath)return n(e,x,t,r,a);switch(o){case"ellipse":return!0;case"trochoid":var l="out"==t.location?t.r1+t.r2+t.d:t.r1-t.r2+t.d;return c(t,r,a,l);case"rose":return c(t,r,a,t.maxr);default:return!1}}function i(e,t,i,n){var r=e.type;switch(r){case"bezier-curve":if("undefined"==typeof t.cpX2)return s(t.xStart,t.yStart,t.cpX1,t.cpY1,t.xEnd,t.yEnd,t.lineWidth,i,n);else return o(t.xStart,t.yStart,t.cpX1,t.cpY1,t.cpX2,t.cpY2,t.xEnd,t.yEnd,t.lineWidth,i,n);case"line":return a(t.xStart,t.yStart,t.xEnd,t.yEnd,t.lineWidth,i,n);case"polyline":return h(t.pointList,t.lineWidth,i,n);case"ring":return d(t.x,t.y,t.r0,t.r,i,n);case"circle":return c(t.x,t.y,t.r,i,n);case"sector":var l=t.startAngle*Math.PI/180,f=t.endAngle*Math.PI/180;if(!t.clockWise)l=-l,f=-f;return p(t.x,t.y,t.r0,t.r,l,f,!t.clockWise,i,n);case"path":return t.pathArray&&_(t.pathArray,Math.max(t.lineWidth,5),t.brushType,i,n);case"polygon":case"star":case"isogon":return m(t.pointList,i,n);case"text":var g=t.__rect||e.getRect(t);return u(g.x,g.y,g.width,g.height,i,n);case"rectangle":case"image":return u(t.x,t.y,t.width,t.height,i,n)}}function n(e,t,i,n,r){return t.beginPath(),e.buildPath(t,i),t.closePath(),t.isPointInPath(n,r)}function r(e,i,n,r){return!t(e,i,n,r)}function a(e,t,i,n,r,a,o){if(0===r)return!1;var s=Math.max(r,5),l=0,h=e;if(o>t+s&&o>n+s||t-s>o&&n-s>o||a>e+s&&a>i+s||e-s>a&&i-s>a)return!1;if(e!==i)l=(t-n)/(e-i),h=(e*n-i*t)/(e-i);else return Math.abs(a-e)<=s/2;var d=l*a-o+h,u=d*d/(l*l+1);return s/2*s/2>=u}function o(e,t,i,n,r,a,o,s,l,h,d){if(0===l)return!1;var u=Math.max(l,5);if(d>t+u&&d>n+u&&d>a+u&&d>s+u||t-u>d&&n-u>d&&a-u>d&&s-u>d||h>e+u&&h>i+u&&h>r+u&&h>o+u||e-u>h&&i-u>h&&r-u>h&&o-u>h)return!1;var c=L.cubicProjectPoint(e,t,i,n,r,a,o,s,h,d,null);return u/2>=c}function s(e,t,i,n,r,a,o,s,l){if(0===o)return!1;var h=Math.max(o,5);if(l>t+h&&l>n+h&&l>a+h||t-h>l&&n-h>l&&a-h>l||s>e+h&&s>i+h&&s>r+h||e-h>s&&i-h>s&&r-h>s)return!1;var d=L.quadraticProjectPoint(e,t,i,n,r,a,s,l,null);return h/2>=d}function l(t,i,n,r,a,o,s,l,h){if(0===s)return!1;var d=Math.max(s,5);l-=t,h-=i;var u=Math.sqrt(l*l+h*h);if(u-d>n||n>u+d)return!1;if(Math.abs(r-a)>=X)return!0;if(o){var c=r;r=e(a),a=e(c)}else r=e(r),a=e(a);if(r>a)a+=X;var p=Math.atan2(h,l);if(0>p)p+=X;return p>=r&&a>=p||p+X>=r&&a>=p+X}function h(e,t,i,n){for(var t=Math.max(t,10),r=0,o=e.length-1;o>r;r++){var s=e[r][0],l=e[r][1],h=e[r+1][0],d=e[r+1][1];if(a(s,l,h,d,t,i,n))return!0}return!1}function d(e,t,i,n,r,a){var o=(r-e)*(r-e)+(a-t)*(a-t);return n*n>o&&o>i*i}function u(e,t,i,n,r,a){return r>=e&&e+i>=r&&a>=t&&t+n>=a}function c(e,t,i,n,r){return i*i>(n-e)*(n-e)+(r-t)*(r-t)}function p(e,t,i,n,r,a,o,s,h){return l(e,t,(i+n)/2,r,a,o,n-i,s,h)}function m(e,t,i){for(var n=e.length,r=0,a=0,o=n-1;n>a;a++){var s=e[o][0],l=e[o][1],h=e[a][0],d=e[a][1];r+=f(s,l,h,d,t,i),o=a}return 0!==r}function f(e,t,i,n,r,a){if(a>t&&a>n||t>a&&n>a)return 0;if(n==t)return 0;var o=t>n?1:-1,s=(a-t)/(n-t),l=s*(i-e)+e;return l>r?o:0}function g(){var e=S[0];S[0]=S[1],S[1]=e}function y(e,t,i,n,r,a,o,s,l,h){if(h>t&&h>n&&h>a&&h>s||t>h&&n>h&&a>h&&s>h)return 0;var d=L.cubicRootAt(t,n,a,s,h,T);if(0===d)return 0;else{for(var u,c,p=0,m=-1,f=0;d>f;f++){var y=T[f],V=L.cubicAt(e,i,r,o,y);if(!(l>V)){if(0>m){if(m=L.cubicExtrema(t,n,a,s,S),S[1]<S[0]&&m>1)g();if(u=L.cubicAt(t,n,a,s,S[0]),m>1)c=L.cubicAt(t,n,a,s,S[1])}if(2==m)if(y<S[0])p+=t>u?1:-1;else if(y<S[1])p+=u>c?1:-1;else p+=c>s?1:-1;else if(y<S[0])p+=t>u?1:-1;else p+=u>s?1:-1}else;}return p}}function V(e,t,i,n,r,a,o,s){if(s>t&&s>n&&s>a||t>s&&n>s&&a>s)return 0;var l=L.quadraticRootAt(t,n,a,s,T);if(0===l)return 0;else{var h=L.quadraticExtremum(t,n,a);if(h>=0&&1>=h){for(var d=0,u=L.quadraticAt(t,n,a,h),c=0;l>c;c++){var p=L.quadraticAt(e,i,r,T[c]);if(!(p>o))if(T[c]<h)d+=t>u?1:-1;else d+=u>a?1:-1;else;}return d}else{var p=L.quadraticAt(e,i,r,T[0]);if(p>o)return 0;else return t>a?1:-1}}}function U(t,i,n,r,a,o,s,l){if(l-=i,l>n||-n>l)return 0;var h=Math.sqrt(n*n-l*l);if(T[0]=-h,T[1]=h,Math.abs(r-a)>=X){r=0,a=X;var d=o?1:-1;if(s>=T[0]+t&&s<=T[1]+t)return d;else return 0}if(o){var h=r;r=e(a),a=e(h)}else r=e(r),a=e(a);if(r>a)a+=X;for(var u=0,c=0;2>c;c++){var p=T[c];if(p+t>s){var m=Math.atan2(l,p),d=o?1:-1;if(0>m)m=X+m;if(m>=r&&a>=m||m+X>=r&&a>=m+X){if(m>Math.PI/2&&m<1.5*Math.PI)d=-d;u+=d}}}return u}function _(e,t,i,n,r){var h=0,d=0,u=0,c=0,p=0,m=!0,g=!0;i=i||"fill";for(var _="stroke"===i||"both"===i,b="fill"===i||"both"===i,v=0;v<e.length;v++){var x=e[v],k=x.points;if(m||"M"===x.command){if(v>0){if(b)h+=f(d,u,c,p,n,r);if(0!==h)return!0}if(c=k[k.length-2],p=k[k.length-1],m=!1,g&&"A"!==x.command)g=!1,d=c,u=p}switch(x.command){case"M":d=k[0],u=k[1];break;case"L":if(_)if(a(d,u,k[0],k[1],t,n,r))return!0;if(b)h+=f(d,u,k[0],k[1],n,r);d=k[0],u=k[1];break;case"C":if(_)if(o(d,u,k[0],k[1],k[2],k[3],k[4],k[5],t,n,r))return!0;if(b)h+=y(d,u,k[0],k[1],k[2],k[3],k[4],k[5],n,r);d=k[4],u=k[5];break;case"Q":if(_)if(s(d,u,k[0],k[1],k[2],k[3],t,n,r))return!0;if(b)h+=V(d,u,k[0],k[1],k[2],k[3],n,r);d=k[2],u=k[3];break;case"A":var L=k[0],w=k[1],W=k[2],I=k[3],C=k[4],M=k[5],X=Math.cos(C)*W+L,T=Math.sin(C)*I+w;if(!g)h+=f(d,u,X,T);else g=!1,c=X,p=T;var S=(n-L)*I/W+L;if(_)if(l(L,w,I,C,C+M,1-k[7],t,S,r))return!0;if(b)h+=U(L,w,I,C,C+M,1-k[7],S,r);d=Math.cos(C+M)*W+L,u=Math.sin(C+M)*I+w;break;case"z":if(_)if(a(d,u,c,p,t,n,r))return!0;m=!0}}if(b)h+=f(d,u,c,p,n,r);return 0!==h}function b(e,t){var i=e+":"+t;if(w[i])return w[i];if(x=x||k.getContext(),x.save(),t)x.font=t;e=(e+"").split("\n");for(var n=0,r=0,a=e.length;a>r;r++)n=Math.max(x.measureText(e[r]).width,n);if(x.restore(),w[i]=n,++I>M)I=0,w={};return n}function v(e,t){var i=e+":"+t;if(W[i])return W[i];if(x=x||k.getContext(),x.save(),t)x.font=t;e=(e+"").split("\n");var n=(x.measureText("国").width+2)*e.length;if(x.restore(),W[i]=n,++C>M)C=0,W={};return n}var x,k=require("./util"),L=require("./curve"),w={},W={},I=0,C=0,M=5e3,X=2*Math.PI,T=[-1,-1,-1],S=[-1,-1];return{isInside:t,isOutside:r,getTextWidth:b,getTextHeight:v,isInsidePath:_,isInsidePolygon:m,isInsideSector:p,isInsideCircle:c,isInsideLine:a,isInsideRect:u,isInsidePolyline:h,isInsideCubicStroke:o,isInsideQuadraticStroke:s}});