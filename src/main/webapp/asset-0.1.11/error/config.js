/*! 2015 Baidu Inc. All Rights Reserved */
define("error/config",["require","./GenericError","er/controller"],function(require){require("./GenericError");var e=[{path:"/404",type:"error/NotFound",title:"无法找到页面"},{path:"/400",type:"error/GenericError",title:"出现未知问题"}];require("er/controller").registerAction(e)});