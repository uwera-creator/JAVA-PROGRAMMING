//GROUP 18
//UWERA Josianne 223002615
//UWASE Monique 223005990
//NYIRAMBARUSHIMANA Assoumpta 223016189


package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/loanmanagement", 
            "root", 
            ""
        );
    }
}

