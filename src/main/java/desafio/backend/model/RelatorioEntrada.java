
package desafio.backend.model;

import java.time.OffsetDateTime;
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
    private OffsetDateTime dataHoraProcessamento;

    public OffsetDateTime getDataHoraProcessamento() {
        return dataHoraProcessamento;
    }

    public void setDataHoraProcessamento(OffsetDateTime dataHoraProcessamento) {
        this.dataHoraProcessamento = dataHoraProcessamento;
    }

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

}
