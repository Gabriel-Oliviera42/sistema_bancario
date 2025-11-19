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

    public boolean transferir(Conta destino, double valor) {
    
        // 1. Validação do valor
        if (valor <= 0) {
            return false;
        }
        
        // 2. Tenta sacar da conta de origem.
        // O método sacar já faz a validação de saldo insuficiente.
        if (this.sacar(valor)) {
            // 3. Se o saque foi bem-sucedido, deposita na conta de destino.
            // O método depositar já faz a validação de limite de depósito (se implementado)
            destino.depositar(valor);
            return true;
        }
        
        // 4. Se o saque falhou (saldo insuficiente, por exemplo)
        return false;
    }
}