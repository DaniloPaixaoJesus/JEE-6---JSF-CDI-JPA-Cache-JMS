package br.com.livrariaonline.loja.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Calendar.class)
public class CalendarConverter implements Converter {

	private DateTimeConverter converter = new DateTimeConverter();
	
	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
	
	
	//tela para mb
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String dataTexto) {
		if(dataTexto == null || dataTexto.trim().equals(""))
			return null;
		Date data = (Date) converter.getAsObject(context, component, dataTexto);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	//mb para a tela
	@Override
	public String getAsString(FacesContext context, 
			UIComponent component, Object dataObject) {
		if (dataObject == null) 
			return null;
		
		Calendar calendar = (Calendar) dataObject;
		return converter.getAsString(
				context, component, calendar.getTime());
	}

}







