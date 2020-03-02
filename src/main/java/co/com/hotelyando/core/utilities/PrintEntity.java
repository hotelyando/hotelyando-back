package co.com.hotelyando.core.utilities;

import java.util.List;

import co.com.hotelyando.database.model.BranchOffice;
import co.com.hotelyando.database.model.Permit;
import co.com.hotelyando.database.model.Person;
import co.com.hotelyando.database.model.Plan;

public class PrintEntity {
	
	/*
	 * Usuarios
	 */
	public static final String VALIDACION_ID_USUARIO = "Debe ingresar un Id de usuario.";
	public static final String VALIDACION_HOTEL_ID = "Debe ingresar un Id de hotel.";
	public static final String VALIDACION_ROL = "Debe ingresar al menos un rol para el usuario.";
	public static final String VALIDACION_PERSONA = "El usuario debe estar vinculado a una persona.";
	public static final String VALIDACION_USUARIO = "Debe ingresar un nombre de usuario.";
	public static final String VALIDACION_CONTRASENA = "Debe ingresar una contraseña.";
	
	public static final String USUARIO_NO_ENCONTRADO = "User no encontrado!.";
	public static final String USUARIO_REGISTRADO = "El usuario se registro correctamente";
	public static final String DATOS_RETORNADOS = "Registros retornados, ";
	public static final String USUARIO_LOGUEADO = "User existente";

	
	/*
	 * Persona
	 */
	public static final String VALIDACION_ID_PERSONA = "Debe ingresar un Id de persona.";
	
	public static final String PERSONA_NO_ENCONTRADA = "Persona no encontrada!.";
	public static final String PERSONA_REGISTRADA = "La persona se registro correctamente";
	public static final String PERSONA_EXISTENTE = "Persona existente";
	
	public static final String PERSONA_CELULAR = "Debe ingresar un número de celular.";
	public static final String PERSONA_CORREO_ELECTRONICO = "Debe ingresar un correo electrónico.";
	public static final String PERSONA_DIRECCION = "Debe ingresar una dirección de residencia.";
	public static final String PERSONA_FECHA_NACIMIENTO = "Debe ingresar su fecha de nacimiento.";
	public static final String PERSONA_NOMBRE_COMPLETO = "Debe ingresar su nombre completo.";
	public static final String PERSONA_NUMERO_DOCUMENTO = "Debe ingresar su número de documento.";
	public static final String PERSONA_TIPO_DOCUMENTO = "Debe ingresar su tipo de documento.";
	public static final String PERSONA_TELEFONO_FIJO = "Debe ingresar un número de telefono fijo.";
	
	
	/*
	 * Room
	 */
	public static final String HABITACION_NO_ENCONTRADA = "Habitación no encontrada!.";
	public static final String HABITACION_REGISTRADA = "La habitación se registro correctamente!.";
	public static final String HABITACION_EXISTENTE = "Habitación existente.";
	
	public static final String HABITACION_VALIDACION_ID = "Debe ingresar el id de habitación.";
	public static final String HABITACION_HOTEL_ID = "Debe ingresar el ID de hotel.";
	public static final String HABITACION_CHECK_IN = "Debe ingresar la fecha de checkIn.";
	public static final String HABITACION_CHECK_OUT = "Debe ingresar la fecha de checkOut.";
	public static final String HABITACION_TIPO = "Debe seleccionar el tipo de habitación.";
	public static final String HABITACION_ITEM = "Debe seleccionar el item de la habitación.";
	
	
	
	public static final String TIPO_HABITACION_ID = "Debe ingresar el ID del tipo de habitación.";
	public static final String TIPO_HABITACION_HOTEL_ID = "Debe ingresar el ID del hotel.";
	public static final String TIPO_HABITACION_DESCRIPCION = "Debe ingresar la descripción del tipo de habitación.";
	public static final String TIPO_HABITACION_PRECIO_DIA = "Debe ingresar el valor del día.";
	public static final String TIPO_HABITACION_PRECIO_HORA = "Debe ingresar el valor de la hora.";
	public static final String TIPO_HABITACION_REGISTRADA = "El tipo de habitación se registró correctamente!.";
	
	
	/*
	 * Role
	 */
	public static final String ROLE_ID = "Debe ingresar el ID del rol.";
	public static final String ROLE_HOTEL_ID = "Debe ingresar el ID del hotel.";
	public static final String ROLE_NAME = "Debe ingresar el nombre del rol.";
	public static final String ROLE_DESCRIPTION = "Debe ingresar una descripción del rol.";
	public static final String ROLE_PERMITS = "Debe ingresar al menos un permiso para el rol.";
	
	
	/*
	 * 
	 * Reservation 
	 */
	public static final String RESERVATION_ID = "Debe ingresar el ID de la reservación.";
	public static final String RESERVATION_HOTEL_ID = "Debe ingresar el ID del hotel.";
	public static final String RESERVATION_DATE = "Debe ingresar la fecha de la reservación.";
	public static final String RESERVATION_START_DATE = "Debe ingresar la fecha de ingreso.";
	public static final String RESERVATION_EXIT_DATE = "Debe ingresar la fecha de salida.";
	public static final String RESERVATION_ADULT_QUANTITY = "Debe ingresar la cantidad de personas adultas.";
	public static final String RESERVATION_CHILDREN_QUANTITY = "Debe ingresar la cantidad de personas menores.";
	public static final String RESERVATION_FULL_PAYMENT = "Debe ingresar el valor de pago completo.";
	public static final String RESERVATION_ADVANCED_PAYMENT = "Debe ingresar el valor de pago por adelantado.";
	public static final String RESERVATION_PERSON = "Debe ingresar la información de la persona que reserva.";
	
	
	/*
	 * Item
	 * 
	 */
	public static final String ITEM_ID = "Debe ingresar el ID del item.";
	public static final String ITEM_HOTEL_ID = "Debe ingresar el ID del hotel.";
	public static final String ITEM_ACTIVE = "Debe validar si está habilitado o no.";
	public static final String ITEM_QUANTITY = "Debe ingresar la cantidad del item.";
	public static final String ITEM_DESCRIPTION = "Debe ingresar la descripción del item.";
	public static final String ITEM_PRICE = "Debe ingresar el valor del item.";
	public static final String ITEM_STOCK = "Debe ingresar la cantidad en stock.";
	
	
	
	
	
	
}
