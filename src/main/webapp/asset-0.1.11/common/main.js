/*! 2015 Baidu Inc. All Rights Reserved */
define("common/main",["require","common/extension/ajax","common/shim","common/indicator","./GlobalData","er/Deferred"],function(require){function e(){var e=a.getInstance();return o.all(e.getUser())}function t(){var e=["er/config","er/permission","ub-ria","common/extension","common/config","common/account","common/Navigator"];if("object"!=typeof JSON)e.push("js!external/json2");require(e,function(e,t,n,r,a,o,s){r.enable(),e.indexURL=i(t),n.start(),o.init(),s.getInstance()})}function i(){return"/marker"}function n(){var e=window.DEBUG?"login.html":"login.html";throw location.href=e+location.hash,new Error("Failed to redirect to login")}function r(){e().then(t,n)}require("common/extension/ajax").enable(),require("common/shim").enable(),require("common/indicator").enable();var a=require("./GlobalData"),o=require("er/Deferred");return{start:r}});