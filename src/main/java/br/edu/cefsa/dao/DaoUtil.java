package br.edu.cefsa.dao;

import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Departamento;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DaoUtil {

    private final Logger LOG = Logger.getLogger(DepartamentoDAO.class.getName());

    public void carregaSql() throws PersistenciaException {
        Path path = Paths.get("script.txt");

        try(Connection connection = Conexao.getInstance().getConnection()) {
            String sql = Files.lines(path).collect(Collectors.joining());
            String[] split = sql.split(";");
            Arrays.stream(split).forEach(query -> {

                try(PreparedStatement pStatement = connection.prepareStatement(query)) {
                    pStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
            LOG.info("Carregou banco");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
