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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import at.simonmader.fuelprices.model.BotChatId;
import at.simonmader.fuelprices.model.FuelPrice;
import at.simonmader.fuelprices.model.FuelPriceWithoutDate;
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

  // FuelPrice
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

  @GetMapping("/latestJetLangenrohr")
  public FuelPrice getLatestJetLangenrohrPrice() {
    return fuelPriceRepository.findJetLangenrohrPricesDesc().get(0);
  }

  @GetMapping("/latestBp")
  public FuelPrice getLatestBpPrice() {
    return fuelPriceRepository.findBpPricesDesc().get(0);
  }

  @GetMapping("/latest")
  public FuelPriceWithoutDate getLatest() {
    double avanti = getLatestAvantiPrice().getAvanti();
    double jet = getLatestJetPrice().getJet();
    double jetLangenrohr = getLatestJetLangenrohrPrice().getJetLangenrohr();
    double bp = getLatestBpPrice().getBp();
    return new FuelPriceWithoutDate() {

      @Override
      public double getAvanti() {
        return avanti;
      }

      @Override
      public double getJet() {
        return jet;
      }

      @Override
      public double getJetLangenrohr() {
        return jetLangenrohr;
      }

      @Override
      public double getBp() {
        return bp;
      }

    };
  }

  @GetMapping("/lowestSinceDays")
  public FuelPriceWithoutDate getLowestSinceDays(@RequestParam(defaultValue = "0") int days) {
    if (days == 0)
      return fuelPriceRepository.findLowest().get(0);
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(days);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findLowestSinceDays(startDate).get(0);
  }

  @GetMapping("/highestSinceDays")
  public FuelPriceWithoutDate getHighestSinceDays(@RequestParam(defaultValue = "0") int days) {
    if (days == 0)
      return fuelPriceRepository.findHighest().get(0);
    LocalDateTime startDateTime = LocalDateTime.now().minusDays(days);
    Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return fuelPriceRepository.findHighestSinceDays(startDate).get(0);
  }

  // BotChatId
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
