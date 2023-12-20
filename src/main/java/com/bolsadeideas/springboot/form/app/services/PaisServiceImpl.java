package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;
@Service
public class PaisServiceImpl implements PaisService{

	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = Arrays.asList(new Pais(1,"ES", "España"),
				new Pais(2,"MX", "México"),
				new Pais(3,"CL", "Chile"),
				new Pais(4,"AR", "Argentina"),
				new Pais(5,"BR", "Brazil"),
				new Pais(6,"PE", "Perú"),
				new Pais(7,"CO", "Colombia"),
				new Pais(8,"VE", "Venezuela"),
				new Pais(9,"EU", "Estados Unidos"),
				new Pais(10,"GU", "Guatemala"),
				new Pais(11,"BO", "Bolivia"),
				new Pais(12,"HO", "Honduras"));
	}

	@Override
	public List<Pais> listar() {
		
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		
		Pais resultado = null;
		
		for(Pais pais: this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		
		return resultado;
	}

}
