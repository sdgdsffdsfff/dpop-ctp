define("er/events",["require","eoo","mini-event/EventTarget"],function(require){var exports={};exports.notifyError=function(e){if("string"==typeof e)e=new Error(e);return this.fire("error",{error:e}),e};var e=require("eoo").create(require("mini-event/EventTarget"),exports),t=new e;return t.EventBus=e,t});