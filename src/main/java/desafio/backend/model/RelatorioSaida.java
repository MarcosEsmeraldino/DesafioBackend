
package desafio.backend.model;

/**
 *
 * @author marcos
 * @since 07/07/2020
 */
public class RelatorioSaida {

    private int quantClientes;
    private int quantVendedores;
    private Long maiorVenda;
    private String piorVendedor;

    public int getQuantClientes() {
        return quantClientes;
    }

    public void setQuantClientes(int quantClientes) {
        this.quantClientes = quantClientes;
    }

    public int getQuantVendedores() {
        return quantVendedores;
    }

    public void setQuantVendedores(int quantVendedores) {
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
