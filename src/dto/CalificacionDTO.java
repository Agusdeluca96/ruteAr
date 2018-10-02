package dto;

public class CalificacionDTO {
	private Long id;
	private Integer valor;
	private UsuarioDTO usuario;

	public CalificacionDTO() {
	}

	public CalificacionDTO(Long id) {
		super();
		this.id = id;
	}

	public CalificacionDTO(Long id, Integer valor, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValor() {
		return valor;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}