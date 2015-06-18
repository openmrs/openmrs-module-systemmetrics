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
 /*
 * Get Disk Usage
 */
 var disk_usage_val = null;
 function disk_usage(){  
	$.ajax({  
	    type : "GET",   
	    url : "disk_usage.form",
		data : {}, 
	    success : function(diskUsage) {  
			disk_usage_val = diskUsage;
	    },  
	    error : function(e) {  
			disk_usage_val = null;	
	   }  
	}); 
 }
 /*
 * Get Processor Available to JVM
 */
 var processor_avail_val = 0;
 function processor_avail_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "processor_avail_jvm.form",
		data : {}, 
	    success : function(processorAvailJVM) {  
			processor_avail_val = processorAvailJVM;
	    },  
	    error : function(e) {  
			processor_avail_val = 0;		
	   }  
	}); 
	return processor_avail_val;
 }
 
 /*
 * Get Memory Free of JVM
 */
 var memory_free_jvm_val = 0.0;
 function memory_free_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_free_jvm.form",
		data : {}, 
	    success : function(memoryFreeJVM) {  
			memory_free_jvm_val =  memoryFreeJVM;
	    },  
	    error : function(e) {  
			memory_free_jvm_val = 0.0;	
	   }  
	}); 
	return memory_free_jvm_val;
 }
 /*
 * Get Memory Available to JVM
 */
 var memory_avail_jvm_val = 0.0;
 function memory_avail_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_avail_jvm.form",
		data : {}, 
	    success : function(memoryAvailJVM) {  
			memory_avail_jvm_val =  memoryAvailJVM;
	    },  
	    error : function(e) {  
			memory_avail_jvm_val = 0.0;	
	   }  
	}); 
	return memory_avail_jvm_val;
 }
 /*
 * Get Memory Total to JVM
 */
 var memory_total_jvm_val = 0.0;
 function memory_total_jvm(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_total_jvm.form",
		data : {}, 
	    success : function(memoryTotalJVM) {  
			memory_total_jvm_val =  memoryTotalJVM;
	    },  
	    error : function(e) {  
			memory_total_jvm_val = 0.0;	
	   }  
	}); 
	return memory_total_jvm_val;
 }
 /*
 * Get Memory Total
 */
 var memory_total_val = 0.0;
 function memory_total(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_total.form",
		data : {}, 
	    success : function(memoryTotal) {  
			memory_total_val =  memoryTotal;
	    },  
	    error : function(e) {  
			memory_total_val = 0.0;	
	   }  
	}); 
	return memory_total_val;
 }
 /*
 * Get Page Memory Total
 */
 var memory_total_page_val = 0.0;
 function memory_total_page(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_total_page.form",
		data : {}, 
	    success : function(memoryTotalPage) {  
			memory_total_page_val =  memoryTotalPage;
	    },  
	    error : function(e) {  
			memory_total_page_val = 0.0;	
	   }  
	}); 
	return memory_total_page_val;
 }
 /*
 * Get Memory Free
 */
 var memory_free_val = 0.0;
 function memory_free(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_free.form",
		data : {}, 
	    success : function(memoryFree) {  
			memory_free_val =  memoryFree;
	    },  
	    error : function(e) {  
			memory_free_val = 0.0;	
	   }  
	}); 
	return memory_free_val;
 }
 /*
 * Get Page Memory Free
 */
 var memory_free_page_val = 0.0;
 function memory_free_page(){  
	$.ajax({  
	    type : "GET",   
	    url : "memory_free_page.form",
		data : {}, 
	    success : function(memoryFreePage) {  
			memory_free_page_val =  memoryFreePage;
	    },  
	    error : function(e) {  
			memory_free_page_val = 0.0;	
	   }  
	}); 
	return memory_free_page_val;
 }
 /*
 * Generalize Value Retrieval
 */
 function indicatorCall(indicatorId, indicatorCallBack){  
	$.ajax({  
	    type : "GET",   
	    url : indicatorId+".form",
		data : {}, 
	    success : function(val) {  
			indicatorCallBack(val);
	    },  
	    error : function(e) {  
			console.error("Communication Error");	
	   }  
	}); 
}