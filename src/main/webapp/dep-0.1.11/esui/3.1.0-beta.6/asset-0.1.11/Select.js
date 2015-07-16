define("esui/Select",["require","underscore","./lib","./InputControl","./Layer","./painters","./main"],function(require){function e(e){for(var t=s.event.getTarget(e),i=this.layer.getElement();t&&t!==i&&!s.hasAttribute(t,"data-index");)t=t.parentNode;if(t&&!this.helper.isPart(t,"item-disabled")){var n=t.getAttribute("data-index");this.set("selectedIndex",+n),this.layer.hide()}}function t(){h.apply(this,arguments)}function i(){l.apply(this,arguments),this.layer=new t(this)}function n(e){if(null==e.selectedIndex&&null==e.rawValue&&null==e.value)e.selectedIndex=-1;if(null!=e.rawValue)e.value=null,e.selectedIndex=null;else if(null!=e.value)e.selectedIndex=null;if(null==e.selectedIndex&&(null!=e.value||null!=e.rawValue)){e.selectedIndex=-1;for(var t=e.rawValue||e.value,i=0;i<e.datasource.length;i++)if(e.datasource[i].value==t){e.selectedIndex=i;break}delete e.value,delete e.rawValue}if(e.selectedIndex<0||e.selectedIndex>=e.datasource.length)if(e.emptyText)e.selectedIndex=-1;else{e.selectedIndex=-1;for(var i=0;i<e.datasource.length;i++)if(!e.datasource[i].disabled){e.selectedIndex=i;break}}}function a(){this.fire("layerrendered",{layer:this.layer})}function o(e){var t=e.helper.getPart("text"),i=-1===e.selectedIndex?null:e.datasource[e.selectedIndex];t.innerHTML=e.getDisplayHTML(i);var n=e.layer.getElement(!1);if(n)e.layer.syncState(n)}var r=require("underscore"),s=require("./lib"),l=require("./InputControl"),h=require("./Layer");s.inherits(t,h),t.prototype.nodeName="ol",t.prototype.render=function(e){for(var t="",i=0;i<this.control.datasource.length;i++){var n=this.control.datasource[i],a=this.control.helper.getPartClasses("item");if(n.disabled)a.push.apply(a,this.control.helper.getPartClasses("item-disabled"));t+='<li data-index="'+i+'" class="'+a.join(" ")+'">',t+=this.control.getItemHTML(n),t+="</li>"}e.innerHTML=t},t.prototype.initBehavior=function(t){this.control.helper.addDOMEvent(t,"click",e)},t.prototype.syncState=function(e){for(var t=this.control.helper.getPartClasses("item-selected"),i=s.getChildren(e),n=i.length-1;n>=0;n--){var a=i[n];if(n===this.control.selectedIndex)s.addClasses(a,t);else s.removeClasses(a,t)}},t.prototype.dock={strictWidth:!0},i.prototype.type="Select",i.prototype.initOptions=function(e){var t={datasource:[]},i={};if(r.extend(i,t,e),"select"===this.main.nodeName.toLowerCase()){i.datasource=[];for(var n=this.main.getElementsByTagName("option"),a=0,o=n.length;o>a;a++){var s=n[a],l={name:s.name||s.text,value:s.value};if(s.disabled)l.disabled=!0;if(i.datasource.push(l),s.selected&&null==i.selectedIndex&&null==i.value&&null==i.rawValue)i.selectedIndex=s.value?a:0}this.helper.extractOptionsFromInput(this.main,i)}if("string"==typeof i.selectedIndex)i.selectedIndex=+i.selectedIndex;this.setProperties(i)},i.prototype.itemTemplate="<span>${text}</span>",i.prototype.getItemHTML=function(e){var t={text:r.escape(e.name||e.text),value:r.escape(e.value)};return s.format(this.itemTemplate,t)},i.prototype.displayTemplate="${text}",i.prototype.getDisplayHTML=function(e){if(!e)return r.escape(this.emptyText||"");var t={text:r.escape(e.name||e.text),value:r.escape(e.value)};return s.format(this.displayTemplate,t)},i.prototype.initStructure=function(){if("select"===this.main.nodeName.toLowerCase())this.helper.replaceMain();this.main.tabIndex=0,this.main.innerHTML=this.helper.getPartHTML("text","span")},i.prototype.initEvents=function(){this.helper.addDOMEvent(this.main,"click",r.bind(this.layer.toggle,this.layer)),this.layer.on("rendered",r.bind(a,this))},i.prototype.getRawValue=function(){if(this.selectedIndex<0)return null;var e=this.datasource[this.selectedIndex];return e?e.value:null};var m=require("./painters");return i.prototype.repaint=m.createRepaint(l.prototype.repaint,m.style("width"),m.style("height"),{name:"datasource",paint:function(e){e.layer.repaint()}},{name:["selectedIndex","emptyText","datasource"],paint:o},{name:["disabled","hidden","readOnly"],paint:function(e,t,i,n){if(t||i||n)e.layer.hide()}}),i.prototype.updateDatasource=function(e){if(!e)e=this.datasource;this.datasource=e;var t={name:"datasource"};this.repaint([t],{datasource:t})},i.prototype.setProperties=function(e){if(null==e.datasource)e.datasource=this.datasource;if(null==e.value&&null==e.rawValue&&null==e.selectedIndex&&e.datasource===this.datasource)e.selectedIndex=this.selectedIndex;if(!e.hasOwnProperty("emptyText"))e.emptyText=this.emptyText;n(e);var t=l.prototype.setProperties.apply(this,arguments);if(t.hasOwnProperty("selectedIndex"))this.fire("change");return t},i.prototype.dispose=function(){if(!this.helper.isInStage("DISPOSED")){if(this.layer)this.layer.dispose(),this.layer=null;l.prototype.dispose.apply(this,arguments)}},i.prototype.getSelectedItem=function(){return this.get("datasource")[this.get("selectedIndex")]},s.inherits(i,l),require("./main").register(i),i});