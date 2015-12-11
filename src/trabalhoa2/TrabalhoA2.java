package trabalhoa2;

import java.util.Scanner;

public class TrabalhoA2 {

    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        CaixaEletronico ce;
        int opcao;
        float saldoIni;
        
        ce = new CaixaEletronico();

        while (true)
        {
            System.out.println("\n---------------------------------------");
            System.out.println("Caixa Eletrônico - Menu de Opções");
            System.out.println("---------------------------------------");
            System.out.println("1- Repor");
            System.out.println("2- Sacar");
            System.out.println("3- Consultar saldo");
            System.out.println("4- Fim");
            System.out.print("Opção: ");
            opcao = teclado.nextInt();
            
            if (opcao == 4)
                break;
            
            switch(opcao)
            {
                case 1: // repor
                        int qtdNota5 = 0;
                        int qtdNota10 = 0;
                        int qtdNota20 = 0;
                        int qtdNota50 = 0;
                        int qtdNota100 = 0;
                        
                        System.out.println("\n---------------------------------------");
                        System.out.println("Caixa Eletrônico - Reposição de notas");
                        System.out.println("---------------------------------------");
                        System.out.print("Informa quantidade de notas de 5 reais: ");
                        qtdNota5 = teclado.nextInt();

                        System.out.print("Informa quantidade de notas de 10 reais: ");
                        qtdNota10 = teclado.nextInt();

                        System.out.print("Informa quantidade de notas de 20 reais: ");
                        qtdNota20 = teclado.nextInt();

                        System.out.print("Informa quantidade de notas de 50 reais: ");
                        qtdNota50 = teclado.nextInt();
                        
                        System.out.print("Informa quantidade de notas de 100 reais: ");
                        qtdNota100 = teclado.nextInt();                        

                        ce.depositar(5, qtdNota5);
                        ce.depositar(10, qtdNota10);
                        ce.depositar(20, qtdNota20);
                        ce.depositar(50, qtdNota50);
                        ce.depositar(100, qtdNota100);
                        
                        break;
   
                case 2: // sacar
                        int quantiaSaque;
                        
                        System.out.println("\n---------------------------------------");
                        System.out.println("Caixa Eletrônico - Saque");
                        System.out.println("---------------------------------------");
                        System.out.print("Quantia: ");
                        quantiaSaque = teclado.nextInt();
                    
                        System.out.println(ce.sacar(quantiaSaque));
                        
                        break;

                case 3: // consultar saldo
                        System.out.println(ce.getSaldo());
                        break;
                    
                default: // opcao invalida
                        System.out.println("Opção inválida!");
                        break;
            }
        }
    }
}
