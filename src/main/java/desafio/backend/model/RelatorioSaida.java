
package desafio.backend.model;

/**
 *
 * @author marcos
 * @since 07/07/2020
 */
public class RelatorioSaida {

    private Long quantClientes;
    private Long quantVendedores;
    private Long maiorVenda;
    private String piorVendedor;

    public Long getQuantClientes() {
        return quantClientes;
    }

    public void setQuantClientes(Long quantClientes) {
        this.quantClientes = quantClientes;
    }

    public Long getQuantVendedores() {
        return quantVendedores;
    }

    public void setQuantVendedores(Long quantVendedores) {
        this.quantVendedores = quantVendedores;
    }

    public Long getMaiorVenda() {
        return maiorVenda;
    }

    public void setMaiorVenda(Long maiorVenda) {
        this.maiorVenda = maiorVenda;
    }

    public String getPiorVendedor() {
        return piorVendedor;
    }

    public void setPiorVendedor(String piorVendedor) {
        this.piorVendedor = piorVendedor;
    }
}
