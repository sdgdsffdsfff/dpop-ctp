define("esui/SearchBox",["require","./lib","esui","./Control","./TextBox","./Button","mini-event","./painters"],function(require){function e(){o.apply(this,arguments)}function t(){this.removeState("clear"),this.addState("focus")}function i(){if(this.hasState("clear"))this.getChild("text").setValue(""),this.removeState("clear");this.fire("search")}var n=require("./lib"),a=require("esui"),o=require("./Control");require("./TextBox"),require("./Button"),e.prototype.type="SearchBox",e.prototype.initOptions=function(e){var t={};if(n.extend(t,e),"false"===t.disabled)t.disabled=!1;if(n.isInput(this.main)){if(!t.placeholder)t.placeholder=n.getAttribute(this.main,"placeholder");if(!t.text)t.text=this.main.value;if(!t.maxLength&&(n.hasAttribute(this.main,"maxlength")||this.main.maxLength>0))t.maxLength=this.main.maxLength}else if(!t.text)t.text=n.getText(this.main);if(!t.title)t.title=this.main.title;o.prototype.initOptions.call(this,t)},e.prototype.initStructure=function(){var e={mode:"text",childName:"text",height:this.height,viewContext:this.viewContext,placeholder:this.placeholder};if(n.isInput(this.main))this.helper.replaceMain();var t=a.create("TextBox",e);t.appendTo(this.main),this.addChild(t);var i={main:document.createElement("span"),childName:"button",content:"搜索",viewContext:this.viewContext},o=a.create("Button",i);o.appendTo(this.main),this.addChild(o)},e.prototype.initEvents=function(){var e=this.getChild("text"),a=require("mini-event").delegate;a(e,this,"input"),a(e,"enter",this,"search"),e.on("keypress",function(e){if(13===e.keyCode)e.preventDefault()}),e.on("focus",t,this),e.on("blur",n.bind(this.removeState,this,"focus"));var o=this.getChild("button");o.on("click",i,this)},e.prototype.getValue=function(){var e=this.getChild("text");return e.getValue()};var r=require("./painters");return e.prototype.repaint=r.createRepaint(o.prototype.repaint,r.attribute("title"),{name:["maxLength","placeholder","text","width","disabled","readOnly"],paint:function(e,t,i,n,a,o,r){var s={maxLength:t,placeholder:i,value:n,width:a,disabled:o,readOnly:r};e.getChild("text").setProperties(s)}},{name:"disabled",paint:function(e,t){if("false"===t)t=!1;var i=e.getChild("button");i.set("disabled",t)}},{name:"fitWidth",paint:function(e,t){var i=t?"addState":"removeState";e[i]("fit-width")}}),e.prototype.getTextProperty=function(){var e=this.getChild("text");return e?e.getValue():this.text},n.inherits(e,o),a.register(e),e});