define("ub-ria/mvc/DetailAction",["require","underscore","../util","er/URL","./BaseAction","eoo"],function(require){function e(e){var t=this.context.url,n=t.getPath();return e=require("../util").purify(e),require("er/URL").withQuery(n,e).toString()}function t(e,t){e.preventDefault();var n={id:this.model.get("id")},a=this.view.getListQuery();if(!t)a.page=1;i.each(a,function(e,t){n["list."+t]=e}),this.reloadWithQueryUpdate(n)}function n(e){t.call(this,e,!0)}var i=require("underscore"),exports={};exports.category="detail",exports.reloadWithQueryUpdate=function(t){var n=e.call(this,t);this.redirect(n,{force:!0})},exports.initBehavior=function(){this.$super(arguments),this.view.on("listrefresh",t,this),this.view.on("pagechange",n,this)};var a=require("./BaseAction"),r=require("eoo").create(a,exports);return r});