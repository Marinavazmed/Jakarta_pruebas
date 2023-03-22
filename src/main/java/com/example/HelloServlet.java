package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
			
//		//CONEXIÓN A BBDD CON HIBERNATE:
		//Añadimos JPA en Facets, y se generará un archivo persistence.xml en META-INF en src/main/java
		//Una vez configuremos en ese archivo el hibernate, podemos crear una entidad JPA (ORM). Los JPA deben ser serializables
		//Estas entidades ya pueden asociarse @OneToOne, @ManyToMany
		//Probamos a hacer un JPA añadiendo las características del mismo a la clase Employee
		
		//Pasamos el nombre de la unidad de persistencia (persistence.xml)
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jakarta_ee_hibernate");
		EntityManager manager = managerFactory.createEntityManager();
		
		//select
		Employee empleado = manager.find(Employee.class, 1L);
		System.out.println(empleado);
		
		//create
		manager.getTransaction().begin();
		//ID null para que se genere solo
		Employee empleado_nuevo = new Employee(null, "Empleado JPA", "211623627", 50);
		manager.persist(empleado_nuevo);
		manager.getTransaction().commit();
		
		//update
		manager.getTransaction().begin();
		//aqui seria un empleado ya construido no uno nuevo
		manager.merge(empleado);
		manager.getTransaction().commit();
		
		//delete
		manager.getTransaction().begin();
		manager.remove(empleado);
		manager.getTransaction().commit();

		response.getWriter().append("Servido desde servlet: ").append(request.getContextPath());
		
		
		
		//LA CAPA DAO PUEDE IMPLEMENTARSE CON HIBERNATE + JPA. Para eso se introduce dentro de la clase de implementación los métodos 
		//usados arriba. Creamos un JpaUtil para ahorrarnos tener que llamar al manager factory y al factory y un dao de implementación
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
