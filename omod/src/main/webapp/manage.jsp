<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div><p>Hello ${user.systemId}!</p></div>

<div id="submitform">
    <a href="chart.form">
    <input type="button" name="chart_button" id="chart_button" style="width:100px" value="View Chart" />
    </a>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>