<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.Traindao" %>
<%@ page import="vo.Tpath" %>
<%@ page import="vo.Path" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火车乘车路线规划系统</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String startcity=request.getParameter("startcity");
String endcity=request.getParameter("endcity");
String snum=request.getParameter("num");
String startvalue="北京";
String endvalue="广州";
if(startcity!=null) startvalue=startcity;
if(endcity!=null) endvalue=endcity;
%>
<form action="Transfer.jsp" method="post">
出发地：<input name="startcity" type="text" value=<%=startvalue %>>
目的地：<input name="endcity" type="text" value=<%=endvalue %>>
中转次数：<select name="num">
<option value="0">直达</option>
<option value="1">一次中转</option>
<option value="2">二次中转</option>
</select>
<input type="submit" value="提交"/>
</form>
<%
int num=0;
try{
	num=Integer.parseInt(snum);
	System.out.println(num);
}catch(NumberFormatException e){
	e.printStackTrace();
}
if(startcity==null||endcity==null){
	return;
}
Tpath tpath=new Tpath();
Path path=new Path();
path.setStartstation(startcity);
path.setEndStation(endcity);
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String str="9999-01-01 00:00:00";
Date d=sdf.parse(str);
path.setStarttime(d);
path.setEndtime(d);
path.setTrain("12306");
path.setTime(0, d);
tpath.setPath(path);
ArrayList<String> debcities=new ArrayList<>();
debcities.add(startcity);
ArrayList<Tpath> tps=new ArrayList<>();//终点路径集合
Traindao td=new Traindao();
td.setPath(startcity, endcity, num, tpath, debcities,tps);
%>
<table border="1">
<tr><th>始发站</th><th>出发时间</th><th>车次</th><th>到达时间</th><th>终点站</th></tr>
</table>
<%
for(Tpath tp:tps){
	ArrayList<Path> ps=new ArrayList<>();
	td.setPathArray(ps,tp,tpath);
%>
<table border="1">
<%
for(Path sp:ps){
%>
<tr>
<td><%=sp.getStartstation() %></td>
<td><%=sp.getStarttime() %></td>
<td><%=sp.getTrain() %></td>
<td><%=sp.getEndtime() %></td>
<td><%=sp.getEndStation() %></td>
</tr>
<%
}
%>
</table><br>
<%
}
%>
</body>
</html>