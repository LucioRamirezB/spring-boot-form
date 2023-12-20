package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.editors.TextoMayusculaEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class FormController {
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"fechaNacimiento", new CustomDateEditor(dateFormat,false));
		
		binder.registerCustomEditor(String.class, "nombre",new TextoMayusculaEditor());
		
		binder.registerCustomEditor(Pais.class,"pais", paisEditor);
		
		binder.registerCustomEditor(Role.class,"roles", roleEditor);
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("España","México","Chile","Argentina","Brazil","Perú","Colombia","Venezuela","Estados Unidos","Guatemala","Bolivia","Honduras");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		
		return Arrays.asList("Hombre","Mujer");
	
	}
	
	
	@ModelAttribute("listaPaises")
	public List<Pais> ListaPaises(){
	
		//05 21 10 02 15 08 
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String,String> listaRolesMap(){
		Map<String,String> roles = new HashMap<String,String>();
		
		roles.put("ROLE_ADMIN", "Administrador");//
		roles.put("ROLE_USER", "Usuario");//05 21 10 02 15 08 
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}
	
	
	
	@ModelAttribute("paisesMap")
	public Map<String,String> paisesMap(){
		Map<String,String> paises = new HashMap<String,String>();
		
		paises.put("ES", "España");//
		paises.put("MX", "México");//05 21 10 02 15 08 
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("BR", "Brazil");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		paises.put("EU", "Estados Unidos");
		paises.put("GU", "Guatemala");
		paises.put("BO", "Bolivia");
		paises.put("HO", "Honduras");
		return paises;
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setIdentificador("12.456.789-K");
		usuario.setHabilitar(true);
		usuario.setPais(new Pais(2,"MX", "México"));
		usuario.setRoles(Arrays.asList(new Role(2,"Usuario","ROLE_USER")));
		usuario.setValorSecreto("Algún valor secreto ****");
		model.addAttribute("titulo","Form User");
		model.addAttribute("user",usuario);
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("user") Usuario usuario ,BindingResult resultValidation,Model model) {
		//validador.validate(usuario, resultValidation);
		
		if(resultValidation.hasErrors()) {
			model.addAttribute("titulo","Resultado form");
			/*
			Map<String,String> errores = new HashMap<>();
			resultValidation.getFieldErrors().forEach(err->{
				errores.put(err.getField(), "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			
			model.addAttribute("error", errores);
			*/
			return "form";
		}
		
		
		
		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "user",required = false) Usuario usuario,Model model, SessionStatus status) {
		if(usuario == null) {
			return "redirect:/form";
		}
		
		model.addAttribute("titulo","Resultado form");
		status.setComplete();
		return "resultado";
		
	}
}
