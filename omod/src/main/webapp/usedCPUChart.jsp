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
      
	  setInterval(function() {
        cpuData.append(new Date().getTime(), cpu_usage() * 100);
		document.getElementById('cpu_head').innerHTML = 'CPU Usage : '+Math.round(cpu_usage_val*100)+'%';
		jvmData.append(new Date().getTime(), cpu_usage_jvm());
		document.getElementById('jvm_head').innerHTML = 'CPU Usage JVM: '+Math.round(cpu_usage_jvm_val)+'%';
      }, 500);
      
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
	<b id='cpu_head'>CPU Usage</b><br>
    <canvas id="cpu_chart" width="720" height="200"></canvas><br>
	<b id='jvm_head'>CPU Usage JVM</b><br>
	<canvas id="cpu_chart_jvm" width="720" height="200"></canvas>

  </body>
	
<%@ include file="/WEB-INF/template/footer.jsp"%>