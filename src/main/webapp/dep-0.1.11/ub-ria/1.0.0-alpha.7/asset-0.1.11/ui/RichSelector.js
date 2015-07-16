define("ub-ria/ui/RichSelector",["require","esui/Label","esui/Panel","esui/SearchBox","esui/lib","esui/painters","esui/InputControl","underscore","esui"],function(require){function e(){r.apply(this,arguments)}function t(){this.search()}function i(e){e.removeState("queried");var t=e.getSearchBox();if(t)t.set("text","")}require("esui/Label"),require("esui/Panel"),require("esui/SearchBox");var n=require("esui/lib"),a=require("esui/painters"),r=require("esui/InputControl"),o=require("underscore");return n.inherits(e,r),e.prototype.type="RichSelector",e.prototype.initOptions=function(e){var t={height:340,width:200,hasHead:!0,title:"标题名",needBatchAction:!1,batchActionLabel:"批量操作",hasSearchBox:!0,hasFoot:!0,itemName:"结果",emptyText:"没有相应的搜索结果",holdState:!1,mode:"add",multi:!0};if("false"===e.hasHead)e.hasHead=!1;if("false"===e.hasSearchBox)e.hasSearchBox=!1;if("false"===e.hasFoot)e.hasFoot=!1;if("false"===e.holdState)e.holdState=!1;if("false"===e.multi)e.multi=!1;n.extend(t,e),t.width=Math.max(200,t.width),this.setProperties(t)},e.prototype.getHeadHTML=function(){var e=this.helper,t="";if(this.needBatchAction){var i=e.getPartClassName("batch-action-link"),a=this.helper.getId("batch-action");t='<span class="'+i+'" id="'+a+'" >'+this.batchActionLabel+"</span>"}var r=['<div data-ui="type:Panel;childName:head;"',' class="${headClass}">','<h3 data-ui="type:Label;childName:title;">',"${title}</h3>","${actionLink}","</div>"].join("\n");return r=n.format(r,{headClass:e.getPartClassName("head"),title:this.title,actionLink:t})},e.prototype.getFootHTML=function(){return['<div data-ui="type:Panel;childName:foot;"',' class="'+this.helper.getPartClassName("foot")+'">','<span data-ui="type:Label;childName:totalCount">',"</span>","</div>"].join("\n")},e.prototype.initStructure=function(){var e=["${head}",'<div data-ui="type:Panel;childName:body;"',' class="${bodyClass}">',"${searchInput}",'<div data-ui="type:Panel;childName:content"',' class="${contentClass}">','<div data-ui="type:Label;childName:emptyText"',' class="${emptyTextClass}">${emptyText}</div>','<div data-ui="type:Panel;childName:queryList"',' class="${queryListClass}">',"</div>","</div>","</div>","${footInfo}"],i=this.helper,a="";if(this.hasHead)a=this.getHeadHTML();var r="";if(this.hasSearchBox){var s=this.width-45;r=['<div data-ui="type:Panel;childName:searchBoxArea"',' class="${searchWrapperClass}">','<div data-ui="type:SearchBox;childName:itemSearch;"',' data-ui-skin="magnifier"',' data-ui-width="'+s+'">',"</div>","</div>",'<div data-ui="type:Panel;','childName:generalQueryResultArea"',' class="${generalQueryResultClass}"',' id="${queryResultId}">','<span class="${linkClass}" id="${linkId}">清空</span>','共找到<span id="${queryResultCountId}"></span>个',"</div>"].join("\n"),r=n.format(r,{searchWrapperClass:i.getPartClassName("search-wrapper"),generalQueryResultClass:i.getPartClassName("query-result-general"),queryResultCountId:i.getId("result-count"),linkClass:i.getPartClassName("clear-query-link"),linkId:i.getId("clear-query")})}var l="";if(this.hasFoot)l=this.getFootHTML();if(this.main.style.width=this.width+"px",this.main.innerHTML=n.format(e.join("\n"),{head:a,bodyClass:i.getPartClassName("body"),searchInput:r,contentClass:i.getPartClassName("content-wrapper"),emptyTextClass:i.getPartClassName("empty-text"),emptyText:this.emptyText,queryListClass:i.getPartClassName("query-list"),footInfo:l}),this.initChildren(),"load"===this.mode)this.addState("load");else if("add"===this.mode)this.addState("add");else this.addState("del");var h=i.getPart("batch-action");if(h)i.addDOMEvent(h,"click",o.bind(this.batchAction,this));var d=i.getPart("clear-query");if(d)i.addDOMEvent(d,"click",o.bind(this.clearQuery,this));var m=this.getSearchBox();if(m)m.on("search",t,this);var u=this.getQueryList().main;i.addDOMEvent(u,"click",o.bind(this.eventDispatcher,this))},e.prototype.eventDispatcher=function(){return!1},e.prototype.search=function(){var e={filterData:[]};if(e=this.fire("search",e),!e.isDefaultPrevented()){var t=this.getSearchBox();if(t){var i={value:n.trim(t.getValue())};e.filterData.push(i)}}if(e.filterData.length)this.queryItem(e.filterData),this.refreshResult(),this.refreshFoot(),this.addState("queried"),this.adjustHeight();else this.clearQuery()},e.prototype.refreshResult=function(){var e=this.getCurrentStateItemsCount(),t=this.helper.getPart("result-count");if(t)t.innerHTML=e},e.prototype.clearQuery=function(){return i(this),this.clearData(),this.refreshResult(),this.refreshContent(),this.refreshFoot(),this.adjustHeight(),this.fire("clearquery"),!1},e.prototype.getContent=function(){var e=this.getChild("body");if(e)return e.getChild("content");else return null},e.prototype.getKeyword=function(){var e=this.getSearchBox(),t=this.isQuery();if(e&&t)return n.trim(e.getValue());else return null},e.prototype.getQueryList=function(){var e=this.getContent();if(e)return e.getChild("queryList");else return null},e.prototype.getSearchBox=function(){var e=this.getChild("body").getChild("searchBoxArea");if(e)return e.getChild("itemSearch");else return void 0},e.prototype.getTotalCountPanel=function(){var e=this.getChild("foot");if(!e)return null;else return e.getChild("totalCount")},e.prototype.isQuery=function(){return this.hasState("queried")},e.prototype.batchAction=function(){if("delete"===this.mode)this.deleteAll(),this.refreshFoot();else if("add"===this.mode)this.selectAll();return!1},e.prototype.deleteAll=function(){return!1},e.prototype.addAll=function(){return!1},e.prototype.adjustHeight=function(){var e=this.height,t=28,i=this.hasSearchBox?45:0,n=this.hasFoot?25:0,a=e-t-i-n;if(this.isQuery())a-=30;var r=this.getContent().main;r.style.height=a+"px"},e.prototype.adaptData=function(){},e.prototype.refresh=function(){var e=this.adaptData(),t=!0;if(this.hasSearchBox&&this.isQuery())if(this.holdState)this.search(this.getKeyword()),t=!1;else i(this);if(t)this.refreshContent(),this.processDataAfterRefresh(e),this.refreshFoot(),this.adjustHeight()},e.prototype.processDataAfterRefresh=function(){},e.prototype.refreshFoot=function(){if(this.hasFoot){var e=this.getCurrentStateItemsCount(),t=this.getTotalCountPanel();if(t){var i=o.escape(this.itemName);t.setText("共 "+e+" 个"+i)}}},e.prototype.getCurrentStateItemsCount=function(){return 0},e.prototype.repaint=a.createRepaint(r.prototype.repaint,{name:"title",paint:function(e,t){var i=e.getChild("head"),n=i&&i.getChild("title");n&&n.setText(t)}}),e.prototype.getSelectedItems=function(){return[]},e.prototype.selectItems=function(){},e.prototype.setRawValue=function(e){this.rawValue=e,this.selectItems(e,!0)},e.prototype.getRawValue=function(){return this.getSelectedItems()},e.prototype.stringifyValue=function(e){var t=[];return o.each(e,function(e){t.push(e.id)}),t.join(",")},require("esui").register(e),e});