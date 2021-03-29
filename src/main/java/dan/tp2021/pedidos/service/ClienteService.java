package dan.tp2021.pedidos.service;

import dan.tp2021.pedidos.domain.Obra;

public interface ClienteService {

	public Double deudaCliente(Obra id);
	public Double maximoSaldoNegativo(Obra id);
	public Integer situacionCrediticiaBCRA(Obra id);

}
