void function(e,t,i){e("uioc/main",["require","./Container","./util","./DependencyParser"],function(require){function e(t){if(t=t||{},!(this instanceof e))return new e(t);else return this.moduleLoader=t.loader||V,this.parser=new f(this),this.components={},this.container=new c(this),void this.addComponent(t.components||{})}function n(e,t){var n={id:e,args:t.args||[],properties:t.properties||{},anonyDeps:null,argDeps:null,propDeps:null,setterDeps:null,scope:t.scope||"transient",creator:t.creator||null,module:t.module||i,isFactory:!!t.isFactory,auto:!!t.auto,instance:null};return"function"==typeof n.creator&&a(n),n}function a(e,module){var t=e.creator=e.creator||module;if("string"==typeof t){var i=module[t],n=function(){return i.apply(module,arguments)};t=!e.isFactory||"static"===e.scope?i:n,e.creator=t}if(!e.isFactory&&"static"!==e.scope)e.creator=function(){return g.prototype=t.prototype,new g(t,arguments)}}function r(e,t,i,n){var a=i.$import,r=e.getComponentConfig(a);if(!r)throw new Error("$import `%s` component, but it is not exist, please check!!",i.$import);var o=t.id+"-"+n+a;return i.id=o=(-1!==o.indexOf(U)?"":U)+o,delete i.$import,e.addComponent(o,p.merge(r,i)),o}function o(e,t){t.anonyDeps=[];for(var i=t.args,n=null,a=i.length-1;a>-1;--a)if(p.hasImport(i[a]))n=r(e,t,i[a],"$arg."),i[a]={$ref:n},t.anonyDeps.push(n);var o=t.properties;for(var s in o)if(p.hasImport(o[s]))n=r(e,t,o[s],"$prop."),o[s]={$ref:n},t.anonyDeps.push(n)}function s(e,t,i){var n=[];for(var r in t)n.push(r);e.moduleLoader(n,function(){for(var e=arguments.length-1;e>-1;--e)for(var module=arguments[e],r=t[n[e]],o=r.length-1;o>-1;--o){var s=r[o];"function"!=typeof s.creator&&a(s,module)}i()})}function l(e,t){var i=Array(e.length);if(0===e.length)return t.apply(null,i);for(var n=this.container,a=this.parser,r=this,o={},l=e.length,d=function(){0===--l&&t.apply(null,i)},u=function(e,t){return function(n){if(i[e]=n,t){if(o=a.getDependentModules(t,{},t.propDeps),!t.setterDeps&&t.auto)t.setterDeps=a.getDepsFromSetters(n,t.properties),o=a.getDependentModules(t,o,t.setterDeps);s(r,o,p.bind(h,r,n,t,d))}else d()}},m=e.length-1;m>-1;--m){var c=this.components[e[m]];n.createInstance(c,u(m,c))}}function h(e,t,i){var n={prop:!1,setter:!1},a=function(e){n[e]=!0,n.prop&&n.setter&&i()};u(this,e,t,p.bind(a,null,"prop")),d(this,e,t,p.bind(a,null,"setter"))}function d(e,t,i,n){var a=i.setterDeps||[];e.getComponent(a,function(){for(var e=a.length-1;e>-1;--e){var i=a[e];m(t,i,arguments[e])}n()})}function u(e,t,i,n){var a=i.propDeps,r=i.properties;e.getComponent(a,function(){for(var e in r){var i=r[e];if(p.hasReference(i))i=arguments[p.indexOf(a,i.$ref)];m(t,e,i)}n()})}function m(e,t,i){var n="set"+t.charAt(0).toUpperCase()+t.slice(1);"function"==typeof e[n]?e[n](i):e[t]=i}var c=require("./Container"),p=require("./util"),f=require("./DependencyParser"),V=t.require,U="^uioc-",g=function(e,t){return e.apply(this,t)};return e.prototype.addComponent=function(e,t){var i=[];if("string"==typeof e){var a={};a[e]=t,this.addComponent(a)}else for(var r in e)if(!this.components[e])this.components[r]=n.call(this,r,e[r]),i.push(r);else p.warn(e+" has been add! This will be no effect");for(var s=i.length-1;s>-1;--s){var l=this.getComponentConfig(i[s]);!l.anonyDeps&&o(this,l),l.argDeps=this.parser.getDepsFromArgs(l.args),l.propDeps=this.parser.getDepsFromProperties(l.properties)}},e.prototype.getComponent=function(e,t){e=e instanceof Array?e:[e];for(var i={},n=this,a=n.parser,r=0,o=e.length;o>r;++r){var h=e[r],d=this.components[h];if(!d)p.warn("`%s` has not been added to the Ioc",h);else i=a.getDependentModules(d,i,d.argDeps)}return s(this,i,p.bind(l,this,e,t)),this},e.prototype.hasComponent=function(e){return!!this.components[e]},e.prototype.getComponentConfig=function(e){return this.components[e]},e.prototype.loader=function(e){this.moduleLoader=e},e.prototype.dispose=function(){this.container.dispose(),this.components=null,this.parser=null},e})}("function"==typeof define&&define.amd?define:function(e){module.exports=e},this),define("uioc",["uioc/main"],function(e){return e});