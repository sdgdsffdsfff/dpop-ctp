define("zrender/tool/util",["require","../dep/excanvas"],function(require){function e(e){return e&&1===e.nodeType&&"string"==typeof e.nodeName}function t(i){if("object"==typeof i&&null!==i){var n=i;if(i instanceof Array){n=[];for(var r=0,a=i.length;a>r;r++)n[r]=t(i[r])}else if(!m[g.call(i)]&&!e(i)){n={};for(var o in i)if(i.hasOwnProperty(o))n[o]=t(i[o])}return n}return i}function i(t,i,r,a){if(i.hasOwnProperty(r)){var o=t[r];if("object"==typeof o&&!m[g.call(o)]&&!e(o))n(t[r],i[r],a);else if(a||!(r in t))t[r]=i[r]}}function n(e,t,n){for(var r in t)i(e,t,r,n);return e}function r(){if(!d)if(require("../dep/excanvas"),window.G_vmlCanvasManager){var e=document.createElement("div");e.style.position="absolute",e.style.top="-1000px",document.body.appendChild(e),d=G_vmlCanvasManager.initElement(e).getContext("2d")}else d=document.createElement("canvas").getContext("2d");return d}function a(){if(!c)u=document.createElement("canvas"),p=u.width,f=u.height,c=u.getContext("2d");return c}function o(e,t){var i,n=100;if(e+y>p)p=e+y+n,u.width=p,i=!0;if(t+V>f)f=t+V+n,u.height=f,i=!0;if(-y>e)y=Math.ceil(-e/n)*n,p+=y,u.width=p,i=!0;if(-V>t)V=Math.ceil(-t/n)*n,f+=V,u.height=f,i=!0;if(i)c.translate(y,V)}function s(){return{x:y,y:V}}function l(e,t){if(e.indexOf)return e.indexOf(t);for(var i=0,n=e.length;n>i;i++)if(e[i]===t)return i;return-1}function h(e,t){function i(){}var n=e.prototype;i.prototype=t.prototype,e.prototype=new i;for(var r in n)e.prototype[r]=n[r];e.constructor=e}var d,u,c,p,f,m={"[object Function]":1,"[object RegExp]":1,"[object Date]":1,"[object Error]":1,"[object CanvasGradient]":1},g=Object.prototype.toString,y=0,V=0;return{inherits:h,clone:t,merge:n,getContext:r,getPixelContext:a,getPixelOffset:s,adjustCanvasSize:o,indexOf:l}});