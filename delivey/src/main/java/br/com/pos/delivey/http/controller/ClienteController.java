package br.com.pos.delivey.http.controller;

import br.com.pos.delivey.model.Cliente;
import br.com.pos.delivey.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(description = "Insere o cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insere o cliente"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @Operation(description = "Busca cliente pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o cliente"),
    })
    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        return clienteService.listarClientes(cpf);
    }
}
