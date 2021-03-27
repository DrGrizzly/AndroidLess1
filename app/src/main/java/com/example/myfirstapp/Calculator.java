package com.example.myfirstapp;

import android.widget.Toast;

public class Calculator {
    private StringBuilder inputNumber;
    private StringBuilder operation;
    private boolean newValue;   //Флаг ввода первого значения
    private boolean funcSet;    //Флаг выбора функции
    final int NUMBER_ARR_MAX_VAL = 100; //100 - Количество сохраненных значений
    double numberArr[] = new double[NUMBER_ARR_MAX_VAL];
    int numberIdx;              //Итератор сохраненных значений в массиве

    Calculator() {
        inputNumber = new StringBuilder("0");
        operation = new StringBuilder();
        newValue = true; //Подняли флаг ввода нового значения
        funcSet = false; //Запрещаем нажатие функций
        numberIdx = 0; //Индекс сохраненных чисел
    }

    /**
     * Обработка ввода чисел
     *
     * @param val
     */
    public void setInputNumber(String val) {
        if (inputNumber.length() > 10) return; //Ограничем количество знаков ввода

        if (".".equals(val)) {
            if (inputNumber.indexOf(".") > 0) return; //Если уже есть точка, то выходим
        }

        if (newValue) {
            inputNumber = new StringBuilder();
            newValue = false;   //Сняли флаг ввода нового значения
        }

        inputNumber.append(val);
        funcSet = true; //Разрешаем нажать функциональную кнопку
    }

    /**
     * Очищает весь стек данных
     */
    public void clearAll() {
        inputNumber = new StringBuilder("0");
        operation = new StringBuilder();
        numberArr = new double[NUMBER_ARR_MAX_VAL]; //Сбросим массив сохраненных значений
        numberIdx = 0; //Сбросим индекс массива сохраненных значений
        newValue = true;
        funcSet = false;
    }

    /**
     * Очищает текущее введенное значение
     */
    public void clearVal() {
        inputNumber = new StringBuilder("0");
        newValue = true;
        funcSet = false;
    }

    /**
     * Выводит результат
     *
     * @return
     */
    public String getInputNumber() {
        return inputNumber.toString();
    }

    /**
     * Сохраняет в стек данных полученное значение
     */
    public void saveVal() {
        try {
            validateNumber(); //Приведем в норму полученное значение
            numberArr[numberIdx] = Double.parseDouble(inputNumber.toString());
            numberIdx++;
            clearVal();
        } catch (Exception ex) {
            clearAll(); //Сбросим все
        }
    }

    /**
     * Устанавливает операцию над числами
     * Варианты: + - / *
     */
    public void setFunc(String func) {
        //Если не разрешено нажатия функции
        if (!funcSet) return;
        saveVal();        //Сохраним в стек введенное значение
        operation.append(func);
        funcSet = false;  //Запрещаем повторное нажатие функциональной кнопки
    }

    /**
     * Корректирует значение
     */
    private void validateNumber() {
        //Проверим, что точка не находится в конце строки
        if (inputNumber.lastIndexOf(".") == inputNumber.length() - 1) {
            inputNumber.deleteCharAt(inputNumber.length() - 1);
        }
    }

    /**
     * Удаляет последнее число в строке
     */
    public void funcBack() {
        if (inputNumber.length() > 1) {
            inputNumber.deleteCharAt(inputNumber.length() - 1);
        } else {
            clearVal();
        }
    }

    public void calcResult() {
        if (!funcSet) return;
        saveVal();
        if (numberIdx <= 1) {
            clearAll();
            return; //Выходим, если у нас не достаточно значений
        }
        //Положим первое значение
        double resultCalc = numberArr[0];
        for (int i = 0; i < numberIdx - 1; i++) {
            if (operation.charAt(i) == '+')
                resultCalc = resultCalc + numberArr[i + 1];
            if (operation.charAt(i) == '-')
                resultCalc = resultCalc - numberArr[i + 1];
            if (operation.charAt(i) == '*')
                resultCalc = resultCalc * numberArr[i + 1];
            if (operation.charAt(i) == '/')
                try {
                    resultCalc = resultCalc / numberArr[i + 1];
                } catch (Exception ex) {
                    clearAll(); //Если не смогли поделить - то очистим все
                }
        }
        clearAll();
        inputNumber = new StringBuilder(String.valueOf(resultCalc));
        setInputNumber(inputNumber.toString());
    }

}
