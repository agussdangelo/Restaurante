package tp3;

import java.util.Scanner;

public class Main {
	
	static Scanner entrada = new Scanner(System.in);
	static Cliente[] clientes;
	static Menu[] menus;
	static Pedido[] pedidos; 
	public static void main(String[] args) {
		
		boolean salirDelPrograma = false;
		int IDCliente = 0, IDMenu = 0, numeroPedido = 0;
		System.out.println("\n## Bienvenido al restaurant ##");
		int nClientes = cantidadDeClientes();
		clientes = new Cliente[nClientes];
		int nMenus = cantidadDeMenus();
		menus = new Menu[nMenus];
		int nPedidos = cantidadDePedidos();
		pedidos = new Pedido[nPedidos];
		
		do {
			int opcion = seleccionarOpcion();
			switch(opcion) {
			case 1:
				if(clientes[nClientes-1] == null) {
					System.out.println("Registre al cliente ["+(IDCliente+1)+"]");
					entrada.nextLine();
					System.out.print("\nIngrese el nombre: ");
					String nombre = entrada.nextLine();
					System.out.print("\nIngrese el apellido: ");
					String apellido = entrada.nextLine();
					System.out.print("\nIngrese la direccion: ");
					String direccion = entrada.nextLine();
					int saldoEnCuenta = verificarSaldoInicial();
					Cliente cliente = new Cliente(IDCliente, nombre, apellido, direccion, saldoEnCuenta);
					clientes[IDCliente] = cliente;
					IDCliente++;
					break;
				}
				System.out.println("No se puede agregar mas clientes");
				break;

			case 2:
				if(menus[nMenus-1] == null) {
					System.out.println("\nRegistre el menu ["+(IDMenu+1)+"]");
					entrada.nextLine();
					System.out.print("\nIngrese el plato principal: ");
					String platoPrincipal = entrada.nextLine();
					System.out.print("\nIngrese la guarnición: ");
					String guarnicion = entrada.nextLine();
					System.out.print("\nIngrese la bebida: ");
					String bebida = entrada.nextLine();
					System.out.print("\nIngrese el postre: ");
					String postre = entrada.nextLine();
					int valor = verificarValor();
					Menu menu = new Menu(IDMenu, platoPrincipal, guarnicion, bebida, postre, valor);
					menus[IDMenu] = menu;
					IDMenu++;
					break;
				}
				System.out.println("No se puede agregar mas menus");
				break;

			case 3:
				if(clientes[0]==null || menus[0]==null) {
					System.out.println("No hay clientes y/o menus cargados");
					break;
				}

				if(pedidos[nPedidos-1] != null) {
					System.out.println("No se pueden tomar mas pedidos, ya vamos a cerrar.");
					break;
				}

				Cliente cliente = busquedaDeCliente();
				if(cliente==null) {
					System.out.println("No se encontró al cliente");
					break;
				}
				System.out.println("\nSeleccionó correctamente al cliente con id: " + cliente.getIDCliente());

				Menu menu = busquedaDeMenu();
				if(menu==null) {
					System.out.println("\nNo se encontró el menu");
					break;
				}
				System.out.println("\nSeleccionó correctamente al menu con id: " + menu.getIDMenu());
				System.out.println("\nVerificando transaccion...\n");
				if(cliente.getSaldoEnCuenta() < menu.getValor()) {
					System.out.println("No tiene saldo suficiente, pero puede cargar saldo en el menu de opciones :)");
					break;
				}
				cliente.setSaldoEnCuenta(cliente.getSaldoEnCuenta() - menu.getValor());
				System.out.println("Buen provecho! ahora el saldo restante es " + cliente.getSaldoEnCuenta());
				Pedido pedido = new Pedido(numeroPedido, cliente.getIDCliente(), menu.getIDMenu(), menu.getValor());
				pedidos[numeroPedido] = pedido;
				numeroPedido++;		
				break;

			case 4:
				if(clientes[0] != null) {
					System.out.println("\nLista de clientes ordenados de mayor a menor por saldo: ");
					for(int i=0; i < nClientes && clientes[i] != null; i++) {
						for(int j=0; j < nClientes-i-1;j++) {
							if(clientes[j+1] != null && clientes[j].getSaldoEnCuenta() < clientes[j+1].getSaldoEnCuenta()) {
								Cliente aux = clientes[j];
								clientes[j] = clientes[j+1];
								clientes[j+1] = aux;
							}
						}
					}
					for(Cliente c: clientes) {
						if(c != null) {
							System.out.println(c);
						}
					}
					break;
				}
				System.out.println("\nNo hay clientes cargados");
				break;

			case 5:
				if(menus[0] != null) {
					System.out.println("\nLista de menus ordenados de menor a mayor por valor:");
					for(int i=0; i<nMenus && menus[i]!=null; i++) {
						for(int j=0; j<nMenus-i-1;j++) {
							if(menus[j+1] != null && menus[j].getValor() > menus[j+1].getValor()) {
								Menu aux = menus[j];
								menus[j] = menus[j+1];
								menus[j+1] = aux;
							}
						}
					}
					for(Menu m: menus) {
						if(m != null) {
							System.out.println(m);
						}
					}
					break;
				}
				System.out.println("\nNo hay menus cargados");
				break;

			case 6:
				if(pedidos[0] != null) {					
					Cliente clienteConMasPedidos = obtenerClienteConMasPedidos();
					System.out.println("El cliente con mas pedidos es:\n"+clienteConMasPedidos);
					System.out.println("En total gastó " + clienteConMasPedidos.getTotalGastado());
					break;
				}
				System.out.println("\nNo hay pedidos cargados");
				break;

			case 7:
				if(clientes[0] == null) {
					System.out.println("\nEs necesario tener clientes ingresados para poder usar esta funcion");
					break;
				}
				Cliente clienteACargarSaldo = busquedaDeCliente();
				if(clienteACargarSaldo == null) {
					System.out.println("No se encontró al cliente");
					break;
				}
				System.out.println("\nSeleccionó correctamente al cliente con id: "+clienteACargarSaldo.getIDCliente());
				int saldo = verificarSaldoACargar();
				clienteACargarSaldo.setSaldoEnCuenta(clienteACargarSaldo.getSaldoEnCuenta() + saldo);
				System.out.println("¡Felicidades! El saldo actual del cliente es "+clienteACargarSaldo.getSaldoEnCuenta());
				break;

			case 8:
				System.out.println("\n¿Está seguro que desea salir del programa?");
				salirDelPrograma = verificarSalida();
				break;
			}
		} while (!salirDelPrograma);
	}

