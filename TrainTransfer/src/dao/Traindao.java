package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import vo.Tpath;
import vo.Path;
import vo.Stationtime;
import vo.Traintime;


public class Traindao {
	//获取数据库连接
	public Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/12306?user=root&characterEncoding=utf-8");
		return conn;
	}
	//关闭数据库连接
	public void closeConnection(Statement stat,Connection conn) throws Exception{
		stat.close();
		conn.close();
	}

	public void closeConnection(PreparedStatement ps,Connection conn) throws Exception{
		ps.close();
		conn.close();
	}
	//通过城市获取车站
	public ArrayList<String> getStationByCity(String city) throws Exception{
		ArrayList<String> stas=new ArrayList<>();
		Connection conn=getConnection();
		String sql="select staname from station where city=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, city);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			stas.add(rs.getString(1));
		}
		closeConnection(ps,conn);
		return stas;
	}
	//通过车站获取列车
	public ArrayList<Traintime> getTrainByStation(String station) throws Exception{
		ArrayList<Traintime> trains=new ArrayList<Traintime>();
		Connection conn=getConnection();
		String sql="select train,outtime,num from traintime where station=? and outtime is not null";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, station);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Traintime tt=new Traintime();
			tt.setTrain(rs.getString(1));
			tt.setOuttime(rs.getTimestamp(2));
			tt.setNum(rs.getInt(3));
			trains.add(tt);
		}
		closeConnection(ps,conn);
		return trains;
	}
	//查询列车是否经过终点站并返回日期
	public Date getTimeByStation(String train,String station) throws Exception{
		Date st=null;
		Connection conn=getConnection();
		String sql="select intotime from traintime where train=? and station=? and intotime is not null";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, train);
		ps.setString(2, station);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			st=rs.getTimestamp(1);
		}
		closeConnection(ps,conn);
		//if(st!=null){
			//System.out.println(st.toString());
		//}
		return st;
	}
	//通过列车获取站点
	public ArrayList<Stationtime> getStationByTrain(int num,String train) throws Exception{
		ArrayList<Stationtime> stats=new ArrayList<>();
		Connection conn=getConnection();
		String sql="select station,intotime from traintime where train=? and intotime is not null and num>?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, train);
		ps.setInt(2, num);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Stationtime stat=new Stationtime();
			stat.setStation(rs.getString(1));
			stat.setIntotime(rs.getTimestamp(2));
			stats.add(stat);
		}
		closeConnection(ps,conn);
		return stats;
	}
	//获取城市
	public String getCityByStation(String station) throws Exception{
		String city=null;
		Connection conn=getConnection();
		String sql="select city from station where staname=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, station);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			city=rs.getString(1);
		}
		closeConnection(ps,conn);
		return city;
	}
	//建立路径函数
	public void setPath(String startcity,String endcity,int num,Tpath fatherpath,ArrayList<String> debcities,ArrayList<Tpath> tp) throws Exception{
		if(num==0&startcity.equals(endcity)){
			return;
		}
		if(num>0&debcities.contains(endcity)){
			return;
		}
		ArrayList<String> startstats=getStationByCity(startcity);
		ArrayList<String> endstats=getStationByCity(endcity);
		if(num==0){
			for(String startstat:startstats){
				ArrayList<Traintime> trains=getTrainByStation(startstat);
				for(Traintime train:trains){
					if(!train.getTrain().equals(fatherpath.getPath().getTrain())){
						System.out.print(train.getTrain()+" ");
						System.out.println(fatherpath.getPath().getTrain());
						for(String endstat:endstats){
							Date endtime=getTimeByStation(train.getTrain(),endstat);
							if(endtime!=null){
								Tpath tpath=new Tpath();
								Path path=new Path();
								path.setStartstation(startstat);
								path.setEndStation(endstat);
								path.setTrain(train.getTrain());
								path.setStarttime(train.getOuttime());
								path.setEndtime(endtime);
								path.setTime(fatherpath.getPath().getTime(),fatherpath.getPath().getEndtime());
								tpath.setPath(path);
								tpath.setFp(fatherpath);
								fatherpath.addNext(tpath);
								tp.add(tpath);
							}
						}
					}
				}
			}
		}else{
			for(String startstat:startstats){
				ArrayList<Traintime> trains=getTrainByStation(startstat);
				for(Traintime train:trains){
					System.out.print(train.getTrain()+" ");
					System.out.println(fatherpath.getPath().getTrain());
					if(!train.getTrain().equals(fatherpath.getPath().getTrain())){
						ArrayList<Stationtime> endtempstats=getStationByTrain(train.getNum(),train.getTrain());
						for(Stationtime endstat:endtempstats){
							Tpath temptpath=new Tpath();
							Path temppath=new Path();
							temppath.setStartstation(startstat);
							temppath.setEndStation(endstat.getStation());
							temppath.setTrain(train.getTrain());
							temppath.setStarttime(train.getOuttime());
							temppath.setEndtime(endstat.getIntotime());
							temppath.setTime(fatherpath.getPath().getTime(), fatherpath.getPath().getEndtime());
							temptpath.setPath(temppath);
							debcities.add(getCityByStation(endstat.getStation()));
							setPath(getCityByStation(endstat.getStation()),endcity,num-1,temptpath,debcities,tp);
							if(temptpath.getNext()!=null) {
								temptpath.setFp(fatherpath);
								fatherpath.addNext(temptpath);
							}
							debcities.remove(debcities.size()-1);
						}
					}
				}
			}
		}
	}
	//建立路径数组，便于显示
	public void setPathArray(ArrayList<Path> ps,Tpath tp,Tpath fatherpath){
		if(!tp.getFp().equals(fatherpath))
			setPathArray(ps,tp.getFp(),fatherpath);
		ps.add(tp.getPath());
	}

}
