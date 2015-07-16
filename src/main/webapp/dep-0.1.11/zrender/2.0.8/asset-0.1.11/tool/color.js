define("zrender/tool/color",["require","../tool/util"],function(require){function e(e){O=e}function t(){O=z}function i(e,t){return e=0|e,t=t||O,t[e%t.length]}function n(e){N=e}function r(){B=N}function a(){return N}function o(e,t,i,n,r,a,o){if(!F)F=J.getContext();for(var s=F.createRadialGradient(e,t,i,n,r,a),l=0,h=o.length;h>l;l++)s.addColorStop(o[l][0],o[l][1]);return s.__nonRecursion=!0,s}function s(e,t,i,n,r){if(!F)F=J.getContext();for(var a=F.createLinearGradient(e,t,i,n),o=0,s=r.length;s>o;o++)a.addColorStop(r[o][0],r[o][1]);return a.__nonRecursion=!0,a}function l(e,t,i){e=p(e),t=p(t),e=C(e),t=C(t);for(var n=[],r=(t[0]-e[0])/i,a=(t[1]-e[1])/i,o=(t[2]-e[2])/i,s=(t[3]-e[3])/i,l=0,h=e[0],u=e[1],c=e[2],m=e[3];i>l;l++)n[l]=d([T(Math.floor(h),[0,255]),T(Math.floor(u),[0,255]),T(Math.floor(c),[0,255]),m.toFixed(4)-0],"rgba"),h+=r,u+=a,c+=o,m+=s;return h=t[0],u=t[1],c=t[2],m=t[3],n[l]=d([h,u,c,m],"rgba"),n}function h(e,t){var i=[],n=e.length;if(void 0===t)t=20;if(1===n)i=l(e[0],e[0],t);else if(n>1)for(var r=0,a=n-1;a>r;r++){var o=l(e[r],e[r+1],t);if(a-1>r)o.pop();i=i.concat(o)}return i}function d(e,t){if(t=t||"rgb",e&&(3===e.length||4===e.length)){if(e=X(e,function(e){return e>1?Math.ceil(e):e}),t.indexOf("hex")>-1)return"#"+((1<<24)+(e[0]<<16)+(e[1]<<8)+ +e[2]).toString(16).slice(1);else if(t.indexOf("hs")>-1){var i=X(e.slice(1,3),function(e){return e+"%"});e[1]=i[0],e[2]=i[1]}if(t.indexOf("a")>-1){if(3===e.length)e.push(1);return e[3]=T(e[3],[0,1]),t+"("+e.slice(0,4).join(",")+")"}return t+"("+e.slice(0,3).join(",")+")"}}function u(e){if(e=x(e),e.indexOf("rgba")<0)e=p(e);var t=[],i=0;return e.replace(/[\d.]+/g,function(e){if(3>i)e=0|e;else e=+e;t[i++]=e}),t}function c(e,t){if(!S(e))return e;var i=C(e),n=i[3];if("undefined"==typeof n)n=1;if(e.indexOf("hsb")>-1)i=K(i);else if(e.indexOf("hsl")>-1)i=E(i);if(t.indexOf("hsb")>-1||t.indexOf("hsv")>-1)i=A(i);else if(t.indexOf("hsl")>-1)i=P(i);return i[3]=n,d(i,t)}function p(e){return c(e,"rgba")}function m(e){return c(e,"rgb")}function f(e){return c(e,"hex")}function g(e){return c(e,"hsva")}function y(e){return c(e,"hsv")}function V(e){return c(e,"hsba")}function U(e){return c(e,"hsb")}function b(e){return c(e,"hsla")}function _(e){return c(e,"hsl")}function v(e){for(var t in Y)if(f(Y[t])===f(e))return t;return null}function x(e){return String(e).replace(/\s+/g,"")}function k(e){if(Y[e])e=Y[e];if(e=x(e),e=e.replace(/hsv/i,"hsb"),/^#[\da-f]{3}$/i.test(e)){e=parseInt(e.slice(1),16);var t=(3840&e)<<8,i=(240&e)<<4,n=15&e;e="#"+((1<<24)+(t<<4)+t+(i<<4)+i+(n<<4)+n).toString(16).slice(1)}return e}function L(e,t){if(!S(e))return e;var i=t>0?1:-1;if("undefined"==typeof t)t=0;t=Math.abs(t)>1?1:Math.abs(t),e=m(e);for(var n=C(e),r=0;3>r;r++)if(1===i)n[r]=n[r]*(1-t)|0;else n[r]=(255-n[r])*t+n[r]|0;return"rgb("+n.join(",")+")"}function w(e){if(!S(e))return e;var t=C(p(e));return t=X(t,function(e){return 255-e}),d(t,"rgb")}function W(e,t,i){if(!S(e)||!S(t))return e;if("undefined"==typeof i)i=.5;i=1-T(i,[0,1]);for(var n=2*i-1,r=C(p(e)),a=C(p(t)),o=r[3]-a[3],s=((n*o===-1?n:(n+o)/(1+n*o))+1)/2,l=1-s,h=[],u=0;3>u;u++)h[u]=r[u]*s+a[u]*l;var c=r[3]*i+a[3]*(1-i);if(c=Math.max(0,Math.min(1,c)),1===r[3]&&1===a[3])return d(h,"rgb");else return h[3]=c,d(h,"rgba")}function I(){return"#"+(Math.random().toString(16)+"0000").slice(2,8)}function C(e){e=k(e);var t=e.match(R);if(null===t)throw new Error("The color format error");var i,n,r,a=[];if(t[2])i=t[2].replace("#","").split(""),r=[i[0]+i[1],i[2]+i[3],i[4]+i[5]],a=X(r,function(e){return T(parseInt(e,16),[0,255])});else if(t[4]){var o=t[4].split(",");if(n=o[3],r=o.slice(0,3),a=X(r,function(e){return e=Math.floor(e.indexOf("%")>0?2.55*parseInt(e,0):e),T(e,[0,255])}),"undefined"!=typeof n)a.push(T(parseFloat(n),[0,1]))}else if(t[5]||t[6]){var s=(t[5]||t[6]).split(","),l=parseInt(s[0],0)/360,h=s[1],d=s[2];if(n=s[3],a=X([h,d],function(e){return T(parseFloat(e)/100,[0,1])}),a.unshift(l),"undefined"!=typeof n)a.push(T(parseFloat(n),[0,1]))}return a}function M(e,t){if(!S(e))return e;if(null===t)t=1;var i=C(p(e));return i[3]=T(Number(t).toFixed(4),[0,1]),d(i,"rgba")}function X(e,t){if("function"!=typeof t)throw new TypeError;for(var i=e?e.length:0,n=0;i>n;n++)e[n]=t(e[n]);return e}function T(e,t){if(e<=t[0])e=t[0];else if(e>=t[1])e=t[1];return e}function S(e){return e instanceof Array||"string"==typeof e}function K(e){var t,i,n,r=e[0],a=e[1],o=e[2];if(0===a)t=255*o,i=255*o,n=255*o;else{var s=6*r;if(6===s)s=0;var l=0|s,h=o*(1-a),d=o*(1-a*(s-l)),u=o*(1-a*(1-(s-l))),c=0,p=0,m=0;if(0===l)c=o,p=u,m=h;else if(1===l)c=d,p=o,m=h;else if(2===l)c=h,p=o,m=u;else if(3===l)c=h,p=d,m=o;else if(4===l)c=u,p=h,m=o;else c=o,p=h,m=d;t=255*c,i=255*p,n=255*m}return[t,i,n]}function E(e){var t,i,n,r=e[0],a=e[1],o=e[2];if(0===a)t=255*o,i=255*o,n=255*o;else{var s;if(.5>o)s=o*(1+a);else s=o+a-a*o;var l=2*o-s;t=255*D(l,s,r+1/3),i=255*D(l,s,r),n=255*D(l,s,r-1/3)}return[t,i,n]}function D(e,t,i){if(0>i)i+=1;if(i>1)i-=1;if(1>6*i)return e+6*(t-e)*i;if(1>2*i)return t;if(2>3*i)return e+(t-e)*(2/3-i)*6;else return e}function A(e){var t,i,n=e[0]/255,r=e[1]/255,a=e[2]/255,o=Math.min(n,r,a),s=Math.max(n,r,a),l=s-o,h=s;if(0===l)t=0,i=0;else{i=l/s;var d=((s-n)/6+l/2)/l,u=((s-r)/6+l/2)/l,c=((s-a)/6+l/2)/l;if(n===s)t=c-u;else if(r===s)t=1/3+d-c;else if(a===s)t=2/3+u-d;if(0>t)t+=1;if(t>1)t-=1}return t=360*t,i=100*i,h=100*h,[t,i,h]}function P(e){var t,i,n=e[0]/255,r=e[1]/255,a=e[2]/255,o=Math.min(n,r,a),s=Math.max(n,r,a),l=s-o,h=(s+o)/2;if(0===l)t=0,i=0;else{if(.5>h)i=l/(s+o);else i=l/(2-s-o);var d=((s-n)/6+l/2)/l,u=((s-r)/6+l/2)/l,c=((s-a)/6+l/2)/l;if(n===s)t=c-u;else if(r===s)t=1/3+d-c;else if(a===s)t=2/3+u-d;if(0>t)t+=1;if(t>1)t-=1}return t=360*t,i=100*i,h=100*h,[t,i,h]}var F,J=require("../tool/util"),O=["#ff9277"," #dddd00"," #ffc877"," #bbe3ff"," #d5ffbb","#bbbbff"," #ddb000"," #b0dd00"," #e2bbff"," #ffbbe3","#ff7777"," #ff9900"," #83dd00"," #77e3ff"," #778fff","#c877ff"," #ff77ab"," #ff6600"," #aa8800"," #77c7ff","#ad77ff"," #ff77ff"," #dd0083"," #777700"," #00aa00","#0088aa"," #8400dd"," #aa0088"," #dd0000"," #772e00"],z=O,N="rgba(255,255,0,0.5)",B=N,R=/^\s*((#[a-f\d]{6})|(#[a-f\d]{3})|rgba?\(\s*([\d\.]+%?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+%?)?)\s*\)|hsba?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+)?)%?\s*\)|hsla?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+)?)%?\s*\))\s*$/i,Y={aliceblue:"#f0f8ff",antiquewhite:"#faebd7",aqua:"#0ff",aquamarine:"#7fffd4",azure:"#f0ffff",beige:"#f5f5dc",bisque:"#ffe4c4",black:"#000",blanchedalmond:"#ffebcd",blue:"#00f",blueviolet:"#8a2be2",brown:"#a52a2a",burlywood:"#deb887",cadetblue:"#5f9ea0",chartreuse:"#7fff00",chocolate:"#d2691e",coral:"#ff7f50",cornflowerblue:"#6495ed",cornsilk:"#fff8dc",crimson:"#dc143c",cyan:"#0ff",darkblue:"#00008b",darkcyan:"#008b8b",darkgoldenrod:"#b8860b",darkgray:"#a9a9a9",darkgrey:"#a9a9a9",darkgreen:"#006400",darkkhaki:"#bdb76b",darkmagenta:"#8b008b",darkolivegreen:"#556b2f",darkorange:"#ff8c00",darkorchid:"#9932cc",darkred:"#8b0000",darksalmon:"#e9967a",darkseagreen:"#8fbc8f",darkslateblue:"#483d8b",darkslategray:"#2f4f4f",darkslategrey:"#2f4f4f",darkturquoise:"#00ced1",darkviolet:"#9400d3",deeppink:"#ff1493",deepskyblue:"#00bfff",dimgray:"#696969",dimgrey:"#696969",dodgerblue:"#1e90ff",firebrick:"#b22222",floralwhite:"#fffaf0",forestgreen:"#228b22",fuchsia:"#f0f",gainsboro:"#dcdcdc",ghostwhite:"#f8f8ff",gold:"#ffd700",goldenrod:"#daa520",gray:"#808080",grey:"#808080",green:"#008000",greenyellow:"#adff2f",honeydew:"#f0fff0",hotpink:"#ff69b4",indianred:"#cd5c5c",indigo:"#4b0082",ivory:"#fffff0",khaki:"#f0e68c",lavender:"#e6e6fa",lavenderblush:"#fff0f5",lawngreen:"#7cfc00",lemonchiffon:"#fffacd",lightblue:"#add8e6",lightcoral:"#f08080",lightcyan:"#e0ffff",lightgoldenrodyellow:"#fafad2",lightgray:"#d3d3d3",lightgrey:"#d3d3d3",lightgreen:"#90ee90",lightpink:"#ffb6c1",lightsalmon:"#ffa07a",lightseagreen:"#20b2aa",lightskyblue:"#87cefa",lightslategray:"#789",lightslategrey:"#789",lightsteelblue:"#b0c4de",lightyellow:"#ffffe0",lime:"#0f0",limegreen:"#32cd32",linen:"#faf0e6",magenta:"#f0f",maroon:"#800000",mediumaquamarine:"#66cdaa",mediumblue:"#0000cd",mediumorchid:"#ba55d3",mediumpurple:"#9370d8",mediumseagreen:"#3cb371",mediumslateblue:"#7b68ee",mediumspringgreen:"#00fa9a",mediumturquoise:"#48d1cc",mediumvioletred:"#c71585",midnightblue:"#191970",mintcream:"#f5fffa",mistyrose:"#ffe4e1",moccasin:"#ffe4b5",navajowhite:"#ffdead",navy:"#000080",oldlace:"#fdf5e6",olive:"#808000",olivedrab:"#6b8e23",orange:"#ffa500",orangered:"#ff4500",orchid:"#da70d6",palegoldenrod:"#eee8aa",palegreen:"#98fb98",paleturquoise:"#afeeee",palevioletred:"#d87093",papayawhip:"#ffefd5",peachpuff:"#ffdab9",peru:"#cd853f",pink:"#ffc0cb",plum:"#dda0dd",powderblue:"#b0e0e6",purple:"#800080",red:"#f00",rosybrown:"#bc8f8f",royalblue:"#4169e1",saddlebrown:"#8b4513",salmon:"#fa8072",sandybrown:"#f4a460",seagreen:"#2e8b57",seashell:"#fff5ee",sienna:"#a0522d",silver:"#c0c0c0",skyblue:"#87ceeb",slateblue:"#6a5acd",slategray:"#708090",slategrey:"#708090",snow:"#fffafa",springgreen:"#00ff7f",steelblue:"#4682b4",tan:"#d2b48c",teal:"#008080",thistle:"#d8bfd8",tomato:"#ff6347",turquoise:"#40e0d0",violet:"#ee82ee",wheat:"#f5deb3",white:"#fff",whitesmoke:"#f5f5f5",yellow:"#ff0",yellowgreen:"#9acd32"};return{customPalette:e,resetPalette:t,getColor:i,getHighlightColor:a,customHighlight:n,resetHighlight:r,getRadialGradient:o,getLinearGradient:s,getGradientColors:h,getStepColors:l,reverse:w,mix:W,lift:L,trim:x,random:I,toRGB:m,toRGBA:p,toHex:f,toHSL:_,toHSLA:b,toHSB:U,toHSBA:V,toHSV:y,toHSVA:g,toName:v,toColor:d,toArray:u,alpha:M,getData:C}});