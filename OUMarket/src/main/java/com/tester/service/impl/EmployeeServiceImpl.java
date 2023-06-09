/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tester.service.impl;

import com.tester.pojo.Employee;
import com.tester.service.EmployeeService;
import com.tester.utils.MySQLConnectionUtil;
import com.tester.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<Employee> getEmployees(String kw) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            List<Employee> employees = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                Employee c = new Employee(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("join_date"),
                        rs.getDate("birthday"),
                        rs.getBoolean("active"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getInt("branch_id"),
                        rs.getString("avatar"));
                employees.add(c);
            }
            return employees;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("join_date"),
                        rs.getDate("birthday"),
                        rs.getBoolean("active"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getInt("branch_id"),
                        rs.getString("avatar"));
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Employee authencateEmployee(String username, String password) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            String query = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs != null && rs.next()) {
                if (rs.getBoolean("active")
                        && Utils.verifyPassword(password, rs.getString("password"))) {
                    return new Employee(rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getDate("join_date"),
                            rs.getDate("birthday"),
                            rs.getBoolean("active"),
                            rs.getString("phone"),
                            rs.getString("role"),
                            rs.getInt("branch_id"),
                            rs.getString("avatar"));
                }
                return null;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int addEmployee(Employee employee) {
        employee.setPassword(Utils.hashPassword(employee.getPassword()));
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            conn.setAutoCommit(false);
            String query = "INSERT INTO employee(id, name, username, password, join_date, birthday, phone, role, branch_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, employee.getId());
            stm.setString(2, employee.getName());
            stm.setString(3, employee.getUsername());
            stm.setString(4, employee.getPassword());
            stm.setDate(5, new Date(employee.getJoinDate().getTime()));
            stm.setDate(6, new Date(employee.getBirthday().getTime()));
            stm.setString(7, employee.getPhone());
            stm.setString(8, employee.getRole());
            if (employee.getBranchId() == 0) {
                stm.setObject(9, null);
            } else {
                stm.setInt(9, employee.getBranchId());
            }
            int r = stm.executeUpdate();
            conn.commit();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, "Hệ thống có lỗi!!!", ex);
            return -1;
        }
    }

    @Override
    public int updateEmployee(Employee employee) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            conn.setAutoCommit(false);
            String query = "UPDATE employee SET name = ?, birthday = ?, phone = ?, active = ? ,role = ?, branch_id = ? WHERE id = ?";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, employee.getName());
            stm.setDate(2, new Date(employee.getBirthday().getTime()));
            stm.setString(3, employee.getPhone());
            stm.setBoolean(4, employee.isActive());
            stm.setString(5, employee.getRole());
            if (employee.getBranchId() == 0) {
                stm.setObject(6, null);
            } else {
                stm.setInt(6, employee.getBranchId());
            }
            stm.setString(7, employee.getId());
            int r = stm.executeUpdate();
            conn.commit();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, "Hệ thống có lỗi!!!", ex);
            return -1;
        }
    }

    @Override
    public Employee getEmployeeByPhone(String phone) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            String query = "SELECT * FROM employee WHERE phone = ?";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, phone);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDate("join_date"),
                        rs.getDate("birthday"),
                        rs.getBoolean("active"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getInt("branch_id"),
                        rs.getString("avatar"));
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int saveImage(String empId, String url) {
        try (Connection conn = MySQLConnectionUtil.getConnection()) {
            conn.setAutoCommit(false);
            String query = "UPDATE employee SET avatar = ? WHERE id = ?";
            PreparedStatement stm = conn.prepareCall(query);
            stm.setString(1, url);
            stm.setString(2, empId);

            int r = stm.executeUpdate();
            conn.commit();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, "Hệ thống có lỗi!!!", ex);
            return -1;
        }
    }
}
