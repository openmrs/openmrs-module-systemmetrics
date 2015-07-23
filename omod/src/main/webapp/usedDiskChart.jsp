<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

<openmrs:htmlInclude file="/moduleResources/systemmetrics/css/styles.css"/>
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/charts/smoothie.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />
    
	<script type="text/javascript">
	/*
	* Update Disk Usage Table
	*/
	var size = 0; // size stands for data size {Bytes, KB, MB, GB} 
	var disk_usage_val;
	function getDiskUsage(val){
		disk_usage_val = val;
	}
	function updateTable(){
		var disk_table = document.getElementById('disk_usage_data');
		disk_table.innerHTML = "";
		var disk_td;
		for(var i = 0; i < disk_usage_val.length; i++){
			disk_td= '<tr><td>'+disk_usage_val[i].absolutePath+
					'</td><td>'+((disk_usage_val[i].totalSpace-disk_usage_val[i].freeSpace)/Math.pow(1024,size)).toFixed(size)+
					'</td><td>'+(disk_usage_val[i].freeSpace/Math.pow(1024,size)).toFixed(size)+
					'</td><td>'+(disk_usage_val[i].totalSpace/Math.pow(1024,size)).toFixed(size)+
					'</td></tr>';
			disk_table.innerHTML += disk_td;
		}	
	}

	 setInterval(function(){indicatorCall("disk_usage",getDiskUsage);	updateTable();}, 1000);
	  
	 function setDataSize(){
		size = $('input[name="size"]:checked').val();
		updateTable();
	 }

	</script>

	<body>
		<div  align="center">
		<input type="radio" name="size" value="0" onclick="setDataSize();"/> <spring:message code="systemmetrics.datasize.bytes"/>
		<input type="radio" name="size" value="1" onclick="setDataSize();"/><spring:message code="systemmetrics.datasize.kb"/>
		<input type="radio" name="size" value="2" onclick="setDataSize();"/><spring:message code="systemmetrics.datasize.mb"/>
		<input type="radio" name="size" value="3" onclick="setDataSize();"/><spring:message code="systemmetrics.datasize.gb"/>
		</div>
		<br>
		<div align="center">
		<table id="disk_usage_table" class="CSSTableGenerator">
            <thead align="center">    
                <th><spring:message code="systemmetrics.diskusage.absolutepath"/></th> 
				<th><spring:message code="systemmetrics.diskusage.usedspace"/></th>  
                <th><spring:message code="systemmetrics.diskusage.freespace"/></th> 
                <th><spring:message code="systemmetrics.diskusage.totalspace"/></th>   
            </thead>
			<tbody id="disk_usage_data">  
			</tbody>    				
		</table>
		</div>
	
	</body>
	
<%@ include file="/WEB-INF/template/footer.jsp"%>