<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

	<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
	<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />

    <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    var usedMemory;
	function getUsedMemory(val){
		usedMemory = val;
	}
    function drawChart() {
	indicatorCall("usedMemory",getUsedMemory);
    var text= usedMemory;// this list contains the Date and Memory Usage data from the model
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
	setInterval(function() {
		drawChart();
    }, 1000);
    </script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChartPerMin);
    var usedMemoryPerMin;
	function getUsedMemoryPerMin(val){
		usedMemoryPerMin = val;
	}
    function drawChartPerMin() {
	indicatorCall("usedMemoryPerMin",getUsedMemoryPerMin);
    var perminvalues = usedMemoryPerMin;   // this list contains the Date and Memory Usage data from the model
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
	setInterval(function() {
		drawChartPerMin();
    }, 1000);
    </script>
    </head>

    <body>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='memory_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('memory_head','memory_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.memusage.persec"/></b><b id='cpu_head'></b><br>
			</font>
		</div>
		<div id='memory_info' align="center">
			<div id="chart_div" style="height:400px"></div><br>
  		</div>
	</div>
  	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='memory_per_min_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('memory_per_min_head','memory_per_min_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.memusage.permin"/></b><b id='jvm_head'></b><br>
			</font>
		</div>
		<div id='memory_per_min_info'  align="center">
			<div id="permin_chart_div" style="height:400px"></div>
  		</div>
	</div>
    </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>