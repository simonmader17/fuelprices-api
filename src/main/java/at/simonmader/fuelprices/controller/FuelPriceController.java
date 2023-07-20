package at.simonmader.fuelprices.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import at.simonmader.fuelprices.model.BotChatId;
import at.simonmader.fuelprices.model.FuelPrice;
import at.simonmader.fuelprices.repositories.BotChatIdRepository;
import at.simonmader.fuelprices.repositories.FuelPriceRepository;

@CrossOrigin(origins = "*")
@RestController
public class FuelPriceController {

  private final FuelPriceRepository fuelPriceRepository;
  private final BotChatIdRepository botChatIdRepository;

  public FuelPriceController(FuelPriceRepository fuelPriceRepository,
      BotChatIdRepository botChatIdRepository) {
    this.fuelPriceRepository = fuelPriceRepository;
    this.botChatIdRepository = botChatIdRepository;
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

  @GetMapping("/highestJetWeek")
  public double getHighestJetPriceWeek() {
    LocalDateTime startDateTime = LocalDateTime.now().minusWeeks(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findHighestJetPriceSinceDate(startDate);
  }

  @GetMapping("/highestAvantiWeek")
  public double getHighestAvantiPriceWeek() {
    LocalDateTime startDateTime = LocalDateTime.now().minusWeeks(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findHighestAvantiPriceSinceDate(startDate);
  }

  @GetMapping("/highestJetMonth")
  public double getHighestJetPriceMonth() {
    LocalDateTime startDateTime = LocalDateTime.now().minusMonths(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findHighestJetPriceSinceDate(startDate);
  }

  @GetMapping("/highestAvantiMonth")
  public double getHighestAvantiPriceMonth() {
    LocalDateTime startDateTime = LocalDateTime.now().minusMonths(1);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findHighestAvantiPriceSinceDate(startDate);
  }

  @GetMapping("/chatIDs")
  public List<Integer> getAllChatIds() {
    return botChatIdRepository.findAllChatIds();
  }

  @PostMapping("/chatIDs")
  public BotChatId insertChatId(@RequestBody BotChatId chatId) {
    return botChatIdRepository.save(chatId);
  }

  @DeleteMapping("/chatIDs")
  public BotChatId deleteChatId(@RequestBody BotChatId chatId) {
    botChatIdRepository.delete(chatId);
    return chatId;
  }

}
