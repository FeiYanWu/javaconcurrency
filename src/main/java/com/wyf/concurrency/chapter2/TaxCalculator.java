
package com.wyf.concurrency.chapter2;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/19 22:35
 * @Version 1.0
 *************************************/
public class TaxCalculator {
    private final double salary;
    private final double bonus;
    
    public TaxCalculator(double salary,double bonus){
        this.salary = salary;
        this.bonus = bonus;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    private CalculatorStrategy calculatorStrategy;
    
    
    protected double calcuTax(){
        //return 0.0d;
        return calculatorStrategy.calculate(salary,bonus);
    }
    
    public double calculate(){
        return this.calcuTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
