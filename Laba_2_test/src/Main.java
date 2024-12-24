import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Создаем основное окно
        JFrame frame = new JFrame("Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200); // Размер окна

        // Создаем кнопку
        JButton button = new JButton("нажми на меня ");

        // Добавляем обработчик события для кнопки
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создаем новое окно
                JFrame newWindow = new JFrame("Image Window");
                newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newWindow.setSize(2000, 1000); // Размер нового окна

                // Загружаем картинку
                ImageIcon imageIcon = new ImageIcon("D:\\wallpaper\\cute.jpg");
                // D:\обоиУкажите путь к картинке

                // Добавляем картинку в JLabel
                JLabel label = new JLabel(imageIcon);
                newWindow.add(label); // Добавляем JLabel в окно

                // Отображаем новое окно
                newWindow.setVisible(true);
            }
        });

        // Размещаем кнопку на главном окне
        frame.getContentPane().add(button, BorderLayout.CENTER);

        // Отображаем основное окно
        frame.setVisible(true);
    }
}
