<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div id="tracekerTable">
    <b class="boxHeader"> Invoke Trackers : User Log-ins </b>
    <div class="box">
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

<div id="manageTable">
    <b class="boxHeader"> System Indicators : Memory Usage </b>
    <div class="box">
        <div>
            <span><b> Used Memory(MB) </b></span>
            <span>
            <a href="usedMemoryChart.form">
            <input type="button" name="chart_button" id="per_sec_chart_button" value="View Data Table & Chart" />
            </a>
            </span>
        </div>
    </div>
</div>

    <div id="userLoginsTable">
    <b class="boxHeader"> Application Indicators : User Logins </b>
    <div class="box">
    <div>
    <span><b> User Logins </b></span>
    <span>
    <a href="loggedInUsers.form">
    <input type="button" name="logins_button" id="user_login_chart_button" value="View Data Table & Chart" />
    </a>
    </span>
    </div>
    </div>
    </div>

    <div id="createdEncountersTable">
    <b class="boxHeader"> Application Indicators : Created Encounters Count </b>
    <div class="box">
    <div>
    <span><b> Created Encounters </b></span>
    <span>
    <a href="createdEncounters.form">
    <input type="button" name="enc_button" id="enc__button" value="View Data Table & Chart" />
    </a>
    </span>
    </div>
    </div>
    </div>

<script>
    function disableButton(){
    <!-- Todo: Add a method to disable the button when its clicked once-->
    }
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>