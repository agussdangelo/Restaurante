package tp3;

public class Menu {
	private int IDMenu;
	private String platoPrincipal;
	private String guarnicion;
	private String bebida;
	private String postre;
	private int valor;

	public Menu(int IDMenu, String platoPrincipal, String guarnicion, String bebida, String postre, int valor) {
		setIDMenu(IDMenu);
		setPlatoPrincipal(platoPrincipal);
		setGuarnicion(guarnicion);
		setBebida(bebida);
		setPostre(postre);
		setValor(valor);
	}

	public int getIDMenu() {
		return IDMenu;
	}

	public void setIDMenu(int iDMenu) {
		IDMenu = iDMenu;
	}

	public String getPlatoPrincipal() {
		return platoPrincipal;
	}

	public void setPlatoPrincipal(String platoPrincipal) {
		this.platoPrincipal = platoPrincipal;
	}
	
	public String getGuarnicion() {
		return guarnicion;
	}

	public void setGuarnicion(String guarnicion) {
		this.guarnicion = guarnicion;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	public String getPostre() {
		return postre;
	}

	public void setPostre(String postre) {
		this.postre = postre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String toString() {
		return "[id: "+IDMenu+", valor: "+valor+", plato principal: "+platoPrincipal
				+", guarnicion: "+guarnicion+", bebida: "+bebida+", postre: "+postre+"]"; 
	}
}
