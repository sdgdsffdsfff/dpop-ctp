/*! 2015 Baidu Inc. All Rights Reserved */
define("common/ListView",["require","common/util","ui/DrawerActionPanel","eoo","ub-ria/mvc/ListView"],function(require){function e(e){var i=s.findWhere(this.model.getStatusTransitions(),{statusName:e.name});if("click"===e.triggerType)if(i){var n={id:parseInt(e.args),status:i.status};this.fire("modifystatus",n)}else if("modify"===e.name){var r=e.args,a=t.call(this,r),o={url:a};this.popDrawerAction(o).show()}}function t(e){var t=this.model.get("url").getPath(),i=t.lastIndexOf("/"),n=t.substring(0,i+1)+"update~"+e;return n}function i(e){e.preventDefault();var t=String(e.target.get("href"));if("#"===t.charAt(0))t=t.slice(1);this.popDrawerAction({url:t}).show()}function n(e){e.preventDefault(),this.dispose()}function r(e){e.stopPropagation(),e.preventDefault(),this.hide()}function a(e){e.target.hide()}function o(){this.fire("close")}var s=require("common/util");require("ui/DrawerActionPanel");var exports={};exports.popDrawerAction=function(e){e.id=e.id||"drawer-action";var t=this.get(e.id);if(!t)t=this.create("DrawerActionPanel",e),t.render(),t.on("action@submitcancel",n),t.on("action@back",r),t.on("action@saveandclose",a),t.on("close",o,this);else t.setProperties(e);return t},exports.enterDocument=function(){this.$super(arguments),this.getSafely("create").on("click",i,this)},exports.commandHandler=e,exports.uiEvents={"table:command":e},exports.updateItem=function(e){var t=this.model.indexOf(e);if(0>t)throw new Error("No row found");this.get("table").updateRowAt(t,e)},exports.adjustLayout=function(){var e=this.get("table");if(e)e.adjustWidth()};var l=require("eoo").create(require("ub-ria/mvc/ListView"),exports);return l});