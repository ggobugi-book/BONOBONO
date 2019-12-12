
window.onload=function(){

    var Request = function() {
        this.getParameter = function(name) {
            var rtnval = '';
            var nowAddress = decodeURIComponent(location.href);
            var parameters = (nowAddress.slice(nowAddress.indexOf('?') + 1,
                    nowAddress.length)).split('&');
            for (var i = 0; i < parameters.length; i++) {
                var varName = parameters[i].split('=')[0];
                if (varName.toUpperCase() == name.toUpperCase()) {
                    rtnval = parameters[i].split('=')[1];
                    break;
                }
            }
            return rtnval;
        }
    }
    var request = new Request();
    var paramValue = request.getParameter('text');





// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("chartdiv", am4plugins_forceDirected.ForceDirectedTree);
/* chart.chartContainer.background.events.on("hit", function () { zoomOut() }); */
var networkSeries = chart.series.push(new am4plugins_forceDirected.ForceDirectedSeries())
networkSeries.dataFields.linkWith = "linkWith";
networkSeries.dataFields.name = "name";
networkSeries.dataFields.id = "name";
networkSeries.dataFields.value = "value";
networkSeries.dataFields.children = "children";

/* networkSeries.dataFields.linkWith = "link";
networkSeries.dataFields.name = "named";
networkSeries.dataFields.id = "named"; */



networkSeries.nodes.template.label.text = "{name}"
networkSeries.fontSize = 30;
networkSeries.linkWithStre1ngth = 0;
networkSeries.links.template.distance = 1;
networkSeries.maxRadius = am4core.percent(10);
networkSeries.manyBodyStrength = -16;
networkSeries.nodes.template.label.hideOversized = true;
networkSeries.nodes.template.label.truncate = true;
networkSeries.dataFields.collapsed = "off";
var nodeTemplate = networkSeries.nodes.template;
nodeTemplate.tooltipText = "{name}";
nodeTemplate.fillOpacity = 1;
nodeTemplate.label.hideOversized = true;
nodeTemplate.label.truncate = true;

var linkTemplate = networkSeries.links.template;
linkTemplate.strokeWidth = 5;
var linkHoverState = linkTemplate.states.create("hover");
linkHoverState.properties.strokeOpacity = 1;
linkHoverState.properties.strokeWidth = 5;




nodeTemplate.events.on("over", function (event) {
    var dataItem = event.target.dataItem;
    dataItem.childLinks.each(function (link) {
        link.isHover = true;
    })
})


nodeTemplate.events.on("out", function (event) {
    var dataItem = event.target.dataItem;
    dataItem.childLinks.each(function (link) {
        link.isHover = false;
    })
})



/*
var selectedNode;

networkSeries.nodes.template.events.on("up", function (event) {
  var node = event.target;
  if (!selectedNode) {
    node.outerCircle.disabled = false;
    node.outerCircle.strokeDasharray = "3,3";
    selectedNode = node;
  }
  else if (selectedNode == node) {
    *//* node.outerCircle.disabled = true; *//*
    node.outerCircle.strokeDasharray = "";
    selectedNode = undefined;
  }
  else {
    var node = event.target;

    var link = node.linksWith.getKey(selectedNode.uid);

    if (link) {
      node.unlinkWith(selectedNode);
    }
    else {
      node.linkWith(selectedNode, 0.2);
    }
  }
})*/



networkSeries.data = JSON.parse(paramValue);







}



