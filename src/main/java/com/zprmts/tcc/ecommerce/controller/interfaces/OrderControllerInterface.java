package com.zprmts.tcc.ecommerce.controller.interfaces;

import com.zprmts.tcc.ecommerce.dto.order.OrderRequest;
import com.zprmts.tcc.ecommerce.dto.order.OrderResponse;
import com.zprmts.tcc.ecommerce.dto.order.OrderUpdate;
import com.zprmts.tcc.ecommerce.dto.perfume.PerfumeResponse;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface OrderControllerInterface {

    @Operation(summary = "Cadastrar uma Ordem", description = "Cadastra uma nova Ordem")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Ordem criada com sucesso!"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest orderRequest) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar uma ordem", description = "Atualiza uma ordem no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ordem atualizada com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Ordem não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idOrder}")
    ResponseEntity<OrderResponse> update(@Valid @PathVariable("idOrder") Long idOrder,
                                                @Valid @RequestBody OrderUpdate orderUpdate) throws RegraDeNegocioException;

    @Operation(summary = "Buscar itens de uma Ordem por ID da ordem", description = "Busca todos os itens de uma Ordem")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ordem encontrada com sucesso, itens trazidos!"),
                    @ApiResponse(responseCode = "404", description = "Ordem não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}/itens")
    ResponseEntity<List<PerfumeResponse>> getOrderItemsByOrderId(@PathVariable("id") Long id) throws Exception;

    @Operation(summary = "Buscar Ordens por usuário", description = "Busca ordens por usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ordens encontradas com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Ordens não encontradas"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<Page<OrderResponse>> getUserOrders(@PageableDefault(size = 10) Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Buscar todas as ordens", description = "Busca todas as ordens")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ordens encontradas com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Ordens não encontradas"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar")
    ResponseEntity<Page<OrderResponse>> list(@PageableDefault(page = 0, size = 10, sort = "idOrder", direction = Sort.Direction.ASC) Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Buscar uma Ordem por ID", description = "Busca uma Ordem por ID no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ordem encontrada com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Ordem não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idOrder}")
    ResponseEntity<OrderResponse> getById(@PathVariable Long idOrder) throws RegraDeNegocioException;



    @Operation(summary = "Deletar uma Ordem", description = "Deleta uma Ordem no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Ordem deletada com sucesso!"),
                    @ApiResponse(responseCode = "404", description = "Ordem não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idOrder}")
    ResponseEntity<Void> delete(@PathVariable Long idOrder) throws RegraDeNegocioException;
}
