void function(e){e("uioc/DependencyParser",["require","./util","./DependencyTree"],function(require){function e(e){this.context=e}function t(e,a,r,o,s){if(e){var module=e.module;if("function"!=typeof e.creator&&module)r[module]=r[module]||[],r[module].push(e);var l=o.checkForCircular(e.id);if(l){var h=e.id+" has circular dependencies ";throw new i.CircularError(h,e)}o.addData(e);var d=o.appendChild(new n);s=s||e.argDeps.concat(e.propDeps).concat(e.setterDeps||[]);for(var u=s.length-1;u>-1;--u)t(a.getComponentConfig(s[u]),a,r,d)}return r}var i=require("./util"),n=require("./DependencyTree"),a=/^set([A-Z].*)$/;return e.prototype.getPropertyFromSetter=function(e){var t=null,i=e.match(a);if(i)t=i[1],t=t.charAt(0).toLowerCase()+t.slice(1);return t},e.prototype.getDepsFromArgs=function(e){for(var t=[],n=e.length-1;n>-1;--n)i.hasReference(e[n])&&i.addToSet(t,e[n].$ref);return t},e.prototype.getDepsFromProperties=function(e){var t=[];for(var n in e)if(i.hasOwn(e,n)){var a=e[n];i.hasReference(a)&&i.addToSet(t,a.$ref)}return t},e.prototype.getDepsFromSetters=function(e,t){t=t||{};var i=[],n=null;for(var a in e)if("function"==typeof e[a])n=this.getPropertyFromSetter(a),n&&!t.hasOwnProperty(n)&&this.context.hasComponent(n)&&i.push(n);return i},e.prototype.getDepsFromInterfaces=function(){},e.prototype.getDependentModules=function(e,i,a){return t(e,this.context,i||{},new n,a)},e})}("function"==typeof define&&define.amd?define:function(e){module.exports=e});