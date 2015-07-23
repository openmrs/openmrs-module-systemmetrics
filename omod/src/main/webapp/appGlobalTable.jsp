<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeaderApp.jsp"%>

	<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
	<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

	<openmrs:htmlInclude file="/moduleResources/systemmetrics/css/styles.css"/>
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/charts/highcharts.js" />
	<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />

    <head>
	<script type="text/javascript">

	var globalAppData;
	function getAppData(val){
		globalAppData = val;
	}
	
	function updateTable(dataArray){
		var data_table = document.getElementById('count_data');
		data_table.innerHTML = "";
		var data_td;
		for(var i = 0; i < dataArray.length; i+=2){
			data_td= '<tr><td>'+dataArray[i]+
					'</td><td>'+dataArray[i+1]+
					'</td></tr>';
			data_table.innerHTML += data_td;
		}	
	}
	function drawTable(){
		indicatorCall("globalAppTableData",getAppData);
		console.log(globalAppData);
		updateTable(globalAppData);
	}
	setInterval(function() {
		drawTable();
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
		<table id="count_table" class="CSSTableGenerator">
            <thead align="center">    
                <th>Field</th> 
				<th>Count</th>  
            </thead>
			<tbody id="count_data">  
			</tbody>    				
		</table>
  		</div>
	</div>
    </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>