define("esui/Schedule",["require","./lib","./InputControl","./controlHelper","./main"],function(require){function e(){T.apply(this,arguments)}function t(){function e(e){for(var t=[],i=0;7>i&&i<e.length;i++){t[i]=[];for(var n=0;24>n;n++)t[i][n]=e[i]}return t}return[{text:"全周投放",tip:"周一到周日全天投放",getValue:function(){return e([1,1,1,1,1,1,1])}},{text:"周一到周五投放",tip:"周一到周五全天投放",getValue:function(){return e([1,1,1,1,1,0,0])}},{text:"周末投放",tip:"周六、周日全天投放",getValue:function(){return e([0,0,0,0,0,1,1])}}]}function i(){for(var e=[],t=0;7>t;t++){var i=[];e.push(i);for(var n=0;24>n;n++)i.push(0)}return e}function n(e,t){return A.getPartClasses(e,t).join(" ")}function a(e,t){return A.getId(e,t)}function o(e){var t=e,i=[],a='<span class="${clazz}" data-item="${index}" >${text}</span>',o=n(t,"shortcut-text-item");i.push('<span class="'+o+'">快速设定：</span>');for(var r=t.shortcut,s=n(t,"shortcut-item"),l=0,h=r.length;h>l;l++){var m=r[l];i.push(E.format(a,{clazz:s,text:m.text,index:l}))}return i.join("")}function r(e){E.g(a(e,"body")).innerHTML=""+s(e)+l(e)+h(e)}function s(e){var t=e,i=[],o=n(t,"time-line"),r=a("body-head");i.push('<div class="',o,'" id="',r+'">');for(var s=n(t,"time-head"),l=0;24>=l;l+=2)i.push('<div class="',s,'" data-time="',l,'" ','id="',a(t,"time-head"+l),'">',l+":00","</div>");return i.push("</div>"),i.join("")}function l(e){var t=e,i=[],o=n(t,"day-head"),r=a(t,"day-head");i.push('<div id="',r,'" class="',o,'">');for(var s=n(t,"day"),l='<div class="'+s+'"><input type="checkbox" id="${dayId}" value="${value}">&nbsp;<label for="${dayId}">${dayWord}</label></div>',h=t.dayTexts,m=0;7>m;m++)i.push(E.format(l,{dayWord:h[m],dayId:a(t,"line-state"+m),value:m}));return i.push("</div>"),i.join("")}function h(e){var t=e,i=[],o='<div class="${timeClass}" id="${itemId}" data-day="${dayIndex}" data-time-item="1" data-time="${timeIndex}"></div>',r=n(t,"time-body"),s=a(t,"time-body");i.push('<div id="',s,'" class="',r,'">');for(var l=n(t,"line"),h=0;7>h;h++){var m=a(t,"line"+h);i.push('<div class="',l,'" id="',m,'">');for(var d=0;24>d;d++){var c=a(t,"time_"+h+"_"+d);i.push(E.format(o,{itemId:c,timeClass:n(t,"time"),dayIndex:h,timeIndex:d}))}i.push("</div>")}return i.push("</div>"),i.join("")}function m(e,t){for(var i=e,n=A.getPartClasses(i,"time-selected"),o=A.getPartClasses(i,"time-hover"),r=0;7>r;r++){var s=[],l=E.g(a(i,"line"+r));V(e,l);for(var h=0;24>h;h++){var m=E.g(a(i,"time_"+r+"_"+h)),c=t[r][h];if(c)E.addClasses(m,n);else E.removeClasses(m,n);E.removeClasses(m,o),s.push(c)}d(i,s,l,r)}}function d(e,t,i,o){var r=e,s=o,l=E.g(a(r,"line-state"+s));l.checked=!1;for(var h,m=/1{3,}/g,d=t.join(""),V=n(r,"continue-covertimes"),p='<div class="${coverClass}"><strong>${text}</strong></div>';null!=(h=m.exec(d));){var U=h[0].length,u=h.index,f=u+U,g=document.createElement("aside"),y=";width:"+25*U+"px;top:0;left:"+25*u+"px;";l.checked=24===U?!0:!1,g.setAttribute("data-start-time",u),g.setAttribute("data-end-time",f),g.setAttribute("data-day",s),g.className=V,g.style.cssText+=y,g.innerHTML=E.format(p,{start:u,end:f,text:24===U?"全天投放":u+".00-"+f+".00",coverClass:n(r,"covertimes-tip")}),i.appendChild(g),A.addDOMEvent(r,g,"mouseover",E.curry(c,g))}}function c(e){e.style.display="none"}function V(e,t){for(var i=t.getElementsByTagName("aside"),n=i.length;n;){var a=i[0];if(null!=a.getAttribute("data-day"))A.clearDOMEvents(e,a),t.removeChild(a);n--}}function p(e,t,i,o){var r=e;t=t||a(r,"tip");var s=E.g(t);if(s)s.style.top=i.y+"px",s.style.left=i.x+"px",s.innerHTML=o;else{var l=";position:absolute;z-index:50;background:#fff6bd;top:"+i.y+"px;left:"+i.x+"px;display:none;",h=n(r,"shortcut-item-tip");s=document.createElement("div"),s.style.cssText=l,s.id=t,s.className=h,s.innerHTML=o,document.body.appendChild(s),r.followTip[t]=s}return r.tipElementTime=setTimeout(function(){s.style.display="block"},100),s}function U(e,t){clearTimeout(e.tipElementTime);var i=E.g(t);i&&(i.style.display="none")}function u(e){var t=E.event.getTarget(e);if("input"===t.nodeName.toLowerCase()){for(var i=this,n=t,a=parseInt(n.value,10),o=n.checked,r=C(i.rawValue),s=r[a],l=0,h=s.length;h>l;l++)s[l]=o?1:0;i.setRawValue(r)}}function f(e){var t=E.event.getTarget(e);if(t&&E.hasAttribute(t,"data-item")){var i=t.getAttribute("data-item"),n=this.shortcut[i].getValue;"function"==typeof n&&n.call(this);var a;if("function"==typeof n)a=n.call(this);else a=n;this.setRawValue(a)}}function g(e){var t=E.event.getTarget(e);if(t&&t.getAttribute("data-item")){var i=t,n=this;E.event.getMousePosition(e);var o={};o.y=e.pageY+20,o.x=e.pageX+10;var r=i,s=r.getAttribute("data-item"),l=a(n,"shortcut-item")+s;setTimeout(function(){var e=E.g(l);if(e)e.style.top=o.y+"px",e.style.left=o.x+"px"},0)}}function y(e,t){var i=E.event.getTarget(t);if(i&&i.getAttribute("data-item")){var n=i;E.event.getMousePosition(t);var o={};o.y=t.pageY+20,o.x=t.pageX+10;var r=this,s=n,l=s.getAttribute("data-item"),h=a(r,"shortcut-item")+l,m=A.getPartClasses(r,"shortcut-item-hover");if(e){E.addClasses(s,m);var d=r.shortcut[l].tip;p(r,h,o,d)}else E.removeClasses(s,m),U(r,h)}}function b(e){var t=E.event.getTarget(e);if(t&&t.getAttribute("data-time-item")){var i=t;E.addClasses(i,A.getPartClasses(this,"time-hover")),E.event.getMousePosition(e);var o={};o.y=e.pageY+20,o.x=e.pageX+10;var r=this,s=parseInt(i.getAttribute("data-time"),10),l=parseInt(i.getAttribute("data-day"),10),h=E.format(O,{time:"<strong>"+s+":00</strong>&nbsp;—&nbsp;<strong>"+(s+1)+":00</strong>",text:"点击/拖动鼠标选择",timeId:a(r,"timeitem-tip-head"),textId:a(r,"timeitem-tip-body"),timeClass:n(r,"timeitem-tip-head"),textClass:n(r,"timeitem-tip-body")}),m=a(r,"timeitem-tip");p(r,m,o,h);for(var d=E.g(a(r,"time-body")),c=d.getElementsByTagName("aside"),V=0,U=c.length;U>V;V++){var u=c[V],f=parseInt(u.getAttribute("data-start-time"),10),g=parseInt(u.getAttribute("data-end-time"),10),y=parseInt(u.getAttribute("data-day"),10);if(s>=f&&g>s&&l===y)u.style.display="none";else u.style.display="block"}}}function k(e){var t=E.event.getTarget(e);if(t&&t.getAttribute("data-time-item"))E.removeClasses(t,A.getPartClasses(this,"time-hover")),U(this,a(this,"timeitem-tip"))}function x(e){var t=this,i=document;P=E.bind(L,t),F=E.bind(_,t),E.on(i,"mousemove",P),E.on(i,"mouseup",F),E.event.getMousePosition(e),this.dragStartPos={x:e.pageX,y:e.pageY};var n=E.g(a(t,"time-body"));t.dragRange=[];var o=E.getOffset(n).top,r=E.getOffset(n).left;t.dragRange.push(o),t.dragRange.push(r+n.offsetWidth),t.dragRange.push(o+n.offsetHeight),t.dragRange.push(r),X(n);var s=W(this,{x:e.pageX,y:e.pageY}),l=a(t,"timeitem-tip");E.g(l)&&(E.g(l).style.display="none"),w(this,s)}function L(e){E.event.getMousePosition(e);var t=W(this,{x:e.pageX,y:e.pageY});w(this,t)}function _(e){var t=this;K(E.g(a(t,"time-body")));var i=E.g(a(t,"follow-item"));i.style.display="none",E.event.getMousePosition(e);var n=W(t,{x:e.pageX,y:e.pageY});setTimeout(function(){v(t,n)},10);var o=document;E.un(o,"mousemove",P),E.un(o,"mouseup",F)}function v(e,t){for(var i=e,n=t.startcell,a=t.endcell,o=Math.min(n.x,a.x),r=Math.min(n.y,a.y),s=Math.max(n.x,a.x),l=Math.max(n.y,a.y),h=C(i.rawValue),m=r;l>=m;m++)for(var d=o;s>=d;d++)if(h[m][d])h[m][d]=0;else h[m][d]=1;i.setRawValue(h)}function W(e,t){var i=e,n=i.dragRange,a=i.dragStartPos,o={};if(t.x<=n[1]&&t.x>=n[3])o.x=t.x;else o.x=t.x-a.x<0?n[3]:n[1];if(t.y<=n[2]&&t.y>=n[0])o.y=t.y;else o.y=t.y-a.y<0?n[0]:n[2];var r={startcell:{},endcell:{}};if(r.startcell.x=Math.floor((a.x-i.dragRange[3])/25),r.startcell.y=Math.floor((a.y-i.dragRange[0])/25),r.endcell.x=Math.floor((o.x-i.dragRange[3])/25),r.endcell.y=Math.floor((o.y-i.dragRange[0])/25),r.endcell.x>=23)r.endcell.x=23;if(r.endcell.y>=6)r.endcell.y=6;return r}function w(e,t){var i=e,o=a(e,"follow-item"),r=E.g(o);if(!r)r=document.createElement("div"),r.className=n(i,"follow-item"),r.id=o,E.g(a(i,"time-body")).appendChild(r);var s,l,h,m,d=t.startcell,c=t.endcell,V=d.x,p=d.y,U=c.x,u=c.y;if(u>=p)s=25*p,h=25*(u-p+1)-2;else s=25*u,h=25*(p-u+1)-2;if(U>=V)l=25*V,m=25*(U-V+1)-2;else l=25*U,m=25*(V-U+1)-2;var f=";display:block;;width:"+m+"px;height:"+h+"px;top:"+s+"px;left:"+l+"px;background:#faffbe";r.style.cssText+=f}function X(e){var t=document;if(E.on(t,"selectstart",I),e.setCapture)e.setCapture();else if(window.captureEvents)window.captureEvents(window.Event.MOUSEMOVE|window.Event.MOUSEUP);if(document.selection)document.selection.empty&&document.selection.empty();else if(window.getSelection)window.getSelection().removeAllRanges()}function K(e){var t=document;if(e.releaseCapture)e.releaseCapture();else if(window.releaseEvents)window.releaseEvents(window.Event.MOUSEMOVE|window.Event.MOUSEUP);E.un(t,"selectstart",I)}function I(e){E.event.preventDefault(e)}function C(e){for(var t=[],i=0,n=e.length;n>i;i++)t.push([].slice.call(e[i],0));return t}function J(e,t,i){for(var n=E.g(a(e,"day-head")),o=n.getElementsByTagName("input"),r=0,s=o.length;s>r;r++)o[r][t]=i}function S(e,t,i){for(var n=C(e.rawValue),a=0,o=i.length;o>a;a++){var r=i[a];if(null!=n[r[0]]&&null!=n[r[0]][r[1]])n[r[0]][r[1]]=t?1:0}e.setRawValue(n)}var E=require("./lib"),T=require("./InputControl"),A=require("./controlHelper");e.defaultProperties={helpSelectedText:"投放时间段",helpText:"暂停时间段",dayTexts:["星期一","星期二","星期三","星期四","星期五","星期六","星期日"],shortcut:t()};var P,F,O='<div id="${timeId}" class="${timeClass}">${time}</div><div id="${textId}" class="${textClass}">${text}</div>';return e.prototype={constructor:e,type:"Schedule",createMain:function(e){if(!e.tagName)return T.prototype.createMain.call(this);else return document.createElement(e.tagName)},initOptions:function(t){var n={};if(E.extend(n,e.defaultProperties,t),this.setProperties(n),null==this.rawValue)this.setRawValue(i());this.followTip={}},initStructure:function(){var e=this;this.main.tabIndex=0;var t='<input type="hidden" name="${name}" id="${inputId}"/><div class="${bodyClass}" id="${bodyId}"></div><div class="${headClass}"><div class="${helpClass}"><div class="${helpSelectedClass}"></div><div class="${helpTextClass}">${helpSelected}</div><div class="${helpUnselectedClass}"></div><div class="${helpTextClass}">${help}</div></div><div class="${shortcutClass}" id="${shortcutId}">${shortcutHtml}</div></div>';this.main.innerHTML=E.format(t,{name:this.name,inputId:a(e,"value-input"),headClass:n(e,"head"),bodyClass:n(e,"body"),helpClass:n(e,"help"),helpSelectedClass:n(e,"help-selected"),helpUnselectedClass:n(e,"help-unselected"),helpTextClass:n(e,"help-text"),shortcutClass:n(e,"shortcut"),shortcutId:a(e,"shortcut"),bodyId:a(e,"body"),helpSelected:e.helpSelectedText,help:e.helpText,shortcutHtml:o(e)}),r(e)},initEvents:function(){var e=E.g(a(this,"time-body"));this.helper.addDOMEvent(e,"mousedown",x),this.helper.addDOMEvent(e,"mouseover",b),this.helper.addDOMEvent(e,"mouseout",k),this.helper.addDOMEvent(E.g(a(this,"day-head")),"click",u);var t=this.helper.getPart("shortcut");this.helper.addDOMEvent(t,"click",f),this.helper.addDOMEvent(t,"mouseover",E.curry(y,!0)),this.helper.addDOMEvent(t,"mouseout",E.curry(y,!1)),this.helper.addDOMEvent(t,"mousemove",g)},setProperties:function(e){var t=T.prototype.setProperties.call(this,e),i=t.rawValue;if(i&&this.stringifyValue(i.oldValue)!==this.stringifyValue(i.newValue))this.fire("change",{rawValue:this.rawValue})},repaint:A.createRepaint(T.prototype.repaint,{name:"rawValue",paint:function(e,t){var i=e.stringifyValue(t);E.g(a(e,"value-input")).value=null==i?"":i,m(e,t)}},{name:"disabled",paint:function(e,t){J(e,"disabled",t)}},{name:"readonly",paint:function(e,t){J(e,"readonly",t)}}),parseValue:function(e){for(var t=[],i=24,n=0,a=e.length;a>n;n+=i){for(var o=e.substring(n,n+i).split(""),r=[],s=0;s<o.length;s++)r.push(o[s]-0);t.push(r)}return t},stringifyValue:function(e){var t=[];if(!e)return null;for(var i=0,n=e.length;n>i;i++)t.push(e[i].join(""));return t.join("")},setRawValue:function(e){this.setProperties({rawValue:e})},getRawValue:function(){return this.rawValue},select:function(){S(this,1,[].slice.call(arguments))},unselect:function(){S(this,0,[].slice.call(arguments))},dispose:function(){A.beforeDispose(this);var e=this.followTip;for(var t in e)if(e[t])document.body.removeChild(e[t]);A.dispose(this),A.afterDispose(this)}},E.inherits(e,T),require("./main").register(e),e});