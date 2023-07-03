package at.simonmader.fuelprices.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuelprices")
public class FuelPrice {

  @Id
  @Column(name = "timestamp")
  private Date timestamp;

  @Column(name = "jet")
  private Double jet;

  @Column(name = "avanti")
  private Double avanti;

  public FuelPrice() {

  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public Double getJet() {
    return jet;
  }

  public void setJet(Double jet) {
    this.jet = jet;
  }

  public Double getAvanti() {
    return avanti;
  }

  public void setAvanti(Double avanti) {
    this.avanti = avanti;
  }

}
