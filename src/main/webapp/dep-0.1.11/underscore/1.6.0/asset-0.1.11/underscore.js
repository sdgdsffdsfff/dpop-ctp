define("underscore/underscore",["require","exports","module"],function(require,exports,module){(function(){var e=this,t=e._,i={},n=Array.prototype,a=Object.prototype,r=Function.prototype,o=n.push,s=n.slice,l=n.concat,h=a.toString,d=a.hasOwnProperty,u=Array.isArray,c=Object.keys,m=r.bind,p=function(e){if(e instanceof p)return e;if(!(this instanceof p))return new p(e);else return void(this._wrapped=e)};if("undefined"!=typeof exports){if("undefined"!=typeof module&&module.exports)exports=module.exports=p;exports._=p}else e._=p;p.VERSION="1.6.0";var f=function(e,t,i){if(void 0===t)return e;switch(null==i?3:i){case 1:return function(i){return e.call(t,i)};case 2:return function(i,n){return e.call(t,i,n)};case 3:return function(i,n,a){return e.call(t,i,n,a)};case 4:return function(i,n,a,r){return e.call(t,i,n,a,r)}}return function(){return e.apply(t,arguments)}},V=function(e,t,i){if(null==e)return p.identity;if(p.isFunction(e))return f(e,t,i);if(p.isObject(e))return p.matches(e);else return p.property(e)};p.each=p.forEach=function(e,t,n){var a,r;if(null==e)return e;if(t=f(t,n),e.length===+e.length)for(a=0,r=e.length;r>a&&t(e[a],a,e)!==i;a++);else{var o=p.keys(e);for(a=0,r=o.length;r>a&&t(e[o[a]],o[a],e)!==i;a++);}return e},p.map=p.collect=function(e,t,i){var n=[];if(null==e)return n;else return t=V(t,i),p.each(e,function(e,i,a){n.push(t(e,i,a))}),n};var U="Reduce of empty array with no initial value";p.reduce=p.foldl=p.inject=function(e,t,i,n){var a=arguments.length>2;if(null==e)e=[];if(t=f(t,n,4),p.each(e,function(e,n,r){if(!a)i=e,a=!0;else i=t(i,e,n,r)}),!a)throw TypeError(U);return i},p.reduceRight=p.foldr=function(e,t,i,n){var a=arguments.length>2;if(null==e)e=[];var r=e.length;if(t=f(t,n,4),r!==+r){var o=p.keys(e);r=o.length}if(p.each(e,function(n,s,l){if(s=o?o[--r]:--r,!a)i=e[s],a=!0;else i=t(i,e[s],s,l)}),!a)throw TypeError(U);return i},p.find=p.detect=function(e,t,i){var n;return t=V(t,i),p.some(e,function(e,i,a){if(t(e,i,a))return n=e,!0;else return void 0}),n},p.filter=p.select=function(e,t,i){var n=[];if(null==e)return n;else return t=V(t,i),p.each(e,function(e,i,a){if(t(e,i,a))n.push(e)}),n},p.reject=function(e,t,i){return p.filter(e,p.negate(V(t)),i)},p.every=p.all=function(e,t,n){var a=!0;if(null==e)return a;else return t=V(t,n),p.each(e,function(e,n,r){if(a=t(e,n,r),!a)return i;else return void 0}),!!a},p.some=p.any=function(e,t,n){var a=!1;if(null==e)return a;else return t=V(t,n),p.each(e,function(e,n,r){if(a=t(e,n,r))return i;else return void 0}),!!a},p.contains=p.include=function(e,t){if(null==e)return!1;if(e.length===+e.length)return p.indexOf(e,t)>=0;else return p.some(e,function(e){return e===t})},p.invoke=function(e,t){var i=s.call(arguments,2),n=p.isFunction(t);return p.map(e,function(e){return(n?t:e[t]).apply(e,i)})},p.pluck=function(e,t){return p.map(e,p.property(t))},p.where=function(e,t){return p.filter(e,p.matches(t))},p.findWhere=function(e,t){return p.find(e,p.matches(t))},p.max=function(e,t,i){var n,a,r=-1/0,o=-1/0;if(!t&&p.isArray(e)){for(var s=0,l=e.length;l>s;s++)if(n=e[s],n>r)r=n}else t=V(t,i),p.each(e,function(e,i,n){if(a=t(e,i,n),a>o||a===-1/0&&r===-1/0)r=e,o=a});return r},p.min=function(e,t,i){var n,a,r=1/0,o=1/0;if(!t&&p.isArray(e)){for(var s=0,l=e.length;l>s;s++)if(n=e[s],r>n)r=n}else t=V(t,i),p.each(e,function(e,i,n){if(a=t(e,i,n),o>a||1/0===a&&1/0===r)r=e,o=a});return r},p.shuffle=function(e){var t,i=0,n=[];return p.each(e,function(e){t=p.random(i++),n[i-1]=n[t],n[t]=e}),n},p.sample=function(e,t,i){if(null==t||i){if(e.length!==+e.length)e=p.values(e);return e[p.random(e.length-1)]}return p.shuffle(e).slice(0,Math.max(0,t))},p.sortBy=function(e,t,i){return t=V(t,i),p.pluck(p.map(e,function(e,i,n){return{value:e,index:i,criteria:t(e,i,n)}}).sort(function(e,t){var i=e.criteria,n=t.criteria;if(i!==n){if(i>n||void 0===i)return 1;if(n>i||void 0===n)return-1}return e.index-t.index}),"value")};var g=function(e){return function(t,i,n){var a={};return i=V(i,n),p.each(t,function(n,r){var o=i(n,r,t);e(a,n,o)}),a}};p.groupBy=g(function(e,t,i){if(p.has(e,i))e[i].push(t);else e[i]=[t]}),p.indexBy=g(function(e,t,i){e[i]=t}),p.countBy=g(function(e,t,i){if(p.has(e,i))e[i]++;else e[i]=1}),p.sortedIndex=function(e,t,i,n){i=V(i,n,1);for(var a=i(t),r=0,o=e.length;o>r;){var s=r+o>>>1;if(i(e[s])<a)r=s+1;else o=s}return r},p.toArray=function(e){if(!e)return[];if(p.isArray(e))return s.call(e);if(e.length===+e.length)return p.map(e,p.identity);else return p.values(e)},p.size=function(e){if(null==e)return 0;else return e.length===+e.length?e.length:p.keys(e).length},p.partition=function(e,t,i){t=V(t,i);var n=[],a=[];return p.each(e,function(e,i,r){(t(e,i,r)?n:a).push(e)}),[n,a]},p.first=p.head=p.take=function(e,t,i){if(null==e)return void 0;if(null==t||i)return e[0];if(0>t)return[];else return s.call(e,0,t)},p.initial=function(e,t,i){return s.call(e,0,Math.max(0,e.length-(null==t||i?1:t)))},p.last=function(e,t,i){if(null==e)return void 0;if(null==t||i)return e[e.length-1];else return s.call(e,Math.max(e.length-t,0))},p.rest=p.tail=p.drop=function(e,t,i){return s.call(e,null==t||i?1:t)},p.compact=function(e){return p.filter(e,p.identity)};var y=function(e,t,i,n){if(t&&p.every(e,p.isArray))return l.apply(n,e);for(var a=0,r=e.length;r>a;a++){var s=e[a];if(!p.isArray(s)&&!p.isArguments(s)){if(!i)n.push(s)}else if(t)o.apply(n,s);else y(s,t,i,n)}return n};p.flatten=function(e,t){return y(e,t,!1,[])},p.without=function(e){return p.difference(e,s.call(arguments,1))},p.uniq=p.unique=function(e,t,i,n){if(null==e)return[];if(p.isFunction(t))n=i,i=t,t=!1;if(i)i=V(i,n);for(var a=[],r=[],o=0,s=e.length;s>o;o++){var l=e[o];if(i)l=i(l,o,e);if(t?!o||r!==l:!p.contains(r,l)){if(t)r=l;else r.push(l);a.push(e[o])}}return a},p.union=function(){return p.uniq(y(arguments,!0,!0,[]))},p.intersection=function(e){if(null==e)return[];for(var t=[],i=arguments.length,n=0,a=e.length;a>n;n++){var r=e[n];if(!p.contains(t,r)){for(var o=1;i>o&&p.contains(arguments[o],r);o++);if(o===i)t.push(r)}else;}return t},p.difference=function(e){var t=y(s.call(arguments,1),!0,!0,[]);return p.filter(e,function(e){return!p.contains(t,e)})},p.zip=function(e){if(null==e)return[];for(var t=p.max(arguments,"length").length,i=Array(t),n=0;t>n;n++)i[n]=p.pluck(arguments,n);return i},p.object=function(e,t){if(null==e)return{};for(var i={},n=0,a=e.length;a>n;n++)if(t)i[e[n]]=t[n];else i[e[n][0]]=e[n][1];return i},p.indexOf=function(e,t,i){if(null==e)return-1;var n=0,a=e.length;if(i)if("number"==typeof i)n=0>i?Math.max(0,a+i):i;else return n=p.sortedIndex(e,t),e[n]===t?n:-1;for(;a>n;n++)if(e[n]===t)return n;return-1},p.lastIndexOf=function(e,t,i){if(null==e)return-1;for(var n=null==i?e.length:i;n--;)if(e[n]===t)return n;return-1},p.range=function(e,t,i){if(arguments.length<=1)t=e||0,e=0;i=arguments[2]||1;for(var n=Math.max(Math.ceil((t-e)/i),0),a=0,r=Array(n);n>a;)r[a++]=e,e+=i;return r};var b=function(){};p.bind=function(e,t){var i,n;if(m&&e.bind===m)return m.apply(e,s.call(arguments,1));if(!p.isFunction(e))throw TypeError("Bind must be called on a function");return i=s.call(arguments,2),n=function(){if(!(this instanceof n))return e.apply(t,i.concat(s.call(arguments)));b.prototype=e.prototype;var a=new b;b.prototype=null;var r=e.apply(a,i.concat(s.call(arguments)));if(Object(r)===r)return r;else return a}},p.partial=function(e){var t=s.call(arguments,1);return function(){for(var i=0,n=t.slice(),a=0,r=n.length;r>a;a++)if(n[a]===p)n[a]=arguments[i++];for(;i<arguments.length;)n.push(arguments[i++]);return e.apply(this,n)}},p.bindAll=function(e){var t,i=1,n=arguments.length;if(1>=n)throw Error("bindAll must be passed function names");for(;n>i;i++)t=arguments[i],e[t]=f(e[t],e,1/0);return e},p.memoize=function(e,t){var i=function(n){var a=i.cache,r=t?t.apply(this,arguments):n;if(!p.has(a,r))a[r]=e.apply(this,arguments);return a[n]};return i.cache={},i},p.delay=function(e,t){var i=s.call(arguments,2);return setTimeout(function(){return e.apply(null,i)},t)},p.defer=function(e){return p.delay.apply(p,[e,1].concat(s.call(arguments,1)))},p.throttle=function(e,t,i){var n,a,r,o=null,s=0;if(!i)i={};var l=function(){if(s=i.leading===!1?0:p.now(),o=null,r=e.apply(n,a),!o)n=a=null};return function(){var h=p.now();if(!s&&i.leading===!1)s=h;var d=t-(h-s);if(n=this,a=arguments,0>=d||d>t){if(clearTimeout(o),o=null,s=h,r=e.apply(n,a),!o)n=a=null}else if(!o&&i.trailing!==!1)o=setTimeout(l,d);return r}},p.debounce=function(e,t,i){var n,a,r,o,s,l=function(){var h=p.now()-o;if(t>h&&h>0)n=setTimeout(l,t-h);else if(n=null,!i)if(s=e.apply(r,a),!n)r=a=null};return function(){r=this,a=arguments,o=p.now();var h=i&&!n;if(!n)n=setTimeout(l,t);if(h)s=e.apply(r,a),r=a=null;return s}},p.once=function(e){var t,i=!1;return function(){if(i)return t;else return i=!0,t=e.apply(this,arguments),e=null,t}},p.wrap=function(e,t){return p.partial(t,e)},p.negate=function(e){return function(){return!e.apply(this,arguments)}},p.compose=function(){var e=arguments;return function(){for(var t=arguments,i=e.length-1;i>=0;i--)t=[e[i].apply(this,t)];return t[0]}},p.after=function(e,t){return function(){if(--e<1)return t.apply(this,arguments);else return void 0}},p.keys=function(e){if(!p.isObject(e))return[];if(c)return c(e);var t=[];for(var i in e)if(p.has(e,i))t.push(i);return t},p.values=function(e){for(var t=p.keys(e),i=t.length,n=Array(i),a=0;i>a;a++)n[a]=e[t[a]];return n},p.pairs=function(e){for(var t=p.keys(e),i=t.length,n=Array(i),a=0;i>a;a++)n[a]=[t[a],e[t[a]]];return n},p.invert=function(e){for(var t={},i=p.keys(e),n=0,a=i.length;a>n;n++)t[e[i[n]]]=i[n];return t},p.functions=p.methods=function(e){var t=[];for(var i in e)if(p.isFunction(e[i]))t.push(i);return t.sort()},p.extend=function(e){if(!p.isObject(e))return e;for(var t,i,n=1,a=arguments.length;a>n;n++){t=arguments[n];for(i in t)e[i]=t[i]}return e},p.pick=function(e,t,i){var n,a={};if(p.isFunction(t))for(n in e){var r=e[n];if(t.call(i,r,n,e))a[n]=r}else for(var o=l.apply([],s.call(arguments,1)),h=0,d=o.length;d>h;h++)if(n=o[h],n in e)a[n]=e[n];return a},p.omit=function(e,t,i){if(p.isFunction(t))t=p.negate(t);else{var n=p.map(l.apply([],s.call(arguments,1)),String);t=function(e,t){return!p.contains(n,t)}}return p.pick(e,t,i)},p.defaults=function(e){if(!p.isObject(e))return e;for(var t=1,i=arguments.length;i>t;t++){var n=arguments[t];for(var a in n)if(void 0===e[a])e[a]=n[a]}return e},p.clone=function(e){if(!p.isObject(e))return e;else return p.isArray(e)?e.slice():p.extend({},e)},p.tap=function(e,t){return t(e),e};var _=function(e,t,i,n){if(e===t)return 0!==e||1/e===1/t;if(null==e||null==t)return e===t;if(e instanceof p)e=e._wrapped;if(t instanceof p)t=t._wrapped;var a=h.call(e);if(a!==h.call(t))return!1;switch(a){case"[object RegExp]":case"[object String]":return""+e==""+t;case"[object Number]":if(e!=+e)return t!=+t;else return 0==e?1/e==1/t:e==+t;case"[object Date]":case"[object Boolean]":return+e===+t}if("object"!=typeof e||"object"!=typeof t)return!1;for(var r=i.length;r--;)if(i[r]===e)return n[r]===t;var o=e.constructor,s=t.constructor;if(o!==s&&"constructor"in e&&"constructor"in t&&!(p.isFunction(o)&&o instanceof o&&p.isFunction(s)&&s instanceof s))return!1;i.push(e),n.push(t);var l,d;if("[object Array]"===a){if(l=e.length,d=l===t.length)for(;l--&&(d=_(e[l],t[l],i,n)););}else{var u,c=p.keys(e);if(l=c.length,d=p.keys(t).length==l)for(;l--&&(u=c[l],d=p.has(t,u)&&_(e[u],t[u],i,n)););}return i.pop(),n.pop(),d};if(p.isEqual=function(e,t){return _(e,t,[],[])},p.isEmpty=function(e){if(null==e)return!0;if(p.isArray(e)||p.isString(e)||p.isArguments(e))return 0===e.length;for(var t in e)if(p.has(e,t))return!1;return!0},p.isElement=function(e){return!(!e||1!==e.nodeType)},p.isArray=u||function(e){return"[object Array]"===h.call(e)},p.isObject=function(e){return e===Object(e)},p.each(["Arguments","Function","String","Number","Date","RegExp"],function(e){p["is"+e]=function(t){return h.call(t)==="[object "+e+"]"}}),!p.isArguments(arguments))p.isArguments=function(e){return p.has(e,"callee")};if("function"!=typeof/./)p.isFunction=function(e){return"function"==typeof e};p.isFinite=function(e){return isFinite(e)&&!isNaN(parseFloat(e))},p.isNaN=function(e){return p.isNumber(e)&&e!==+e},p.isBoolean=function(e){return e===!0||e===!1||"[object Boolean]"===h.call(e)},p.isNull=function(e){return null===e},p.isUndefined=function(e){return void 0===e},p.has=function(e,t){return null!=e&&d.call(e,t)},p.noConflict=function(){return e._=t,this},p.identity=function(e){return e},p.constant=function(e){return function(){return e}},p.noop=function(){},p.property=function(e){return function(t){return t[e]}},p.matches=function(e){return function(t){if(null==t)return p.isEmpty(e);if(t===e)return!0;for(var i in e)if(e[i]!==t[i])return!1;return!0}},p.times=function(e,t,i){var n=Array(Math.max(0,e));t=f(t,i,1);for(var a=0;e>a;a++)n[a]=t(a);return n},p.random=function(e,t){if(null==t)t=e,e=0;return e+Math.floor(Math.random()*(t-e+1))},p.now=Date.now||function(){return(new Date).getTime()};var k={escape:{"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;"}};k.unescape=p.invert(k.escape);var v={escape:RegExp("["+p.keys(k.escape).join("")+"]","g"),unescape:RegExp("("+p.keys(k.unescape).join("|")+")","g")};p.each(["escape","unescape"],function(e){p[e]=function(t){if(null==t)return"";else return(""+t).replace(v[e],function(t){return k[e][t]})}}),p.result=function(e,t){if(null==e)return void 0;var i=e[t];return p.isFunction(i)?e[t]():i};var x=0;p.uniqueId=function(e){var t=++x+"";return e?e+t:t},p.templateSettings={evaluate:/<%([\s\S]+?)%>/g,interpolate:/<%=([\s\S]+?)%>/g,escape:/<%-([\s\S]+?)%>/g};var L=/(.)^/,w={"'":"'","\\":"\\","\r":"r","\n":"n","\u2028":"u2028","\u2029":"u2029"},W=/\\|'|\r|\n|\u2028|\u2029/g,X=function(e){return"\\"+w[e]};p.template=function(e,t,i){i=p.defaults({},i,p.templateSettings);var n=RegExp([(i.escape||L).source,(i.interpolate||L).source,(i.evaluate||L).source].join("|")+"|$","g"),a=0,r="__p+='";if(e.replace(n,function(t,i,n,o,s){if(r+=e.slice(a,s).replace(W,X),a=s+t.length,i)r+="'+\n((__t=("+i+"))==null?'':_.escape(__t))+\n'";else if(n)r+="'+\n((__t=("+n+"))==null?'':__t)+\n'";else if(o)r+="';\n"+o+"\n__p+='";return t}),r+="';\n",!i.variable)r="with(obj||{}){\n"+r+"}\n";r="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+r+"return __p;\n";try{var o=Function(i.variable||"obj","_",r)}catch(s){throw s.source=r,s}if(t)return o(t,p);var l=function(e){return o.call(this,e,p)},h=i.variable||"obj";return l.source="function("+h+"){\n"+r+"}",l},p.chain=function(e){var t=p(e);return t._chain=!0,t};var I=function(e){return this._chain?p(e).chain():e};p.mixin=function(e){p.each(p.functions(e),function(t){var i=p[t]=e[t];p.prototype[t]=function(){var e=[this._wrapped];return o.apply(e,arguments),I.call(this,i.apply(p,e))}})},p.mixin(p),p.each(["pop","push","reverse","shift","sort","splice","unshift"],function(e){var t=n[e];p.prototype[e]=function(){var i=this._wrapped;if(t.apply(i,arguments),("shift"===e||"splice"===e)&&0===i.length)delete i[0];return I.call(this,i)}}),p.each(["concat","join","slice"],function(e){var t=n[e];p.prototype[e]=function(){return I.call(this,t.apply(this._wrapped,arguments))}}),p.prototype.value=function(){return this._wrapped}}).call(this)}),define("underscore",["underscore/underscore"],function(e){return e});