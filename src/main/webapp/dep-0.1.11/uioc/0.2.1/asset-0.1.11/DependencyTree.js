void function(e){e("uioc/DependencyTree",[],function(){var e=function(){this.data=[],this.children=[],this.parent=null};return e.prototype.appendChild=function(e){return e.parent=this,this.children.push(e),e},e.prototype.checkForCircular=function(e){var t=this.parent;if(null!==t)for(var i=t.data,n=i.length-1;n>-1;--n)if(t.data[n].id&&t.data[n].id===e)return t.data[n];else return t.checkForCircular(e);return null},e.prototype.addData=function(e,t){if(t=!!t,t&&this.checkForCircular(e.id))return!1;else return this.data.push(e),!0},e})}("function"==typeof define&&define.amd?define:function(e){module.exports=e});