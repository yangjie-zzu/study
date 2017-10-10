package vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Path {
	String startstation;
	String endStation;
	String train;
	Date starttime;
	Date endtime;
	long time;
	public String getStartstation() {
		return startstation;
	}
	public void setStartstation(String startstation) {
		this.startstation = startstation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long fathertime,Date fatherendtime) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str="9999-01-01 00:00:00";
		Date d=sdf.parse(str);
		if(endtime==d){
			this.time = endtime.getTime()-starttime.getTime();
		}else{
			this.time = fathertime+(starttime.getTime()-fatherendtime.getTime())+(endtime.getTime()-starttime.getTime());
		}
	}

}
