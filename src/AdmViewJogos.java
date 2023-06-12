package src;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class AdmViewJogos extends JFrame {

    private JSONObject session;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panel; // Declare panel as an instance variable

    public AdmViewJogos(JSONObject session) {
        this.session = session;
        if (!session.has("name")) {
            JOptionPane optionPane = new JOptionPane("Por favor realize login", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);

            JButton customButton = new JButton("Fechar");

            optionPane.setOptions(new Object[]{customButton});

            // Create a dialog with the option pane
            JDialog dialog = optionPane.createDialog("No Session");

            // Add an action listener to the custom button
            customButton.addActionListener(e -> {
                descartar();
                dialog.dispose();
            });

            // Set the dialog to be modal
            dialog.setModal(true);

            // Set the dialog to be non-resizable
            dialog.setResizable(false);

            // Display the dialog
            dialog.setVisible(true);
        }

        try {
            if (session.has("name")) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1000, 700);
                getContentPane().setBackground(Color.DARK_GRAY);
                setLayout(new BorderLayout());
                JMenuBar menuBar = new JMenuBar();

                // Create "Perfil" menu
                JMenu perfilMenu = new JMenu("Perfil");
                JMenu usuariosMenu = new JMenu("Usuarios");
                // Create "Ver Perfil" menu item
                JMenuItem verPerfilMenuItem = new JMenuItem("Ver Perfil");
                verPerfilMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new PerfilAdm(session).setVisible(true);
                        dispose();
                    }
                });
                perfilMenu.add(verPerfilMenuItem);

                // Create "Ver Usuários" menu item
                JMenuItem verUsuariosMenuItem = new JMenuItem("Ver Usuários");
                verUsuariosMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new AdmViewUser(session).setVisible(true);
                        dispose();
                    }
                });
                JMenuItem registrarJogoMenuItem = new JMenuItem("Registrar Jogo");
                registrarJogoMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new RegistroJogoGUI(session).setVisible(true);
                    }
                });
                perfilMenu.add(registrarJogoMenuItem);
                usuariosMenu.add(verUsuariosMenuItem);

                // Add "Perfil" menu to the menu bar
                menuBar.add(perfilMenu);
                menuBar.add(usuariosMenu);
                // Set the menu bar
                setJMenuBar(menuBar);

                // Create a panel with GridBagLayout to hold the table and delete buttons
                panel = new JPanel(new BorderLayout());
                panel.setBackground(Color.DARK_GRAY);

                // Create a table
                table = new JTable();
                table.setBackground(Color.DARK_GRAY);
                table.setForeground(Color.WHITE);
                JScrollPane scrollPane = new JScrollPane(table);
                panel.add(scrollPane, BorderLayout.CENTER);

                // Create a button panel to hold the delete buttons
                JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
                buttonPanel.setBackground(Color.DARK_GRAY);

                // Add the button panel to the west of the panel
                panel.add(buttonPanel, BorderLayout.WEST);

                // Add the panel to the center of the frame
                add(panel, BorderLayout.CENTER);

                // Load data from JSON file
                loadTableData();

                // Add action listener to the delete buttons
                table.getColumn("Deletar").setCellRenderer((TableCellRenderer) new ButtonRenderer());
                table.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox()));

                setVisible(true);
                setLocationRelativeTo(null);
            } else {
                throw new MyCustomException("Session undefined");
            }
        } catch (MyCustomException e) {
            System.out.println(e.getMessage());
            descartar();
        }
    }

    private void loadTableData() {
        // Read data from JSON file and populate the table
        try {
            String jsonFileContent = new String(Files.readAllBytes(Paths.get("src/games.json")));
            JSONArray jsonArray = new JSONArray(jsonFileContent);

            // Create table model with column names
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Name");
            columnNames.add("Description");
            columnNames.add("Price");
            columnNames.add("Deletar");
            tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 0 && column != 1 && column != 2;
                }
            };

            // Populate table model with data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                double price = jsonObject.getDouble("price");
                tableModel.addRow(new Object[]{name, description, price, "Deletar"});
            }

            // Set the table model
            table.setModel(tableModel);

            // Set the cell renderer for the table cells
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    cellComponent.setBackground(Color.DARK_GRAY);
                    cellComponent.setForeground(Color.WHITE);
                    return cellComponent;
                }
            });

            // Set the background color of the table header
            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setBackground(Color.DARK_GRAY);
            tableHeader.setForeground(Color.WHITE);

            // Set the background color of the scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBackground(Color.DARK_GRAY);
            scrollPane.getViewport().setBackground(Color.DARK_GRAY);

            // Add the scroll pane to the panel
            panel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void descartar() {
        new PerfilAdm(session);
        dispose();
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (clicked) {
                // Perform deletar action
                int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar este jogo?", "Confirmar Deletar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete the row from the table model
                    tableModel.removeRow(table.getSelectedRow());
                    // Write the updated data back to the JSON file
                    writeTableDataToJson();
                }
            }
            clicked = false;
            return new String(label);
        }

        private void writeTableDataToJson() {
            // Write the updated data back to the JSON file
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", tableModel.getValueAt(i, 0));
                jsonObject.put("description", tableModel.getValueAt(i, 1));
                jsonObject.put("price", tableModel.getValueAt(i, 2));
                jsonArray.put(jsonObject);
            }
            try {
                Files.write(Paths.get("src/games.json"), jsonArray.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JSONObject session = new JSONObject();
                session.put("name", "admin");
                new AdmViewJogos(session);
            }
        });
    }
}
