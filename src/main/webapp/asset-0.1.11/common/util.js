/*! 2015 Baidu Inc. All Rights Reserved */
define("common/util",["require","underscore","ub-ria/util"],function(require){function e(e){return 10>e?"0"+e:e}var t=require("underscore"),i=function(){};i.prototype=t;var n=new i;return n.formatTime=function(t){var i=t.getFullYear()+"-"+e(t.getMonth()+1)+"-"+e(t.getDate());return i},n.formatTimeToSecond=function(t){var i=this.formatTime(t);return i+=" "+e(t.getHours())+":"+e(t.getMinutes()+1)+":"+e(t.getSeconds())},n.formatTimeString=function(t){var i=t.getFullYear()+""+e(t.getMonth()+1)+e(t.getDate());return i},n.getToday=function(){var e=new Date;return Date.parse(e.toDateString())},n.getIndexOfList=function(e,t,i){var n=-1;return e.forEach(function(e,r){if(e[t]===i)n=r}),n},n.hasClass=function(e,t){return this.find(e.classList,function(e){return e===t})},n.formatTagmap=function(e){var t={};return this.each(this.keys(e),function(i){t[i]={},this.each(this.keys(e[i]),function(n){var r="";if("name_ch"===n)r="text";else if("name"===n)r="alias";else if("tags"===n)r="source";else r=n;t[i][r]=this.clone(e[i][n])},this)},this),t},n.assembleAttachGroupData=function(e,t){var i=this.map(this.keys(e),function(e){return t[e]});return this.sortBy(i,"priority")},n.assembleAttachGroupValue=function(e,t,i){var n={};return this.each(this.keys(t),function(r){var a=t[r];if("batch"===e)n[r]=i[r].defaultValue;else n[r]="0"===a?i[r].defaultValue:a}),n},n.extend(n,require("ub-ria/util")),n});