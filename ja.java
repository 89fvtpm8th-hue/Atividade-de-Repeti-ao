import java.util.Scanner;

/*
 * Exercicios 1 a 8. Algoritmo geral:
 * ler a opcao; executar o procedimento correspondente; voltar ao menu
 * enquanto o usuario nao escolher sair. Cada procedimento segue os passos:
 * inicializar acumuladores/contadores, ler os dados conforme a quantidade
 * ou sentinela definida, atualizar os resultados e apresenta-los.
 *
 * Para o exercicio 6, como o enunciado nao define sentinela, o valor 0
 * encerra a leitura e nao entra nos calculos.
 */
public class ja {
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== EXERCICIOS ===");
            System.out.println("1 - Calculadora");
            System.out.println("2 - Soma de impares multiplos de 3 (1 a 500)");
            System.out.println("3 - Empregado mais recente e mais antigo");
            System.out.println("4 - Alturas e sexo de 15 pessoas");
            System.out.println("5 - Pesquisa salarial da prefeitura");
            System.out.println("6 - Estatisticas de valores (0 encerra)");
            System.out.println("7 - Media de 75 inteiros positivos");
            System.out.println("8 - Estatisticas de 15 amigos");
            System.out.println("0 - Encerrar");
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1: calculadora(); break;
                case 2: somaImparesMultiplosDeTres(); break;
                case 3: empregados(); break;
                case 4: pessoas(); break;
                case 5: pesquisaPrefeitura(); break;
                case 6: estatisticasValores(); break;
                case 7: media75(); break;
                case 8: amigos(); break;
                case 0: System.out.println("Programa encerrado."); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    // 1. Ler dois inteiros e a operacao; repetir ate o usuario optar por sair.
    private static void calculadora() {
        String continuar;
        do {
            int a = lerInt("Primeiro numero inteiro: ");
            int b = lerInt("Segundo numero inteiro: ");
            int operacao = lerInt("Operacao (1 soma, 2 subtracao, 3 multiplicacao, 4 quociente inteiro): ");
            switch (operacao) {
                case 1: System.out.println("Resultado: " + ((long) a + b)); break;
                case 2: System.out.println("Resultado: " + ((long) a - b)); break;
                case 3: System.out.println("Resultado: " + ((long) a * b)); break;
                case 4:
                    if (b == 0) System.out.println("Nao e possivel dividir por zero.");
                    else System.out.println("Quociente inteiro: " + (a / b));
                    break;
                default: System.out.println("Operacao invalida.");
            }
            System.out.print("Deseja informar novos valores? (S/N): ");
            continuar = entrada.next().trim();
        } while (continuar.equalsIgnoreCase("S"));
    }

    // 2. Somar os valores de 1 a 500 que sao simultaneamente impares e divisiveis por 3.
    private static void somaImparesMultiplosDeTres() {
        int soma = 0;
        for (int n = 1; n <= 500; n++)
            if (n % 2 != 0 && n % 3 == 0) soma += n;
        System.out.println("Soma = " + soma);
    }

    // 3. Ler ate 300 registros; 0,0 encerra. Menor tempo = mais recente.
    private static void empregados() {
        int menorMeses = Integer.MAX_VALUE, maiorMeses = Integer.MIN_VALUE;
        int idRecente = 0, idAntigo = 0, lidos = 0;
        while (lidos < 300) {
            int id = lerInt("Numero do empregado (0 para encerrar): ");
            int meses = lerInt("Meses de trabalho: ");
            if (id == 0 && meses == 0) break;
            if (id == 0 || meses < 0) {
                System.out.println("Registro invalido; informe numero e meses validos.");
                continue;
            }
            lidos++;
            if (meses < menorMeses) { menorMeses = meses; idRecente = id; }
            if (meses > maiorMeses) { maiorMeses = meses; idAntigo = id; }
        }
        if (lidos == 0) System.out.println("Nenhum empregado informado.");
        else {
            System.out.println("Mais recente: empregado " + idRecente + " (" + menorMeses + " meses)");
            System.out.println("Mais antigo: empregado " + idAntigo + " (" + maiorMeses + " meses)");
        }
    }

    // 4. Ler 15 pessoas; acumular media feminina, quantidade masculina e extremos.
    private static void pessoas() {
        double menor = Double.POSITIVE_INFINITY, maior = Double.NEGATIVE_INFINITY;
        double somaMulheres = 0;
        int qtdMulheres = 0, qtdHomens = 0;
        char sexoMaisAlto = '?';
        for (int i = 1; i <= 15; i++) {
            double altura = lerDouble("Altura da pessoa " + i + " (m): ");
            char sexo = lerSexo("Sexo (M/F): ");
            if (altura < menor) menor = altura;
            if (altura > maior) { maior = altura; sexoMaisAlto = sexo; }
            if (sexo == 'F') { somaMulheres += altura; qtdMulheres++; }
            else qtdHomens++;
        }
        System.out.printf("Menor altura: %.2f m%n", menor);
        if (qtdMulheres == 0) System.out.println("Media das mulheres: nao ha mulheres no grupo.");
        else System.out.printf("Media de altura das mulheres: %.2f m%n", somaMulheres / qtdMulheres);
        System.out.println("Numero de homens: " + qtdHomens);
        System.out.println("Sexo da pessoa mais alta: " + sexoMaisAlto);
    }

