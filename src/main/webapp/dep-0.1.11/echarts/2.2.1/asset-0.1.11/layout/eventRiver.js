define("echarts/layout/eventRiver",["require"],function(){function t(t,o,n){function r(t,e){var i=t.importance,s=e.importance;return i>s?-1:s>i?1:0}function h(t,e){if(t.indexOf)return t.indexOf(e);for(var i=0,s=t.length;s>i;i++)if(t[i]===e)return i;return-1}for(var l=5,d=o,p=0;p<t.length;p++){for(var c=0;c<t[p].data.length;c++){if(null==t[p].data[c].weight)t[p].data[c].weight=1;for(var g=0,u=0;u<t[p].data[c].evolution.length;u++)g+=t[p].data[c].evolution[u].valueScale;t[p].data[c].importance=g*t[p].data[c].weight}t[p].data.sort(r)}for(var p=0;p<t.length;p++){if(null==t[p].weight)t[p].weight=1;for(var g=0,c=0;c<t[p].data.length;c++)g+=t[p].data[c].weight;t[p].importance=g*t[p].weight}t.sort(r);for(var f=Number.MAX_VALUE,_=0,p=0;p<t.length;p++)for(var c=0;c<t[p].data.length;c++)for(var u=0;u<t[p].data[c].evolution.length;u++){var m=t[p].data[c].evolution[u].timeScale;f=Math.min(f,m),_=Math.max(_,m)}for(var y=i(Math.floor(f),Math.ceil(_)),x=0,p=0;p<t.length;p++)for(var c=0;c<t[p].data.length;c++){var v=t[p].data[c];v.time=[],v.value=[];for(var u=0;u<t[p].data[c].evolution.length;u++)v.time.push(t[p].data[c].evolution[u].timeScale),v.value.push(t[p].data[c].evolution[u].valueScale);var b=h(v.value,Math.max.apply(Math,v.value)),S=s(y,v.time[b],v.time[b+1]),u=0;for(v.y=S+v.value[b]/2+l,u=0;u<v.time.length-1;u++){var z=s(y,v.time[u],v.time[u+1]);if(v.y-v.value[u]/2-l<z)v.y=z+v.value[u]/2+l}var z=s(y,v.time[u],v.time[u]+d);if(v.y-v.value[u]/2-l<z)v.y=z+v.value[u]/2+l;for(t[p].y=v.y,x=Math.max(x,v.y+v.value[b]/2),u=0;u<v.time.length-1;u++)a(y,v.time[u],v.time[u+1],v.y+v.value[u]/2);a(y,v.time[u],v.time[u]+d,v.y+v.value[u]/2)}e(t,n,x,l)}function e(t,e,i,s){for(var a=e.y,o=(e.height-s)/i,n=0;n<t.length;n++){t[n].y=t[n].y*o+a;for(var r=t[n].data,h=0;h<r.length;h++){r[h].y=r[h].y*o+a;for(var l=r[h].evolution,d=0;d<l.length;d++)l[d].valueScale*=1*o}}}function i(t,e){var s={left:t,right:e,leftChild:null,rightChild:null,maxValue:0};if(e>t+1){var a=Math.round((t+e)/2);s.leftChild=i(t,a),s.rightChild=i(a,e)}return s}function s(t,e,i){if(1>i-e)return 0;var a=Math.round((t.left+t.right)/2),o=0;if(e==t.left&&i==t.right)o=t.maxValue;else if(a>=i&&null!=t.leftChild)o=s(t.leftChild,e,i);else if(e>=a&&null!=t.rightChild)o=s(t.rightChild,e,i);else{var n=0,r=0;if(null!=t.leftChild)n=s(t.leftChild,e,a);if(null!=t.rightChild)r=s(t.rightChild,a,i);o=n>r?n:r}return o}function a(t,e,i,s){if(null!=t){var o=Math.round((t.left+t.right)/2);if(t.maxValue=t.maxValue>s?t.maxValue:s,Math.floor(10*e)!=Math.floor(10*t.left)||Math.floor(10*i)!=Math.floor(10*t.right))if(o>=i)a(t.leftChild,e,i,s);else if(e>=o)a(t.rightChild,e,i,s);else a(t.leftChild,e,o,s),a(t.rightChild,o,i,s)}}return t});