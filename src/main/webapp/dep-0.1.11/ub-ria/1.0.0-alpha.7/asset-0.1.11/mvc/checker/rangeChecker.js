define("ub-ria/mvc/checker/rangeChecker",["require"],function(){function e(e,t){if(!e&&0!==e)return!0;var n=t[2].min,i=t[2].max;if(n>i){var a=i;i=n,n=a}return e>=n&&i>=e}var t={name:"range",errorMessage:"${title}必须是≥${min}且≤${max}的数字",priority:20,check:e};return t});