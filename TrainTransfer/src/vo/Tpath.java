package vo;

import java.util.ArrayList;

public class Tpath {
	Path path;
	Tpath fp;
	ArrayList<Tpath> next;
	public Tpath(){
		next=new ArrayList<>();
	}
	public Tpath getFp() {
		return fp;
	}
	public void setFp(Tpath fp) {
		this.fp = fp;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public ArrayList<Tpath> getNext() {
		return next;
	}
	public void setNext(ArrayList<Tpath> next) {
		this.next = next;
	}
	public void addNext(Tpath path){
		this.next.add(path);
	}

}
