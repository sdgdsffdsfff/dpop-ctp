define("ub-ria/mvc/SingleEntityModel",["require","underscore","er/util","./BaseModel"],function(require){function e(e){return this.fill(e),e}function t(){a.apply(this,arguments),this.putDatasource(r)}var n=require("underscore"),i=require("er/util"),a=require("./BaseModel"),r={entity:function(t){var i=t.get("id");if(i){var a=t.get("entity");if(a)return e.call(t,a);else return t.findById(i).then(n.bind(e,t))}else return{}}};return i.inherits(t,a),t.prototype.findById=function(e){var t=this.data();if(!t)throw new Error("No default data object attached to this Model");if("function"!=typeof t.findById)throw new Error("No findById method implemented on default data object");return t.findById(e)},t});