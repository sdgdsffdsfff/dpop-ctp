define("ub-ria/mvc/checker/typeChecker",["require","underscore"],function(require){function e(e,n){var i=n[0],a={Undefined:!0,Null:!0,Array:["array"],String:["string"],Number:["number","enum"],Boolean:["bool"],Object:["object"]},r="";if(null===e)r="Null";else if(void 0===e)r="Undefined";else r=Object.prototype.toString.call(e),r=r.substring(8,r.length-1);return a[r]===!0||t.indexOf(a[r],i)>=0}var t=require("underscore"),n={name:"type",errorMessage:"${title}的类型不符合要求",priority:10,check:e};return n});