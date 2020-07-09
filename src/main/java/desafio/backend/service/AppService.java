
package desafio.backend.service;

import desafio.backend.model.Cliente;
import desafio.backend.model.RelatorioSaida;
import desafio.backend.model.RelatorioEntrada;
import desafio.backend.model.Venda;
import desafio.backend.model.Vendedor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author marcos
 * @since 07/07/2020
 */
public class AppService {
    
    public List<RelatorioEntrada> buscarDadosEntrada(String path) {
        
        FileFilter filter = (File f) -> f.getName().endsWith(".dat");
        File dir = new File(path);
        File[] files = dir.listFiles(filter);
        
        return Arrays.stream(files)
                .map(this::processarArquivoEntrada)
                .collect(Collectors.toList());
    }
    
    public RelatorioEntrada processarArquivoEntrada(File file) {

        List<Vendedor> vendedores = new ArrayList();
        List<Cliente> clientes = new ArrayList();
        List<Venda> vendas = new ArrayList();
        
        FileInputStream stream = null;
        try {
            
            stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            while (linha != null) {

                try {
                    linha = br.readLine();
                    String[] split = linha.split("ç");
                    
                    switch(split[0]) {
                        
                        // vendedor -> 001çCPFçNameçSalary
                        case "001":
                            Vendedor vendedor = new Vendedor();
                            vendedor.setCPF(split[1]);
                            vendedor.setNome(split[2]);
                            vendedor.setSalario(Double.valueOf(split[3]));
                            vendedores.add(vendedor);
                            break;
                            
                        // cliente -> 002çCNPJçNameçBusiness Area
                        case "002":
                            Cliente cliente = new Cliente();
                            cliente.setCNPJ(split[1]);
                            cliente.setNome(split[2]);
                            cliente.setArea(split[3]);
                            clientes.add(cliente);
                            break;
                            
                        // venda -> 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
                        case "003":
                            Venda venda = new Venda();
                            venda.setId(Long.parseLong(split[1]));

                            venda.setItens(
                                Arrays.stream(split[2].substring(1, split[2].length()-1).split(","))
                                        .map(s -> {
                                            
                                            // Item ID-Item Quantity-Item Price
                                            String[] s1 = s.split("-");
                                            
                                            Venda.Item item = new Venda.Item();
                                            item.setId(Long.parseLong(s1[0]));
                                            item.setQuant(Integer.valueOf(s1[1]));
                                            item.setPreco(Double.parseDouble(s1[2]));
                                            
                                            return item;
                                        })
                                        .collect(Collectors.toList())
                            );
                            
                            venda.setVendedor(split[3]);
                            vendas.add(venda);
                            break;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        RelatorioEntrada relatorioEntrada = new RelatorioEntrada();
        relatorioEntrada.setClientes(clientes);
        relatorioEntrada.setVendas(vendas);
        relatorioEntrada.setVendedores(vendedores);
        relatorioEntrada.setDataHoraProcessamento(OffsetDateTime.now());
        return relatorioEntrada;
    }
    
    public RelatorioSaida processaRelatoriosEntrada(List<RelatorioEntrada> entradas) {
/*
        Quantidade de clientes no arquivo de entrada
        Quantidade de vendedor no arquivo de entrada
        ID da venda mais cara
        O pior vendedor
        O sistema deve estar funcionando o tempo todo.
        Todos os arquivos novos estar disponível, tudo deve ser executado
        Seu código deve ser escrito em Java.
        Você tem total liberdade para utilizar google com o que você precisa. Sinta-se à vontade para escolher qualquer biblioteca externa se for necessário.
         */

        long quantClientes = entradas.stream()
                .map(RelatorioEntrada::getClientes)
                .count();
        
        long quantVendedores = entradas.stream()
                .map(RelatorioEntrada::getVendedores)
                .count();
        
        return null;
    }
    
    public void escreverSaida(RelatorioSaida relatorio) {
    }

}
