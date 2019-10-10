package com.wyf.concurrency.chapter2;

public class TaxCalculatorMain {
    
    public static void main(String[] args) {
        //TaxCalculator calculator = new TaxCalculator(10000d,2000d);
        /*TaxCalculator calculator = new TaxCalculator(10000d,2000d){
            @Override
            public double calcuTax() {
                return getSalary()*0.1+getBonus()*0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);*/
        TaxCalculator calculator = new TaxCalculator(10000d,2000d);
        CalculatorStrategy calculatorStrategy = new SimpleCalculatorStrategy();
        calculator.setCalculatorStrategy(calculatorStrategy);
        System.out.println(calculator.calculate());
        
    }
}
