package br.com.caelum.livraria.jms;

import java.io.Serializable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.caelum.livraria.jaxb.SerializadorXml;
import br.com.caelum.livraria.modelo.Pedido;

@Component
@Lazy(true)
public class EnviadorMensagemJms implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConnectionFactory factory;
	
	@Autowired
	private Topic topic;

	public void enviar(Pedido pedido) throws JAXBException {
		System.out.println("JMS: Enviando pedido:" + pedido);
		
		try(JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			//producer.send(topic, pedido.toString());
			
			String xml = new SerializadorXml().toXml(pedido);
			System.out.println(xml);
			producer.send(topic, xml);
		}
	}
}
