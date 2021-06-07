package com.example.listapersonagem.dao;

import com.example.listapersonagem.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    private final static List<Personagem> personagens = new ArrayList<>();
    //contador do id
    private static int contadorId = 1;

    public void salva(Personagem personagemsalvo) {
        //salva o personagem novo e seta um id
        personagemsalvo.setId(contadorId);
        //adiciona o personagem a lista
        personagens.add(personagemsalvo);
        //chama metodo atualiza id
        AtualizaID();
    }

    private void AtualizaID() {
        //adiciona +1 no contador id
        contadorId++;
    }

    public void Editar(Personagem personagem) {
        //pega o personagem e joga no metodo buscar id
        Personagem personagemEscolhido = BuscaPersonagemID(personagem);
        //verifica se personagem escolhido nao é nulo
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            //set informaçoes na lista personagem
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }

    private Personagem BuscaPersonagemID(Personagem personagem) {
        // procura o id do personagem para editar
        for (Personagem p : personagens) {
            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }
    //retorna a lista sempre atualizada
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }

    //metodo para excluir o personagem
    public void remove (Personagem personagem){
        Personagem personagemDesenvolvido = BuscaPersonagemID(personagem);
        if(personagemDesenvolvido != null){
            personagens.remove(personagemDesenvolvido);
        }
    }

}

