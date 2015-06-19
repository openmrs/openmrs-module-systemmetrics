<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/charts/smoothie.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />
    
	<script type="text/javascript">
      var dataRAM = new TimeSeries();
      
	  setInterval(function() {
		memory_total_jvm();
		memory_free_jvm();
        dataRAM.append(new Date().getTime(), (memory_total_jvm_val-memory_free_jvm_val)* 100/memory_total_jvm_val );
		document.getElementById('ram_head').innerHTML = ' : '+Math.round((memory_total_jvm_val-memory_free_jvm_val)*100/memory_total_jvm_val)+'%';
      }, 500);
      function setOSName(val){
		document.getElementById('os_name').innerHTML = val;
	  }
	  function setOSArch(val){
		document.getElementById('os_arch').innerHTML = val;
	  }
	  function setOSVersion(val){
		document.getElementById('os_ver').innerHTML = val;
	  }
	  function setVMName(val){
		document.getElementById('jvm_name').innerHTML = val;
	  }
	  function setVMVendor(val){
		document.getElementById('jvm_vendor').innerHTML = val;
	  }
	  function setVMVersion(val){
		document.getElementById('jvm_ver').innerHTML = val;
	  }
	  function setAvailProcessors(val){
		document.getElementById('processor_count').innerHTML = val;
	  }
	  function setMemFree(val){
		document.getElementById('memory_free').innerHTML = val+" bytes";
	  }
	  function setMemTotal(val){
		document.getElementById('memory_total').innerHTML = val+" bytes";
	  }
	  function setPageFree(val){
		document.getElementById('page_free').innerHTML = val+" bytes";
	  }
	  function setPageTotal(val){
		document.getElementById('page_total').innerHTML = val+" bytes";
	  }
	  function setJVMMemFree(val){
		document.getElementById('jvm_memory_free').innerHTML = val+" bytes";
	  }
	  function setJVMMemTotal(val){
		document.getElementById('jvm_memory_total').innerHTML = val+" bytes";
	  }
	  function setJVMMemMax(val){
		document.getElementById('jvm_memory_max').innerHTML = val+" bytes";
	  }
	  function resize_canvas_set(){
		resize_canvas("ram_chart","jvm_stat_info");
	  }
      function createTimeline() {
		var chartRAM = new SmoothieChart({millisPerPixel:45}),
		canvasRAM = document.getElementById('ram_chart');
		
		chartRAM.addTimeSeries(dataRAM, {lineWidth:2,strokeStyle:'#00ff00'});
		chartRAM.streamTo(canvasRAM, 500);
		indicatorCall("get_name",setOSName);
		indicatorCall("get_arch",setOSArch);
		indicatorCall("get_version",setOSVersion);
		indicatorCall("get_name_jvm",setVMName);
		indicatorCall("get_vendor_jvm",setVMVendor);
		indicatorCall("get_version_jvm",setVMVersion);
		indicatorCall("processor_avail_jvm",setAvailProcessors);
		indicatorCall("memory_free_jvm",setJVMMemFree);
		indicatorCall("memory_avail_jvm",setJVMMemMax);
		indicatorCall("memory_total_jvm",setJVMMemTotal);
		indicatorCall("memory_total",setMemTotal);
		indicatorCall("memory_total_page",setPageTotal);
		indicatorCall("memory_free",setMemFree);
		indicatorCall("memory_free_page",setPageFree);
      }
    </script>

  <body onload="createTimeline()">
   <div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='os_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('os_head','os_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.os.info"/></b>
			</font>
		</div>
		<div id='os_info'>
			<p>
			<b><spring:message code="systemmetrics.systeminfo.os.name"/> : </b><b id='os_name'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.os.arch"/> : </b><b id='os_arch'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.os.version"/> : </b><b id='os_ver'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='jvm_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('jvm_head','jvm_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.jvm.info"/></b>
			</font>
		</div>
		<div id='jvm_info'>
			<p>
			<b><spring:message code="systemmetrics.systeminfo.jvm.name"/> : </b><b id='jvm_name'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.jvm.vendor"/> : </b><b id='jvm_vendor'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.os.version"/> : </b><b id='jvm_ver'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='process_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('process_head','process_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.processor"/></b>
			</font>
		</div>
		<div id='process_info'>
			<p>
			<b><spring:message code="systemmetrics.systeminfo.processor.count"/> : </b><b id='processor_count'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='memory_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('memory_head','memory_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.memory.main"/></b>
			</font>
		</div>
		<div id='memory_info'>
			<p>
			<b><spring:message code="systemmetrics.systeminfo.memory.free"/> : </b><b id='memory_free'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.memory.total"/> : </b><b id='memory_total'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='page_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('page_head','page_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.memory.page"/></b>
			</font>
		</div>
		<div id='page_info'>
			<p>
				<b><spring:message code="systemmetrics.systeminfo.memory.free"/> : </b><b id='page_free'></b><br>
				<b><spring:message code="systemmetrics.systeminfo.memory.total"/> : </b><b id='page_total'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='jvm_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('jvm_head','jvm_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.systeminfo.memory.jvm"/></b>
			</font>
		</div>
		<div id='jvm_info'>
			<p>
			<b><spring:message code="systemmetrics.systeminfo.memory.free"/> : </b><b id='jvm_memory_free'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.memory.total"/> : </b><b id='jvm_memory_total'></b><br>
			<b><spring:message code="systemmetrics.systeminfo.memory.jvm.max"/> : </b><b id='jvm_memory_max'></b><br>
			</p>
  		</div>
	</div>
	<div class="info_head" style="border:1px solid  #009D8E;">
	 	<div style="background: #009D8E; width:'100%';border:1px solid  #009D8E;">
  			<input id='jvm_stat_head' type="button" style="background: #009D8E;" value="-" onclick="hidePanel('jvm_stat_head','jvm_stat_info');"/>
			<font color="#ffffff" size="4pt">
				<b><spring:message code="systemmetrics.memoryusage.total"/></b><b id='ram_head'></b><br>
			</font>
		</div>
		<div id='jvm_stat_info' align="center">
			<canvas id="ram_chart" height="200" width="800"></canvas><br>
  		</div>
	</div>
  </body>
	
<%@ include file="/WEB-INF/template/footer.jsp"%>