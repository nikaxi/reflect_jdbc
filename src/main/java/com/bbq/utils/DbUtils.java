package com.bbq.utils;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {
    // get connection

   static private  Properties properties;

   private DbUtils(String filename) throws IOException {
      if (properties == null) {
         properties = new Properties();
      }
      FileInputStream fileInputStream = new FileInputStream(filename);
      properties.load(fileInputStream);
   }

   public Properties getProperties() {
      return properties;
   }

   public static Connection getConection() throws IOException, SQLException {
      DbUtils dbUtils = new DbUtils("/Users/huangweiyi/java-dev/reflect_jdbc/src/main/resources/database.propertites");
       Properties properties = dbUtils.getProperties();

       String dbUrl = properties.getProperty("db.url");
       String username = properties.getProperty("db.username");
       String password = properties.getProperty("db.password");

       Connection connection = DriverManager.getConnection(dbUrl, username, password);
       return connection;
   }
}
