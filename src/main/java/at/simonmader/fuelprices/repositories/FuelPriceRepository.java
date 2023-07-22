package at.simonmader.fuelprices.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import at.simonmader.fuelprices.model.FuelPrice;
import at.simonmader.fuelprices.model.FuelPriceWithoutDate;

@Repository
public interface FuelPriceRepository extends JpaRepository<FuelPrice, Date> {

  @Query("select fp from FuelPrice fp order by fp.timestamp")
  List<FuelPrice> findAllOrderByTimestamp();

  @Query("select fp from FuelPrice fp order by fp.timestamp desc")
  List<FuelPrice> findAllOrderByTimestampDesc();

  @Query("select fp from FuelPrice fp where fp.jet != null")
  List<FuelPrice> findJetPrices();

  @Query("select fp from FuelPrice fp where fp.avanti != null")
  List<FuelPrice> findAvantiPrices();

  @Query("select fp from FuelPrice fp where fp.jet != null order by fp.timestamp desc")
  List<FuelPrice> findJetPricesDesc();

  @Query("select fp from FuelPrice fp where fp.avanti != null order by fp.timestamp desc")
  List<FuelPrice> findAvantiPricesDesc();

  @Query("select fp from FuelPrice fp where fp.jetLangenrohr != null order by fp.timestamp desc")
  List<FuelPrice> findJetLangenrohrPricesDesc();

  @Query("select fp from FuelPrice fp where fp.bp != null order by fp.timestamp desc")
  List<FuelPrice> findBpPricesDesc();

  @Query("select min(fp.avanti) as avanti, min(fp.jet) as jet, min(fp.jetLangenrohr) as jetLangenrohr, min(fp.bp) as bp from FuelPrice fp")
  List<FuelPriceWithoutDate> findLowest();

  @Query("select min(fp.avanti) as avanti, min(fp.jet) as jet, min(fp.jetLangenrohr) as jetLangenrohr, min(fp.bp) as bp from FuelPrice fp where fp.timestamp >= :startDate")
  List<FuelPriceWithoutDate> findLowestSinceDays(Date startDate);

  @Query("select max(fp.avanti) as avanti, max(fp.jet) as jet, max(fp.jetLangenrohr) as jetLangenrohr, max(fp.bp) as bp from FuelPrice fp")
  List<FuelPriceWithoutDate> findHighest();

  @Query("select max(fp.avanti) as avanti, max(fp.jet) as jet, max(fp.jetLangenrohr) as jetLangenrohr, max(fp.bp) as bp from FuelPrice fp where fp.timestamp >= :startDate")
  List<FuelPriceWithoutDate> findHighestSinceDays(Date startDate);

}
