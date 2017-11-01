/**
 * 
 */

function logined(){
	$.ajax({
		type:"get",
		url:"logined",
		dataType:"json",
		success:function(msg){
			if(msg.logined){
				$("#li1").attr("href","#");
				$("#li1").empty();
				$("#li1").html(msg.name);
			}else if(!msg.logined){
				$("#li1").attr("href","login.html");
				$("#li1").empty();
				$("#li1").html("登录");
			}
		},
		error:function(XMLHttpRequest,textStatus,msg){
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			alert(msg);
		}
	});
}