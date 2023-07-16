package at.simonmader.fuelprices.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import at.simonmader.fuelprices.model.FuelPrice;

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

  @Query("select min(fp.jet) from FuelPrice fp where fp.timestamp >= :startDateTime")
  double findLowestJetPriceSinceDate(@Param("startDateTime") Date startDateTime);

  @Query("select min(fp.avanti) from FuelPrice fp where fp.timestamp >= :startDateTime")
  double findLowestAvantiPriceSinceDate(@Param("startDateTime") Date startDateTime);

}
