/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.cefsa.dao;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Imagem;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Israel
 */
public class ImagemDAO implements GenericoDAO<Imagem> {

    @Override
    public List<Imagem> listar() throws PersistenciaException {
        List<Imagem> imagems = new ArrayList();
        String sql = "SELECT * FROM EMPRESA.IMAGEM";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                imagems.add(new Imagem(result.getLong("ID_IMAGEM"), result.getString("NOME"), result.getBytes("CONTEUDO")));
            }
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return imagems;
    }

    @Override
    public void inserir(Imagem imagem) throws PersistenciaException {
        String sql = "INSERT INTO EMPRESA.IMAGEM (ID_IMAGEM, NOME, CONTEUDO) VALUES (?, ?, ?)";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, imagem.getCodigo());
            pStatement.setString(2, imagem.getNome());
            pStatement.setBlob(3, new SerialBlob(imagem.getConteudo()));
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(Imagem imagem) throws PersistenciaException {
        String sql = "UPDATE EMPRESA.IMAGEM SET NOME=?, CONTEUDO=? WHERE ID_IMAGEM = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, imagem.getNome());
            pStatement.setBlob(2, new SerialBlob(imagem.getConteudo()));
            pStatement.setLong(3, imagem.getCodigo());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(Imagem imagem) throws PersistenciaException {
        String sql = "DELETE FROM EMPRESA.IMAGEM WHERE ID_IMAGEM = ?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, imagem.getCodigo());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Imagem listarPorID(Long codigo) throws PersistenciaException {

        return this.listarPorID(new Imagem(codigo));
    }

    @Override
    public Imagem listarPorID(Imagem imagem) throws PersistenciaException {
        String sql = "SELECT * FROM EMPRESA.IMAGEM WHERE ID_IMAGEM = ?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, imagem.getCodigo());
            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                imagem.setCodigo(result.getLong("ID_IMAGEM"));
                imagem.setNome(result.getString("NOME"));
                Blob blob = result.getBlob("CONTEUDO");
                imagem.setConteudo(blob.getBytes(1, (int) blob.length()));
                blob.free();
            } else {
                imagem = null;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return imagem;
    }

    public String imageToString(Long codigo) throws PersistenciaException {

        String retorno = null;
        byte[] input = null;
        try {
            String sql = "SELECT * FROM EMPRESA.IMAGEM WHERE ID_IMAGEM = ?";
            Connection connection = null;
            connection = Conexao.getInstance().getConnection();

            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, codigo);
            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                result.getLong("ID_IMAGEM");
                result.getString("NOME");
                Blob blob = result.getBlob("CONTEUDO");
                input = blob.getBytes(1, (int) blob.length());
                blob.free();
            }
            retorno = new String( input);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
//    public static void main(String[] args) throws PersistenciaException {
//        ImagemDAO img = new ImagemDAO();
//        System.out.println(img.imageToString(1040L));
//    }
}
