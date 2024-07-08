package com.asset.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.entity.Instrument;
import com.asset.entity.Menu;
import com.asset.model.InstrumentDto;
import com.asset.model.respone.InstrumentVo;

@Transactional
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	@Query(value = "SELECT new com.asset.model.respone.InstrumentVo(o.idNo, o.instrumentName,o.iIN) FROM Instrument o where o.isInstrumentMapped=?1 and o.userid=?2 ")
	List<InstrumentVo> getQueryMapInstrNameAndID(boolean mapped, long userId);

	@Query(value = "SELECT new com.asset.model.respone.InstrumentVo(o.idNo, o.instrumentName,o.iIN) FROM Instrument o where o.isInstrumentMapped=false")
	List<InstrumentVo> getQueryUnMapInstruments();

	@Modifying
	@Query("update Instrument u set u.userid = :userId, u.isInstrumentMapped=:mapped where u.idNo=:instrumentId")
	void updateUseridForMapping(@Param("instrumentId") long instrumentId, @Param("userId") long userId,
			@Param("mapped") boolean mapped);

	@Modifying
	@Query("update Instrument u set u.userid = 0, u.isInstrumentMapped=false where u.userid=:userId")
	void updateUseridForUnMapping(@Param("userId") long userId);

	@Query(value = "SELECT new com.asset.model.InstrumentDto(calibrationDate, userid,instrumentName,iIN) FROM Instrument WHERE to_char(calibrationDate, 'YYYY-MM-dd')=to_char(CURRENT_DATE+3,'YYYY-MM-dd') and isInstrumentMapped=true and isMailSent=false")
	List<InstrumentDto> getInstrumentCalibrationDetails();
	
	@Modifying
	@Query("update Instrument u set u.isMailSent=true where u.userid=:userId")
	void updateIsMailSent(@Param("userId") long userId);
	
	public List<Instrument> findByClientAdminId(long clientAdminId);
	
	public List<Instrument> findByUserid(long clientAdminId);
	
	public List<Instrument> findByInstrumentStatus(String  instrumentStatus);
	
}
