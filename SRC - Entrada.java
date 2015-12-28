public class Entrada {

	private String titulo;
	private String descricao;
	
	public Entrada(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
