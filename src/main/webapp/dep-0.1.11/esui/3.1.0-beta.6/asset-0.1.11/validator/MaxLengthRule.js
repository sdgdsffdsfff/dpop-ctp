define("esui/validator/MaxLengthRule",["require","./Rule","./ValidityState","../lib","../main"],function(require){function e(){t.apply(this,arguments)}var t=require("./Rule"),i=require("./ValidityState");return e.prototype.type="maxLength",e.prototype.errorMessage="${title}不能超过${maxLength}个字符",e.prototype.check=function(e,t){return new i(e.length<=this.getLimitCondition(t),this.getErrorMessage(t))},e.prototype.getErrorMessage=function(e){var t=require("../lib"),i=e.get(this.type+"ErrorMessage")||this.errorMessage,n=this.getLimitCondition(e),a={title:e.get("title"),maxLength:n,length:n};return t.format(i,a)},e.prototype.getLimitCondition=function(e){return e.get("length")||e.get("maxLength")},require("../lib").inherits(e,t),require("../main").registerRule(e,100),e});