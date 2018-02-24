<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!-- 核心标签库 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" href="/myblog/css/style.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="/myblog/js/main.js"></script>
<script src="/myblog/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="http://localhost:8080/myblog/UE123ditor/ueditor.config.js"></script>
<script type="text/javascript" src="http://localhost:8080/myblog/UE123ditor/ueditor.all.js"></script>
<title>${article.title }</title>
<script type="text/javascript">
$(function(){
	logined();
	$("#commentOn button").click(function(){
		if(ue.hasContents()){
			var c_content=ue.getContent();
		}else{
			alert("内容不能为空！");
			return;
		}
		var artId=${article.id};
		$.ajax({
			url:"http://localhost:8080/myblog/comment",
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				content:c_content,
				article:{
					id:artId
				},
			}),
			dataType:"json",
			success:function(msg){
				if(msg.stat=="success"){
					if(window.confirm("评论成功，是否刷新页面？")){
						location.reload();
					}
				}else if(msg.stat=="fail"){
					alert("发表评论失败，请重试！");
				}else if(msg.stat=="empty"){
					alert("内容不能为空");
				}else if(msg.stat=="logout"){
					if(window.confirm("您为登录，是否登录？")){
						window.open("http://localhost:8080/myblog/login.html");
					}
				}
			}
			
		})
	})
});
function reply(floor){
	ue.setContent("回复"+floor+"楼：");
}
</script>
<style>
#article
{
	margin-left:10%;
	width:50%;
	float:left;
}
#blogger
{
	width:20%;
	float:left;
}
#commentOn
{
}
</style>
</head>
<body>
	<div class="tool">
		<ul style="float:left">
			<li><a href="http://localhost:8080/myblog">主页</a></li>
			<li><a href="#about">关于</a></li>
		</ul>
		<ul style="float:right">
			<li><a href="http://localhost:8080/myblog/write.html">发表博客</a></li>
			<li><a id="li1" href="http://localhost:8080/myblog/login.html">登录</a></li>
			<li><a id="li2" href="http://localhost:8080/myblog/register.html">注册</a></li>
		</ul>
	</div>
	<div id="article">
		<div id="title">
			<h1>${article.title }</h1>
			<span><fmt:formatDate type="both" value="${article.publishtime }" pattern="yyyy-MM-dd HH:mm:ss"/> 分类：${article.type.name }， ${article.hit }阅读</span>
		</div>
		<div id="content">
			${article.content}
		</div>
		<div id="commentOn">
		发表评论：
			<script type="text/plain" id="container"></script>
			<script type="text/javascript">
				var ue=UE.getEditor("container",{toolbars:[
					['fullscreen','source','undo','redo','bold','insertcode','emotion','spechars']
				]});
			</script>
			<button>发表评论</button>
		</div>
		<div id="comments">
			评论：
			<c:forEach items="${article.comments }" var="comment" varStatus="status">
				<div class="comment">
					<div class="c_user" style="width:15%;float:left;text-align:center">
						${comment.author.userName }
					</div>
					<div class="c_detailed" style="width:85%;float:left">
						<div class="c_publishtime">
							${status.count }楼：<fmt:formatDate type="both" value="${comment.publishtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
						<div class="c_contents">
							${comment.content }
						</div>
						<div class="reply" style="float:right"><button onclick="reply(${status.count })">回复</button></div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="blogger">
		<div id="headpic"></div>
		<div id="userName">${article.publisher.userName }</div>
	</div>
</body>
</html>