package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private List<Desenvolvedor> desenvolvedores;
    private String nome;
    private Integer vagas;

    public Consultoria(){
        desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public void contratar(Desenvolvedor desenvolvedor){
        if (vagas > desenvolvedores.size()){
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor){
        if (desenvolvedor.isFullstack()){
            desenvolvedores.add(desenvolvedor);
        }
    }

    public Double getTotalSalarios(){
        Double somaSalarios = 0.0;
        for (Desenvolvedor d: desenvolvedores){
            somaSalarios += d.calcularSalario();
        }

        return somaSalarios;
    }

    public Integer qtdDesenvolvedoresMobile(){
        int qtd = 0;
        for (int i = 0; i < desenvolvedores.size(); i++){
            if (desenvolvedores.get(i) instanceof DesenvolvedorMobile){
                qtd += 1;
            }
        }

        return qtd;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        List<Desenvolvedor> desenvolvedoresMaiorque = new ArrayList<>();

        for (Desenvolvedor d: desenvolvedores){
            if (d.calcularSalario() > salario){
                desenvolvedoresMaiorque.add(d);
            }
        }

        return desenvolvedoresMaiorque;
    }

    public Desenvolvedor buscarMenorSalario(){
        if (desenvolvedores.isEmpty()) {
            return null;
        }

        Desenvolvedor menor = desenvolvedores.get(0);

        for (Desenvolvedor d : desenvolvedores) {
            if (d.calcularSalario() < menor.calcularSalario()) {
                menor = d;
            }
        }

        return menor;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){
        List<Desenvolvedor> listaDesenvolvedores = new ArrayList<>();

        if (tecnologia == null){
            return  listaDesenvolvedores;
        }

        for (Desenvolvedor d: desenvolvedores) {
            if (d instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb devWeb = (DesenvolvedorWeb) d;
                if (tecnologia.equals(devWeb.getBackend()) || tecnologia.equals(devWeb.getFrontend()) || tecnologia.equals(devWeb.getSgbd())) {
                    listaDesenvolvedores.add(d);
                }
            } else if (d instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile devMob = (DesenvolvedorMobile) d;
                if (tecnologia.equals(devMob.getPlataforma()) || tecnologia.equals(devMob.getLinguagem())) {
                    listaDesenvolvedores.add(d);
                }
            }
        }
        return listaDesenvolvedores;

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){
        List<Desenvolvedor> listaDesevolvedores = this.buscarPorTecnologia(tecnologia);
        Double somaTotal = 0.0;

        for (Desenvolvedor d: listaDesevolvedores){
            somaTotal += d.calcularSalario();
        }

        return somaTotal;

    }
}
