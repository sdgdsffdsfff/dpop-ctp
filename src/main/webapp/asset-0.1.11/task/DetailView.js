/*! 2015 Baidu Inc. All Rights Reserved */
define("task/DetailView",["require","er/events","tpl!b1c6f6b6.tpl.html","esui/controlHelper","ub-ria/mvc/BaseView","eoo"],function(require){function e(){if("修改"!==event.target.innerHTML&&this.get("tagCtrl"))this.get("tagCtrl").hideTradePanel()}function t(){this.fire("refresh")}var exports={},i=require("er/events");require("tpl!b1c6f6b6.tpl.html");var n=require("esui/controlHelper");exports.template="taskDetail",exports.uiEvents={"refresh:click":t,"tagCtrl:changeTrade":"changeTrade","tagCtrl:toggleTradePanel":"toggleTradeList"},exports.enterDocument=function(){this.$super(arguments),n.addDOMEvent(this,this.get("detailView").main,"click",e)},exports.changeTrade=function(e){this.fire("tradeChange",{tradeId:e.tradeId,taskType:this.model.get("detailInfo").taskType,callback:function(){this.get("tagCtrl").setTagDatasource(this.model.get("attachDatasource")),this.get("tagCtrl").setRawValue({tag:this.model.get("attachValue")})}.bind(this)})},exports.toggleTradeList=function(e){var t=e.clickButton.getBoundingClientRect(),i=e.target.main.getBoundingClientRect(),n=e.tradePanel.main;n.style.top=t.bottom-i.top+"px"},exports.dispose=function(){var e=this.get("tagCtrl");if(e&&""!==e.getAllNeedData().id)i.fire("detailSubmit",{value:e.getAllNeedData()});this.$super(arguments)};var r=require("ub-ria/mvc/BaseView"),a=require("eoo").create(r,exports);return a});