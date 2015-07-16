define("esui/Validity",["require","underscore","./lib","./Control","./Helper","./painters","./main"],function(require){function e(){a.apply(this,arguments)}function t(e,t){var i=e.target,n=null;if(i||e.targetType){var a={type:e.targetType||i.type,skin:i&&i.skin};n=new r(a)}var o=e.helper.getPartClasses();if(n)o.push.apply(o,n.getPartClasses("validity-label"));if(t)if(o.push.apply(o,e.helper.getPartClasses(t)),n)o.push.apply(o,n.getPartClasses("validity-label-"+t));if(i&&i.isHidden()||e.isHidden())if(o.push.apply(o,e.helper.getStateClasses("hidden")),i)o.push.apply(o,i.helper.getPartClasses("validity-label-hidden"));return o}var i=require("underscore"),n=require("./lib"),a=require("./Control"),r=require("./Helper");return e.prototype.type="Validity",e.prototype.createMain=function(){return document.createElement("label")},e.prototype.initOptions=function(t){var n=i.extend({},e.defaultProperties,t);a.prototype.initOptions.call(this,n)},e.prototype.display=function(e,t){this.main.innerHTML=t},e.prototype.repaint=require("./painters").createRepaint(a.prototype.repaint,{name:["target","targetType"],paint:function(e){var i=e.validity?e.validity.getValidState():"",n=t(e,i);e.main.className=n.join(" ")}},{name:"focusTarget",paint:function(e,t){if("label"===e.main.nodeName.toLowerCase())if(t&&t.id)n.setAttribute(e.main,"for",t.id);else n.removeAttribute(e.main,"for")}},{name:"validity",paint:function(e,n){var a=n&&n.getValidState(),r=t(e,a);if(e.main.className=r.join(" "),e.disposeChildren(),n){var o=n.getCustomMessage();if(!o){var s=i.find(n.getStates(),function(e){return!e.getState()});o=s&&s.getMessage()}if(e.display(a,o||"",n),e.helper.initChildren(),o)e.show();else e.hide()}else e.main.innerHTML="",e.hide()}}),e.prototype.dispose=function(){if(!this.helper.isInStage("DISPOSED")){if(this.target)this.target.validityLabel=null,this.target=null;if(this.focusTarget=null,this.main.parentNode)this.main.parentNode.removeChild(this.main);a.prototype.dispose.apply(this,arguments)}},n.inherits(e,a),require("./main").register(e),e});