package com.company;

import com.company.view.EmpregadoGUI;

import javax.swing.*;

public class GerenciarPrograma {

    public static void main(String[] args) {
        JFrame telaCadastro = new JFrame(); //Cria um objeto de tela do tipo JFrame
        telaCadastro.setContentPane(new EmpregadoGUI().getPanelTelaCadastro()); // Adiciona a este objeto a classe EmpregadoGUI
        telaCadastro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Encerra o programa ao clicar no X
        telaCadastro.setSize(550,370); // Tamanho da tela
        telaCadastro.setTitle("Calcula INSS"); // Coloca um titulo para a Janela
        telaCadastro.setVisible(true); // Deixa visível
    }
}
