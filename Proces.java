public class Proces implements Comparable<Proces>{
	int t; // time - czas pozostały do ukończenia
	int p; // power - 1~100% użycia procesora

	Proces(int len,int pow){ // dł,moc
		t=len;
		p=pow;
	}

	@Override
	public String toString(){
		return "Proces [t="+t+", p="+p+"]";
	}

	public int compareTo(Proces p){
		return p!=null?((Integer)t).compareTo(p.t):1;
	}

	public boolean equals(Object o){
		return o instanceof Proces ? p==((Proces)o).p&&t==((Proces)o).t : false;
	}
}