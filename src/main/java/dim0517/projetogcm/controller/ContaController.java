package dim0517.projetogcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dim0517.projetogcm.model.DadosConta;
import dim0517.projetogcm.model.NovaConta;
import dim0517.projetogcm.model.Saldo;
import dim0517.projetogcm.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @PostMapping
    public String criarConta(@RequestBody NovaConta novaConta) {
        return contaService.criarConta(novaConta);
    }

    @GetMapping("/{idConta}/saldo")
    public Saldo consultarSaldo(@PathVariable int idConta) {
        return contaService.consultarSaldo(idConta);
    }

    @GetMapping("/{idConta}")
    public DadosConta consultarDados(@PathVariable int idConta) {
        return contaService.consultarDados(idConta);
    }

    @PutMapping("/{idConta}/creditar")
    public String creditar(@PathVariable int idConta, double valor) {
        return contaService.creditar(idConta, valor);
    }

    @PutMapping("/{idConta}/debitar")
    public String debitar(@PathVariable int idConta, double valor) {
        return contaService.debitar(idConta, valor);
    }

    @PostMapping("/{idOrigem}/transferir/{idDestino}")
    public String transferir(@PathVariable int idOrigem, @PathVariable int idDestino, double valor) {
        return contaService.transferir(idOrigem, idDestino, valor);
    }

    @PostMapping("/{idConta}/renderjuros")
    public String renderJuros(@PathVariable int idConta, double valor) {
        return contaService.renderJuros(idConta, valor);
    }

}
