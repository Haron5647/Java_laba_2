import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;


public class MainFrame extends JFrame {


    Formula sum = new Formula();

    private static final int WIDTH = 500;
    private static final int HEIGHT = 420;


    Box mainBox = Box.createVerticalBox();
    Box box_radio_button = Box.createHorizontalBox();
    Box box_variable = Box.createHorizontalBox();
    Box box_result = Box.createHorizontalBox();
    Box box_sum = Box.createHorizontalBox();
    Box box_button = Box.createHorizontalBox();


    private ButtonGroup radioButtons = new ButtonGroup();
    private int formulaId = 1;

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        box_radio_button.add(button);
    }


    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldSum;


    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.sin(Math.sin(y) + Math.exp(Math.cos(y)) + Math.pow(z,2)) * Math.pow((Math.sin(Math.PI*y*y) +Math.log(x*x) ) ,0.25);
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return Math.atan(Math.pow(z,1/x))/(y*y + z*Math.sin(Math.log(x)));
    }


    public MainFrame() {
        super("калькулятор чего-то");
        this.setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit(); // Отцентрировать окно приложения на экране
        this.setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // 1
        box_radio_button.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        box_radio_button.add(Box.createHorizontalGlue());


        //2

        JLabel xlabel = new JLabel("X:");
        JLabel ylabel = new JLabel("Y:");
        JLabel zlabel = new JLabel("Z:");
        textFieldX = new JTextField("0",10);
        textFieldY = new JTextField("0",10);
        textFieldZ = new JTextField("0",10);

        box_variable.add(Box.createHorizontalGlue());


        box_variable.add(xlabel);
        box_variable.add(Box.createHorizontalStrut(10));
        textFieldX.setMaximumSize( textFieldX.getPreferredSize());
        box_variable.add(textFieldX);
        box_variable.add(Box.createHorizontalStrut(50));


        box_variable.add(ylabel);
        box_variable.add(Box.createHorizontalStrut(10));
        textFieldY.setMaximumSize( textFieldY.getPreferredSize());
        box_variable.add(textFieldY);
        box_variable.add(Box.createHorizontalStrut(50));



        box_variable.add(zlabel);
        box_variable.add(Box.createHorizontalStrut(10));
        textFieldZ.setMaximumSize( textFieldZ.getPreferredSize());
        box_variable.add(textFieldZ);
        box_variable.add(Box.createHorizontalGlue());


        //3
        JLabel resultLabel = new JLabel("Результат:");
        textFieldResult = new JTextField("0",20);
        textFieldResult.setMaximumSize( textFieldResult.getPreferredSize());
        box_result.add(Box.createHorizontalGlue());
        box_result.add(resultLabel);
        box_result.add(Box.createHorizontalStrut(10));
        box_result.add(textFieldResult);
        box_result.add(Box.createHorizontalGlue());

        // бокс для суммы
        JLabel SumLabel = new JLabel("Сумма:");
        textFieldSum = new JTextField("0",20);
        textFieldSum.setMaximumSize( textFieldSum.getPreferredSize());
        box_sum.add(Box.createHorizontalGlue());
        box_sum.add(SumLabel);
        box_sum.add(Box.createHorizontalStrut(13));
        box_sum.add(textFieldSum);
        box_sum.add(Box.createHorizontalGlue());




        //4
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    // Считываем данные
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());

                    // Вычисляем результат
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);

                    // Отображаем результат
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    // Обрабатываем ошибку
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                sum.clearMemory();
                Double result  = sum.getSum();
                textFieldSum.setText(result.toString());
            }
        });

        JButton Mplus = new JButton("M+");
        Mplus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                double current = Double.parseDouble(textFieldResult.getText());
                sum.add(current);
                Double result  = sum.getSum();
                textFieldSum.setText(result.toString());
            }
        });



        box_button.add(Box.createHorizontalStrut(7));
        box_button.add(buttonCalc);
        box_button.add(Box.createHorizontalStrut(40));
        box_button.add(buttonReset);
        box_button.add(Box.createHorizontalGlue());
        box_button.add(MC);
        box_button.add(Box.createHorizontalStrut(40));
        box_button.add(Mplus);
        box_button.add(Box.createHorizontalStrut(7));





        //6

        mainBox.add(Box.createVerticalGlue());
        mainBox.add(box_radio_button);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box_variable);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box_result);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box_sum);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box_button);
        mainBox.add(Box.createVerticalGlue());
        getContentPane().add(mainBox, BorderLayout.CENTER);
        this.setVisible(true);

















//        Box firsBox = Box.createHorizontalBox();
//        Box secondBox = Box.createVerticalBox();
//        JLabel firslabel = new JLabel("идите на хуй ");
//        JLabel secondlabel = new JLabel("сударь несоблаговолите ли вы пойти на причинное место того барина");
//        firsBox.add(Box.createHorizontalGlue());
//        firsBox.add(firslabel);
//        firsBox.add(Box.createHorizontalGlue());
//
//        secondBox.add(Box.createHorizontalGlue());
//        secondBox.add(secondlabel);
//        secondBox.add(Box.createHorizontalGlue());
//
//
//        mainBox.add(firsBox);
//        mainBox.add(Box.createVerticalStrut(10));
//        mainBox.add(secondBox);
//        getContentPane().add(mainBox, BorderLayout.CENTER);

    }
}
