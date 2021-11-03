/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.bancoMysql;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.editoras;

/**
 *
 * @author diego.menegazzi
 */

// DAO - Objeto de Acesso a Dados
// MVC Model View Controller


public class editorasDAO {
   
    public void cadastrar(editoras e){
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("INSERT INTO editoras (nome) VALUES (?)");
            stmt.setString(1, e.getNome());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Editora cadastrada com sucesso!");
                                   
        } catch(SQLException ex){                        
        }
                
    }
    
    public List<editoras> listar(){
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<editoras> editoras = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras");
            rs = stmt.executeQuery();
        
        while (rs.next()){
            editoras e = new editoras();
            e.setId(rs.getInt("id_editora"));
            e.setNome(rs.getString("nome"));
            
            editoras.add(e);
        }            
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return editoras;
    }
    
    
    public void alterar(editoras e){
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("UPDATE editoras set nome = ? where id_editora = ?");
            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Editora alterada com sucesso!");
                                   
        } catch(SQLException ex){                        
        }
                
    }
    
    public void excluir(editoras e){
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("DELETE FROM editoras WHERE id_editora = ?");
            stmt.setInt(1, e.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Editora excluída com sucesso!");
                                   
        } catch(SQLException ex){                        
        }
                
    }
    
    public List<editoras> pesquisar(String texto){
        
        Connection conn = bancoMysql.conectaBanco();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<editoras> editoras = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras where nome like ? ");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();
        
        while (rs.next()){
            editoras e = new editoras();
            e.setId(rs.getInt("id_editora"));
            e.setNome(rs.getString("nome"));
            
            editoras.add(e);
        }            
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return editoras;
    }
    
    
}
