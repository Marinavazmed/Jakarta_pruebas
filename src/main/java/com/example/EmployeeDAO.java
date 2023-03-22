package com.example;

import java.util.List;

public interface EmployeeDAO {
	List<Employee> findAll();
	
	
	//METODOS CRUD
	Employee findOne(Long id);
	boolean create(Employee empleado);
	boolean update(Employee empleado);
	boolean delete(Long id);
	
	
}
