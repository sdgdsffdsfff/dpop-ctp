define("ub-ria/tpl",["require","er/ajax","etpl","./util"],function(require){function e(e){for(var t=[],i={},n=/<\s*esui-([\w-]+)[^>]*>|data-ui-type="(\w+)"/g,a=n.exec(e);a;){var s=a[1]&&r.pascalize(a[1])||a[2];if(!i[s]){i[s]=!0;var l=(o[s]||"ui")+"/";t.push(l+s)}a=n.exec(e)}return t}function t(e){for(var t=[],i={},n=/data-ui-extension-[^-]+-type="(\w+)"/g,a=n.exec(e);a;){var r=a[1];if(!i[r]){i[r]=!0;var o=(s[r]||"ui/extension")+"/";t.push(o+r)}a=n.exec(e)}return t}var i=require("er/ajax"),n=require("etpl"),a=n,r=require("./util");a.addFilter("trim",r.trim),a.addFilter("pascalize",r.pascalize),a.addFilter("camelize",r.camelize),a.addFilter("dasherize",r.dasherize),a.addFilter("constlize",r.constlize),a.addFilter("pluralize",r.pluralize);var o={BoxGroup:"esui",Button:"esui",Calendar:"esui",CheckBox:"esui",CommandMenu:"esui",Crumb:"esui",Dialog:"esui",Form:"esui",Frame:"esui",Label:"esui",Link:"esui",MonthView:"esui",Pager:"esui",Panel:"esui",RangeCalendar:"esui",Region:"esui",RichCalendar:"esui",Schedule:"esui",SearchBox:"esui",Select:"esui",Tab:"esui",Table:"esui",TextBox:"esui",TextLine:"esui",Tip:"esui",TipLayer:"esui",Tree:"esui",Validity:"esui",Wizard:"esui",ActionPanel:"ef",ActionDialog:"ef",ViewPanel:"ef",TogglePanel:"ub-ria/ui",ToggleButton:"ub-ria/ui",Uploader:"ub-ria/ui",RichSelector:"ub-ria/ui",TableRichSelector:"ub-ria/ui",SelectorTreeStrategy:"ub-ria/ui",TreeRichSelector:"ub-ria/ui",AbstractBoxGroup:"ub-ria/ui",Sidebar:"ub-ria/ui",PartialForm:"ub-ria/ui"},s={AutoSort:"esui/extension",Command:"esui/extension",CustomData:"esui/extension",TableEdit:"esui/extension",AutoSubmit:"ub-ria/ui/extension",TableTip:"ub-ria/ui/extension",ExternSearch:"ub-ria/ui/extension",ExternSelect:"ub-ria/ui/extension",TableSubrow:"esui/extension",WordCount:"ub-ria/ui/extension"},l={setupTemplateEngine:function(e){a=e||n},load:function(n,r,o){function s(i){a.parse(i);var n=e(i),r=t(i),s=n.concat(r);window.require(s,function(){o(i)})}var l={method:"GET",url:r.toUrl(n),cache:!0,dataType:"text"};i.request(l).then(s)},registerControl:function(e){var t=e.lastIndexOf("/"),i=e.substring(0,t),n=e.substring(t+1);o[n]=i},registerExtension:function(e){var t=e.lastIndexOf("/"),i=e.substring(0,t),n=e.substring(t+1);s[n]=i}};return l});