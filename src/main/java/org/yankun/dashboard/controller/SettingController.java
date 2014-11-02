package org.yankun.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yankun.dashboard.model.setting.PowerDefault;
import org.yankun.dashboard.service.SettingService;

/**
 * 
 * @author kun.yan
 *
 */
@Controller
@RequestMapping("/setting")
public class SettingController {
	
	@Autowired
	private SettingService settingService;

	@RequestMapping(value = "/power", method = RequestMethod.GET)
	public List<PowerDefault> getPowerDefaults() {
		return settingService.getPowerDefaults();
	}
}
