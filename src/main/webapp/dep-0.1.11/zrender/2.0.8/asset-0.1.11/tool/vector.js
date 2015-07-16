define("zrender/tool/vector",[],function(){var e="undefined"==typeof Float32Array?Array:Float32Array,t={create:function(t,i){var n=new e(2);return n[0]=t||0,n[1]=i||0,n},copy:function(e,t){return e[0]=t[0],e[1]=t[1],e},clone:function(t){var i=new e(2);return i[0]=t[0],i[1]=t[1],i},set:function(e,t,i){return e[0]=t,e[1]=i,e},add:function(e,t,i){return e[0]=t[0]+i[0],e[1]=t[1]+i[1],e},scaleAndAdd:function(e,t,i,n){return e[0]=t[0]+i[0]*n,e[1]=t[1]+i[1]*n,e},sub:function(e,t,i){return e[0]=t[0]-i[0],e[1]=t[1]-i[1],e},len:function(e){return Math.sqrt(this.lenSquare(e))},lenSquare:function(e){return e[0]*e[0]+e[1]*e[1]},mul:function(e,t,i){return e[0]=t[0]*i[0],e[1]=t[1]*i[1],e},div:function(e,t,i){return e[0]=t[0]/i[0],e[1]=t[1]/i[1],e},dot:function(e,t){return e[0]*t[0]+e[1]*t[1]},scale:function(e,t,i){return e[0]=t[0]*i,e[1]=t[1]*i,e},normalize:function(e,i){var n=t.len(i);if(0===n)e[0]=0,e[1]=0;else e[0]=i[0]/n,e[1]=i[1]/n;return e},distance:function(e,t){return Math.sqrt((e[0]-t[0])*(e[0]-t[0])+(e[1]-t[1])*(e[1]-t[1]))},distanceSquare:function(e,t){return(e[0]-t[0])*(e[0]-t[0])+(e[1]-t[1])*(e[1]-t[1])},negate:function(e,t){return e[0]=-t[0],e[1]=-t[1],e},lerp:function(e,t,i,n){return e[0]=t[0]+n*(i[0]-t[0]),e[1]=t[1]+n*(i[1]-t[1]),e},applyTransform:function(e,t,i){var n=t[0],r=t[1];return e[0]=i[0]*n+i[2]*r+i[4],e[1]=i[1]*n+i[3]*r+i[5],e},min:function(e,t,i){return e[0]=Math.min(t[0],i[0]),e[1]=Math.min(t[1],i[1]),e},max:function(e,t,i){return e[0]=Math.max(t[0],i[0]),e[1]=Math.max(t[1],i[1]),e}};return t.length=t.len,t.lengthSquare=t.lenSquare,t.dist=t.distance,t.distSquare=t.distanceSquare,t});