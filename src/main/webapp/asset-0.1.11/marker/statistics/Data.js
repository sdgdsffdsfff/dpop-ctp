/*! 2015 Baidu Inc. All Rights Reserved */
define("marker/statistics/Data",["require","common/util","common/BaseData","eoo","ub-ria/mvc/RequestManager"],function(require){var e=require("common/util"),t=require("common/BaseData"),exports={};exports.getBasicInfo=function(e){return this.request("statistics/getBasicInfo",e,{method:"GET",url:"/getBasicInfo.do",urlPrefix:"ctp/statistics"})};var i=require("eoo").create(t,exports),n={getDimention:{name:"statistics/getBasicInfo",scope:"instance",policy:"auto"}},r=require("ub-ria/mvc/RequestManager");return e.each(n,function(e){r.register(i,e.name,e)}),i});