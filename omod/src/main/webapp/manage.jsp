<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<!-- Tell 1.7+ versions of core to not include JQuery themselves. Also, on 1.7+ we may get different jquery and jquery-ui versions than 1.3.2 and 1.7.2 -->
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true"/>

<openmrs:htmlInclude file="/moduleResources/systemmetrics/css/styles.css"/>
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/lib/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/methods.js" />

<openmrs:htmlInclude file="/moduleResources/systemmetrics/js/tabcontent.js" />
<openmrs:htmlInclude file="/moduleResources/systemmetrics/css/tabcontent.css" />

    <div style="width: 80%; margin: 0 auto; padding: 20px 0 40px;">
        <ul class="tabs" data-persist="true">
            <li><a href="#view1">System Indicators</a></li>
            <li><a href="#view2">Application Indicators</a></li>
            <li><a href="#view3">Invoke Trackers</a></li>
        </ul>
        <div class="tabcontents">
            <div id="view1">
				<div>
					<span>
						<a href="usedMemoryChart.form">
						<input type="button" name="chart_button" id="per_sec_chart_button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Used Memory(MB) </b></span>
					<br>
					<span>
						<a href="usedCPUChart.form">
						<input type="button" name="chart_button" id="per_sec_chart_button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Used CPU </b></span>
					<br>
					<span>
						<a href="usedDiskChart.form">
						<input type="button" name="chart_button" id="per_sec_chart_button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Used Disk </b></span>
					<br>
					<span>
						<a href="systemInfo.form">
						<input type="button" name="chart_button" id="per_sec_chart_button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> System Information </b></span>
				</div>
            </div>
            <div id="view2">
				<div>
					<span>
						<a href="loggedInUsers.form">
						<input type="button" name="logins_button" id="user_login_chart_button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> User Logins </b></span>
				</div>
				<div>
					<span>
						<a href="createdEncounters.form">
						<input type="button" name="enc_button" id="enc__button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Created Encounters </b></span>
				</div>
				<div>
					<span>
						<a href="createdPatients.form">
						<input type="button" name="enc_button" id="enc__button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Created Patients </b></span>
				</div>
				<div>
					<span>
						<a href="createdForms.form">
						<input type="button" name="enc_button" id="enc__button" value="View" class="view_indicator"/>
						</a>
					</span>
					<span><b> Created Forms </b></span>
				</div>				
            </div>
            <div id="view3">
				<div>
					<span>
						<a href="enable_tracking.form">
						<input type="button" name="track_button" id="track_button" value="Yes" onclick="disableButton" class="view_indicator"/>
						</a>
					</span>
					<span><b> Enable User Login </b></span>
				</div>
            </div>
        </div>
    </div>
<script>
    function disableButton(){
    <!-- Todo: Add a method to disable the button when its clicked once-->
    }
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>