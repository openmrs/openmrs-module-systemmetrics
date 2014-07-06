<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

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

<div>
</div>



<%@ include file="/WEB-INF/template/footer.jsp"%>