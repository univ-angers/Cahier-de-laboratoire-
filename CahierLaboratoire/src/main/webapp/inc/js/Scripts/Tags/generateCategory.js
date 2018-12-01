/**
 * 
 */


function generateCategory(categoryName){
		
	$("#categorylist").append(
			'<div class="card text-white bg-info mb-3 " style="margin: 2%; width : 100%">'+
	
			'<div class="card-header">'+
				'<p class="my-0 font-weight-normal ">'+categoryName+'</p>'+
				
				
			'</div>'+
	
			'<div id="tagsList'+categoryName+'" class="card-body text-center font-weight-normal" style="font-size: 20px;">'+
			
			'</div>'+
	
		'</div>'
	);

}