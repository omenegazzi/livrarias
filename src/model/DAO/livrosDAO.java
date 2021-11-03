/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import connection.bancoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.autores;
import model.editoras;
import model.livros;

/**
 *
 * @author diego.menegazzi
 */
public class livrosDAO {

    public void cadastrar(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (id_editora, id_autor, titulo, ano) "
                    + "VALUES (?,?,?,?)");
            stmt.setInt(1, l.getEditoras().getId());
            stmt.setInt(2, l.getAutores().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<livros> listar() {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<livros> livros = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("select * vwlistar");
            rs = stmt.executeQuery();

            while (rs.next()) {
                livros l = new livros();
                l.setId_livro(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                editoras e = new editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditoras(e);

                autores a = new autores();
                a.setNome(rs.getString("nome_autor"));
                l.setAutores(a);

                livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livros;
    }

    public void alterar(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE livros set id_editora = ?, id_autor = ?, titulo = ?, ano = ? where id_livros = ?");
            stmt.setInt(1, l.getEditoras().getId());
            stmt.setInt(2, l.getAutores().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            stmt.setInt(5, l.getId_livro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");

        } catch (SQLException ex) {
        }

    }

    public void excluir(livros l) {

        Connection conn = bancoMysql.conectaBanco();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM livros WHERE id_livros = ?");
            stmt.setInt(1, l.getId_livro());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");

        } catch (SQLException ex) {
        }

    }
}
