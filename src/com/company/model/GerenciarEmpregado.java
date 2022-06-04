package com.company.model;

import com.company.helper.message;
import com.company.view.EmpregadoGUI;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class GerenciarEmpregado {
    public static List<Empregado> listaEmpregados = new ArrayList<Empregado>();

    public void adcionarEmpregado(Empregado empregado){ // Recebe um empregado como argumento
        if(verificarExistencia(empregado)){ // Verifica se existe
            listaEmpregados.add(empregado); //Adiciona a lista o empregado atual
            message.mensagemSucesso();
        }else {
            message.mensagemEmpregadoDulicado();
            System.out.println("Usuario já Cadastrado!");
        }
    }

    public ArrayList listarTodosEmpregados(){
        for (Empregado emp: listaEmpregados) {
            System.out.println(emp);
        }
        System.out.println("-------------------");
        return (ArrayList) listaEmpregados;
    }

    public static void removerEmpregado(Empregado empregado){
        listaEmpregados.remove(empregado);
    }

    public static boolean verificarExistencia(Empregado empregado){
        for (Empregado emp: listaEmpregados) { // Percorre a lista de empregados
            if(emp.getCodigoEmpregado() == (empregado.getCodigoEmpregado())){ // Verifica se o código de entrada é o mesmo do que o usuário atual da lista
                System.out.println("Ja cadastrado");
                return false;
            }
        }
        return true;
    }

    public GerenciarEmpregado(List<Empregado> listaEmpregados) {
        this.listaEmpregados = listaEmpregados;
    }

    public GerenciarEmpregado() {}

}
