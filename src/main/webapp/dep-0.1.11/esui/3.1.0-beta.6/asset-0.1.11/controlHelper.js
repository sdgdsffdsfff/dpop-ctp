define("esui/controlHelper",["require","./lib","./Helper","./painters","underscore","./Layer"],function(require){var e=require("./lib"),t=require("./Helper"),i={};i.getGUID=e.getGUID;var n=["initViewContext","initExtensions","isInStage","changeStage","dispose","beforeDispose","afterDispose","getPartClasses","addPartClasses","removePartClasses","getStateClasses","addStateClasses","removeStateClasses","getId","replaceMain","addDOMEvent","removeDOMEvent","clearDOMEvents"];i.createRepaint=require("./painters").createRepaint,require("underscore").each(n,function(e){i[e]=function(i){var n=i.helper||new t(i),a=[].slice.call(arguments,1);return n[e].apply(n,a)}}),i.extractValueFromInput=function(t,i){var n=t.main;if(e.isInput(n)){if(n.value&&!i.value)i.value=n.value;if(n.name&&!i.name)i.name=n.name;if(n.disabled&&(null===i.disabled||void 0===i.disabled))i.disabled=n.disabled;if(n.readOnly&&(null===i.readOnly||void 0===i.readOnly))i.readOnly=n.readonly||n.readOnly}};var a=i.layer={},o=require("./Layer");return a.create=o.create,a.getZIndex=o.getZIndex,a.moveToTop=o.moveToTop,a.moveTo=o.moveTo,a.resize=o.resize,a.attachTo=o.attachTo,a.centerToView=o.centerToView,i});