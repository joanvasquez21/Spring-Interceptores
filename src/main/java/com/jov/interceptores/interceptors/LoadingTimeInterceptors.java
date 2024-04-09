package com.jov.interceptores.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptors implements HandlerInterceptor {
	
	//Registramos 
	private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptors.class);



	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Para saber cual es el metodo al que estoy llamando
		HandlerMethod controller = ((HandlerMethod) handler);
		
		logger.info("LoadingTimeInterceptor: preHandler() entrando...." + controller.getMethod().getName());
		//Implementamos el tiempo de carga, en esta linea obtenemos el tiempo actual en milisegundos
		long start = System.currentTimeMillis();
		// Guardamos este tiempo en el objeto HttpServletRequest
		request.setAttribute("start", start);
		//Creamos un objeto Random para generar un valor aleatorio que representara un retraso
		Random random = new Random();
		int delay = random.nextInt(500);
		//Pausamos la ejecucion del hilo actual durante el tiempo especificado(delay)
		Thread.sleep(delay);
		
		Map<String,String> result = new HashMap<>();
		result.put("error", "No tienes acceso a esta pagina");
		result.put("date", new Date().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(result);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().write(jsonString);
		
		return false;
		//return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//Obtener una fecha final
		long end = System.currentTimeMillis();
		long start = (long) request.getAttribute("start");
		long resultado = end - start;
		logger.info("Tiempo transcurrido: " + resultado + " milisegundos" );
		
		logger.info("LoadingTimeInterceptor: postHandler() Saliendo...." + ((HandlerMethod) handler).getMethod().getName());
	}
	

	
	

}
