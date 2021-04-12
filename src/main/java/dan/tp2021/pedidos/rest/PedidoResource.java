package dan.tp2021.pedidos.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.tp2021.pedidos.domain.Pedido;
import dan.tp2021.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource {
	
	@Autowired
	PedidoService pedidoSrv;

	@PostMapping
	public ResponseEntity<String> crearPedido(@RequestBody Pedido unPedido){
		if(unPedido.getObra()==null) {
			return ResponseEntity.badRequest().body("Debe elegir una obra");
		}
		if(unPedido.getDetalle()==null || unPedido.getDetalle().isEmpty() ) {
			return ResponseEntity.badRequest().body("Debe agregar items al pedido");
		}
		pedidoSrv.crearPedido(unPedido);
		return ResponseEntity.status(HttpStatus.CREATED).body("OK");
	}
}
