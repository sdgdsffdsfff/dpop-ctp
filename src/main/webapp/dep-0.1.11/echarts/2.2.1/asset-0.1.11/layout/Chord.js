define("echarts/layout/Chord",["require"],function(){var t=function(t){t=t||{},this.sort=t.sort||null,this.sortSub=t.sortSub||null,this.padding=.05,this.startAngle=t.startAngle||0,this.clockWise=null==t.clockWise?!1:t.clockWise,this.center=t.center||[0,0],this.directed=!0};t.prototype.run=function(t){if(!(t instanceof Array))t=[t];var s=t.length;if(s){for(var a=t[0],o=a.nodes.length,n=[],r=0,h=0;o>h;h++){var l=a.nodes[h],d={size:0,subGroups:[],node:l};n.push(d);for(var p=0,c=0;c<t.length;c++){var g=t[c],u=g.getNodeById(l.id);if(u){d.size+=u.layout.size;for(var f=this.directed?u.outEdges:u.edges,_=0;_<f.length;_++){var m=f[_],y=m.layout.weight;d.subGroups.push({weight:y,edge:m,graph:g}),p+=y}}else;}r+=d.size;for(var x=d.size/p,_=0;_<d.subGroups.length;_++)d.subGroups[_].weight*=x;if("ascending"===this.sortSub)d.subGroups.sort(e);else if("descending"===this.sort)d.subGroups.sort(e),d.subGroups.reverse()}if("ascending"===this.sort)n.sort(i);else if("descending"===this.sort)n.sort(i),n.reverse();for(var x=(2*Math.PI-this.padding*o)/r,v=this.startAngle,b=this.clockWise?1:-1,h=0;o>h;h++){var d=n[h];d.node.layout.startAngle=v,d.node.layout.endAngle=v+b*d.size*x,d.node.layout.subGroups=[];for(var _=0;_<d.subGroups.length;_++){var S=d.subGroups[_];S.edge.layout.startAngle=v,v+=b*S.weight*x,S.edge.layout.endAngle=v}v=d.node.layout.endAngle+b*this.padding}}};var e=function(t,e){return t.weight-e.weight},i=function(t,e){return t.size-e.size};return t});