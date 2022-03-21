/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.principal;

import br.edu.cefsa.dao.CargoDAO;
import br.edu.cefsa.dao.DepartamentoDAO;
import br.edu.cefsa.dao.FuncionarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cargo;
import br.edu.cefsa.model.Departamento;
import br.edu.cefsa.model.Funcionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Israel
 */
public class Principal {

    public static void main(String[] args) {
        try {
            List<Cargo> cargos = new CargoDAO().listar();
            for (Cargo cargo : cargos) {
                System.out.println(cargo.getCodigo() + " " + cargo.getNome());
            }
            List<Departamento> departamentos = new DepartamentoDAO().listar();
            for (Departamento departamento : departamentos) {
                System.out.println(departamento.getCodigo() + " " + departamento.getNome());
            }
            List<Funcionario> funcionarios = new FuncionarioDAO().listar();
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.getCodigo() + " " + funcionario.getNome());
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
