package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> findAll() {
		List<Employee> lista_empleados = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jakarta_ee", "root",
					"jumpinthefire11");
			Statement statement = conexion.createStatement();
			String sql = "SELECT * FROM employees";
			ResultSet ResultadoSet = statement.executeQuery(sql);
			
			while (ResultadoSet.next()) {
				Long id = ResultadoSet.getLong("id");

				String nombre = ResultadoSet.getString("name");

				String nif = ResultadoSet.getString("nif");

				Integer age = ResultadoSet.getInt("age");
				//metemos en lista
				lista_empleados.add(new Employee(id, nombre, nif, age));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return lista_empleados;
	}

	@Override
	public Employee findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Employee empleado) {
		boolean result = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jakarta_ee", "root",
					"jumpinthefire11");
			
			//para crear usamos preparedstatement que junto a execute monta y lanza la query
			String sql = "INSERT INTO employees (name, nif, age) VALUES (?,?,?)";
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, empleado.getName());
			statement.setString(2, empleado.getNif());
			statement.setLong(3, empleado.getAge());
			statement.executeUpdate();
			result = true;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Employee empleado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
