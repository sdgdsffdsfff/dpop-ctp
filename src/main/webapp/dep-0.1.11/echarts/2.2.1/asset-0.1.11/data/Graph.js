define("echarts/data/Graph",["require","zrender/tool/util"],function(require){var t=require("zrender/tool/util"),e=function(t){this._directed=t||!1,this.nodes=[],this.edges=[],this._nodesMap={},this._edgesMap={}};e.prototype.isDirected=function(){return this._directed},e.prototype.addNode=function(t,i){if(this._nodesMap[t])return this._nodesMap[t];var s=new e.Node(t,i);return this.nodes.push(s),this._nodesMap[t]=s,s},e.prototype.getNodeById=function(t){return this._nodesMap[t]},e.prototype.addEdge=function(t,i,s){if("string"==typeof t)t=this._nodesMap[t];if("string"==typeof i)i=this._nodesMap[i];if(t&&i){var a=t.id+"-"+i.id;if(this._edgesMap[a])return this._edgesMap[a];var o=new e.Edge(t,i,s);if(this._directed)t.outEdges.push(o),i.inEdges.push(o);if(t.edges.push(o),t!==i)i.edges.push(o);return this.edges.push(o),this._edgesMap[a]=o,o}},e.prototype.removeEdge=function(e){var i=e.node1,s=e.node2,a=i.id+"-"+s.id;if(this._directed)i.outEdges.splice(t.indexOf(i.outEdges,e),1),s.inEdges.splice(t.indexOf(s.inEdges,e),1);if(i.edges.splice(t.indexOf(i.edges,e),1),i!==s)s.edges.splice(t.indexOf(s.edges,e),1);delete this._edgesMap[a],this.edges.splice(t.indexOf(this.edges,e),1)},e.prototype.getEdge=function(t,e){if("string"!=typeof t)t=t.id;if("string"!=typeof e)e=e.id;if(this._directed)return this._edgesMap[t+"-"+e];else return this._edgesMap[t+"-"+e]||this._edgesMap[e+"-"+t]},e.prototype.removeNode=function(e){if("string"==typeof e)if(e=this._nodesMap[e],!e)return;delete this._nodesMap[e.id],this.nodes.splice(t.indexOf(this.nodes,e),1);for(var i=0;i<this.edges.length;){var s=this.edges[i];if(s.node1===e||s.node2===e)this.removeEdge(s);else i++}},e.prototype.filterNode=function(t,e){for(var i=this.nodes.length,s=0;i>s;)if(t.call(e,this.nodes[s],s))s++;else this.removeNode(this.nodes[s]),i--},e.prototype.filterEdge=function(t,e){for(var i=this.edges.length,s=0;i>s;)if(t.call(e,this.edges[s],s))s++;else this.removeEdge(this.edges[s]),i--},e.prototype.eachNode=function(t,e){for(var i=this.nodes.length,s=0;i>s;s++)if(this.nodes[s])t.call(e,this.nodes[s],s)},e.prototype.eachEdge=function(t,e){for(var i=this.edges.length,s=0;i>s;s++)if(this.edges[s])t.call(e,this.edges[s],s)},e.prototype.clear=function(){this.nodes.length=0,this.edges.length=0,this._nodesMap={},this._edgesMap={}},e.prototype.breadthFirstTraverse=function(t,e,i,s){if("string"==typeof e)e=this._nodesMap[e];if(e){var a="edges";if("out"===i)a="outEdges";else if("in"===i)a="inEdges";for(var o=0;o<this.nodes.length;o++)this.nodes[o].__visited=!1;if(!t.call(s,e,null))for(var r=[e];r.length;)for(var n=r.shift(),h=n[a],o=0;o<h.length;o++){var l=h[o],d=l.node1===n?l.node2:l.node1;if(!d.__visited){if(t.call(d,d,n))return;r.push(d),d.__visited=!0}}}},e.prototype.clone=function(){for(var t=new e(this._directed),i=0;i<this.nodes.length;i++)t.addNode(this.nodes[i].id,this.nodes[i].data);for(var i=0;i<this.edges.length;i++){var s=this.edges[i];t.addEdge(s.node1.id,s.node2.id,s.data)}return t};var i=function(t,e){this.id=t,this.data=e||null,this.inEdges=[],this.outEdges=[],this.edges=[]};i.prototype.degree=function(){return this.edges.length},i.prototype.inDegree=function(){return this.inEdges.length},i.prototype.outDegree=function(){return this.outEdges.length};var s=function(t,e,i){this.node1=t,this.node2=e,this.data=i||null};return e.Node=i,e.Edge=s,e.fromMatrix=function(t,i,s){if(i&&i.length&&i[0].length===i.length&&t.length===i.length){for(var a=i.length,o=new e(s),r=0;a>r;r++){var n=o.addNode(t[r].id,t[r]);if(n.data.value=0,s)n.data.outValue=n.data.inValue=0}for(var r=0;a>r;r++)for(var h=0;a>h;h++){var l=i[r][h];if(s)o.nodes[r].data.outValue+=l,o.nodes[h].data.inValue+=l;o.nodes[r].data.value+=l,o.nodes[h].data.value+=l}for(var r=0;a>r;r++)for(var h=r;a>h;h++){var l=i[r][h];if(0!==l){var d=o.nodes[r],p=o.nodes[h],c=o.addEdge(d,p,{});if(c.data.weight=l,r!==h)if(s&&i[h][r]){var g=o.addEdge(p,d,{});g.data.weight=i[h][r]}}else;}return o}},e});