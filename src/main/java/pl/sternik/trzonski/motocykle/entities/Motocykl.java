package pl.sternik.trzonski.motocykle.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import pl.sternik.trzonski.motocykle.entities.Motocykl;
import pl.sternik.trzonski.motocykle.entities.Status;


public class Motocykl {

    private Long numerKatalogowy;
	
	private String producent;

	private String model;

	private BigDecimal cena;

	private Date dataProdukcji;

	private Status status;

	public Long getNumerKatalogowy() {
		return numerKatalogowy;
	}

	public void setNumerKatalogowy(Long numerKatalogowy) {
		this.numerKatalogowy = numerKatalogowy;
	}

	public String getProducent() {
		return producent;
	}

	public void setProducent(String producent) {
		this.producent = producent;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Date getDataProdukcji() {
		return dataProdukcji;
	}

	public void setDataProdukcji(Date dataProdukcji) {
		this.dataProdukcji = dataProdukcji;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public static Motocykl produceMotocykl(long numerKatalogowy, String producent, String model, BigDecimal cena, Date dataProdukcji, Status status) {
		Motocykl m = new Motocykl();
		m.numerKatalogowy = numerKatalogowy;
		m.producent = producent;
		m.model = model;
		m.cena = cena;
		m.dataProdukcji = dataProdukcji;
		m.status = status;
		return m;
	}


}
