define("er/permission",[],function(){var e={},t={add:function(t){for(var i in t)if(t.hasOwnProperty(i)){var n=t[i];if("object"==typeof n)this.add(n);else e[i]=n}},isAllow:function(t){return!!e[t]}};return t});