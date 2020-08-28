package com.github.lassulfi.resouce;

import com.github.lassulfi.dto.AtualizaProdutoDto;
import com.github.lassulfi.dto.CadastroProdutoDto;
import com.github.lassulfi.model.Produto;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> listarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void cadastrarProduto(CadastroProdutoDto produtoDto) {
        Produto produto = new Produto(produtoDto.getNome(), produtoDto.getValor());
        produto.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizarProduto(@PathParam("id") Long id, AtualizaProdutoDto produtoDto) {
        Optional<Produto> produto = Produto.findByIdOptional(id);
        Produto entity = produto.orElseThrow(() -> new NotFoundException("Nenhum produto encontrado para id: " + id));
        entity.setPreco(produtoDto.getValor());
        entity.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void excluirProduto(@PathParam("id") Long id) {
        Optional<Produto> produto = Produto.findByIdOptional(id);
        produto.ifPresentOrElse(Produto::delete, () -> {
            throw new NotFoundException("Nenhum produto encontrado para id: " + id);
        });
    }
}
