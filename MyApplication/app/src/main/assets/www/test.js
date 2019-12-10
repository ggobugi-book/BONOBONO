
window.onload=function(){

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
networkSeries.fontSize = 8;
networkSeries.linkWithStre1ngth = 0;
networkSeries.links.template.distance = 1;
networkSeries.maxRadius = am4core.percent(18);
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


networkSeries.data = [
   {
      "name":"여우",
      "value":102,
      "linkWith":[

      ],
      "children":[
         {
            "name":"David",
            "value":14
         },
         {
            "name":"Roger",
            "value":1
         },
         {
            "name":"Duncan",
            "value":1
         },
         {
            "name":"Rob Dohnen",
            "value":2
         },
         {
            "name":"Ryan",
            "value":5
         },
         {
            "name":"Malcom",
            "value":1
         },
         {
            "name":"Robert",
            "value":1
         },
         {
            "name":"Sergei",
            "value":1
         },
         {
            "name":"Vince",
            "value":2
         },
         {
            "name":"Jason",
            "value":1
         },
         {
            "name":"Rick",
            "value":2
         },
         {
            "name":"Gary",
            "value":7
         },
         {
            "name":"Jake",
            "value":2
         },
         {
            "name":"Eric",
            "value":3
         },
         {
            "name":"Mike",
            "value":18
         }
      ]
   },
   {
      "name":"왕자",
      "value":204,
      "link":[

      ],
      "children":[
         {
            "name":"Paul the wine guy",
            "value":1
         },
         {
            "name":"Mr Geller",
            "value":8
         },
         {
            "name":"Mrs Geller",
            "value":14
         },
         {
            "name":"Aunt Lilian",
            "value":2
         },
         {
            "name":"Nana",
            "value":1
         },
         {
            "name":"Young Ethan",
            "value":5
         },
         {
            "name":"Ben",
            "value":9
         },
         {
            "name":"Fun Bobby",
            "value":3
         },
         {
            "name":"Richard",
            "value":16
         },
         {
            "name":"Mrs Green",
            "value":4
         },
         {
            "name":"Paolo2",
            "value":1
         },
         {
            "name":"Pete",
            "value":10
         },
         {
            "name":"Chip",
            "value":1
         },
         {
            "name":"Timothy (Burke)",
            "value":1
         },
         {
            "name":"Emily",
            "value":17
         },
         {
            "name":"Dr. Roger",
            "value":3
         }
      ]
   },
   {
   "name" : "왕자, 여우",
   "value" : 50,
   "linkWith" : ["왕자", "여우"]
   },
    {
      "name":"바오밥나무",
      "value":95,
      "linkWith":[

      ],
      "children":[
         {
            "name":"David",
            "value":14
         },
         {
            "name":"Roger",
            "value":1
         },
         {
            "name":"Duncan",
            "value":1
         },
         {
            "name":"Rob Dohnen",
            "value":2
         },
         {
            "name":"Ryan",
            "value":5
         },
         {
            "name":"Malcom",
            "value":1
         },
         {
            "name":"Robert",
            "value":1
         },
         {
            "name":"Sergei",
            "value":1
         },
         {
            "name":"Vince",
            "value":2
         },
         {
            "name":"Jason",
            "value":1
         },
         {
            "name":"Rick",
            "value":2
         },
         {
            "name":"Gary",
            "value":7
         },
         {
            "name":"Jake",
            "value":2
         },
         {
            "name":"Eric",
            "value":3
         },
         {
            "name":"Mike",
            "value":18
         }
      ]
   },  {
   "name" : "왕자, 바오밥나무",
   "value" : 50,
   "linkWith" : ["왕자", "바오밥나무"]
   },  {
   "name" : "바오밥나무, 여우",
   "value" : 50,
   "linkWith" : ["바오밥나무", "여우"]
   },
];




}



