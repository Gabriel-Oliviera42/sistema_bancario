package br.com.trabalho;

public class Conta {
    
    private double saldo;

    private static final double LIMITE_MAXIMO_DEPOSITO = 5000.0;

    // Construtor: Adicionando validação
    public Conta(double saldoInicial) {
        if (saldoInicial < 0) {
            // Se o valor inicial for negativo, força o saldo para zero.
            this.saldo = 0.0;
        } else {
            this.saldo = saldoInicial;
        }
    }

    public boolean depositar(double valor) {
        
        if (valor <= 0) {
            return false;
        }

        // NOVA VALIDAÇÃO: Se o valor exceder o limite, rejeita o depósito
        if (valor > LIMITE_MAXIMO_DEPOSITO) {
            return false; 
        }

        this.saldo += valor;
        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        double valorComTaxa = valor;
        
        // CORREÇÃO DO HOTFIX
        if (valor > 100.0) {
            valorComTaxa = valor + 0.50; 
        }

        // A VALIDAÇÃO e o DESCONTO DEVEM USAR 'valorComTaxa'
        if (valor <= 0 || valorComTaxa > saldo) {
            return false;
        }
        
        // ESTA LINHA É CRÍTICA: Desconta o valor com a taxa.
        this.saldo -= valorComTaxa;
        return true;
    }
}