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

        var text= "${conceptCounts}";   // this list contains the Date and Memory Usage data from the model
        var timeAndEncCountArray = eval("[" + text + "]");
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
        dataTable.addColumn('number', 'Concepts Count');
        dataTable.addRows(timeAndEncCountArray);

        var options = {
        title:'Per hour',
        chartArea : { left :150 },
        hAxis: { title: 'Time' },
        vAxis: { title: 'Concepts Count' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('concept_div'));
        chart.draw(dataTable, options);
        }
        </script>

        </head>

        <body>
        <div id="perHourEncountersChart">
        <b class="boxHeader"> Created Concepts against Date-Time  </b>
        <div class="box">
        <div id="concept_div" style="height:400px"></div>
        </div>
        </div>
        </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>