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

    @Test
    void deveSacarValorValido() {
        Conta conta = new Conta(200.0);
        conta.sacar(50.0);
        assertEquals(150.0, conta.getSaldo());
    }

    @Test
    void deveRetornarFalseAoSacarValorNegativoOuZero() {
        Conta conta = new Conta(100.0);
        assertFalse(conta.sacar(0.0));
        assertFalse(conta.sacar(-10.0));
        assertEquals(100.0, conta.getSaldo()); // O saldo deve permanecer intacto
    }

    @Test
    void deveRetornarFalseParaSaqueComSaldoInsuficiente() {
        Conta conta = new Conta(50.0);
        assertFalse(conta.sacar(100.0));
        assertEquals(50.0, conta.getSaldo()); // O saldo não deve mudar
    }

    @Test
    void deveSacarTodoOSaldo() {
        Conta conta = new Conta(75.0);
        assertTrue(conta.sacar(75.0));
        assertEquals(0.0, conta.getSaldo());
    }

    @Test
    void deveCriarContaComSaldoZeroSeInicialNegativo() {
        // Entradas: Tentar criar uma conta com saldo inicial de -100.0
        Conta conta = new Conta(-100.0);
        
        // Asserção: O saldo deve ser 0.0, não -100.0 (Verificação do comportamento esperado)
        assertEquals(0.0, conta.getSaldo(), "O saldo deve ser zero se o valor inicial for negativo.");
    }

    @Test
    void deveDepositarValorAbaixoDoLimite() {
        Conta conta = new Conta(100.0);
        // 4999.99 deve ser aceito
        assertTrue(conta.depositar(4999.99));
        // Saldo esperado: 5099.99
        assertEquals(5099.99, conta.getSaldo()); 
    }

    @Test
    void naoDeveDepositarValorAcimaDoLimite() {
        Conta conta = new Conta(100.0);
        // 5000.01 deve ser rejeitado
        assertFalse(conta.depositar(5000.01)); 
        // O saldo deve permanecer intacto, 100.0
        assertEquals(100.0, conta.getSaldo()); 
    }

    @Test
    void deveAplicarTaxaDeSaqueAcimaDeCem() {
        // 1. Arrange: Saldo inicial suficiente para o saque de 100.01 + 0.50 de taxa
        Conta conta = new Conta(500.0);
        double valorSaque = 100.01;
        double taxa = 0.50;
        double valorTotalDescontado = valorSaque + taxa; // 100.51

        // 2. Act: Executar o saque
        boolean sucesso = conta.sacar(valorSaque);

        // 3. Assert: Verificar o sucesso e o saldo final
        assertTrue(sucesso, "O saque com taxa deveria ser bem-sucedido.");
        
        // Saldo esperado: 500.0 - 100.51 = 399.49
        assertEquals(399.49, conta.getSaldo(), "O saldo deve refletir o saque mais a taxa.");
    }
}