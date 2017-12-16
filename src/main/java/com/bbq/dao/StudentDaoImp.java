package com.bbq.dao;


import com.bbq.beans.Student;
import com.bbq.dbexception.DBException;
import com.bbq.utils.DbUtils;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements  StudentDao {

    private Connection connection;

    public StudentDaoImp() {
        try {
            connection = DbUtils.getConection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public int insertStudent(Student st){
        int result = -1;
        String sql = "insert into students (name,careerType,entryDate,school, idOnSite, dailyReportUrl, brotherName, createAt, updateAt, QQ, desire)" +
                "values (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        List<Object> params = Student2List(st);
        try {
            result = updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Student getStudentById(long id) {
        String sql = "select * from students where id=?";
        List<Object> params = new ArrayList<Object>();
        params.add(id);
        Student student=null;
        try {
            student = findSingleObject(sql, params, Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getStudentByName(String name) {
        String sql = "select * from students where name=?";
        List<Object> params = new ArrayList<Object>();
        params.add(name);
        try {
            return findObjects(sql, params, Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStudent(Student st) {
        int result = -1;
        String sql = "update students set name=?, careeyType=?, entryDate=?, school=?, idOnSite=?, dailyReportUrl=?," +
                "brotherName=?, createAt=?, updateAt=?, desire=?";
        List<Object> params = Student2List(st);
        try {
            result = updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    public int deleteStudentById(long id) {
        String sql = "delete from students where id=?";
        List<Object> params = new ArrayList<Object>();
        params.add(id);
        int result = 0;
        try {
            result = updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public int updateByPreparedStatement(String sql, List<Object> params) throws Exception{
        int result = -1;
        PreparedStatement pst = connection.prepareStatement(sql);
        int index = 1;
        if(params != null && params.size()>0) {
            for(int i = 0; i < params.size(); i++){
                pst.setObject(index++, params.get(i));
            }
        }

        result = pst.executeUpdate();
        return result;
    }

    private List<Object> Student2List(Student st) {
        List<Object> params = new ArrayList<Object>();
        params.add(st.getName());
        params.add(st.getCareerType());
        params.add(st.getEntryDate());
        params.add(st.getSchool());
        params.add(st.getIdOnSite());
        params.add(st.getDailyReportUrl());
        params.add(st.getBrotherName());
        params.add(st.getCreateAt());
        params.add(st.getUpdateAt());
        params.add(st.getQQ());
        params.add(st.getDesire());
        return params;
    }

    // 多记录查询

    public <T> List<T> findObjects(String sql, List<Object> params, Class<T> cls) throws  Exception
    {
        List<T> results = new ArrayList<T>();
        int index = 1;
        PreparedStatement pst = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for(int i=0; i< params.size(); i++) {
                pst.setObject(index++, params.get(i));
            }
        }

        ResultSet resultSet = pst.executeQuery();
        ResultSetMetaData meta_data = resultSet.getMetaData();
        int cols = meta_data.getColumnCount();
        while (resultSet.next()) {
            T result = cls.newInstance();
            for(int i=0 ; i < cols; i++) {
                String col_name = meta_data.getColumnName(i+1);
                Object col_value = null;
                // 利用反射需要各种数据类型的一致性
                if (col_name.equals("id")) {
                    col_value = Long.valueOf("0"+resultSet.getObject(col_name));
                }
                else {
                    col_value = resultSet.getObject(col_name);
                }
                Field field = cls.getDeclaredField(col_name);
                field.setAccessible(true);
                field.getType();
                field.set(result, col_value);
            }
            results.add(result);
        }
        return results;
    }

    // 利用发射查询单记录
    public <T> T findSingleObject(String sql, List<Object> params, Class<T> cls)
        throws Exception {
        T result = null;
        int index = 1;
        PreparedStatement pst = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for(int i=0; i< params.size(); i++) {
                pst.setObject(index++, params.get(i));
            }
        }

        ResultSet resultSet = pst.executeQuery();
        ResultSetMetaData meta_data = resultSet.getMetaData();
        int cols = meta_data.getColumnCount();
        while (resultSet.next()) {
            result = cls.newInstance();
            for(int i=0 ; i < cols; i++) {
                String col_name = meta_data.getColumnName(i+1);
                Object col_value = null;
                // 利用反射需要各种数据类型的一致性
                if (col_name.equals("id")) {
                    col_value = Long.valueOf("0"+resultSet.getObject(col_name));
                }
                else {
                    col_value = resultSet.getObject(col_name);
                }
                Field field = cls.getDeclaredField(col_name);
                field.setAccessible(true);
                field.getType();
                field.set(result, col_value);
            }
        }
        return result;
    }
}
