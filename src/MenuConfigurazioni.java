import javax.swing.*;
import java.awt.*;

public class MenuConfigurazioni extends JPanel {

    private JList<String> optionList;

    public MenuConfigurazioni() {
        // Imposta il layout per il pannello
        setLayout(new BorderLayout());

        // Crea la lista delle opzioni
        optionList = new JList<>(Configurazione.valuesToString(null));
        optionList.setCellRenderer(new ListRenderer());

        // Imposta il font per la lista delle opzioni
        Font font = new Font("Arial", Font.PLAIN, 16); // Modifica la dimensione del font qui
        optionList.setFont(font);

        // Crea uno scroll pane per la lista delle opzioni
        JScrollPane scrollPane = new JScrollPane(optionList);

        // Imposta le preferenze dello scroll pane
        scrollPane.setPreferredSize(new Dimension(Griglia.FRAME_WIDTH -Griglia.FRAME_HEIGHT, Griglia.FRAME_HEIGHT));

        // Aggiungi lo scroll pane al pannello
        add(scrollPane, BorderLayout.CENTER);
    }

    // Metodo per ottenere l'opzione selezionata
    public String getSelectedOption() {
        String nome = optionList.getSelectedValue();
        if (nome == null) return null;
        return nome;
    }

    // Renderer personalizzato per la JList
    private class ListRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Formatta il testo per visualizzare i sottogruppi in grassetto
            if (value != null && value.toString().startsWith("Still Lifes") ) {
                label.setFont(label.getFont().deriveFont(Font.BOLD));
            }

            return label;
        }
    }

}
