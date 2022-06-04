package com.company.view;

import com.company.model.Empregado;
import com.company.model.GerenciarEmpregado;
import com.company.model.ParametrosInss;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpregadoGUI {
    private JTextField textFieldNomeEmpregado;
    private JTextField textFieldCodigoEmpregado;
    private JTextField textFieldSetor;
    private JTextField textFieldSalarioBruto;
    private JButton calcularRecolhimentoINSSButton;
    private JButton cadastrarButton;
    private JButton apresentarEmpregadosButton;
    private JLabel LabelCodigoEmpregado;
    private JLabel LabelNomeEmpregado;
    private JLabel LabelSetor;
    private JLabel LabelSalarioBruto;
    private JLabel LabelRecolhimento;
    private JLabel LabelReais;
    private JLabel LabelValorRecolhido;
    private JPanel JPanelTelaCadastro;
    private JButton buscarButton;
    private JButton deletarButton;
    private JTable table1;

    public Empregado empregado;
    public GerenciarEmpregado ge = new GerenciarEmpregado();
    public ParametrosInss inss;
//    Integer i=0;


    public JPanel getPanelTelaCadastro(){
        return JPanelTelaCadastro;
    }

    public EmpregadoGUI(){
        apresentarEmpregadosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirGrupoEmpregados();
            }
        });
        calcularRecolhimentoINSSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                textFieldCodigoEmpregado.setText(String.valueOf(i++));
//                textFieldNomeEmpregado.setText("Nome do caboclo");
//                textFieldSetor.setText("Logistica");
//                textFieldSalarioBruto.setText(String.valueOf(1500));
                CalcularRecolhimentoInss();
            }
        });
        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               adicionarEmpregado();
            }
        });

        buscarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Integer codigoBusca = Integer.valueOf(textFieldCodigoEmpregado.getText());
                for (Empregado emp: GerenciarEmpregado.listaEmpregados) {
                    if (emp.getCodigoEmpregado() == codigoBusca){
                        textFieldNomeEmpregado.setText(emp.getNomeEmpregado());
                        textFieldSetor.setText(emp.getSetor());
                        textFieldSalarioBruto.setText(Double.toString(emp.getSalarioBruto()));
                        LabelValorRecolhido.setText(Double.toString(emp.getRecInss()));
                        System.out.println(emp);
                        empregado = emp;
                    }
                }
            }
        });
        deletarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(empregado!= null){
                        GerenciarEmpregado.removerEmpregado(empregado);
                        LimparCampos();
                        mensagemDeletado();
                        empregado = new Empregado();
                    }
                }
                catch (Exception erro){
                    mensagemErro();
                    System.out.println(erro);
                }
            }
        });
    }

    public void CalcularRecolhimentoInss() {
        try{
            empregado = new Empregado();
            inss = new ParametrosInss();
            Integer codigo = Integer.parseInt(textFieldCodigoEmpregado.getText());
            String nome = textFieldNomeEmpregado.getText();
            String setor = textFieldSetor.getText();
            Double salarioBruto = Double.parseDouble(textFieldSalarioBruto.getText());
            empregado.setCodigoEmpregado(codigo);
            empregado.setNomeEmpregado(nome);
            empregado.setSetor(setor);
            empregado.setSalarioBruto(salarioBruto);
            empregado.setRecInss(valorRecolhimentoInss(salarioBruto));
            LabelValorRecolhido.setText(String.valueOf(empregado.getRecInss()));


        }catch (Exception e){
            mensagemInformativa();
            System.out.println(e);
        }
    }

    public double valorRecolhimentoInss(double salarioBruto)
    {
        double recolhimento = 0.0;

        // LIMITE FAIXA 1
        if (salarioBruto <= inss.LIMITEFAIXA1){
            return recolhimento = salarioBruto * inss.FAIXA1;
        }

        // LIMITE FAIXA 2
        if (salarioBruto <= inss.LIMITEFAIXA2){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            return recolhimento += (salarioBruto - inss.LIMITEFAIXA1) * inss.FAIXA2;
        }

        // LIMITE FAIXA 3
        if (salarioBruto <= inss.LIMITEFAIXA3){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
            return recolhimento += (salarioBruto - inss.LIMITEFAIXA2) * inss.FAIXA3;
        }

        //LIMITE FAIXA 4
        if (salarioBruto <= inss.LIMITEFAIXA4){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
            recolhimento += (inss.LIMITEFAIXA3-inss.LIMITEFAIXA2) * inss.FAIXA3;
            return recolhimento += (salarioBruto- inss.LIMITEFAIXA3) * inss.FAIXA4;
        }

        recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
        recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
        recolhimento += (inss.LIMITEFAIXA3-inss.LIMITEFAIXA2) * inss.FAIXA3;
        recolhimento += (inss.LIMITEFAIXA4-inss.LIMITEFAIXA3) * inss.FAIXA4;
        return recolhimento;
    }

    public void abrirGrupoEmpregados(){
        try {
            JFrame gerenciarEmpregado = new JFrame();
            gerenciarEmpregado.setContentPane(new GerenciarEmpregadosGUI().getPanelEmpregados());
            gerenciarEmpregado.setSize(500,350);
            gerenciarEmpregado.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarEmpregado(){
        if(validaCampos()){
            ge.adcionarEmpregado(empregado);
            ge.listarTodosEmpregados();
            LimparCampos();
            empregado = new Empregado();
        }
    }

    public void LimparCampos(){
        textFieldCodigoEmpregado.setText("");
        textFieldNomeEmpregado.setText("");
        textFieldSetor.setText("");
        textFieldSalarioBruto.setText("");
        LabelValorRecolhido.setText("0.000,00");
    }

    public boolean validaCampos(){
        if ((empregado.getNomeEmpregado() == null) && (empregado.getSetor()==null)){
            mensagemErro();
            return false;
        }
        if (empregado == null){
            mensagemErro();
            return false;
        }
        return true;
    }

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

    public void mensagemInformativa(){
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
