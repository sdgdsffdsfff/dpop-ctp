define("mini-event/EventTarget",["require","./lib","./Event","./EventQueue"],function(require){function e(){}var t=require("./lib"),i=require("./Event"),n=require("./EventQueue");return e.prototype.on=function(e,i,a,r){if(!this.miniEventPool)this.miniEventPool={};if(!this.miniEventPool.hasOwnProperty(e))this.miniEventPool[e]=new n;var o=this.miniEventPool[e];if(r=t.extend({},r),a)r.thisObject=a;o.add(i,r)},e.prototype.once=function(e,i,n,a){a=t.extend({},a),a.once=!0,this.on(e,i,n,a)},e.prototype.un=function(e,t,i){if(this.miniEventPool&&this.miniEventPool.hasOwnProperty(e)){var n=this.miniEventPool[e];n.remove(t,i)}},e.prototype.fire=function(e,t){if(1===arguments.length&&"object"==typeof e)t=e,e=t.type;if(!e)throw new Error("No event type specified");if("*"===e)throw new Error("Cannot fire global event");var n=t instanceof i?t:new i(e,t);n.target=this;var a=this["on"+e];if("function"==typeof a)a.call(this,n);if(this.miniEventPool&&this.miniEventPool.hasOwnProperty(e)){var r=this.miniEventPool[e];r.execute(n,this)}if(this.miniEventPool&&this.miniEventPool.hasOwnProperty("*")){var o=this.miniEventPool["*"];o.execute(n,this)}return n},e.prototype.destroyEvents=function(){if(this.miniEventPool){for(var e in this.miniEventPool)if(this.miniEventPool.hasOwnProperty(e))this.miniEventPool[e].dispose();this.miniEventPool=null}},e.enable=function(i){i.miniEventPool={},t.extend(i,e.prototype)},e});