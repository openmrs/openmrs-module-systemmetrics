/*
 * Get CPU Usage of JVM
 */
 function cpu_usage_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "cpu_usage_jvm.form",
		data : {}, 
	    success : function(cpu_usage) {  
			alert(cpu_usage);
	    },  
	    error : function(e) {  
	    alert('Error: ' + e);   
	   }  
	}); 
 }
 
 /*
 * Get CPU Usage Total
 */
 function cpu_usage(){  
	$.ajax({  
	    type : "GET",   
	    url : "cpu_usage.form",
		data : {}, 
	    success : function(cpu_usage) {  
			alert(cpu_usage);
	    },  
	    error : function(e) {  
	    alert('Error: ' + e);   
	   }  
	}); 
 }