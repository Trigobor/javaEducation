package exercize;

import exercize.classes.CreditCard;

public class main {
    public static void main(String[] args) {
        CreditCard raiffeisen = new CreditCard(200000, 110);
        raiffeisen.getInfo();

        System.out.println("----------------------------------------------------------------");
        raiffeisen.withdrawal(300000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.repayment(100000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.withdrawal(50000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.withdrawal(200000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.withdrawal(50000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.repayment(50000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.repayment(100000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.withdrawal(50000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
        raiffeisen.repayment(100000);
        raiffeisen.getInfo();
        System.out.println("----------------------------------------------------------------");
    }
}
