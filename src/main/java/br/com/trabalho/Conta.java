package br.com.trabalho;

public class Conta {
    
    private double saldo;

    // Construtor: Adicionando validação
    public Conta(double saldoInicial) {
        if (saldoInicial < 0) {
            // Se o valor inicial for negativo, força o saldo para zero.
            this.saldo = 0.0;
        } else {
            this.saldo = saldoInicial;
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {

        if (valor <= 0) {
            return false; // Não permite saque de zero ou negativo
        }
        
        if (valor > saldo) {
            return false; // Não permite saque se o saldo for insuficiente
        }

        this.saldo -= valor;
        return true; // Saque realizado com sucesso
    }
}