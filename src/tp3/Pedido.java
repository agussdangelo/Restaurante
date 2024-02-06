package tp3;

public class Pedido {
	private int numeroPedido;
	private int IDCliente;
	private int IDMenu;
	private int importe;

	public Pedido(int numeroPedido, int IDCliente, int IDMenu, int importe) {
		setNumeroPedido(numeroPedido);
		setIDCliente(IDCliente);
		setIDMenu(IDMenu);
		setImporte(importe);
	}
	
	public int getNumeroPedido() {
		return numeroPedido;
	}
	
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}
	
	public int getIDMenu() {
		return IDMenu;
	}
	
	public void setIDMenu(int iDMenu) {
		IDMenu = iDMenu;
	}
	
	public int getImporte() {
		return importe;
	}
	
	public void setImporte(int importe) {
		this.importe = importe;
	}
	
	public String toString() {
		return "[numero de pedido: "+numeroPedido+", id cliente: "+IDCliente+ ", id menu: "+IDMenu+", valor: "+importe+"]";
	}
}
