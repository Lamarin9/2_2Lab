// Определение функции main, точка входа в программу
fun main() {
    val alphabet = arrayOf( // Создание массива символов, представляющих алфавит
        'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
        'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    )

    println("Введите текст:") // Печать сообщения о вводе текста
    val text = readLine()?.toLowerCase() ?: return // Считывание введенного текста

    println("Введите ключевое слово:") // Печать сообщения о вводе ключевого слова
    val keyword = readLine()?.toLowerCase() ?: return // Считывание введенного ключевого слова

    println("Выберите действие:") // Печать сообщения о выборе действия
    println("1. Зашифровать")
    println("2. Расшифровать")
    val choice = readLine()?.toIntOrNull() ?: return // Считывание выбора действия

// Выбор действия в зависимости от введенного числа
    val result = when (choice) {
        1 -> encryptText(text, keyword, alphabet) // Вызов функции шифрования текста
        2 -> decryptText(text, keyword, alphabet) // Вызов функции расшифрования текста
        else -> "Неверный выбор действия" // Вывод сообщения об ошибке при неверном выборе
    }

    println("Результат: $result") // Печать результата
}

// Функция для шифрования текста
fun encryptText(text: String, keyword: String, alphabet: Array<Char>): String {
    val encryptedText = StringBuilder() // Создание объекта для хранения зашифрованного текста
    var keywordIndex = 0 // Инициализация индекса ключевого слова

// Итерация по каждому символу в тексте
    for (char in text) {
        val index = alphabet.indexOf(char) // Получение индекса символа в алфавите
        if (index >= 0) { // Если символ присутствует в алфавите
            val shiftedIndex = (index + getShiftByIndex(keyword[keywordIndex], alphabet.size)) % alphabet.size // Вычисление сдвинутого индекса
            encryptedText.append(alphabet[shiftedIndex]) // Добавление зашифрованного символа в результирующий текст
            keywordIndex = (keywordIndex + 1) % keyword.length // Обновление индекса ключевого слова
        } else {
            encryptedText.append(char) // Добавление символа без изменений, если он не содержится в алфавите
        }
    }

    return encryptedText.toString() // Возврат зашифрованного текста
}

// Функция для расшифрования текста
fun decryptText(text: String, keyword: String, alphabet: Array<Char>): String {
    val decryptedText = StringBuilder() // Создание объекта для хранения расшифрованного текста
    var keywordIndex = 0 // Инициализация индекса ключевого слова

// Итерация по каждому символу в тексте
    for (char in text) {
        val index = alphabet.indexOf(char) // Получение индекса символа в алфавите
        if (index >= 0) { // Если символ присутствует в алфавите
            val shiftedIndex = (index - getShiftByIndex(keyword[keywordIndex], alphabet.size) + alphabet.size) % alphabet.size // Вычисление сдвинутого индекса
            decryptedText.append(alphabet[shiftedIndex]) // Добавление расшифрованного символа в результирующий текст
            keywordIndex = (keywordIndex + 1) % keyword.length // Обновление индекса ключевого слова
        } else {
            decryptedText.append(char) // Добавление символа без изменений, если он не содержится в алфавите
        }
    }

    return decryptedText.toString() // Возврат расшифрованного текста
}

// Функция для вычисления сдвига по индексу
fun getShiftByIndex(char: Char, alphabetSize: Int): Int {
    return (char.toInt() - 'а'.toInt()) % alphabetSize // Вычисление сдвига путем вычитания кода символа 'а' и взятия остатка от деления на размер алфавита
}