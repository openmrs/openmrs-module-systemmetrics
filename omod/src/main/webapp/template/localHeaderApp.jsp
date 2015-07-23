<spring:htmlEscape defaultHtmlEscape="true"/>

<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/manage.form"><spring:message
				code="systemmetrics.manage" /></a>
	</li>
	<li
		<c:if test='<%= request.getRequestURI().contains("/createdEncounters") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdEncounters.form">Created Encounters</a>
	</li>	
	<li
		<c:if test='<%= request.getRequestURI().contains("/createdPatients") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdPatients.form">Created Patients</a>
	</li>	
	<li
		<c:if test='<%= request.getRequestURI().contains("/createdForms") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdForms.form">Created Forms</a>
	</li>	
	<li
		<c:if test='<%= request.getRequestURI().contains("/createdVisits") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdVisits.form">Created Visits</a>
	</li>	
		<li
		<c:if test='<%= request.getRequestURI().contains("/createdConcepts") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdConcepts.form">Created Concepts</a>
	</li>	
	<li
		<c:if test='<%= request.getRequestURI().contains("/createdObservations") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/createdObservations.form">Created Observations</a>
	</li>	
	<li
		<c:if test='<%= request.getRequestURI().contains("/ranReports") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/systemmetrics/ranReports.form">Report Runs</a>
	</li>	
	<!-- Add further links here -->
</ul>
<h2>
    System Performance and Utilization Module
</h2>
