
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
    
    public RelatorioEntrada lerEntradas(String path) {
        
        FileFilter filter = (File f) -> f.getName().endsWith(".dat");
        File dir = new File(path);
        File[] files = dir.listFiles(filter);
        
        List<RelatorioEntrada> todasEntradas = Arrays.stream(files)
                .map(this::processarEntrada)
                .collect(Collectors.toList());
        
        return RelatorioEntrada.agrupar(todasEntradas);
    }
    
    public RelatorioEntrada processarEntrada(File file) {

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
        return relatorioEntrada;
    }
    
    public RelatorioSaida processarSaida(RelatorioEntrada entrada) {

        long quantClientes = entrada.getClientes().size();
        
        long quantVendedores = entrada.getVendedores().size();
        
        long vendaMaisCara = 0;
        
        String piorVendedor = null;
        
        RelatorioSaida saida = new RelatorioSaida();
        saida.setMaiorVenda(vendaMaisCara);
        saida.setPiorVendedor(piorVendedor);
        saida.setQuantClientes(quantClientes);
        saida.setQuantVendedores(quantVendedores);
        
        return saida;
    }
    
    public void escreverSaida(RelatorioSaida relatorio) {
    }

}
