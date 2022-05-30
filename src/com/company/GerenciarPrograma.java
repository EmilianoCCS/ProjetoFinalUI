package com.company;

import com.company.view.EmpregadoGUI;

import javax.swing.*;

public class GerenciarPrograma {

    public static void main(String[] args) {
        JFrame telaCadastro = new JFrame();
        telaCadastro.setContentPane(new EmpregadoGUI().getPanelTelaCadastro());
        telaCadastro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        telaCadastro.setSize(500,350);
        telaCadastro.setTitle("Calcula INSS");
        telaCadastro.setVisible(true);
    }
}