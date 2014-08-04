<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

    <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);

    function drawChart() {

    var text= "${logInValues}";   // this list contains the Date and Memory Usage data from the model
    var timeAndLoginCountArray = eval("[" + text + "]");
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
    dataTable.addColumn('number', 'User Logins');
    dataTable.addRows(timeAndLoginCountArray);

    var options = {
    title:'Per second',
    chartArea : { left :150 },
    hAxis: { title: 'Time' },
    vAxis: { title: 'User Logins' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('logins_div'));
    chart.draw(dataTable, options);
    }
    </script>
    <script type="text/javascript">

    // Drawing the used memory graph using the google chart API
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);

    function drawChart() {

    var text= "${perMinLogInValues}";   // this list contains the Date and Memory Usage data from the model
    var timeAndLoginCountArrayPermin = eval("[" + text + "]");
    var dataTable = new google.visualization.DataTable();
    dataTable.addColumn('datetime', 'Time');          // the format 'datetime' only accepts Date objects
    dataTable.addColumn('number', 'User Logins');
    dataTable.addRows(timeAndLoginCountArrayPermin);

    var options = {
    title:'Per Minutes',
    chartArea : { left :150 },
    hAxis: { title: 'Time' },
    vAxis: { title: 'User Logins' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('permin_logins_div'));
    chart.draw(dataTable, options);
    }
    </script>

    </head>

    <body>
    <div id="perSecondUserLoginsChart">
    <b class="boxHeader"> User Login Statistics (Count) against Date-Time  </b>
    <div class="box">
        <div id="logins_div" style="height:400px"></div>
        <div id="permin_logins_div" style="height:400px"></div>
    </div>
    </div>



    </body>

<%@ include file="/WEB-INF/template/footer.jsp"%>