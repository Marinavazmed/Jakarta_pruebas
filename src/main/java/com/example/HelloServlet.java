package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class HelloServlet
 * //Esto es el enroutado (url que responde a este servlet)
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
//		//CONEXIÓN A BBDD A "MANO":
//		
//			try {
//				//Señalamos el driver
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				//1.-Se crea conexión con DriverManager, bbdd, user, contraseña
//				Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jakarta_ee", "root", "jumpinthefire11");
//				
//				Statement statement = conexion.createStatement();
//				
//				//2.-Elabora + ejecuta sentencia
//				String sql = "SELECT * FROM employees";
//				ResultSet ResultadoSet = statement.executeQuery(sql);
//				
//				
//				//3.-Se procesa resultado
//				while(ResultadoSet.next()) {
//					Long id = ResultadoSet.getLong("id");
//
//					String nombre = ResultadoSet.getString("name");
//
//					String nif = ResultadoSet.getString("nif");
//
//					Integer age  = ResultadoSet.getInt("age");
//					System.out.println(id + " " + nombre +  " " + nif + " " + age);
//				}
//				
//			} catch (SQLException | ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		//3.-Procesar resultados
		response.getWriter().append("Servido desde servlet: ").append(request.getContextPath());
		
		//LA CAPA DAO EXTRAE EL CODIGO DE MANEJO DE BBDD Y LO ORGANIZA EN INTERFACES PARA NO MEZCLAR LAS DIVERSAS CAPAS
		//1.-CREAMOS UNA INTERFAZ
		//2.-CREAMOS UNA CLASE QUE HEREDA DE ESA INTERFAZ PARA QUE PODAMOS ACCEDER A ELLOS. EN ESTA CLASE ESTARÁ NUESTRO CÓDIGO SUPERIOR
		//3.-LA INTERFAZ EMPLOYEEDATO TIENE LOS MÉTODOS CRUD, Y LA EMPLOYEE DAO IMPL ES LA INSTANCIA
		
		//4.-SACAMOS UN OBJETO DAOIMP DE TIPO ABSTRACTO PARA ACCEDER A SUS MÉTODOS
		EmployeeDAO dao_abstracto = new EmployeeDAOImpl();
		
		Employee nuevoEmpleado = new Employee(null, "Prueba nuevo empleado", "21152557C", 40);
		
		//métodos CRUD del objeto daoimpl:
		dao_abstracto.create(nuevoEmpleado);
		System.out.println(dao_abstracto.findAll());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
