package br.com.agendaDois.agendaDois.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.agendaDois.agendaDois.model.Cadastro;

@Repository
public interface CadastroRepository extends CrudRepository<Cadastro, Long>{

}
