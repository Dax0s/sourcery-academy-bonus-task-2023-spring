package com.example.backend.mybatis.repositories;

import com.example.backend.models.Employee;
import com.example.backend.mybatis.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeRepository {
    @Select("""
            SELECT * FROM employee;
            """)
    @Result(column = "id", property = "id", typeHandler = UuidTypeHandler.class)
    @Result(column = "phone_number", property = "phoneNumber")
    List<Employee> getAllEmployees();

    @Insert("""
            INSERT INTO employee (id, name, email, phone_number)
            VALUES (#{employee.id}, #{employee.name}, #{employee.email}, #{employee.phoneNumber});
            """)
    void createEmployee(@Param("employee") Employee employee);

    @Delete("""
            DELETE FROM employee;
            """)
    void clearDatabase();
}
