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
				$("#li1").text(msg.msg);
				$("#li2").attr("href","#");
				$("#li2").attr("onclick","return false");
				$("#li2").text("退出");
				logoutbind();
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
		url:"autologin",
		dataType:"json",
		success:function(msg){
			if(msg.logined){
				var link=window.location.url;
				$("#li1").attr("href","#");
				$("#li1").text(msg.msg);
				$("#li2").attr("href","");
				$("#li2").attr("onclick","return false");
				$("#li2").text("退出");
				logoutbind();
			}else{
				$("#li1").attr("href","login.html");
				$("#li1").text("登录");
				$("#li2").attr("href","register.html");
				$("#li2").text("注册");
			}
		}
	});
}

function logoutbind(){
	$("#li2").click(function(){
		$.cookie("username","",{expires:-1});
		$.cookie("password","",{expires:-1});
		$.ajax({
			type:"post",
			url:"account/logout",
			success:function(){
				location.reload(true);
			}
		})
	});
}