	public static int cantidadDeClientes() {
		String input;
		int nClientes = 0;
		do {
			System.out.print("\nIngrese la cantidad de personas a cargar (10 - 20): ");
			input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("No está permitido el ingreso de caracteres");
				continue;
			}
			nClientes = Integer.parseInt(input);
			if(nClientes < 10 || nClientes > 20) {
				System.err.println("Valor incorrecto. Reingrese.");
			} else {
				System.out.println("Ingreso exitoso.");
			}
		} while (nClientes < 10 || nClientes > 20);
		return nClientes;
	}

	public static int cantidadDeMenus() {
		String input = "";
		int nMenus = 0;
		do {
			System.out.print("\nIngrese la cantidad de Menus a cargar (5 - 8): ");
			input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("No está permitido el ingreso de caracteres");
				continue;
			}
			nMenus = Integer.parseInt(input);
			if(nMenus < 5 || nMenus > 8) {
				System.err.println("Valor incorrecto. Reingrese.");
			} else {
				System.out.println("Ingreso exitoso.");
			}
		} while (nMenus < 5 || nMenus > 8);
		return nMenus;
	}

	public static int cantidadDePedidos() {
		String input;
		int nPedidos = 0;
		do {
			System.out.println("\nIngrese la cantidad de pedidos a tomar (20 - 30)");
			input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("No está permitido el ingreso de caracteres");
				continue;
			}
			nPedidos = Integer.parseInt(input);
			if(nPedidos < 20 || nPedidos > 30) {
				System.err.println("Valor incorrecto. Reingrese.");
			} else {
				System.out.println("\nIngreso exitoso. Que disfrute del restaurant :)");
			}
		} while (nPedidos < 20 || nPedidos > 30);
		return nPedidos;
	}

	public static boolean esEntero(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public static int seleccionarOpcion() {
		int opcion = 0;
		do {
			System.out.println("\nMenu de opciones\n");
			System.out.println("1 - Cargar cliente");
			System.out.println("2 - Cargar menu");
			System.out.println("3 - Realizar pedido");
			System.out.println("4 - Ver clientes");
			System.out.println("5 - Ver menus");
			System.out.println("6 - Ver cliente con mas pedidos");
			System.out.println("7 - Cargar saldo");
			System.out.println("8 - Salir del programa");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			opcion = Integer.parseInt(input);
			if(opcion < 1 || opcion > 8) {
				System.err.println("\nOpcion invalida. Reingrese:");
			}
		} while (opcion < 1 || opcion > 8);
		return opcion;
	}

	public static boolean verificarSalida() {
		String input = "";
		do {
			System.out.println("Ingrese 'S' si desea salir, 'N' para quedarse");
			input = entrada.next();
			if(!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("N")) {
				System.err.println("\nIngreso invalido. Reingrese");
			}
		} while (!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("N"));
		if(input.equalsIgnoreCase("S")) {
			System.out.println("\n-- Fin del programa --");
			return true;
		}
		return false;
	}

	public static Cliente busquedaDeCliente() {
		int IDCliente = -1;
		do {
			System.out.println("\nLista de clientes ordenados por id:");
			for(int i=0; i<clientes.length && clientes[i]!=null; i++) {
				System.out.println(clientes[i]);
			}
			System.out.print("\nIngrese el ID del cliente a seleccionar: ");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			IDCliente = Integer.parseInt(input);
		} while (IDCliente==-1);
		for(int i=0; i<clientes.length && clientes[i]!=null;i++) {
			if(clientes[i].getIDCliente() == IDCliente) {
				return clientes[i];
			}
		}
		return null;
	}

	public static Menu busquedaDeMenu() {
		int IDMenu = -1;
		System.out.println("\nAhora, ingrese el id del menu a seleccionar");
		do {
			System.out.println("\nLista de menus ordenados por id:");
			for(int i=0; i<menus.length && menus[i]!=null; i++) {
				System.out.println(menus[i]);
			}
			System.out.print("\nIngrese el ID del menu a seleccionar: ");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			IDMenu = Integer.parseInt(input);
		} while (IDMenu==-1);
		for(int i=0; i<menus.length && menus[i]!=null;i++) {
			if(menus[i].getIDMenu() == IDMenu) {
				return menus[i];
			}
		}
		return null;
	}
	
	public static int verificarSaldoInicial() {
		int saldo = -1;
		do {
			System.out.print("\nIngrese el saldo a cargar: ");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			saldo = Integer.parseInt(input);
			if(saldo<1) {
				System.err.println("\nEl saldo debe ser positivo. Reingrese");
			}
		} while (saldo<1);
		return saldo;
	}
	
	public static int verificarValor() {
		int valor = -1;
		do {
			System.out.print("\nIngrese el valor: ");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			valor = Integer.parseInt(input);
			if(valor<1) {
				System.err.println("\nEl valor debe ser positivo. Reingrese.");
			}
		} while (valor<1);
		return valor;
	}

	public static int verificarSaldoACargar() {
		int saldo = -1;
		do {
			System.out.print("\nIngrese el saldo a cargar: ");
			String input = entrada.next();
			if(!esEntero(input)) {
				System.err.println("\nNo está permitido el ingreso de caracteres");
				continue;
			}
			saldo = Integer.parseInt(input);
			if(saldo<1) {
				System.err.println("\nEl saldo debe ser positivo. Reingrese");
			}
		} while (saldo<1);
		return saldo;
	}

	public static Cliente obtenerClienteConMasPedidos() {
		int[] contadorPedidos = new int[clientes.length];
		int[] totalGastado = new int[clientes.length]; 
		// Contar los pedidos de cada cliente y calcular el total gastado
		for (Pedido pedido : pedidos) {
			if (pedido != null) {
				int clienteID = pedido.getIDCliente();
				contadorPedidos[clienteID]++;
				totalGastado[clienteID] += pedido.getImporte(); 
			}
		}
		// Encontrar el cliente con más pedidos
		int cantidadMayorDePedidos = 0;
		int clienteConMasPedidos = 0;
		for (int i = 0; i < contadorPedidos.length; i++) {
			if (contadorPedidos[i] > cantidadMayorDePedidos) {
				cantidadMayorDePedidos = contadorPedidos[i];
				clienteConMasPedidos = i;
			}
		}
		// Setearle el total gastado al cliente con mas pedido y devolverlo
		Cliente cliente = clientes[clienteConMasPedidos];
		cliente.setTotalGastado(totalGastado[cliente.getIDCliente()]);
		return cliente;
	}

}
