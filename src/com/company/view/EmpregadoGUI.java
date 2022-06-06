package com.company.view;

import com.company.helper.message;
import com.company.model.Empregado;
import com.company.model.GerenciarEmpregado;
import com.company.model.ParametrosInss;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpregadoGUI {
    private JTextField textFieldNomeEmpregado; //Campo para inserir o nome do usuário
    private JTextField textFieldCodigoEmpregado; // Campo para inserir o código
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
                    if((empregado!= null) && validaCampos()){
                        GerenciarEmpregado.removerEmpregado(empregado);
                        LimparCampos();
                        message.mensagemDeletado();
                        empregado = new Empregado();
                    }else{
                        message.mensagemUsurioNaoEncontrado();
                    }
                }
                catch (Exception erro){
                    System.out.println(erro);
                }
            }
        });
    }

    public void CalcularRecolhimentoInss() {
        try{
            empregado = new Empregado(); // Cria um novo objeto Empregado
            inss = new ParametrosInss(); // Cria um objeto Inss
            Integer codigo = Integer.parseInt(textFieldCodigoEmpregado.getText()); // Variavel código recebe o que esta no campo de texto textFieldCodigoEmpregado
            String nome = textFieldNomeEmpregado.getText();
            String setor = textFieldSetor.getText();
            Double salarioBruto = Double.parseDouble(textFieldSalarioBruto.getText());
            empregado.setCodigoEmpregado(codigo);
            empregado.setNomeEmpregado(nome);
            empregado.setSetor(setor);
            empregado.setSalarioBruto(salarioBruto);
            empregado.setRecInss(valorRecolhimentoInss(salarioBruto)); //Recebe o valor da função valorRecolhimentoInss e salva em RecInss
            LabelValorRecolhido.setText(String.valueOf(empregado.getRecInss())); //Colocando o valor do atibuto RecInss na label


        }catch (Exception e){
            message.mensagemInformativa();
            System.out.println(e);
        }
    }

    public double valorRecolhimentoInss(double salarioBruto) // Calcula o valor de recolhimento do INSS
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

        //MAIOR QUE A FAIXA 4
        recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
        recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
        recolhimento += (inss.LIMITEFAIXA3-inss.LIMITEFAIXA2) * inss.FAIXA3;
        recolhimento += (inss.LIMITEFAIXA4-inss.LIMITEFAIXA3) * inss.FAIXA4;
        return recolhimento;
    }
    public void abrirGrupoEmpregados(){
        try { //Try catch para caso dê algum erro na lista
            JFrame gerenciarEmpregado = new JFrame(); // Cria um novo objeto do tipo JFrame com o nome gerenciarEmpregado
            gerenciarEmpregado.setContentPane(new GerenciarEmpregadosGUI().getPanelEmpregados()); // Setar o Panel com o getPanelEmpregado
            gerenciarEmpregado.setSize(500,350); // Informar o tamanho da tela
            gerenciarEmpregado.setVisible(true); // Deixar visivel

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarEmpregado(){
        if(validaCampos()){ //Se for verdadeiro segue nessa parte
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
        if (empregado == null){
            message.mensagemErro();
            return false;
        }
        if ((empregado.getNomeEmpregado() == null) && (empregado.getSetor()==null)){
//            message.mensagemErro();
            return false;
        }
        return true;
    }

}
