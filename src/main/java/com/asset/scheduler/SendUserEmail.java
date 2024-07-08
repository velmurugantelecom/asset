package com.asset.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asset.model.InstrumentDto;
import com.asset.model.UserDto;
import com.asset.repositories.InstrumentRepository;
import com.asset.repositories.UsersRepository;
import com.asset.util.Utility;

@Component

public class SendUserEmail {
	@Autowired
	InstrumentRepository instrumentRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Scheduled(cron = "${cron.instrument.calibration.mail}")
	public void scheduleTaskUsingCronExpression() {
		List<InstrumentDto> instrumentDtoList = instrumentRepository.getInstrumentCalibrationDetails();
		System.out.println("scheduleTaskUsingCronExpression");
		for (int instrumentDto = 0; instrumentDto < instrumentDtoList.size(); instrumentDto++) {
			long userId=instrumentDtoList.get(instrumentDto).getUserId();
			UserDto userDto=usersRepository.getUserDetails(userId);
			String emailId =userDto.getEmail();
			String InstrumentIncharge = userDto.getName();
			String InstrumentName = instrumentDtoList.get(instrumentDto).getInstrumentName();
			String iin = instrumentDtoList.get(instrumentDto).getIIN();
			String nextCalibrationDate = Utility.dateFormatType1(instrumentDtoList.get(instrumentDto).getCalibrationdate());
			String emailText = "Hi " + InstrumentIncharge + "," + "\n\nYour Instrument's next calibration date is "
					+ nextCalibrationDate + "\n\nInstrument Details" + "\n\nInstrument IIN : " + iin
					+ "\n\nInstrument Name : " + InstrumentName;
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emailId);
			msg.setSubject("Remainder:-Instrument Next Calibration Date");
			msg.setText(emailText);
			instrumentRepository.updateIsMailSent(userId);
			javaMailSender.send(msg);

		}
	}
}
