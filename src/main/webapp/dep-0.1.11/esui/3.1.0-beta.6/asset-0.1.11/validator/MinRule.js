define("esui/validator/MinRule",["require","./Rule","./ValidityState","../lib","../main"],function(require){function e(){t.apply(this,arguments)}var t=require("./Rule"),i=require("./ValidityState");return e.prototype.type="min",e.prototype.errorMessage="${title}不能小于${min}",e.prototype.check=function(e,t){var n=+e,a=!isNaN(n)&&n>=this.getLimitCondition(t);return new i(!e||a,this.getErrorMessage(t))},require("../lib").inherits(e,t),require("../main").registerRule(e,300),e});