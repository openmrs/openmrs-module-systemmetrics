/*
 * Get CPU Usage of JVM
 */
 var cpu_usage_jvm_val = 0.0;
 function cpu_usage_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "cpu_usage_jvm.form",
		data : {}, 
	    success : function(cpuUsageJVM) {  
			cpu_usage_jvm_val = cpuUsageJVM;
	    },  
	    error : function(e) {  
			cpu_usage_jvm_val = 0.0;		
	   }  
	}); 
	return cpu_usage_jvm_val;
 }
 
 /*
 * Get CPU Usage Total
 */
 var cpu_usage_val = 0.0;
 function cpu_usage(){  
	$.ajax({  
	    type : "GET",   
	    url : "cpu_usage.form",
		data : {}, 
	    success : function(cpuUsage) {  
			cpu_usage_val =  cpuUsage;
	    },  
	    error : function(e) {  
			cpu_usage_val = 0.0;	
	   }  
	}); 
	return cpu_usage_val;
 }