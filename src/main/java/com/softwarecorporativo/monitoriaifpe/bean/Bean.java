/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.MensagemExcecao;
import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import com.softwarecorporativo.monitoriaifpe.servico.GenericService;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Edmilson Santana
 */
public abstract class Bean {

    @PostConstruct
    protected void inicializar() {
        inicializarEntidades();
    }

    public void mensagemAlteracaoSucesso() {
        this.adicionarMensagemView("Alteração realizada com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void mensagemCadastroSucesso() {
        this.adicionarMensagemView("Cadastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void mensagemRemoverSucesso() {
        this.adicionarMensagemView("Cadastro removido com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void adicionarMensagemView(String mensagem) {
        this.adicionarMensagemComponente(null, mensagem, null);
    }

    public void adicionarMensagemView(String mensagem, FacesMessage.Severity severity) {
        this.adicionarMensagemComponente(null, mensagem, severity);
    }

    private void adicionarMensagemComponente(String componente, String mensagem, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(componente, new FacesMessage(severity, mensagem, null));
    }

    public void alterar(GenericService service, EntidadeNegocio entidadeNegocio) {
        try {
            service.atualizar(entidadeNegocio);
            mensagemAlteracaoSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        } catch (EJBException ejbe) {
            if (ejbe.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ejbe.getCause());
                adicionarMensagemView(mensagemExcecao.getMensagem(), FacesMessage.SEVERITY_WARN);
            } else {
                throw ejbe;
            }
        }
    }

    public void cadastrar(GenericService service, EntidadeNegocio entidadeNegocio) {
        try {
            service.salvar(entidadeNegocio);
            mensagemCadastroSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        } catch (EJBException ejbe) {
            if (ejbe.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ejbe.getCause());
                adicionarMensagemView(mensagemExcecao.getMensagem(), FacesMessage.SEVERITY_WARN);
            } else {
                throw ejbe;
            }
        }
    }

    public void remover(GenericService service, EntidadeNegocio entidadeNegocio) {
        try {
            service.remover(entidadeNegocio);
            mensagemRemoverSucesso();
        } catch (NegocioException ex) {
            adicionarMensagemView(ex.getMessage(), FacesMessage.SEVERITY_WARN);
        }
    }

    abstract void inicializarEntidades();

}
