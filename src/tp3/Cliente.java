package tp3;

public class Cliente {
	private int IDCliente;
	private String nombre;
	private String apellido;
	private String direccion;
	private int saldoEnCuenta;
	private int totalGastado;
	
	public Cliente(int IDCliente, String nombre, String apellido, String direccion, int saldoEnCuenta) {
		setIDCliente(IDCliente);
		setNombre(nombre);
		setApellido(apellido);
		setDireccion(direccion);
		setSaldoEnCuenta(saldoEnCuenta);
	}

	public void setIDCliente(int IDCliente) {
		this.IDCliente = IDCliente;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setSaldoEnCuenta(int saldoEnCuenta) {
		this.saldoEnCuenta = saldoEnCuenta;
	}
	
	public int getSaldoEnCuenta() {
		return saldoEnCuenta;
	}
	
	public String toString() {
		return "[id: "+IDCliente+", saldo: "+saldoEnCuenta+", nombre: "+nombre
				+", apellido: "+apellido+", direccion: "+direccion+"]";
	}

	public void setTotalGastado(int totalGastado) {
		this.totalGastado = totalGastado;
	}
	
	public int getTotalGastado() {
		return totalGastado;
	}
	
}
