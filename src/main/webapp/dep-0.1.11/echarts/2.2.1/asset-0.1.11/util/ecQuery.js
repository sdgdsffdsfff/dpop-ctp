define("echarts/util/ecQuery",["require","zrender/tool/util"],function(require){function t(t,e){if("undefined"!=typeof t){if(!e)return t;e=e.split(".");for(var i=e.length,s=0;i>s;){if(t=t[e[s]],"undefined"==typeof t)return;s++}return t}}function e(e,i){for(var s,o=0,a=e.length;a>o;o++)if(s=t(e[o],i),"undefined"!=typeof s)return s}function i(e,i){for(var o,a=e.length;a--;){var n=t(e[a],i);if("undefined"!=typeof n)if("undefined"==typeof o)o=s.clone(n);else s.merge(o,n,!0)}return o}var s=require("zrender/tool/util");return{query:t,deepQuery:e,deepMerge:i}});