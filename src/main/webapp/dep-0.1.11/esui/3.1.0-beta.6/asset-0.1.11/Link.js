define("esui/Link",["require","underscore","./lib","./Control","./painters","./main"],function(require){function e(){n.apply(this,arguments)}var t=require("underscore"),i=require("./lib"),n=require("./Control"),a=require("./painters");return e.prototype={constructor:e,type:"Link",getCategory:function(){return"container"},createMain:function(){return document.createElement("a")},initOptions:function(e){var i={};if(t.extend(i,e),null==e.href)i.href=this.main.href;if(null==e.target)i.target=this.main.target;if(null==e.content)i.content=this.main.innerHTML;t.extend(this,i)},initEvents:function(){this.helper.delegateDOMEvent(this.main,"click")},render:function(){if(this.main&&"a"===this.main.nodeName.toLowerCase())n.prototype.render.apply(this,arguments)},repaint:a.createRepaint(n.prototype.repaint,a.attribute("href"),a.attribute("target"),{name:"content",paint:function(e,t){e.helper.disposeChildren(),e.main.innerHTML=t,e.helper.initChildren()}})},i.inherits(e,n),require("./main").register(e),e});