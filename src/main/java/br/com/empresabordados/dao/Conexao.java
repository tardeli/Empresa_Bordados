/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresabordados.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tardeli
 */
public class Conexao {
    private static Connection conn;
    
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "11r4954388";
    private static final String url = "jdbc:mysql://localhost:3306/casadasmarmitas";

    public Conexao() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if(conn!=null){
                System.out.println("Conexao estabelecida com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar!");
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
}
