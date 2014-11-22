package org.yankun.dashboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.yankun.dashboard.model.Data;
import org.yankun.dashboard.service.DataService;

@Controller
public class MainController {
	@Autowired
	private DataService dataService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		return new ModelAndView("dashboard");
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView statistics(HttpServletRequest request, ModelMap model) {
		return this.postStatistics(request, model);
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	public ModelAndView postStatistics(HttpServletRequest request, ModelMap model) {
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		List<Data> list = new ArrayList<Data>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null;
		Date endDate = null;
		
		if (startDateStr != null && startDateStr.trim().length() != 0
				&& endDateStr != null && endDateStr.trim().length() != 0) {
			try {
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		if (startDate != null && endDate != null) {
			list = dataService.getDataListByDatePeriod(startDate, endDate);
		}
		model.put("dataList", list);
		model.put("startDate", startDateStr);
		model.put("endDate", endDateStr);
		return new ModelAndView("statistics", model);
	}
}
