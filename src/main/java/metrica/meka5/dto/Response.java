package metrica.meka5.dto;

public class Response {

	private boolean ok;
	private Data data;
	
	public Response(boolean ok, Data data) {
		super();
		this.ok = ok;
		this.data = data;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}	
	
}
