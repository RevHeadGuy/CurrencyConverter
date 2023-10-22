import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame {
    private final JTextField amountField;
    private final JComboBox<String> fromCurrency;
    private final JComboBox<String> toCurrency;
    private final JButton convertButton;
    private final JLabel resultLabel;

    private final double[] exchangeRates = {
        1.0, // USD to USD (1:1)
        0.85, // USD to EUR
        110.96, // USD to JPY
        0.74, // USD to GBP
        1.28, // USD to CAD
        83.34 // USD to Rs
    };

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Amount"));
        amountField = new JTextField(10);
        panel.add(amountField);

        panel.add(new JLabel("From Currency"));
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "GBP", "CAD","Rs"});
        panel.add(fromCurrency);

        panel.add(new JLabel("To Currency"));
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "GBP", "CAD","Rs"});
        panel.add(toCurrency);

        convertButton = new JButton("Convert");
        resultLabel = new JLabel("");
        panel.add(convertButton);
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(panel);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();
            double convertedAmount = amount / exchangeRates[fromIndex] * exchangeRates[toIndex];
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            resultLabel.setText(decimalFormat.format(convertedAmount) + " " + toCurrency.getSelectedItem());
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
