/*
 * Hide/Show Panel
 */
function hidePanel(mainDiv, toggleDiv){
	if($( "#"+mainDiv).val()=="+"){
		$( "#"+mainDiv).val("-");
		$( "#"+toggleDiv ).show();
	}
	else{
		$( "#"+mainDiv).val("+");
		$( "#"+toggleDiv ).hide();
	}
}
/*
 * Resizing Canvas
 */
function resize_canvas(id,match){
    var canvas = document.getElementById(id), 
		match = window.getComputedStyle(document.getElementById(match));
    if (canvas.width  != match.width)
    {
       canvas.width = match.width;
    }
	if (canvas.height  != 200)
    {
        canvas.height  = 200;
    }
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