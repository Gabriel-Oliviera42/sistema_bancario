package br.com.trabalho;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    void deveCriarContaComSaldoInicial() {
        Conta conta = new Conta(100.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void deveDepositarValorPositivo() {
        Conta conta = new Conta(50.0);
        conta.depositar(25.0);
        assertEquals(75.0, conta.getSaldo());
    }

    @Test
    void naoDeveDepositarValorNegativo() {
        Conta conta = new Conta(50.0);
        conta.depositar(-25.0);
        assertEquals(50.0, conta.getSaldo()); // Saldo deve permanecer o mesmo
    }
}