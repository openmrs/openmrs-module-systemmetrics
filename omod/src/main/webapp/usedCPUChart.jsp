<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/charts/smoothie.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />
    
	<script type="text/javascript">
      var cpuData = new TimeSeries(),
		  jvmData = new TimeSeries();
      
	  var cpu_usage_val,cpu_usage_jvm_val;
	  function getCPU(val){
		cpu_usage_val = val;
	  }
	  function getCPUJVM(val){
		cpu_usage_jvm_val= val;
	  }
	  function updateCPUChart() {
        cpuData.append(new Date().getTime(), cpu_usage_val * 100);
		document.getElementById('cpu_head').innerHTML = ' : '+Math.round(cpu_usage_val*100)+'%';
		jvmData.append(new Date().getTime(), cpu_usage_jvm_val);
		document.getElementById('jvm_head').innerHTML = ' : '+Math.round(cpu_usage_jvm_val)+'%';
      }
	  setInterval(function(){
		indicatorCall("cpu_usage",getCPU);
		indicatorCall("cpu_usage_jvm",getCPUJVM);	
		updateCPUChart();
	  }, 1000);
	  setInterval(function() {

      }, 500);
      function resize_canvas_set(){
		resize_canvas("cpu_chart_jvm","cpu_stat_info");
		resize_canvas("cpu_chart","cpu_jvm_stat_info");
	  }
      function createTimeline() {
		var chartCPU = new SmoothieChart({millisPerPixel:45}),
		chartJVM = new SmoothieChart({millisPerPixel:45}),
		canvasCPU = document.getElementById('cpu_chart'),
		canvasJVM = document.getElementById('cpu_chart_jvm');
		
		chartCPU.addTimeSeries(cpuData, {lineWidth:2,strokeStyle:'#00ff00'});
		chartCPU.streamTo(canvasCPU, 500);
		chartJVM.addTimeSeries(jvmData, {lineWidth:2,strokeStyle:'#00ff00'});
		chartJVM.streamTo(canvasJVM, 500);
      }
    </script>

  <body onload="createTimeline()">
  	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='cpu_stat_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('cpu_stat_head','cpu_stat_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.cpuusage.total"/></b><b id='cpu_head'></b><br>
			</font>
		</div>
		<div id='cpu_stat_info' align="center">
			<canvas id="cpu_chart" height="200" width="800"></canvas><br>
  		</div>
	</div>
  	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='cpu_jvm_stat_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('cpu_jvm_stat_head','cpu_jvm_stat_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.cpuusage.jvm"/></b><b id='jvm_head'></b><br>
			</font>
		</div>
		<div id='cpu_jvm_stat_info'  align="center">
			<canvas id="cpu_chart_jvm" height="200" width="800"></canvas>
  		</div>
	</div>
  </body>
	
<%@ include file="/WEB-INF/template/footer.jsp"%>