define("ub-ria/extension/ajax",["require","er/ajax","underscore"],function(require){function e(){var e=require("er/ajax"),t=e.hooks.serializeData;e.hooks.serializeData=function(n,i,a){if(!n&&"application/json"===a)return JSON.stringify(i);else return t.apply(e.hooks,arguments)},e.hooks.serializeData.getKey=t.getKey}return{enable:require("underscore").once(e)}});