package dan.tp2021.pedidos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dan.tp2021.pedidos.domain.Pedido;

public interface PedidoRepository  extends CrudRepository<Pedido, Integer> { 
	
}
