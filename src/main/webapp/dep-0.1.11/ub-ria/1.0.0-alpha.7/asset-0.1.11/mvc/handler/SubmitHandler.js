define("ub-ria/mvc/handler/SubmitHandler",["require","eoo"],function(require){var exports={};exports.nextSubmitHandler=null,exports.setNextSubmitHandler=function(e){this.nextSubmitHandler=e},exports.getNextSubmitHandler=function(){return this.nextSubmitHandler},exports.handle=function(e,t){this.next(e,t)},exports.next=function(e,t){var n=this.getNextSubmitHandler();if(n)n.handle(e,t)};var e=require("eoo").create(exports);return e});