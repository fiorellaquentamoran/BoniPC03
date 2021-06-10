package model;
//
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.classgen.ReturnAdder.ReturnStatementListener;

import util.MySqlDBConexion;
import entidad.Cliente;
import entidad.Tipo_Cliente;

public class ClienteModel {
	
	//LISTAR CLIENTES 
	
	public List<Cliente> LISTACLIENTES() {
		//
		ArrayList<Cliente> lista= new ArrayList<Cliente>();
		//conexion 
		Connection conexion= null;
		//pstm
		PreparedStatement pstm= null;
		//result set 
		ResultSet rs= null;
		
		try {
			
			//conexion 
			conexion =  MySqlDBConexion.getConexion();
			//sentencia 
			String sentencia="select c.idCliente,c.nombres,c.apellidos, c.fechaNacimiento,c.idTipoCliente, tc.nombre from cliente c inner join tipo_cliente tc on c.idTipoCliente= tc.idTpoCliente";
					//
			pstm= conexion.prepareStatement(sentencia);
			//
			rs= pstm.executeQuery();
			//recorrido 
			Cliente cliente = null;
			Tipo_Cliente  TpoCli=null;
			
			while(rs.next()) {
				//obj cliente 
				 cliente= new Cliente();
				cliente.setIdCliente(rs.getInt(1));
				cliente.setNombres(rs.getString(2));
				cliente.setApellidos(rs.getString(3));
				cliente.setFechaNacimiento(rs.getDate(4));
				
				//objTipoCliente 
			     TpoCli= new Tipo_Cliente();
			     TpoCli.setIdTipoCliente(rs.getInt(5));
				TpoCli.setNombre(rs.getString(6));
				
				//
				cliente.setTipo_Cliente(TpoCli);
				
				//guarda data en arraylist
				lista.add(cliente);
				}
		  	}
		
		catch (SQLException error) {
			
			error.printStackTrace();
		}
		finally {
			
			try {
			if(pstm!=null) {
				
				pstm.close();
			}
			if(conexion!=null) {
				
				conexion.close();
				}
			}
			catch(Exception error) {
				
				error.printStackTrace();
			}	
			
		}
		return lista;
	}
		
		
	
	////////////////////////////////////////////////////////////////////
	////METODO PARA CONSULTAR 
	public List<Cliente> CONSULTAPORDNI(String dni){
		
		// 
		ArrayList<Cliente>lista= new ArrayList<Cliente>();
		//
	Connection conexion= null;
	//
	PreparedStatement pstm= null;
	//
	ResultSet rs= null;
	
		try {
			
	//conexion
			conexion= MySqlDBConexion.getConexion();
			//sentencia 
			String sentencia="select c.idCliente,c.nombres,c.apellidos, c.fechaNacimiento,c.idTipoCliente,tc.nombre from cliente c inner join tipo_cliente tc on c.idTipoCliente= tc.idTpoCliente where c.dni=?";
			//preparastetment 
			pstm= conexion.prepareStatement(sentencia);
			//parametro
			pstm.setString(1, dni);
			//
			rs= pstm.executeQuery();
			//
			//RECORRIDO 
			//obj cliente
			Cliente objC = null;
			Tipo_Cliente objT=null;
			//obj tipocliente
			while(rs.next()) {
				//traer datos a cliente 
				objC= new Cliente();
				objC.setIdCliente(rs.getInt(1));
				objC.setNombres(rs.getString(2));
				objC.setApellidos(rs.getString(3));
				objC.setFechaNacimiento(rs.getDate(4));
				
				//traer datos a tipo
				objT= new Tipo_Cliente();
				objT.setIdTipoCliente(rs.getInt(5));
				objT.setNombre(rs.getString(6));
				//mandar datos a la variable tipo de la clase cliente 
				objC.setTipo_Cliente(objT);
				
				//
				lista.add(objC);
				
			}
		} 
		
		catch (SQLException error) {
			
			error.printStackTrace();
		}
		
		finally {
			
			
			try {
				//cerrar conexion y psrtm
				if(conexion!=null) {
					
					conexion.close();
				}
				if(pstm!=null) {
					
					pstm.close();
				                }
				
			} catch (Exception e) {
				e.printStackTrace();
			                       }	
			
		       }
		

		return lista;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


