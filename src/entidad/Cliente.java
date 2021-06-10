package entidad;
//
import java.sql.Date;
public class Cliente {

	private int idCliente ;
	private String nombres ;
	private String apellidos;
	private String dni;
	private Date fechaNacimiento;
	private Tipo_Cliente Tipo;
	private String tipocliente;
	
	//METODOS DE ACCESO GET Y SET
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Tipo_Cliente getTipo() {
		return Tipo;}
	
	public void setTipo_Cliente(Tipo_Cliente Tipo) {
		this.Tipo= Tipo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	public String gettipocliente() {
		tipocliente = Tipo.getNombre();
		return tipocliente;
	}
	public void settipocliente(String tipocliente) {
		this.tipocliente = tipocliente;
	}
	
	
	
	
	
	
	
}
