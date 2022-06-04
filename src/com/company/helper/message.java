package com.company.helper;

import com.company.view.EmpregadoGUI;

import javax.swing.*;

public class message {
    public static void mensagemSucesso(){
        JOptionPane.showMessageDialog(null,
                "Cadastro realizado com sucesso",
                "Empregado cadastrado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemErro(){
        JOptionPane.showMessageDialog(null,
                "Todos os campos devem estar preenchidos",
                "Campos não preenchidos",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemEmpregadoDulicado(){
        JOptionPane.showMessageDialog(null,
                "Código de usuário já cadastrado!",
                "Empregado já existe",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemInformativa(){
        JOptionPane.showMessageDialog(null,
                "Verifique as informações dos campos",
                "Informações inválidas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemDeletado(){
        JOptionPane.showMessageDialog(null,
                "Usuario deletado com sucesso",
                "Deletado",
                JOptionPane.INFORMATION_MESSAGE);
    }


}
