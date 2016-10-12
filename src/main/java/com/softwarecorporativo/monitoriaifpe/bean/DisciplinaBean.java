/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.bean;

import com.softwarecorporativo.monitoriaifpe.exception.MensagemExcecao;
import com.softwarecorporativo.monitoriaifpe.exception.NegocioException;
import com.softwarecorporativo.monitoriaifpe.modelo.aluno.Aluno;
import com.softwarecorporativo.monitoriaifpe.modelo.turma.Turma;
import com.softwarecorporativo.monitoriaifpe.modelo.periodo.Periodo;
import com.softwarecorporativo.monitoriaifpe.modelo.professor.Professor;
import com.softwarecorporativo.monitoriaifpe.servico.DisciplinaService;
import com.softwarecorporativo.monitoriaifpe.servico.PeriodoService;
import com.softwarecorporativo.monitoriaifpe.servico.ProfessorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Douglas Albuquerque
 */
@ManagedBean
@ViewScoped
public class DisciplinaBean extends GenericBean<Turma> {

    private static final long serialVersionUID = -4299577354116933320L;

    @ManagedProperty(value = "#{userSettings}")
    private UserSettings userSettings;

    @EJB
    private DisciplinaService disciplinaService;

    @EJB
    private PeriodoService periodoService;

    private Periodo periodo;

    @Override
    void inicializarEntidadeNegocio() {
        periodo = periodoService.getEntidadeNegocio();
        super.setEntidadeNegocio(disciplinaService.getEntidadeNegocio());
    }

    @Override
    protected void inicializar() {
        super.inicializar();
    }

    @Override
    void inicializarServico() {
        setService(disciplinaService);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * TODO: Utilizar tratamento de exceção da classe generica *
     */
    public void ofertarDisciplinaParaMonitoria() {
        Professor professor = (Professor) userSettings.getUsuario();
        professor.addTurma(entidadeNegocio);
        try {
            disciplinaService.salvarDisciplinaComPeriodoAtual(entidadeNegocio);
            mensagemCadastroSucesso();
        } catch (NegocioException e) {
            adicionarMensagemView(e.getMessage(), FacesMessage.SEVERITY_WARN);
        } catch (EJBException ejbe) {
            if (ejbe.getCause() instanceof ConstraintViolationException) {
                MensagemExcecao mensagemExcecao = new MensagemExcecao(ejbe.getCause());
                adicionarMensagemView(mensagemExcecao.getMensagem(), FacesMessage.SEVERITY_WARN);
            } else {
                throw ejbe;
            }
        }
    }

    public void cadastrarDisciplina() {
        entidadeNegocio.setPeriodo(periodo);
        super.cadastrar();
    }

    public List<Turma> getDisciplinasProfessor() {
        Professor professor = (Professor) userSettings.getUsuario();
        return disciplinaService.obterDisciplinasDoProfessor(professor);
    }

    public List<Turma> getDisciplinasPorCursoForaPeriodoAtual() throws NegocioException {

        Aluno aluno = (Aluno) userSettings.getUsuario();
        return disciplinaService.obterDisciplinasPorCursoDePeriodoNaoAtual(aluno.getCurso());
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

}
