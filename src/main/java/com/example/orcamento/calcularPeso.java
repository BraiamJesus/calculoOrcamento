package com.example.orcamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class calcularPeso {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora de Peso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(9, 2));

        JTextField larguraField = new JTextField();
        JTextField alturaField = new JTextField();
        JTextField papelField = new JTextField();
        JTextField laminasField = new JTextField();
        JTextField quantidadeField = new JTextField();

        configurarCampoNumerico(larguraField);
        configurarCampoNumerico(alturaField);
        configurarCampoNumerico(papelField);
        configurarCampoNumerico(laminasField);
        configurarCampoNumerico(quantidadeField);

        frame.add(new JLabel("Largura:"));
        frame.add(larguraField);
        frame.add(new JLabel("Altura:"));
        frame.add(alturaField);
        frame.add(new JLabel("Papel:"));
        frame.add(papelField);
        frame.add(new JLabel("Lâminas (Páginas totais):"));
        frame.add(laminasField);
        frame.add(new JLabel("Quantidade:"));
        frame.add(quantidadeField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton calcularButton = new JButton("Calcular");
        buttonPanel.add(calcularButton);

        frame.add(buttonPanel);

        JLabel resultadoLabel = new JLabel("Peso: ");
        JLabel centroLabel = new JLabel("30%: ");

        frame.add(resultadoLabel);
        frame.add(centroLabel);

        calcularButton.addActionListener(e -> {
            try {
                float largura = Float.parseFloat(larguraField.getText().replace(",", "."));
                float altura = Float.parseFloat(alturaField.getText().replace(",", "."));
                float papel = Float.parseFloat(papelField.getText().replace(",", "."));
                int laminas = Integer.parseInt(laminasField.getText());
                float quantidade = Float.parseFloat(quantidadeField.getText().replace(",", "."));

                double peso = largura * altura * papel * laminas / 2 * quantidade * 1.2;
                double porcentagem = peso * 1.3;
                DecimalFormat df = new DecimalFormat("#0.00");
                resultadoLabel.setText("Peso: " + df.format(peso) + " kg");
                centroLabel.setText("30%: " + df.format(porcentagem) + "%");
            } catch (NumberFormatException ex) {
                resultadoLabel.setText("Por favor,Insira os valores corretos");
            }
        });

        resultadoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String peso = resultadoLabel.getText().replace("Peso: ", "");
                copiarTextoParaAreaTransferencia(peso);
            }
        });

        centroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String centro = centroLabel.getText().replace("30%: ", "").replace("%", "");
                copiarTextoParaAreaTransferencia(centro);
            }
        });

        frame.setVisible(true);
    }

    private static void configurarCampoNumerico(JTextField textField) {
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (c == '0' && textField.getText().equals("")) {
                    textField.setText("0.");
                    evt.consume();
                } else if (!Character.isDigit(c) && c != '.' && c != '\b') {
                    evt.consume();
                }
                if (c == '.' && textField.getText().contains(".")) {
                    evt.consume();
                }
            }
        });
    }

    private static void copiarTextoParaAreaTransferencia(String texto) {
        StringSelection selection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(null, "Texto copiado");
    }
}
