package vo;

public class CorreoVo {

	
	private String para = "jalexanderg8@gmail.com";
	private String asunto, cuerpo;

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {
		return "CorreoVo [para=" + para + ", asunto=" + asunto + ", cuerpo=" + cuerpo + "]";
	}
}
