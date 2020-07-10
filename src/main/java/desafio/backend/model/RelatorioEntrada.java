
package desafio.backend.model;

import java.util.List;

/**
 *
 * @author marcos
 * @since 08/07/2020
 */
public class RelatorioEntrada {
    
    private List<Cliente> clientes;
    private List<Vendedor> vendedores;
    private List<Venda> vendas;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    public static RelatorioEntrada agrupar(List<RelatorioEntrada> entradas) {
        return null;
    }

}
