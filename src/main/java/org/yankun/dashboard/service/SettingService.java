package org.yankun.dashboard.service;

import java.util.List;

import org.yankun.dashboard.model.setting.PowerDefault;

public interface SettingService {
	PowerDefault getPowerDefaultByHour(int hour);
	List<PowerDefault> getPowerDefaults();
	void updatePowerDefault(PowerDefault powerDefault);
}