    // 5. Salario negativo encerra; calcular medias, maior salario e percentual ate R$250.
    private static void pesquisaPrefeitura() {
        double somaSalarios = 0, maiorSalario = Double.NEGATIVE_INFINITY;
        long somaFilhos = 0, ate250 = 0, pessoas = 0;
        while (true) {
            double salario = lerDouble("Salario (negativo encerra): R$ ");
            if (salario < 0) break;
            int filhos = lerInt("Numero de filhos: ");
            if (filhos < 0) { System.out.println("Numero de filhos nao pode ser negativo."); continue; }
            pessoas++;
            somaSalarios += salario;
            somaFilhos += filhos;
            if (salario > maiorSalario) maiorSalario = salario;
            if (salario <= 250) ate250++;
        }
        if (pessoas == 0) { System.out.println("Nenhum dado valido informado."); return; }
        System.out.printf("Media salarial: R$ %.2f%n", somaSalarios / pessoas);
        System.out.printf("Media de filhos: %.2f%n", (double) somaFilhos / pessoas);
        System.out.printf("Maior salario: R$ %.2f%n", maiorSalario);
        System.out.printf("Pessoas com salario ate R$250,00: %.2f%%%n", 100.0 * ate250 / pessoas);
    }

    // 6. Neste programa, zero e a sentinela; contabilizar positivos e negativos.
    private static void estatisticasValores() {
        double soma = 0;
        long total = 0, positivos = 0, negativos = 0;
        while (true) {
            double valor = lerDouble("Valor (0 encerra): ");
            if (valor == 0) break;
            soma += valor;
            total++;
            if (valor > 0) positivos++; else negativos++;
        }
        if (total == 0) { System.out.println("Nenhum valor informado."); return; }
        System.out.printf("Media aritmetica: %.2f%n", soma / total);
        System.out.println("Quantidade de positivos: " + positivos);
        System.out.println("Quantidade de negativos: " + negativos);
        System.out.printf("Percentual de positivos: %.2f%%%n", 100.0 * positivos / total);
        System.out.printf("Percentual de negativos: %.2f%%%n", 100.0 * negativos / total);
    }

    // 7. Ler exatamente 75 inteiros positivos, contando cada entrada aceita.
    private static void media75() {
        long soma = 0;
        int quantidade = 0;
        while (quantidade < 75) {
            int valor = lerInt("Inteiro positivo " + (quantidade + 1) + "/75: ");
            if (valor <= 0) { System.out.println("Informe um inteiro positivo."); continue; }
            soma += valor;
            quantidade++;
        }
        System.out.println("Quantidade de numeros lidos: " + quantidade);
        System.out.printf("Media: %.2f%n", (double) soma / quantidade);
    }

    // 8. Ler os dados de 15 amigos e apresentar extremos, medias por sexo e geral.
    private static void amigos() {
        double menor = Double.POSITIVE_INFINITY, maior = Double.NEGATIVE_INFINITY;
        double somaHomens = 0, somaMulheres = 0, somaTurma = 0;
        int qtdHomens = 0, qtdMulheres = 0;
        String nomeMenor = "", nomeMaior = "";
        for (int i = 1; i <= 15; i++) {
            System.out.print("Nome do amigo " + i + ": ");
            String nome = entrada.next();
            double altura = lerDouble("Altura (m): ");
            int sexo = lerInt("Sexo (1 masculino, 2 feminino): ");
            while (sexo != 1 && sexo != 2) sexo = lerInt("Codigo invalido. Digite 1 ou 2: ");
            if (altura < menor) { menor = altura; nomeMenor = nome; }
            if (altura > maior) { maior = altura; nomeMaior = nome; }
            somaTurma += altura;
            if (sexo == 1) { somaHomens += altura; qtdHomens++; }
            else { somaMulheres += altura; qtdMulheres++; }
        }
        System.out.printf("Maior altura: %.2f m (%s)%n", maior, nomeMaior);
        System.out.printf("Menor altura: %.2f m (%s)%n", menor, nomeMenor);
        if (qtdMulheres == 0) System.out.println("Media das mulheres: nao ha mulheres no grupo.");
        else System.out.printf("Media das mulheres: %.2f m%n", somaMulheres / qtdMulheres);
        if (qtdHomens == 0) System.out.println("Media dos homens: nao ha homens no grupo.");
        else System.out.printf("Media dos homens: %.2f m%n", somaHomens / qtdHomens);
        System.out.printf("Media da turma: %.2f m%n", somaTurma / 15);
    }

    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!entrada.hasNextInt()) { entrada.next(); System.out.print("Digite um numero inteiro: "); }
        return entrada.nextInt();
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!entrada.hasNextDouble()) { entrada.next(); System.out.print("Digite um numero valido: "); }
        return entrada.nextDouble();
    }

    private static char lerSexo(String mensagem) {
        System.out.print(mensagem);
        String valor = entrada.next().trim().toUpperCase();
        while (!valor.equals("M") && !valor.equals("F")) {
            System.out.print("Digite M ou F: ");
            valor = entrada.next().trim().toUpperCase();
        }
        return valor.charAt(0);
    }
}
