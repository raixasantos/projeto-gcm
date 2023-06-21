package dim0517.projetogcm.controller;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dim0517.projetogcm.model.Conta;
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
    public Conta criarConta(@RequestBody NovaConta novaConta) {
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
    public DadosConta consultarDados(@PathVariable int idConta, @RequestBody Valor) {
        return contaService.consultarDados(idConta);
    }

}
