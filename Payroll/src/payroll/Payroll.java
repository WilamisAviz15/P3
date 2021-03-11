package payroll;

import java.util.Scanner;

public class Payroll {
    
    public static void Funcionarios() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("FUNCIONÁRIOS");
        System.out.println("1 - Adicionar Funcionário");
        System.out.println("2 - Remover Funcionário");
        System.out.println("3 - Editar Funcionário");
        System.out.println("4 - Listar Funcionários");
        System.out.println("5 - Voltar");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        }
    }

    public static void CartaoPonto() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("CARTÃO PONTO");
        System.out.println("1 - Bater Cartão Ponto Entrada");
        System.out.println("2 - Bater Cartão Ponto Saída");
        System.out.println("3 - Listar Cartões Ponto");
        System.out.println("4 - Voltar");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        }
    }

    public static void Vendas() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("VENDAS");
        System.out.println("1 - Lançar Venda");
        System.out.println("2 - Listar Vendas");
        System.out.println("3 - Voltar");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        }
    }

    public static void TaxaDeServico() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("TAXA DE SERVIÇO");
        System.out.println("1 - Lançar Taxa de Serviço");
        System.out.println("2 - Listar Taxas de Serviço");
        System.out.println("3 - Voltar");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        }
    }

    public static void RodarFolhaPagamento() {

    }

    public static void AgendaPagamentos() {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("AGENDAS DE PAGAMENTO");
        System.out.println("1 - Listar Agendas de Pagamento");
        System.out.println("2 - Voltar");
        option = op.nextInt();
        switch (option) {
        case 1:
            break;
        case 2:
            break;
        }
    }

    public static void main(String[] args) {
        int option;
        Scanner op = new Scanner(System.in);
        System.out.println("===============FOLHA DE PAGAMENTO===============");
        System.out.println("1  - Funcionários");
        System.out.println("2  - Cartão Ponto");
        System.out.println("3  - Venda");
        System.out.println("4  - Taxa de Serviço");
        System.out.println("5  - Rodar Folha de Pagamento");
        System.out.println("6  - Agenda de Pagamentos");
        System.out.println("7  - Undo");
        System.out.println("8  - Redo");
        System.out.println("9 - Sair");
        System.out.println("================================================");
        option = op.nextInt();
        switch (option) {
        case 1:
            Funcionarios();
            break;
        case 2:
            CartaoPonto();
            break;
        case 3:
            Vendas();
            break;
        case 4:
            TaxaDeServico();
            break;
        case 5:
            RodarFolhaPagamento();
            break;
        case 6:
            AgendaPagamentos();
            break;
        case 7:

            break;
        case 8:

            break;
        case 9:
            System.exit(0);
            break;
        }
    }
}
