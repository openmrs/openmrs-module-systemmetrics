<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

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
					<span><b> Used Memory(MB) </b></span>
					<span>
						<a href="usedMemoryChart.form">
						<input type="button" name="chart_button" id="per_sec_chart_button" value="View Data Table & Chart" />
						</a>
					</span>
				</div>
            </div>
            <div id="view2">
				<div>
					<span><b> User Logins </b></span>
					<span>
						<a href="loggedInUsers.form">
						<input type="button" name="logins_button" id="user_login_chart_button" value="View Data Table & Chart" />
						</a>
					</span>
				</div>
				<div>
					<span><b> Created Encounters </b></span>
					<span>
						<a href="createdEncounters.form">
						<input type="button" name="enc_button" id="enc__button" value="View Data Table & Chart" />
						</a>
					</span>
				</div>
            </div>
            <div id="view3">
				<div>
					<span><b> Enable User Login </b></span>
					<span>
						<a href="enable_tracking.form">
						<input type="button" name="track_button" id="track_button" value="Yes" onclick="disableButton"/>
						</a>
					</span>
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