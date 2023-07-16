package at.simonmader.fuelprices.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import at.simonmader.fuelprices.model.FuelPrice;
import at.simonmader.fuelprices.repositories.FuelPriceRepository;

@CrossOrigin(origins = "*")
@RestController
public class FuelPriceController {

  private final FuelPriceRepository fuelPriceRepository;

  public FuelPriceController(FuelPriceRepository fuelPriceRepository) {
    this.fuelPriceRepository = fuelPriceRepository;
  }

  @GetMapping("/")
  public List<FuelPrice> getAllFuelPrices() {
    return fuelPriceRepository.findAllOrderByTimestamp();
  }

  @GetMapping("/desc")
  public List<FuelPrice> getAllFuelPricesDesc() {
    return fuelPriceRepository.findAllOrderByTimestampDesc();
  }

  @GetMapping("/jet")
  public List<FuelPrice> getJetPrices() {
    return fuelPriceRepository.findJetPrices();
  }

  @GetMapping("/avanti")
  public List<FuelPrice> getAvantiPrices() {
    return fuelPriceRepository.findAvantiPrices();
  }

  @GetMapping("/latestJet")
  public FuelPrice getLatestJetPrice() {
    return fuelPriceRepository.findJetPricesDesc().get(0);
  }

  @GetMapping("/latestAvanti")
  public FuelPrice getLatestAvantiPrice() {
    return fuelPriceRepository.findAvantiPricesDesc().get(0);
  }

  @GetMapping("/lowestJetWeek")
  public double getLowestJetPriceWeek() {
    LocalDateTime startDateTime = LocalDateTime.now().minusWeeks(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findLowestJetPriceSinceDate(startDate);
  }

  @GetMapping("/lowestAvantiWeek")
  public double getLowestAvantiPriceWeek() {
    LocalDateTime startDateTime = LocalDateTime.now().minusWeeks(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findLowestAvantiPriceSinceDate(startDate);
  }

  @GetMapping("/lowestJetMonth")
  public double getLowestJetPriceMonth() {
    LocalDateTime startDateTime = LocalDateTime.now().minusMonths(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findLowestJetPriceSinceDate(startDate);
  }

  @GetMapping("/lowestAvantiMonth")
  public double getLowestAvantiPriceMonth() {
    LocalDateTime startDateTime = LocalDateTime.now().minusMonths(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findLowestAvantiPriceSinceDate(startDate);
  }

}
