package com.example.demo.mappers;

import com.example.demo.resource.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {


    @Select("select * from employee;")
    List<Employee> findAll();

    @Insert("insert into employee(name,email,job_title,phone,image_url,employee_code) values(#{name},#{email},#{jobTitle},#{phone},#{imageUrl},#{employeeCode});")
    @SelectKey(statement = "SELECT MAX( id ) FROM employee;", keyProperty = "id",
            before = false, resultType = Long.class)
    void insert(Employee employee);

    @Select("SELECT MAX( id ) FROM employee;")
    long getCurrentId();


    @Select("SELECT * FROM employee where id=#{id};")
    Employee getEmployee(Long id);

    @Delete("delete from employee where id=#{id};")
    void deleteEmployee(Long id);

    @Update("UPDATE employee SET name=#{name},email=#{email},job_title=#{jobTitle},phone=#{phone},image_url=#{imageUrl},employee_code=#{employeeCode} WHERE id=#{id};")
    void updateEmployee(Employee employee);
}
