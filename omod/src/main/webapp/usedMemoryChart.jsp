<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

    <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);

    function drawChart() {

    var text= "${values}";   // this list contains the Date and Memory Usage data from the model
    var timeAndMemUsageArray = eval("[" + text + "]");
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
    dataTable.addColumn('number', 'Memory');
    dataTable.addRows(timeAndMemUsageArray);

    var options = {
    title:'Per second',
    chartArea : { left :150 },
    hAxis: { title: 'Time' },
    vAxis: { title: 'Memory (MB)' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(dataTable, options);
    }
    </script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);

    function drawChart() {

    var perminvalues= "${perMinValues}";   // this list contains the Date and Memory Usage data from the model
    var perMinTimeAndMemUsageArray = eval("[" + perminvalues + "]");
    var perminDataTable = new google.visualization.DataTable();
    perminDataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
    perminDataTable.addColumn('number', 'Memory');
    perminDataTable.addRows(perMinTimeAndMemUsageArray);

    var perMinOptions = {
    title:'Per minute',
    chartArea : { left :150 },
    hAxis: { title: 'Time' },
    vAxis: { title: 'Memory (MB)' }
    };

    var perMinChart = new google.visualization.LineChart(document.getElementById('permin_chart_div'));
    perMinChart.draw(perminDataTable, perMinOptions);
    }
    </script>
    </head>

    <body>
    <div id="perSecondMemoryChart">
    <b class="boxHeader"> Used Memory Statistics (MB) against Date-Time  </b>
    <div class="box">
        <div id="chart_div" style="height:400px"></div>
        <div id="permin_chart_div" style="height:400px"></div>
    </div>
    </div>


    </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>