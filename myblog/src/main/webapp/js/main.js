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
		url:"http://localhost:8080/myblog/logined",
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
		url:"http://localhost:8080/myblog/autologin",
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
			url:"http://localhost:8080/myblog/account/logout",
			success:function(){
				location.reload(true);
			}
		})
	});
}

function getUrlValueByKey(name){
	var reg=new RegExp("[?&]"+name+"=([^&]*)");
	var match=reg.exec(window.location.search);
	if(match!=null){
		return match[1];
	}else{
		return null;
	}
}

function loadArticleList(){
	var order=getUrlValueByKey("order");
	if(order==null){
		order="hit";
	}
	var publisherId=getUrlValueByKey("publisherId");
	var type=getUrlValueByKey("type");
	var pageNum=getUrlValueByKey("page");
	if(pageNum==null){
		pageNum=1;
	}
	var showNum=5;
	$.ajax({
		type:"post",
		url:"http://localhost:8080/myblog/showArticles",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify({
			"publisherId":publisherId,
			"typeName":type,
			"order":order,
			"pageNum":pageNum,
			"showNum":showNum
		}),
		dataType:"json",
		success:function(page){
			var articles=page.articles;
			if(articles==null){
				$("#artList").html("无数据");
			}else{
				for(i=0;i<articles.length;i++){
					article=$("<a href='http://localhost:8080/myblog/article/"+articles[i].id+"'><div style='margin:10px auto;'>"+articles[i].title+"<div>"+articles[i].content+"</div><span>"+articles[i].publisher.userName+"发表于"+articles[i].publishtime+"，"+articles[i].hit+"阅读。</span></div></a>");
					$("#artList").append(article);
				}
				var intPageNum=Number(pageNum);
				if(intPageNum!=1){
					$("#first").attr("href",setPage(1));
					$("#before").attr("href",setPage(intPageNum-1));
				}
				if(!page.lastPage){
					$("#last").attr("href",setPage(999));
					$("#next").attr("href",setPage(intPageNum+1));
				}
			}
		}
	});
}

function setPage(pageNumber){
	var url=window.location.href;
	var search=window.location.search;
	var pageRegExp=/page=[^&]*/;
	var newSearch="";
	var newUrl=url;
	if(search==null||search==""){
		newSearch+="?";
		newSearch+="page="+pageNumber;
		newUrl+=newSearch;
	}else if(pageRegExp.test(search)){
		newSearch+=search.replace(pageRegExp,"page="+pageNumber);
		newUrl=url.replace(search,newSearch);
	}else{
		newSearch+="&page="+pageNumber;
		newUrl+=newSearch;
	}
	return newUrl;
}

function skipPage(){
	var num=$("#pageNum").val();
	var sRegExp=/^[0-9]+$/;
	if(sRegExp.exec(num)){
		if(Number(num)==0){
			alert("不可输入0！")
		}else{
			$("#skip").attr("href",setPage(num));
		}
	}else{
		alert("请输入数字！");
	}
}
