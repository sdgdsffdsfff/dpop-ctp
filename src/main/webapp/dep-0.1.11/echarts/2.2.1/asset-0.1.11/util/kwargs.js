define("echarts/util/kwargs",[],function(){function t(t,e){var i=new RegExp("(\\/\\*[\\w\\'\\,\\(\\)\\s\\r\\n\\*]*\\*\\/)|(\\/\\/[\\w\\s\\'][^\\n\\r]*$)|(<![\\-\\-\\s\\w\\>\\/]*>)","gim"),s=new RegExp("\\s+","gim"),o=new RegExp("function.*?\\((.*?)\\)","i"),a=t.toString().replace(i,"").replace(s,"").match(o)[1].split(",");if(e!==Object(e))e={};return function(){var i=Array.prototype.slice.call(arguments),s=i[i.length-1];if(s&&s.constructor===Object)i.pop();else s={};for(var o=0;o<a.length;o++){var n=a[o];if(n in s)i[o]=s[n];else if(n in e&&null==i[o])i[o]=e[n]}return t.apply(this,i)}}return t});