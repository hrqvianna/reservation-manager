import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private ReservationsManager manager;

    public Main() {
        this.manager = new ReservationsManager();
        createGUI();
    }

    public void createGUI() {
        // Criação da janela principal
        JFrame frame = new JFrame("Sistema de Gestão de Reservas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Criação do painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        frame.add(panel, BorderLayout.CENTER);

        // Botões
        JButton addReserveButton = new JButton("Adicionar Reserva");
        JButton cancelReserveButton = new JButton("Cancelar Reserva");
        JButton listReserveButton = new JButton("Listar Reservas");
        JButton exitButton = new JButton("Sair");

        // Adiciona os botões ao painel
        panel.add(addReserveButton);
        panel.add(cancelReserveButton);
        panel.add(listReserveButton);
        panel.add(exitButton);

        // Listener para o botão "Adicionar Reserva"
        addReserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("Adicionar Reserva");
                addFrame.setSize(300, 300);
                addFrame.setLayout(new GridLayout(10, 2));

                JLabel nameLabel = new JLabel("Nome:");
                JTextField nameField = new JTextField();

                JLabel dateLabel = new JLabel("Data (dd/mm/aaaa):");
                JTextField dateField = new JTextField();
                JLabel dateErrorLabel = new JLabel();
                dateErrorLabel.setForeground(Color.RED);

                JLabel shiftLabel = new JLabel("Turno (tarde/noite):");
                JTextField shiftField = new JTextField();
                JLabel shiftErrorLabel = new JLabel();
                shiftErrorLabel.setForeground(Color.RED);

                JButton confirmButton = new JButton("Confirmar");

                addFrame.add(nameLabel);
                addFrame.add(nameField);
                addFrame.add(dateLabel);
                addFrame.add(dateField);
                addFrame.add(dateErrorLabel);
                addFrame.add(shiftLabel);
                addFrame.add(shiftField);
                addFrame.add(shiftErrorLabel);
                addFrame.add(confirmButton);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String date = dateField.getText();
                        String shift = shiftField.getText();

                        boolean valid = true;

                        // Validação da data
                        if (!manager.isValidDate(date)) {
                            dateErrorLabel.setText("Data inválida. Tente novamente.");
                            dateField.setText("");  // Limpa o campo da data
                            valid = false;
                        } else {
                            dateErrorLabel.setText("");  // Limpa o erro se estiver correto
                        }

                        // Validação do turno
                        if (!shift.equalsIgnoreCase("tarde") && !shift.equalsIgnoreCase("noite")) {
                            shiftErrorLabel.setText("Turno inválido. Deve ser 'tarde' ou 'noite'.");
                            shiftField.setText("");  // Limpa o campo do turno
                            valid = false;
                        } else {
                            shiftErrorLabel.setText("");  // Limpa o erro se estiver correto
                        }

                        if (valid) {
                            Reserve reserve = new Reserve(name, date, shift);
                            String result = manager.addReserve(reserve);
                            JOptionPane.showMessageDialog(addFrame, result);
                            addFrame.dispose();  // Fecha a janela após a confirmação
                        }
                    }
                });

                addFrame.setVisible(true);
            }
        });

        // Listener para o botão "Cancelar Reserva"
        cancelReserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame cancelFrame = new JFrame("Cancelar Reserva");
                cancelFrame.setSize(300, 300);
                cancelFrame.setLayout(new GridLayout(10, 2));

                JLabel nameLabel = new JLabel("Nome:");
                JTextField nameField = new JTextField();

                JLabel dateLabel = new JLabel("Data (dd/mm/aaaa):");
                JTextField dateField = new JTextField();
                JLabel dateErrorLabel = new JLabel();
                dateErrorLabel.setForeground(Color.RED);

                JLabel shiftLabel = new JLabel("Turno (tarde/noite):");
                JTextField shiftField = new JTextField();
                JLabel shiftErrorLabel = new JLabel();
                shiftErrorLabel.setForeground(Color.RED);

                JButton confirmButton = new JButton("Confirmar");

                cancelFrame.add(nameLabel);
                cancelFrame.add(nameField);
                cancelFrame.add(dateLabel);
                cancelFrame.add(dateField);
                cancelFrame.add(dateErrorLabel);
                cancelFrame.add(shiftLabel);
                cancelFrame.add(shiftField);
                cancelFrame.add(shiftErrorLabel);
                cancelFrame.add(confirmButton);

                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String date = dateField.getText();
                        String shift = shiftField.getText();

                        boolean valid = true;

                        // Validação da data
                        if (!manager.isValidDate(date)) {
                            dateErrorLabel.setText("Data inválida. Tente novamente.");
                            dateField.setText("");  // Limpa o campo da data
                            valid = false;
                        } else {
                            dateErrorLabel.setText("");  // Limpa o erro se estiver correto
                        }

                        // Validação do turno
                        if (!shift.equalsIgnoreCase("tarde") && !shift.equalsIgnoreCase("noite")) {
                            shiftErrorLabel.setText("Turno inválido. Deve ser 'tarde' ou 'noite'.");
                            shiftField.setText("");  // Limpa o campo do turno
                            valid = false;
                        } else {
                            shiftErrorLabel.setText("");  // Limpa o erro se estiver correto
                        }

                        if (valid) {
                            Reserve reserve = new Reserve(name, date, shift);
                            String result = manager.cancelReserve(reserve);
                            JOptionPane.showMessageDialog(cancelFrame, result);
                            cancelFrame.dispose();  // Fecha a janela após a confirmação
                        }
                    }
                });

                cancelFrame.setVisible(true);
            }
        });

        // Listener para o botão "Listar Reservas"
        listReserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Reserve> reserves = manager.listReserves();
                if (reserves.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não há reservas.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Reserve r : reserves) {
                        sb.append(r.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            }
        });

        // Listener para o botão "Sair"
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Torna a janela visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
