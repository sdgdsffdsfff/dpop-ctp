define("echarts/util/ndarray",["require","./kwargs"],function(require){"use strict";function e(e){if("undefined"==typeof e)return"number";switch(Object.prototype.toString.call(e)){case"[object Int32Array]":return"int32";case"[object Int16Array]":return"int16";case"[object Int8Array]":return"int8";case"[object Uint32Array]":return"uint32";case"[object Uint16Array]":return"uint16";case"[object Uint8Array]":return"uint8";case"[object Uint8ClampedArray]":return"uint8c";case"[object Float32Array]":return"float32";case"[object Float64Array]":return"float64";default:return"number"}}function t(e,t){if(e.indexOf(":")>=0){var i,n,a=e.split(/\s*:\s*/),o=parseInt(a[2]||1,10);if(0===o)throw new Error("Slice step cannot be zero");else if(o>0)i=parseInt(a[0]||0,10),n=parseInt(a[1]||t,10);else i=parseInt(a[0]||t-1,10),n=parseInt(a[1]||-1,10);if(0>i)i=t+i;if(0>n&&a[1])n=t+n;if(o>0)i=Math.max(Math.min(t,i),0),n=Math.max(Math.min(t,n),0);else i=Math.max(Math.min(t-1,i),-1),n=Math.max(Math.min(t-1,n),-1);return[i,n,o]}else{var i=parseInt(e,10);if(0>i)i=t+i;if(0>i||i>t)throw new Error(V(e));return i=Math.max(Math.min(t-1,i),0),[i,i+1,1]}}function i(e){for(var t=e[0],i=1;i<e.length;i++)t*=e[i];return t}function n(e){for(var t=1,i=e[0];i instanceof Array;)i=i[0],t++;return t}function a(e){for(var t=[e.length],i=e[0];i instanceof Array;)t.push(i.length),i=i[0];return t}function o(e,t){if(t==e.length-1)return 1;for(var i=e[t+1],n=t+2;n<e.length;n++)i*=e[n];return i}function s(e){for(var t=[],n=1,a=i(e),o=0;o<e.length;o++)n*=e[o],t.push(a/n);return t}function l(e,t){if(e.length!==t.length)return!1;for(var i=0;i<e.length;i++)if(e[i]!==t[i])return!1;return!0}function r(e,t){return"Shape ("+e.toString()+") ("+t.toString()+") could not be broadcast together"}function h(e){return"Axis "+e+" out of bounds"}function V(e){return"Index "+e+" out of bounds"}var U=require("./kwargs"),m=Array.prototype.slice;this.Int32Array=window.Int32Array||Array,this.Int16Array=window.Int16Array||Array,this.Int8Array=window.Int8Array||Array,this.Uint32Array=window.Uint32Array||Array,this.Uint16Array=window.Uint16Array||Array,this.Uint8Array=window.Uint8Array||Array,this.Float32Array=window.Float32Array||Array,this.Float64Array=window.Float64Array||Array;var p={int32:this.Int32Array,int16:this.Int16Array,int8:this.Int8Array,uint32:this.Uint32Array,uint16:this.Uint16Array,uint8:this.Uint8Array,uint8c:this.Uint8ClampedArray,float32:this.Float32Array,float64:this.Float64Array,number:Array},d={int32:4,int16:2,int8:1,uint32:4,uint16:2,uint8:1,uint8c:1,float32:4,float64:8,number:1},c=0,y=1,b=2,k=3,u=4,f=5,g=6,L=7,_=8,x=function(t){var i=arguments[arguments.length-1];if("string"==typeof i)this._dtype=i;else this._dtype=e(t);if(t&&"string"!=typeof t){if(t instanceof x)return t._dtype=this._dtype,t;else if("undefined"!=typeof t.length)this.initFromArray(t);else if("number"==typeof t)this.initFromShape.apply(this,arguments)}else this._array=new p[this._dtype],this._shape=[0],this._size=0};return x.prototype={initFromArray:function(e){function t(e,i,n){for(var a=n.length,l=0;a>l;l++)if(o-1>e)t(e+1,i,n[l]);else i[s++]=n[l]}var o=n(e),s=0,l=a(e),r=i(l);return this._array=new p[this._dtype](r),t(0,this._array,e),this._shape=l,this._size=r,this},initFromShape:function(e){if("number"==typeof e)e=Array.prototype.slice.call(arguments);if(e){var t=i(e);if("number"===this._dtype){this._array=[];for(var n=this._array,a=0;t>a;a++)n[a]=0}else this._array=new p[this._dtype](t)}return this._shape=e,this._size=i(e),this},fill:function(e){for(var t=this._array,i=0;i<t.length;i++)t[i]=e;return this},shape:function(){return this._shape.slice()},size:function(){return this._size},dtype:function(){return this._dtype},dimension:function(){return this._shape.length},strides:function(){for(var e=s(this._shape),t=d[this._dtype],i=0;i<e.length;i++)e[i]*=t;return e},reshape:function(e){if("number"==typeof e)e=Array.prototype.slice.call(arguments);if(this._isShapeValid(e))this._shape=e;else throw new Error("Total size of new array must be unchanged");return this},_isShapeValid:function(e){return i(e)===this._size},resize:function(e){if("number"==typeof e)e=Array.prototype.slice.call(arguments);var t=i(e);if(t<this._size){if("number"===this._dtype)this._array.length=t}else if("number"===this._dtype)for(var n=this._array.length;t>n;n++)this._array[n]=0;else{for(var a=new p[this._dtype](t),o=this._array,n=0;n<o.length;n++)a[n]=o[n];this._array=a}return this._shape=e,this._size=t,this},transpose:U(function(e,t){for(var i=[],n=0;n<this._shape.length;n++)i.push(n);if("undefined"==typeof e)e=i.slice();for(var n=0;n<e.length;n++)if(e[n]>=this._shape.length)throw new Error(h(e[n]));if(e.length<=1)return this;for(var a=i.slice(),n=0;n<Math.floor(e.length/2);n++)for(var o=e.length-1;o>=Math.ceil(e.length/2);o--)a[e[n]]=e[o],a[e[o]]=e[n];return this._transposelike(a,t)}),swapaxes:U(function(e,t,i){return this.transpose([e,t],i)}),rollaxis:U(function(e,t,i){if(e>=this._shape.length)throw new Error(h(e));for(var n=[],a=0;a<this._shape.length;a++)n.push(a);return n.splice(e,1),n.splice(t,0,e),this._transposelike(n,i)},{start:0}),_transposelike:function(e,t){function i(e,t,s){var r=a[e],h=o[e],V=m[e];if(l-1>e)for(var U=0;r>U;U++)i(e+1,t+h*U,s+V*U);else for(var U=0;r>U;U++)d[s+U]=n[t+h*U]}for(var n=this._array,a=this._shape.slice(),o=s(this._shape),l=a.length,r=[],h=[],V=0;V<e.length;V++){var U=e[V];h[V]=a[U],r[V]=o[U]}o=r,a=h,this._shape=a;var m=s(this._shape);if(!t)t=new x,t._shape=this._shape.slice(),t._dtype=this._dtype,t._size=this._size;var d=new p[this._dtype](this._size);return t._array=d,i(0,0,0),t},repeat:U(function(e,t,i){var n;if("undefined"==typeof t)n=[this._size],t=0;else n=this._shape.slice();var a=n.slice();if(n[t]*=e,!i)i=new x(this._dtype),i.initFromShape(n);else if(!l(n,i._shape))throw new Error(r(n,i._shape));for(var s=i._array,h=o(a,t),V=a[t],U=this._array,m=h*V,p=0;p<this._size;p+=m)for(var d=0;h>d;d++)for(var c=p+d,y=p*e+d,b=0;V>b;b++){for(var k=0;e>k;k++)s[y]=U[c],y+=h;c+=h}return i}),choose:function(){console.warn("TODO")},take:function(){console.warn("TODO")},tile:function(){console.warn("TODO")},_withPreprocess1:function(e,t,i,n){var a=this._array;if(this._size)if("undefined"!=typeof e){if(0>e)e=this._shape.length+e;if(e>=this._shape.length||0>e)throw new Error(h(e));var s=this._shape.slice();if(s.splice(e,1),t&&!l(s,t._shape))throw new Error(r(s,t._shape));if(!t)t=new x(this._dtype),t.initFromShape(s);var V=t._array,U=o(this._shape,e),m=this._shape[e],p=U*m;return i.call(this,V,a,p,m,U),t}else return n.call(this,a)},_withPreprocess2:function(e,t,i,n){var a=this._array;if(this._size){if(t&&!l(this._shape,t._shape))throw new Error(r(this._shape,t._shape));if(!t)t=new x(this._dtype),t.initFromShape(this._shape);var s=t._array;if("undefined"!=typeof e){if(0>e)e=this._shape.length+e;if(e>=this._shape.length||0>e)throw new Error(h(e));if(e>=this._shape.length)throw new Error(h(e));var V=o(this._shape,e),U=this._shape[e],m=V*U;i.call(this,s,a,m,U,V)}else t.reshape([this._size]),n.call(this,s,a);return t}},max:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=l+s,h=t[r],V=0;n>V;V++){var U=t[r];if(U>h)h=U;r+=a}e[o++]=h}}function t(e){for(var t=e[0],i=1;i<this._size;i++)if(e[i]>t)t=e[i];return t}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),min:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=l+s,h=t[r],V=0;n>V;V++){var U=t[r];if(h>U)h=U;r+=a}e[o++]=h}}function t(e){for(var t=e[0],i=1;i<this._size;i++)if(e[i]<t)t=e[i];return t}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),argmax:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=t[h],U=0;n>U;U++){var m=t[h];if(m>V)V=m,r=U;h+=a}e[o++]=r}}function t(e){for(var t=e[0],i=0,n=1;n<this._size;n++)if(e[n]>t)i=n,t=e[n];return i}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),argmin:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=t[h],U=0;n>U;U++){var m=t[h];if(V>m)V=m,r=U;h+=a}e[o++]=r}}function t(e){for(var t=e[0],i=0,n=1;n<this._size;n++)if(e[n]<t)i=n,t=e[n];return i}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),sum:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=0;n>V;V++)r+=t[h],h+=a;e[o++]=r}}function t(e){for(var t=0,i=0;i<this._size;i++)t+=e[i];return t}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),prod:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=1,h=l+s,V=0;n>V;V++)r*=t[h],h+=a;e[o++]=r}}function t(e){for(var t=1,i=0;i<this._size;i++)t*=e[i];return t}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),mean:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=0;n>V;V++)r+=t[h],h+=a;var U=r/n;e[o++]=U}}function t(e){for(var t=0,i=e.length,n=0;i>n;n++)t+=e[n];var a=t/i;return a}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),"var":U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=0;n>V;V++)r+=t[h],h+=a;var U=r/n,m=0;h=l+s;for(var V=0;n>V;V++){var p=t[h]-U;m+=p*p,h+=a}e[o++]=m/n}}function t(e){for(var t=0,i=e.length,n=0;i>n;n++)t+=e[n];for(var a=t/i,o=0,n=0;i>n;n++){var s=e[n]-a;o+=s*s}return o/i}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),std:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=0,h=l+s,V=0;n>V;V++)r+=t[h],h+=a;var U=r/n,m=0;h=l+s;for(var V=0;n>V;V++){var p=t[h]-U;m+=p*p,h+=a}e[o++]=Math.sqrt(m/n)}}function t(e){for(var t=0,i=e.length,n=0;i>n;n++)t+=e[n];for(var a=t/i,o=0,n=0;i>n;n++){var s=e[n]-a;o+=s*s}return Math.sqrt(o/i)}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),ptp:U(function(){function e(e,t,i,n,a){for(var o=0,s=0;s<this._size;s+=i)for(var l=0;a>l;l++){for(var r=s+l,h=t[r],V=t[r],U=0;n>U;U++){var m=t[r];if(h>m)h=m;if(m>V)V=m;r+=a}e[o++]=V-h}}function t(e){for(var t=e[0],i=e[0],n=1;n<this._size;n++){if(e[n]<t)t=e[n];if(e[n]>i)i=e[n]}return i-t}return function(i,n){return this._withPreprocess1(i,n,e,t)}}()),sort:U(function(e,t){if(0>e)e=this._shape.length+e;var i;if("ascending"===t)i=function(e,t){return e-t};else if("descending"===t)i=function(e,t){return t-e};for(var n=this._array,a=o(this._shape,e),s=this._shape[e],l=a*s,r=new Array(s),h=0;h<this._size;h+=l)for(var V=0;a>V;V++){for(var U=h+V,m=0;s>m;m++)r[m]=n[U],U+=a;r.sort(i);for(var U=h+V,m=0;s>m;m++)n[U]=r[m],U+=a}return this},{axis:-1,order:"ascending"}),argsort:U(function(e,t,i){if(0>e)e=this._shape.length+e;if(this._size){if(i&&!l(this._shape,i._shape))throw new Error(r(this._shape,i._shape));if(!i)i=new x(this._dtype),i.initFromShape(this._shape);var n,a=i._array;if("ascending"===t)n=function(e,t){return m[e]-m[t]};else if("descending"===t)n=function(e,t){return m[t]-m[e]};for(var s=this._array,h=o(this._shape,e),V=this._shape[e],U=h*V,m=new Array(V),p=new Array(V),d=0;d<this._size;d+=U)for(var c=0;h>c;c++){for(var y=d+c,b=0;V>b;b++)m[b]=s[y],p[b]=b,y+=h;p.sort(n);for(var y=d+c,b=0;V>b;b++)a[y]=p[b],y+=h}return i}},{axis:-1,order:"ascending"}),cumsum:U(function(){function e(e,t,i,n,a){for(var o=0;o<this._size;o+=i)for(var s=0;a>s;s++){var l=o+s,r=l;e[l]=t[l];for(var h=1;n>h;h++)r=l,l+=a,e[l]=e[r]+t[l]}}function t(e,t){e[0]=t[0];for(var i=1;i<e.length;i++)e[i]=e[i-1]+t[i]}return function(i,n){return this._withPreprocess2(i,n,e,t)}}()),cumprod:U(function(){function e(e,t,i,n,a){for(var o=0;o<this._size;o+=i)for(var s=0;a>s;s++){var l=o+s,r=l;e[l]=t[l];for(var h=1;n>h;h++)r=l,l+=a,e[l]=e[r]*t[l]}}function t(e,t){e[0]=t[0];for(var i=1;i<e.length;i++)e[i]=e[i-1]*t[i]}return function(i,n){return this._withPreprocess2(i,n,e,t)}}()),dot:function(){console.warn("TODO")},map:function(e,t){for(var i=this._array,n=this._array,a=i[0],o=i[0],s=this._size,l=1;s>l;l++){var r=i[l];if(a>r)a=r;if(r>o)o=r}for(var h=o-a,V=t-e,l=0;s>l;l++)if(0===h)n[l]=e;else{var r=i[l],U=(r-a)/h;n[l]=V*U+e}return this},add:function(e,t){return this.binaryOperation(this,e,c,t)},sub:function(e,t){return this.binaryOperation(this,e,y,t)},mul:function(e,t){return this.binaryOperation(this,e,b,t)},div:function(e,t){return this.binaryOperation(this,e,k,t)},mod:function(e,t){return this.binaryOperation(this,e,u,t)},and:function(e,t){return this.binaryOperation(this,e,f,t)},or:function(e,t){return this.binaryOperation(this,e,g,t)},xor:function(e,t){return this.binaryOperation(this,e,L,t)},equal:function(e,t){return this.binaryOperation(this,e,_,t)},binaryOperation:function(e,t,i,n){var a=[],s="number"==typeof e,h="number"==typeof t;if(s)a=t._shape.slice();else if(h)a=e._shape.slice();else{for(var V=e._shape.length-1,U=t._shape.length-1,m=e,p=t;V>=0&&U>=0;){if(1==e._shape[V])a.unshift(t._shape[U]),m=e.repeat(t._shape[U],V);else if(1==t._shape[U])a.unshift(e._shape[V]),p=t.repeat(e._shape[V],U);else if(t._shape[U]==e._shape[V])a.unshift(e._shape[V]);else throw new Error(r(e._shape,t._shape));V--,U--}for(var d=V;d>=0;d--)a.unshift(e._shape[d]);for(var d=U;d>=0;d--)a.unshift(t._shape[d]);e=m,t=p}if(!n)n=new x(this._dtype),n.initFromShape(a);else if(!l(a,n._shape))throw new Error(r(a,n._shape));var W,X,K,I,w=n._array;if(s)W=t._shape.length-1,X=!1,K=e,I=t._array;else if(h)W=e._shape.length-1,X=!0,I=t,K=e._array;else W=Math.abs(e._shape.length-t._shape.length),X=e._shape.length>=t._shape.length,K=e._array,I=t._array;var J,v,C,S=o(a,W),F=a[W],E=S*F,A=n._size/E,O=0;if(X)if(h)for(var z=0;A>z;z++)for(var d=0;E>d;d++){switch(J=K[O],v=I,i){case c:C=J+v;break;case y:C=J-v;break;case b:C=J*v;break;case k:C=J/v;break;case u:C=J%v;break;case f:C=J&v;break;case g:C=J|v;break;case L:C=J^v;break;case _:C=J==v;break;default:throw new Error("Unkown operation "+i)}w[O]=C,O++}else for(var z=0;A>z;z++)for(var d=0;E>d;d++){switch(J=K[O],v=I[d],i){case c:C=J+v;break;case y:C=J-v;break;case b:C=J*v;break;case k:C=J/v;break;case u:C=J%v;break;case f:C=J&v;break;case g:C=J|v;break;case L:C=J^v;break;case _:C=J==v;break;default:throw new Error("Unkown operation "+i)}w[O]=C,O++}else if(s)for(var z=0;A>z;z++)for(var d=0;E>d;d++){switch(J=K,v=I[O],i){case c:C=J+v;break;case y:C=J-v;break;case b:C=J*v;break;case k:C=J/v;break;case u:C=J%v;break;case f:C=J&v;break;case g:C=J|v;break;case L:C=J^v;break;case _:C=J==v;break;default:throw new Error("Unkown operation "+i)}w[O]=C,O++}else for(var z=0;A>z;z++)for(var d=0;E>d;d++){switch(J=K[O],v=I[d],i){case c:C=J+v;break;case y:C=J-v;break;case b:C=J*v;break;case k:C=J/v;break;case u:C=J%v;break;case f:C=J&v;break;case g:C=J|v;break;case L:C=J^v;break;case _:C=J==v;break;default:throw new Error("Unkown operation "+i)}w[O]=C,O++}return n},neg:function(){for(var e=this._array,t=0;t<this._size;t++)e[t]=-e[t];return this},sin:function(){return this._mathAdapter(Math.sin)},cos:function(){return this._mathAdapter(Math.cos)},tan:function(){return this._mathAdapter(Math.tan)},abs:function(){return this._mathAdapter(Math.abs)},log:function(){return this._mathAdapter(Math.log)},sqrt:function(){return this._mathAdapter(Math.sqrt)},ceil:function(){return this._mathAdapter(Math.ceil)},floor:function(){return this._mathAdapter(Math.floor)},pow:function(e){for(var t=this._array,i=0;i<this._size;i++)t[i]=Math.pow(t[i],e);return this},_mathAdapter:function(e){for(var t=this._array,i=0;i<this._size;i++)t[i]=e(t[i]);return this},round:function(e){e=Math.floor(e||0);var t=Math.pow(10,e),i=this._array;if(0===e)for(var n=0;n<this._size;n++)i[n]=Math.round(i[n]);else for(var n=0;n<this._size;n++)i[n]=Math.round(i[n]*t)/t;return this},clip:function(e,t){for(var i=this._array,n=0;n<this._size;n++)i[n]=Math.max(Math.min(i[n],t),e);return this},get:function(e,t){function i(e,t){var a=o[e],s=n[e];if(h-1>e)if(a[2]>0)for(var l=a[0];l<a[1];l+=a[2])i(e+1,t+s*l);else for(var l=a[0];l>a[1];l+=a[2])i(e+1,t+s*l);else if(a[2]>0)for(var l=a[0];l<a[1];l+=a[2])for(var m=0;s>m;m++)r[U++]=V[l*s+m+t];else for(var l=a[0];l>a[1];l+=a[2])for(var m=0;s>m;m++)r[U++]=V[l*s+m+t]}if("number"==typeof e)e=e.toString();var n=s(this._shape),a=this._parseRanges(e),o=a[0],l=a[1];if(o.length>this._shape.length)throw new Error("Too many indices");var r,h=o.length;if(l.length)t=new x(this._dtype),t.initFromShape(l),r=t._array;else r=[];var V=this._array,U=0;if(i(0,0),l.length)return t;else return r[0]},set:function(e,t){if("number"==typeof e)e=e.toString();var i=s(this._shape),n=this._parseRanges(e),a=n[0],o=n[1];if(a.length>this._shape.length)throw new Error("Too many indices");var h="number"==typeof t,V=a.length,U=this._array;if(h)var m=t;else{if(!l(o,t.shape()))throw new Error(r(o,t.shape()));var m=t._array}var p=0,d=function(e,t){var n=a[e],o=i[e];if(V-1>e)if(n[2]>0)for(var s=n[0];s<n[1];s+=n[2])d(e+1,t+o*s);else for(var s=n[0];s>n[1];s+=n[2])d(e+1,t+o*s);else if(n[2]>0)for(var s=n[0];s<n[1];s+=n[2])for(var l=0;o>l;l++)if(h)U[s*o+l+t]=m;else U[s*o+l+t]=m[p++];else for(var s=n[0];s>n[1];s+=n[2])for(var l=0;o>l;l++)if(h)U[s*o+l+t]=m;else U[s*o+l+t]=m[p++]};return d(0,0),this},insert:U(function(e,t,n){var a=this._array,s=!1;if("number"==typeof e)e=[e],s=!0;if("number"==typeof t)t=new x([t]);else if(t instanceof Array)t=new x(t);if("undefined"==typeof n)this._shape=[this._size],n=0;for(var l=e[0],h=this._shape[n],U=0;U<e.length;U++){if(e[U]<0)e[U]=h+e[U];if(e[U]>h)throw new Error(V(e[U]));if(e[U]<l)throw new Error("Index must be in ascending order");l=e[U]}var m=this._shape.slice();if(s)m.splice(n,1);else m[n]=e.length;for(var d=t._shape,c=d.length-1,y=m.length-1,b=t;c>=0&&y>=0;){if(1===d[c])b=t.repeat(m[y],c);else if(d[c]!==m[y])throw new Error(r(d,m));c--,y--}t=b;for(var k=o(this._shape,n),h=this._shape[n],u=h*k,f=this._size/u,g=e.length,L=new Uint32Array(f*g),_=0,W=0;W<this._size;W+=u)for(var U=0;g>U;U++){var X=e[U];L[_++]=W+X*k}var K=this._shape.slice();K[n]+=e.length;var I=i(K);if(this._array.length<I)var a=new p[this._dtype](I);else var a=this._array;for(var w=this._array,J=t._array,v=L.length-1,C=this._size,S=L[v],F=I-1,E=t._size-1;v>=0;){for(var U=C-1;U>=S;U--)a[F--]=w[U];C=S,S=L[--v];for(var U=0;k>U;U++){if(0>E)E=t._size-1;a[F--]=J[E--]}}for(var U=C-1;U>=0;U--)a[F--]=w[U];return this._array=a,this._shape=K,this._size=I,this}),append:function(){console.warn("TODO")},"delete":U(function(e,t){var n=this._array;if("number"==typeof e)e=[e];var a=this._size;if("undefined"==typeof t)this._shape=[a],t=0;for(var s=o(this._shape,t),l=this._shape[t],r=s*l,h=0,U=0;a>U;U+=r){for(var m=0,p=e[0],d=0;d<e.length;){if(0>p)p+=l;if(p>l)throw new Error(V(p));if(m>p)throw new Error("Index must be in ascending order");for(var c=m;p>c;c++)for(var y=0;s>y;y++)n[h++]=n[c*s+y+U];m=p+1,p=e[++d]}for(var c=m;l>c;c++)for(var y=0;s>y;y++)n[h++]=n[c*s+y+U]}return this._shape[t]-=e.length,this._size=i(this._shape),this}),_parseRanges:function(e){for(var i=e.split(/\s*,\s*/),n=[],a=[],o=0,s=0;s<i.length;s++)if("..."===i[s])for(var l=this._shape.length-(i.length-s);l>=o;)n.push([0,this._shape[o],1]),a.push(this._shape[o]),o++;else{var r=t(i[s],this._shape[o]);if(n.push(r),i[s].indexOf(":")>=0){var h=Math.floor((r[1]-r[0])/r[2]);h=0>h?0:h,a.push(h)}o++}for(;o<this._shape.length;o++)a.push(this._shape[o]);return[n,a]},toArray:function(){function e(o,s){for(var l=n[o],r=0;l>r;r++)if(a-1>o)e(o+1,s[r]=[]);else s[r]=t[i++]}var t=this._array,i=0,n=this._shape,a=n.length,o=[];return e(0,o),o},copy:function(){var e=new x;return e._array=m.call(this._array),e._shape=this._shape.slice(),e._dtype=this._dtype,e._size=this._size,e},constructor:x},x.range=U(function(e,t,i,n){var a=m.call(arguments),o=a[a.length-1];if("string"==typeof o){var n=o;a.pop()}if(1===a.length)t=a[0],i=1,e=0;else if(2==a.length)i=1;n=n||"number";for(var s=new p[n](Math.ceil((t-e)/i)),l=0,r=e;t>r;r+=i)s[l++]=r;var h=new x;return h._array=s,h._shape=[s.length],h._dtype=n,h._size=s.length,h}),x.zeros=U(function(e,t){var i=new x(t);return i.initFromShape(e),i}),x});