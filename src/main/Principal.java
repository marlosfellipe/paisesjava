package principal;

import modelo.Pais;
import servico.ServicoPais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final String ARQUIVO_JSON = "paises_pesquisados.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        ServicoPais servicoPais = new ServicoPais();
        Scanner scanner = new Scanner(System.in);

        // Carregar países já pesquisados do arquivo JSON
        List<Pais> paisesPesquisados = carregarPaisesPesquisados();

        System.out.println("Bem-vindo ao sistema de informações sobre países!");
        System.out.println("Digite o nome de um país para buscar informações ou 'sair' para encerrar.");

        while (true) {
            System.out.print("\nDigite o nome do país: ");
            String nomePais = scanner.nextLine().trim();

            // Verificar se o usuário deseja sair
            if (nomePais.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando o programa. Obrigado por usar o sistema!");
                break;
            }

            // Buscar informações sobre o país
            try {
                List<Pais> paises = servicoPais.buscarPaisPorNome(nomePais);

                if (paises == null || paises.isEmpty()) {
                    System.out.println("Nenhum país encontrado para o nome fornecido.");
                } else {
                    for (Pais pais : paises) {
                        exibirInformacoesPais(pais);
                        paisesPesquisados.add(pais); // Adicionar o país à lista
                    }

                    // Salvar países pesquisados no arquivo JSON
                    salvarPaisesPesquisados(paisesPesquisados);
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Erro ao buscar informações: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Método auxiliar para exibir informações do país
    private static void exibirInformacoesPais(Pais pais) {
        if (pais.getNome() != null) {
            System.out.println("\nPaís: " + pais.getNome().getComum());
            System.out.println("Nome oficial: " + pais.getNome().getOficial());
        } else {
            System.out.println("Informações sobre o nome do país estão indisponíveis.");
        }

        System.out.println("Região: " + (pais.getRegion() != null ? pais.getRegion() : "Indisponível"));
        System.out.println("População: " + (pais.getPopulation() > 0 ? pais.getPopulation() : "Indisponível"));
        System.out.println("Capital: " + (pais.getCapital() != null ? String.join(", ", pais.getCapital()) : "Indisponível"));
        System.out.println("Bandeira (URL): " + (pais.getFlags() != null ? pais.getFlags().getPng() : "Indisponível"));
        System.out.println("-----------------------------------");
    }

    // Método para salvar os países pesquisados no arquivo JSON
    private static void salvarPaisesPesquisados(List<Pais> paisesPesquisados) {
        try (Writer writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(paisesPesquisados, writer);
            System.out.println("Países pesquisados foram salvos no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar países pesquisados: " + e.getMessage());
        }
    }

    // Método para carregar países pesquisados do arquivo JSON
    private static List<Pais> carregarPaisesPesquisados() {
        File arquivo = new File(ARQUIVO_JSON);
        if (!arquivo.exists()) {
            System.out.println("Arquivo JSON não encontrado. Criando novo arquivo.");
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_JSON)) {
            List<Pais> paisesPesquisados = gson.fromJson(reader, ArrayList.class);
            return paisesPesquisados != null ? paisesPesquisados : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao carregar países pesquisados: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
