/*! 2015 Baidu Inc. All Rights Reserved */
define("audit/FormView",["require","common/util","er/permission","tpl!b1c6f6b6.tpl.html","task/enum","common/FormView","eoo"],function(require){function e(e){if("time"===e.target.getValue())this.getSafely("task-time-dimension").show(),this.getSafely("task-id-dimension").hide(),this.getSafely("id-dimension").setDisabled(!0);else this.getSafely("task-time-dimension").hide(),this.getSafely("task-id-dimension").show(),this.getSafely("id-dimension").required=!0,this.getSafely("id-dimension").setDisabled(!1)}function t(e){if("0"===e.target.getValue())this.getSafely("task-custom-condition").hide(),this.getSafely("trade-id").setDisabled(!0);else this.getSafely("task-custom-condition").show(),this.getSafely("qiushiWuliaoType").hide(),this.getSafely("dspWuliaoType").hide(),this.getSafely("baidudspWuliaoType").hide()}function i(e){if("0"===e.target.getValue())this.getSafely("custom-group-num").hide(),this.getSafely("availableNum").setText(""),this.getSafely("custom-group-num").setDisabled(!0);else this.getSafely("custom-group-num").show(),this.getSafely("custom-group-num").setDisabled(!1),this.getSafely("custom-group-num").required=!0}function n(e){if("0"===e.target.getValue())this.getSafely("customTrade").hide(),this.getSafely("trade-id").setDisabled(!0);else this.getSafely("customTrade").show(),this.getSafely("trade-id").required=!0,this.getSafely("trade-id").setDisabled(!1)}function r(e){if(this.getSafely("beidouWuliaoType").hide(),this.getSafely("qiushiWuliaoType").hide(),this.getSafely("dspWuliaoType").hide(),this.getSafely("baidudspWuliaoType").hide(),"0"===e.target.getValue())this.getSafely("beidouWuliaoType").show();else if("1"===e.target.getValue())this.getSafely("qiushiWuliaoType").show();else if("2"===e.target.getValue())this.getSafely("dspWuliaoType").show();else if("3"===e.target.getValue())this.getSafely("baidudspWuliaoType").show()}function a(e,t){var i=h.omit(this.getEntity(),["taskName","groupNum","isBlind","moduserLevel"]);this.model.data().getGroupNum(i).then(function(e){if(e.hasSuccess){if(this.getSafely("availableNum").setText("1~"+e.data),t)this.getSafely("custom-group-num").setValue(e.data>300?300:e.data)}else this.getSafely("availableNum").setText(e.resultInfo)}.bind(this))}function o(e){var t=e.target,i=t.getRawValue();if(h.every(i,function(t){return t.taskType===e.item.taskType}))t.showValidationMessage("valid",""),this.fire("syncHasSelectedList",{rawValue:i,targetTree:t});else t.selectItems([e.item.id],!1),t.showValidationMessage("invalid","与已选择的任务的标注标签类型不同")}function s(e){var t=e.target,i=t.getValue(),n=this.getSafely(t.targetTree);n.selectItems(i.split(","),!1),o.call(this,{target:n,item:e.item})}function l(){var e="提示",t="是否下载该文件",i=null,n=h.omit(this.getEntity(),["taskName","isBlind","moduserLevel"]);this.model.data().getGroupNum(n).then(function(r){if(r.hasSuccess){var a=parseInt(n.groupNum,10);a=a>r.data?r.data:a,t="该条件下共有"+a+"道题,是否确定下载？",n.groupNum=a,i=function(){this.model.data().downloadWhenCreateReview(n).then(function(e){if(e.hasSuccess)window.open(e.data)})}.bind(this);var o=this.confirm(t,e);o.on("ok",i)}else t=r.resultInfo,this.alert(t,e)}.bind(this))}var h=require("common/util"),d=require("er/permission");require("tpl!b1c6f6b6.tpl.html");var u=require("task/enum").DataType,exports={};exports.template="auditForm",exports.getEntity=function(){var e=this.$super(arguments),t={taskName:this.getSafely("task-name").getValue(),moduserLevel:parseInt(this.getSafely("moduserLevel").getValue(),10),conditionType:parseInt(this.getSafely("conditionType").getValue(),10),isBlind:parseInt(this.getSafely("isBlind").getValue(),10)};if("id"===this.getSafely("taskDimension").getValue()){if(t.taskList=this.getSafely("id-dimension").getValue().split(","),t.taskType=null,this.getSafely("id-dimension").getRawValue().length)t.taskType=this.getSafely("id-dimension").getRawValue()[0].taskType}else if("time"===this.getSafely("taskDimension").getValue())t.beginTime=e.timeDimension.begin.getTime(),t.endTime=e.timeDimension.end.getTime();else t.taskList=[this.model.get("id")];if("1"===this.getSafely("conditionType").getValue()){var i=parseInt(e.dataType[0],10),n=u.fromValue(i).alias+"WuliaoType",r=h.pick(e.tagGroup,function(e){return"0"!==e});if(h.each(h.keys(r),function(e){r[e]=r[e].split(",")}),t.reviewTaskCondition={dataType:i,wuliaoType:"0000"===e[n][0]?null:e[n].join(","),tagValue:r},"1"===this.getSafely("belongTrade").getValue()){var a=this.getSafely("trade-id").getValue().split(",");if(""===this.getSafely("trade-id").getValue())a=[];t.reviewTaskCondition.adTradeType=h.map(this.model.omitValue(a,!0),function(e){return parseInt(e,10)})}}if("1"===this.getSafely("groupNum").getValue())t.groupNum=e.customGroupNum;return t},exports.uiEvents={"taskDimension:change":e,"conditionType:change":t,"groupNum:change":i,"belongTrade:change":n,"dataType:change":r,"custom-group-num:focus":a,"id-dimension:add":o,"trade-id:add":o,"id-selected-dimension:add":s,"trade-selected-id:add":s,"download:click":l},exports.enterDocument=function(){if(this.$super(arguments),this.getSafely("task-custom-condition").hide(),"create"===this.model.get("fromPath"))this.getSafely("custom-group-num").hide(),this.getSafely("task-time-dimension").hide();else{if(this.getSafely("task-time-dimension").hide(),this.getSafely("custom-group-num").required=!0,d.isAllow("managerRole")||d.isAllow("innerAuditRole"))this.getSafely("id-dimension").selectItems([this.model.get("id")],!0),this.getSafely("id-dimension").required=!0;a.call(this,null,!0)}this.getSafely("customTrade").hide()},exports.refreshSelectUI=function(e){if("id-dimension"===e.id)this.getSafely(e.targetTree).datasource=this.model.get("allSelectedTask");else if("trade-id"===e.id)this.getSafely(e.targetTree).datasource=this.model.get("allSelectedTrade");this.getSafely(e.targetTree).refresh()},exports.syncTagGroupUI=function(){this.get("tagGroup").set("datasource",this.model.get("tagDatasource")),this.get("tagGroup").refresh(),this.get("tagGroup").setRawValue(this.model.get("tagValue"))},exports.isTradePanelShow=function(){return"1"===this.getSafely("belongTrade").getValue()};var c=require("common/FormView"),p=require("eoo").create(c,exports);return p});