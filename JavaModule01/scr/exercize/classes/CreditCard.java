package exercize.classes;

import exercize.interfaces.FinancePull;


public class CreditCard implements FinancePull {
    private final int creditLimit;
    private final int trialTime;
    private boolean isUntoched;
    private int currDebt;
    private int freeMoney;

    public CreditCard(int creditLimit, int trialTime){
        this.creditLimit = creditLimit;
        this.trialTime = trialTime;
        this.currDebt = 0;
        this.freeMoney = 0;
        this.isUntoched = true;
    }

    public int withdrawal(int summ){
        if(summ <= 0){
            System.out.println("Можно снять только более, чем 0 рублей");
            return 1;
        } else if (summ > getBalance()) {
            System.out.println("Можно снять только менее, чем " + getBalance() + " рублей");
            return 1;
        } else if ((this.currDebt + (summ - this.freeMoney)) > this.creditLimit) {
            System.out.println("Вы пытаетесь снять слишком много. Доступно для снятия " + getBalance() + " рублей");
            return 1;
        } else {
            int fr = summ - this.freeMoney;
            if (fr <= 0) {
                this.freeMoney = fr * -1;
            }else{
                this.isUntoched = false;
                this.freeMoney = 0;
                this.currDebt = this.currDebt + fr;
            }
            System.out.println("Вы сняли с карты " + summ + " Доступно для снятия " + getBalance() + " рублей");
            return 0;
        }
    };
    public void repayment(int summ){
        if(summ > 0){
            System.out.println("Вы внесли " + summ + " рублей");
            this.currDebt = this.currDebt - summ;
            if (this.currDebt <= 0) {
                this.freeMoney = freeMoney + (currDebt * -1);
                this.currDebt = 0;
                if (!this.isUntoched) {
                    System.out.println("Задолженность погашена");
                    this.isUntoched = true;
                }
            }
        }else{
            System.out.println("Спасибо, что внесли 0 рублей");
        }
    };

    public boolean isUntoched() {
        if (this.isUntoched) {
            System.out.println("Льготный период не начат");
        } else {
            System.out.println("Льготный период начат");
        }
        return isUntoched;
    }

    public int getTrialTime() {
        return trialTime;
    }

    public int getCurrDebt() {
        return currDebt;
    }
    public int getFreeMoney() {
        return freeMoney;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public int getBalance() {
        return (this.creditLimit - this.currDebt + this.freeMoney);
    }

    public void getInfo(){
        System.out.println("\nОбщая информация по карте\n");
        System.out.println("Кредитный лимит составляет " + this.creditLimit + " рублей");
        System.out.println("Льготный период составляет " + this.trialTime + " дней");
        if(this.freeMoney > 0){
            System.out.println("Сейчас на карте " + this.freeMoney + " ваших не обложенных кредитом рублей");
            System.out.println("Общий баланс карты составляет " + (this.creditLimit - this.currDebt + this.freeMoney) + " рублей");
        }
        if(this.isUntoched) {
            this.isUntoched();
        } else {
            System.out.println("\nИнформация по кредиту\n");
            this.isUntoched();
            System.out.println("Текущая заложенность составляет " + this.currDebt + " рублей");
            System.out.println("Баланс карты составляет " + (this.creditLimit - this.currDebt + this.freeMoney) + " рублей");
        }
    }
}
