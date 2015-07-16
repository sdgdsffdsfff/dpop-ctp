define("esui/TextBox",["require","underscore","./lib","./main","./InputControl","mini-event","./painters"],function(require){function e(){h.apply(this,arguments)}function t(e){var t=e.which||e.keyCode;if(13===t)this.fire("enter");var i={keyCode:t,key:String.fromCharCode(t),ctrlKey:e.ctrlKey,altKey:e.altKey},n=require("mini-event").fromDOMEvent(e,"keypress",i);this.fire("keypress",n)}function i(e,t){var i=s.g(e.inputId);if(!d){var n=e.helper.getPart("placeholder");if("boolean"!=typeof t)t=document.activeElement===i;if(!t&&!e.getRawValue())e.helper.removePartClasses("placeholder-hidden",n);else e.helper.addPartClasses("placeholder-hidden",n)}}function n(){if(i(this,!0),this.autoSelect){var e=s.g(this.inputId);e.select()}this.fire("focus")}function a(){i(this,!1),this.fire("blur")}function o(e){if("input"===e.type||"value"===e.propertyName)this.fire("input")}var r=require("underscore"),s=require("./lib"),l=require("./main"),h=require("./InputControl"),d="placeholder"in document.createElement("input");return e.defaultProperties={width:200},e.prototype.type="TextBox",e.prototype.initOptions=function(t){var i={mode:"text",placeholder:"",autoSelect:!1};if(r.extend(i,e.defaultProperties),!i.name)i.name=this.main.getAttribute("name");if(s.isInput(this.main)){var n=this.main.nodeName.toLowerCase();if("textarea"===n)i.mode="textarea";else{var a=this.main.type;i.mode="password"===a?"password":"text"}if(!i.placeholder)i.placeholder=this.main.getAttribute("placeholder");this.helper.extractOptionsFromInput(this.main,i)}if(r.extend(i,t),!i.hasOwnProperty("title")&&this.main.title)i.title=this.main.title;this.setProperties(i)},e.prototype.getFocusTarget=function(){return s.g(this.inputId)},e.prototype.initStructure=function(){if(s.isInput(this.main)){var e=this.helper.replaceMain();if(s.removeAttribute(this.main,"tabindex"),this.inputId=e.id||this.helper.getId("input"),this.main.id)this.main.id=this.helper.getId();var t=e.cloneNode(!1);s.removeAttribute(t,l.getConfig("instanceAttr")),t.id=this.inputId,this.main.appendChild(t)}else{this.inputId=this.helper.getId("input");var i="textarea"===this.mode?'<textarea id="'+this.inputId+'"':'<input type="'+this.mode+'" placeholder="'+this.placeholder+'" id="'+this.inputId+'"';if(this.name)i+=' name="'+r.escape(this.name)+'"';i+="textarea"===this.mode?"></textarea>":" />",this.main.innerHTML=i}if(!d){var t=s.g(this.inputId),n=document.createElement("label");n.id=this.helper.getId("placeholder"),s.setAttribute(n,"for",t.id),this.helper.addPartClasses("placeholder",n),s.insertAfter(n,t)}},e.prototype.initEvents=function(){var e=s.g(this.inputId);this.helper.addDOMEvent(e,"keypress",t),this.helper.addDOMEvent(e,"focus",n),this.helper.addDOMEvent(e,"blur",a);var i="oninput"in e?"input":"propertychange";this.helper.addDOMEvent(e,i,o),this.helper.delegateDOMEvent(e,"change")},e.prototype.repaint=require("./painters").createRepaint(h.prototype.repaint,{name:"rawValue",paint:function(e,t){var n=s.g(e.inputId),a="oninput"in n?"input":"propertychange";e.helper.removeDOMEvent(n,a),n.value=e.stringifyValue(t),e.helper.addDOMEvent(n,a,o),i(e)}},{name:"title",paint:function(e,t){var i=s.g(e.inputId),n=e.helper.getPart("placeholder");if(t){if(s.setAttribute(e.main,"title",t),s.setAttribute(i,"title",t),n)s.setAttribute(n,"title",t)}else if(s.removeAttribute(e.main,"title"),s.removeAttribute(i,"title"),n)s.removeAttribute(n,"title")}},{name:"maxLength",paint:function(e,t){var i=s.g(e.inputId);if(t=parseInt(t,10),!t||0>=t){try{i.maxLength=void 0,delete i.maxLength}catch(n){}s.removeAttribute(i,"maxlength"),s.removeAttribute(i,"maxLength")}else i.maxLength=t,s.setAttribute(i,"maxlength",t)}},{name:["disabled","readOnly"],paint:function(e,t,i){var n=s.g(e.inputId);n.disabled=t,n.readOnly=i}},{name:"placeholder",paint:function(e,t){var n=s.g(e.inputId);if(d)if(t)s.setAttribute(n,"placeholder",t);else s.removeAttribute(n,"placeholder");else{var a=e.helper.getPart("placeholder");a.innerHTML=r.escape(t||"")}i(e)}},{name:["hint","hintType"],paint:function(e,t,i){var n=e.helper.getPart("hint");if(e.removeState("hint-prefix"),e.removeState("hint-suffix"),!t&&n)s.removeNode(n);if(t){if(!n)n=document.createElement("label"),n.id=e.helper.getId("hint"),e.helper.addPartClasses("hint",n),s.setAttribute(n,"for",e.inputId);n.innerHTML=r.escape(t),i="prefix"===i?"prefix":"suffix";var a="prefix"===i?"insertBefore":"insertAfter",o=s.g(e.inputId);s[a](n,o),e.addState("hint-"+i)}}},{name:["width","hint","hidden"],paint:function(e,t,i,n){if(!n&&!isNaN(t)){if(i){var a=e.helper.getPart("hint");if(a)t-=a.offsetWidth}var o=s.g(e.inputId);o.style.width=t+"px";var r=e.helper.getPart("placeholder");if(r)r.style.maxWidth=t+"px"}}},{name:"height",paint:function(e,t){if(!isNaN(t)){var i=e.helper.getPart("hint"),n=t+"px";if(i)i.style.height=n,i.style.lineHeight=n;var a=s.g(e.inputId);a.style.height=n;var o=e.helper.getPart("placeholder");if(o)o.style.height=n,o.style.lineHeight=n}}}),e.prototype.getValidityLabel=function(){var e=h.prototype.getValidityLabel.apply(this,arguments);if(e)e.set("targetType","textarea"===this.mode?"TextArea":"TextBox");return e},e.prototype.getRawValue=function(){var e=s.g(this.inputId);return e?e.value:this.rawValue||this.value||""},e.prototype.getRawValueProperty=e.prototype.getRawValue,s.inherits(e,h),l.register(e),e});