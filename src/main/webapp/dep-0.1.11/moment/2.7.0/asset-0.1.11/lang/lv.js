!function(e){if("function"==typeof define&&define.amd)define("moment/lang/lv",["moment"],e);else if("object"==typeof exports)module.exports=e(require("../moment"));else e(window.moment)}(function(e){function t(e,t,i){var n=e.split("_");if(i)return t%10===1&&11!==t?n[2]:n[3];else return t%10===1&&11!==t?n[0]:n[1]}function i(e,i,a){return e+" "+t(n[a],e,i)}var n={mm:"minūti_minūtes_minūte_minūtes",hh:"stundu_stundas_stunda_stundas",dd:"dienu_dienas_diena_dienas",MM:"mēnesi_mēnešus_mēnesis_mēneši",yy:"gadu_gadus_gads_gadi"};return e.lang("lv",{months:"janvāris_februāris_marts_aprīlis_maijs_jūnijs_jūlijs_augusts_septembris_oktobris_novembris_decembris".split("_"),monthsShort:"jan_feb_mar_apr_mai_jūn_jūl_aug_sep_okt_nov_dec".split("_"),weekdays:"svētdiena_pirmdiena_otrdiena_trešdiena_ceturtdiena_piektdiena_sestdiena".split("_"),weekdaysShort:"Sv_P_O_T_C_Pk_S".split("_"),weekdaysMin:"Sv_P_O_T_C_Pk_S".split("_"),longDateFormat:{LT:"HH:mm",L:"DD.MM.YYYY",LL:"YYYY. [gada] D. MMMM",LLL:"YYYY. [gada] D. MMMM, LT",LLLL:"YYYY. [gada] D. MMMM, dddd, LT"},calendar:{sameDay:"[Šodien pulksten] LT",nextDay:"[Rīt pulksten] LT",nextWeek:"dddd [pulksten] LT",lastDay:"[Vakar pulksten] LT",lastWeek:"[Pagājušā] dddd [pulksten] LT",sameElse:"L"},relativeTime:{future:"%s vēlāk",past:"%s agrāk",s:"dažas sekundes",m:"minūti",mm:i,h:"stundu",hh:i,d:"dienu",dd:i,M:"mēnesi",MM:i,y:"gadu",yy:i},ordinal:"%d.",week:{dow:1,doy:4}})});