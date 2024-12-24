public class Formula {
    private Double sum = 0.0;  // Переменная для накопления суммы

    // Метод для обнуления суммы
    public void clearMemory() {
        sum = 0.0;
    }

    // Метод для добавления текущего значения к накопленной сумме
    public void add(Double value) {
        sum += value;
    }

    // Метод для получения текущего значения суммы
    public Double getSum() {
        return sum;
    }
}
