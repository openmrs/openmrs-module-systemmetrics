<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

    <body>
    <svg id="chart"></svg>
    </body>


    <script src="http://d3js.org/d3.v3.js"></script>
    <script src="https://raw.githubusercontent.com/novus/nvd3/master/nv.d3.min.js"></script>
    <script>

    nv.addGraph(function() {
    var chart = nv.models.lineChart()
    .margin({left: 100}).margin({top: 400})  //Adjust chart margins
    .useInteractiveGuideline(true)
    .transitionDuration(350)  //how fast the lines to transition?
    .showLegend(true)
    .showYAxis(true)        //Show the y-axis
    .showXAxis(true)        //Show the x-axis
    ;

    chart.xAxis     //Chart x-axis settings
    .axisLabel('Timestamp (ms)')
    .tickFormat(d3.format(',r'))
    ;

    chart.yAxis     //Chart y-axis settings
    .axisLabel('Memory Usage (MB)')
    .tickFormat(d3.format('.02f'));


    var memData = getMemoryValues();   // data...
    console.log(memData);
    d3.select('#chart')      //Select the <svg> element you want to render the chart in.
    .datum(memData)         //Populate the <svg> element with chart data...
    .call(chart);          //Render the chart!

    //Update the chart when window resizes.
    nv.utils.windowResize(function() { chart.update() });
    return chart;
    });

   /*
    * Test data
    */
    function getMemoryValues() {
    var chartEntries = [];

    //Data is represented as an array of {x,y} pairs.

    chartEntries.push({x: 1, y: 435.89});
    chartEntries.push({x: 2, y: 435.73});
    chartEntries.push({x: 3, y: 435.88});
    chartEntries.push({x: 4, y: 434.39});
    chartEntries.push({x: 5, y: 435.99});


    //Line chart data should be sent as an array of series objects.
    return [
    {
    values: chartEntries,      //values - represents the array of {x,y} data points
    key: 'Used Memory', //key  - the name of the series.
    color: '#ff7f0e'  //color -  line color.
    }
    ];
    }

    </script>

<%@ include file="/WEB-INF/template/footer.jsp"%>