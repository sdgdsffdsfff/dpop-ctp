!function(e){if("function"==typeof define&&define.amd)define("moment/lang/br",["moment"],e);else if("object"==typeof exports)module.exports=e(require("../moment"));else e(window.moment)}(function(e){function t(e,t,i){var n={mm:"munutenn",MM:"miz",dd:"devezh"};return e+" "+a(n[i],e)}function i(e){switch(n(e)){case 1:case 3:case 4:case 5:case 9:return e+" bloaz";default:return e+" vloaz"}}function n(e){if(e>9)return n(e%10);else return e}function a(e,t){if(2===t)return r(e);else return e}function r(e){var t={m:"v",b:"v",d:"z"};if(void 0===t[e.charAt(0)])return e;else return t[e.charAt(0)]+e.substring(1)}return e.lang("br",{months:"Genver_C'hwevrer_Meurzh_Ebrel_Mae_Mezheven_Gouere_Eost_Gwengolo_Here_Du_Kerzu".split("_"),monthsShort:"Gen_C'hwe_Meu_Ebr_Mae_Eve_Gou_Eos_Gwe_Her_Du_Ker".split("_"),weekdays:"Sul_Lun_Meurzh_Merc'her_Yaou_Gwener_Sadorn".split("_"),weekdaysShort:"Sul_Lun_Meu_Mer_Yao_Gwe_Sad".split("_"),weekdaysMin:"Su_Lu_Me_Mer_Ya_Gw_Sa".split("_"),longDateFormat:{LT:"h[e]mm A",L:"DD/MM/YYYY",LL:"D [a viz] MMMM YYYY",LLL:"D [a viz] MMMM YYYY LT",LLLL:"dddd, D [a viz] MMMM YYYY LT"},calendar:{sameDay:"[Hiziv da] LT",nextDay:"[Warc'hoazh da] LT",nextWeek:"dddd [da] LT",lastDay:"[Dec'h da] LT",lastWeek:"dddd [paset da] LT",sameElse:"L"},relativeTime:{future:"a-benn %s",past:"%s 'zo",s:"un nebeud segondennoù",m:"ur vunutenn",mm:t,h:"un eur",hh:"%d eur",d:"un devezh",dd:t,M:"ur miz",MM:t,y:"ur bloaz",yy:i},ordinal:function(e){var t=1===e?"añ":"vet";return e+t},week:{dow:1,doy:4}})});