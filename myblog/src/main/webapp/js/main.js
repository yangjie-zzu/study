/**
 * 
 */
function iecheck(){
	var ua=navigator.userAgent.toLowerCase();
	var isIE=ua.indexOf("msie")>-1;
}
function logined(){
	$.ajax({
		type:"post",
		url:"logined",
		dataType:"json",
		success:function(msg){
			if(msg.logined){
				$("#li1").attr("href","#");
				$("#li1").html(msg.msg);
				$("#li2").attr("href","#");
				$("#li2").html("退出");
			}else{
				cookielogin();
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

function cookielogin(){
	$.ajax({
		type:"post",
		url:"login",
		dataType:"json",
		success:function(msg){
			if(msg.logined){
				$("#li1").attr("href","#");
				$("#li1").html(msg.msg);
				$("#li2").attr("href","#");
				$("#li2").html("退出");
			}else{
				$("#li1").attr("href","login.html");
				$("#li1").html("登录");
				$("#li2").attr("href","register.html");
				$("#li2").html("注册");
			}
		}
	});
}