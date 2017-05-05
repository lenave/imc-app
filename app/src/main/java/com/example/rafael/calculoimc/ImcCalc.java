package com.example.rafael.calculoimc;


public class ImcCalc {
    private double userHeight;
    private double userWeigth;
    private String userName;
    private String userAge;
    private double imcResult;

    public ImcCalc(double txtHeightC, double txtWeightC, String userName, String userAge) {
        this.userHeight = txtHeightC;
        this.userWeigth = txtWeightC;
        this.userName = userName;
        this.userAge = userAge;

        this.imcResult = this.calc();
    }

    private double calc() {
        double result = this.userWeigth / (this.userHeight * this.userHeight);

        return result;
    }

    public String getMsg() {
        double imc = this.imcResult;
        String msg;
        int userAge = Integer.parseInt(this.userAge);
        String userName = this.userName;

        if (imc < 17) {
            msg = "Você está muito abaixo do peso, "+userName+"";
            if (userAge <= 20) {
                msg += ". Tu ainda é "+userName+", pode comer mais!";
            } else if (userAge <= 30) {
                msg += ". Sei que você não é mais tão jovem, mas pode comer mais!";
            } else {
                msg += ". Mesmo sendo meio velho você ainda pode comer. Está muito magro!";
            }
        } else if (imc >= 17 && imc < 18.5) {
            msg = "Você está abaixo do peso, " + userName;
            if (userAge <= 20) {
                msg += ". Tu ainda é jovem, pode comer mais!";
            } else if (userAge <= 30) {
                msg += ". Sei que você não é mais tão jovem, mas pode comer mais!";
            } else {
                msg += ". Mesmo sendo meio velho você ainda pode comer.";
            }
        } else if (imc >= 18.5 && imc < 25) {
            msg = "Parabéns "+userName+"! Você está no seu peso ideal";
            if (userAge <= 20) {
                msg += ". Mesmo sendo jovem, ou seja, mais fácil manter o peso, ta de parabéns.";
            } else if (userAge <= 30) {
                msg += ". Você é o cara!";
            } else {
                msg += ". Uau, ta de parabéns mesmo, não é mais aquele mocinho mas ainda ta em forma.";
            }
        } else if (imc >= 25 && imc < 30) {
            msg = "Cuidado, "+userName+"! Você está acima do peso";
            if (userAge <= 20) {
                msg += ". Tu ainda é jovem, emagrece rápido.";
            } else if (userAge <= 30) {
                msg += ". Vai caminhar um pouco, ainda dá tempo.";
            } else {
                msg += ". Sei que nessa idade já é difícil perder peso, mas vamos fazer uma força.";
            }
        } else if (imc >= 30 && imc < 35) {

            msg = "Eita! Você está com obesidade nível 1, " + userName;
            if (userAge <= 20) {
                msg += ". A situação ta um pouquinho ruim, mas você ainda é jovem.";
            } else if (userAge <= 30) {
                msg += ". Talvez só uma caminha não faça efeito, que tal uma academia?";
            } else {
                msg += ". Melhor começar uma academia... Não se esqueça da esteira!";
            }
        } else if (imc >= 35 && imc < 40) {

            msg = "Nossa! Você está com obesidade nível 2, " + userName;
            if (userAge <= 20) {
                msg += ". Toma jeito rapaz, vai fazer alguma coisa para emagrecer!";
            } else if (userAge <= 30) {
                msg += ". A situação não ta boa né? Que tal academia e algum esporte?";
            } else {
                msg += ". Só academia e aquele futebol não vai adiantar. Vai ter que fechar a boca!";
            }
        } else {

            msg = "Vai procurar um especialista, "+userName+"! Você está com obesidade nível 3";
            if (userAge <= 20) {
                msg += ". Seus pais deviam procurar um médico, você pode ter consequências no futuro.";
            } else if (userAge <= 30) {
                msg += ". A situação tá horrível. Procure um especialista!!";
            } else {
                msg += ". VAI AGORA PROCURAR UM ESPECIALISTA OU VOCÊ VAI MORRER!!!!";
            }
        }

        return msg;
    }

    public double getImc() {

        return this.imcResult;
    }
}
