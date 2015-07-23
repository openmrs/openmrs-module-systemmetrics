<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeaderApp.jsp"%>

	<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
	<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/charts/highcharts.js" />
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />

    <head>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
	var globalAppData;
	function getAppData(val){
		globalAppData = val;
	}
	
	function convertDataToArr(data){
		var val = new Array();
		var text= data;// this list contains the Date and Memory Usage data from the model
		var timeAndDataUsageArray = eval("[" + text + "]");
		var i;
		for(i=0;i<timeAndDataUsageArray.length;i++){
			val[i] = timeAndDataUsageArray[i][1];
		}
		return val;
	}
	function drawChart(){
		indicatorCall("globalAppData",getAppData);
		
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
    dataTable.addColumn('number', globalAppData[0]);
	dataTable.addColumn('number', globalAppData[2]);
	dataTable.addColumn('number', globalAppData[4]);
	dataTable.addColumn('number', globalAppData[6]);
	dataTable.addColumn('number', globalAppData[8]);
	dataTable.addColumn('number', globalAppData[10]);
	dataTable.addColumn('number', globalAppData[12]);
	
	var text = globalAppData[1];// this list contains the Date and Memory Usage data from the model
	var timeArray = eval("[" + text + "]");
	 // determine the number of rows and columns.
    var numRows = convertDataToArr(globalAppData[1]).length;
    var numCols = globalAppData.length/2;
	var rowData = []
	for(var i=0; i<numRows; i++){
		rowData = [];
		rowData = rowData.concat(timeArray[i][0]);
		for(var j=0; j<numCols;j++){
			var val = convertDataToArr(globalAppData[2*j+1])[i]!='undefined'?convertDataToArr(globalAppData[2*j+1])[i]:0;
			rowData = rowData.concat(val);

		}
		if(rowData.length==8)
		dataTable.addRow(rowData);            
	}

    var dataOptions = {
    title:'Per Hour',
    chartArea : { left :150 },
    hAxis: { title: 'Time' },
    vAxis: { title: 'Counts' }
    };

    var dataChart = new google.visualization.LineChart(document.getElementById('chart_div'));
    dataChart.draw(dataTable, dataOptions);
	}
	setInterval(function() {
		drawChart();
    }, 1000);
	</script>
    </head>

    <body>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
			<font color="#ffffff" size="4pt">
				<b>Application Indicators</b>
			</font>
		</div>
		<br>
		<div id='info'  align="center">
			<div id="chart_div" style="height:400px"></div>
  		</div>
	</div>
    </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>