import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InterfaceCifraCesar extends JFrame {

    private JTextArea textoEntrada;
    private JTextArea textoSaida;
    private JTextField campoChave;

    public InterfaceCifraCesar() {

        setTitle("Cifra de César");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(15, 15));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        painelPrincipal.setBackground(new Color(45, 45, 45));

        JLabel titulo = new JLabel("CRIPTOGRAFIA - CIFRA DE CÉSAR");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelControles = new JPanel();
        painelControles.setBackground(new Color(45, 45, 45));

        JLabel labelChave = new JLabel("Chave:");
        labelChave.setForeground(Color.WHITE);
        labelChave.setFont(new Font("Arial", Font.BOLD, 16));

        campoChave = new JTextField(5);
        campoChave.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnCriptografar = new JButton("Criptografar");
        JButton btnDescriptografar = new JButton("Descriptografar");

        estilizarBotao(btnCriptografar);
        estilizarBotao(btnDescriptografar);

        painelControles.add(labelChave);
        painelControles.add(campoChave);
        painelControles.add(btnCriptografar);
        painelControles.add(btnDescriptografar);

        textoEntrada = new JTextArea();
        textoEntrada.setLineWrap(true);
        textoEntrada.setWrapStyleWord(true);
        textoEntrada.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane scrollEntrada = new JScrollPane(textoEntrada);
        scrollEntrada.setBorder(
                BorderFactory.createTitledBorder("Texto de Entrada")
        );

        textoSaida = new JTextArea();
        textoSaida.setLineWrap(true);
        textoSaida.setWrapStyleWord(true);
        textoSaida.setEditable(false);
        textoSaida.setFont(new Font("Consolas", Font.PLAIN, 16));

        JScrollPane scrollSaida = new JScrollPane(textoSaida);
        scrollSaida.setBorder(
                BorderFactory.createTitledBorder("Resultado")
        );

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new GridLayout(2, 1, 10, 10));
        painelCentro.add(scrollEntrada);
        painelCentro.add(scrollSaida);

        btnCriptografar.addActionListener(e -> {

            try {

                int chave = Integer.parseInt(campoChave.getText());

                String resultado = criptografar(
                        textoEntrada.getText(),
                        chave
                );

                textoSaida.setText(resultado);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas números na chave!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnDescriptografar.addActionListener(e -> {

            try {

                int chave = Integer.parseInt(campoChave.getText());

                String resultado = descriptografar(
                        textoEntrada.getText(),
                        chave
                );

                textoSaida.setText(resultado);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas números na chave!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        painelPrincipal.add(titulo, BorderLayout.NORTH);
        painelPrincipal.add(painelControles, BorderLayout.CENTER);
        painelPrincipal.add(painelCentro, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void estilizarBotao(JButton botao) {

        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(70, 130, 180));
        botao.setForeground(Color.WHITE);
    }

    // Código para criptografia
    public static String criptografar(String texto, int chave) {

        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String resultado = "";

        texto = texto.toUpperCase();

        for (int i = 0; i < texto.length(); i++) {

            char caractere = texto.charAt(i);

            int posicao = alfabeto.indexOf(caractere);

            if (posicao == -1) {

                resultado += caractere;

            } else {

                int novaPosicao = (posicao + chave) % 26;

                resultado += alfabeto.charAt(novaPosicao);
            }
        }

        return resultado;
    }

    // Código para descriptografar
    public static String descriptografar(String texto, int chave) {

        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String resultado = "";

        texto = texto.toUpperCase();

        for (int i = 0; i < texto.length(); i++) {

            char caractere = texto.charAt(i);

            int posicao = alfabeto.indexOf(caractere);

            if (posicao == -1) {

                resultado += caractere;

            } else {

                int novaPosicao = (posicao - chave + 26) % 26;

                resultado += alfabeto.charAt(novaPosicao);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            InterfaceCifraCesar tela =
                    new InterfaceCifraCesar();

            tela.setVisible(true);
        });
    }
}