(function(){"use strict";function e(e){return e=String(e),e.charAt(0).toUpperCase()+e.slice(1)}function t(e,t,n){var a={6.3:"8.1",6.2:"8",6.1:"Server 2008 R2 / 7","6.0":"Server 2008 / Vista",5.2:"Server 2003 / XP 64-bit",5.1:"XP",5.01:"2000 SP1","5.0":"2000","4.0":"NT","4.90":"ME"};if(t&&n&&/^Win/i.test(e)&&(a=a[/[\d.]+$/.exec(e)]))e="Windows "+a;if(e=String(e),t&&n)e=e.replace(RegExp(t,"i"),n);return e=i(e.replace(/ ce$/i," CE").replace(/hpw/i,"web").replace(/Macintosh/,"Mac OS").replace(/_PowerPC/i," OS").replace(/(OS X) [^ \d]+/i,"$1").replace(/Mac (OS X)/,"$1").replace(/\/(\d)/," $1").replace(/_/g,".").replace(/(?: BePC|[ .]*fc[ \d.]+)$/i,"").replace(/x86\.64/gi,"x86_64").replace(/(Windows Phone)(?! OS)/,"$1 OS").split(" on ")[0])}function n(e,t){var n=-1,i=e?e.length:0;if("number"==typeof i&&i>-1&&g>=i)for(;++n<i;)t(e[n],n,e);else a(e,t)}function i(t){return t=h(t),/^(?:webOS|i(?:OS|P))/.test(t)?t:e(t)}function a(e,t){for(var n in e)if(_.call(e,n))t(e[n],n,e)}function r(t){return null==t?e(t):k.call(t).slice(8,-1)}function o(e,t){var n=null!=e?typeof e[t]:"number";return!/^(?:boolean|number|string|undefined)$/.test(n)&&("object"==n?!!e[t]:!0)}function s(e){return String(e).replace(/([ -])(?!$)/g,"$1?")}function l(e,t){var i=null;return n(e,function(n,a){i=t(i,n,a,e)}),i}function h(e){return String(e).replace(/^ +| +$/g,"")}function d(e){function n(t){return l(t,function(t,n){return t||RegExp("\\b"+(n.pattern||s(n))+"\\b","i").exec(e)&&(n.label||n)})}function u(t){return l(t,function(t,n,i){return t||(n[j]||n[/^[a-z]+(?: +[a-z]+\b)*/i.exec(j)]||RegExp("\\b"+s(i)+"(?:\\b|\\w*\\d)","i").exec(e))&&i})}function m(t){return l(t,function(t,n){return t||RegExp("\\b"+(n.pattern||s(n))+"\\b","i").exec(e)&&(n.label||n)})}function f(n){return l(n,function(n,i){var a=i.pattern||s(i);if(!n&&(n=RegExp("\\b"+a+"(?:/[\\d.]+|[ \\w.]*)","i").exec(e)))n=t(n,a,i.label||i);return n})}function V(t){return l(t,function(t,n){var a=n.pattern||s(n);if(!t&&(t=RegExp("\\b"+a+" *\\d+[.\\w_]*","i").exec(e)||RegExp("\\b"+a+"(?:; *(?:[a-z]+[_-])?[a-z]+\\d+|[^ ();-]*)","i").exec(e))){if((t=String(n.label&&!RegExp(a,"i").test(n.label)?n.label:t).split("/"))[1]&&!/[\d.]+/.test(t[0]))t[0]+=" "+t[1];n=n.label||n,t=i(t[0].replace(RegExp(a,"i"),n).replace(RegExp("; *(?:"+n+"[_-])?","i")," ").replace(RegExp("("+n+")[-_.]?(\\w)","i"),"$1 $2"))}return t})}function g(t){return l(t,function(t,n){return t||(RegExp(n+"(?:-[\\d.]+/|(?: for [\\w-]+)?[ /-])([\\d.]+[^ ();/_-]*)","i").exec(e)||0)[1]||null})}function b(){return this.description||""}var _=c,v=e&&"object"==typeof e&&"String"!=r(e);if(v)_=e,e=null;var x=_.navigator||{},L=x.userAgent||"";e||(e=L);var w,W,I=v||y==p,X=v?!!x.likeChrome:/\bChrome\b/.test(e)&&!/internal|\n/i.test(k.toString()),M="Object",C=v?M:"ScriptBridgingProxyObject",K=v?M:"Environment",S=v&&_.java?"JavaPackage":r(_.java),T=v?M:"RuntimeObject",D=/Java/.test(S)&&_.java,E=D&&r(_.environment)==K,P=D?"a":"α",J=D?"b":"β",A=_.document||{},O=_.operamini||_.opera,F=U.test(F=v&&O?O["[[Class]]"]:r(O))?F:O=null,z=e,N=[],Y=null,B=e==L,H=B&&O&&"function"==typeof O.version&&O.version(),R=n([{label:"WebKit",pattern:"AppleWebKit"},"iCab","Presto","NetFront","Tasman","Trident","KHTML","Gecko"]),G=m(["Adobe AIR","Arora","Avant Browser","Breach","Camino","Epiphany","Fennec","Flock","Galeon","GreenBrowser","iCab","Iceweasel",{label:"SRWare Iron",pattern:"Iron"},"K-Meleon","Konqueror","Lunascape","Maxthon","Midori","Nook Browser","PhantomJS","Raven","Rekonq","RockMelt","SeaMonkey",{label:"Silk",pattern:"(?:Cloud9|Silk-Accelerated)"},"Sleipnir","SlimBrowser","Sunrise","Swiftfox","WebPositive","Opera Mini",{label:"Opera Mini",pattern:"OPiOS"},"Opera",{label:"Opera",pattern:"OPR"},"Chrome",{label:"Chrome Mobile",pattern:"(?:CriOS|CrMo)"},{label:"Firefox",pattern:"(?:Firefox|Minefield)"},{label:"IE",pattern:"MSIE"},"Safari"]),j=V([{label:"BlackBerry",pattern:"BB10"},"BlackBerry",{label:"Galaxy S",pattern:"GT-I9000"},{label:"Galaxy S2",pattern:"GT-I9100"},{label:"Galaxy S3",pattern:"GT-I9300"},{label:"Galaxy S4",pattern:"GT-I9500"},"Google TV","iPad","iPod","iPhone","Kindle",{label:"Kindle Fire",pattern:"(?:Cloud9|Silk-Accelerated)"},"Nook","PlayBook","PlayStation 4","PlayStation 3","PlayStation Vita","TouchPad","Transformer",{label:"Wii U",pattern:"WiiU"},"Wii","Xbox One",{label:"Xbox 360",pattern:"Xbox"},"Xoom"]),q=u({Apple:{iPad:1,iPhone:1,iPod:1},Amazon:{Kindle:1,"Kindle Fire":1},Asus:{Transformer:1},"Barnes & Noble":{Nook:1},BlackBerry:{PlayBook:1},Google:{"Google TV":1},HP:{TouchPad:1},HTC:{},LG:{},Microsoft:{Xbox:1,"Xbox One":1},Motorola:{Xoom:1},Nintendo:{"Wii U":1,Wii:1},Nokia:{},Samsung:{"Galaxy S":1,"Galaxy S2":1,"Galaxy S3":1,"Galaxy S4":1},Sony:{"PlayStation 4":1,"PlayStation 3":1,"PlayStation Vita":1}}),Q=f(["Android","CentOS","Debian","Fedora","FreeBSD","Gentoo","Haiku","Kubuntu","Linux Mint","Red Hat","SuSE","Ubuntu","Xubuntu","Cygwin","Symbian OS","hpwOS","webOS ","webOS","Tablet OS","Linux","Mac OS X","Macintosh","Mac","Windows 98;","Windows "]);if(R&&(R=[R]),q&&!j)j=V([q]);if(w=/Google TV/.exec(j))j=w[0];if(/\bSimulator\b/i.test(e))j=(j?j+" ":"")+"Simulator";if("Opera Mini"==G&&/OPiOS/.test(e))N.push("running in Turbo / Uncompressed mode");if(/^iP/.test(j))G||(G="Safari"),Q="iOS"+((w=/ OS ([\d_]+)/i.exec(e))?" "+w[1].replace(/_/g,"."):"");else if("Konqueror"==G&&!/buntu/i.test(Q))Q="Kubuntu";else if(q&&"Google"!=q&&(/Chrome/.test(G)&&!/Mobile Safari/.test(e)||/Vita/.test(j)))G="Android Browser",Q=/Android/.test(Q)?Q:"Android";else if(!G||(w=!/\bMinefield\b|\(Android;/i.test(e)&&/Firefox|Safari/.exec(G))){if(G&&!j&&/[\/,]|^[^(]+?\)/.test(e.slice(e.indexOf(w+"/")+8)))G=null;if((w=j||q||Q)&&(j||q||/Android|Symbian OS|Tablet OS|webOS/.test(Q)))G=/[a-z]+(?: Hat)?/i.exec(/Android/.test(Q)?Q:w)+" Browser"}if((w=/\((Mobile|Tablet).*?Firefox/i.exec(e))&&w[1])if(Q="Firefox OS",!j)j=w[1];if(!H)H=g(["(?:Cloud9|CriOS|CrMo|Iron|Opera ?Mini|OPiOS|OPR|Raven|Silk(?!/[\\d.]+$))","Version",s(G),"(?:Firefox|Minefield|NetFront)"]);if("iCab"==R&&parseFloat(H)>3)R=["WebKit"];else if(w=/Opera/.test(G)&&(/OPR/.test(e)?"Blink":"Presto")||/\b(?:Midori|Nook|Safari)\b/i.test(e)&&"WebKit"||!R&&/\bMSIE\b/i.test(e)&&("Mac OS"==Q?"Tasman":"Trident"))R=[w];else if(/PlayStation(?! Vita)/i.test(G)&&"WebKit"==R)R=["NetFront"];if("IE"!=G&&"Trident"==R&&(w=/\brv:([\d.]+)/.exec(e))){if(G)N.push("identifying as "+G+(H?" "+H:""));G="IE",H=w[1]}if(B){if(o(_,"global")){if(D)w=D.lang.System,z=w.getProperty("os.arch"),Q=Q||w.getProperty("os.name")+" "+w.getProperty("os.version");if(I&&o(_,"system")&&(w=[_.system])[0]){Q||(Q=w[0].os||null);try{w[1]=_.require("ringo/engine").version,H=w[1].join("."),G="RingoJS"}catch(Z){if(w[0].global.system==_.system)G="Narwhal"}}else if("object"==typeof _.process&&(w=_.process))G="Node.js",z=w.arch,Q=w.platform,H=/[\d.]+/.exec(w.version)[0];else if(E)G="Rhino"}else if(r(w=_.runtime)==C)G="Adobe AIR",Q=w.flash.system.Capabilities.os;else if(r(w=_.phantom)==T)G="PhantomJS",H=(w=w.version||null)&&w.major+"."+w.minor+"."+w.patch;else if("number"==typeof A.documentMode&&(w=/\bTrident\/(\d+)/i.exec(e))){if(H=[H,A.documentMode],(w=+w[1]+4)!=H[1])N.push("IE "+H[1]+" mode"),R&&(R[1]=""),H[1]=w;H="IE"==G?String(H[1].toFixed(1)):H[0]}Q=Q&&i(Q)}if(H&&(w=/(?:[ab]|dp|pre|[ab]\d+pre)(?:\d+\+?)?$/i.exec(H)||/(?:alpha|beta)(?: ?\d)?/i.exec(e+";"+(B&&x.appMinorVersion))||/\bMinefield\b/i.test(e)&&"a"))Y=/b/i.test(w)?"beta":"alpha",H=H.replace(RegExp(w+"\\+?$"),"")+("beta"==Y?J:P)+(/\d+\+?/.exec(w)||"");if("Fennec"==G||"Firefox"==G&&/Android|Firefox OS/.test(Q))G="Firefox Mobile";else if("Maxthon"==G&&H)H=H.replace(/\.[\d.]+/,".x");else if("Silk"==G){if(!/Mobi/i.test(e))Q="Android",N.unshift("desktop mode");if(/Accelerated *= *true/i.test(e))N.unshift("accelerated")}else if("IE"==G&&(w=(/; *(?:XBLWP|ZuneWP)(\d+)/i.exec(e)||0)[1]))G+=" Mobile",Q="Windows Phone OS "+w+".x",N.unshift("desktop mode");else if(/Xbox/i.test(j)){if(Q=null,"Xbox 360"==j&&/IEMobile/.test(e))N.unshift("mobile mode")}else if(("Chrome"==G||"IE"==G||G&&!j&&!/Browser|Mobi/.test(G))&&("Windows CE"==Q||/Mobi/i.test(e)))G+=" Mobile";else if("IE"==G&&B&&null===_.external)N.unshift("platform preview");else if((/BlackBerry/.test(j)||/BB10/.test(e))&&(w=(RegExp(j.replace(/ +/g," *")+"/([.\\d]+)","i").exec(e)||0)[1]||H))w=[w,/BB10/.test(e)],Q=(w[1]?(j=null,q="BlackBerry"):"Device Software")+" "+w[0],H=null;else if(this!=a&&"Wii"!=j&&(B&&O||/Opera/.test(G)&&/\b(?:MSIE|Firefox)\b/i.test(e)||"Firefox"==G&&/OS X (?:\d+\.){2,}/.test(Q)||"IE"==G&&(Q&&!/^Win/.test(Q)&&H>5.5||/Windows XP/.test(Q)&&H>8||8==H&&!/Trident/.test(e)))&&!U.test(w=d.call(a,e.replace(U,"")+";"))&&w.name){if(w="ing as "+w.name+((w=w.version)?" "+w:""),U.test(G)){if(/IE/.test(w)&&"Mac OS"==Q)Q=null;w="identify"+w}else{if(w="mask"+w,F)G=i(F.replace(/([a-z])([A-Z])/g,"$1 $2"));else G="Opera";if(/IE/.test(w))Q=null;if(!B)H=null}R=["Presto"],N.push(w)}if(w=(/\bAppleWebKit\/([\d.]+\+?)/i.exec(e)||0)[1]){if(w=[parseFloat(w.replace(/\.(\d)$/,".0$1")),w],"Safari"==G&&"+"==w[1].slice(-1))G="WebKit Nightly",Y="alpha",H=w[1].slice(0,-1);else if(H==w[1]||H==(w[2]=(/\bSafari\/([\d.]+\+?)/i.exec(e)||0)[1]))H=null;if(w[1]=(/\bChrome\/([\d.]+)/i.exec(e)||0)[1],537.36==w[0]&&537.36==w[2]&&parseFloat(w[1])>=28)R=["Blink"];if(!B||!X&&!w[1])R&&(R[1]="like Safari"),w=w[0],w=400>w?1:500>w?2:526>w?3:533>w?4:534>w?"4+":535>w?5:537>w?6:538>w?7:"7";else R&&(R[1]="like Chrome"),w=w[1]||(w=w[0],530>w?1:532>w?2:532.05>w?3:533>w?4:534.03>w?5:534.07>w?6:534.1>w?7:534.13>w?8:534.16>w?9:534.24>w?10:534.3>w?11:535.01>w?12:535.02>w?"13+":535.07>w?15:535.11>w?16:535.19>w?17:536.05>w?18:536.1>w?19:537.01>w?20:537.11>w?"21+":537.13>w?23:537.18>w?24:537.24>w?25:537.36>w?26:"Blink"!=R?"27":"28");if(R&&(R[1]+=" "+(w+="number"==typeof w?".x":/[.+]/.test(w)?"":"+")),"Safari"==G&&(!H||parseInt(H)>45))H=w}if("Opera"==G&&(w=/(?:zbov|zvav)$/.exec(Q)))if(G+=" ",N.unshift("desktop mode"),"zvav"==w)G+="Mini",H=null;else G+="Mobile";else if("Safari"==G&&/Chrome/.exec(R&&R[1]))if(N.unshift("desktop mode"),G="Chrome Mobile",H=null,/OS X/.test(Q))q="Apple",Q="iOS 4.3+";else Q=null;if(H&&0==H.indexOf(w=/[\d.]+$/.exec(Q))&&e.indexOf("/"+w+"-")>-1)Q=h(Q.replace(w,""));if(R&&!/Avant|Nook/.test(G)&&(/Browser|Lunascape|Maxthon/.test(G)||/^(?:Adobe|Arora|Breach|Midori|Opera|Phantom|Rekonq|Rock|Sleipnir|Web)/.test(G)&&R[1]))(w=R[R.length-1])&&N.push(w);if(N.length)N=["("+N.join("; ")+")"];if(q&&j&&j.indexOf(q)<0)N.push("on "+q);if(j)N.push((/^on /.test(N[N.length-1])?"":"on ")+j);if(Q)w=/ ([\d.+]+)$/.exec(Q),W=w&&"/"==Q.charAt(Q.length-w[0].length-1),Q={architecture:32,family:w&&!W?Q.replace(w[0],""):Q,version:w?w[1]:null,toString:function(){var e=this.version;return this.family+(e&&!W?" "+e:"")+(64==this.architecture?" 64-bit":"")}};if((w=/\b(?:AMD|IA|Win|WOW|x86_|x)64\b/i.exec(z))&&!/\bi686\b/i.test(z)){if(Q)Q.architecture=64,Q.family=Q.family.replace(RegExp(" *"+w),"");if(G&&(/WOW64/i.test(e)||B&&/\w(?:86|32)$/.test(x.cpuClass||x.platform)&&!/^win32$/i.test(x.platform)))N.unshift("32-bit")}e||(e=null);var $={};if($.description=e,$.layout=R&&R[0],$.manufacturer=q,$.name=G,$.prerelease=Y,$.product=j,$.ua=e,$.version=G&&H,$.os=Q||{architecture:null,family:null,version:null,toString:function(){return"null"}},$.parse=d,$.toString=b,$.version)N.unshift(H);if($.name)N.unshift(G);if(Q&&G&&(Q!=String(Q).split(" ")[0]||Q!=G.split(" ")[0]&&!j))N.push(j?"("+Q+")":"on "+Q);if(N.length)$.description=N.join(" ");return $}var u={"function":!0,object:!0},c=u[typeof window]&&window||this,p=c,m=u[typeof exports]&&exports,f=u[typeof module]&&module&&!module.nodeType&&module,V=m&&f&&"object"==typeof global&&global;if(V&&(V.global===V||V.window===V||V.self===V))c=V;var g=Math.pow(2,53)-1,U=/Opera/,y=this,b=Object.prototype,_=b.hasOwnProperty,k=b.toString;if("function"==typeof define&&"object"==typeof define.amd&&define.amd)define([],function(){return d()});else if(m&&f)a(d(),function(e,t){m[t]=e});else c.platform=d()}).call(this);